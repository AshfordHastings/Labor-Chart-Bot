package labor.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import labor.Entity.Cooper;
import labor.Entity.LaborSlot;
import labor.Entity.Position;
import labor.Entity.Embedded.TimeSlot;

@Service
public class DBService {
	
	RestTemplate rest = new RestTemplate();
	
	/* CooperRespository Requests */
	// GET cooper by DiscordTag
	public Cooper findCooperByDiscordTag(String discordTag) {
		CooperList matchingCoopers = rest.getForObject(
				"http://localhost:8090/api/coopers/search/findByDiscordTag{discordTag}",
				CooperList.class, 
				discordTag);
		try {
			return matchingCoopers.getCoopers().get(0);
		} catch(Exception e) {
			return null;
		}
	}
	
	// GET cooper by DiscordTag
	public String findCooperByUsername(String username) {
		StringList matchingCoopers = rest.getForObject(
				"http://localhost:8090/api/coopers/search/findByUsername{username}",
				StringList.class, 
				username);
		try {
			return matchingCoopers.getStrings().get(0);
		} catch(Exception e) {
			return null;
		}
	}
	
	// POST new Cooper
	public Cooper saveCooper(Cooper cooper) {
		return rest.postForObject("http://localhost:8090/api/coopers", cooper, Cooper.class);
	}
	
	
	
	/* LaborSlotRespository Requests */
	
	public List<LaborSlot> findLaborSlotByTimeSlot(TimeSlot timeSlot) {
		
	}
	
	public List<LaborSlot> findLaborSlotByDayOfWeekAndPosition(DayOfWeek dayOfWeek, String position) {
		
	}
	
	public List<LaborSlot> findLaborSlotByDayOfWeekAndPositionAndDiscordTag(DayOfWeek dayOfWeek, String position, String discordName) {
		
	}
	
	public List<LaborSlot> findLaborSlotByDayOfWeekAndTime(DayOfWeek dayOfWeek, String time) {
		
	}
	
	public List<LaborSlot> findLaborSlotByCooper(Cooper cooper) {
		
	}
	
	public void patchLaborSlot(LaborSlot laborSlot) {
		rest.patchForObject("http://localhost:8090/api/laborSlots/{laborSlot}", laborSlot, LaborSlot.class);
	}
	
	/* PositionRepository Requests */
	
	public Position createPosition(Position position) {
		return rest.postForObject(
				"http://localhost:8090/api/positions{position}", 
				position, 
				Position.class);
	}
	
	public Position findPositionById(String id) {
		return rest.getForObject("http://localhost:8090/api/positions{id}", Position.class, id);
	}
	
	// Wrapper class StringList to receive lists from HTTP call
	public class StringList {
		private List<String> stringList;
		public StringList() {
			stringList = new ArrayList<String>();
		}
		public List<String> getStrings() {
			return stringList;
		}
	}
	
	public class CooperList {
		private List<Cooper> cooperList;
		public CooperList() {
			cooperList = new ArrayList<Cooper>();
		}
		public List<Cooper> getCoopers() {
			return cooperList;
		}
	}
	

}
