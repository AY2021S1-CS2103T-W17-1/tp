package seedu.address.model.bug;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.bug.exceptions.DuplicateBugException;
import seedu.address.model.bug.exceptions.BugNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueBugListTest {

    private final UniqueBugList uniqueBugList = new UniqueBugList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueBugList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueBugList.add(ALICE);
        assertTrue(uniqueBugList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBugList.add(ALICE);
        Bug editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueBugList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueBugList.add(ALICE);
        assertThrows(DuplicateBugException.class, () -> uniqueBugList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(BugNotFoundException.class, () -> uniqueBugList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueBugList.add(ALICE);
        uniqueBugList.setPerson(ALICE, ALICE);
        UniqueBugList expectedUniqueBugList = new UniqueBugList();
        expectedUniqueBugList.add(ALICE);
        assertEquals(expectedUniqueBugList, uniqueBugList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueBugList.add(ALICE);
        Bug editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueBugList.setPerson(ALICE, editedAlice);
        UniqueBugList expectedUniqueBugList = new UniqueBugList();
        expectedUniqueBugList.add(editedAlice);
        assertEquals(expectedUniqueBugList, uniqueBugList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueBugList.add(ALICE);
        uniqueBugList.setPerson(ALICE, BOB);
        UniqueBugList expectedUniqueBugList = new UniqueBugList();
        expectedUniqueBugList.add(BOB);
        assertEquals(expectedUniqueBugList, uniqueBugList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBugList.add(ALICE);
        uniqueBugList.add(BOB);
        assertThrows(DuplicateBugException.class, () -> uniqueBugList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(BugNotFoundException.class, () -> uniqueBugList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueBugList.add(ALICE);
        uniqueBugList.remove(ALICE);
        UniqueBugList expectedUniqueBugList = new UniqueBugList();
        assertEquals(expectedUniqueBugList, uniqueBugList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.setPersons((UniqueBugList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueBugList.add(ALICE);
        UniqueBugList expectedUniqueBugList = new UniqueBugList();
        expectedUniqueBugList.add(BOB);
        uniqueBugList.setPersons(expectedUniqueBugList);
        assertEquals(expectedUniqueBugList, uniqueBugList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBugList.setPersons((List<Bug>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueBugList.add(ALICE);
        List<Bug> bugList = Collections.singletonList(BOB);
        uniqueBugList.setPersons(bugList);
        UniqueBugList expectedUniqueBugList = new UniqueBugList();
        expectedUniqueBugList.add(BOB);
        assertEquals(expectedUniqueBugList, uniqueBugList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Bug> listWithDuplicateBugs = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateBugException.class, () -> uniqueBugList.setPersons(listWithDuplicateBugs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBugList.asUnmodifiableObservableList().remove(0));
    }
}
