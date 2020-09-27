package seedu.description.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.description.commons.core.GuiSettings;
import seedu.description.logic.commands.CommandResult;
import seedu.description.logic.commands.exceptions.CommandException;
import seedu.description.logic.parser.exceptions.ParseException;
import seedu.description.model.ReadOnlyAddressBook;
import seedu.description.model.bug.Bug;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the BugList.
     *
     * @see seedu.description.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Bug> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
