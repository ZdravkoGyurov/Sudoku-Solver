public class Sudoku {

    private static void printBoard(int[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j]);
                System.out.print(" ");
                if(j == 2 || j == 5)
                    System.out.print("| ");
            }
            if(i == 2 || i == 5){
                System.out.println();
                System.out.println("---------------------");
            }
            else
                System.out.println();
        }
    }

    private static boolean isRowAvailable(int[][] board, int coordinateX, int value){
        for(int i = 0; i < 9; i++){
            if(board[coordinateX][i] == value)
                return false;
        }
        return true;
    }

    private static boolean isColumnAvailable(int[][] board, int coordinateY, int value){
        for(int i = 0; i < 9; i++){
            if(board[i][coordinateY] == value)
                return false;
        }
        return true;
    }

    private static boolean isSquareAvailable(int[][] board, int coordinateX, int coordinateY, int value){
        int firstNewX = 0, secondNewX = 0, firstNewY = 0, secondNewY = 0;

        if(coordinateX >= 0 && coordinateX <= 2){
            firstNewX = 0;
            secondNewX = 2;
        }
        else if(coordinateX >= 3 && coordinateX <= 5){
            firstNewX = 3;
            secondNewX = 5;
        }
        else if(coordinateX >= 6 && coordinateX <= 8){
            firstNewX = 6;
            secondNewX = 8;
        }

        if(coordinateY >= 0 && coordinateY <= 2){
            firstNewY = 0;
            secondNewY = 2;
        }
        else if(coordinateY >= 3 && coordinateY <= 5){
            firstNewY = 3;
            secondNewY = 5;
        }
        else if(coordinateY >= 6 && coordinateY <= 8){
            firstNewY = 6;
            secondNewY = 8;
        }

        for(int i = firstNewX; i <= secondNewX; i++){
            for(int j = firstNewY; j <= secondNewY; j++){
                if(board[i][j] == value)
                    return false;
            }
        }
        return true;

    }

    private static boolean isAvailable(int[][] board, int coordinateX, int coordinateY, int value){
        if(isRowAvailable(board, coordinateX, value) &&
                isColumnAvailable(board, coordinateY, value) &&
                isSquareAvailable(board, coordinateX, coordinateY, value) &&
                board[coordinateX][coordinateY] == 0)
            return true;
        return false;
    }

    private static boolean findEmptySpot(int[][] board, int[] coordinates){
        for(coordinates[0] = 0; coordinates[0] < 9; coordinates[0]++){
            for(coordinates[1] = 0; coordinates[1] < 9; coordinates[1]++){
                if(board[coordinates[0]][coordinates[1]] == 0)
                    return true;
            }
        }
        return false;
    }

    private static boolean solveSudoku(int[][] board){
        int x = 0, y = 0;
        int[] coordinates = {x, y};

        if (!findEmptySpot(board, coordinates))
            return true;

        x = coordinates[0];
        y = coordinates[1];

        for(int num = 1; num <= 9; num++)
        {
            if (isAvailable(board, x, y, num))
            {
                board[x][y] = num;

                if(solveSudoku(board))
                    return true;

                board[x][y] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board1 = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                          {5, 2, 0, 0, 0, 0, 0, 0, 0},
                          {0, 8, 7, 0, 0, 0, 0, 3, 1},
                          {0, 0, 3, 0, 1, 0, 0, 8, 0},
                          {9, 0, 0, 8, 6, 3, 0, 0, 5},
                          {0, 5, 0, 0, 9, 0, 6, 0, 0},
                          {1, 3, 0, 0, 0, 0, 2, 5, 0},
                          {0, 0, 0, 0, 0, 0, 0, 7, 4},
                          {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        int[][] board2 = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                          {6, 0, 0, 1, 9, 5, 0, 0, 0},
                          {0, 9, 8, 0, 0, 0, 0, 6, 0},
                          {8, 0, 0, 0, 6, 0, 0, 0, 3},
                          {4, 0, 0, 8, 0, 3, 0, 0, 1},
                          {7, 0, 0, 0, 2, 0, 0, 0, 6},
                          {0, 6, 0, 0, 0, 0, 2, 8, 0},
                          {0, 0, 0, 4, 1, 9, 0, 0, 5},
                          {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        if(solveSudoku(board1) == true)
            printBoard(board1);
        else
            System.out.printf("No solution exists");

    }
}
