# 📱 DirhamWay - Personal Finance Management App

DirhamWay is a mobile application developed with **Java** and **Android Studio** designed to help users manage their personal finances effectively. With features like secure authentication, smart budget recommendations powered by **TensorFlow Lite**, local data persistence using **Realm**, and PDF report generation, the app provides a robust tool for budgeting, expense tracking, and goal setting.

---

##  Table of Contents

* [Features](#features)
* [Architecture](#architecture)
* [Technologies Used](#technologies-used)
* [Installation](#installation)
* [Application Screens](#application-screens)
* [AI Model Integration](#ai-model-integration)
* [Project Structure](#project-structure)
* [Contributors](#contributors)


---

##  Features

*  **Firebase Authentication** (Email & Google Sign-In).
*  **Transaction Management**: Add, edit, and delete income or expense entries.
*  **Savings Goal Management**: Create goals, track progress, and define deadlines.
*  **Interactive Dashboard**: Displays budget distribution, real-time balances, and charts.
*  **AI Budget Recommendations** using a pre-trained TFLite model.
*  **PDF Report Export**: Generates reports including all goals and transactions.
*  **Realm Database**: Ensures offline data storage and fast access.

---

##  Architecture (MVVM)

The **DirhamWay** mobile application is designed using the **Model-View-ViewModel (MVVM)** architecture. This separation of concerns ensures a robust, testable, and scalable codebase. Below is a breakdown of each layer and its role in the project:

---

###  Model (`data/model/`)
**Purpose:** Represents the data structure and business logic of the application.




**Characteristics:**
- All models are stored locally using **Realm**, offering efficient, object-oriented data persistence.

---

###  ViewModel (`viewmodel/`)
**Purpose:** Acts as an intermediary between the `View` and `Model` layers.

**Responsibilities:**
- Exposes observable `LiveData` to the UI.
- Handles business logic such as filtering transactions, computing balances, and managing budget thresholds.
- Manages asynchronous operations like predictions using **TensorFlow Lite** or data retrieval from the **Realm** database.

---

###  View (`ui/`)
**Purpose:** Represents the user interface and handles user interactions.


**Tools Used:**
- **Android Jetpack** libraries such as `ViewModel`, `LiveData`, `RecyclerView`, and `Material Components`.

---

###  Repository (`data/repository/`)
**Purpose:** Abstracts the data access logic from ViewModels.

**Role:**
- Interacts directly with the **Realm database** for CRUD operations.
- Provides clean APIs for retrieving, saving, or updating `Transaction`, `Goal`, and `UserCategory` data.
- Allows easier testing and mocking of data sources.

---

###  Session Management (`data/session/`)
**Purpose:** Manages authentication state and user context throughout the app.

**Features:**
- Stores the authenticated user’s ID locally.
- Ensures session continuity between app restarts.
- Used for filtering user-specific data in all views and repositories.

---

##  Technologies Used

| Technology          | Role                                                  |
| ------------------- | ----------------------------------------------------- |
| **Java**            | Main programming language                             |
| **Android Studio**  | Primary development environment (IDE)                 |
| **Firebase Auth**   | Secure authentication with email and social providers |
| **Realm**           | Lightweight local database for mobile                 |
| **TensorFlow Lite** | Embedded model for real-time budget suggestions       |
| **Gradle**          | Build automation and dependency management            |

---

##  Installation

### Requirements

* Android Studio (version Arctic Fox or later)
* Java 11+
* Android SDK Platform 30 or above

### Setup Steps

```bash
git clone https://github.com/WisalELALOUAN1/GFP
cd GFP
```

1. Open the project in Android Studio.
2. Add your `google-services` for Firebase integration.
3. Run the app on an emulator or a physical device.

---

##  Application Screens

* **Login/Register Screens** (Email or Google)
* **Dashboard** ( total balance, latest transactions)
* **Add Transaction** (Form with category, amount, type)
* **Define Goal** (Set target amount, current savings, deadline)
* **Report Screen** (PDF export of all goals and transactions)

---

##  AI Model Integration

We use **TensorFlow Lite** to embed a trained model for predicting budget allocations. The model:

* Takes input parameters like category ID, current month, and day of week.
* Outputs estimated allocation per category.
* Normalizes inputs using MinMax scaling for better accuracy.
* Provides suggestions based on previous spending behavior.

Model file: `budget_model.tflite`

---

##  Project Structure

```text
com.example.gfp
│
├── ui/                        # Activities and UI logic
│   ├── LoginActivity.java
│   ├── DashboardActivity.java
│   ├── AnalyseActivity.java
│   └── ...
|   
│
├── viewmodel/                # MVVM ViewModels
│   └── UserViewModel.java
│   └── ...
│
├── data/
│   ├── model/                # Realm models
│   ├── repository/           # Data access layer
│   └── session/              # SessionManager.java
│
├── utils/                    # PdfExporter, TFLitePredictor
├── res/                      # Layouts, drawables, styles
└── budget_model.tflite       # AI model file
```

---

##  Contributors

* **KAWTAR TAIK**
* **HANANE JAMYL**
* **KHAOULA OUAZRI**
* **WISAL EL ALOUAN**


---




