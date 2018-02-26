package fr.mbayou.taskservice.model;

public class Task {

    /**
     * Task's identifier
     */
    public long id;

    /**
     * Task's title
     */
    public String title;

    /**
     * Task's description
     */
    public String description;

    /**
     * Task's due date in ISO8601
     */
    public String dueDate;
}
