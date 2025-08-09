package vehicle.parking;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class AppTest {

    @Test
    public void testParkingAvailability() {
        int totalFloors = 2;
        int slotsPerFloor = 50;
        assertEquals(100, totalFloors * slotsPerFloor);
    }

    @Test
    public void testDurationCalculation() {
        LocalDateTime entry = LocalDateTime.now().minusMinutes(90);
        LocalDateTime exit = LocalDateTime.now();
        double hours = java.time.Duration.between(entry, exit).toMinutes() / 60.0;
        assertTrue(hours >= 1.4 && hours <= 1.6);
    }

    @Test
    public void testSlotAssignment() {
        // Simulate a slot assignment for floor 1, slot 10
        String assignedSlot = "Floor 1 - Slot 10";
        assertTrue(assignedSlot.contains("Floor 1"));
        assertTrue(assignedSlot.contains("Slot 10"));
    }
}
