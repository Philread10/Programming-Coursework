//This Class is where the bulk of the game logic for Connect 4 is. It includes the method to initiate the game which is called in the main class. Methods include conducting checks during the game for winning/drawing, validating player moves, and initiating the computers move. It is made up of Players and a Board, which are the other two class choices.

//This choice of class felt natural as a place to handle the bulk of the methods and checks that are specific to the game of connect 4, whilst having the object made up of Players (user and computer), and a board.

public class MyConnectFour { 
	
  private Players player; 
  public Board board;
  
	public MyConnectFour(){ 
		board = new Board(); 
    player = new Players();
		playGame();
	}

//This is the method that initiates the game of connect 4 in the main function. It describes the game, allows a player to decide whethey they would like to go first or second, and then enters a loop that continues as long as neither play has won, or the game is drawn. 

	private void playGame(){  

    printInstructions();

    player.decideTurn();

    board.printBoard();

    boolean win = false;
    boolean draw = false;

    //The while loop below has two parts (if and else), and differ only by the order of play. They are sign posted by comments. In the scenario where players go first, the place counter method is called to initiate their move. We then check to see if that move has resulted in a win. If not, the bot move has their turn and the check process repeats. We also check for draws after every other turn (as a draw would end on an even move).

    while(!win && !draw){

      //This scenario occurs if the user decides to go first. 
      if(player.playerOneTurn == 1){
			  board.placeCounter(player.playerOne, checkUserInput());
        board.printBoard();
        if(checkWin(player.playerOne, board) == true){
          win = true;
          System.out.println("you are the winner!");   
        }
        if(win == true){
          //pass
        }
        else{
          if(botmove() == true){
            win = true;
          }
          if(checkDraw(board) == true){
            draw = true;
          }
        }
      }
      else{
      //This scenario occurs if the user decides to go second.
        if(botmove() == true){
          win = true;
        }
        if(win == true){
          //pass   
        }
        else{     
			  board.placeCounter(player.playerOne, checkUserInput());
        board.printBoard();
        if(checkWin(player.playerOne, board) == true){
          win = true;
          System.out.println("you are the winner!");
        }
        if(checkDraw(board) == true){
          draw = true;     
        }
	      }
      }
    }
  System.out.println("\nThank you for playing");
  }

//This method is used for checking whether a player or bot has won. Checks occur horizontally, then vertically, then diagonally up/right, and finally diagonally up/left. Unfortunately the slight variations in checks mean it is difficult to collapse this code.

//The checkWin method accepts the arguments player (ie. 'r' or 'y'), and a Board class, which will be the existing state of the 2D array representing the board state. It returns a boolean, which represents whether win is set to true or false. If the function evaluates to true, the while loop in playgame() will break. 

