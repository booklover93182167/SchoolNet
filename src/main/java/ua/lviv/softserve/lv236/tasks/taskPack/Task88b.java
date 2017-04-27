package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Sensei on 22.04.2017.
 */
public class Task88b extends GeneralTask{

    {
        name = "88б";
        description = "Дано натуральне число n, поміняти порядок числа n на зворотній";
    }

    @Override
    public void countResult() {

        try{

            int a = Math.abs(Integer.parseInt(getInput()));
            int result = 0;
            while(a > 0){
                result = result * 10 + a % 10;
                a /= 10;
            }
            setResult(Integer.toString(result));

        }catch(Exception e){
            setResult("Некоректний аргумент)) Введіть натуральне число");
        }

    }

}
