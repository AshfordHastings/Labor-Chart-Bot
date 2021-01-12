package labor.Entity.Embedded;

import java.time.DayOfWeek;


public class TimeSlot {
	
	private DayOfWeek dayOfWeek;
	private String timeString;
	
	public TimeSlot() {		
	}
	public TimeSlot(DayOfWeek dayString, String timeString) {
		this.timeString = timeString;
		this.dayOfWeek = dayString;
	}
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public String getTimeString() {
		return timeString;
	}
	
	
}
