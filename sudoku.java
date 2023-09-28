import java.util.Scanner;

public class sudoku {

    public static void main(String[] args) {
        int[][] board = new int[9][9];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Don't leave spaces, instead write zeroes for spaces" );
        System.out.println("Enter the Sudoku puzzle (9x9 grid):");
        for (int i = 0; i < 9; i++) {
            String row = scanner.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = row.charAt(j) - '0';
                sc.close();
            }
        }

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku:");
            printSudoku(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Sudoku is solved
    }

    private static boolean isValidMove(int[][] board, int row, int col, int num) {
        // Check if 'num' is not present in the same row, column, or 3x3 grid
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num || board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private static void printSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
