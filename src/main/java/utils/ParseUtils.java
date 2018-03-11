package utils;

public class ParseUtils {
    public static class ParseResult {

        private String commandName;
        private String[] args;

        private ParseResult(String command, String[] args) {
            this.commandName = command;
            this.args = args;
        }

        public String getCommandName() {
            return commandName;
        }

        public String[] getArgs() {
            return args;
        }
    }

    public static ParseResult parseCommand(String line) {
        String commandName = line;
        String[] arguments = null;
        int spaceIndex = 0;
        while (spaceIndex < line.length() && !Character.isWhitespace(line.charAt(spaceIndex)))
            ++spaceIndex;
        if (spaceIndex != line.length()) { /* There are some arguments */
            commandName = line.substring(0, spaceIndex);
            while (Character.isWhitespace(line.charAt(spaceIndex)))
                ++spaceIndex;
            arguments = line.substring(spaceIndex).split("\\s+");
        }
        return new ParseResult(commandName, arguments);
    }
}
