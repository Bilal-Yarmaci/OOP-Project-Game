import java.util.ArrayList;

public class GameData {
	private ArrayList<Knight> knights = new ArrayList<>();
	private ArrayList<Knight> activeKnights = new ArrayList<>();
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Enemy> activeEnemies = new ArrayList<>();
	private ArrayList<Luck> luck = new ArrayList<>();
	
	public GameData(){
		
	}
	
	public ArrayList<Knight> getKnights(){
		return knights;
	}
	
	public ArrayList<Knight> getActiveKnights(){
		return activeKnights;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public ArrayList<Luck> getSanslar(){
		return luck;
	}
	
	public Knight getKnight(String name) throws Exception { 
		int i=0;
		for(i = 0; i < activeKnights.size(); i++) {				//Takes the name of each knight in activeKnights array and checks if it matches the entered name.
			if(name.equals(activeKnights.get(i).getName())) {
				return activeKnights.get(i);
			}	
		}
		throw new java.lang.Exception("No knight!");	//Since the method needs to return something, it returns an exception if the knight is not found.
	}
	
	public void addKnight(String name, int id, int index) {	//Method described to ease adding knights to game data.
		luck.add(new Luck());
		knights.add(new Knight(name,id));
		luck.get(index).sansAta(knights.get(index));
		activeKnights.add(knights.get(index));
	}
	
	public void addEnemy(String name, int id, int index) {	//Method described to ease adding enemies to game data.
		enemies.add(new Enemy(name,id));
		activeEnemies.add(enemies.get(index));
	}
	
	
}



