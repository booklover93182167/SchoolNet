package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Volodymyr on 26.04.2017.
 */
public class Task559Test {
    private static Task559 task559;

    @BeforeClass
    public static void setUp() throws Exception {
        task559 = new Task559();
    }

    @Before
    public void beforeTest() throws Exception {
        task559.setInput("");
        task559.setResult("");
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task559.setInput("10");
        task559.countResult();
        String result = "[1, 3, 7]";
        assertEquals(result, task559.getResult());
    }

    @Test
    public void testGetResultTrue() throws Exception {
        task559.setInput("100");
        task559.countResult();
        String result = "[1, 3, 7, 31]";
        assertTrue(result.equals(task559.getResult()));
    }

    @After
    public void afterTest() throws Exception {
        task559.setInput("");
        task559.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task559 = null;
    }
}