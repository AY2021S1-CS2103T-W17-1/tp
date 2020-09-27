package seedu.description.logic.parser;

import static seedu.description.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.description.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.description.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.description.logic.commands.AddCommand;
import seedu.description.logic.commands.CommandTestUtil;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.Description;
import seedu.description.model.bug.Location;
import seedu.description.model.bug.Name;
import seedu.description.model.tag.Tag;
import seedu.description.testutil.PersonBuilder;
import seedu.description.testutil.TypicalPersons;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Bug expectedBug = new PersonBuilder(TypicalPersons.BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedBug));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedBug));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedBug));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedBug));

        // multiple tags - all accepted
        Bug expectedBugMultipleTags = new PersonBuilder(TypicalPersons.BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND,
                CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedBugMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Bug expectedBug = new PersonBuilder(TypicalPersons.AMY).withTags().build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.ADDRESS_DESC_AMY, new AddCommand(expectedBug));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.VALID_PHONE_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.VALID_PHONE_BOB
                        + CommandTestUtil.VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND, Location.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.INVALID_ADDRESS_DESC + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.INVALID_TAG_DESC
                + CommandTestUtil.VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
