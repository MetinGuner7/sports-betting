# Sports Betting Application Project

This project is a modern Android application that offers users the ability to bet on various sports events. It is developed with a fully declarative UI using Jetpack Compose and follows modern Android development principles.

## ✨ Key Features

* User login and registration
* Bulletin view listing sports events and odds
* Event details and market/odds options
* Bet slip creation and management
* Dynamic theming and modern user interface
* Fluent page transition animations
* Firebase Analytics integration

## 🛠️ Technologies and Libraries

* **Programming Language:** Kotlin
* **UI:** Jetpack Compose
* **Architectural Pattern:** MVVM (Model-View-ViewModel) and Clean Architecture principles
* **Asynchronous Programming:** Kotlin Coroutines & Flow
* **Dependency Injection:** Hilt
* **Navigation:** Jetpack Navigation Compose
* **Data Storage (Local):** Jetpack DataStore (Proto DataStore preferred)
* **Network Requests:** Retrofit & OkHttp (If API integration exists in the project)
* **Analytics:** Firebase Analytics
* **Image Loading:** Coil (If used)
* **Testing:** JUnit, Mockito, Turbine (for Flow tests), Compose UI Tests

## 🏛️ Architectural Approach

The project adopts a layered architecture and the MVVM design pattern to provide a scalable, testable, and maintainable structure. It is inspired by Clean Architecture principles.

### Layers:

1.  **UI Layer (`presentation`):**
    * Contains screens (Composable functions) and UI components built with Jetpack Compose.
    * Receives user interactions and forwards them to the ViewModel.
    * Updates the interface by observing the UI state from the ViewModel.
    * Manages navigation logic.
    * **Main Components:**
        * `Activity` (Usually a single `MainActivity`)
        * `Composable` Screens (e.g., `LoginScreen`, `BulletinListScreen`, `BasketScreen`)
        * `ViewModel`s (e.g., `LoginViewModel`, `BulletinViewModel`, `BasketViewModel`)
        * `NavHost` and navigation graph definitions

2.  **Domain Layer (Optional but recommended, via UseCases):**
    * Contains the application's business logic. It is independent of the UI and Data layers.
    * Performs specific functions through `UseCase` (Interactor) classes.
    * Orchestrates Repositories.
    * Defines domain models.
    * **Main Components:**
        * `UseCase`s (e.g., `GetSportsUseCase`, `AddBetToBasketUseCase`, `LoginUserUseCase`)
        * Domain data models (Platform-independent pure Kotlin classes)

3.  **Data Layer:**
    * Manages and abstracts data sources (local and remote).
    * Uses the `Repository` pattern.
    * Contains data models (DTOs, Proto models, Entities).
    * **Main Components:**
        * `Repository` interfaces and implementations (e.g., `AuthRepository`, `BulletinRepository`, `BasketRepository`)
        * `DataSource`s:
            * `LocalDataSource` (e.g., using DataStore or Room DAO)
            * `RemoteDataSource` (e.g., using Retrofit service interfaces)
        * Data mapping classes (for converting DTOs to domain models or vice versa).

## 📂 Project Structure (Modules and Packages)

The project uses a mix of feature-based and layer-based packaging strategies.

