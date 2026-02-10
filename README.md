# MCABClassWork

A comprehensive Android application built with Jetpack Compose that serves as a portfolio for various laboratory and classroom activities. This app features a centralized navigation system to access different sub-applications, each representing a specific assignment or project.

## 🚀 Features

### Navigation Hub
- **Home Screen**: A central dashboard providing easy access to all implemented labs and activities.
- **Dynamic Routing**: Uses `Navigation Compose` for seamless transitions between different modules.

### Laboratory Works
- **Lab 1: Birthday Greeting App**
  - Interactive user interface to input names.
  - Custom birthday greeting generation with background imagery.
  - Demonstrates state management and layout composables.
- **Lab 2: Calculator App**
  - Integrated calculator functionality (In Progress).

### In-Class Activities
- **Activity Lifecycle Demo**: Visualization of Android activity lifecycle states.
- **Dashboard Page**: demonstrating UI layout and navigation stack management.
- **Shopping Cart**: A UI implementation of a product listing and cart system.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Navigation**: Compose Navigation
- **Architecture**: Modern Android Architecture patterns.
- **Build System**: Gradle (Kotlin DSL)

## 📁 Project Structure

```text
app/src/main/java/com/example/mcabclasswork/
├── MainActivity.kt          # Main Entry point with NavHost
├── Routes.kt                # Navigation route constants
├── HomeScreen.kt            # Main landing screen
├── Lab1_BirthdayGreeting.kt # Birthday app implementation
├── Lab2_Calculator.kt       # Calculator implementation
└── mainframe/
    └── ClassActivities.kt   # Directory for in-class exercises
```

## ⚙️ Getting Started

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   ```
2. **Open in Android Studio**:
   - Ensure you have the latest version of Android Studio (Ladybug or newer recommended).
3. **Sync Project with Gradle Files**.
4. **Run the app**:
   - Select a physical device or emulator and click 'Run'.

## 📝 Dependencies

The project utilizes several modern Android libraries:
- `androidx.compose.ui:ui`
- `androidx.compose.material3:material3`
- `androidx.navigation:navigation-compose`
- `androidx.activity:activity-compose`

---
*Created as part of the MCAB course curriculum.*
