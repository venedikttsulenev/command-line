package commands;

import utils.Environment;

public abstract class Command {

    private final String name;
    private final String usage;
    private final String description;

    protected Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    abstract public void execute(String[] args, Environment env);
}
