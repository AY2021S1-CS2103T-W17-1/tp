package seedu.description.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.description.model.bug.Bug;
import seedu.description.model.bug.Description;
import seedu.description.model.bug.Location;
import seedu.description.model.bug.Name;
import seedu.description.model.tag.Tag;
import seedu.description.model.util.SampleDataUtil;

/**
 * A utility class to help with building Bug objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Location location;
    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        location = new Location(DEFAULT_PHONE);
        description = new Description(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code bugToCopy}.
     */
    public PersonBuilder(Bug bugToCopy) {
        name = bugToCopy.getName();
        location = bugToCopy.getLocation();
        description = bugToCopy.getDescription();
        tags = new HashSet<>(bugToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Bug} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Bug} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Bug} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.description = new Description(address);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Bug} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.location = new Location(phone);
        return this;
    }

    public Bug build() {
        return new Bug(name, location, description, tags);
    }

}
