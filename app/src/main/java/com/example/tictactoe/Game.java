package com.example.tictactoe;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    public int moves;
    public Boolean playerOneTurn;
    public Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
        moves = 0;
    }

    public boolean isBetween(int value, int min, int max)
    {
        return((value > min) && (value < max));
    }

    public TileState choose(int row, int column) {

        if (isBetween(row, 4, 4) && isBetween(column, 4, 4)) {
            return TileState.INVALID;
        }

        TileState tilestate = board[row-1][column-1];

        switch(tilestate) {
            case BLANK:
                moves += 1;
                playerOneTurn ^= true;
                if (playerOneTurn) {
                    board[row-1][column-1] = TileState.CIRCLE;
                } else {
                    board[row-1][column-1] = TileState.CROSS;
                }
                break;
            default:
                board[row-1][column-1] = TileState.INVALID;
        }
        return board[row-1][column-1];
    }

    public GameState won(int row, int col, TileState state) {

        boolean win=true;

        //check row
        for(int i=0; i<BOARD_SIZE; i++){
            if(board[row-1][i]!=state){
                win=false;
                break;
            }
        }
        if (win){
            gameOver = true;
            if (playerOneTurn){
                return GameState.PLAYER_ONE;
            }
            else {
                return GameState.PLAYER_TWO;
            }
        }

        win=true;
        for(int j=0; j<BOARD_SIZE; j++){
            if(board[j][col-1]!=state){
                win=false;
                break;
            }
        }
        if (win){
            gameOver = true;
            if (playerOneTurn){
                return GameState.PLAYER_ONE;
            }
            else {
                return GameState.PLAYER_TWO;
            }
        }

        win=true;
        for(int k=0; k<BOARD_SIZE; k++){
            if(board[k][k]!=state){
                win=false;
                break;
            }
        }
        if (win){
            gameOver = true;
            if (playerOneTurn){
                return GameState.PLAYER_ONE;
            }
            else {
                return GameState.PLAYER_TWO;
            }
        }

        win=true;
        for(int l=0; l<BOARD_SIZE; l++){
            if(board[l][BOARD_SIZE-l-1]!=state){
                win=false;
                break;
            }
        }

        if (win){
            gameOver = true;
            if (playerOneTurn){
                return GameState.PLAYER_ONE;
            }
            else {
                return GameState.PLAYER_TWO;
            }
        }

        return GameState.DRAW;
    }


}
