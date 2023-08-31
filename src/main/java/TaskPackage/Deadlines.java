package TaskPackage;

import DukePackage.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline task with the task and "by" date.
     *
     * @param task Task to complete.
     * @param details Date to complete task by.
     * @param done Indicator of whether task has been completed.
     * @throws DukeException if there are missing dates or improper date format used.
     */
    public Deadlines(String task, String details, String done) throws DukeException {
        super(task, done);
        try {
            this.by = LocalDate.parse(details.substring(3));
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the deadline (YYYY-MM-DD).");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There are missing details for the deadline.");
        }
    }

    /**
     * Prints the "by" date of the deadline.
     *
     * @return String
     */
    public String printDetails() {
        return String.format("(by: %s)", this.by.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printTask() {
        return String.format("[D]%s%s", super.printTask(), this.printDetails());
    }

    /**
     * Formats and returns the "by" date of the deadline.
     *
     * @return String
     */
    public String addDetailsToStorage() {
        return String.format("| by %s", this.by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToStorage() {
        return String.format("D %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
