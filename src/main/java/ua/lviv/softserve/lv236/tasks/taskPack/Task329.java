package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Vasyl on 22.04.2017.
 */
public class Task329 extends GeneralTask {
    {
        name = "329";
        description = "Дано натуральні числа n, m. Получити всі менші n натуральні числа, квадрат суми цифр яких дорівнює m";
    }

    @Override
    public void countResult() {
        try {
            String[] tmp = getInput().split(",");
            long n = Long.parseLong(tmp[0].trim());
            long m = Long.parseLong(tmp[1].trim());
            if (n <= 0 || m <= 0)
                throw new IllegalArgumentException();
            StringBuilder sb = new StringBuilder();
            long num, res;
            for (long i = 1; i < n; i++) {
                num = i;
                res = 0;
                while (num > 0) {
                    res += num % 10;
                    num /= 10;
                }
                if (m == (res * res))
                    sb.append(i).append(" ");
            }
            setResult(sb.toString().trim().replaceAll(" ", ", "));
        } catch (IllegalArgumentException e) {
            setResult("Wrong input! Must be integers: n, m (bigger then 1)");
        }
    }
}
