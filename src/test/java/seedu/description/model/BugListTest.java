package seedu.description.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.description.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.description.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.exceptions.DuplicateBugException;
import seedu.description.testutil.Assert;
import seedu.description.testutil.PersonBuilder;
import seedu.description.testutil.TypicalPersons;

public class BugListTest {

    private final BugList bugList = new BugList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bugList.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> bugList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        BugList newData = TypicalPersons.getTypicalAddressBook();
        bugList.resetData(newData);
        assertEquals(newData, bugList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two bugs with the same identity fields
        Bug editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        List<Bug> newBugs = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newBugs);

        Assert.assertThrows(DuplicateBugException.class, () -> bugList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> bugList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(bugList.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        bugList.addPerson(TypicalPersons.ALICE);
        assertTrue(bugList.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        bugList.addPerson(TypicalPersons.ALICE);
        Bug editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(bugList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> bugList.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose bugs list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Bug> bugs = FXCollections.observableArrayList();

        AddressBookStub(Collection<Bug> bugs) {
            this.bugs.setAll(bugs);
        }

        @Override
        public ObservableList<Bug> getPersonList() {
            return bugs;
        }
    }

}
