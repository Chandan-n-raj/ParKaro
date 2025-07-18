import tkinter as tk
from tkinter import messagebox, Toplevel, Scrollbar, Text
import time

# Constants
RATE_PER_HOUR = 50.0
TOTAL_PARKING_SPOTS = 100

# Parking System Logic
class ParkingSystem:
    def __init__(self):
        self.available_spots = TOTAL_PARKING_SPOTS
        self.parked_vehicles = {}  # vehicleNumber -> entryTime
        self.history = []  # List of tuples: (vehicleNumber, entryTime, exitTime, cost)

    def check_in(self, vehicle_number):
        if self.available_spots <= 0:
            return False, "No parking spots available."

        if vehicle_number in self.parked_vehicles:
            return False, "Vehicle is already parked."

        entry_time = time.time()
        self.parked_vehicles[vehicle_number] = entry_time
        self.available_spots -= 1
        return True, entry_time

    def check_out(self, vehicle_number):
        if vehicle_number not in self.parked_vehicles:
            return False, "Vehicle not found."

        entry_time = self.parked_vehicles[vehicle_number]
        exit_time = time.time()
        duration = (exit_time - entry_time) / 3600  # in hours
        cost = round(duration * RATE_PER_HOUR, 2)

        # Save to history
        self.history.append((vehicle_number, entry_time, exit_time, cost))

        del self.parked_vehicles[vehicle_number]
        self.available_spots += 1
        return True, (entry_time, exit_time, cost)

    def get_status(self):
        return self.available_spots, self.parked_vehicles.keys()

    def get_history(self):
        return self.history

# GUI
class ParkingApp:
    def __init__(self, root):
        self.root = root
        self.system = ParkingSystem()

        self.root.title("Vehicle Parking System")
        self.root.geometry("500x400")

        self.title_label = tk.Label(root, text="Vehicle Parking System", font=("Arial", 16, "bold"))
        self.title_label.pack(pady=10)

        # Check-in
        self.checkin_frame = tk.LabelFrame(root, text="Check-in Vehicle")
        self.checkin_frame.pack(pady=10, fill="x", padx=20)

        tk.Label(self.checkin_frame, text="Vehicle Number:").pack(side="left", padx=10)
        self.checkin_entry = tk.Entry(self.checkin_frame)
        self.checkin_entry.pack(side="left", padx=10)
        self.checkin_btn = tk.Button(self.checkin_frame, text="Check In", command=self.check_in)
        self.checkin_btn.pack(side="left", padx=10)

        # Check-out
        self.checkout_frame = tk.LabelFrame(root, text="Check-out Vehicle")
        self.checkout_frame.pack(pady=10, fill="x", padx=20)

        tk.Label(self.checkout_frame, text="Vehicle Number:").pack(side="left", padx=10)
        self.checkout_entry = tk.Entry(self.checkout_frame)
        self.checkout_entry.pack(side="left", padx=10)
        self.checkout_btn = tk.Button(self.checkout_frame, text="Check Out", command=self.check_out)
        self.checkout_btn.pack(side="left", padx=10)

        # Status display
        self.status_label = tk.Label(root, text="", fg="blue", font=("Arial", 12))
        self.status_label.pack(pady=10)

        # Buttons
        self.refresh_btn = tk.Button(root, text="Refresh Status", command=self.update_status)
        self.refresh_btn.pack(pady=5)

        self.history_btn = tk.Button(root, text="View Parking History", command=self.show_history)
        self.history_btn.pack(pady=5)

        self.quit_btn = tk.Button(root, text="Exit", command=root.quit)
        self.quit_btn.pack(pady=10)

        self.update_status()

    def check_in(self):
        number = self.checkin_entry.get().strip().upper()
        if not number:
            messagebox.showwarning("Input Error", "Please enter a vehicle number.")
            return

        success, result = self.system.check_in(number)
        if success:
            t = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(result))
            messagebox.showinfo("Check-In Success", f"Vehicle {number} checked in at {t}")
        else:
            messagebox.showerror("Check-In Failed", result)

        self.checkin_entry.delete(0, tk.END)
        self.update_status()

    def check_out(self):
        number = self.checkout_entry.get().strip().upper()
        if not number:
            messagebox.showwarning("Input Error", "Please enter a vehicle number.")
            return

        success, result = self.system.check_out(number)
        if success:
            entry, exit, cost = result
            t_in = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(entry))
            t_out = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(exit))
            duration = round((exit - entry) / 3600, 2)
            messagebox.showinfo("Check-Out Success",
                f"Vehicle: {number}\nTime In: {t_in}\nTime Out: {t_out}\nDuration: {duration} hrs\nCost: ₹{cost}")
        else:
            messagebox.showerror("Check-Out Failed", result)

        self.checkout_entry.delete(0, tk.END)
        self.update_status()

    def update_status(self):
        available, parked = self.system.get_status()
        self.status_label.config(text=f"Available Spots: {available} / {TOTAL_PARKING_SPOTS}\n"
                                      f"Parked Vehicles: {', '.join(parked) if parked else 'None'}")

    def show_history(self):
        history = self.system.get_history()
        win = Toplevel(self.root)
        win.title("Parking History")
        win.geometry("500x400")

        text = Text(win, wrap="word")
        text.pack(fill="both", expand=True)

        scroll = Scrollbar(text)
        scroll.pack(side="right", fill="y")
        text.config(yscrollcommand=scroll.set)
        scroll.config(command=text.yview)

        if not history:
            text.insert(tk.END, "No vehicles checked out yet.")
        else:
            for i, (num, entry, exit, cost) in enumerate(history, 1):
                t_in = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(entry))
                t_out = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(exit))
                duration = round((exit - entry) / 3600, 2)
                text.insert(tk.END, f"{i}. {num} | In: {t_in} | Out: {t_out} | Duration: {duration} hrs | ₹{cost}\n")

# Run the app
if __name__ == "__main__":
    root = tk.Tk()
    app = ParkingApp(root)
    root.mainloop()
