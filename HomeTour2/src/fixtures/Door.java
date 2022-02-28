package fixtures;

public class Door extends Fixture {
	/*Declare boolean variables to check whether or not door is open or closed*/
	private boolean locked;
	
	public Door(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		if (locked) {
			System.out.println("This door is locked. Please find the key first.");
		}
	}
	
	/*Getter and setter method for doorlock*/
	public boolean isLocked() {
		return locked;
	}
	
	public void setLock(boolean locked) {
		this.locked=locked;
	}
}