  private boolean checkWin(char player, Board board){
  int count = 0;
  boolean hasWon = false;
  char[][] boardState = board.getBoardState();

  //This is the horizontal check. It iterates across the 2d array from [0][0], [0][1]... etc. the count variable is used to assess the number of the players colour encountered in a row. count = 0 resets the count when you reach the end of a row, or encounter an array reference that is not the players colour.

  for(int i=0; i<boardState.length; i++){ 
    for(int j=0; j<boardState[i].length; j++){
      if(boardState[i][j] == player){
        count = count + 1;
        if(count >= 4){
          hasWon = true; 
        }
      }
      else{
        count = 0;
      }
    }
    count = 0;
  }

  //This is the vertical check. It iterates across the 2d array from [0][0], [1][0]... etc.
  count = 0;
  for(int i=0; i<boardState[0].length; i++){  
    for(int j=0; j<boardState.length; j++){
      if(boardState[j][i] == player){
        count = count + 1;
        if(count >= 4){
          hasWon = true;    
        }
      }
      else{
        count = 0;
      }
    }
    count = 0;
  }

  //This is the diagonal up and right check. This iterates across the array in the same manner as the horizontal check. If we encounter the players colour, it checks the array reference up and right for the players colour, for as long as we stay within the 2D array reference parameters. 

  count = 0;
  int k = 0;

  for(int i=0; i<boardState.length; i++){ 
    for(int j=0; j<boardState[i].length; j++){
      if(boardState[i][j] == player){
        for(k=0; ((i-k<=5 && i-k>=0) && (j+k>=0 && j+k<=6)); k++ ){
          if(boardState[i-k][j+k] == player){
            count = count+1;
            if(count >= 4){
              hasWon = true; 
            }            
          }               
          else{
            count = 0;
          }
        }
        count = 0;
      }
    }
  }

  //As above, except checking diagonal up and to the left.
  count = 0;
  k = 0;

  for(int i=0; i<boardState.length; i++){ 
    for(int j=0; j<boardState[i].length; j++){
      if(boardState[i][j] == player){ 
        for(k=0; ((i-k<=5 && i-k>=0) && (j-k>=0 && j-k<=6)); k++ ){
          if(boardState[i-k][j-k] == player){
            count = count+1;
            if(count >= 4){
              hasWon = true; 
            }            
          }               
          else{
            count = 0;
          }
        }
        count = 0;
      }
    }
  } 
  return hasWon;
}
      
//Method for printing instructions at start of game
  private void printInstructions(){
		System.out.println("\nWelcome to Connect 4!"); 
		System.out.println("\nYou will be playing as Red, and the computer will be playing as Yellow");
		System.out.println("\nTo play the game, type in the number of the column you want to drop you counter in"); //ERROR FIXED ;
		System.out.println("\nA player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
		System.out.println("Have fun!"); 
    System.out.println("");
  }

//This method takes the user input as a string and tries to convert it to an integer. It performs checks to see if a column is full (if input is between 1 and 7), or repeats itself if the move is outside of the board range / is an input that cannot be converted to an integer. It accepts no arguments, but returns an integer value. This will represent the players move once a correct input is given.

  private int checkUserInput(){
    char[][] boardState = board.getBoardState();
    String userInput = player.getUserInput();
    int move1;
    try {
    move1 = Integer.parseInt(userInput);
    if(move1 >= 1 && move1 <= 7){
      if(boardState[0][move1-1] == 'r' || boardState[0][move1-1] == 'y'){
        System.out.println("This column is full! Please try a different column");       
        return checkUserInput();
      }
      else{
        return move1;
      }
    }
    else {
      System.out.println("I didn't recognise that input! Please enter a number between 1 and 7");
      return checkUserInput();         
    }
    }
    catch (Exception e1){
    System.out.println("I didn't recognise that input! Please enter a number between 1 and 7");
    return checkUserInput();
    }
  }

//This method implements the bots move using place counter, and performs the check win method above. 

  private boolean botmove(){
    board.placeCounter(player.computerPlayer, botInput());
    board.printBoard();
    if(checkWin(player.computerPlayer, board) == true){
      System.out.println("The computer is the winner!");
      return true;
    }
    else{
      return false;
    }
  }

//This method checks for draws. It iterares along the 2D array in a similar fashion to the horizontal check. It is checking to see if every reference is full of a player colour. If it evaluates to true, the while loop in playgame() will break.

  private boolean checkDraw(Board board){
    int count = 0;
    boolean hasDrawn = false;
    char[][] boardState = board.getBoardState();
    for(int j=0; j<boardState[0].length; j++){
      if(boardState[0][j] == 'y' || boardState[0][j] == 'r' ){
        count = count + 1;
        if(count >= 7){
          hasDrawn = true; 
          System.out.println("It's a draw!");
        }
      }
      else{
        count = 0;
      }
    }
    count = 0;
    return hasDrawn;
  }

//This method computes the bots input randomly, and is used as an argument for the botmove() function above. It performs a validation of whether a column is full, similar to checkUserInput() above. If it is, it will return itself and try again. Otherwise, it will return an integer from 1 to 7.

  public int botInput(){
    char[][] boardState = board.getBoardState();
    int min = 1;
    int max = 7;
    int computerInput = (int)Math.floor(Math.random()*(max-min+1)+min);
    if(boardState[0][computerInput-1] == 'r' || boardState[0][computerInput-1] == 'y'){
       return botInput();
    }
    else {
      return computerInput;
    }
  }  
}
