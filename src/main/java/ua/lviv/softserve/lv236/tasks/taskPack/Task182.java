package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Vlad on 24.04.2017.
 */

public class Task182 extends GeneralTask {
    {
        name = "182";
        description = "Дано натуральне число n, цілі числа a1, ..., an. Знайти кількість та суму тих членів даної послідовності, які діляться на 5 та не діляться на 7.\nПриклад: 5 1 5 7 10 15";
    }

    @Override
    public void countResult() {
        try {
            String[] arguments = getInput().split(" ");
            int count = Integer.parseInt(arguments[0].trim());

            if (count <= 0) {
                throw new IllegalArgumentException();
            }

            int numbers[] = new int[count];

            for(int i = 0; i < count; i++) {
                numbers[i] = Integer.parseInt(arguments[i+1].trim());
            }

            StringBuilder result = new StringBuilder("Числа: ");
            int quantity = 0;
            int sum = 0;

            for(int i = 0; i < count; i++) {
                if(numbers[i] % 5 == 0 && numbers[i] % 7 != 0) {
                    quantity += 1;
                    sum += numbers[i];
                    result.append(numbers[i] + "; ");
                }
            }
            if (quantity == 0) {
                result.append("немає;");
            }
            result.append("\nКількість: " + quantity + "; \nСума: " + sum + ";");
            setResult(result.toString());
        } catch (IllegalArgumentException e) {
            setResult("Помилка! Невірні вхідні дані!");
        }
    }
}
