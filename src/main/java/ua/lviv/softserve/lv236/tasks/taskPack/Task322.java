package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Sensei on 22.04.2017.
 */
public class Task322 extends GeneralTask{

    {
        name = "322";
        description = "Знайти натуральне число від 1 до 10000 з максимальною сумою дільників";
    }

    @Override
    public void countResult() {

        try {
            int number = 1, count, max = 1;

            for (int i = 1; i < 10000; i++) {
                count = 1;
                for (int j = 1; j < i/2 + 1; j++) {
                    if(i % j == 0){
                        count++;
                    }
                }
                if(count > max){
                    max = count;// need
                    number = i;
                }
            }

            setResult(Integer.toString(number));
        } catch (Exception e) {
            setResult("Never!");
        }

    }

}
