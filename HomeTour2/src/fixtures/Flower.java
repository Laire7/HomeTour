package fixtures;

public class Flower extends Fixture{

	public Flower(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		System.out.println("How pretty! Nothing like flowers to make the mood festive.");
	}
}