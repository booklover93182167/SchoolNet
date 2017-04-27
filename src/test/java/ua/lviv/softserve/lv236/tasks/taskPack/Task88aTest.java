package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Soltys on 26.04.2017.
 */
public class Task88aTest {
    private static Task88a task88a;

    @BeforeClass
    public static void setUp() throws Exception {
        task88a = new Task88a();
    }

    @Before
    public void beforeTest() throws Exception {
        task88a.setInput("");
        task88a.setResult("");
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task88a.setInput("6");
        task88a.countResult();
        assertEquals("true", task88a.getResult());
    }

    @Test
    public void testGetResultTrue() throws Exception {
        task88a.setInput("123");
        task88a.countResult();
        assertTrue("false".equals(task88a.getResult()));

    }

    @After
    public void afterTest() throws Exception {
        task88a.setInput("");
        task88a.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task88a = null;
    }

}