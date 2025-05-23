import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {
    List<List<Integer>> getTwoDimensionalList(
            int rowCnt,
            int colCnt,
            int[] elements
    ) {
        List<List<Integer>> twoDimensionalList = new ArrayList<>();
        for (int rowIdx = 0; rowIdx < rowCnt; rowIdx++) {
            List<Integer> curRow = new ArrayList<Integer>();
            for (int colIdx = 0; colIdx < colCnt; colIdx++) {
                curRow.add(elements[rowIdx * colCnt + colIdx]);
            }
            twoDimensionalList.add(curRow);
        }
        return twoDimensionalList;
    }

    @org.junit.jupiter.api.Test
    void testSampleExampleOne() {
        int rowCnt = 4;
        int colCnt = 4;
        List<List<Integer>> inputMatrix = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 2, 3, 4,
                        5, 6, 7, 8,
                        9, 10, 11, 12,
                        13, 14, 15, 16
                }
        );
        int rotationFactor = 2;
        List<List<Integer>> actualOutput = Result.getRotatedMatrix(
                inputMatrix,
                rotationFactor
        );
        List<List<Integer>> expectedOutput = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        3, 4, 8, 12,
                        2, 11, 10, 16,
                        1, 7, 6, 15,
                        5, 9, 13, 14,
                }
        );
        assertEquals(
                expectedOutput,
                actualOutput
        );
    }

    @org.junit.jupiter.api.Test
    void testSampleExampleTwo() {
        int rowCnt = 5;
        int colCnt = 4;
        List<List<Integer>> inputMatrix = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 2, 3, 4,
                        7, 8, 9, 10,
                        13, 14, 15, 16,
                        19, 20, 21, 22,
                        25, 26, 27, 28
                }
        );
        int rotationFactor = 7;
        List<List<Integer>> actualOutput = Result.getRotatedMatrix(
                inputMatrix,
                rotationFactor
        );
        List<List<Integer>> expectedOutput = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        28, 27, 26, 25,
                        22, 9, 15, 19,
                        16, 8, 21, 13,
                        10, 14, 20, 7,
                        4, 3, 2, 1
                }
        );
        assertEquals(
                expectedOutput,
                actualOutput
        );
    }

    @org.junit.jupiter.api.Test
    void testSampleExampleThree() {
        int rowCnt = 2;
        int colCnt = 2;
        List<List<Integer>> inputMatrix = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 1,
                        1, 1
                }
        );
        int rotationFactor = 3;
        List<List<Integer>> actualOutput = Result.getRotatedMatrix(
                inputMatrix,
                rotationFactor
        );
        List<List<Integer>> expectedOutput = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 1,
                        1, 1
                }
        );
        assertEquals(
                expectedOutput,
                actualOutput
        );
    }

    @org.junit.jupiter.api.Test
    void testOuterNoEffectiveRotationInnerEffectiveRotation() {
        int rowCnt = 5;
        int colCnt = 4;
        List<List<Integer>> inputMatrix = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 2, 3, 4,
                        7, 8, 9, 10,
                        13, 14, 15, 16,
                        19, 20, 21, 22,
                        25, 26, 27, 28
                }
        );
        int rotationFactor = 14;
        List<List<Integer>> actualOutput = Result.getRotatedMatrix(
                inputMatrix,
                rotationFactor
        );
        List<List<Integer>> expectedOutput = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 2, 3, 4,
                        7, 15, 21, 10,
                        13, 9, 20, 16,
                        19, 8, 14, 22,
                        25, 26, 27, 28
                }
        );
        assertEquals(
                expectedOutput,
                actualOutput
        );
    }

    @org.junit.jupiter.api.Test
    void testFourByTwoMatrix() {
        int rowCnt = 4;
        int colCnt = 2;
        List<List<Integer>> inputMatrix = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 2,
                        3, 4,
                        5, 6,
                        7, 8
                }
        );
        int rotationFactor = 1;
        List<List<Integer>> actualOutput = Result.getRotatedMatrix(
                inputMatrix,
                rotationFactor
        );
        List<List<Integer>> expectedOutput = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        2, 4,
                        1, 6,
                        3, 8,
                        5, 7
                }
        );
        assertEquals(
                expectedOutput,
                actualOutput
        );
    }

    @org.junit.jupiter.api.Test
    void testTwoByFourMatrix() {
        int rowCnt = 2;
        int colCnt = 4;
        List<List<Integer>> inputMatrix = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        1, 2, 3, 4,
                        5, 6, 7, 8
                }
        );
        int rotationFactor = 1;
        List<List<Integer>> actualOutput = Result.getRotatedMatrix(
                inputMatrix,
                rotationFactor
        );
        List<List<Integer>> expectedOutput = getTwoDimensionalList(
                rowCnt,
                colCnt,
                new int[]{
                        2, 3, 4, 8,
                        1, 5, 6, 7
                }
        );
        assertEquals(
                expectedOutput,
                actualOutput
        );
    }
}
