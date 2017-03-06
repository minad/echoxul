package net.mendler.echoxul.compiler;

import java.io.Serializable;

public interface IContext extends Serializable {

    Object get(String name);

    void set(String name, Object value);

    IContext getParent();

    IContext createChild();

    void compileDefinition(String code) throws CompilerException;

    IExecutable compileExecutable(String code) throws CompilerException;
}
