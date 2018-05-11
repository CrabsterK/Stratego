package com.si;

public class Board {
    private final int N;
    private int columnControl[];
    private int rowControl[];
    private boolean board[][];

    public Board(int n) {
        this.N = n;
        this.columnControl = new int[n];
        this.rowControl = new int[n];
        this.board = new boolean[n][n];
        initializeControl();
        initializeBoard();
    }

    public boolean[][] getBoard(){
        return board;
    }

    public int getSize(){
        return N;
    }

    private void initializeBoard(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                board[i][j] = false;
            }
        }
    }

    public boolean fieldEmpty(int x, int y){
        return (!board[x][y]);
    }

    private void initializeControl(){
        for (int i = 0; i < N; i++){
            rowControl[i] = 0;
            columnControl[i] = 0;
        }
    }

    public boolean isFull(){
        boolean isFull = true;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(!board[i][j]){
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public int putAndGetNewPoints(int x, int y){//zwraca punkty
        if (board[x][y]){
          return -1;                          ///////////////////////////////Taka obsługa błędu to nienajlepszy pomysł
        }
        board[x][y] = true;
        rowControl[y]++;
        columnControl[x]++;
        return getPoints(x, y);
    }


    public int getPoints(int x, int y) { //to nie działa tak fajnie jeśli tylko chcemy sprawdzić ile byśmy dostali. Można wstawiać gdzie już jest i pokazuje ile za to pkt
        int points = 0;

        if (rowControl[y] == N){ // tylko tutaj dostaje się pkt za narożniki
            points += N;
        }
        if (columnControl[x] == N){ // tylko tutaj dostaje się pkt za narożniki
            points += N;
        }
        points += getDiagonalsPoints(x, y);

        return points;
    }

    private int getDiagonalsPoints(int x, int y) { // tutaj ma nie być pkt za sam narożnik
        int points = 0;
        boolean lowDiagonal = true;
        int lowDiagonalPoinjts = 1;
        for(int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--) { //NW
            lowDiagonalPoinjts++;
            if(!board[i][j]) {
                lowDiagonal = false;
            }
        }
        for(int i = x+1, j = y+1; i < N && j < N; i++, j++) { //SE
            lowDiagonalPoinjts++;
            if(!board[i][j]) {
                lowDiagonal = false;
            }
        }


        boolean higihDiagonal = true;
        int highDiagonalPoinjts = 1;
        for(int i = x+1, j = y-1; i < N && j >= 0; i++, j--) { //SW
            highDiagonalPoinjts++;
            if(!board[i][j]) {
                higihDiagonal = false;
            }
        }
        for(int i = x-1, j = y+1; i >= 0 && j < N; i--, j++) { //NE
            highDiagonalPoinjts++;
            if(!board[i][j]) {
                higihDiagonal = false;
            }
        }


        if(lowDiagonal && lowDiagonalPoinjts > 1){
            points += lowDiagonalPoinjts;
        }
        if(higihDiagonal && highDiagonalPoinjts > 1){
            points += highDiagonalPoinjts;
        }

        return points;
    }




    public void print() {
        String matrix = "";
        for(int i = 0; i< board.length; i++) {
            for(int j = 0; j< board[i].length; j++) {
                if(board[i][j]){
                    matrix += "1  ";
                }
                else {
                    matrix += "0  ";
                }
            }
            matrix += "\n";
        }
        System.out.println(matrix);
    }
}
