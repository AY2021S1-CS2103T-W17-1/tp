package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BugList;
import seedu.address.model.ReadOnlyBugList;
import seedu.address.model.bug.Bug;

/**
 * An Immutable BugList that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate bug(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyBugList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyBugList source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code BugList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BugList toModelType() throws IllegalValueException {
        BugList bugList = new BugList();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Bug bug = jsonAdaptedPerson.toModelType();
            if (bugList.hasPerson(bug)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            bugList.addPerson(bug);
        }
        return bugList;
    }

}
