import java.util.*;
import java.util.concurrent.TimeUnit;
public class ProgramManager {
	//Generating objects from defined classes.
	private static Scanner sc = new Scanner(System.in);
	private static GameData gd = new GameData();
	private static GameView gv = new GameView();
	private static CSVGameData csv = new CSVGameData(gd);
	private static WarEngine we = new WarEngine(csv);
	static byte chose=-1, check = 1, won=0;
	
	//Main method.
	public static void main(String[] args) {
		//Prints start screen and starts the game.
		gv.startScreen();
		fillCharacters();
		gv.makeSelectionFromMenu();
		activateMenu();
		csv.winner(won);
	}
	
	public static void fillCharacters() {	//Fills Enemies, activeKnights and Knights arrays in GameData object.
		gd.addKnight("K1", 1, 0);
		gd.addKnight("K2", (int)(Math.random()*2+1), 1);
		gd.addEnemy("E1", 1, 0);
		gd.addEnemy("E2", (int)(Math.random()*2+1), 1);
		for(int i = 2; i < 5; i++) {
			gd.addKnight("K"+(i+1), 2, i);
			gd.addEnemy("E"+(i+1), 2, i);
		}
		Collections.shuffle(gd.getActiveKnights());		//Only K1, K2, E1 and E2 can be strong. Therefore, the arrays are shuffled to make the game fair and more exciting. 
		Collections.shuffle(gd.getEnemies());
	}
	
	public static void activateMenu() {		//Activates menu and therefore starts the game.
		byte c = 0;
		while(check!=0) {							//Runs in an endless loop until the game is over or option 7 is selected from the menu.
			while(true) {							//A method that runs until a digit is entered. 
				try {
					chose = sc.nextByte();
					sc.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.print("Please enter a digit! ");
					sc.nextLine();
				}
			}
			
			switch (chose) {		//A switch-case structure that performs the operations of the menu selection.
				case 0:
					gv.showMainMenu();			//Related method.
					break;
				case 1:
					gv.showKnights(gd.getKnights());		//Related method.
					break;
				case 2:
					gv.showActiveKnights(gd.getActiveKnights());		//Related method.
					break;
				case 3:
					System.out.print("Knight to be shown (K1/...): ");
					try {
						gv.showKnight(gd.getKnight(sc.nextLine()));		//Prints the corresponding knight.
					} catch (Exception e) {
						System.out.println("Knight not found!!!\n");	//If not found
					}
					break;
				case 4:
					System.out.println("Knight to be changed (K2/...): ");
					String a = sc.nextLine();
					try{
						Knight temp = gd.getKnight(a);
						if(temp.getId()==1&&!temp.getName().equals("K1")) {		//A strong knight must be selected. K1 can never be changed as it must be at least 1 strong knight in the game.
							gd.getActiveKnights().remove(temp);						//Deletes the strong knight.
							gd.getActiveKnights().add(new Knight("NewKnight 1",2));		//Adds new weak knights to activeKnights array.
							gd.getActiveKnights().add(new Knight("NewKnight 2",2));
						}
						else if(temp.getName().equals("K1")) {					//If K1 is selected
							System.out.println("You cannot change K1. Try another one.");
							break;
						}
						else {
							System.out.println("Weak knights cannot be changed!");		//If weak knight is selected
							break;
						}
					} catch (Exception e) {
						System.out.println("Something went wrong. The knights were not changed.");	//If a typo or other error occurs
						break;
					}
					System.out.println("The knights have been successfully changed!");		//Reporting of successfully replaced knights.
					break;
				case 5:
					gv.help();		//Help menu.
					break;
				case 6:
					if(c==0) {
						csv.saveCharacterData();			//One-time saving of generated characters to the file.
						c++; 
					}
					while(true) {				//Iterates until the game is over.
						gv.showGameState(gd.getEnemies(),gd.getActiveKnights());
						we.setWarriors();														//Getting the indexes of hitter knight and the hit enemy.
						we.attack(gd.getActiveKnights(), gd.getEnemies());						//Related method.
						if(gd.getActiveKnights().size()==0&&gd.getEnemies().size()!=0) {		//If the activeKnights array is empty and the Enemies array is not, it means that the player has lost the game.
							System.out.println("You have no knights left. Game over.\nYOU LOST!");
							break;
						}
						else if (gd.getActiveKnights().size()!=0&&gd.getEnemies().size()==0) {	//If both arrays are empty, the last knight has defeated the last enemy.
							//If the knight defeats the enemy on the last hit, the enemy will be deleted from its array, and the knight will be deleted from its own as its right to strike will be over.
							System.out.println("YOU WON!!!");			
							won++;					//A control variable to save the winner on the file.
							break;
						}
						else if(gd.getActiveKnights().size()==0&&gd.getEnemies().size()==0){	//It is clear that the player has won.
							System.out.println("YOU WON!!!");
							won++;						//Same control variable. If it's not increased the enemy has won.							
							break;	
						}
						System.out.println("Game continues...");
						try {			//Waiting 2 seconds in the console to see whether the hit was successful or not.
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					check--;				//When the game is over, the control variable is decremented so that the infinite loop could be broken.
					break;
				case 7:
					System.out.println("Exiting the game...");
					check--;				//If 7 is selected from the menu, the value of the control variable is decreased so that the program ends.
					break;
			}
			if(check!=0&&chose!=0)		//Different output after anything is selected from the menu.
				System.out.print("Please make a selection (Press 0 to see the main menu): ");		//The last selection should not be 0. The key should not be 0, if so, the game is over.
			else if(check!=0)
				gv.makeSelectionFromMenu();		//The relevant method is called before proceeding to the next iteration, if the game is not started yet and is not finished. 
		}
	}
}