package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kolja on 26.04.2017.
 */
public class Task562Test {

    private static Task562 task562;

    @BeforeClass
    public static void initializing(){
        task562 = new Task562();
    }

    @Before
    public void before(){
        task562.setInput("");
        task562.setResult("");
    }

    @Test
    public void testTrue1(){
        task562.setInput("");
        task562.countResult();
        assertTrue(task562.getResult().equalsIgnoreCase("153, 370, 371, 407, 1634, 8208, 9474"));
    }

    @Test
    public void testEquals(){
        task562.setInput("234");
        task562.countResult();
        assertEquals(task562.getResult(), "153, 370, 371, 407, 1634, 8208, 9474");
    }

   @After
    public void afterTeest(){
       task562.setInput("");
       task562.setResult("");
   }

   @AfterClass
    public static void closeTest(){
        task562 = null;
   }



}
