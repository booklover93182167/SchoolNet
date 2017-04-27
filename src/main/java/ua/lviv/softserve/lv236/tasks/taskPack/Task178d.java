package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;

/**
 * Created by Soltys on 23.04.2017.
 */
public class Task178d extends GeneralTask{
    {
        name = "178d";
        description = "Дано натуральні числа n, A1,..,An. Визначити кількість членів Ak, послідовності" +
                "A1,..,An, що відповідають умові:                  2^k < Ak < k!";
    }

    @Override
    public void countResult() {
        int count = 0;
        ArrayList<Integer> list = new ArrayList();
        try {
            for (String c : getInput().split(" ")) {
                int n = Integer.parseInt(c);
                if (c.contains(".") || n <= 0){
                    throw new NumberFormatException();
                }
                list.add(n);
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > Math.pow(2, i) && list.get(i) < factorial(i)) {
                    count++;
                }
            }
            setResult(String.valueOf(count));
        } catch (NumberFormatException e){
            setResult("Please, enter natural number in Integer range.");
        }
    }

    private static Integer factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
}
