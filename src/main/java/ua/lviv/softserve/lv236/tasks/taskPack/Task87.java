package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.InputMismatchException;

/**
 * Created by inva on 4/22/2017.
 */
public class Task87 extends GeneralTask {

    {
        name = "87";
        description = "Given natural numbers n, m, get sum of m last digits in n";
    }
    @Override
    public void countResult() {
        try{
            String s = getInput();
            if(s == null || s.isEmpty()){
                throw new IllegalArgumentException();
            }
            String[] strings = s.split(" ");
            Long n = Long.parseLong(strings[0]);
            Long m = Long.parseLong(strings[1]);
            if(strings[0].length() < m || n<=0 || m<=0){
                throw new InputMismatchException();
            }
            Long result = 0L;
            for(long i = 0; i < m; i++){
                result += n%10;
                n = n/10;
            }
            setResult(String.valueOf(result));
        }
        catch (InputMismatchException e){
            setResult("Wrong input! Can`t calculate.");
        }catch (Exception e){
            setResult("Wrong input! Please enter two natural numbers, divided by a single space");
        }

    }

}
