/**
* The Othello class.
*
* This class represents a Othello (TM)
* game, which allows two players to place
* pieces onto a board.  Each move can 
* result in outflanking 0 or more opponent's 
* piece.
*/

    public class Othello {
   
      final int EMPTY = -1;
      final int NUMPLAYER;
      final int NUMROW;
      final int NUMCOL;
      final int MAXGAME;
      final int PLAYER1 = 0;
      final int PLAYER2 = 1;
      final int TIE = -1;
   
      OthelloGUI gui;
      int numGame = 0;
      int curPlayer;
      int board[][];
      int score[];
      int flank = 0;
      int player1Win;
      int player2Win;
      int winner;
   
   
      boolean checkFull = true;
   
   /**
   * Constructor:  Othello
   */
       public Othello(OthelloGUI gui) {
         this.gui = gui;
         NUMPLAYER = gui.NUMPLAYER;
         NUMROW = gui.NUMROW;
         NUMCOL = gui.NUMCOL;
         MAXGAME = gui.MAXGAME;
      
      // TO DO:  creation of arrays, and initialization of variables should be added here
      
      //Declaring and creating new board and score arrays
         board = new int[NUMROW][NUMCOL];
         score = new int[NUMPLAYER];
      
      //Calling the initBoard method
         initBoard();
      
      }
   
   //Creating initBoard method to initialize the game board to initial set-up
       private void initBoard(){
      
      //Resetting the gameboard
         gui.resetGameBoard();
      
      //Setting all the spaces to empty
         for(int i = 0; i < NUMROW; i ++){
            for(int k = 0; k < NUMCOL; k ++){
            
               board[i][k] = EMPTY;
            }
         }
      
      //Adding the starting pieces
         board[3][3] = PLAYER2;
         gui.setPiece(3,3,PLAYER2);
         board[3][4] = PLAYER1;
         gui.setPiece(3,4,PLAYER1);
         board[4][3] = PLAYER1;
         gui.setPiece(4,3,PLAYER1);
         board[4][4] = PLAYER2;
         gui.setPiece(4,4,PLAYER2);
      
      //Updating the score of the players
         score[PLAYER1] = 2;
         score[PLAYER2] = 2;
         gui.setPlayerScore(PLAYER1,score[PLAYER1]);
         gui.setPlayerScore(PLAYER2,score[PLAYER2]);
      
      //Initilizing the current player
      //And telling the user who the next player is
         curPlayer = PLAYER1;
         gui.setNextPlayer(PLAYER1);
      
      }
   
   //Creating validMove method to check if the space clicked by the user is a valid move
       public boolean validMove(int row, int col){
      
      //Variable declaration
         final int CORNERFALSE = -3;
         final int EDGESFALSE = -5;
         final int MIDFALSE = -8;
         int sumCheck;
      
      //Checking Left corner
         if (row == 0 && col ==0){
         
         //Calculating the sum of the 3 squares around the left corner
            sumCheck = (board[row][1])+(board[1][col])+(board[1][1]);
         
         //Checking if the the sum of the 3 spaces around the corner is -3. If it is, it means that they're all empty which indicates that it's an invalid move
         //If its not, it's a valid move
            if (sumCheck != CORNERFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else {
            
               return(false);
            
            }
         
         }
         //Checking the right corner
         else if(row == 0 && col == (NUMCOL - 1)){
         
         //Calculating the sum of the 3 squares around the right corner
            sumCheck = (board[row][col-1])+(board[row+1][col-1])+(board[row+1][col]);
         
         //Checking if the 3 spaces around the square is -3
         //If it is, it is an invalid move, otherwise it's a valid move
            if (sumCheck != CORNERFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            
            }
         
         
         }
         //Checking the bottom left corner
         else if (row == (NUMROW - 1) && col == 0){
         
         // Calculating the sum of the 3 squares around the bottom left corner
            sumCheck = (board[row-1][col])+(board[row-1][1])+(board[row][1]);
         
         //Checking if the 3 spaces around the square is -3
         //If it is, it's an invalid move, otherwise it's a valid move
            if (sumCheck != CORNERFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            }
         
         }
         //Checking the bottom right corner
         else if (row == (NUMROW - 1) && col == (NUMCOL - 1)){
         
         //Calculating the sum of the 3 squares around the bottom right corner
            sumCheck = (board[row - 1][col - 1])+(board[row - 1][col])+(board[row][col - 1]);
         
         //Checking if the 3 spaces around the square is -3
         //If it is, it's an invalid move, otherwise it's a valid move
            if (sumCheck != CORNERFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            
            }
         
         }
         //Checking first row excluding the top two corners
         else if (row == 0 && col != 0 && col != (NUMCOL - 1)){
         
         //Calculating the 5 squares around the square on the edges
            sumCheck = (board[row][col-1])+(board[row][col+1])+(board[1][col-1])+(board[1][col])+(board[1][col+1]);
         
         //Checking if the 5 spaces around the square is -5
         //If it is, it's an invalid move, otherwise it's a valid move
            if (sumCheck != EDGESFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            
            }
         
         }
         //Check the last row excluding the bottom two corners
         else if (row == (NUMROW - 1) && col != 0 && col!= (NUMCOL - 1)){
         
         //Calculating the 5 squares around the square on the edges
            sumCheck = (board[row - 1][col - 1])+(board[row - 1][col])+(board[row - 1][col + 1])+(board[row][col - 1])+(board[row][col + 1]);
         
         //Checking if the 5 spaces around the square is -5
         //If it is, it's an invalid move, otherwise it's a valid move
            if (sumCheck != EDGESFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            
            }
         
         }
         //Checking the first column excluding the top and bottom corners
         else if (row != 0 && row!= (NUMROW - 1) && col == 0){
         
         //Calculating the 5 squares around the square on the edges
            sumCheck = (board[row-1][col+1])+(board[row][col+1])+(board[row+1][col+1])+(board[row+1][col])+(board[row-1][col]);
         
         //Checking if the 5 spaces around the square is -5
         //If it is, it's an invalid move, otherwise it's a valid move
            if (sumCheck != EDGESFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            
            }
         
         }
         //Checking the last column excluding the top and bottom corners
         else if (row !=0 && row!= (NUMROW - 1) && col == (NUMCOL - 1)){
         
         //Calculating the 5 squares around the square on the edges
            sumCheck = (board[row-1][col-1])+(board[row][col-1])+(board[row+1][col-1])+(board[row+1][col])+(board[row-1][col]);
         
         //Checking if the 5 spaces around the square is -5
         //If it is, it's an invalid move, otherwise it's a valid move
            if (sumCheck != EDGESFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else{
            
               return(false);
            
            }
         
         }
         //The spaces left in the middle (so excluding the first and last row and first and last column)
         else{
         
         //Calculating the 8 squares around the squares in the middle
            sumCheck = (board[row-1][col-1])+(board[row-1][col])+(board[row-1][col+1])+(board[row][col-1])+(board[row][col+1])+(board[row+1][col-1])+(board[row+1][col])+(board[row+1][col+1]);
         
         //Checking if the 8 spaces around the squares in the middle is -8
         //If it is, it's an invalid move, otherwise it's a valid move
            if(sumCheck != MIDFALSE && board[row][col] == EMPTY){
            
               board[row][col] = curPlayer;
               return(true);
            
            }
            else {
            
               return(false);   
            }
         
         }
      
      }
   
   //Creating outFlankHori method to check if the move by the player result in any outflank horizontally
       public int outFlankHori (int row, int col){
      
      //Variable declaration
         int flankLeft = 0;
         int flankRight = 0;
         int right = 0;
         int rightcheck = 0;
         int left = 0;
         int leftcheck = 0;
         int playerOpp;
         boolean check = false;
      
         int column;
      
      //Determining who the current player is and who the next player is
         if (curPlayer == PLAYER1){
         
            playerOpp = PLAYER2;
         
         }
         else{
         
            playerOpp = PLAYER1;
         
         }
      //Flanking towards the right
         column = col - 1;
         for (int i = column; i >= 0 && check == false; i--){
         
            flankRight++;
            if(board[row][i] == curPlayer){
            
               check = true;
            
            }
         
         }
      
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            flankRight = flankRight - 1;
         
            for(int i = column; i >= col - flankRight; i--){
            
               right = right + board[row][i];
               rightcheck = rightcheck + playerOpp;
            
            }
         
         //Flipping the pieces if there're no empty spaces inbetween
            if (right == rightcheck){
            
               for(int i = column; i >= col - flankRight; i--){
               
                  board[row][i] = curPlayer;
                  gui.setPiece(row,i,curPlayer);
               
               }
            
            }
            else{
            
               flankRight = 0;
            
            }
         
         //If it didn't encounter the same piece that you clicked down
         }
         else{
         
            flankRight = 0;
         
         }
      
      //Flanking towards the left
         column = col + 1;
         check = false;
         for (int i = column; i < NUMCOL && check == false; i++){
         
            flankLeft++;
         
            if (board[row][i] == curPlayer){
            
               check = true;
            
            }
         
         }
      
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            flankLeft = flankLeft - 1;
         
            for (int i = column; i <= (col + flankLeft); i ++){
            
               left = left + board[row][i];
               leftcheck = leftcheck + playerOpp;
            
            }
         
         //Flipping the pieces if there're no empty spaces inbetween
            if (left == leftcheck){
            
               for(int i = column; i <= (col + flankLeft); i++){
               
                  board[row][i] = curPlayer;
                  gui.setPiece(row,i,curPlayer);
               
               }  
            
            }
            else {
            
               flankLeft = 0;
            
            }
         
         
         }
         //If it didn't encounter the same piece that you clicked down
         else{
         
            flankLeft = 0;
         
         }
      
      //Determining how many pieces it outflanked
         return (flankRight + flankLeft);
      
      }
   
   //Creating outFlankVert method to check if the move by the player result in any outflank vertically
       public int outFlankVert (int row, int col){
      
      //Variable declaration
         int flankDown = 0;
         int flankUp = 0;
         int up = 0;
         int upcheck = 0;
         int down = 0;
         int downcheck = 0;
         int playerOpp;
         boolean check = false;
         int vert;
      
      //Determining who the current player is and who the next player is
         if (curPlayer == PLAYER1){
         
            playerOpp = PLAYER2;
         
         }
         else{
         
            playerOpp = PLAYER1;
         
         }
      //Flanking Downwards
         vert = row - 1;
         for (int i = vert; i >= 0 && check == false; i--){
         
            flankDown++;
            if(board[i][col] == curPlayer){
            
               check = true;
            
            }
         
         }
      
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            flankDown = flankDown - 1;
         
            for(int i = vert; i >= row - flankDown; i--){
            
               down = down + board[i][col];
               downcheck = downcheck + playerOpp;
            
            }
         
         //Flipping the pieces if there're no empty spaces inbetween
            if (down == downcheck){
            
               for(int i = vert; i >= row - flankDown; i--){
               
                  board[i][col] = curPlayer;
                  gui.setPiece(i,col,curPlayer);
               
               }
            
            }
            else{
            
               flankDown = 0;
            
            }
         
         //If it didn't encounter the same piece that you clicked down
         }
         else{
         
            flankDown = 0;
         
         }
      
      //Flanking Upwards
         vert = row + 1;
         check = false;
         for (int i = vert; i < NUMROW && check == false; i++){
         
            flankUp++;
         
            if (board[i][col] == curPlayer){
            
               check = true;
            
            }
         
         }
      
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            flankUp = flankUp - 1;
         
            for (int i = vert; i <= (row + flankUp); i ++){
            
               up = up + board[i][col];
               upcheck = upcheck + playerOpp;
            
            }
         
         //Flipping the pieces if there're no empty spaces inbetween
            if (up == upcheck){
            
               for(int i = vert; i <= (row + flankUp); i++){
               
                  board[i][col] = curPlayer;
                  gui.setPiece(i,col,curPlayer);
               
               }  
            
            }
            else{
            
               flankUp = 0;
            
            }
         
         
         }
         //If it didn't encounter the same piece that you clicked down
         else{
         
            flankUp = 0;
         
         }
      
      //Determining how many pieces it outflanked
         return flankUp + flankDown;
      
      
      }
   
   //Creating outFlankDiag method to check if the move by the player result in any outflank diagonally
       public int outFlankDiag(int row, int col){
      
      //Variable declaration
         int flankNW = 0;
         int northwest = 0;
         int checkNW = 0;
      
         int flankNE = 0;
         int northeast = 0;
         int checkNE = 0;
      
         int flankSW = 0;
         int southwest = 0;
         int checkSW = 0;
      
         int flankSE = 0;
         int southeast = 0;
         int checkSE = 0; 	
      
         int playerOpp;
         int diagRow;
         int diagCol;
         boolean check = false;
         boolean checkRow = false;
      
      //Determining who the current player is and who the next player is
         if (curPlayer == PLAYER1){
         
            playerOpp = PLAYER2;
         
         }
         else{
         
            playerOpp = PLAYER1;
         
         }
      
      //Flanking towards the northwest
         diagRow = row + 1;
         diagCol = col + 1;
      
         for (int i = diagCol; i < NUMCOL && check == false && checkRow == false; i++){
         
            if(diagRow < NUMROW){
            
               if (board[diagRow][i] == curPlayer){
               
                  check = true;
               
               }
               else {
               
                  diagRow++;
                  flankNW++;  
               }
            
            }
            else{
               checkRow = true;
            }
         
         }
      
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            diagRow = row;
         
            for (int i = diagCol; i <= (col + flankNW); i ++){
            
               diagRow++;
            
               northwest = northwest + playerOpp;
               checkNW = checkNW + board[diagRow][i]; 
            
            }	
         
            if(northwest == checkNW){
            
               diagRow = row;
            
               for (int i = diagCol; i <= (col + flankNW); i++){
               
                  diagRow++;
               
                  board[diagRow][i] = curPlayer;
                  gui.setPiece(diagRow,i,curPlayer);
               
               }
            
            }
            else{
            
               flankNW = 0;
            
            }
         
         }
         //If it didn't encounter the same piece you clicked down  
         else{
         
            flankNW = 0;
         
         }	
      
      //Flank towards southeast
         diagRow = row - 1;
         diagCol = col - 1;
         check = false;
         checkRow = false;
      
         for (int i = diagCol; i >= 0 && check == false && checkRow == false; i--){
         
            if(diagRow >= 0){
            
               if (board[diagRow][i] == curPlayer){
               
                  check = true;
               
               }
               else {
               
                  diagRow--;
                  flankSE++;  
               }
            
            }
            else{
               checkRow = true;
            }
         }
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            diagRow = row;
         
            for (int i = diagCol; i >= (col - flankSE); i --){
            
               diagRow--;
            
               southeast = southeast + playerOpp;
               checkSE = checkSE + board[diagRow][i]; 
            
            }	
         
            if(southeast == checkSE){
            
               diagRow = row;
            
               for (int i = diagCol; i >= (col - flankSE); i--){
               
                  diagRow--;
               
                  board[diagRow][i] = curPlayer;
                  gui.setPiece(diagRow,i,curPlayer);
               
               }
            
            }
            else {
            
               flankSE = 0;
            
            }
         
         }
         //If it didn't encounter the same piece you clicked down  
         else{
         
            flankSE = 0;
         
         }
      
      //Flanking towards the southwest
         diagRow = row - 1;
         diagCol = col + 1;
         check = false;
         checkRow = false;
      
         for (int i = diagCol; i < NUMCOL && check == false && checkRow == false; i++){
         
            if(diagRow >= 0){
            
               if (board[diagRow][i] == curPlayer){
               
                  check = true;
               
               }
               else {
               
                  diagRow--;
                  flankSW++;  
               }
            
            }
            else{
               checkRow = true;
            }
         
         }
      
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            diagRow = row;
         
            for (int i = diagCol; i <= (col + flankSW); i ++){
            
               diagRow--;
            
               southwest = southwest + playerOpp;
               checkSW = checkSW + board[diagRow][i]; 
            
            }	
         
            if(southwest == checkSW){
            
               diagRow = row;
            
               for (int i = diagCol; i <= (col + flankSW); i++){
               
                  diagRow--;
               
                  board[diagRow][i] = curPlayer;
                  gui.setPiece(diagRow,i,curPlayer);
               
               }
            
            }
            else {
            
               flankSW = 0;
            
            }
         
         }
         //If it didn't encounter the same piece you clicked down  
         else{
         
            flankSW = 0;
         
         }	
      
      //Flank towards northeast
         diagRow = row + 1;
         diagCol = col - 1;
         check = false;
         checkRow = false;
      
         for (int i = diagCol; i >= 0 && check == false && checkRow == false; i--){
         
            if(diagRow < NUMROW){
            
               if (board[diagRow][i] == curPlayer){
               
                  check = true;
               
               }
               else {
               
                  diagRow++;
                  flankNE++;  
               }
            
            }
            else{
               checkRow = true;
            }
         }
      //If it encountered the same piece that you clicked down
         if (check == true){
         
            diagRow = row;
         
            for (int i = diagCol; i >= (col - flankNE); i --){
            
               diagRow++;
            
               northeast = northeast + playerOpp;
               checkNE = checkNE + board[diagRow][i]; 
            
            }	
         
            if(northeast == checkNE){
            
               diagRow = row;
            
               for (int i = diagCol; i >= (col - flankNE); i--){
               
                  diagRow++;
               
                  board[diagRow][i] = curPlayer;
                  gui.setPiece(diagRow,i,curPlayer);
               
               }
            
            }
            else {
            
               flankNE = 0;
            
            }
         
         }
         //If it didn't encounter the same piece you clicked down  
         else{
         
            flankNE = 0;
         
         }
      
      //Determining how many pieces it outflanked
         return flankNE + flankNW + flankSW + flankSE;
      
      }
   
   //Creating checkWinner method to check who the winner is
       public int checkWinner (){
      
      //Determining who the winner is and whether the game is tie
      
         if(score[PLAYER1] > score[PLAYER2]){
         
            gui.showWinnerMessage(PLAYER1);
            return PLAYER1;
         
         }
         else if (score[PLAYER2] > score[PLAYER1]){
         
            gui.showWinnerMessage(PLAYER2);
            return PLAYER2;
         
         }
         else {
         
            gui.showTieGameMessage();
            return TIE;
         
         }
      
      
      } 
   
   
   
   /**
   * play
   * This method will be called when a square is clicked.  Parameter "row" and "column" is 
   * the location of the square that is clicked by the user
   */
   
       public void play (int row, int column) {
      // TO DO:  implement the logic of the game
         flank = 0;
      
      //If its a valid move
         if (validMove(row,column) == true){
         
         //Adding the current score of the player by 1 and placing the piece down
            score[curPlayer]++;
            gui.setPiece(row,column,curPlayer);
         
         //Checking for any flanks horizontally, vertically or diagonally
         //And calculating the pieces flanked
            flank = outFlankHori(row,column) + outFlankVert(row,column) + outFlankDiag(row,column);
         
         //Updating the score board
            gui.setPlayerScore(PLAYER1,score[PLAYER1]);
            gui.setPlayerScore(PLAYER2,score[PLAYER2]);
         
         //Runs if the move flanks a piece
            if (flank != 0){
            
            //Determining who the current player is and who the opposite player is
            //Then updating the score of the players
               if (curPlayer == PLAYER1){
               
                  score[PLAYER1] = score[PLAYER1] + flank;
                  score[PLAYER2] = score[PLAYER2] - flank;
               }
               else {
               
                  score[PLAYER2] = score[PLAYER2] + flank;
                  score[PLAYER1] = score[PLAYER1] - flank;
               
               }
            
            //Updating the score board
               gui.setPlayerScore(PLAYER1,score[PLAYER1]);
               gui.setPlayerScore(PLAYER2,score[PLAYER2]);
            
            //Telling the user the amount it flanked
               gui.showOutflankMessage(curPlayer,flank);
            
            }
            
         	//Switching turns between players
            if (curPlayer == PLAYER1){
            
               curPlayer = PLAYER2;
               gui.setNextPlayer(PLAYER2);
            
            }
            else {
            
               curPlayer = PLAYER1;
               gui.setNextPlayer(PLAYER1);
            
            }
         
               //Checking when the board is filled
            if ((score[PLAYER1] + score[PLAYER2]) == (NUMCOL * NUMROW)){
            
               winner = checkWinner();
            
            //Checking who won the game
               if(winner == PLAYER1){		
               
                  numGame++;
                  player1Win++; 
               
               }
               else if (winner == PLAYER2){
               
                  numGame++;
                  player2Win++;
               
               }
            
            //Starting with the initial board
               initBoard();
            
            }
         
         //Checking who the winner of the match is
            if (player1Win == MAXGAME){
            
               gui.showFinalWinnerMessage(PLAYER1);
               
               }else if (player2Win == MAXGAME){
               gui.showFinalWinnerMessage(PLAYER2);
               
            }
         }
         else {
         //Showing invalid message if the move is invalid
            gui.showInvalidMoveMessage();
         
         }
      
      } 
   }
