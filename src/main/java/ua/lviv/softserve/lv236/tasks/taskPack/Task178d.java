package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soltys on 23.04.2017.
 * Task178d determines the number of members of the sequence
 * of positive integers that satisfy the conditions.
 * contain one public method 'countResult'.
 * <p>
 *
 * @author Soltys Yaroslav
 * @version 1.0
 */
public class Task178d extends GeneralTask {
    {
        name = "178d";
        description = "Дано натуральні числа n, A1,..,An. "
                + "Визначити кількість членів Ak, послідовності"
                + "A1,..,An, що відповідають умові:"
                + " 2^k < Ak < k!";
    }

    /**
     * It is overridden method of GeneralTask class, which checks
     * the entered numbers, calls 'factorial' method and check how
     * many members meets to the specified condition.
     */
    @Override
    public void countResult() {
        int count = 0;
        List<Integer> list = new ArrayList();
        try {
            for (String c : getInput().split(" ")) {
                int n = Integer.parseInt(c);
                if (c.contains(".") || n <= 0) {
                    throw new NumberFormatException();
                }
                list.add(n);
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > Math.pow(2, i)
                        && list.get(i) < factorial(i)) {
                    count++;
                }
            }
            setResult(String.valueOf(count));
        } catch (NumberFormatException e) {
            setResult("Please, enter natural number in Integer range.");
        }
    }

    /**
     * Returns the factorial of a given number.
     *
     * @param n - number you want to find
     * @return factorial numbers
     */
    private static Integer factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}