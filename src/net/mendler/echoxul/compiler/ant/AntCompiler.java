package net.mendler.echoxul.compiler.ant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import net.mendler.echoxul.ApplicationOptions;
import net.mendler.echoxul.compiler.CompilerException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;

public class AntCompiler {
    private URLClassLoader loader;

    private Project project;

    private String className;

    private File tempDir;

    private static HashMap<String, AntCompiler> compilers = new HashMap<String, AntCompiler>();

    public static ICompiledContext compile(ApplicationOptions options,
	    AntContext context) throws CompilerException {
	AntCompiler compiler = compilers.get(options.getName());
	if (compiler == null) {
	    compiler = new AntCompiler(options);
	    compilers.put(options.getName(), compiler);
	}
	return compiler.compile(context);
    }

    private AntCompiler(ApplicationOptions options) {
	className = "Generated_" + options.getName();
	tempDir = options.getTempDir();
    }

    private ICompiledContext compile(AntContext context)
	    throws CompilerException {
	try {
	    ICompiledContext compiled = (ICompiledContext) createInstance(className);
	    if (compiled == null) {
		generateJava(className, context);
		generateClass(className);
		compiled = (ICompiledContext) createInstance(className);
		if (compiled == null)
		    throw new CompilerException("Class not found");
	    }
	    return compiled;
	} catch (IOException ex) {
	    throw new CompilerException(ex);
	} catch (BuildException ex) {
	    throw new CompilerException(ex);
	}
    }

    private Object createInstance(String className) {
	try {
	    ClassLoader loader = getClassLoader();
	    Class clazz = loader.loadClass("net.mendler.echoxul.compiler.ant."
		    + className);
	    return clazz.newInstance();
	} catch (ClassNotFoundException ex) {
	    return null;
	} catch (InstantiationException ex) {
	    throw new RuntimeException(ex);
	} catch (IllegalAccessException ex) {
	    throw new RuntimeException(ex);
	}
    }

    private URLClassLoader getClassLoader() {
	if (loader == null) {
	    try {
		loader = new URLClassLoader(new URL[] { tempDir.toURL() },
			getCurrentClassLoader());
	    } catch (MalformedURLException ex) {
		throw new RuntimeException(ex);
	    }
	}
	return loader;
    }

    private URLClassLoader getCurrentClassLoader() {
	URLClassLoader loader = (URLClassLoader) Thread.currentThread()
		.getContextClassLoader();
	if (loader == null)
	    loader = (URLClassLoader) getClass().getClassLoader();
	return loader;
    }

    private Path getClassPath() {
	Path path = new Path(project);
	path.setPath(System.getProperty("java.class.path"));

	URLClassLoader loader = getCurrentClassLoader();

	URL[] urls = loader.getURLs();
	StringBuilder buffer = new StringBuilder();
	for (int i = 0; i < urls.length; ++i) {
	    if (urls[i].getProtocol().equals("file"))
		buffer.append((String) urls[i].getFile() + File.pathSeparator);
	}
	path.setPath(buffer.toString());

	return path;
    }

    private void generateJava(String className, AntContext context)
	    throws IOException {
	Writer writer = new FileWriter(new File(tempDir, className + ".java"));
	try {
	    new AntGenerator(writer, context, className).generate();
	} finally {
	    writer.close();
	}
    }

    private void generateClass(String className) throws IOException,
	    BuildException {
	Path srcPath = new Path(getProject());
	srcPath.setLocation(tempDir);

	Javac javac = (Javac) getProject().createTask("javac");
	javac.setSrcdir(srcPath);
	javac.setDestdir(tempDir);
	javac.setClasspath(getClassPath());
	javac.setDebugLevel("lines");
	javac.setDebug(true);
	javac.execute();
    }

    private Project getProject() {
	if (project == null) {
	    project = new Project();
	    String basedir = System.getProperty("echoxul.basedir");
	    if (basedir != null)
		project.setBasedir(basedir);
	    project.init();
	}
	return project;
    }
}
