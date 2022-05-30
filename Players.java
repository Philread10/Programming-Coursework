import java.io.InputStreamReader;
import java.io.BufferedReader;

//This class handles creating both the player and the computer, as well as owning methods for getting the deciding turn order and getting the users initial input.

//This made sense as a class, as other two player games would also need a user and computer to play against, and would also need to receieve the player input/decide turns. Methods relating to bot inputs, bot moves and checks are under the MyConnectFour class, as those checks are specific to the game and would not necessarily be useful across games. I considered a Token class, however I believe it is adequately handled by the player as it would only constitute a single char variable. 

public class Players {
  
  private BufferedReader input; 
  public Character playerOne;
  public Character computerPlayer;
  public Integer playerOneTurn;
  public Integer computerTurn;  

  public Players(){
    playerOne = 'r';
    computerPlayer = 'y';
    input = new BufferedReader(new InputStreamReader(System.in));
  }

//This function determines whether the user or the computer goes first. It checks that the users input is either one or two, otherwise it returns itself and asks again. 

  public void decideTurn(){
    System.out.println("\nWould you like to go first or second? please enter 1 or 2 :   ");
    String userInput = getUserInput();
    try{
    playerOneTurn = Integer.parseInt(userInput);
    }
    catch(Exception e){
      System.out.println("Sorry we didn't recognise that. Please select 1 or 2");
      decideTurn();
    }
    if(playerOneTurn == 1){
      computerTurn = 2;
    } 
    else if(playerOneTurn == 2){
      computerTurn = 1;
    }
    else{
      System.out.println("Sorry we didn't recognise that. Please select 1 or 2");
      decideTurn();
    } 
  }

//This method is responsible for receiving the users input. It is used as an argument in other functions to check that the input is applicable in the game of connect four.
  
  public String getUserInput(){ 
		String toReturn = null;   
		try{			
			toReturn = input.readLine(); 
		}
		catch(Exception e){  
      System.out.println("I didn't recognise that input!");
      getUserInput();
		}
		return toReturn;     
	}
}
