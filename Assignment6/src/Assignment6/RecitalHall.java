/* Name: Caroline Yao & Horng-Bin Justin Wei
 * EID: Chy253 & Hjw396
 * Section: Thursday 3:30-5:30pm, Friday 2-3:30pm
 * EE 422C Assignment 6
 */
package Assignment6;

import java.util.ArrayList;

public class RecitalHall {
	
	private final int SEAT_NUM = 114;
	private char seatRow;
	private ArrayList<String> seats;
	
	public RecitalHall(){
		seatRow = 'A';
		seats = new ArrayList<String>();
		createList();
	}
	
	//creates list of seats in decreasing preference order
	public void createList(){
		while(seatRow <= 'Z'){
			for(int i = 0; i <= 14; i++){
				seats.add(seatRow + "" + (SEAT_NUM + i));
				if(i != 0 && i != 14){
					seats.add(seatRow + "" + (SEAT_NUM - i));
				}
			}
			seatRow += 1;
		}
	}
	
	//returns the first seat in the list
	public synchronized String getBestAvailableSeat(){
		String out;
		if(seats.size() != 0){
			out = seats.get(0);
			seats.remove(0);
		} else {
			out = "empty";
			// no more seats
		}
		return out;
	}
	
	public void printTicketSeat(String seat, String requester){
		if(seat.equals("empty")){
			System.out.println("Sorry, we are sold out.");
		}
		else{
			System.out.println(requester + ": Reserved seat " + seat);
		}
	}
	
	

}
