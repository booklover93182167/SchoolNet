package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by User on 26.04.2017.
 */
public class Task178dTest { public static Task178d task178d;
    @BeforeClass
    public static void beforeClass(){
        task178d=new Task178d();
    }
    @Before
    public void beforeTest(){
        task178d.setInput("");
        task178d.setResult("");
    }

    @Test
    public void countResultTestEquals() throws Exception {
        task178d.setInput("4 4 5 2 5 66 34 23 12");
        task178d.countResult();
        Assert.assertEquals("1",task178d.getResult());


    }
    @Test
    public void countResultTestTrue() throws Exception {
        task178d.setInput("4 72 32 12 544 34 23 44 344 ");
        task178d.countResult();
        Assert.assertTrue("2".equals(task178d.getResult()));


    }
    @Test
    public void countResultTestTrue1() throws Exception {
        task178d.setInput("fgsvbd");
        task178d.countResult();
        Assert.assertEquals("Please, enter natural number in Integer range.",task178d.getResult());


    }
    @Test
    public void countResultTestTrue2() throws Exception {
        task178d.setInput("-647 4");
        task178d.countResult();
        Assert.assertEquals("Please, enter natural number in Integer range.",task178d.getResult());


    }
    @Test
    public void countResultTestTrue3() throws Exception {
        task178d.setInput("0 0 0");
        task178d.countResult();
        Assert.assertEquals("Please, enter natural number in Integer range.",task178d.getResult());


    }
    @Test
    public void countResultTestTrue4() throws Exception {
        task178d.setInput("7 7 7 7");
        task178d.countResult();
        Assert.assertEquals("0",task178d.getResult());


    }
    @Test
    public void countResultTestNotNull() throws Exception {

        Assert.assertNotNull(task178d.getResult());

    }


    @After
    public void afterTest() throws Exception {
        task178d.setInput("");
        task178d.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task178d = null;
    }

}