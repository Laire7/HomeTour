package fixtures;

public class Basket extends Fixture{

	public Basket(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		System.out.println("Now you can harvest lots of fruits at once - enough to make fruit pie for 55 people plus more!");
	}
}
