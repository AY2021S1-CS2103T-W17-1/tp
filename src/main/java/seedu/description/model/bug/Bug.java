package seedu.description.model.bug;

import static seedu.description.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.description.model.tag.Tag;

/**
 * Represents a Bug in the description book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Bug {

    // Identity fields
    private final Name name;
    private final Location location;

    // Data fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Bug(Name name, Location location, Description description, Set<Tag> tags) {
        requireAllNonNull(name, location, description, tags);
        this.name = name;
        this.location = location;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Bug otherBug) {
        if (otherBug == this) {
            return true;
        }

        return otherBug != null
                && otherBug.getName().equals(getName())
                && (otherBug.getLocation().equals(getLocation()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Bug)) {
            return false;
        }

        Bug otherBug = (Bug) other;
        return otherBug.getName().equals(getName())
                && otherBug.getLocation().equals(getLocation())
                && otherBug.getDescription().equals(getDescription())
                && otherBug.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, location, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Location: ")
                .append(getLocation())
                .append(" Description: ")
                .append(getDescription())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
