package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

public class Task325Test {

    private static Task325 task325;

    @BeforeClass
    public static void setUp() throws Exception {
        task325 = new Task325();
    }

    @Before
    public void beforeTest() throws Exception {
        task325.setInput("");
        task325.setResult("");
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task325.setInput("19");
        task325.countResult();
        assertEquals("1 19",task325.getResult());
    }


    @Test
    public void testGetResultTrue() throws Exception {
        task325.setInput("125");
        task325.countResult();
        assertTrue("1 5".equals(task325.getResult()));
    }

    @After
    public void afterTest() throws Exception {
        task325.setInput("");
        task325.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task325 = null;
    }

}