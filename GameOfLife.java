public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y) {
        // Construct a 2D array of the given x and y size.
        board = new int[x][y];
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if ((i + x) < board.length && (j + y) < board[0].length) { // Bounds check
                    board[i + x][j + y] = data[i][j];
                }
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    // Step the simulation forward one turn.
    public void step() {
        int[][] newBoard = new int[board.length][board[0].length];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int neighbors = countNeighbors(x, y);

                if (board[x][y] == 1) {
                    // Any live cell with 2 or 3 neighbors survives
                    newBoard[x][y] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    // Any dead cell with exactly 3 neighbors becomes alive
                    newBoard[x][y] = (neighbors == 3) ? 1 : 0;
                }
            }
        }

        board = newBoard; // Update the board with the next state
        print();
    }

    public int countNeighbors(int x, int y) {
        int count = 0;
        int m = board.length, n = board[0].length;

        // Count the number of neighbors the cell has
        // Use the get(x,y) method to read any board state you need.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                if (get(x + i, y + j) == 1) count++; // Count live neighbors
            }
        }

        return count;
    }

    // Get a value from the board with "wrap around"
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit = board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get() {
        return board;
    }

    // Test helper to print the current state
    public void print() {
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y] == 1 ? "⬛" : "⬜");
            }
        }
        System.out.println();
    }
}