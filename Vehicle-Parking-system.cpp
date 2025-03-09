#include <iostream>
#include <ctime>
#include <map>
using namespace std;

const double RATE_PER_HOUR = 50.0;
const int TOTAL_PARKING_SPOTS = 100;
map<string, time_t> parkedVehicles; // Store vehicle number and entry time

void printBorder() {
    cout << "============================================" << endl;
}

void printHeader() {
    printBorder();
    cout << "         VEHICLE PARKING SYSTEM            " << endl;
    printBorder();
    cout << "Available Parking Spots: " << availableSpots << " / " << TOTAL_PARKING_SPOTS << endl;
    printBorder();
}

void printFooter() {
    printBorder();
    cout << "        THANK YOU FOR USING OUR SERVICE    " << endl;
    printBorder();
}

void checkInVehicle() {
    if (availableSpots <= 0) {
        cout << "\n[ERROR] No parking spots available! Please try later." << endl;
        return;
    }
    
    string vehicleNumber;
    cout << "Enter Vehicle Number (Alphanumeric): ";
    cin >> vehicleNumber;
    
    time_t entryTime = time(0);
    parkedVehicles[vehicleNumber] = entryTime;
    availableSpots--;
    
    cout << "\n[ENTRY TIME] Vehicle " << vehicleNumber << " entered at: " << ctime(&entryTime);
    printHeader();
}

void checkOutVehicle() {
    string vehicleNumber;
    cout << "Enter Vehicle Number for Checkout: ";
    cin >> vehicleNumber;
    
    if (parkedVehicles.find(vehicleNumber) == parkedVehicles.end()) {
        cout << "\n[ERROR] Vehicle number not found! Please enter a valid vehicle number." << endl;
        return;
    }
    
    time_t exitTime = time(0);
    time_t entryTime = parkedVehicles[vehicleNumber];
    parkedVehicles.erase(vehicleNumber);
    availableSpots++;
    
    double duration = difftime(exitTime, entryTime) / 3600.0;
    double totalCost = duration * RATE_PER_HOUR;
    
    cout << "\n[EXIT TIME] Vehicle " << vehicleNumber << " exited at: " << ctime(&exitTime);
    printBorder();
    cout << "        PARKING RECEIPT                   " << endl;
    printBorder();
    cout << "Total duration: " << duration << " hours" << endl;
    cout << "Total cost: ₹" << totalCost << endl;
    printFooter();
}

int main() {
    int choice;
    do {
        printHeader();
        cout << "1. Vehicle Check-in\n2. Vehicle Check-out\n3. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;
        
        switch (choice) {
            case 1:
                checkInVehicle();
                break;
            case 2:
                checkOutVehicle();
                break;
            case 3:
                cout << "Exiting system...\n";
                break;
            default:
                cout << "Invalid choice! Please enter again." << endl;
        }
    } while (choice != 3);
    
    return 0;
}
