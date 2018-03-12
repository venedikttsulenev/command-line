package commands;

import utils.Environment;

public class PwdCommand extends Command {

    private static final PwdCommand instance = new PwdCommand();

    public static PwdCommand getInstance() {
        return instance;
    }

    private PwdCommand() {
        super("pwd", "pwd", "print working directory");
    }

    @Override
    public void execute(String[] args, Environment env) {
        env.getConsole().printf("%s%n", env.getCurrentDirectory().toString());
    }
}
