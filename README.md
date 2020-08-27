# Sudoku
This project is used to validate the Sudoku matrix using java.

What is Sudoku: (https://en.wikipedia.org/wiki/Sudoku)
A Sudoku puzzle consists of a 9x9 grid, where the objective is to fill the grid with the digits 1-9 such that each digit appears only once in each: 
• Row 
• Column 
• 3x3 Sub-grid (Note: The sub-grids do not overlap)

Main
 - the Main application is the program entry
 - main ask the user, weather he want to enter file path for data source y/n ?
 - if user choose not to enter data source file, the program run on a stored data and display the result for check a valid Sudoku Matrix and invalid one.
 - if the Matrix is not complete 9*9 the program gives run time exception error with missing row or element.
 - the program could check different dimeinsion Sudoku Matrix 4*4
 
 
 Suduko 
    /** The class build Matrices of rows, columns, boxes and validate each record in each Matrix if the all records are valid the Sudoku Matrix is valid  */
    /** the process 
     * fillMatrix take a tow dimensional array of N*N and fills rows matrix & columns matrix & boxes matrix
    /**
     * placeNumber place number in the calculated location into rows matrix & columns matrix & boxes matrix
     * it calls PlaceNumberInRow & PlaceNumberInColumn PlaceNumberInBox
     *
     * @param d   digit value of grid[row][col]
     * @param row row index
     * @param col column index
     */
     /**
      * isValid method checks whether a filled out grid is a valid Sudoku solution. 
      * matrix of ints to represent the grid. 
      * no values less than 1 or greater than 9 will appear in the grid.
      */

    /** validateRecord check if each element in the record is unique */

    /**
     * isValidGrid checks if the Filled Sudoku Grid is valid
     */
 
