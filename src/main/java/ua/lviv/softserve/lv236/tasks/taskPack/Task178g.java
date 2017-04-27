package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;

/**
 * Created by Soltys on 23.04.2017.
 */
public class Task178g extends GeneralTask {
    {
        name = "178g";
        description = "Дано натуральні числа n, A1,..,An. Визначити кількість членів Ak, послідовності" +
                "A1,..,An, що відповідають умові:              Ak < (Ak-1 + Ak+1)/2";
    }

    @Override
    public void countResult() {
        int count = 0;
        ArrayList<Integer> list = new ArrayList();
        try {
            for (String c : getInput().split(" ")) {
                int n = Integer.parseInt(c);
                if (c.contains(".") || n <= 0) {
                    throw new NumberFormatException();
                }
                list.add(n);
            }
            for (int i = 1; i < list.size() - 1; i++) {
                if (list.get(i) < (list.get(i - 1) + list.get(i + 1)) / 2) {
                    count++;
                }
            }
            setResult(String.valueOf(count));
        } catch (NumberFormatException e) {
            setResult("Please, enter natural number in Integer range.");
        }
    }
}
