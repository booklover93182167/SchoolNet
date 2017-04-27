package ua.lviv.softserve.lv236.tasks.taskPack;

/**
 * Created by Sensei on 22.04.2017.
 */
public class Task88a extends GeneralTask{

    {
        name = "88a";
        description = "Дано натуральне число n, чи входить цифра 3 у запис n^2";
    }


    @Override
    public void countResult() {
        try{

            int n = Math.abs(Integer.parseInt(getInput()));
            int q = n*n;
            String a=Integer.toString(q);
            boolean isThere = false;
            for(int i=0; i<a.length(); i++){
                if(a.charAt(i)=='3'){
                    isThere=true;
                    break;
                }
            }
            setResult(Boolean.toString(isThere));

        }catch(Exception e){
            setResult("Некоректний аргумент)) Введіть натуральне число");
        }

    }

}
