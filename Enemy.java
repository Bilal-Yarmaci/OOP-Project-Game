public class Enemy extends Characters{
	private String enemyType;
	
	public Enemy(String name, int id) {		//An object is created by calling the corresponding constructor of Characters class.
		super(name, id);
		if(this.getId()==1)					//All StrongEnemy objects have id numbers of 1 and WeakEnemies, 2.
			enemyType="StrongEnemy";		//Accordingly, enemyType adjustment is done during the creation phase.
		else								//This data is used to write the data to a file, the player cannot see the enemy type.
			enemyType="WeakEnemy";
	}
	
	public String getType() {				//Allows to get private enemyType.
		return enemyType;
	}
}
