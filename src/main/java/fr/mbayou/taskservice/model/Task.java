package fr.mbayou.taskservice.model;

public class Task {

    /**
     * Task's identifier
     * /!\ I use a Long object to check if the value is really set
     * (if it's not with a long primitive type I will have 0)
     */
    public Long id;

    /**
     * Task's title
     */
    public String title;

    /**
     * Task's description
     */
    public String description;

    /**
     * Task's due date in ISO8601 (1977-04-22T06:00:00Z)
     */
    public String dueDate;
}
