package game;

import java.util.Scanner;

import fixtures.*;

public class Runner {
	// We only need one room manager, so we will make it static
	private static RoomManager manager = new RoomManager(5);
	
	// Boolean used to control when our game is running. We set it to true initially
	private static boolean running = true;
	
	// String to keep track of user input
	private static String[] userInput = {};
	
	// Boolean to keep track of whether user input was interpreted
	private static boolean unableToInterpret = true;
		
	// Main method
	public static void main(String[] args) {
		// Run the init method to instantiate our house
		manager.init();	
		
		// Create the player here. Note: We would have made the player a static object as well...
		System.out.println("Please enter your name to begin game: ");
		Player player = new Player();
		Scanner enterName= new Scanner(System.in); 
		player.setName(enterName.nextLine());
		
		//Welcome and Beginning Instructions
		System.out.println("Welcome "+ player.getName()+"!");
		howToPlay();
		
		//The player should start in the 'starting room'
		player.setCurrentRoom(manager.getStartingRoom());
		printRoom(player);
		
		while (running) {//game doesn't stop until user types 'QUIT'
			String[] input = collectInput();
			parse(input, player);
		}
		
		if (!running) {
			System.out.println("Exiting the game. Thanks for playing!");
		}
	}
	
	//How to play instructions
	private static void howToPlay() {
		System.out.println();
		System.out.println("How to Play:"
		+ "\n Input two-word commands into the console to find your way out of this house."
		+ "\n--How to Move Between Rooms--"
		+ "\n Enter 'GO' or 'MOVE', followed by the direction or the name of room you wish to go to e.g. 'GO SOUTH' or 'MOVE RAINBOW EDEN'."
		+ "\n--How to Interact with Items--"
		+ "\n Enter the item you wish to interact with, preceded by the verb describing how you wish to interact with the item i.e. 'Pick flower'"
		+ "\n Please do not move items from one room to another. The console will not recognize this command."
		+ "\n Type 'HELP' to view these instructions anytime during the game."
		+ "\n Type in any character(s) to go back to playing the game.");
		Scanner input=new Scanner(System.in);
		String userManual=input.nextLine(); //goes back to game upon user inputting any character
	}
	
	// This method prints information to the console regarding the player's current location
	private static void printRoom(Player player) {
		System.out.println(":::: CURRENT ROOM ::::");
		System.out.println("Name: " + player.getCurrentRoom().getName());
		System.out.println("Short Desc: " + player.getCurrentRoom().getShortDesc());
		System.out.println("Long Desc: " + player.getCurrentRoom().getLongDesc());
		if(player.getCurrentRoom().getItems(player.getCurrentRoom()).length>1){
			System.out.println("Items: ");
			for(int i=0; i<player.getCurrentRoom().getItems(player.getCurrentRoom()).length; i++) {
				System.out.println(player.getCurrentRoom().getItems(player.getCurrentRoom())[i].getName());
			}
		}
		// You can also print information about connecting rooms here...
		System.out.println("");
		System.out.println("--Adjacent Room(s)--");
		int i=0;
		while(i<player.getCurrentRoom().getExits().length) {
			String directionOfAdjacentRoom="";
			switch (i) {
			case 0:
				directionOfAdjacentRoom += "NORTH: ";
				break;
			case 1:
				directionOfAdjacentRoom += "SOUTH: ";
				break;
			case 3:
				directionOfAdjacentRoom += "EAST: ";
				break;
			case 2:
				directionOfAdjacentRoom += "WEST: ";
				break;
			}
			if(player.getCurrentRoom().getExits()[i]!=null)	
				System.out.println(directionOfAdjacentRoom+player.getCurrentRoom().getExits()[i].getName());
			i++;
			
		}
	}
	
	// Method used to gather input from the user, and converts it to an array
	private static String[] collectInput() {
		//Prompt user to enter input
		System.out.println();
		System.out.println("What would you like to do?");	
		Scanner scan = new Scanner(System.in);
		String stringUserInput = new String(scan.nextLine());
		userInput = new String[stringUserInput.split(" ").length];
		userInput = stringUserInput.split(" ");
		return userInput;
	}
	
