public class Move {
    private int row, column;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
