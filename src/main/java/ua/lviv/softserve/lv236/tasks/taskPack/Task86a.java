package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Simple task class number 86a.
 *
 * @author Kolja
 */

//my task example
public class Task86a extends GeneralTask {
    {
        name = "86a";
        description = "Скільки цифр в числі n";
    }

    /**
     * Overridden method for all task classes.
     */
    @Override
    public void countResult() {

        //my task
        if (getInput().matches("^[0-9]+")) {
            int res = String.valueOf(this.getInput()).length();
            //setting result
            this.setResult(String.valueOf(res));
        } else {
            setResult("Wrong Input");
        }
    }
}
