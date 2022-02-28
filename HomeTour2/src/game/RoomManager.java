package game;

import fixtures.*;

public class RoomManager {
	//Declare which room the player will be in at the start of the game
	private Room startingRoom;
	/*Track which rooms are connected to which other rooms and where*/
	private Room[] rooms;
	
	/*so that the main program can access house blueprint*/
	public RoomManager(int roomCount) {
		super();
		rooms = new Room[roomCount];
	}
	
	/*Create house*/
	public void init() {
		//Initiate rooms within house
		Room room1 = new Room(
				"Rainbow Eden",
				"An open center",
				"An orchard oasis. Peaches, grapes, apples, clementines, persimmons, dates, pomegranates, grapefruits, berries, mangoes, cherries, plums, bananas, kiwis, avocadoes all around.  There are two main paths, one leading to the north and south rooms, and the other, to the east andd west rooms. Flowers of all seasons are planted alongside the path.");
		this.rooms[0] = room1;
		
		Room room2 = new Room(
			"Cast Room",
			"A room easy to overlook",
			"A whitewashed room with plain wooden floor. To the north, a long horizontal slit shows tops of trees against a white expanse. This room is cast a spell by the sorcerer!");
		this.rooms[1] = room2;
		
		Room room3 = new Room(
			"Looking Glass",
			"A corridor of windows",
			"Rows of windows lined the walls. You casually glance at them from the corner of your eye, and suddenly come to the realization that the windows show different seasons and different parts of the world. You step up to one of the windows and see snow blanketing a Danish town from which fireplace glows from every window and chimneys actively fuming what's cooking for tonight's dinner. Wait, what's that engraved on the mantelpiece? I can't see it with my bare eyes. I need something to help magnify it to read what it says there.");
		this.rooms[2] = room3;
		
		Room room4 = new Room(
			"Story Oasis",
			"Literature from all over the world",
			"Shelves brimming over with books can be seen covering the walls ten stories high. At the top-most floor is a sky window where the sunlight streams in during day and moonlight during night. Immediately to the left of you is a ladder that can reach the books on the top-most shelves.");
		this.rooms[3] = room4;
		
		Room room5 = new Room(
			"Changing Room",
			"The future of fitting room",
			"In front of you is a screen in which you see your reflection. Upon few words of command, a door opens to your left. ");
		this.rooms[4] = room5;
		
		this.startingRoom = room1; //Set starting room to Rainbow Eden
		
		//Blueprint of house
		room1.setExits(room2, 0);
		room1.setExits(room3, 2);
		room1.setExits(room4, 1);
		room1.setExits(room5, 3);
		room2.setExits(room1, 1);
		room3.setExits(room1, 3);
		room4.setExits(room1, 0);
		room5.setExits(room1, 2);
	}
	
	/*for other classes/methods to access house blueprint*/
	public Room getRoom(int index) {
		return rooms[index];
	}
	
	public Room getRoom(String roomName) {
		int index = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (roomName.toUpperCase().intern() == rooms[i].getName().toUpperCase().intern()) {
				// return the room based on the index (we call the overloaded function)
				return getRoom(i);
			}
		}
		
		// If the for loop does NOT find a matching room name
		return null;
	}
	
	//Getter and setter method for starting room
	public Room getStartingRoom() {
		return this.startingRoom;
	}
	
	public void setStartingRoom(Room startingRoom) {
		this.startingRoom = startingRoom;
	}
	
	//to access and set house blueprint
	public Room[] getRooms() {
		return this.rooms;
	}
	
	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
}
