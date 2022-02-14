package com.assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for TicTacToe
 */
public class TicTacToe {
    private static Board board;

    public static void main(String[] args) {
	// write your code here
        board = new Board();
        int numInput;
        Scanner in = new Scanner(System.in);
        Move move = new Move();


        System.out.println(board.determineBoardStatus());
        board.draw();

        /**
         * Start of the Program
         */

        System.out.println("Tic Tac Toe");
        System.out.println("1. Play against human");
        System.out.println("2. Play against computer (Human: X, Computer: O)");
        System.out.println("3. Play against computer (Computer: X, Human: O)");
        System.out.println("4. End program");

        /**
         * Repeat Until user inputs from 1-4 only
         */
        while(true){
            Boolean noError = Boolean.TRUE;
            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 4)) {
                    System.err.println("Invalid input; Please re-enter from 1-4 only");
                    in.nextLine();
                    continue;
                }

            }catch (InputMismatchException e) {
                System.err.println("Invalid input; Please re-enter from 1-4 only");
                in.nextLine();
                continue;
            }
            if(noError){
                break;
            }
        }


        /**
         *  Check for Input Conditions
         */

        switch(numInput){

            case 1:
                playAgainstHuman(board,in);
                break;
            case 2:
                playAsHumanXComputerO(board,in);
                break;
            case  3:
                playAsHumanOComputerX(board,in);
                break;
            case 4:
                System.out.println("4. End program");

        }


    }

    /**
     * Play Against Human Board
     * @param board
     * @param in
     */
    public static  void playAgainstHuman(Board board,Scanner in){
        char turn = 'X';

        System.out.println("Human opponent");
        board.draw();

        /**
         * Play till BoardStatus is Unfinished
         */
        while(board.determineBoardStatus().equals(BoardStatus.UNFINISHED)){
            int row=0;
            int column=0;

                    System.out.println("Enter a column (0-2) for '" + turn + "':");
                    try{
                        column = in.nextInt();
                        if (!(column >= 0 && column <= 2)) {
                            System.err.println("Invalid input , Please re-enter from 0-2 only");
                            in.nextLine();
                            continue;
                        }
                    }catch (InputMismatchException e) {
                        System.err.println("Invalid input , Please re-enter from 0-2 only ");
                        in.nextLine();
                        continue;
                    }


                    System.out.println("Enter a row (0-2) for '" + turn + "':");
                 try {
                     row = in.nextInt();
                     if (!(row >= 0 && row <= 2)) {
                         System.err.println("Invalid input ,Please re-enter from 0-2 only");
                         in.nextLine();
                         continue;
                     }
                 }
                    catch (InputMismatchException e) {
                    System.err.println("Invalid input , Please re-enter from 0-2 only");
                    in.nextLine();
                    continue;
                }

                if(board.getSquare(column,row) != ' '){
                    System.err.println("Slot already taken , Please re-enter");
                    continue;
                }

            board.setSquare(column,row,turn);
            board.draw();

            if (turn =='X') {
                turn = 'O';
            }
            else {
                turn = 'X';
            }


        }
        System.out.println(board.determineBoardStatus());
    }

    /**
     * play As Human X and Computer O Board
     * @param board
     * @param in
     */
    public static  void playAsHumanXComputerO(Board board,Scanner in){
        char turn = 'X';

        System.out.println("Human: X, Computer: O");
        board.draw();
        Move move = new Move();
        while(board.determineBoardStatus().equals(BoardStatus.UNFINISHED)){

            if(turn == 'X'){
                System.out.println("Human: X turn");
                int row=0;
                int column=0;

                System.out.println("Enter a column (0-2) for '" + turn + "':");
                try{
                    column = in.nextInt();
                    if (!(column >= 0 && column <= 2)) {
                        System.err.println("Invalid input , Please re-enter from 0-2 only");
                        in.nextLine();
                        continue;
                    }
                }catch (InputMismatchException e) {
                    System.err.println("Invalid input , Please re-enter from 0-2 only ");
                    in.nextLine();
                    continue;
                }


                System.out.println("Enter a row (0-2) for '" + turn + "':");
                try {
                    row = in.nextInt();
                    if (!(row >= 0 && row <= 2)) {
                        System.err.println("Invalid input ,Please re-enter from 0-2 only");
                        in.nextLine();
                        continue;
                    }
                }
                catch (InputMismatchException e) {
                    System.err.println("Invalid input , Please re-enter from 0-2 only");
                    in.nextLine();
                    continue;
                }

                if(board.getSquare(column,row) != ' '){
                    System.err.println("Slot already taken , Please re-enter");
                    continue;
                }
                board.setSquare(column,row,turn);

            }else{
                System.out.println("Computer: O");
                Board tempBoard = temporaryBoard(board);
                //board.draw();
                /**
                 * Call MakeAMove Method for Computer
                 */
                move = tempBoard.makeAMove(turn);
              //  board.setSquare(move.getColumn(),move.getRow(),turn);
                //System.out.println("returned from makeamove");
                //System.out.println("move :" + move.toString());
               // board.draw();
                //board = tempBoard;
                board.setSquare(move.getColumn(),move.getRow(),turn);
            }
            board.draw();
            if (turn =='X') {
                turn = 'O';
            }
            else {
                turn = 'X';
            }


        }
        System.out.println(board.determineBoardStatus());

    }

    /**
     * play As Human O And Computer X Board
     * @param board
     * @param in
     */
    public static  void playAsHumanOComputerX(Board board,Scanner in){
        char turn = 'X';
        System.out.println("Computer: X, Human: O");
        board.draw();
        Move move = new Move();
        while(board.determineBoardStatus().equals(BoardStatus.UNFINISHED)){

            if(turn == 'O'){
                System.out.println("Human: O turn");
                int row=0;
                int column=0;

                System.out.println("Enter a column (0-2) for '" + turn + "':");
                try{
                    column = in.nextInt();
                    if (!(column >= 0 && column <= 2)) {
                        System.err.println("Invalid input , Please re-enter from 0-2 only");
                        in.nextLine();
                        continue;
                    }
                }catch (InputMismatchException e) {
                    System.err.println("Invalid input , Please re-enter from 0-2 only ");
                    in.nextLine();
                    continue;
                }


                System.out.println("Enter a row (0-2) for '" + turn + "':");
                try {
                    row = in.nextInt();
                    if (!(row >= 0 && row <= 2)) {
                        System.err.println("Invalid input ,Please re-enter from 0-2 only");
                        in.nextLine();
                        continue;
                    }
                }
                catch (InputMismatchException e) {
                    System.err.println("Invalid input , Please re-enter from 0-2 only");
                    in.nextLine();
                    continue;
                }

                if(board.getSquare(column,row) != ' '){
                    System.err.println("Slot already taken , Please re-enter");
                    continue;
                }
                board.setSquare(column,row,turn);

            }else{
                System.out.println("Computer: X");
                Board tempBoard = temporaryBoard(board);
                //board.draw();
                /**
                 * Call MakeAMove Method for Computer
                 */
                move = tempBoard.makeAMove(turn);
                //  board.setSquare(move.getColumn(),move.getRow(),turn);
                //System.out.println("returned from makeamove");
                //System.out.println("move :" + move.toString());
                //board.draw();
                //board = tempBoard;
                board.setSquare(move.getColumn(),move.getRow(),turn);
            }
            board.draw();
            if (turn =='X') {
                turn = 'O';
            }
            else {
                turn = 'X';
            }


        }
        System.out.println(board.determineBoardStatus());
    }


    public static Board temporaryBoard( Board board){
        Board tempBoard  = new Board();
        for(int row = 0 ; row <=2 ;row++){
            for (int column=0;column<=2;column++){
                tempBoard.setSquare(column,row,board.getSquare(column,row));
            }
        }
        return tempBoard;
    }




}
