package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by inva on 4/26/2017.
 */
public class Task330Test {
    public static Task330 task330;

    @BeforeClass
    public static void setUp(){
        task330 = new Task330();
    }

    @Test
    public void testSmallInput(){
        task330.setInput("555");
        task330.countResult();
        assertEquals(task330.getResult(), "6 28 496");
    }

    @Test
    public void testBiggerInput(){
        task330.setInput("12313");
        task330.countResult();
        assertEquals(task330.getResult(), "6 28 496 8128");
    }

    @Test
    public void testException(){
        task330.setInput("onetwo");
        task330.countResult();
        assertEquals(task330.getResult(), "Wrong Input!");
    }

    @After
    public void afterTest() throws Exception {
        task330.setInput("");
        task330.setResult("");
    }

    @AfterClass
    public static void tearDown(){
        task330 = null;
    }

}
