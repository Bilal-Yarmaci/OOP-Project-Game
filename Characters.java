public class Characters {
	private String name;
	private int id;
	private boolean active;

	public Characters(String name, int id) {	
		this.name=name;
		this.id=id;
		active = true;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active=active;
	}
	
	public String getName() {		//Method to get private name variable.
		return name;
	}
	
	public int getId() {		//Method to get private id variable.
		return id;
	}
}