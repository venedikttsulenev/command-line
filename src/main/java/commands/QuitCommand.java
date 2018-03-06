package commands;

import utils.Environment;

public class QuitCommand extends Command {
    private static final QuitCommand instance = new QuitCommand();

    public static QuitCommand getInstance() {
        return instance;
    }

    private QuitCommand() {
        super("quit", "quit", "quit");
    }

    @Override
    public void execute(String[] args, Environment env) {
        env.exit();
    }
}
