package com.assignment;

import java.util.Arrays;

public class Board {
    private char[][] square;

    public Board(){
         square=new char[3][3];
         /*for(int i =0 ; i<=2 ;i++){
             for (int j=0 ; j<=2 ; j++){
                 setSquare(j,i,' ');
             }
         }*/


         square[0][0]=' ';
         square[0][1]=' ';
         square[0][2]=' ';
         square[1][0]=' ';
         square[1][1]=' ';
         square[1][2]=' ';
         square[2][0]=' ';
         square[2][1]=' ';
         square[2][2]=' ';
    }
    public char getSquare(int column , int row){

        return square[row][column];
    }

    public void  setSquare (int column , int row,char c){
        this.square[row][column] = c;
    }


    public void draw(){

        System.out.println( " " + square[0][0] + " | "
                + square[0][1] + " | " + square[0][2]
                );
        System.out.println("---+---+---");
        System.out.println( " " + square[1][0] + " | "
                + square[1][1] + " | " + square[1][2]
                );
        System.out.println("---+---+---");
        System.out.println(" " + square[2][0] + " | "
                + square[2][1] + " | " + square[2][2]
                );

    }

    public BoardStatus determineBoardStatus(){
        BoardStatus boardStatus;
        Boolean boardFull = Boolean.TRUE;

        for (int i = 0; i < 8; i++) {
            String line = null;

            switch (i) {
                case 0:
                    line = "" + square[0][0] + square[0][1] + square[0][2];
                    //System.out.println("line : " + line);
                    break;
                case 1:
                    line = "" + square[1][0] + square[1][1] + square[1][2];
                    //System.out.println("line : " + line);
                    break;
                case 2:
                    line = "" + square[2][0] + square[2][1] + square[2][2];
                    //System.out.println("line : " + line);
                    break;
                case 3:
                    line = "" + square[0][0] + square[1][0] + square[2][0];
                    //System.out.println("line : " + line);
                    break;
                case 4:
                    line = "" + square[0][1] + square[1][1] + square[2][1];
                    //System.out.println("line : " + line);
                    break;
                case 5:
                    line = "" + square[0][2] + square[1][2] + square[2][2];
                    //System.out.println("line : " + line);
                    break;
                case 6:
                    line = "" + square[0][0] + square[1][1] + square[2][2];
                    //System.out.println("line : " + line);
                    break;
                case 7:
                    line = "" + square[0][2] + square[1][1] + square[2][0];
                    //System.out.println("line : " + line);
                    break;
            }
            //For X winner
            if (line.equals("XXX")) {
                 boardStatus = BoardStatus.X_WINS;
                 return  boardStatus;
            }

            // For O winner
             if (line.equals("OOO")) {
                boardStatus = BoardStatus.O_WINS;
                 return  boardStatus;
            }
        }

            for(int i =0 ; i<=2 ;i++){
                for (int j=0 ; j<=2 ; j++){
                    if(square[i][j] == ' ')
                    {
                        boardFull = Boolean.FALSE;
                        break;
                    }
                }
            }
            if(boardFull){
                return BoardStatus.DRAW;
            }else{
                return  BoardStatus.UNFINISHED;
            }

    }

