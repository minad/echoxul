package net.mendler.echoxul;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.elements.ElementFactory;
import net.mendler.echoxul.elements.IXulContainer;
import net.mendler.echoxul.elements.IXulElement;
import net.mendler.echoxul.elements.XulWindow;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XulLoader {

    private XulWindow rootWindow;

    public XulLoader(InputStream in) throws XulException {
	Document document;
	try {
	    DocumentBuilderFactory factory = DocumentBuilderFactory
		    .newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    document = builder.parse(in);
	} catch (IOException ex) {
	    throw new XulException(ex);
	} catch (ParserConfigurationException ex) {
	    throw new XulException(ex);
	} catch (SAXException ex) {
	    throw new XulException(ex);
	}

	rootWindow = (XulWindow) walkTree(document.getDocumentElement());
    }

    private IXulElement walkTree(Element node) throws CompilerException {
	NodeList children = node.getChildNodes();
	String content = null;
	if (children.getLength() == 1
		&& children.item(0) instanceof CharacterData)
	    content = children.item(0).getTextContent();

	IXulElement element = ElementFactory.create(node.getNodeName(),
		toMap(node.getAttributes()), content);

	if (element instanceof IXulContainer) {
	    IXulContainer container = (IXulContainer) element;
	    for (int i = 0; i < children.getLength(); ++i) {
		Node child = children.item(i);
		if (child.getNodeType() == Node.ELEMENT_NODE)
		    container.addChild(walkTree((Element) child));
	    }
	}

	return element;
    }

    public XulWindow getRootWindow() {
	return rootWindow;
    }

    private Map<String, String> toMap(NamedNodeMap nodeMap) {
	Map<String, String> result = new HashMap<String, String>();
	for (int i = 0; i < nodeMap.getLength(); ++i) {
	    Node node = nodeMap.item(i);
	    result.put(node.getNodeName(), node.getNodeValue());
	}
	return result;
    }
}
