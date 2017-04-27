package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Kolja on 22.04.2017.
 */
public class Task330 extends GeneralTask{

    {
        name = "330";
        description = "Всі абсолютні числа";
    }


    @Override
    public void countResult() {

        String result = "";
        try {
            long number = Long.parseLong(this.getInput());

            for (int i = 2; i <= number; i++) {
                int res = 0;
                for (int k = 1; k < i; k++) {
                    if (i % k == 0) res += k;
                }
                if (res == i) result += (res + " ");
            }
            setResult(result);
        }catch (Exception e){
            setResult("Wrong Input!");
        }
    }

    }

