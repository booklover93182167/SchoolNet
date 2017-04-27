package ua.lviv.softserve.lv236.tasks.taskPack;

public class Task561 extends GeneralTask {

    {
        name = "561";
        description = "Дано натуральне число n. Серед чисел 1...n " +
                "знайти всі такі, запис яких співпадає з останніми цифрами запису їх квадрату";
    }

    @Override
    public void countResult() {
        long number = Long.parseLong(this.getInput()) + 1;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < number; i++) {
            int iSquare = i*i;
            int counter = 0;
            int mistakesCount = 0;

            char[] myI = Integer.toString(i).toCharArray();
            char[] myISquare = Integer.toString(iSquare).toCharArray();

            for(int j = myI.length-1; j >= 0; j--) {
                if(myI[j] != myISquare[myISquare.length - 1 - counter]) mistakesCount++;
                else counter++;
            }

            if(mistakesCount == 0) {
                sb.append(i);
                sb.append(" ");
            }
        }
        setResult(sb.toString());
    }
}