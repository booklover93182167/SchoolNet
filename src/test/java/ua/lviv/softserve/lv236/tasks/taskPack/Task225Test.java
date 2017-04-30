package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Vasyl on 23.04.2017.
 * Test case for 225 task.
 */
public class Task225Test {
    /** Object of Task225 for test */
    static Task225 task225;

    @BeforeClass
    public static void beforeClass() throws Exception // run one time before all tests
    {
        task225 = new Task225();
    }

    @Before
    public void beforeTest() throws Exception // run before each tests
    {
        task225.setInput("");
        task225.setResult("");
    }

    @Test
    public void testGetResultNotNull() throws Exception {
        assertNotNull(task225.getResult()); // check not null
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task225.setInput("100");
        task225.countResult();
        assertEquals("2, 5, 10", task225.getResult()); // check equals
    }

    @Test
    public void testGetResultEmpty() throws Exception {
        task225.setInput("10");
        task225.countResult();
        assertEquals("", task225.getResult()); // check equals
    }

    @Test
    public void testGetResultTrue() throws Exception {
        task225.setInput("10000");
        task225.countResult();
        assertTrue("4, 20, 25, 50, 100".equals(task225.getResult())); // check if true
    }

    @Test
    public void testGetResultIllegalArg() throws Exception {
        task225.setInput("-1");
        task225.countResult();
        assertTrue(task225.getResult().startsWith("Wrong")); // check if true
    }
}
