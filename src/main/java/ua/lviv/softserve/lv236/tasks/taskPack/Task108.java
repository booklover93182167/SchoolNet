package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.InputMismatchException;

public class Task108 extends GeneralTask {

    {
        name = "108";
        description = "Дано натуральне число n. "
                + "Отримати найменше число виду 2^r, більше за n";
    }

    @Override
    public void countResult() {
        try {
            String str = this.getInput();
            long m = Long.parseLong(str);
            long twoInDegree = 1;
            if (m < 1 || m > Long.MAX_VALUE - 1)
                throw new IllegalArgumentException();


            while (true) {
                if (m >= twoInDegree) {
                    twoInDegree *= 2;
                } else {
                    break;
                }
            }
            setResult(Long.toString(twoInDegree));
        } catch (IllegalArgumentException | InputMismatchException e) {
            setResult("Wrong input!");

        }
    }
}


