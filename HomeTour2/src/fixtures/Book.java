package fixtures;

public class Book extends Fixture{

	public Book(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		System.out.println("You have find a key!");
	}
}