package seedu.description.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.description.model.Model;
import seedu.description.model.ModelManager;
import seedu.description.model.UserPrefs;
import seedu.description.model.bug.Bug;
import seedu.description.testutil.PersonBuilder;
import seedu.description.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Bug validBug = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validBug);

        CommandTestUtil.assertCommandSuccess(new AddCommand(validBug), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validBug), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Bug bugInList = model.getAddressBook().getPersonList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(bugInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
