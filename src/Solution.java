import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    private static Queue<Integer> getLayerElementsInQueue(
            List<List<Integer>> matrix,
            int currentLayerNumber
    ) {
        int rowCnt = matrix.size();
        int columnCnt = matrix.get(0).size();
        int lastRow = rowCnt - currentLayerNumber - 1;
        int lastColumn = columnCnt - currentLayerNumber - 1;

        Queue<Integer> layerElements = new LinkedList<>();

        // Add elements of the first row to the queue
        for (int col = currentLayerNumber; col < lastColumn; col++) {
            layerElements.add(matrix.get(currentLayerNumber).get(col));
        }
        // Add elements of last column to the queue
        for (int row = currentLayerNumber; row < lastRow; row++) {
            layerElements.add(matrix.get(row).get(lastColumn));
        }
        // Add elements of last row to the queue
        for (int col = lastColumn; col > currentLayerNumber; col--) {
            layerElements.add(matrix.get(lastRow).get(col));
        }
        // Add elements of first column to the queue
        for (int row = lastRow; row > currentLayerNumber; row--) {
            layerElements.add(matrix.get(row).get(currentLayerNumber));
        }
        return layerElements;
    }

    private static void populateMatrixFromQueue(
            List<List<Integer>> matrix,
            Queue<Integer> queue,
            int currentLayerNumber
    ) {
        int rowCnt = matrix.size();
        int columnCnt = matrix.get(0).size();
        int lastRow = rowCnt - currentLayerNumber - 1;
        int lastColumn = columnCnt - currentLayerNumber - 1;

        // Add elements of the first row to the queue
        for (int col = currentLayerNumber; col < lastColumn; col++) {
            matrix.get(currentLayerNumber).set(
                    col,
                    queue.poll()
            );
        }
        // Add elements of last column to the queue
        for (int row = currentLayerNumber; row < lastRow; row++) {
            matrix.get(row).set(
                    lastColumn,
                    queue.poll()
            );
        }
        // Add elements of last row to the queue
        for (int col = lastColumn; col > currentLayerNumber; col--) {
            matrix.get(lastRow).set(
                    col,
                    queue.poll()
            );
        }
        // Add elements of first column to the queue
        for (int row = lastRow; row > currentLayerNumber; row--) {
            matrix.get(row).set(
                    currentLayerNumber,
                    queue.poll()
            );
        }
    }

    private static Queue<Integer> getRotatedQueue(
            Queue<Integer> queueToRet,
            int rotationFactor
    ) {
        queueToRet = new LinkedList<Integer>(queueToRet);
        for (int idx = 0; idx < rotationFactor; idx++) {
            queueToRet.add(queueToRet.poll());
        }
        return queueToRet;
    }

    private static void handleCurrentLayer(
            List<List<Integer>> originalMatrix,
            List<List<Integer>> newMatrix,
            int currentLayerNumber,
            int rotationFactor
    ) {
        Queue<Integer> layerEleemntsInQueue = getLayerElementsInQueue(
                originalMatrix,
                currentLayerNumber
        );
        rotationFactor = rotationFactor % layerEleemntsInQueue.size();
        Queue<Integer> rotatedLayerElements = getRotatedQueue(
                layerEleemntsInQueue,
                rotationFactor
        );
        populateMatrixFromQueue(
                newMatrix,
                rotatedLayerElements,
                currentLayerNumber
        );
    }

    public static List<List<Integer>> getRotatedMatrix(
            List<List<Integer>> inputMatrix,
            int rotationFactor
    ) {
        if (inputMatrix == null) {
            throw new IllegalArgumentException();
        }
        List<List<Integer>> newMatrix = inputMatrix.stream().
                map(ArrayList::new).
                collect(Collectors.toList());

        int row = inputMatrix.size();
        if (row == 0) {
            return newMatrix;
        }
        int col = inputMatrix.get(0).size();
        int layerCnt = Math.min(
                row,
                col
        ) / 2;
        for (int currentLayerNumber = 0; currentLayerNumber < layerCnt; currentLayerNumber++) {
            handleCurrentLayer(
                    inputMatrix,
                    newMatrix,
                    currentLayerNumber,
                    rotationFactor
            );
        }
        return newMatrix;
    }

    public static void matrixRotation(
            List<List<Integer>> matrix,
            int r
    ) {
        List<List<Integer>> rotatedMatrix = getRotatedMatrix(
                matrix,
                r
        );
        for (List<Integer> outerlist : rotatedMatrix) {
            for (Integer integer : outerlist) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll(
                "\\s+$",
                ""
        ).split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(
                0,
                m
        ).forEach(i -> {
            try {
                matrix.add(Stream.of(bufferedReader.readLine().replaceAll(
                        "\\s+$",
                        ""
                ).split(" ")).map(Integer::parseInt).collect(toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(
                matrix,
                r
        );

        bufferedReader.close();
    }
}
