package labor.Entity;

import java.time.DayOfWeek;
import labor.Entity.Embedded.TimeSlot;


public class LaborSlot {
	// Day:Hour:Minute
	private String id;
	
	Cooper cooper;
	private Position position;
	private TimeSlot timeSlot;
	
	LaborSlot() {
		
	}

	public String getId() {
		return id;
	}

	public Cooper getCooper() {
		return cooper;
	}

	public Position getPosition() {
		return position;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	
	
	
	

}
