package net.mendler.echoxul.elements;

import java.io.Serializable;

import net.mendler.echoxul.XulApplication;
import net.mendler.echoxul.compiler.CompilerException;
import nextapp.echo2.app.Component;

public interface IXulElement extends Serializable, Cloneable {
    void setParent(IXulElement parent);

    IXulElement getParent();

    String getID();

    Component getComponent();

    XulApplication getApplication();

    void init(XulApplication app) throws CompilerException;

    IXulElement clone();
}
