package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;

/**
 * Created by inva on 4/22/2017.
 */
public class Task323 extends GeneralTask {
    {
        name = "323";
        description = "Given natural number 1 < N < 2^31-1, get all its coprime numbers < N";
    }

    @Override
    public void countResult() {
        try {
            String s = getInput();
            if(s == null || s.isEmpty()){
                throw new IllegalArgumentException();
            }
            ArrayList<Integer> list = new ArrayList<>();
            int n = Integer.parseInt(s);
            if(n < 1){
                throw new IllegalArgumentException();
            }
            for (int i = 1; i < n; i++) {
                if(gcd(i, n) == 1){
                    list.add(i);
                }
            }
            setResult(list.toString());
        } catch (Exception e){
            setResult("Wrong input! Must be 1 < N < 2^31-1");
        }
    }

    private static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }
}
