package labor.Entity;

import java.time.DayOfWeek;

import com.fasterxml.jackson.annotation.JsonProperty;

import labor.Entity.Embedded.TimeSlot;


public class LaborSlot {
	// Day:Hour:Minute
	private String id;
	
	private Cooper cooper;
	private Position position;
	private TimeSlot timeSlot;
	
	LaborSlot() {
		
	}
	
	
	public void clearCooper() {
		this.cooper = null;
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
	
	public void setCooper(Cooper cooper) {
		this.cooper = cooper;
	}
	
	

}
