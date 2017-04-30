package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soltys on 23.04.2017.
 * Task178d extends GeneralTask and contain one public overridden
 * method 'countResult' determines the number of members of the sequence
 * of positive integers that satisfy the conditions.
 * <p>
 *
 * @author Soltys Yaroslav
 * @version 1.0
 */
public class Task178g extends GeneralTask {
    {
        name = "178g";
        description = "Дано натуральні числа n, A1,..,An. Визначити кількість членів Ak, послідовності" +
                "A1,..,An, що відповідають умові:              Ak < (Ak-1 + Ak+1)/2";
    }

    /**
     * It is overridden method of GeneralTask class, which checks
     * the entered numbers many numbers and check how many
     * members meets the specified condition.
     */
    @Override
    public void countResult() {
        int countMembers = 0;
        List<Integer> listOfMembers = new ArrayList();
        try {
            for (String c : getInput().split(" ")) {
                int n = Integer.parseInt(c);
                if (c.contains(".") || n <= 0) {
                    throw new NumberFormatException();
                }
                listOfMembers.add(n);
            }
            for (int i = 1; i < listOfMembers.size() - 1; i++) {
                if (listOfMembers.get(i) < (listOfMembers.get(i - 1) + listOfMembers.get(i + 1)) / 2) {
                    countMembers++;
                }
            }
            setResult(String.valueOf(countMembers));
        } catch (NumberFormatException e) {
            setResult("Please, enter natural number in Integer range.");
        }
    }
}
