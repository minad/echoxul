package net.mendler.echoxul.compiler;

import java.io.Serializable;

public interface IExecutable extends Serializable {
    IContext getContext();

    void execute() throws Exception;
}