	//Method to interpret input from user
	private static void parse(String[] command, Player player) {
		Room currentRoom = player.getCurrentRoom();
		Fixture item;
		unableToInterpret=true; 
		
		//consolidates room names under one index instead of two
		if(command.length>=3) {
			for(int i=0; i<command.length-1; i++){
				if(isRoom(command, i)) {
					String[]temp;
					temp=roomNameOneIndex(command);
					command=new String[command.length-1];
					command=temp;
					unableToInterpret=false;
				}
			}
		}
		
		//For one line commands
		if(command.length==1) {
			if(command[0].toUpperCase().intern()=="HELP") {
				howToPlay();
				unableToInterpret=false;
				printRoom(player);
			}
			else if(command[0].toUpperCase().intern() == "QUIT") { 
				unableToInterpret=false;
				running = false;
			}
		}		
		if(command.length>=2) {
			for(int i=0; i<command.length; i++){
				if(isDirection(command[i])) {
					changeRooms(command, player);
					unableToInterpret=false;
				}	
				else {
					for(int j=0; j<currentRoom.getItems(currentRoom).length; j++){
						if(command[i].toUpperCase().intern()==currentRoom.getItems(currentRoom)[j].getName().toUpperCase().intern()) {
							item = currentRoom.getItems(currentRoom)[j];
							interactWithItem(command, item, currentRoom, player);
							unableToInterpret=false;
						}
					}
				}
			}
		}
		//when exhausted all other possibilities of what the user meant
		if(unableToInterpret) 
			System.out.println("Sorry. Didn't recognize your command. Please enter your input again.");
		unableToInterpret = true;
	}

	/*Method to change Rooms*/
	//Method to recognize if command includes direction
		public static boolean isDirection(String checkCommandForDirection) {
			switch (checkCommandForDirection.toUpperCase().intern()) {
				case "NORTH":
					return true;
				case "SOUTH":
					return true;
				case "WEST":
					return true;
				case "EAST":
					return true;
				case "UP":
					return true;
				case "DOWN":
					return true;
				case "LEFT":
					return true;
				case "RIGHT":
					return true;
				case "CAST ROOM":
					return true;
				case "STORY OASIS":
					return true;
				case "LOOKING GLASS":
					return true;
				case "CHANGING ROOM":
					return true;
				case "RAINBOW EDEN":
					return true;
			}
			return false;
		}
		
		//Method to recognize if command includes name of room
		public static boolean isRoom(String[] checkCommandForRoomName, int i) {
			switch (checkCommandForRoomName[i].toUpperCase().intern()) {
				case "RAINBOW":
					if(checkCommandForRoomName[i+1].toUpperCase().intern()=="EDEN") 
						return true;
					return false;
				case "CAST":
					if(checkCommandForRoomName[i+1].toUpperCase().intern()=="ROOM") 
						return true;
					return false;
				case "LOOKING":
					if(checkCommandForRoomName[i+1].toUpperCase().intern()=="GLASS") 
						return true;
					return false;
				case "CHANGING":
					if(checkCommandForRoomName[i+1].toUpperCase().intern()=="ROOM") 
						return true;
					return false;
				case "STORY":
					if(checkCommandForRoomName[i+1].toUpperCase().intern()=="OASIS") 
						return true;
					return false;
			}
			return false;
		}
		
	//Put the two-word names of room into one index of array
	public static String[] roomNameOneIndex(String[] parsed) {
		parsed[1]=parsed[1]+" "+parsed[2];
		String[] temp = new String[parsed.length-1];
		for(int i=0; i<2; i++)
			temp[i]=parsed[i];
		for(int i=2; i<parsed.length-1;i++) 
			temp[i]=parsed[i+1];
		return temp;
	}
	
