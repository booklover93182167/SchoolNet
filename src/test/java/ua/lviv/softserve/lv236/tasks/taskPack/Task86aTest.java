package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Volodymyr on 26.04.2017.
 */
public class Task86aTest {
    private static Task86a task86a;

    @BeforeClass
    public static void setUp() throws Exception {
        task86a = new Task86a();
    }

    @Before
    public void beforeTest() throws Exception {
        task86a.setInput("");
        task86a.setResult("");
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task86a.setInput("123456789");
        task86a.countResult();
        assertEquals("9", task86a.getResult());
    }

    @Test
    public void testGetResultTrue() throws Exception {
        task86a.setInput("123");
        task86a.countResult();
        assertTrue("3".equals(task86a.getResult()));
    }

    @After
    public void afterTest() throws Exception {
        task86a.setInput("");
        task86a.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task86a = null;
    }
}