import java.util.*;

import static java.lang.Math.*;

public class TicTacToe {
    char[][] gameGrid;
    int size, pruned = 0;

    public TicTacToe(int size) {
        this.size = size;
        gameGrid = new char[size][size];
        // initialize the grid size and clear it.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameGrid[i][j] = '.';
            }
        }
    }

    int playerTurn(int alpha, int beta) { // maximizing player -> X

        if (gameOver()) return -1;
        if (noPossibleMoves()) return 0;

        int maxVal = -Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // if the cell is empty do the move then undo it
                if (gameGrid[i][j] == '.') {
                    // do
                    gameGrid[i][j] = 'X';
                    int scoreAfterThisMove = computerTurn(new Move(-1, -1), alpha, beta);
                    maxVal = max(maxVal, scoreAfterThisMove);
                    // undo
                    gameGrid[i][j] = '.';
                    // maximize the alpha value
                    alpha = max(alpha, maxVal);
                    // prune remaining branches if beta became less than or equal to alpha
                    if (beta <= alpha) {
                        ++pruned;
                        break;
                    }
                }

            }
        }
        return maxVal;
    }

    int computerTurn(Move bestMove, int alpha, int beta) { // minimizing player -> O
        if (gameOver()) return 1;
        if (noPossibleMoves()) return 0;

        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // if the cell is empty do the move then undo it
                if (gameGrid[i][j] == '.') {
                    // do
                    gameGrid[i][j] = 'O';
                    int scoreAfterThisMove = playerTurn(alpha, beta);
                    if (scoreAfterThisMove < minVal) {
                        minVal = scoreAfterThisMove;
                        bestMove.setRow(i);
                        bestMove.setColumn(j);
                    }
                    // undo
                    gameGrid[i][j] = '.';
                    // minimize the beta value
                    beta = min(beta, minVal);
                    // prune remaining branches if beta became less than or equal to alpha
                    if (beta <= alpha) {
                        ++pruned;
                        break;
                    }
                }

            }
        }
        return minVal;
    }

    boolean gameOver() {
        return checkMainDiagonal() | checkCounterDiagonal() | checkRows() | checkCols();
    }

    boolean checkMainDiagonal() {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(gameGrid[i][i]);
        }
        return set.size() == 1 && !set.contains('.');
    }

    boolean checkCounterDiagonal() {
        Set<Character> set = new HashSet<>();
        for (int i = 0, j = size - 1; i < size && j >= 0; j--, i++) {
            set.add(gameGrid[i][j]);
        }
        return set.size() == 1 && !set.contains('.');
    }

    boolean checkRows() {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                set.add(gameGrid[i][j]);
            }
            boolean isOver = set.size() == 1 && !set.contains('.');

            if (isOver)
                return true;
            else
                set.clear();
        }
        return false;
    }

    boolean checkCols() {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                set.add(gameGrid[j][i]);
            }
            boolean isOver = set.size() == 1 && !set.contains('.');

            if (isOver)
                return true;
            else
                set.clear();
        }
        return false;
    }

    boolean noPossibleMoves() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameGrid[i][j] == '.') return false;
            }
        }
        return true;
    }

    void startGame() {
        int playerI, playerJ;
        Scanner scanner = new Scanner(System.in);
        displayGrid();
        while (true) {
            System.out.println("Enter I,J: ");
            playerI = scanner.nextInt();
            playerJ = scanner.nextInt();

            // Check for invalid move
            if (playerI < 0 || playerI >= size || playerJ < 0 || playerJ >= size || gameGrid[playerI][playerJ] != '.') {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            gameGrid[playerI][playerJ] = 'X';

            // Check if player wins
            if (gameOver()) {
                System.out.println("Player Wins!");
                break;
            }

            // Check for draw
            if (noPossibleMoves()) {
                System.out.println("Draw");
                break;
            }

            Move bestMove = new Move(-1, -1);
            pruned = 0;
            computerTurn(bestMove, -Integer.MAX_VALUE, Integer.MAX_VALUE);
            System.out.println(pruned);
            // Check if game ends in a draw
            if (bestMove.getRow() == -1 || noPossibleMoves()) {
                System.out.println("Draw");
                break;
            }

            gameGrid[bestMove.getRow()][bestMove.getColumn()] = 'O';
            displayGrid();

            // Check if computer wins
            if (gameOver()) {
                System.out.println("Computer Wins");
                break;
            }

            // Check for draw
            if (noPossibleMoves()) {
                System.out.println("Draw");
                break;
            }
        }
        scanner.close();
    }

    void displayGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(gameGrid[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}