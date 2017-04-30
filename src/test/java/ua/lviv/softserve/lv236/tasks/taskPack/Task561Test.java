package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Vasyl on 26.04.2017.
 * Test case for 561 task.
 */
public class Task561Test {
    /** Object of Task561 for test */
    static Task561 task561;

    @BeforeClass
    public static void beforeClass() throws Exception // run one time before all tests
    {
        task561 = new Task561();
    }

    @Before
    public void beforeTest() throws Exception {
        task561.setInput("");
        task561.setResult("");
    }

    @Test
    public void testCountResultEquals() throws Exception {
        task561.setInput("100");
        task561.countResult();
        assertEquals("1 5 6 25 76", task561.getResult());
    }

    @Test
    public void testCountResultTrue() throws Exception {
        task561.setInput("1");
        task561.countResult();
        assertTrue("1".equals(task561.getResult()));
    }

    @Test(expected = NumberFormatException.class)
    public void testCountResultException() throws Exception {
        task561.countResult();
    }

    @Test
    public void testCountResultNotNull() throws Exception {
        assertNotNull(task561.getResult());
    }
}
