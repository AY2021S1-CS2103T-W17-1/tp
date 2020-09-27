package seedu.description.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.description.model.BugList;
import seedu.description.model.Model;
import seedu.description.model.ModelManager;
import seedu.description.model.UserPrefs;
import seedu.description.testutil.TypicalPersons;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new BugList());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
