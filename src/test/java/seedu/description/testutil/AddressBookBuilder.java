package seedu.description.testutil;

import seedu.description.model.BugList;
import seedu.description.model.bug.Bug;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code BugList ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BugList bugList;

    public AddressBookBuilder() {
        bugList = new BugList();
    }

    public AddressBookBuilder(BugList bugList) {
        this.bugList = bugList;
    }

    /**
     * Adds a new {@code Bug} to the {@code BugList} that we are building.
     */
    public AddressBookBuilder withPerson(Bug bug) {
        bugList.addPerson(bug);
        return this;
    }

    public BugList build() {
        return bugList;
    }
}
