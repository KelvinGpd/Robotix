package data;

/**
 * The Tache class represents a task in the system.
 * It contains information about the task itself.
 */
public class Tache {
    private String task;

    /**
     * Constructs a new Tache object with the provided task.
     *
     * @param task The task description.
     */
    public Tache (String task){
        this.task = task;
    }

    /**
     * Gets the task description.
     *
     * @return The task description as a string.
     */
    public String getTask() {
        return task;
    }

}
