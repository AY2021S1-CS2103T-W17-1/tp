package seedu.description.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.description.commons.core.GuiSettings;
import seedu.description.commons.core.LogsCenter;
import seedu.description.logic.commands.Command;
import seedu.description.logic.commands.CommandResult;
import seedu.description.logic.commands.exceptions.CommandException;
import seedu.description.logic.parser.BugListParser;
import seedu.description.logic.parser.exceptions.ParseException;
import seedu.description.model.Model;
import seedu.description.model.ReadOnlyAddressBook;
import seedu.description.model.bug.Bug;
import seedu.description.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final BugListParser bugListParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        bugListParser = new BugListParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = bugListParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Bug> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
