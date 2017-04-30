package ua.lviv.softserve.lv236.tasks.taskPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Soltys on 23.04.2017.
 * Task №555 involves the construction of Pascal's Triangle
 * contain one public method 'countResult'
 * <p>
 *
 * @author Soltys Yaroslav
 * @version 1.0
 */
public class Task555 extends GeneralTask {
    {
        name = "555";
        description = "Дано натуральне n. Отримати перші n рядків трикутника Паскаля";
    }

    /**
     * Will include character set according to the
     * results of countResult method.
     */
    private StringBuilder resultString = new StringBuilder();
    /**
     * Contain numbers in which lines of triangle.
     * Key corresponds to the number line,
     * value - is a line of numbers of triangle.
     */
    private Map<Integer, List<Integer>> triangleMap = new HashMap<>();   //

    /**
     * It is overridden method of GeneralTask class, which checks
     * the entered number and calls triangleBuilder method.
     */
    @Override
    public void countResult() {
        try {
            int numberOfLines = Integer.parseInt(getInput());
            if (getInput().contains(".") || numberOfLines <= 0) {
                throw new NumberFormatException();
            }
            setResult(triangleBuilder(numberOfLines));
        } catch (NumberFormatException e) {
            setResult("Please, enter one natural number.");
        }
    }

    /**
     * Build triangle given height, using getLineOfNumber method for each row.
     *
     * @param heightOfTriangle - the number of rows triangle
     * @return full line with result
     */
    private String triangleBuilder(int heightOfTriangle) {
        for (int currentLine = 0, k = 1; currentLine < heightOfTriangle; currentLine++, k += 2) {
            for (int j = 0; j < heightOfTriangle - k / 2; j++) {        // how many spaces need in this line
                resultString.append(' ');
            }
            triangleMap.put(currentLine, getLineOfNumber(k, currentLine));
            resultString.append('\n');
        }
        return resultString.toString();
    }

    /**Generates a string of characters and  add it to 'resultString'.
     * @param k           - transition number for the cycle from triangleBuilder method.
     * @param currentLine - is current line of triangle.
     * @return - List of numbers in the current line.
     */
    private List<Integer> getLineOfNumber(int k, int currentLine) {
        List<Integer> numbersInCurrentLine = new ArrayList<>();
        int mapCount = 0;
        for (int j = 0, count = 1; j < k; j++, count++) {
            if (count % 2 == 0) {         // between each number refers spaces
                resultString.append(' ');
            } else {
                if (count == 1 || count == k) {
                    resultString.append(1);
                    numbersInCurrentLine.add(1);
                    mapCount++;
                } else {
                    int m = triangleMap.get(currentLine - 1).get(mapCount - 1)
                            + triangleMap.get(currentLine - 1).get(mapCount);
                    resultString.append(m);
                    numbersInCurrentLine.add(m);
                    mapCount++;
                }
            }
        }
        return numbersInCurrentLine;
    }
}