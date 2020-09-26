package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.bug.Bug;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BugList bugList;
    private final UserPrefs userPrefs;
    private final FilteredList<Bug> filteredBugs;

    /**
     * Initializes a ModelManager with the given bugList and userPrefs.
     */
    public ModelManager(ReadOnlyBugList addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.bugList = new BugList(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBugs = new FilteredList<>(this.bugList.getPersonList());
    }

    public ModelManager() {
        this(new BugList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== BugList ================================================================================

    @Override
    public void setBugList(ReadOnlyBugList bugList) {
        this.bugList.resetData(bugList);
    }

    @Override
    public ReadOnlyBugList getBugList() {
        return bugList;
    }

    @Override
    public boolean hasPerson(Bug bug) {
        requireNonNull(bug);
        return bugList.hasPerson(bug);
    }

    @Override
    public void deletePerson(Bug target) {
        bugList.removePerson(target);
    }

    @Override
    public void addPerson(Bug bug) {
        bugList.addPerson(bug);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Bug target, Bug editedBug) {
        requireAllNonNull(target, editedBug);

        bugList.setPerson(target, editedBug);
    }

    //=========== Filtered Bug List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Bug} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Bug> getFilteredPersonList() {
        return filteredBugs;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Bug> predicate) {
        requireNonNull(predicate);
        filteredBugs.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return bugList.equals(other.bugList)
                && userPrefs.equals(other.userPrefs)
                && filteredBugs.equals(other.filteredBugs);
    }

}
