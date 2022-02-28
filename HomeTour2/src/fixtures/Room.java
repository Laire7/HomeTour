package fixtures;

public class Room extends Fixture {
	/*Declare Array for Rooms*/
	private Room[] exits = new Room[4];
	
	/*Declare Variables for door into Room 2*/
	private Door door = new Door("Door to Cast Room",
			"This door has a lock.",
			"The key to this door lock can be found in one of the other rooms.");
	
	//Methods to determine or communicate whether or not player has access to the Cast Room
	public void keyFound() {
		door.setLock(false);
	}
	
	public boolean canGoInRoom() {
		return !door.isLocked();
	}
	
	/*Declare Variables for each Item*/
	private Fixture basket = new Basket("Basket", 
			"For Collecting harvest from garden",
			"Large basket 3ft x 3ft x 5.5ft that can hold up to 17kg. A magical basket that never drops a fruit once put inside. Carry the basket to hold more fruit!"); 
	private Fixture fruit = new Fruit("Fruit", 
			"Hanging on the trees",
			"The fruits are ripe and ready for picking."); 
	private Fixture flower = new Flower("Flower", 
			"Young buds",
			"Haven't yet opened their eyes to greet 'Good morning'."); 
	private Fixture spectacles = new Spectacles("Spectacles", 
			"Pair of very old spectacles",
			"On its legs it is etched:"
			+ "\n 'All that's needed to start in this world"
			+"\n I will give you two fortnights to master"
			+ "\n But I promise you anyone can do it"
			+ "\n If you can promise you at least fifteen hours a week of yours to this craft.'"); 
	private Fixture ladder = new Ladder("Ladder", 
			"Ten-story high expandable ladder",
			"Type in a number to the console to go up and down the ladder."); 
	private Fixture book = new Book("Book", 
			"Titled 'Cast' with a picture that looks like fireworks on its cover.",
			"There is something inside this book. Wonder what's inside?"); 
	private Fixture screen = new Screen("Screen", 
			"The latest transfiguration technology",
			"Is awaiting your wishes."); 
	
	//Assign items to their corresponding rooms
	protected Fixture[]room1Items = {basket, fruit, flower};
	protected Fixture[]room2Items = {};
	protected Fixture[]room3Items = {spectacles};
	protected Fixture[]room4Items = {ladder};
	protected Fixture[]room4ItemsPlusBook = {ladder, book};
	private Fixture[]room5Items = {screen};
	
	boolean bookFound = false; //whether or not player has found book item yet
	
	public void bookIsFound() {//once the player has found the book, then...
		bookFound=true;
	}
	
	/*Method to return the items corresponding to each room*/
	public Fixture[] getItems(Room currentRoom) {
		switch(currentRoom.getName()) {
		case ("Rainbow Eden"):
			return room1Items;
		case ("Cast Room"):
			return room2Items;
		case ("Looking Glass"):
			return room3Items;
		case ("Story Oasis"):
			if(bookFound)
				return room4ItemsPlusBook; //change items array once player has found book
			else
				return room4Items;
		default:
			return room5Items;
		}
	}
	
	/*Constructors*/
	public Room(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
	}
	
	/*Access house blueprint from Room Manager class*/
	public Room[] getExits() {
		return this.exits;
	}
	
	public Room getExit(String direction) { 
		//Assign a random index to each direction for conveniency's sake
		int index = 4;
		direction = direction.toUpperCase().intern();
		switch (direction) {
		case "NORTH": 
			index = 0;
			break;
		case "SOUTH":
			index = 1;
			break;
		case "WEST":
			index = 2;
			break;
		case "EAST":
			index = 3;
			break;
		case "UP":
			index = 0;
			break;
		case "DOWN":
			index = 1;
			break;
		case "LEFT":
			index = 3;
			break;
		case "RIGHT":
			index = 2;
			break;
		default: //should user input the name of room
			for(int i=0; i<this.exits.length; i++) {//check if the named room exists adjacent to current room
				if(this.exits[i]!=null) {
					if(direction.toUpperCase().intern()==this.exits[i].getName().toUpperCase().intern()) 
						index=i;
				}
			}
			if(index >= this.exits.length || this.exits[index] == null) {
				System.out.println("The room "+ direction + " is not adjacent to your current room "+ this.getName().toUpperCase() +".");
				return this;
			}
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
		
		// If statement checks if there is actually a room in specified direction
		if (index >= this.exits.length || this.exits[index] == null) {
			System.out.println("There is no room in that direction.");
			// Return the current room is there is not a room in that direction
			return this;
		}
		
		return this.exits[index];
	}
	
	// setter for exits
	public void setExits(Room[] exits) {
		this.exits = exits;
	}

	// Overloaded setter method. Allows us to set single exit based on index...
	public void setExits(Room exit, int index) {
		this.exits[index] = exit;
	}
}
