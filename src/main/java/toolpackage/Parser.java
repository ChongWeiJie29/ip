package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.ToDos;

/**
 * Represents the parser tool for the
 * Duke bot. A Parser object is used
 * by the Duke bot to understand
 * and execute user commands.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Parses and executes command given by user.
     *
     * @param userInput String given by user.
     * @param tasks Task list of bot to be modified.
     * @param ui UI of bot.
     * @return boolean Indicate whether Duke bot loop should continue.
     * @throws DukeException if there are any issues in the command execution.
     */
    public static boolean parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] parsedCommand = userInput.split(" ", 2);
        String command = parsedCommand[0];

        String task = "";
        if (parsedCommand.length > 1) {
            task = parsedCommand[1];
        }

        if (command.equals("bye")) {
            return false;
        } else if (command.equals("list")) {
            tasks.printList(ui);
        } else if (command.equals("mark")) {
            tasks.toggleDone(task, "mark", ui);
        } else if (command.equals("unmark")) {
            tasks.toggleDone(task, "unmark", ui);
        } else if (command.equals("delete")) {
            tasks.removeItem(task, ui);
        } else if (command.equals("find")) {
            tasks.findTasks(task, ui);
        } else if (command.equals("todo")) {
            tasks.addItem(new ToDos(task, "0"), ui);
        } else if (command.equals("deadline")) {
            parsedCommand = task.split("/");
            if (parsedCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! There are missing deadline details.");
            } else {
                tasks.addItem(new Deadlines(parsedCommand[0], parsedCommand[1], "0"), ui);
            }
        } else if (command.equals("event")) {
            parsedCommand = task.split("/");
            if (parsedCommand.length <= 2) {
                throw new DukeException("☹ OOPS!!! There are missing event details.");
            } else {
                tasks.addItem(new Events(parsedCommand[0], parsedCommand[1], parsedCommand[2], "0"), ui);
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
