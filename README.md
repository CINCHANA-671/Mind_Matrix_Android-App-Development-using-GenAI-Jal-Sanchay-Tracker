# 💧 Jal-Sanchay Tracker
Rainwater Harvesting Made Measurable

> An Android application that turns rainwater harvesting into a measurable goal by calculating "Water Wealth" from roof area and rainfall data.

---

## 📱 About the App

**Jal-Sanchay Tracker** helps Indian households measure and track their rainwater harvesting efforts. By entering your roof area and daily rainfall, the app calculates exactly how many liters of water you collected — making conservation tangible and motivating.

Many households have rainwater harvesting infrastructure but no way to know if it is effective. Without data, conservation feels abstract. This app solves that problem.

---

## ✨ Features

- **Setup Screen** — Enter roof area (sq.ft), tank capacity (L), and daily household usage once
- **Dashboard** — Shows Liters Saved Today, Total Savings, and tank fill level
- **Water Tank Visual** — A ProgressBar that fills up relative to data entered
- **Impact Score** — Converts liters into "Household Water Days" saved
- **Monthly Report** — Total water collected for the current month with CO₂ savings estimate
- **Tips Section** — 8 practical tips for better rainwater harvesting
- **Input Validation** — Handles non-numeric, negative, blank, and zero inputs gracefully

---

## 🧮 The Water Wealth Formula

    Liters = Roof Area (sq.ft) × Rainfall (mm) × 0.0929 × Runoff Coefficient

| Component | Description |
|---|---|
| Roof Area | Flat catchment area of your roof in square feet |
| Rainfall | Today's rainfall in millimetres |
| 0.0929 | Conversion factor (sq.ft × mm → Litres) |
| Runoff Coefficient | 0.85 for concrete/tile roof (default) |

**Example:** 800 sq.ft × 25mm × 0.0929 × 0.85 = **1,579 Litres collected**

---

## 🏠 Impact Score

    Household Days = Total Litres ÷ Daily Usage (default 540 L/day)

Average family of 4 uses ~135 L/person/day = 540 L/day. The Impact Score tells you how many days of free water your roof just collected.

---

## 🗂️ Project Structure

    com.example.jalsanchay/
    │
    ├── data/
    │   ├── RainfallEntry.kt        # Room @Entity — database table structure
    │   ├── RainfallDao.kt          # @Dao — SQL queries (insert, totals, monthly)
    │   ├── AppDatabase.kt          # Room database singleton
    │   └── RainfallRepository.kt  # Data access layer between UI and DB
    │
    ├── utils/
    │   ├── WaterCalculator.kt      # Core formula + impact score + validation
    │   └── Prefs.kt                # SharedPreferences wrapper for settings
    │
    └── ui/
        ├── SetupActivity.kt        # First-run onboarding screen
        ├── MainActivity.kt         # Dashboard — tank, stats, rainfall entry
        ├── ReportActivity.kt       # Monthly water report
        └── TipsActivity.kt         # Harvesting tips and formula guide

    res/
    ├── layout/
    │   ├── activity_setup.xml
    │   ├── activity_main.xml
    │   ├── activity_report.xml
    │   └── activity_tips.xml
    └── drawable/
        ├── card_bg.xml
        └── card_bg_blue.xml

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Kotlin** | Primary programming language |
| **Android Studio** | IDE (AGP 9.0) |
| **Room Database 2.8.4** | Local SQLite storage for rainfall entries |
| **LiveData 2.10** | Reactive UI updates when database changes |
| **Kotlin Coroutines 1.11** | Background database operations |
| **KSP** | Annotation processing (replaces KAPT) |
| **SharedPreferences** | Persistent storage for user settings |
| **Material Design** | TextInputLayout, styled buttons |

---

## ⚙️ Setup & Installation

### Prerequisites

- Android Studio (AGP 9.0+)
- Android device with API 24+ (Android 7.0 or higher)
- USB cable for physical device deployment

### Steps

1. Clone or download the project
2. Open in Android Studio
3. Sync Gradle — dependencies download automatically
4. Enable USB Debugging on your phone
   - Settings → About Phone → tap Build Number 7 times
   - Settings → Developer Options → USB Debugging ON
   - Settings → Developer Options → Install via USB ON
5. Connect your phone via USB
6. Run the app with Shift + F10

---

## 📦 Dependencies

    implementation("androidx.room:room-runtime:2.8.4")
    ksp("androidx.room:room-compiler:2.8.4")
    implementation("androidx.room:room-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
    implementation("androidx.activity:activity-ktx:1.13.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")

---

## 🗄️ Database Schema

### Table: rainfall_entries

| Column | Type | Description |
|---|---|---|
| id | INTEGER PK | Auto-generated primary key |
| rainfallMm | REAL | Rainfall in millimetres |
| litersCollected | REAL | Calculated litres collected |
| dateMillis | INTEGER | Timestamp in milliseconds |

---

## ✅ Success Criteria

- [x] Water Tank visual fills up relative to data entered
- [x] Monthly report shows total water saved for current month
- [x] Math validated — non-numeric inputs handled via toDoubleOrNull()
- [x] Tips section with 8 practical water harvesting tips included

---

## 📸 App Screens

| Screen | Description |
|---|---|
| **Setup** | Roof area, tank capacity, daily usage — shown once on first launch |
| **Dashboard** | Tank bar, today's litres, total saved, impact score, rainfall entry |
| **Monthly Report** | Total collected, household days covered, CO₂ offset |
| **Tips** | Water Wealth Formula + 8 practical harvesting tips |

---

## 🌍 Social Impact

India faces a critical water crisis with 600 million people under high water stress. Rainwater harvesting at the household level is one of the most effective grassroots interventions — but without measurement, it remains invisible.

Jal-Sanchay Tracker makes household water conservation **visible, measurable, and motivating.**

---

## 📁 Data Storage

| Storage | Location | What it stores |
|---|---|---|
| Room DB | /data/data/com.example.jalsanchay/databases/ | All rainfall entries |
| SharedPrefs | /data/data/com.example.jalsanchay/shared_prefs/ | Settings |

Data persists permanently across app restarts and phone reboots.

---

## 👨‍💻 Developed As Part Of

**MindMatrix Internship Program**
Android App Development using GenAI — Natural Resources Track
CiTech, Bengaluru — Dept. of CSE

---

*Save Water, Water is our Future* 🌱
