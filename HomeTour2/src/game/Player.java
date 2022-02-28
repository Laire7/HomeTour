package game;

import fixtures.Room;

public class Player {
	/*Variables to track for player*/
	private String name; //player's name
	private String futureSelf=""; //whom the player wishes to become
	private Room currentRoom; //to track which room the player is in
	
	/*Check if player has the keys to get inside the Cast Room*/
	private boolean hasKey=false; //whether or not the player has the keys to get inside the Cast Room 
	
	/*Getter and Setter Methods for variables declared above*/
	//Getter and setter methods for key
	public boolean getHasKey() {
		return hasKey;
	}

	public void foundKey() {
		hasKey=true;
	}
	

	//Getter and setter methods of player's current location
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	//Getter and setter methods of player name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//Getter and setter methods of whom the player wishes to become
	public void setFutureSelf(String futureSelf) {
		this.futureSelf = futureSelf;
	}
	
	public String getFutureSelf() {
		return futureSelf;
	}
}
