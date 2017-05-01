package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Simple task class number 86b.
 *
 * @author Kolja
 */
public class Task86b extends GeneralTask {
    {
        name = "86b";
        description = "Сума чисел n";
    }

    /**
     * Overridden method for all task classes.
     */
    @Override
    public void countResult() {
        if (getInput().matches("^[0-9]+")) {
            String str = this.getInput();
            int res = 0;
            for (char c : str.toCharArray()) {
                res += Character.getNumericValue(c);
            }
            this.setResult(String.valueOf(res));
        } else {
            setResult("Wrong Input");
        }
    }
}
