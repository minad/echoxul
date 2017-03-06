package net.mendler.echoxul;

import java.io.File;
import java.io.InputStream;

import javax.servlet.ServletException;

import net.mendler.echoxul.elements.XulWindow;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.webcontainer.WebContainerServlet;

import org.apache.log4j.Logger;

public class XulServlet extends WebContainerServlet {

    public static final long serialVersionUID = 0;

    private static final String PARAM_XUL = "xul";

    private static final String PARAM_STYLE = "style";

    private static final String PARAM_LANGUAGE = "language";

    private static final Logger logger = Logger.getLogger(XulServlet.class);

    private XulWindow rootWindow;

    private ApplicationOptions options = new ApplicationOptions();

    @Override
    public void init() throws ServletException {
	super.init();

	String resource = getInitParameter(PARAM_XUL);
	if (resource == null) {
	    logger.error(PARAM_XUL + " not specified");
	    throw new ServletException();
	}

	try {
	    InputStream in = getClass().getClassLoader().getResourceAsStream(
		    resource);
	    rootWindow = new XulLoader(in).getRootWindow();
	} catch (XulException ex) {
	    throw new ServletException(ex);
	}

	options.setName(getServletName());
	options.setTempDir((File) getServletContext().getAttribute(
		"javax.servlet.context.tempdir"));

	String style = getInitParameter(PARAM_STYLE);
	if (style == null) {
	    logger.error(PARAM_STYLE + " not specified");
	    throw new ServletException();
	}
	options.setStyle(style);

	String language = getInitParameter(PARAM_LANGUAGE);
	if (language == null) {
	    logger.error(PARAM_LANGUAGE + " not specified");
	    throw new ServletException();
	}
	options.setLanguage(language);
    }

    @Override
    public ApplicationInstance newApplicationInstance() {
	logger.info("XulServlet init...");
	return new XulApplication(rootWindow.clone(), options);
    }
}
