package seedu.description.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.description.commons.core.Messages;
import seedu.description.commons.core.index.Index;
import seedu.description.logic.commands.exceptions.CommandException;
import seedu.description.model.Model;
import seedu.description.model.bug.Bug;

/**
 * Deletes a bug identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the bug identified by the index number used in the displayed bug list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Bug: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bug> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Bug bugToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(bugToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, bugToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
