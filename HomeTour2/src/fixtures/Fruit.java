package fixtures;

public class Fruit extends Fixture{

	public Fruit(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		System.out.println("Yum! These fruits are so good...too good to eat them alone!");
	}
}