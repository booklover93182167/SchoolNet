package ua.lviv.softserve.lv236.tasks.taskPack;

public class Task108 extends GeneralTask {

    {
        name = "108";
        description = "Дано натуральне число n. Отримати найменше число виду 2^r, більше за n";
    }

    @Override
    public void countResult() {
        long number = Long.parseLong(this.getInput());
        long twoInDegree = 1;

        if(number > Long.MAX_VALUE - 1) {
            setResult("Your number is too big.");
        }   else {
            while(true) {
                if(number >= twoInDegree)
                    twoInDegree *= 2;
                else
                    break;
            }
            setResult(Long.toString(twoInDegree));
        }
    }
}