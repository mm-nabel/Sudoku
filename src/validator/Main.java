package validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {
    /**
     * Main program take input file for matrix data and get if it is a valid Sudoku Matrix
     * if the user choose not to enter file path the program will run on stored matrix 9*9
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Do you want enter a source data file y/n ?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if (answer.toLowerCase().equals("y")) {
            System.out.println("Enter Sudoku Grid file path");
            answer = sc.nextLine();
            Sudoku sudokuObject = new Sudoku();
            sudokuObject.fillMatrix(readGrid(answer));
            System.out.println("The Grid you have entered is : ");
            System.out.println(sudokuObject.isValidGrid());
        } else {
            System.out.println("System will work on Default Stored Grid");
            Sudoku sudokuObject = new Sudoku();
            sudokuObject.fillMatrix(readGrid(true));
            System.out.println("System will check a valid Stored Grid : " + sudokuObject.isValidGrid());
            int[][] myrows = sudokuObject.getRows();
            for(int i =0 ;i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(myrows[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();

            sudokuObject = null;
            sudokuObject = new Sudoku();
            sudokuObject.fillMatrix(readGrid(false));

            System.out.println("System will check a invalid Stored Grid : " + sudokuObject.isValidGrid());

            myrows = sudokuObject.getRows();
            for(int i =0 ;i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(myrows[i][j]+" ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Helper method to read data from stored file of Sudoku Matrix 9*9
     * @param isValid if it is true the method choose  valid Sudoku Matrix, if false the method choose invalid Sudoku Matrix
     */
    public static int[][] readGrid(boolean isValid) throws IOException {

        ClassLoader classLoader = Main.class.getClassLoader();
        String valid = new File(classLoader.getResource("data/validgrid.txt").getFile()).getAbsolutePath();
        String invalid = new File(classLoader.getResource("data/invalidgrid.txt").getFile()).getAbsolutePath();

        int[][] validGrid = readGrid(valid);
        int[][] invalidGrid = readGrid(invalid);
        if (isValid)
            return validGrid;

        return invalidGrid;
    }

    /**
     * Helper method to read data from source file
     */
    public static int[][] readGrid(String filename) throws IOException {
        int[][] matrix = new int[9][9];
        String stringMatrix = new File(filename).getAbsoluteFile().toString();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        if(lines.size() <9) throw new RuntimeException("Matrix size not supported, record missing");
        for (int i = 0; i < 9; i++) {
            String[] cell = lines.get(i).split(" ");
            if(cell.length <9) throw new RuntimeException("Matrix size not supported, element missing");
            for (int j = 0; j < 9; j++) {
                    matrix[i][j] = Integer.valueOf(cell[j]);
            }
        }
        return matrix;
    }

}
