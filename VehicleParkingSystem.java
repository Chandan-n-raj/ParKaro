import java.util.*;
import java.time.*;

public class VehicleParkingSystem {

    private static final double RATE_PER_HOUR = 50.0;
    private static final int TOTAL_PARKING_SPOTS = 100;
    private static int availableSpots = 100;
    private static Map<String, LocalDateTime> parkedVehicles = new HashMap<>();

    public static void printBorder() {
        System.out.println("============================================");
    }

    public static void printHeader() {
        printBorder();
        System.out.println("         VEHICLE PARKING SYSTEM            ");
        printBorder();
        System.out.println("Available Parking Spots: " + availableSpots + " / " + TOTAL_PARKING_SPOTS);
        printBorder();
    }

    public static void printFooter() {
        printBorder();
        System.out.println("        THANK YOU FOR USING OUR SERVICE    ");
        printBorder();
    }

    public static void checkInVehicle(Scanner scanner) {
        if (availableSpots <= 0) {
            System.out.println("\n[ERROR] No parking spots available! Please try later.");
            return;
        }

        System.out.print("Enter Vehicle Number (Alphanumeric): ");
        String vehicleNumber = scanner.nextLine();

        LocalDateTime entryTime = LocalDateTime.now();
        parkedVehicles.put(vehicleNumber, entryTime);
        availableSpots--;

        System.out.println("\n[ENTRY TIME] Vehicle " + vehicleNumber + " entered at: " + entryTime);
        printHeader();
    }

    public static void checkOutVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle Number for Checkout: ");
        String vehicleNumber = scanner.nextLine();

        if (!parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("\n[ERROR] Vehicle number not found! Please enter a valid vehicle number.");
            return;
        }

        LocalDateTime exitTime = LocalDateTime.now();
        LocalDateTime entryTime = parkedVehicles.get(vehicleNumber);
        parkedVehicles.remove(vehicleNumber);
        availableSpots++;

        Duration duration = Duration.between(entryTime, exitTime);
        double hours = duration.toMinutes() / 60.0;
        double totalCost = hours * RATE_PER_HOUR;

        System.out.println("\n[EXIT TIME] Vehicle " + vehicleNumber + " exited at: " + exitTime);
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
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
