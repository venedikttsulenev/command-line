package commands;

import utils.Environment;

public class QuitCommand implements Command {

    private static final QuitCommand instance = new QuitCommand();

    public static QuitCommand getInstance() {
        return instance;
    }

    private QuitCommand() {}

    @Override
    public void execute(String[] args, Environment env) {
        env.exit();
    }
}
