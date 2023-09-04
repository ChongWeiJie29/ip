package dukepackage;

import toolpackage.Parser;
import toolpackage.Storage;
import toolpackage.TaskList;
import toolpackage.Ui;

import java.util.Scanner;

/**
 * Represents a Duke bot. A Duke object contains
 * its own storage, task lists and ui.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the respective components to
     * initialise Duke, namely the UI, Storage,
     * and TaskList.
     *
     * @param filePath File path of storage.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);

            boolean isCreated = this.storage.createStorage();
            if (!isCreated) {
                tasks = new TaskList(storage.load());
            } else {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs a loop to listen for commands by user.
     */
    private void run() {
        this.ui.showWelcome();
        Scanner inputs = new Scanner(System.in);

        boolean shouldContinue = true;
        while (shouldContinue) {
            String command = this.ui.readCommands(inputs);
            try {
                shouldContinue = Parser.parse(command, this.tasks, this.ui);
                this.storage.saveStorage(this.tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        inputs.close();
        this.ui.showBye();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
