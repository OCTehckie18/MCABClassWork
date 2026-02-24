# MCABClassWork

A comprehensive Android application built with Jetpack Compose that serves as a portfolio for various laboratory and classroom activities. This app features a centralized navigation system to access different sub-applications, each representing a specific assignment or project.

## 🚀 Features

### Navigation Hub
- **Home Screen**: A central dashboard providing easy access to all implemented labs and activities.
- **Dynamic Routing**: Uses `Navigation Compose` for seamless transitions between different modules.

### Laboratory Works
- **Lab 0: Treasure Map Game**
  - Simple game logic demonstrating state management and random values.
- **Lab 1: Birthday Greeting App**
  - Interactive user interface to input names and generate custom greetings.
- **Lab 2: Calculator App**
  - Basic calculator implementation.
- **Lab 3: Campus Connect App**
  - A multi-screen application featuring departments, notifications, profile, and event details.

### In-Class Activities & Demos
- **Activity Lifecycle Demo**: Visualization of Android activity lifecycle states and splash screen.
- **Navigation with Parameters**: Demonstration of passing data between screens.
- **Dashboard & Profile/Settings**: UI layout and navigation stack management (PopStack).
- **Shopping Cart**: A UI implementation of a product listing and cart system.
- **ViewModel Demo**: Multi-ViewModel screen implementation.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Navigation**: Compose Navigation
- **Architecture**: Modern Android Architecture patterns (MVVM with ViewModels)
- **Build System**: Gradle (Kotlin DSL)

## 📁 Project Structure

```text
app/src/main/java/com/example/mcabclasswork/
├── mainframe/               # Main application entry and core screens
│   ├── MainActivity.kt      # Main Entry point with NavHost
│   ├── Routes.kt            # Navigation route constants
│   ├── HomeScreen_Main.kt   # Main landing screen
│   ├── Labs.kt              # Labs listing screen
│   └── ClassActivities.kt   # Class activities listing screen
├── Labs/                    # Laboratory assignments
│   ├── Lab0_MapGame.kt
│   ├── Lab1_BirthdayGreeting.kt
│   ├── Lab2_Calculator.kt
│   └── Lab3_CampusConnectApp/
└── Misc/                    # In-class exercises and demos
    ├── ShoppingCart/
    ├── ViewModalDemo/
    └── inclass27012026/     # Dashboard, Profile, Settings
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
- `androidx.lifecycle:lifecycle-viewmodel-compose`

---
*Created as part of the MCAB course curriculum.*
