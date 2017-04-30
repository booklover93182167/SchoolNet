package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Vasyl on 22.04.2017.
 * Task #562.
 * No need input.
 */
public class Task562 extends GeneralTask {
    {
        name = "562";
        description = "Натуральне число із n цифр являється числом Армстронга, якщо сума його цифр, піднятих в n-у степінь, " +
                "рівна самому числу (як, приклад: 153 = 1^3 + 5^3 + 3^3). Получити всі числа Армстронга, які складаються із двох, " +
                "трьох і чотирьох цифр.";
    }

    /** Start point of 2 digits number */
    static final int START_POINT = 10;
    /** Limit point of 2 digits number */
    static final int TWO_DIGIT_POINT = 100;
    /** Limit point of 3 digits number */
    static final int THREE_DIGIT_POINT = 1000;
    /** End point of 4 digits number */
    static final int END_POINT = 10000;

    @Override
    public void countResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = START_POINT, temp_number = i, tmp_result = 0; i < END_POINT; i++, temp_number = i, tmp_result = 0) {
            while (temp_number > 0) {
                tmp_result += Math.pow(temp_number % 10, i < TWO_DIGIT_POINT ? 2 : i < THREE_DIGIT_POINT ? 3 : 4);
                temp_number /= 10;
            }
            if (i == tmp_result)
                sb.append(i).append(" ");
        }
        setResult(sb.toString().trim().replaceAll(" ", ", "));
    }
}
