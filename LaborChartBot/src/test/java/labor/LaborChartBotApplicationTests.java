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
	
	/* CooperRespository Tests */
	@Test // Pass
	void testFindCooperByDiscordTag() {
		Cooper testCooper = laborService.getDBService().findCooperByDiscordTag("blake#4416");
		assertThat(testCooper).isNotNull();
		assertThat(testCooper.getUsername()).isEqualTo("blake");
	}
	
	@Test // Pass
	void testFindCooperByUsername() {
		Cooper testCooper = laborService.getDBService().findCooperByUsername("blake");
		assertThat(testCooper).isNotNull();
		assertThat(testCooper.getDiscordTag()).isEqualTo("blake#4416");
	}
	
	@Test // Pass
	void testPostCooper() {
		Cooper newCooper = new Cooper("ash", "ash#1234");
		laborService.getDBService().postCooper(newCooper);
		
		Cooper testCooper = laborService.getDBService().findCooperByUsername("ash");
		assertThat(testCooper).isNotNull();
		assertThat(testCooper.getDiscordTag()).isEqualTo("ash#1234");
	}
	
	/* LaborSlotRespository Tests */
	@Test // Fail
	void testFindLaborSlotByTimeSlot() {
		Position testPosition = new Position("DCU", "Dinner Clean-Up", "18:00", "WEEKDAYS", 2, 4);
		laborService.getDBService().createPosition(testPosition);
		List<LaborSlot> responseLaborSlots = laborService.getDBService().findLaborSlotByDayOfWeekAndPosition(DayOfWeek.TUESDAY, testPosition.getId());
		
		List<LaborSlot> responseLaborSlots2 = laborService.getDBService().findLaborSlotByTimeSlot(responseLaborSlots.get(0).getTimeSlot());
		assertThat(responseLaborSlots2.size()).isEqualTo(4);
	}
	
	@Test // Pass
	void testFindLaborSlotByDayOfWeekAndPosition() {
		List<LaborSlot> testLaborSlot1 = laborService.getDBService().findLaborSlotByDayOfWeekAndPosition(DayOfWeek.MONDAY, "DCH");
		assertThat(testLaborSlot1).isNotNull();
		assertThat(testLaborSlot1).hasSize(2);
		assertThat(testLaborSlot1.get(0).getPosition().getId()).isEqualTo("DCH");
	}
	
	@Test // Pass
	void testFindLaborSlotByDayOfWeekAndPositionAndDiscordTag() {
		List<LaborSlot> testLaborSlot2 = laborService.getDBService().findLaborSlotByDayOfWeekAndPositionAndDiscordTag(DayOfWeek.TUESDAY, "DCH", "blake#4416");
		assertThat(testLaborSlot2).isNotNull();
		assertThat(testLaborSlot2).hasSize(1);
		assertThat(testLaborSlot2.get(0).getPosition().getId()).isEqualTo("DCH");
		assertThat(testLaborSlot2.get(0).getCooper().getDiscordTag()).isEqualTo("blake#4416");
	}
	
	@Test // Pass
	void testFindLaborSlotByDayOfWeekAndTime() {
		List<LaborSlot> testLaborSlot3 = laborService.getDBService().findLaborSlotByDayOfWeekAndTime(DayOfWeek.TUESDAY, "16:00");
		assertThat(testLaborSlot3).isNotNull();
		assertThat(testLaborSlot3).hasSize(2);
		assertThat(testLaborSlot3.get(0).getPosition().getId()).isEqualTo("DCH");
	}
	
	@Test // Pass
	void testFindLaborSlotByCooper() {
		Cooper testCooper = laborService.getDBService().findCooperByUsername("blake");
		System.out.println(testCooper);
		List<LaborSlot> testLaborSlot = laborService.getDBService().findLaborSlotByCooper(testCooper);
		assertThat(testLaborSlot).isNotNull();
		assertThat(testLaborSlot).hasSize(1);
		assertThat(testLaborSlot.get(0).getPosition().getId()).isEqualTo("DCH");
	}
	
	/* PositionRepository Tests */
	
	@Test //Fail
	void testFindPositionById() {
		Position testPosition = laborService.getDBService().findPositionById("DCH");
		assertThat(testPosition).isNotNull();
		assertThat(testPosition.getName()).isEqualTo("Dinner Chef");
	}
	
	@Test
	void createPosition() {
		Position testPosition = new Position("DCU", "Dinner Clean-Up", "18:00", "WEEKDAYS", 2, 4);
		laborService.getDBService().createPosition(testPosition);
		Position responsePosition = laborService.getDBService().findPositionById(testPosition.getId());
		
		assertThat(responsePosition.getName()).isEqualTo(testPosition.getName());
		assertThat(responsePosition.getDaysOfWeek().size()).isEqualTo(5);
		assertThat(responsePosition.getLaborSlots().size()).isEqualTo(testPosition.getNumSlots() * 5);
		
		List<LaborSlot> responseLaborSlots = laborService.getDBService().findLaborSlotByDayOfWeekAndPosition(DayOfWeek.THURSDAY, testPosition.getId());
		assertThat(responseLaborSlots.size()).isEqualTo(4);
	}

	
	@Test
	void testPatchPosition() {
		Position testPosition = laborService.getDBService().findPositionById("DCH");
		
		testPosition.setName("Dinner Chef Update");
		laborService.getDBService().patchPosition(testPosition);
		
		Position testPosition2 = laborService.getDBService().findPositionById("DCH");
		assertThat(testPosition2).isNotNull();
		assertThat(testPosition2.getName()).isEqualTo("Dinner Chef Update");
	}

}
