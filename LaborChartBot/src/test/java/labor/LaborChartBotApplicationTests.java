package labor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import labor.Entity.Cooper;
import labor.Entity.LaborSlot;
import labor.Entity.Position;
import labor.Service.LaborService;

@SpringBootTest
class LaborChartBotApplicationTests {

	@Autowired
	LaborService laborService;
	
	@Test
	void testFindPositionById() {
		
		// Find Position, find Member
		
		Position testPosition = laborService.getDBService().findPositionById("DCH");
		assertThat(testPosition).isNotNull();
		assertThat(testPosition.getName()).isEqualTo("Dinner Chef");
		

		
		
		//LaborSlot laborService.getDBService().findLaborSlotByDayOfWeekAndPosition(dayOfWeek, position)
	}
	
	@Test
	void testFindCooper() {
		
		// Bot Testing
		
		//Cooper testCooper = laborService.getDBService().findCooperByDiscordTag("blake#4416");
		//assertThat(testCooper).isNotNull();
		//assertThat(testCooper.getUsername()).isEqualTo("blake");
		
		Cooper testCooperUsername = laborService.getDBService().findCooperByUsername("blake");
		assertThat(testCooperUsername).isNotNull();
		
		//LaborSlot laborService.getDBService().findLaborSlotByDayOfWeekAndPosition(dayOfWeek, position)
	}
	
	@Test
	void testLaborSlotQueries() {
		//TimeSlot timeSlot;
		//Cooper testCooperUsername = laborService.getDBService().findLaborSlotByTimeSlot(timeSlot)
		//assertThat(testCooperUsername).isNotNull();
		
		List<LaborSlot> testLaborSlot1 = laborService.getDBService().findLaborSlotByDayOfWeekAndPosition(DayOfWeek.MONDAY, "DCH");
		assertThat(testLaborSlot1).isNotNull();
		assertThat(testLaborSlot1).hasSize(2);
		assertThat(testLaborSlot1.get(0).getPosition().getId()).isEqualTo("DCH");
		
		List<LaborSlot> testLaborSlot2 = laborService.getDBService().findLaborSlotByDayOfWeekAndPositionAndDiscordTag(DayOfWeek.TUESDAY, "DCH", "blake#4416");
		assertThat(testLaborSlot2).isNotNull();
		assertThat(testLaborSlot2).hasSize(1);
		assertThat(testLaborSlot2.get(0).getPosition().getId()).isEqualTo("DCH");
		assertThat(testLaborSlot2.get(0).getCooper().getDiscordTag()).isEqualTo("blake#4416");
	}
	
	@Test
	void testLaborSlotQuery3() {
		List<LaborSlot> testLaborSlot3 = laborService.getDBService().findLaborSlotByDayOfWeekAndTime(DayOfWeek.TUESDAY, "16:00");
		assertThat(testLaborSlot3).isNotNull();
		assertThat(testLaborSlot3).hasSize(2);
		assertThat(testLaborSlot3.get(0).getPosition().getId()).isEqualTo("DCH");
	}	
	
	@Test
	void testLaborSlotQuery4() {
		Cooper cooper = laborService.getDBService().findCooperByUsername("blake");
		List<LaborSlot> testLaborSlot4 = laborService.getDBService().findLaborSlotByCooper(cooper);
		assertThat(testLaborSlot4).isNotNull();
		assertThat(testLaborSlot4).hasSize(1);
		assertThat(testLaborSlot4.get(0).getPosition().getId()).isEqualTo("DCH");
	}
	
	@Test
	void testFindCooperByTag() {
		Cooper testCooperUsername = laborService.getDBService().findCooperByDiscordTag("blake#4416");
		assertThat(testCooperUsername).isNotNull();
	}
	
	@Test
	void testPost() {
		Cooper newCooper = new Cooper("ash", "ash#1234");
		laborService.getDBService().createCooper(newCooper);
		
		Cooper testCooper = laborService.getDBService().findCooperByUsername("ash");
		assertThat(testCooper).isNotNull();
	}
	
	@Test
	void testPatch() {
		Position testPosition = laborService.getDBService().findPositionById("DCH");
		assertThat(testPosition).isNotNull();
		assertThat(testPosition.getName()).isEqualTo("Dinner Chef");
		
		testPosition.setName("Dinner Chef Update");
		laborService.getDBService().patchPosition(testPosition);
		
		Position testPosition2 = laborService.getDBService().findPositionById("DCH");
		assertThat(testPosition2).isNotNull();
		assertThat(testPosition2.getName()).isEqualTo("Dinner Chef Update");
	}

}
