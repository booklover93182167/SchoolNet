package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Volodymyr on 26.04.2017.
 */
public class Task182Test {
    private static Task182 task182;

    @BeforeClass
    public static void setUp() throws Exception {
        task182 = new Task182();
    }

    @Before
    public void beforeTest() throws Exception {
        task182.setInput("");
        task182.setResult("");
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task182.setInput("5 1 5 7 10 15");
        task182.countResult();
        String result = "Числа: 5; 10; 15; \nКількість: 3; \nСума: 30;";
        assertEquals(result, task182.getResult());
    }

    @After
    public void afterTest() throws Exception {
        task182.setInput("");
        task182.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task182 = null;
    }
}