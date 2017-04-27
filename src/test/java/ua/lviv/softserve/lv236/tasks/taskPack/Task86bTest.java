package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;


public class Task86bTest {

    private static Task86b task86b;

    @BeforeClass
    public static void setUp() throws Exception {
        task86b = new Task86b();
    }

    @Before
    public void beforeTest() throws Exception {
        task86b.setInput("");
        task86b.setResult("");
    }

    @Test
    public void testGetResultEquals() throws Exception {
        task86b.setInput("123");
        task86b.countResult();
        assertEquals("6", task86b.getResult());
    }

    @Test
    public void testGetResultTrue() throws Exception {
        task86b.setInput("11134");
        task86b.countResult();
        assertTrue("10".equals(task86b.getResult()));
    }

    @After
    public void afterTest() throws Exception {
        task86b.setInput("");
        task86b.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task86b = null;
    }

}