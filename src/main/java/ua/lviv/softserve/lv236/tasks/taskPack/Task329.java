package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Vasyl on 22.04.2017.
 * Task #329.
 */
public class Task329 extends GeneralTask {
    {
        name = "329";
        description = "Дано натуральні числа n, m. Получити всі менші n натуральні числа, квадрат суми цифр яких дорівнює m";
    }

    @Override
    public void countResult() {
        try {
            String[] tmp_input = getInput().split(",");
            long n = Long.parseLong(tmp_input[0].trim());
            long m = Long.parseLong(tmp_input[1].trim());
            if (n <= 0 || m <= 0)
                throw new IllegalArgumentException();
            StringBuilder sb = new StringBuilder();
            long temp_number;
            long tmp_result;
            for (long i = 1; i < n; i++) {
                temp_number = i;
                tmp_result = 0;
                while (temp_number > 0) {
                    tmp_result += temp_number % 10;
                    temp_number /= 10;
                }
                if (m == (tmp_result * tmp_result))
                    sb.append(i).append(" ");
            }
            setResult(sb.toString().trim().replaceAll(" ", ", "));
        } catch (IllegalArgumentException e) {
            setResult("Wrong input! Must be integers: n, m (bigger then 1)");
        }
    }
}
