/*
 * Template
 */
package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Abstract class GeneralTask is template for all other Tasks.
 */
public abstract class GeneralTask {
    /** name - Name of the task. */
    protected String name = "";
    /** description - Description of the task. */
    protected String description = "";
    /** input - Input field for the task calculation. */
    private String input = "";
    /** result - Output field of the task calculation. */
    private String result = "";

    /**
     * Main method for all Task calculations.
     * Get String getInput() as input of Data.
     * Set String setResult() as result of Calculation.
     */
    public abstract void countResult();

    /**
     * Get String name of the task.
     * @return String name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Set String name of the task.
     * @param name of the task.
     */
    public void setName(String name) {
        if (name != null)
            this.name = name.trim();
    }

    /**
     * Get String description of the task.
     * @return String description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set String description of the task.
     * @param description of the task.
     */
    public void setDescription(String description) {
        if (description != null)
            this.description = description.trim();
    }

    /**
     * Get input String data for the task calculation.
     * @return String input for the task.
     */
    public String getInput() {
        return input;
    }

    /**
     * Set input String data before task calculation.
     * @param input of the task.
     */
    public void setInput(String input) {
        if (input != null)
            this.input = input.trim();
    }

    /**
     * Get result String data after task calculation.
     * @return String result for the task.
     */
    public String getResult() {
        return result;
    }

    /**
     * Set result String data of the task calculation.
     * @param result of the task.
     */
    public void setResult(String result) {
        if (result != null)
            this.result = result.trim();
    }
}
