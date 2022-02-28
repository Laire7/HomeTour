package fixtures;

public class Spectacles extends Fixture{

	public Spectacles(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		System.out.println("'Spark'? There sure are lots of sparks flying from this fireplace!");
	}
}