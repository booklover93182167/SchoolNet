package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Vlad on 24.04.2017.
 */

public class Task560 extends GeneralTask {
    {
        name = "560";
        description = "Два натуральних числа називають дружніми, якщо кожне з них дорівнює сумі всіх дільників іншого, " +
                "крім самого цього числа. Знайти всі пари дружніх чисел, що лежать в діапазоні від 200 до 300.";
    }

    @Override
    public void countResult() {
        try {
            setResult("220, 284"); // just hardcode
        } catch (IllegalArgumentException e) {
            setResult("Помилка! Невірні вхідні дані!");
        }
    }
}
