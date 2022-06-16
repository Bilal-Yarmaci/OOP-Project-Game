public class Knight extends Characters {

	private int hitCount = 1;		//By default, the knight has 1 strike when first created.
	private String knightType;

	public Knight(String name, int id) {		//An object is created by calling the corresponding constructor of Characters class.
		super(name, id);
		if(this.getId()==1)					//All StrongKnight objects have id numbers of 1 and WeakKnights, 2. 
			knightType="StrongKnight";		//Accordingly, knightType adjustment is done during the creation phase.
		else
			knightType="WeakKnight";
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public void setHitCount(int vh) {
		hitCount=vh;
	}
	
	public String getType() {
		return knightType;
	}
	
	public void hit(Enemy e) { 
			hitCount--;											//hitCount is decreased with every hit.
			if(this.getId()<=e.getId()) {					//Id determines which character will be defeated.
				e.setActive(false);
				System.out.println("The enemy was hit!");
			}
			else {
				this.setActive(false);						//Defeated character's active status is set to false.
				System.out.println("Enemy is not defeated.");
				System.out.println("Weak knight cannot defeat strong enemy!");
				return;
			}
			if(hitCount==0)								//The knight's active status is set to false if its strikes are over.
				this.setActive(false);
	}
	
	@Override
	public String toString() {								//An overriding method to display knight information.
		return	")-------------------------\n"+
				"Knight name: "+this.getName()
				+"\nKnight type: "+knightType
				+"\nKnight id: "+this.getId()
				+"\nKnight strike: "+hitCount
				+ "\n---------------------------\n";
	}
}