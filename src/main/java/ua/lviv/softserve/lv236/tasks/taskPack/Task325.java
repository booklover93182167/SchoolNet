package ua.lviv.softserve.lv236.tasks.taskPack;

public class Task325 extends GeneralTask{
    {
        name = "325";
        description = "Дано натуральне число n. Отримати всі прості дільники числа";
    }

    @Override
    public void countResult() {
        long number = Long.parseLong(this.getInput());

        setResult(findSimpleDivisors(number));
    }

    private String findSimpleDivisors(long number) {
        StringBuilder sb = new StringBuilder();

        for(long i = 1; i <= number; i++) {
            if((number % i == 0) && isSimple(i)) {
                sb.append(Long.toString(i));
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private boolean isSimple(long n) {
        boolean b = true;
        for(int i = 2; i < n; i++) {
            if(n % i == 0)
                b = false;
        }
        return b;
    }
}