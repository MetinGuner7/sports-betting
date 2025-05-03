# Sports Betting App - Case Study

This project is a case study developed for [Company Name]. It aims to demonstrate a simple sports betting application utilizing a free betting API (The Odds API v4).

## ✨ Key Features

* **Bet Bulletin:**
    * List sports events.
    * Search within events.
    * View event details and associated odds.
* **Bet Basket:**
    * Add selected bets (odds) to the basket.
    * Remove bets from the basket.
    * Display the total number of events and the combined odds in the basket.
* **Authentication:** Firebase Authentication (e.g., Email/Password or Anonymous).
* **Analytics:** Tracking key user actions with Firebase Analytics (View Match Detail, Add to Basket, Remove from Basket).

## 🛠️ Tech Stack & Architecture

* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Architecture:** MVVM + Clean Architecture Principles
* **Asynchronous Programming:** Kotlin Coroutines & Flow
* **Dependency Injection:** Hilt
* **Networking:** Retrofit & Moshi
* **API:** The Odds API v4 (Free Tier)
* **Build System:** Gradle (Kotlin DSL) + Convention Plugins (`build-logic`)
* **Authentication:** Firebase Authentication
* **Analytics:** Firebase Analytics

## 🏗️ Project Modules

* `:app`: The main application module, responsible for UI layer entry points and navigation graph.
* `:core:common`: Contains base classes, interfaces, utilities, DI modules shared across the project (e.g., BaseViewModel, Resource wrapper).
* `:core:data`: Implements the data layer, including Repository implementations, Remote/Local Data Sources, DTOs, Mappers, and network/database setup.
* `:core:domain`: Contains the domain layer, including Use Cases, Domain Models, and Repository interfaces. It's independent of other layers.
* `:feature:bulletin`: (Planned) UI and ViewModel related to the betting bulletin (list, search, detail).
* `:feature:basket`: (Planned) UI and ViewModel related to the betting basket.
* `:feature:auth`: (Planned) UI and ViewModel related to user authentication.
* `build-logic`: Manages the Gradle build configuration using Convention Plugins for consistency and maintainability.

## 🚀 Setup & How to Run

1.  **Clone the repository:** `git clone [REPOSITORY_URL]` (Replace with your actual repository URL)
2.  **Open in Android Studio:** Use a recent version (e.g., Iguana, Jellyfish or later is recommended).
3.  **Gradle Sync:** Wait for the project to sync with Gradle.
4.  **API Key:** This project uses The Odds API. To run the application, you need to obtain your own **free API key** from [https://the-odds-api.com/](https://the-odds-api.com/). Once you have the key, create a file named `local.properties` in the project's root directory (if it doesn't exist) and add the following line:
    ```properties
    # local.properties (Create this file if needed - !! DO NOT COMMIT THIS FILE !!)
    ODDS_API_KEY=YOUR_API_KEY_HERE
    ```
    *(The `local.properties` file should be listed in your `.gitignore` file and must not be committed to version control.)*
5.  **Firebase Setup (Optional for Reviewer):** If the reviewer wants to test Firebase features fully, they might need to connect the project to their own Firebase project. This involves downloading their `google-services.json` file and placing it in the `:app` module's root directory. (You should already have your own `google-services.json` in the project, potentially ignored by Git).
6.  **Build and Run:** Select the `:app` configuration and run the application on an emulator or physical device.

## 📸 Screenshots (Optional)

(Add screenshots or GIFs of the application UI here once the UI is developed)

## ⚠️ Known Issues / Limitations (Optional)

* The free tier of The Odds API has usage limits (e.g., 500 requests per month). Please be mindful of this limit during testing.
* (Add any other relevant notes or limitations here, e.g., "Search functionality is client-side only")