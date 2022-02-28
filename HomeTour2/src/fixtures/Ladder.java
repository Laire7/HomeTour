package fixtures;

import java.util.Scanner;

public class Ladder extends Fixture{
	/*Declare variable for the number of stories the player will reach using the ladder*/
	private int numberOfStories=0;
	
	public Ladder(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		numberOfStories=11;
		/*Prompt user to input the number of stories s/he wishes to climb*/
		System.out.println("Please enter the number of stories you wish to climb to using the ladder:");
		while(numberOfStories<0 || numberOfStories>10) { //loop until user has inputed an integer between 0 to 10, inclusive
			Scanner input = new Scanner(System.in); 
			String stringUserInput = input.nextLine();
			try {
			    numberOfStories = Integer.parseInt(stringUserInput);
			    if (numberOfStories>10) //Remind user that the ladder can only go up to 10 stories high
					System.out.println("Oh no! You will going through the roof! Please enter an integer between 1 to 10, inclusive: ");
				else if(numberOfStories<0)
					System.out.println("Ahhh! You are going into the dungeons! Please enter an integer between 1 to 10, inclusive: ");
			}catch(NumberFormatException e) {
			    System.out.println("Input is not an integer. Please enter an integer between 1 to 10, inclusive.");
			}
			
		}
		//Print the number of stories the user has climbed
		if(numberOfStories==0)
			System.out.println("No stories climbed.");
		else {
			System.out.println("You have arrived at floor " +numberOfStories +".");
			if (numberOfStories==10) //User finds a book upon arriving to the tenth story
				System.out.println("Ouch! A book landed smack on my head! I think I see sparks!");
			else
				System.out.println("Remember, you can go up to ten stories high with this ladder!");
		}
	}
	
	/*Getter method to prompt item Book to appear in main console*/
	public int returnInt() {
		return numberOfStories;
	}
}