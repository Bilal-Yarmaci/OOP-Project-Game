public class Luck {
	
	private boolean goodLuck;
	private boolean badLuck;
	private int luck, range;
	
	public void sansAta(Knight s) {
		
		range = (int)(Math.random()*10);
		if(range<=6)			//Luck is equal to 0 by 70% chance.
			luck=0;
		else if(range>6 && range<=8 )		//Luck is equal to 1 by 20% chance.
			luck=1;
		else									//Luck is equal to 2 by 10% chance.
			luck=2;
		if (luck==0)
			badLuck=true;
		else
			goodLuck=true;
		s.setHitCount(1+luck);		//Assigning randomly generated luck to the knight.
	}
	
	@Override
	public String toString() {
		return "\nGood luck: " + goodLuck +
			   "\nBad luck: " + badLuck;
	}
}
