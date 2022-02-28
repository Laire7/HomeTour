package fixtures;

import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;


public class Screen extends Fixture{
	private String searchImage="";

	public Screen(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
		// TODO Auto-generated constructor stub
	}
	
	//Override doItem() method in Fixture
	public void doItem() {
		/*Prompt user to enter any name of character whom they wish to become*/
		System.out.println("Welcome to the Transfiguration Program where you can transform into any character you desire.");
		System.out.println("Please enter the name of whom you wish to become: ");
		Scanner input = new Scanner(System.in); 
		searchImage = input.nextLine();
		//Open Google search page result of the character the user wishes to become
		try {
			  Desktop desktop = java.awt.Desktop.getDesktop();
			  URI oURL = new URI("http://google.com/search?q="+searchImage);
			  desktop.browse(oURL);
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}
	
	//Override returnInput() method in Fixture to store this name of character to instance of the Player class
	public String returnInput() {
		return searchImage;
	}
}