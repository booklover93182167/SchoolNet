package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Soltys on 26.04.2017.
 */
public class Task555Test {
    private static Task555 task555;

    @BeforeClass
    public static void setUp() throws Exception {
        task555 = new Task555();
    }

    @Before
    public void beforeTest() throws Exception {
        task555.setInput("");
        task555.setResult("");
    }

    @Test
    public void testResult() throws Exception {
        task555.setInput("6");
        task555.countResult();
        String result = "1\n     1 1\n    1 2 1\n   1 3 3 1\n  1 4 6 4 1\n 1 5 10 10 5 1";
        assertEquals(result, task555.getResult());
    }

    @Test
    public void testNegativeInput() throws Exception {
        task555.setInput("-1");
        task555.countResult();
        String result = "Please, enter one natural number.";
        assertEquals(result, task555.getResult());
    }

    @Test
    public void testNotNumberInput() throws Exception
    {
        task555.setInput("abc");
        task555.countResult();
        assertSame("Please, enter one natural number.", task555.getResult());
    }

    @Test
    public void testEmptyInput() throws Exception {
        task555.countResult();
        String result = "Please, enter one natural number.";
        assertEquals(result, task555.getResult());
    }

    @After
    public void afterTest() throws Exception {
        task555.setInput("");
        task555.setResult("");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        task555 = null;
    }
}