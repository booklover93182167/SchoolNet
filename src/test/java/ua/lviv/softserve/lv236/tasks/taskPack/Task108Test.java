package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by User on 26.04.2017.
 */
public class Task108Test {
    public static Task108 task108;
    @BeforeClass
    public static void beforeClass(){
        task108=new Task108();
    }
    @Before
    public void beforeTest(){
        task108.setInput("");
        task108.setResult("");
    }

    @Test
    public void countResultTestEquals() throws Exception {
        task108.setInput("8");
        task108.countResult();
        Assert.assertEquals("16",task108.getResult());


    }
    @Test
    public void countResultTestTrue() throws Exception {
        task108.setInput("3");
        task108.countResult();
        Assert.assertTrue("4".equals(task108.getResult()));


    }
    @Test
    public void countResultTestNotNull() throws Exception {

        Assert.assertNotNull(task108.getResult());

    }


    @After
    public void afterTest() throws Exception {
        task108.setInput("");
        task108.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task108 = null;
    }

}