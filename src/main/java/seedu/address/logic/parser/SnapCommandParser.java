package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Files;
import java.nio.file.Path;

import seedu.address.logic.commands.SnapCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SnapCommand object
 */
public class SnapCommandParser implements Parser<SnapCommand> {
    private static final String VALIDATION_REGEX = "^[a-zA-Z0-9_-]*$";

    /**
     * Parses the given {@code String} of arguments in the context of the SnapCommand
     * and returns a SnapCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SnapCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SnapCommand.MESSAGE_USAGE));
        } else if (!trimmedArgs.matches(VALIDATION_REGEX) || trimmedArgs.length() > 100) {
            throw new ParseException(SnapCommand.MESSAGE_CONSTRAINTS);
        } else if (Files.exists(Path.of("data", trimmedArgs + ".json"))) {
            throw new ParseException(String.format(SnapCommand.MESSAGE_WARNING, trimmedArgs + ".json"));
        }

        return new SnapCommand(trimmedArgs);
    }

}
