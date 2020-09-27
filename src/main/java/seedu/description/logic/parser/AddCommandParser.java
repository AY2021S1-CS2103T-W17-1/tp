package seedu.description.logic.parser;

import static seedu.description.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.description.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.description.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.description.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.description.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.description.logic.commands.AddCommand;
import seedu.description.logic.parser.exceptions.ParseException;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.Description;
import seedu.description.model.bug.Name;
import seedu.description.model.bug.Location;
import seedu.description.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Location location = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Description description = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Bug bug = new Bug(name, location, description, tagList);

        return new AddCommand(bug);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
