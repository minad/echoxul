package net.mendler.echoxul;

import java.io.File;
import java.io.Serializable;

public class ApplicationOptions implements Serializable {

    public static final long serialVersionUID = 0;

    private String name;

    private String style;

    private String language;

    private File tempDir;

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getStyle() {
	return style;
    }

    public void setStyle(String style) {
	this.style = style;
    }

    public File getTempDir() {
	return tempDir;
    }

    public void setTempDir(File tempDir) {
	this.tempDir = tempDir;
    }
}
