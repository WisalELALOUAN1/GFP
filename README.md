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
* Java 17+
* Android SDK Platform 35 or above

### Setup Steps

```bash
git clone https://github.com/WisalELALOUAN1/GFP
cd GFP
```

1. Open the project in Android Studio.
2. Add your `google-services` for Firebase integration.
3. Run the app on an emulator or a physical device.


You can directly install the application on any Android device by downloading the latest release:

➡️ Download app-release.apk

This APK contains the full application build, including Firebase authentication, offline storage with Realm, and AI-based budget prediction.

---

##  Application Screens
Below is a visual walkthrough of the key screens that illustrate the user journey within DirhamWay, from authentication to budget prediction, allowing users to efficiently manage and track their personal finances.
* **Login/Register Screens** (Email or Google)
Allow users to securely authenticate using either their email and password or their Google account, ensuring flexibility and ease of access.
    <p align="center">
  <img src="/Screens/login .png" alt="Login Screen" width="200"/>   </p>
  <p align="center">
  <img src="/Screens/signup.png.jpg" alt="Signup Screen" width="200"/> </p>

* **Dashboard** ( total balance, latest transactions)
Presents a summary of the user's financial status, including current balance and a quick view of recent transactions for better tracking
<p align="center">
<img src="/Screens/dashboard.jpg" alt="Dashboard" width="200"/> </p>

* **Add Transaction** (Form with category, amount, type)
Enables users to add new income or expense entries by specifying the description, amount, type (credit/debit), and category.
<p align="center">
  <img src="/Screens/transactions.jpg" alt="Add Transaction" width="200"/> </p>

* **List of Goals** (Set target amount, current savings, deadline)
Displays all user-defined financial goals, allowing them to view progress and manage targets with deadlines and saved amounts.
<p align="center">
  <img src="/Screens/listeObjective.jpg" alt="Goals List" width="200"/> </p>


* **Report Screen** (PDF export of all goals and transactions)
Provides detailed financial summaries and allows exporting complete reports of goals and transaction history in PDF format.
<p align="center">
  <img src="/Screens/reportInterface.jpg" alt="Report Screen" width="200"/> </p>

* **Prediction Screen** <p align="center">
Uses a machine learning model to suggest an optimized distribution of the user's monthly budget across selected spending categories.
  <img src="/Screens/prediction.png" alt="Prediction Screen" width="200"/> </p>

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




