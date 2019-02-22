package com.example.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    public TileState[][] board;

    public int moves;
    public Boolean playerOneTurn;
    public Boolean gameOver;

    /* The constructor for the Game class, that initializes the board, player's turn and game mode.*/
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
        moves = 0;

    }

    /* Save (but not really needed check) if the click is on the board. */
    public boolean isBetween(int value, int min, int max)
    {
        return((value > min) && (value < max));
    }

    /* This function will check if the given index is blank. If not so, it will check if the tile is
    occupied and returns invalid is so. Otherwise, circle or cross depending on the player's turn.*/
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

    /* The GameStateHelper function to shorten the code. */
    public GameState GameStateHelper() {
        gameOver = true;
        if (playerOneTurn){
            return GameState.PLAYER_ONE;
        }
        else {
            return GameState.PLAYER_TWO;
        }
    }

    /* The won function to check if the game is over. If so, it will return the corresponding game
     * state and otherwise DRAW. */
    public GameState won(int row, int col, TileState state) {

        boolean win=true;
        for(int i=0; i<BOARD_SIZE; i++){
            if(board[row-1][i]!=state){
                win=false;
                break;
            }
        }
        if (win){ return GameStateHelper(); }

        win=true;
        for(int j=0; j<BOARD_SIZE; j++){
            if(board[j][col-1]!=state){
                win=false;
                break;
            }
        }
        if (win){ return GameStateHelper(); }

        win=true;
        for(int k=0; k<BOARD_SIZE; k++){
            if(board[k][k]!=state){
                win=false;
                break;
            }
        }
        if (win){ return GameStateHelper(); }

        win=true;
        for(int l=0; l<BOARD_SIZE; l++){
            if(board[l][BOARD_SIZE-l-1]!=state){
                win=false;
                break;
            }
        }
        if (win){ return GameStateHelper(); }

        return GameState.DRAW;
    }


}
