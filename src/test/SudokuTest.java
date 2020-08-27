package test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validator.Main;
import validator.Sudoku;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    Sudoku sudoku;
    Integer squareSize;
    String path;

    @BeforeEach
    void setUp() throws IOException {
        sudoku = null;
        sudoku = new Sudoku();
        squareSize = sudoku.getSquareSize();
        ClassLoader classLoader = Main.class.getClassLoader();
        path = classLoader.getResource("data").getPath();
        sudoku.fillMatrix(readInput(path + "/validgrid.txt"));
    }

    @AfterEach
    void tearDown() {
        sudoku = null;
        squareSize = null;
        String path = null;
    }

    @Test
    void fillMatrixTest() throws IOException {
        getRowsTest();
        getColumnsTest();
        getBoxesTest();
    }

    @Test
    void isValidRecordTest() {
        assertTrue(sudoku.isValidRecord(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 9));
        assertFalse(sudoku.isValidRecord(Arrays.asList(1, 2, 3, 4, 1, 6, 7, 8, 9), 9));
    }

    @Test
    void isValidGridTest() throws IOException {

        sudoku.fillMatrix(readInput(path + "/validgrid.txt"));
        assertTrue(sudoku.isValidGrid());

        sudoku = null;
        sudoku = new Sudoku();
        sudoku.fillMatrix(readInput(path + "/invalidgrid.txt"));
        assertFalse(sudoku.isValidGrid());

    }


    @Test
    void getSquareSizeTest() {
        assertTrue(3 == sudoku.getSquareSize());
        sudoku = null;
        sudoku = new Sudoku(2);
        assertFalse(3 == sudoku.getSquareSize());
    }

    @Test
    void getRowsTest() throws IOException {
        List expected = Arrays.asList(readInput(path + "/rowgrid.txt"));
        List actual = Arrays.asList(sudoku.getRows());
        assertArrayEquals(expected.toArray(), actual.toArray());

    }

    @Test
    void getColumnsTest() throws IOException {
        List expected = Arrays.asList(readInput(path + "/columngrid.txt"));
        List actual = Arrays.asList(sudoku.getColumns());
        assertArrayEquals(expected.toArray(), actual.toArray());

    }


    @Test
    void getBoxesTest() throws IOException {
        List expected = Arrays.asList(readInput(path + "/myboxgrid.txt"));
        List actual = Arrays.asList(sudoku.getBoxes());
        assertArrayEquals(expected.toArray(), actual.toArray());

    }

    private int[][] readInput(String filename) throws IOException {
        int[][] matrix = new int[9][9];
        String stringMatrix = new File(filename).getAbsoluteFile().toString();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        for (int i = 0; i < 9; i++) {
            String[] cell = lines.get(i).split(" ");
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = Integer.valueOf(cell[j]);
            }
        }
        return matrix;
    }
}
