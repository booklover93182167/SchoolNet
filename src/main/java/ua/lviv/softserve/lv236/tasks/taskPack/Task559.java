package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;

/**
 * Created by inva on 4/22/2017.
 */
public class Task559 extends GeneralTask {
    {
        name = "559";
        description = "Given natural number N > 0, get all Mersenne numbers < N. Mersenne numbers can be represented as 2^p-1, where p is a prime number";
    }

    @Override
    public void countResult() {
        try{
            String s = getInput();
            if(s == null || s.isEmpty()){
                throw new IllegalArgumentException();
            }
            Long n = Long.parseLong(s);
            if(n < 1){
                throw new IllegalArgumentException();
            }
            ArrayList<Long> list = new ArrayList<>();
            long nextPrm = 1;
            long nextMersenne = (long)Math.pow(2, nextPrm)-1;
            while(nextMersenne < n){
                list.add(nextMersenne);
                nextPrm = nextPrime(nextPrm);
                nextMersenne = (long)Math.pow(2, nextPrm)-1;
            }
            setResult(list.toString());
        } catch (Exception e){
            setResult("Wrong input! Please enter a natural number N > 0.");
        }
    }

    public static long nextPrime(long number){
        long next = number + 1;
        while(!isPrime(next)){
            next++;
        }
        return next;
    }

    public static boolean isPrime(long n){
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Task559 task = new Task559();
        task.countResult();
    }
}
