package seedu.description.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.description.model.BugList;
import seedu.description.model.ReadOnlyAddressBook;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.Description;
import seedu.description.model.bug.Location;
import seedu.description.model.bug.Name;
import seedu.description.model.tag.Tag;

/**
 * Contains utility methods for populating {@code BugList} with sample data.
 */
public class SampleDataUtil {
    public static Bug[] getSamplePersons() {
        return new Bug[] {
            new Bug(new Name("Alex Yeoh"), new Location("87438807"),
                new Description("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Bug(new Name("Bernice Yu"), new Location("99272758"),
                new Description("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Bug(new Name("Charlotte Oliveiro"), new Location("93210283"),
                new Description("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Bug(new Name("David Li"), new Location("91031282"),
                new Description("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Bug(new Name("Irfan Ibrahim"), new Location("92492021"),
                new Description("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Bug(new Name("Roy Balakrishnan"), new Location("92624417"),
                new Description("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        BugList sampleBl = new BugList();
        for (Bug sampleBug : getSamplePersons()) {
            sampleBl.addPerson(sampleBug);
        }
        return sampleBl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
