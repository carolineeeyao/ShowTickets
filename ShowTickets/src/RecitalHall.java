import java.util.ArrayList;

public class RecitalHall {
	
	private final int SEAT_NUM = 114;
	private char seatRow;
	private ArrayList<String> seats;
	
	public RecitalHall(){
		seatRow = 'A';
		seats = new ArrayList<String>();
	}
	
	public void createList(){
		while(seatRow <= 'Z'){
			for(int i = 0; i <= 14; i++){
				seats.add(seatRow + "" + (SEAT_NUM + i));
				if(i != 0 || i != 14){
					seats.add(seatRow + "" + (SEAT_NUM - i));
				}
			}
			seatRow += 1;
		}
	}
	
	public String getBestAvailableSeat(){
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
	
	

}
