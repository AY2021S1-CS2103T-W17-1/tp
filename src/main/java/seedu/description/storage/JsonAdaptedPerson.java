package seedu.description.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.description.commons.exceptions.IllegalValueException;
import seedu.description.model.bug.Bug;
import seedu.description.model.bug.Description;
import seedu.description.model.bug.Location;
import seedu.description.model.bug.Name;
import seedu.description.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Bug}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Bug's %s field is missing!";

    private final String name;
    private final String phone;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given bug details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Bug} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Bug source) {
        name = source.getName().fullName;
        phone = source.getLocation().value;
        address = source.getDescription().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted bug object into the model's {@code Bug} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted bug.
     */
    public Bug toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        if (!Location.isValidPhone(phone)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidAddress(address)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Bug(modelName, modelLocation, modelDescription, modelTags);
    }

}
