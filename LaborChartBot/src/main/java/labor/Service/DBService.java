package labor.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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
	// Pass
	public Cooper findCooperByDiscordTag(String discordTag) {
		Cooper[] matchingCoopers = rest.getForObject(
				"http://localhost:8090/api/coopers/search/?discordTag={discordTag}",
				Cooper[].class, 
				discordTag);
		try {
			return matchingCoopers[0];
		} catch(Exception e) {
			return null;
		}
	}
	
	// GET cooper by Username
	// Pass
	public Cooper findCooperByUsername(String username) {
		ResponseEntity<Cooper[]> matchingCoopers = rest.getForEntity(
				"http://localhost:8090/api/coopers/search/?username={username}",
				Cooper[].class, 
				username);
		try {
			return matchingCoopers.getBody()[0];
		} catch(Exception e) {
			return null;
		}
	}
	
	// Pass
	public Cooper postCooper(Cooper cooper) {
		return rest.postForObject("http://localhost:8090/api/coopers", cooper, Cooper.class);
	}
	
	
	
	/* LaborSlotRespository Requests */
	// Untested
	public List<LaborSlot> findLaborSlotByTimeSlot(TimeSlot timeSlot) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSlot", timeSlot);
		LaborSlot[] laborSlotQuery = rest.getForObject(
				"http://localhost:8090/api/laborSlots/search/?timeSlot={timeSlot}",
				LaborSlot[].class, 
				params);
		try {
			return Arrays.asList(laborSlotQuery);
		} catch(Exception e) {
			return null;
		}
		
	}
	
	// Pass
	public List<LaborSlot> findLaborSlotByDayOfWeekAndPosition(DayOfWeek dayOfWeek, String position) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dayOfWeek", dayOfWeek);
		params.put("position", position);
		LaborSlot[] laborSlotQuery = rest.getForObject(
				"http://localhost:8090/api/laborSlots/search/?dayOfWeek={dayOfWeek}&position={position}",
				LaborSlot[].class, 
				params);
		try {
			return Arrays.asList(laborSlotQuery);
		} catch(Exception e) {
			return null;
		}
	}
	
	// Pass
	public List<LaborSlot> findLaborSlotByDayOfWeekAndPositionAndDiscordTag(DayOfWeek dayOfWeek, String position, String discordTag) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dayOfWeek", dayOfWeek);
		params.put("position", position);
		params.put("discordTag", discordTag);
		
		//ResponseEntity<String> laborSlotQuery = rest.getForEntity(
		//		"http://localhost:8090/api/laborSlots/search/?dayOfWeek={dayOfWeek}&position={position}&discordTag={discordTag}",
		//		String.class, 
		//		params);
		//System.out.println(laborSlotQuery.getBody());
		
		ResponseEntity<LaborSlot[]> laborSlotQuery = rest.getForEntity(
				"http://localhost:8090/api/laborSlots/search/?dayOfWeek={dayOfWeek}&position={position}&discordTag={discordTag}",
				LaborSlot[].class, 
				params);
		
		try {
			return Arrays.asList(laborSlotQuery.getBody());
		} catch(Exception e) {
			return null;
		}
	}
	
	// Pass
	public List<LaborSlot> findLaborSlotByDayOfWeekAndTime(DayOfWeek dayOfWeek, String time) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dayOfWeek", dayOfWeek);
		params.put("time", time);
		LaborSlot[] laborSlotQuery = rest.getForObject(
				"http://localhost:8090/api/laborSlots/search/?dayOfWeek={dayOfWeek}&time={time}",
				LaborSlot[].class, 
				params);
		try {
			return Arrays.asList(laborSlotQuery);
		} catch(Exception e) {
			return null;
		}
	}
	
	// Pass
	public List<LaborSlot> findLaborSlotByCooper(Cooper cooper) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cooper", cooper.getId());
		LaborSlot[] laborSlotQuery = rest.getForObject(
				"http://localhost:8090/api/laborSlots/search/?cooper={cooper}",
				LaborSlot[].class, 
				params);
		try {
			return Arrays.asList(laborSlotQuery);
		} catch(Exception e) {
			return null;
		}
	}
	
	// Untested
	public void patchLaborSlot(LaborSlot laborSlot) {
		rest.patchForObject("http://localhost:8090/api/laborSlots/{laborSlot}", laborSlot, LaborSlot.class);
	}
	
	/* PositionRepository Requests */
	
	// Pass
	public Position findPositionById(String id) {
		HttpEntity<Position> responseEntity = rest.getForEntity("http://localhost:8090/api/positions/search/?id={id}", Position.class, id);
		return responseEntity.getBody();
	}
	
	// Untested
	public void postPosition(Position position) {
		rest.postForObject(
				"http://localhost:8090/api/positions", 
				position,
				Position.class);
	}
	
	// Untested
	public void patchPosition(Position position) {
		rest.patchForObject("http://localhost:8090/api/positions/{position}", position, Position.class);
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
