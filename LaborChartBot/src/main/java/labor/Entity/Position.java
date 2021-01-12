package labor.Entity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;


public class Position {
	public static enum LaborDays {
		WEEKDAYS,
		WEEKENDS,
		EVERYDAY,
		FLEX
	};
	
	private String id;
	private String name;
	private int length;
	private int numSlots;
	private String stringTime;
	private String laborDays;
	private Set<LaborSlot> laborSlots;
	private Set<DayOfWeek> daysOfWeek;
	
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder positionString = new StringBuilder();
		positionString.append("Position: " + '\n' +
								'\t' + "Id: " + id + '\n' +
								'\t' + "Name: " + name + '\n' +
								'\t' + "Time: " + stringTime + '\n' +
								'\t' + "Frequency: " + laborDays + '\n' +
								'\t' + "Length: " + length + " hours" + '\n' +
								'\t' + "Number of Slots: " + numSlots + '\n');
		return positionString.toString();
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

	public int getNumSlots() {
		return numSlots;
	}

	public String getStringTime() {
		return stringTime;
	}

	public String getLaborDays() {
		return laborDays;
	}

	public Set<LaborSlot> getLaborSlots() {
		return laborSlots;
	}

	public Set<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}
	
	
}
