package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by inva on 4/26/2017.
 */
public class Task178gTest {
    private static Task178g task178g;

    @BeforeClass
    public static void setUp(){
        task178g = new Task178g();
    }

    @Test
    public void testZero(){
        task178g.setInput("1 1 1 1");
        task178g.countResult();
        assertEquals(task178g.getResult(), "0");
    }

    @Test
    public void testThree(){
        task178g.setInput("2 3 6 8 2 1 2");
        task178g.countResult();
        assertEquals(task178g.getResult(), "3");
    }

    @Test
    public void testOne(){
        task178g.setInput("2 3 6");
        task178g.countResult();
        assertEquals(task178g.getResult(), "1");
    }

    @Test
    public void testException(){
        task178g.setInput("onetwo");
        task178g.countResult();
        assertEquals(task178g.getResult(), "Please, enter natural number in Integer range.");
    }

    @After
    public void afterTest() throws Exception {
        task178g.setInput("");
        task178g.setResult("");
    }

    @AfterClass
    public static void tearDown(){
        task178g = null;
    }
}
