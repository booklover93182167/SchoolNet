package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by User on 26.04.2017.
 */
public class Task87Test {


    public static  Task87 task87;
    @BeforeClass
    public static void beforeClass(){
        task87=new Task87();
    }
    @Before
    public void beforeTest(){
        task87.setInput("");
        task87.setResult("");
    }

    @Test
    public void countResultTestEquals() throws Exception {
        task87.setInput("237432 4");
        task87.countResult();
        Assert.assertEquals("16",task87.getResult());


    }
    @Test
    public void countResultTestTrue() throws Exception {
        task87.setInput("232323542234 6");
        task87.countResult();
        Assert.assertTrue("20".equals(task87.getResult()));


    }
    @Test
    public void countResultTestNotNull() throws Exception {

        Assert.assertNotNull(task87.getResult());

    }


    @After
    public void afterTest() throws Exception {
        task87.setInput("");
        task87.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task87 = null;
    }
}