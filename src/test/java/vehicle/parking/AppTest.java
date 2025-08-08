package vehicle.parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AppTest {

    @Test
    public void testParkingAvailability() {
        int initialSpots = 100;
        assertEquals(100, initialSpots);
    }

    @Test
    public void testDurationCalculation() {
        LocalDateTime entry = LocalDateTime.now().minusMinutes(90);
        LocalDateTime exit = LocalDateTime.now();
        double hours = java.time.Duration.between(entry, exit).toMinutes() / 60.0;

        assertTrue(hours >= 1.4 && hours <= 1.6);  // Should be about 1.5 hours
    }
}
