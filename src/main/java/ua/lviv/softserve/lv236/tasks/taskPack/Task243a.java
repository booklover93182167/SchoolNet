package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.InputMismatchException;

/**
 * Created by User on 22.04.2017.
 */
public class Task243a extends GeneralTask {

    {
        name = "243a";
        description = "Задано натуральне n. Чи можна його представити у вигляді суми двох квадратів натуральних чисел?" +
                "Якщо так, то" + "\n" +
                "а) вказати пару натуральних x,y таких, що n=x^2+y^2";
    }

    @Override
    public void countResult() {

        try {
            String str = this.getInput();

            long m = Long.parseLong(str);
            if (m <= 0) {
                throw new InputMismatchException();
            }
            boolean b = false;
            int counter = 0;
            for (int x = 1; x * x < m; x++)
                for (int y = 1; y * y < m; y++) {
                    {
                        if ((x * x + y * y) == m) {
                            b = true;
                            counter += 1;
                            this.setResult(String.valueOf(str + "= " + x + "^2 +" + y + "^2 "));
                        }
                        if (counter == 1)
                            break;
                    }
                }
            if (b == false)
                this.setResult(str + " неможливо представити у вигляді суми квадратів двох натуральних чисел");

        } catch (IllegalArgumentException | InputMismatchException e) {
            setResult("Wrong input!");

        }

    }
}
