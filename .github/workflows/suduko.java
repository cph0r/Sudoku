import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class suduko {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
//        int[][] sudoku = new int[9][9];
//        takeInputMatrix(sudoku);
        int[][] sudoku = {{5,0,0,0,9,7,0,0,0},
                {0,1,0,8,0,0,2,0,0},
                {0,7,9,2,0,0,0,1,0},
                {0,0,0,0,0,0,4,0,8},
                {3,0,0,0,0,0,0,0,1},
                {9,0,1,0,0,0,0,0,0},
                {0,6,0,0,0,8,1,5,0},
                {0,0,5,0,0,1,0,6,0},
                {0,0,0,7,6,0,0,0,3}};
        display(sudoku);
        solve(sudoku);
        System.out.println("Solution");
        display(sudoku);
        solve(sudoku);
        System.out.println("Solution2");
        display(sudoku);
        solve(sudoku);
        System.out.println("Solution3");
        display(sudoku);
        solve(sudoku);
        System.out.println("Solution4");
        display(sudoku);
        solve(sudoku);
        System.out.println("Solution5");
        display(sudoku);
        System.out.println("Solution6");
        display(sudoku);
        System.out.println("Solution6");
        display(sudoku);
    }

    static void solve(int[][] sudoku) {
        createOptions(sudoku);
        fillSingleOption(sudoku);
    }

    static Object[][] createOptions(int[][] sudoku) {
        Object[][] optionsArray = new Object[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set<Integer> options = new HashSet<>();
                if (sudoku[i][j] == 0) {
                    for (int k = 1; k <=9; k++) {
                        if (checkInput(sudoku, k, i, j)) {
                            options.add(k);
                        }
                    }
                    optionsArray[i][j] = options;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(optionsArray[i][j]);
            }
        }
        return optionsArray;
    }

    static void fillSingleOption(int[][] sudoku) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set<Integer> options = new HashSet<>();
                if (sudoku[i][j] == 0) {
                    for (int k = 1; k <=9; k++) {
                        if (checkInput(sudoku, k, i, j)) {
                            options.add(k);
                        }
                    }
                    if (options.size() == 1) {
//                        System.out.println("singleOption" + options.iterator().next());
                        sudoku[i][j] = options.iterator().next();
                    }
                }
            }
        }
    }

    static Boolean rowCheck(int[][] sudoku, int rowIndex, int value) {
        Set<Integer> rowValues = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (sudoku[rowIndex][j] != 0) {
                rowValues.add(sudoku[rowIndex][j]);
            }
        }
        if (!rowValues.contains(value)) {
            return true;
        }
        return false;
    }

    static Boolean columnCheck(int[][] sudoku, int columnIndex, int value) {
        Set<Integer> columnValues = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][columnIndex] != 0) {
                columnValues.add(sudoku[i][columnIndex]);
            }
        }
        if (!columnValues.contains(value)) {
            return true;
        }
        return false;
    }

    static Boolean blockCheck(int[][] sudoku, int rowIndex, int columnIndex, int value) {
        Set<Integer> blockValues = new HashSet<>();
        int rowStart = 0;
        int rowEnd = 0;
        int columnStart = 0;
        int columnEnd = 0;
        int rowBlock = getBlockNumber(rowIndex);
        if (rowBlock == 1) {
            rowStart = 0;
            rowEnd = 2;
        }

        if (rowBlock == 2) {
            rowStart = 3;
            rowEnd = 5;
        }

        if (rowBlock == 3) {
            rowStart = 6;
            rowEnd = 8;
        }
        int columnBlock = getBlockNumber(columnIndex);
        if (columnBlock == 1) {
            columnStart = 0;
            columnEnd = 2;
        }

        if (columnBlock == 2) {
            columnStart = 3;
            columnEnd = 5;
        }

        if (columnBlock == 3) {
            columnStart = 6;
            columnEnd = 8;
        }

        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = columnStart; j <= columnEnd; j++)
                if (sudoku[i][j] != 0) {
                    blockValues.add(sudoku[i][j]);
                }
        }
//        System.out.println("Value" + value + " Block values: " + blockValues);
        if (!blockValues.contains(value)) {
            return true;
        }
        return false;
    }

    static int getBlockNumber(int index) {
        if (index >= 0 && index <= 2) {
            return 1;
        }
        if (index >= 3 && index <= 5) {
            return 2;
        }
        if (index >= 6 && index <= 8) {
            return 3;
        }
        return 0;
    }

    static void takeInputMatrix(int[][] sudoku) {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int input = s.nextInt();
                if (checkInput(sudoku, input, i, j)) {
                    sudoku[i][j] = input;
                }
            }
        }
    }

    static Boolean checkInput(int[][] sudoku, int input, int rowIndex, int columnIndex) {
        if (input <= 9 && input >= 0 &&
                rowCheck(sudoku, rowIndex, input) &&
                columnCheck(sudoku, columnIndex, input) &&
                blockCheck(sudoku, rowIndex, columnIndex, input)) {
            return true;
        }
        return false;
    }

    static void display(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 3 || j == 6) {
                    System.out.print(" ");
                }
                System.out.print(sudoku[i][j]);
            }
            if (i == 2 || i == 5) {
                System.out.println();
            }
            System.out.println();
        }
    }
}
