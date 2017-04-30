package ua.lviv.softserve.lv236.tasks.taskPack;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Soltys on 26.04.2017.
 */
public class Task243bTest {
    private static Task243b task243b;

    @BeforeClass
    public static void setUp() throws Exception // run one time before all tests
    {
        task243b = new Task243b();
    }

    @Before
    public void beforeTest() throws Exception // run before each tests
    {
        task243b.setInput("");
        task243b.setResult("");
    }

    @Test
    public void testEmptyInput() throws Exception
    {
        task243b.countResult();
        assertSame("Wrong input!", task243b.getResult());
    }

    @Test
    public void testNotNumberInput() throws Exception
    {
        task243b.setInput("abc");
        task243b.countResult();
        assertSame("Wrong input!", task243b.getResult());
    }
    @Test
    public void testNegativeNumberInput() throws Exception
    {
        task243b.setInput("-1");
        task243b.countResult();
        assertSame("Wrong input!", task243b.getResult());
    }

    @Test
    public void testZeroInput() throws Exception
    {
        task243b.setInput("0");
        task243b.countResult();
        assertSame("Wrong input!", task243b.getResult());
    }

    @Test
    public void testGetNegativeResult() throws Exception
    {
        task243b.setInput("23");
        task243b.countResult();
        String result = "23 неможливо представити у вигляді суми квадратів двох натуральних чисел";
        assertEquals(result, task243b.getResult());
    }

    @Test
    public void testGetResult() throws Exception
    {
        task243b.setInput("194");
        task243b.countResult();
        assertEquals("194= 13^2 +5^2", task243b.getResult());
    }

    @After
    public void afterTest() throws Exception // run after each tests
    {
        task243b.setInput("");
        task243b.setResult("");
    }

    @AfterClass
    public static void tearDwon() throws Exception // run one time after all tests
    {
        task243b = null;
    }
}