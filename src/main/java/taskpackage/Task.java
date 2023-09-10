package taskpackage;

import java.nio.charset.Charset;

import dukepackage.DukeException;

import toolpackage.Ui;

/**
 * Represents a task. A Task object
 * contains a task in the form of a
 * string and a boolean indicating
 * whether the task is completed.
 */
public class Task {

    private String task;
    private boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param task Task to complete.
     * @param isDone Indicator of whether task has been completed.
     * @throws DukeException if the task is missing.
     */
    public Task(String task, String isDone) throws DukeException {

        // Throws error if there is no task description.
        if (task.isEmpty()) {
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            throw new DukeException(emoji + " OOPS!!! Task description should not be empty.");
        }

        this.task = task;
        this.isDone = isDone.equals("1 ");
    }

    /**
     * Prints tasks for user to see.
     *
     * @return String Representation of task.
     */
    public String printTask() {
        if (this.isDone) {
            return String.format("[X] %s", this.task);
        } else {
            return String.format("[] %s", this.task);
        }
    }

    /**
     * Toggles the completed status of the task.
     *
     * @param keyword Word to indicate whether to mark the task as complete or incomplete.
     * @param ui UI of the bot.
     * @return String Indicate whether task has been marked as complete or incomplete.
     */
    public String toggleDone(String keyword, Ui ui) {
        this.isDone = keyword.equals("mark");
        return ui.toggleDone(this, keyword);
    }

    /**
     * Formats and returns task for storage.
     *
     * @return String
     */
    public String addToStorage() {
        if (this.isDone) {
            return String.format("| 1 | %s", this.task);
        } else {
            return String.format("| 0 | %s", this.task);
        }
    }

    /**
     * Checks whether the given keyword is found in the task string.
     *
     * @param word Keyword to find in the task.
     * @return boolean Whether the word is inside.
     */
    public boolean matchKeyword(String word) {
        return this.task.contains(word);
    }
}