    public Move makeAMove(char currentPlayer){
        Move moveOutCome = new Move();
        Move tempOutCome = new Move();
        Boolean fullFlag = Boolean.TRUE;
        //char[][] tempSquare = this.square;
        //System.out.println("Current Player :" + currentPlayer);
       // System.out.println("Current Draw :");

        /**
         * Check for Any Immediate win
         */
        for(int row=0 ; row<=2 ;row++) {
            for (int column = 0; column <= 2; column++) {
                if(getSquare(column,row) != ' ') continue;

                //Reach here  only if the  square[i][j] position is blank
                setSquare(column,row,currentPlayer);
                if(currentPlayer == 'X'){
                    if(determineBoardStatus().equals(BoardStatus.X_WINS)){
                        moveOutCome.setRow(row);
                        moveOutCome.setColumn(column);
                        moveOutCome.setBoardStatus(BoardStatus.X_WINS);
                        return  moveOutCome;
                    }
                }

                if(currentPlayer == 'O'){
                    if(determineBoardStatus().equals(BoardStatus.O_WINS)){
                        moveOutCome.setRow(row);
                        moveOutCome.setColumn(column);
                        moveOutCome.setBoardStatus(BoardStatus.O_WINS);
                        return  moveOutCome;
                    }
                }

                setSquare(column,row,' ');
            }}


        /**
         * Check for Any Opponent Immediate win
         */
        for(int row=0 ; row<=2 ;row++) {
            for (int column = 0; column <= 2; column++) {
                if(getSquare(column,row) != ' ') continue;

                char opponentPlayer;

                if(currentPlayer == 'X'){
                    opponentPlayer ='O';
                }else{
                    opponentPlayer ='X';
                }

                //Reach here  only if the  square[i][j] position is blank
                setSquare(column,row,opponentPlayer);
                if(currentPlayer == 'O'){
                    if(determineBoardStatus().equals(BoardStatus.X_WINS)){
                        moveOutCome.setRow(row);
                        moveOutCome.setColumn(column);
                        moveOutCome.setBoardStatus(BoardStatus.DRAW);
                        return  moveOutCome;
                    }
                }

                if(currentPlayer == 'X'){
                    if(determineBoardStatus().equals(BoardStatus.O_WINS)){
                        moveOutCome.setRow(row);
                        moveOutCome.setColumn(column);
                        moveOutCome.setBoardStatus(BoardStatus.DRAW);
                        return  moveOutCome;
                    }
                }

                setSquare(column,row,' ');
            }}





        if(currentPlayer == 'X'){
            moveOutCome.setBoardStatus(BoardStatus.O_WINS);
        }else{
            moveOutCome.setBoardStatus(BoardStatus.X_WINS);
        }

        for(int row=0 ; row<=2 ;row++) {
            for (int column = 0; column <= 2; column++) {
                if(getSquare(column,row) != ' ') continue;
                //Reach here  only if the  square[i][j] position is blank
                 fullFlag = Boolean.FALSE;

                setSquare(column,row,currentPlayer);
                //System.out.println("setting " + currentPlayer + " in " + row + "," + column);
               //draw();

                if(currentPlayer == 'X' && determineBoardStatus().equals(BoardStatus.X_WINS)){
                    setSquare(column,row,' ');
                    moveOutCome.setColumn(column);
                    moveOutCome.setRow(row);
                    moveOutCome.setBoardStatus(BoardStatus.X_WINS);
                  //  System.out.println("X_Wins : move " + moveOutCome.toString());
                    return moveOutCome;
                }

                if(currentPlayer == 'O' && determineBoardStatus().equals(BoardStatus.O_WINS)){
                    setSquare(column,row,' ');
                    moveOutCome.setColumn(column);
                    moveOutCome.setRow(row);
                    moveOutCome.setBoardStatus(BoardStatus.O_WINS);
                    //System.out.println("O_WINS : move " + moveOutCome.toString());
                    return moveOutCome;
                }

                if(determineBoardStatus().equals(BoardStatus.DRAW)){
                    //System.out.println("Setting the " + row+","+column+" to draw");
                    moveOutCome.setRow(row);
                    moveOutCome.setColumn(column);
                    moveOutCome.setBoardStatus(BoardStatus.DRAW);
                    return moveOutCome;
                }

                if(currentPlayer == 'X'){
                    //System.out.println("Calling as :" + currentPlayer);
                    tempOutCome = makeAMove('O');
                }else{
                    //System.out.println("Calling as :" + currentPlayer);
                    tempOutCome = makeAMove('X');
                }
               // System.out.println("tempOutCome : " +  tempOutCome + " in " + row + "," + column + "," + currentPlayer);
                if(currentPlayer == 'X' && tempOutCome.getBoardStatus().equals(BoardStatus.O_WINS)){
                    setSquare(column,row,' ');
                    moveOutCome.setColumn(tempOutCome.getColumn());
                    moveOutCome.setRow(tempOutCome.getRow());
                    moveOutCome.setBoardStatus(BoardStatus.X_WINS);
                    //System.out.println("XX_Wins : move " + moveOutCome.toString());
                    return moveOutCome;
                }

                if(currentPlayer == 'O' && tempOutCome.getBoardStatus().equals(BoardStatus.X_WINS)){
                    setSquare(column,row,' ');
                    moveOutCome.setColumn(tempOutCome.getColumn());
                    moveOutCome.setRow(tempOutCome.getRow());
                    moveOutCome.setBoardStatus(BoardStatus.O_WINS);
                    //System.out.println("OO_WINS : move " + moveOutCome.toString());
                    return moveOutCome;
                }




                //System.out.println("Setting the " + row+","+column+" to empty");
                setSquare(column,row,' ');

            }
        }

     if(fullFlag){
         Move movedraw= new Move();
         movedraw.setBoardStatus(BoardStatus.DRAW);
         return  movedraw;
     }

        return  moveOutCome;
    }


    @Override
    public String toString() {
        return "Board{" +
                "square=" + Arrays.toString(square) +
                '}';
    }
}
