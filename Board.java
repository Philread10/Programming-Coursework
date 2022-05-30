//This is the Board Class. It is made up of a 2D array that represents the board, and also includes the placeCounter method, and a method for priting the board to the console. 

//This was an obvious choice to have as a separate class, as multiple games could potentially draw on a board that was made up of a 2D array. The methods also make sense, as counters could potentially vary across games but could also need to be placed on the board. The same goes for the print method.

public class Board {

	private char[][] boardState;
  
  public Board(){
    boardState = new char[6][7];
  }

  public char[][] getBoardState(){
    return boardState;
  }

//This method takes in a players colour (y or r), and an integer representing the column that they want to place their counter in. It checks to see if there is counters occupying that column, and places it on the first unoccupied space in the array (from the bottom up). It has two scenarios, one for r and one for y.

	public void placeCounter(char player, int position){
		boolean placed = false;
		if(player == 'r'){
			for(int i=boardState.length-1; i>=0; i--){
				if(!placed){
					if(boardState[i][position-1] == 'y'){
					}
					else if(boardState[i][position-1] != 'r'){
						boardState[i][position-1] = 'r';
						placed = true;
					}
				}
			}
		}
		else{
			for(int i=boardState.length-1; i>=0; i--){
				if(!placed){
					if(boardState[i][position-1] == 'r'){
						// skip
					}
					else if(boardState[i][position-1] != 'y'){
						boardState[i][position-1] = 'y';
						placed = true;
					} 
				}
			}
		}
	}

//This method prints the board to the screen, and is typically used at the start and after each move to show the user the existing state of the game. It iterates along similar to the checkWin() horizontal scenario, and prints depending on what the array reference is occupied by.

	public void printBoard(){ 
    System.out.print("\n");
		for(int i=0; i<boardState.length; i++){
			for(int j=0; j<boardState[i].length; j++){ 
				if(boardState[i][j] == 'r'){ 
					System.out.print("| r ");
				}
				else if(boardState[i][j] == 'y'){ 
					System.out.print("| y ");
				}
				else{
					System.out.print("|   ");
				}
			}
			System.out.println("|");
		}
		System.out.println("  1   2   3   4   5   6   7");
	} 
}
