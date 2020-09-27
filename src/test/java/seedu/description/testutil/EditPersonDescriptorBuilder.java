package seedu.description.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.description.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.Description;
import seedu.description.model.bug.Location;
import seedu.description.model.bug.Name;
import seedu.description.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code bug}'s details
     */
    public EditPersonDescriptorBuilder(Bug bug) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(bug.getName());
        descriptor.setPhone(bug.getLocation());
        descriptor.setAddress(bug.getDescription());
        descriptor.setTags(bug.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Location(phone));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Description(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
