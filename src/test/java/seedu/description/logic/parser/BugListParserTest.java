package seedu.description.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.description.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.description.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.description.logic.commands.AddCommand;
import seedu.description.logic.commands.ClearCommand;
import seedu.description.logic.commands.DeleteCommand;
import seedu.description.logic.commands.EditCommand;
import seedu.description.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.description.logic.commands.ExitCommand;
import seedu.description.logic.commands.FindCommand;
import seedu.description.logic.commands.HelpCommand;
import seedu.description.logic.commands.ListCommand;
import seedu.description.logic.parser.exceptions.ParseException;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.NameContainsKeywordsPredicate;
import seedu.description.testutil.Assert;
import seedu.description.testutil.EditPersonDescriptorBuilder;
import seedu.description.testutil.PersonBuilder;
import seedu.description.testutil.PersonUtil;
import seedu.description.testutil.TypicalIndexes;

public class BugListParserTest {

    private final BugListParser parser = new BugListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Bug bug = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(bug));
        assertEquals(new AddCommand(bug), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Bug bug = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(bug).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
