package seedu.address.testutil;

import seedu.address.model.BugList;
import seedu.address.model.person.Person;

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
     * Adds a new {@code Person} to the {@code BugList} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        bugList.addPerson(person);
        return this;
    }

    public BugList build() {
        return bugList;
    }
}
