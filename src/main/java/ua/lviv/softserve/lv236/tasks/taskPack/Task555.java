package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Soltys on 23.04.2017.
 */
public class Task555 extends GeneralTask {
    {
        name = "555";
        description = "Дано натуральне n. Отримати перші n рядків трикутника Паскаля";
    }

    @Override
    public void countResult() {
        int n = 0;
        try {
            n = Integer.parseInt(getInput());
            if (getInput().contains(".") || n <= 0){
                throw new NumberFormatException();
            }

            StringBuilder printOut = new StringBuilder();
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0, k = 1; i < n; i++, k += 2) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = 0; j < n - k / 2; j++) {
                    printOut.append(' ');
                }
                int mapCount = 0;
                for (int j = 0, count = 1; j < k; j++, count++) {
                    if (count % 2 == 0){
                        printOut.append(' ');
                    } else {
                        if (count == 1 || count == k){
                            printOut.append(1);
                            list.add(1);
                            mapCount++;
                        } else {
                            int m = map.get(i-1).get(mapCount-1)+map.get(i-1).get(mapCount);
                            printOut.append(m);
                            list.add(m);
                            mapCount++;
                        }
                    }
                }
                map.put(i, list);
                printOut.append('\n');
            }
            setResult(printOut.toString());

        } catch (NumberFormatException e){
            setResult("Please, enter only one natural number in Integer range.");
        }
    }
}
