package seedu.description.testutil;

import static seedu.description.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.description.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.description.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.description.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.description.logic.commands.AddCommand;
import seedu.description.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.description.model.bug.Bug;
import seedu.description.model.tag.Tag;

/**
 * A utility class for Bug.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code bug}.
     */
    public static String getAddCommand(Bug bug) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(bug);
    }

    /**
     * Returns the part of command string for the given {@code bug}'s details.
     */
    public static String getPersonDetails(Bug bug) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + bug.getName().fullName + " ");
        sb.append(PREFIX_PHONE + bug.getLocation().value + " ");
        sb.append(PREFIX_ADDRESS + bug.getDescription().value + " ");
        bug.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
