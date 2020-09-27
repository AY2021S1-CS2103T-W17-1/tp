package seedu.description.model.bug;

import static seedu.description.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.description.testutil.Assert;
import seedu.description.testutil.PersonBuilder;
import seedu.description.testutil.TypicalPersons;

public class BugTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Bug bug = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> bug.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(null));

        // different phone and email -> returns false
        Bug editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Bug aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(5));

        // different bug -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different name -> returns false
        Bug editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }
}
