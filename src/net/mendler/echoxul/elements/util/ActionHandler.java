package net.mendler.echoxul.elements.util;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

public class ActionHandler extends Handler implements ActionListener {

    public static final long serialVersionUID = 0;

    public ActionHandler(String source, IContext context)
	    throws CompilerException {
	super(source, context);
    }

    public void actionPerformed(ActionEvent e) {
	execute();
    }
}
