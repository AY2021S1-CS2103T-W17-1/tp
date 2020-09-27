package seedu.description.logic.commands;

import static seedu.description.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.description.model.Model;
import seedu.description.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        CommandTestUtil.assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
