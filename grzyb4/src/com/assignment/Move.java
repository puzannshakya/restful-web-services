package com.assignment;


/**
 * Class for Move
 */
public class Move {
    private int column;
    private int row;
    private BoardStatus boardStatus;

    /**
     * Constructor for Move
     */
    public Move(){

    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    public void setColumn(int column) {
        this.column = column;
    }



    public void setRow(int row) {
        this.row = row;
    }



    public void setBoardStatus(BoardStatus boardStatus) {
        this.boardStatus = boardStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return column == move.column &&
                row == move.row &&
                boardStatus == move.boardStatus;
    }

    @Override
    public String toString() {
        return "Move{" +
                "column=" + column +
                ", row=" + row +
                ", boardStatus=" + boardStatus +
                '}';
    }
}
