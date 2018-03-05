package commands;

import utils.Environment;

public interface Command {
    void execute(String[] args, Environment env);
}
