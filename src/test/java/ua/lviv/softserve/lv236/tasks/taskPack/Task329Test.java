package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Kolja on 26.04.2017.
 */
public class Task329Test {

    private static Task329 task329;

    @BeforeClass
    public static void setUp() {
        task329 = new Task329();
    }

    @Before
    public void beforeTest() {
        task329.setInput("");
        task329.setResult("");
    }

    @Test
    public void testNull() {
        assertNotNull(task329.getResult());
    }

    @Test
    public void testEquals1() {
        task329.setInput("100, 4");
        task329.countResult();
        assertEquals(task329.getResult(), "2, 11, 20");
    }

    @Test
    public void testEquals2() {
        task329.setInput("500, 4");
        task329.countResult();
        assertEquals(task329.getResult(), "2, 11, 20, 101, 110, 200");
    }

    @Test
    public void testEquals3() {
        task329.setInput("99, 1");
        task329.countResult();
        assertEquals(task329.getResult(), "1, 10");
    }

    @After
    public void afterTest() {
        task329.setInput("");
        task329.setResult("");
    }

    @AfterClass
    public static void closeTest() {
        task329 = null;
    }
}
