package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Sensei on 26.04.2017.
 */
public class Task322Test {

    private static Task322 task322;

    @BeforeClass
    public static void setUp() throws Exception {
        task322 = new Task322();
    }


    @Before
    public void beforeTest() throws Exception{
        task322.setInput("");
        task322.setResult("");
    }

    @Test
    public void countResultEquals() throws Exception {
        task322.countResult();
        assertEquals("7560",task322.getResult());
    }

    @Test
    public void countResultTrue() throws Exception {
        task322.countResult();
        assertTrue("7560".equals(task322.getResult()));
    }


    @After
    public void afterTest() throws Exception{
        task322.setInput("");
        task322.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task322 = null;
    }

}