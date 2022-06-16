import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class WarEngine {
	
	private Scanner input = new Scanner(System.in);
	private byte kIndex, eIndex;
	CSVGameData csv;
	
	public WarEngine() {
	}
	
	public WarEngine(CSVGameData csv) { 
		this.csv=csv;					//Links with its own object, created in ProgramManager, to save game data.
	}
	
	public void setWarriors() {			//Gets indexes of hitter knight and the hit enemy.
		//A method that runs until an available character number is entered. 
		while(true) {							
			try {
				System.out.print("Knight number: ");
				kIndex = (byte) (input.nextInt()-1);
				input.nextLine();
				System.out.print("Enemy number: ");
				eIndex = (byte) (input.nextInt()-1);
				if(kIndex<0||eIndex<0)
					throw new InputMismatchException();
				input.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a digit from the list!");
				input.nextLine();
			}
		}
	}
	
	public void attack(ArrayList<Knight> activeKnights, ArrayList<Enemy> Enemies) {	
		if(activeKnights.size()>kIndex&&Enemies.size()>eIndex) { 	//Checks whether indexes are in the scope of the character's array.
			activeKnights.get(kIndex).hit(Enemies.get(eIndex));		//Invokes the knight's hit method by giving the selected enemy as a parameter.
			csv.saveGameData(kIndex, eIndex);				//Saves the data to the file just before the defeated character is deleted from its array.
			//Checks if the knight's or enemy's active status is set to false and deletes the unactive one from the array.
			if(activeKnights.get(kIndex).getActive()==false)		
				activeKnights.remove(activeKnights.get(kIndex));
			if(Enemies.get(eIndex).getActive()==false)
				Enemies.remove(Enemies.get(eIndex));
		}
		else
			System.out.println("Please choose a character number that is available in the list.");
	}
}
