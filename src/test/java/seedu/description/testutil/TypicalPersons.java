package seedu.description.testutil;

import static seedu.description.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.description.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.description.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.description.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.description.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.description.model.BugList;
import seedu.description.model.bug.Bug;

/**
 * A utility class containing a list of {@code Bug} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Bug ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Bug BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Bug CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAddress("wall street").build();
    public static final Bug DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withAddress("10th street").withTags("friends").build();
    public static final Bug ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withAddress("michegan ave").build();
    public static final Bug FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withAddress("little tokyo").build();
    public static final Bug GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withAddress("4th street").build();

    // Manually added
    public static final Bug HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withAddress("little india").build();
    public static final Bug IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withAddress("chicago ave").build();

    // Manually added - Bug's details found in {@code CommandTestUtil}
    public static final Bug AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Bug BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code BugList} with all the typical persons.
     */
    public static BugList getTypicalAddressBook() {
        BugList ab = new BugList();
        for (Bug bug : getTypicalPersons()) {
            ab.addPerson(bug);
        }
        return ab;
    }

    public static List<Bug> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
