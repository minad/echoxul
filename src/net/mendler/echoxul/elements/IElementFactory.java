package net.mendler.echoxul.elements;

import java.util.Map;

public interface IElementFactory {
    IXulElement create(Map<String, String> attrs, String content);
}
