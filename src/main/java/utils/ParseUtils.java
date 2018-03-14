package utils;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final Pattern argumentPattern = Pattern.compile("(?<quoted>\"[^\"]*\")|\\S+");

    private static String[] splitArguments(String argsString) {
        Matcher matcher = argumentPattern.matcher(argsString);
        LinkedList<String> argsList = new LinkedList<>();
        while (matcher.find()) {
            String match = matcher.group();
            if (matcher.group("quoted") != null)
                argsList.add(match.substring(1, match.length() - 1));
            else
                argsList.add(match);
        }
        return argsList.toArray(new String[argsList.size()]);
    }

    public static ParseResult parseCommand(String line) {
        String commandName = line;
        String[] arguments;
        int whitespaceIndex = 0;
        while (whitespaceIndex < line.length() && !Character.isWhitespace(line.charAt(whitespaceIndex)))
            ++whitespaceIndex;
        if (whitespaceIndex != line.length()) { /* There are some arguments */
            commandName = line.substring(0, whitespaceIndex);
            while (Character.isWhitespace(line.charAt(whitespaceIndex)))
                ++whitespaceIndex;
            arguments = splitArguments(line.substring(whitespaceIndex));
        } else { /* There is no arguments */
            arguments = new String[0];
        }
        return new ParseResult(commandName, arguments);
    }
}
