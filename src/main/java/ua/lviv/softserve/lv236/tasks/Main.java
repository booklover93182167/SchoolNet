package ua.lviv.softserve.lv236.tasks;

import ua.lviv.softserve.lv236.tasks.TasksGUI.MainWindow;
import ua.lviv.softserve.lv236.tasks.taskPack.Task86a;
import ua.lviv.softserve.lv236.tasks.taskPack.Task86b;
import ua.lviv.softserve.lv236.tasks.taskPack.Task87;
import ua.lviv.softserve.lv236.tasks.taskPack.Task88a;
import ua.lviv.softserve.lv236.tasks.taskPack.Task88b;
import ua.lviv.softserve.lv236.tasks.taskPack.Task107;
import ua.lviv.softserve.lv236.tasks.taskPack.Task108;
import ua.lviv.softserve.lv236.tasks.taskPack.Task178d;
import ua.lviv.softserve.lv236.tasks.taskPack.Task178g;
import ua.lviv.softserve.lv236.tasks.taskPack.Task182;
import ua.lviv.softserve.lv236.tasks.taskPack.Task225;
import ua.lviv.softserve.lv236.tasks.taskPack.Task243a;
import ua.lviv.softserve.lv236.tasks.taskPack.Task243b;
import ua.lviv.softserve.lv236.tasks.taskPack.Task322;
import ua.lviv.softserve.lv236.tasks.taskPack.Task325;
import ua.lviv.softserve.lv236.tasks.taskPack.Task329;
import ua.lviv.softserve.lv236.tasks.taskPack.Task330;
import ua.lviv.softserve.lv236.tasks.taskPack.Task555;
import ua.lviv.softserve.lv236.tasks.taskPack.Task559;
import ua.lviv.softserve.lv236.tasks.taskPack.Task560;
import ua.lviv.softserve.lv236.tasks.taskPack.Task561;
import ua.lviv.softserve.lv236.tasks.taskPack.Task562;
import ua.lviv.softserve.lv236.tasks.taskPack.Task323;
import ua.lviv.softserve.lv236.tasks.taskPack.GeneralTask;


import java.util.ArrayList;
import java.util.List;

/**
 * Main is class where all tasks are added by students.
 * So, to add a new solved task you just need to
 * create new class that will override one method -
 * countResult, and than add new instance of this
 * task class in main method.
 *
 * @author Kolja
 */
public final class Main {
    /**
     * List of all tasks.
     */
    private static List<GeneralTask> generalTaskList = new ArrayList<>();

    /**
     * list with tasks names (for dropdown menu).
     */
    private static List<String> allNames = new ArrayList<>();

    /**
     * Access to list of all tasks names.
     *
     * @return list of names
     */
    public static List<String> getAllNames() {
        return allNames;
    }

    /**
     * Access to list of all tasks.
     *
     * @return List of tasks
     */
    public static List<GeneralTask> getGeneralTaskList() {
        return generalTaskList;
    }

    /**
     * Method for adding new task, use it only !!
     *
     * @param task to set the task to add
     */
    private static void addToList(final GeneralTask task) {
        allNames.add(task.getName());
        generalTaskList.add(task);
    }
    /**
     * Main method to start the application.
     *
     * @param args is the input param.
     */
    public static void main(final String[] args) {
        //adding new task in program
        addToList(new Task86a());
        addToList(new Task86b());
        addToList(new Task87());
        addToList(new Task88a());
        addToList(new Task88b());
        addToList(new Task107());
        addToList(new Task108());
        addToList(new Task178d());
        addToList(new Task178g());
        addToList(new Task182());
        addToList(new Task225());
        addToList(new Task243a());
        addToList(new Task243b());
        addToList(new Task322());
        addToList(new Task323());
        addToList(new Task325());
        addToList(new Task329());
        addToList(new Task330());
        addToList(new Task555());
        addToList(new Task559());
        addToList(new Task560());
        addToList(new Task561());
        addToList(new Task562());

        new MainWindow();
    }

    /**
     * Private constructor.
     */
    private Main() {

    }

}
