package validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sudoku Class checks whether a filled out grid is a valid Sudoku Solution.
 * • Sudoku is a Matrix with dimensions 9*9.
 * • Row and column has unique elements between 1-9
 * • Row and column has one shared element.
 * • Square is a Matrix with dimensions 3*3 and contains unique elements between 1-9 and its elements is shared with 3-subRow and 3-subColumn.
 * • A matrix of integers represent's the grid.
 * • It's assumed no values less than 1 or greater than 9 will appear in the grid.
 */
public class Sudoku {

    private final int squareSize;
    private int N;
    private int[][] rows = new int[N][N];
    private int[][] columns = new int[N][N];
    private int[][] boxes = new int[N][N];

    public Sudoku() {
        //Default square Size 3*3
        squareSize = 3;
        N = squareSize * squareSize;
        rows = new int[N][N];
        columns = new int[N][N];
        boxes = new int[N][N];
    }

    public Sudoku(int squareSize) {
        //Default square Size 3*3
        this.squareSize = squareSize;
        N = squareSize * squareSize;
        rows = new int[N][N];
        columns = new int[N][N];
        boxes = new int[N][N];
    }

    /**
     * fillMatrix take a tow dimensional array of N*N and fills rows matrix & columns matrix & boxes matrix
     *
     * @param grid tow dimensional array of N*N
     */
    public void fillMatrix(int[][] grid) {
        // init rows, columns and boxes
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                placeNumber(grid[row][col], row, col);
            }
        }
    }

    /**
     * placeNumber place number in the calculated location into rows matrix & columns matrix & boxes matrix
     * it calls PlaceNumberInRow & PlaceNumberInColumn PlaceNumberInBox
     *
     * @param d   digit value of grid[row][col]
     * @param row row index
     * @param col column index
     */
    private void placeNumber(int d, int row, int col) {
        placeNumberInRow(d, row, col);
        placeNumberInColumn(d, row, col);
        placeNumberInBox(d, row, col);
    }

    private void placeNumberInRow(int d, int row, int col) {
        rows[row][col] = d;
    }

    private void placeNumberInColumn(int d, int row, int col) {
        columns[col][row] = d;
    }

    private void placeNumberInBox(int d, int row, int col) {
        /** bxId is the Box index */
        int bxId = (row / squareSize) * squareSize + (col / squareSize);
        /** bxElement is the index of element within the box  */
        int bxElement =  (col % squareSize) + ((row % squareSize) * squareSize);
        boxes[bxId][bxElement] = d;
    }

    /** validateRecord check if each element in the record is unique */
    public boolean isValidRecord(List<Integer> record, int gridSize) {
        return new HashSet<Integer>(record).size() == gridSize;
    }

    /**
     * isValidGrid checks if the Filled Sudoku Grid is valid
     */
    public boolean isValidGrid() {

        int gridLength = rows.length;
        for (int i = 0; i < gridLength; i++) {
            boolean isValidRow = isValidRecord(getRecord(rows, i), gridLength);
            if (!isValidRow) return false;
            boolean isValidColumn = isValidRecord(getRecord(columns, i), gridLength);
            if (!isValidColumn) return false;
            boolean isValidBox = isValidRecord(getRecord(boxes, i), gridLength);
            if (!isValidBox) return false;
        }
        return true;
    }

    /**
     * getRecord returns a record i of a matrix (row record | column record | box record)
     */
    private List<Integer> getRecord(int[][] matrix, int i) {
        return Arrays.stream(matrix[i]).boxed().collect(Collectors.toList());
    }

    /**
     * getters
     * @return
     */
    public int getSquareSize() {
        return squareSize;
    }
    public int[][] getRows() {
        return this.rows;
    }
    public int[][] getColumns() {
        return columns;
    }
    public int[][] getBoxes() {
        return boxes;
    }
}



