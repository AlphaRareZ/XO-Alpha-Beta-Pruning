# XO-Alpha-Beta-Pruning
This repository contains a Java implementation of a TicTacToe game. The game features a console-based user interface where a human player can play against the computer. The computer uses the minimax algorithm with alpha-beta pruning to determine the optimal moves.
---

## TicTacToe Game in Java

### Overview
This repository contains a Java implementation of a TicTacToe game. The game features a console-based user interface where a human player can play against the computer. The computer uses the minimax algorithm with alpha-beta pruning to determine the optimal moves.

### Features
- **Dynamic Grid Initialization:** The game grid can be of any size, making the game scalable.
- **Human vs. Computer:** The game allows a human player to play against the computer.
- **Minimax Algorithm with Alpha-Beta Pruning:** The computer uses a sophisticated decision-making process to ensure optimal moves.
- **Win and Draw Detection:** The game checks for wins across rows, columns, and both diagonals, and can detect draw situations.
- **Pruning Count:** Tracks the number of pruned branches during the computer's decision-making process.

### How to Play
1. Clone the repository:
   ```sh
   git clone https://github.com/AlphaRareZ/XO-Alpha-Beta-Pruning.git
   ```
2. Follow the on-screen instructions to play the game. Enter your moves in the format `row column`.

### Classes and Methods

- **TicTacToe Class:**
  - **Attributes:**
    - `char[][] gameGrid`: The game grid.
    - `int size`: The size of the grid.
    - `int pruned`: Counter for pruned branches in alpha-beta pruning.
  - **Constructor:**
    - `TicTacToe(int size)`: Initializes the game grid with the specified size.
  - **Methods:**
    - `int playerTurn(int alpha, int beta)`: Manages the player's turn using the minimax algorithm for the maximizing player (X).
    - `int computerTurn(Move bestMove, int alpha, int beta)`: Manages the computer's turn using the minimax algorithm for the minimizing player (O).
    - `boolean gameOver()`: Checks if the game is over by verifying win conditions.
    - `boolean checkMainDiagonal()`, `boolean checkCounterDiagonal()`, `boolean checkRows()`, `boolean checkCols()`: Helper methods to check different win conditions.
    - `boolean noPossibleMoves()`: Checks if there are no possible moves left, indicating a draw.
    - `void startGame()`: Manages the game loop, handling player and computer moves and checking game status.
    - `void displayGrid()`: Displays the current state of the game grid.

- **Move Class:**
  - **Attributes:**
    - `int row`: The row of the move.
    - `int column`: The column of the move.
  - **Methods:**
    - `Move(int row, int column)`: Constructor to initialize the move.
    - `void setRow(int row)`: Sets the row of the move.
    - `void setColumn(int column)`: Sets the column of the move.

### Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

### License
This project is licensed under the MIT License.
