# Vehicle Parking System 🚗🅿️

This project is a **multi-language implementation** of a **Vehicle Parking Management System**. It allows vehicle check-in/check-out, calculates parking fees, tracks availability, and maintains history. The system has been developed as part of an academic project(C++) and casual learning initiative(java & Python).

---

## 🔧 Languages Used

- ✅ **C++** – Console-based, file-structure for data persistence.
- ✅ **Java** – Console-based, basic logic implementation(can use javafx for GUI).
- ✅ **Python** – GUI-based using `Tkinter` for enhanced user interaction (current version).

---

## 📌 Features (Python GUI Version)

- Check-in vehicles with vehicle number
- Check-out with automatic fee calculation (`₹50/hour`)
- Real-time parking spot availability (`100` total spots, can be increased)
- Track and display parked vehicles
- View full **session history** of all checked-out vehicles
- **No database** required (in-memory only)

---

## 🖥️ Tech Stack

- **Python 3.11+**
- `tkinter` (for GUI)
- `time` module for tracking duration
- Single-file standalone GUI app

---

## 🚀 How to Run the Python Project

1. Install Python (recommended: **3.11 or earlier**, avoid 3.13+ for now)
2. Save the file as `anyname.py`
3. Open terminal or command prompt:
    ```bash
    python gui.py
    ```
4. Use the GUI to:
   - Enter vehicle number and check-in
   - Check out the same vehicle to calculate duration & cost
   - View current status and full history

---

## 📘 Notes

- **C++ Version**: Uses file I/O to maintain basic record structure; great for academic-style submission.
- **Java Version**: Console-based simulation; written for practice and comparison.
- **Python Version**: Most user-friendly with Tkinter GUI; does not persist history after closing (can be extended easily).

---

## ✅ Future Scope

- Add database (SQLite or CSV) for permanent record
- Export history as PDF or CSV
- Login system for admin/staff
- Slot-based visual parking map (GUI)

