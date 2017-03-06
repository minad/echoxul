package net.mendler.echoxul.elements;

import java.util.Collection;

public interface IXulContainer extends IXulElement {
    void addChild(IXulElement child);

    void removeChild(IXulElement child);

    Collection<IXulElement> getChildren();

    int getChildCount();
}
