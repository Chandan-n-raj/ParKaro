package vehicle.parking;

import java.util.*;
import java.time.*;

public class VehicleParkingSystem {

    private static final double RATE_PER_HOUR = 50.0;
    private static final int FLOORS = 2;
    private static final int SLOTS_PER_FLOOR = 50;

    // Track available slots for each floor
    private static Map<Integer, Set<Integer>> availableSlots = new HashMap<>();

    // Vehicle Number -> (EntryTime, Floor, Slot)
    private static Map<String, VehicleInfo> parkedVehicles = new HashMap<>();

    static class VehicleInfo {
        LocalDateTime entryTime;
        int floor;
        int slot;

        VehicleInfo(LocalDateTime entryTime, int floor, int slot) {
            this.entryTime = entryTime;
            this.floor = floor;
            this.slot = slot;
        }
    }

    static {
        // Initialize all slots as available
        for (int floor = 1; floor <= FLOORS; floor++) {
            Set<Integer> slots = new LinkedHashSet<>();
            for (int slot = 1; slot <= SLOTS_PER_FLOOR; slot++) {
                slots.add(slot);
            }
            availableSlots.put(floor, slots);
        }
    }

    public static void printBorder() {
        System.out.println("============================================");
    }

    public static void printHeader() {
        printBorder();
        System.out.println("         VEHICLE PARKING SYSTEM            ");
        printBorder();
        System.out.println("Available Slots:");
        for (int floor = 1; floor <= FLOORS; floor++) {
            System.out.printf("Floor %d: %d / %d%n", floor, availableSlots.get(floor).size(), SLOTS_PER_FLOOR);
        }
        printBorder();
    }

    public static void printFooter() {
        printBorder();
        System.out.println("        THANK YOU FOR USING OUR SERVICE    ");
        printBorder();
    }

    public static void checkInVehicle(Scanner scanner) {
        // Find first available slot
        int assignedFloor = -1;
        int assignedSlot = -1;

        for (int floor = 1; floor <= FLOORS; floor++) {
            if (!availableSlots.get(floor).isEmpty()) {
                assignedFloor = floor;
                assignedSlot = availableSlots.get(floor).iterator().next(); // first available
                break;
            }
        }

        if (assignedFloor == -1) {
            System.out.println("\n[ERROR] No parking spots available! Please try later.");
            return;
        }

        System.out.print("Enter Vehicle Number (Alphanumeric): ");
        String vehicleNumber = scanner.nextLine();

        LocalDateTime entryTime = LocalDateTime.now();
        parkedVehicles.put(vehicleNumber, new VehicleInfo(entryTime, assignedFloor, assignedSlot));
        availableSlots.get(assignedFloor).remove(assignedSlot);

        System.out.printf("\n[ENTRY TIME] Vehicle %s entered at: %s%n", vehicleNumber, entryTime);
        System.out.printf("Assigned Floor: %d, Slot: %d%n", assignedFloor, assignedSlot);
    }

    public static void checkOutVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle Number for Checkout: ");
        String vehicleNumber = scanner.nextLine();

        if (!parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("\n[ERROR] Vehicle number not found! Please enter a valid vehicle number.");
            return;
        }

        VehicleInfo info = parkedVehicles.get(vehicleNumber);
        LocalDateTime exitTime = LocalDateTime.now();
        parkedVehicles.remove(vehicleNumber);

        // Free the slot
        availableSlots.get(info.floor).add(info.slot);

        Duration duration = Duration.between(info.entryTime, exitTime);
        double hours = duration.toMinutes() / 60.0;
        double totalCost = hours * RATE_PER_HOUR;

        System.out.printf("\n[EXIT TIME] Vehicle %s exited at: %s%n", vehicleNumber, exitTime);
        System.out.printf("Floor: %d, Slot: %d%n", info.floor, info.slot);
        printBorder();
        System.out.println("        PARKING RECEIPT                   ");
        printBorder();
        System.out.printf("Total duration: %.2f hours%n", hours);
        System.out.printf("Total cost: ₹%.2f%n", totalCost);
        printFooter();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printHeader();
            System.out.println("1. Vehicle Check-in\n2. Vehicle Check-out\n3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    checkInVehicle(scanner);
                    break;
                case 2:
                    checkOutVehicle(scanner);
                    break;
                case 3:
                    System.out.println("Successfully Exited...!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
