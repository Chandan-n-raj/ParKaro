#include <iostream>
#include <ctime>
using namespace std;

const double RATE_PER_HOUR = 50.0;
const int TOTAL_PARKING_SPOTS = 100;
int availableSpots = 100;

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

void getCheckoutTime(int &hh, int &mm, int &ss) {
    cout << "\n--------------------------------------------" << endl;
    cout << "Enter checkout time (HH MM SS): ";
    cin >> hh >> mm >> ss;
    cout << "--------------------------------------------\n";
}

int main() {
    if (availableSpots <= 0) {
        cout << "\n[ERROR] No parking spots available! Please try later." << endl;
        return 1;
    }
    
    time_t entryTime, exitTime;
    struct tm exitInfo;
    int hh, mm, ss;
    
    printHeader();
    
    // Get current time as entry time
    entryTime = time(0);
    cout << "\n[ENTRY TIME] Vehicle entered at: " << ctime(&entryTime);
    availableSpots--; // Decrement available spots after entry
    
    printHeader(); // Show updated spots after entry
    
    // Get checkout time from user
    getCheckoutTime(hh, mm, ss);
    
    // Set checkout time
    exitInfo = *localtime(&entryTime);
    exitInfo.tm_hour = hh;
    exitInfo.tm_min = mm;
    exitInfo.tm_sec = ss;
    exitTime = mktime(&exitInfo);
    
    cout << "\n[EXIT TIME] Vehicle exited at: " << ctime(&exitTime);
    availableSpots++; // Increment available spots after exit
    
    printHeader(); // Show updated spots after exit
    
    // Calculate duration and cost
    double duration = difftime(exitTime, entryTime) / 3600.0;
    if (duration < 0) {
        cout << "\n[ERROR] Invalid checkout time! Please enter a valid time." << endl;
        printBorder();
        return 1;
    }
    
    printBorder();
    cout << "        PARKING RECEIPT                   " << endl;
    printBorder();
    cout << "Total duration: " << duration << " hours" << endl;
    cout << "Total cost: ₹" << duration * RATE_PER_HOUR << endl;
    printFooter();
    
    return 0;
}
