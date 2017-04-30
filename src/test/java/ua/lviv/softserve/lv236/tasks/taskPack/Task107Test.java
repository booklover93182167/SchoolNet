package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Vasyl on 26.04.2017.
 * Test case for 107 task.
 */
public class Task107Test {
    /** Object of Task107 for test */
    static Task107 task107;

    @BeforeClass
    public static void beforeClass() throws Exception // run one time before all tests
    {
        task107 = new Task107();
    }

    @Before
    public void beforeTest() throws Exception {
        task107.setInput("");
        task107.setResult("");
    }

    @Test
    public void countResultEquals() throws Exception {
        task107.setInput("1000");
        task107.countResult();
        assertEquals("4", task107.getResult());
    }

    @Test
    public void countResultTrue() throws Exception {
        task107.setInput("1000000");
        task107.countResult();
        assertTrue("9".equals(task107.getResult()));
    }

    @Test
    public void testGetResultNotNull() throws Exception {
        assertNotNull(task107.getResult()); // check not null
    }

    @Test
    public void testGetResultIllegalArg() throws Exception {
        task107.setInput("-1");
        task107.countResult();
        assertTrue(task107.getResult().startsWith("Wrong")); // check if true
    }
}