	/*Method to change the location of player by direction*/
	public static void changeRooms(String[]command, Player moveRooms) {
		String action = command[0].toUpperCase().intern();
		String direction = null;
		
		if (command.length > 1) {
			direction = command[1].toUpperCase().intern();
		}
		
		if (action == "GO" | action == "MOVE") {
			System.out.println("You try to move to: " + direction);
			Room move = moveRooms.getCurrentRoom().getExit(direction);
			if(move.getName().toUpperCase().intern()=="CAST ROOM") {
				if(!moveRooms.getHasKey())
					System.out.println("Sorry. You can't go in as the door to this room is locked.");
				else {
					moveRooms.setCurrentRoom(move);
					printRoom(moveRooms);
					String userGuess = "";
					String[]parsedUserGuess = {};
					while(parsedUserGuess.length<2 && userGuess.toUpperCase().intern()!="QUIT" && userGuess.toUpperCase().intern()!="SPARK") {
						System.out.println("Guess the magic word (excluding 'HELP' and 'QUIT') or enter directions to move to another room.");
						Scanner input = new Scanner(System.in);
						userGuess = new String(input.nextLine());
						String[] howManySpaces = userGuess.split(" ");
						parsedUserGuess = new String[howManySpaces.length];
						parsedUserGuess = howManySpaces;
						if(userGuess.toUpperCase().intern()=="HELP") 
							howToPlay();
						if(parsedUserGuess.length>1 || userGuess.toUpperCase().intern()=="QUIT") {
							parse(parsedUserGuess,moveRooms);
							if(!unableToInterpret) {
								System.out.println("Enter 'GUESS WORD' to keep guessing word or anything else to quit guessing.");
								collectInput();
								if(userInput[0].toUpperCase().intern()=="GUESSWORD" || (userInput[0].toUpperCase().intern()=="GUESS" && userInput[1].toUpperCase().intern()=="WORD")); 
									parsedUserGuess=new String[1];
							}
							else if (!unableToInterpret && userGuess.toUpperCase().intern()!="SPARK")
								System.out.println("Sorry. Wrong word.");
						}
					}
					
					if(userGuess.toUpperCase().intern()=="SPARK") {
						moveRooms.setCurrentRoom(move);
						if(moveRooms.getFutureSelf().intern()=="")
							System.out.println("Oops! You missed a step! Come back after visiting Changing Room.");
						else {
							System.out.println("Congratulations " + moveRooms.getName() + " on successfully completing the Spark program!"
									+"\nThis marks the end of essentials on computer programming."
									+"\nFor whatever reason you have pursued this program,"
									+"\nRemember how much you have learned during these four weeks."
									+"\nI am confident that you'll be able to successfully venture into your future as a "+ moveRooms.getFutureSelf() +"!");
							}
						}
				}
			} else {
				moveRooms.setCurrentRoom(move);
				printRoom(moveRooms);
			}
		} else 
			System.out.println("Don't recognize that command for changing locations.");
	}
	
	/*Method to interact with Items*/
	public static void interactWithItem(String[]command, Fixture item, Room currentRoom, Player player) {
		String action = command[0].toUpperCase().intern();
		
		switch(item.getName().toUpperCase().intern()) {
		case ("BASKET"):
			if(action == "CARRY" || action == "HOLD" || action == "TAKE")
				item.doItem();
			else 
				System.out.println("Don't recognize command for item. Try 'CARRY','HOLD', or 'TAKE'");
			break;
		case ("SPECTACLES"):
			if(action == "WEAR")
				item.doItem();
			else 
				System.out.println("Don't recognize command for item. Try 'WEAR'");
			break;
		case ("BOOK"):
			if(action == "OPEN" || action == "SEARCH" || action == "FLIP") {
				item.doItem();
				player.foundKey();
			}
			else 
				System.out.println("Don't recognize command for item. Try 'OPEN','SEARCH', or 'FLIP'");
			break;
		case ("FLOWER"):
			if(action == "PICK")
				item.doItem();
			else 
				System.out.println("Don't recognize command for item. Try 'PICK'");
			break;
		case ("FLOWERS"):
			if(action == "PICK")
				item.doItem();
			else 
				System.out.println("Don't recognize command for item. Try 'PICK'");
			break;
		case ("FRUIT"):
			if(action == "PICK" || action == "HARVEST" || action == "TAKE")
				item.doItem();
			else 
				System.out.println("Don't recognize command for item. Try 'PICK','HARVEST', or 'TAKE'");
			break;
		case ("FRUITS"):
			if(action == "PICK" || action == "HARVEST" || action == "TAKE")
				item.doItem();
			else 
				System.out.println("Don't recognize command for item. Try 'PICK','HARVEST', or 'TAKE'");
			break;
		case ("LADDER"):
			if(action == "CLIMB") {
				item.doItem();
				if(item.returnInt()==10)
					currentRoom.bookIsFound();
			}
			else 
				System.out.println("Don't recognize command for item. Try 'CLIMB'");
			break;
		case ("SCREEN"):
			if(action == "TOUCH" || action == "PROMPT" || action == "START") {
				item.doItem();
				player.setFutureSelf(item.returnInput());
			}
			else 
				System.out.println("Don't recognize command for item. Try 'TOUCH', 'START', or 'PROMPT'");
			break;
		}
	}
}
