# 🚗 ParkTrack 🅿️

<b>ParkTrack</b> is a Java-based Vehicle Parking Management System built using Maven.
It was developed by adapting my older console-based parking system code and enhancing it with Maven build management, CI/CD automation, and Docker support.

### 📜 Project Overview
---
Vehicle Check-in / Check-out with slot tracking

Automatic Fee Calculation (can be customized)

Real-time Slot Availability display

Console-based Java Implementation for simplicity and portability

CI/CD Integration to ensure code is always tested before merging to main

Dockerfile added to containerize the application for easy deployment (currently not hosted on Docker Hub)


### 🛠️ Tech Stack
---
Java 17 (Temurin)

Maven (Project Build & Dependency Management)

JUnit (Testing)

GitHub Actions (CI/CD Pipeline)

Docker (Containerization)

### 🔧 Features Implemented
---
✅ Adapted from old console-based Java version<br>
✅ Maven-based project structure<br>
✅ CI/CD pipeline that:<br>
 -Builds the project<br>
    -Runs automated tests on every push to main<br>
    -Sends email alerts about pipeline status<br>
✅ Dockerfile created for building a ready-to-run container image<br>
✅ Image not pushed to Docker Hub — ready for local build and hosting when needed<br>

### 📬 CI/CD Details
---
Workflow Trigger: On every push or PR to main

Actions Performed:<br>
    -Checkout code<br>
    -Set up Java environment<br>
-Build with Maven<br>
-Run tests<br>
-Send email notification of success/failure<br>
-Ensures code quality and prevents broken builds from reaching production.<br>

### 🔮 Future Scope
---
Host Docker image on Docker Hub

Add REST API layer to the parking system

Integrate with a database for persistent storage

Create a basic web UI for interaction