```
.
├── app (Main application module)
│   ├── src/main/java/com/sports/betting
│   │   ├── MainActivity.kt
│   │   ├── MainApplication.kt (HiltApplication)
│   │   ├── di (Hilt modules - App level)
│   │   ├── navigation (NavHost and route definitions)
│   │   ├── ui (General UI themes, MainApp Composable)
│   │
│   ├── auth (Authentication feature)
│   │   ├── di (Auth-related Hilt modules)
│   │   ├── data (AuthRepository, AuthLocalDataSource, AuthRemoteDataSource)
│   │   ├── domain (LoginUseCase, RegisterUseCase, AuthDomainModel)
│   │   └── ui
│   │       ├── login (LoginScreen, LoginViewModel)
│   │       └── register (RegisterScreen, RegisterViewModel)
│   │
│   ├── bulletin (Bulletin feature)
│   │   ├── di
│   │   ├── data
│   │   ├── domain
│   │   └── ui
│   │       ├── list (BulletinListScreen, BulletinListViewModel)
│   │       └── detail (BulletinDetailScreen, BulletinDetailViewModel)
│   │
│   ├── basket (Basket feature)
│   │   ├── di
│   │   ├── data (BasketRepository, BasketLocalDataSource - DataStore)
│   │   ├── domain (GetBasketItemsUseCase, AddBetToBasketUseCase)
│   │   └── ui (BasketScreen, BasketViewModel, component)
│   │
│   ├── splash (Splash screen feature)
│   │   └── ui (SplashScreen, SplashViewModel)
│   │
│   ├── common (Code shared by all modules/features)
│   │   ├── base (BaseViewModel, BaseUseCase, Route interface, etc.)
│   │   ├── model (General data models)
│   │   └── util (Utility functions, constants)
│   │
│   ├── designsystem (The application's design system)
│   │   ├── theme (Color.kt, Theme.kt, Type.kt, Shape.kt)
│   │   ├── component (Generic UI components like AppButton, AppTextField)
│   │   ├── icons (Custom icons)
│   │   └── animations (Transition animations)
│   │
│   ├── datastore (Code related to DataStore or local database)
│   │   ├── di (DataStore Hilt module)
│   │   ├── model (Proto models or Entities)
│   │   └── usecase (DataStore-specific UseCases - e.g., SaveUserPreferencesUseCase)
│   │
│   └── analytics (Analytics-related code)
│       ├── di (Analytics Hilt module)
│       └── AnalyticsHelper.kt, AnalyticsEvent.kt
│
└── build.gradle.kts (Project level)
└── app/build.gradle.kts (Application module level)
```

## 🔄 Important Flows

* **User Login:** `LoginScreen` -> `LoginViewModel` -> `LoginUserUseCase` -> `AuthRepository` -> (Remote/Local DataSource). Redirect to the main screen (Bulletin) upon successful login.
* **Bulletin Listing:** `BulletinListScreen` -> `BulletinListViewModel` -> `GetSportsUseCase` -> `BulletinRepository`.
* **Adding Bet to Basket:** `BulletinDetailScreen` (or `BulletinListScreen`) -> `BulletinDetailViewModel` -> `AddBetToBasketUseCase` (or conflict resolution logic within ViewModel) -> `BasketRepository` -> `BasketLocalDataSource`. Basket updates are reflected in the UI via `Flow`.
* **Page Transitions:** Performed via `NavHost` and defined routes, using custom animations defined in `designsystem.animations`.

## 🚀 Setup and Running

1.  Clone the project: `git clone <repository_url>`
2.  Open the latest stable version of Android Studio.
3.  Open the project with Android Studio.
4.  Ensure necessary SDKs and tools are installed.
5.  Create a Firebase project and add the `google-services.json` file to the `app/` directory (for Firebase Analytics and other Firebase services).
6.  Build and run the project on an emulator or a physical device.

## 🎨 Design System (`designsystem`)

The application has a centralized design system to ensure a consistent look and feel. This package includes:
* **Theme:** Colors, typography, shapes (built on `MaterialTheme`).
* **Components:** Custom `Composable` components used 앱-wide (e.g., `AppButton`, `AppTextField`).
* **Icons:** Custom SVG or vector icons.
* **Animations:** Predefined animation sets for page transitions and other UI interactions.

## 📈 Analytics (`analytics`)

Firebase Analytics integration is implemented to track user behavior and application performance. Standardized event dispatching is provided through the `AnalyticsHelper` interface and its implementations.

---

This README file summarizes the basic structure and architecture of the project. For more detailed information, refer to the in-code documentation in the respective packages and files.
