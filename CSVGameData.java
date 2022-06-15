import java.io.FileWriter;
import java.io.PrintWriter;

//Class that saves the data to a txt file in CSV format.
public class CSVGameData extends GameData {
	GameData gd;
	
	public CSVGameData() {
	}
	
	public CSVGameData(GameData gd) {
		this.gd=gd;
	}
	
	public void saveCharacterData() {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter("CSV_Data.txt"));	//When run in the IDE, it saves in the directory of the Java project as 'CSV_Data.txt'.			
			writer.println("Character data:");
			writer.println("name, characterType, id, hitCount");
			for(Knight s : gd.getActiveKnights()) {
				writer.printf("%s, %s, %d, %d\n",s.getName(),s.getType(),s.getId(),s.getHitCount());
			}
			for(Enemy d : gd.getEnemies()) {
				writer.printf("%s, %s, %d\n",d.getName(),d.getType(),d.getId());
			}
			writer.println("\nGame data:");
			writer.println("hitter, hit");
			writer.close();
		}	catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void saveGameData(byte s, byte d) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter("CSV_Data.txt", true));	//When run in the IDE, it appends to the directory of the Java project as 'CSV_Data.txt'.
			writer.printf("%s, %s\n",gd.getActiveKnights().get(s).getName(),gd.getEnemies().get(d).getName());
			writer.close();
		}	catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void winner(byte a) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter("CSV_Data.txt", true));
			writer.print("\nResult: ");
			if(a==1)
				writer.print("The player won the game.");
			else
				writer.print("The player lost.");
			writer.close();
		}	catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
