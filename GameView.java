import java.util.ArrayList;

public class GameView {
	
	public GameView(){
		
	}
	
	public void startScreen() {
		System.out.print("****************************\n"
				+ "Welcome to the game.\n"
				+ "****************************");
		showMainMenu();
	}
	
	public void showMainMenu() {
		System.out.println("\nMAIN MENU\n"
				+ "1. Show knights\n"
				+ "2. Show active knights\n"
				+ "3. Show knight info\n"
				+ "4. Change knights\n"  
				+ "5. Help\n"
				+ "6. Start the game\n"
				+ "7. Exit");
	}
	
	public void makeSelectionFromMenu() {
		System.out.print("\nPlease make a selection: ");
	}
	
	public void help() {
		System.out.println("\nAfter starting the game, your knights and their information and your enemies will be displayed.\n"
				+ "You will then choose your knight to attack."
				+ "\nThe status will be displayed in each round and at the end the result will be displayed.\n"
				+ "Break a leg!\n");
		
	}
	
	public void showKnights(ArrayList<Knight> knights) {
		for(int i = 0; i < knights.size(); i++) {
			System.out.println((i+1)+knights.get(i).toString());
		}
	}
	
	public void showActiveKnights(ArrayList<Knight> activeKnights) {
		for(int i = 0; i < activeKnights.size(); i++) {
			System.out.println((i+1)+activeKnights.get(i).toString());
		}
	}
	
	public void showKnight(Knight s) {
			System.out.println(s.toString());
	}
	
	public void showGameState(ArrayList<Enemy> enemies, ArrayList<Knight> activeKnights) {
		System.out.println("\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n"
				+ "Knights and enemies:\n"
				+ "____________________\n");
		showActiveKnights(activeKnights);
		for(int i = 0; i < enemies.size(); i++) {
			System.out.println((i+1)+")-------------------------\n"
					+ "Enemy: " + enemies.get(i).getName() 
					+ "\n---------------------------\n");
		}
	}
}