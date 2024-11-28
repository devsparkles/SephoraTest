Certainly! Below is a documentation draft for your repository, based on the details you've provided.

---

# SephoraTest Android Application

## Overview

**SephoraTest** is an Android application designed as part of a technical test for Sephora. The app demonstrates a clean architecture implementation, utilizing modern Android development patterns such as `StateFlow` and `RecyclerView`. This application fetches and displays a list of products along with their reviews. The product and review data are fetched from repositories and presented to the user in a `RecyclerView`. Additionally, the app uses **StateFlow** for state management in the UI layer, ensuring a reactive, lifecycle-aware approach to updates.

## Architecture

The app follows **Clean Architecture** principles to ensure that different layers of the application are well-separated and maintainable. Below is an overview of the different components used in the architecture:

### Layers:
1. **Presentation Layer**: 
   - The UI layer consists of an `Activity` or `Fragment` that holds a `RecyclerView` to display the list of products with reviews.
   - StateFlow is used to manage the UI state reactively.
   - The `ViewModel` observes state changes and updates the UI.

2. **Domain Layer**:
   - The business logic is contained within the **UseCases**. Each UseCase is responsible for a single action or use case, such as fetching products and reviews.
   - A notable point is that some UseCases are currently too large and are performing multiple responsibilities. This can be improved to adhere more strictly to the **Single Responsibility Principle (SRP)**, as some tasks (like calculating the average rating) should ideally be handled in separate classes or functions.

3. **Data Layer**:
   - The repositories fetch data from the network or cache. This includes `RemoteProductRepository` and `RemoteReviewRepository`, which are responsible for interacting with APIs to fetch product and review data.
   - The data layer returns a custom `Result` type, which encapsulates both success and error cases.

### StateFlow

StateFlow is used in the app to hold and manage the UI state. This approach ensures that the UI is always consistent with the data state, reducing the need for manual updates or handling of lifecycle events.

```kotlin
private val _state = MutableStateFlow<State>(State.Loading)
val state: StateFlow<State> = _state
```

The state flow is observed by the UI components and automatically updates when the data changes. This ensures a seamless experience for the user.

---

## Key Features

1. **Fetch Products and Reviews**:
   - The application retrieves a list of products and their corresponding reviews from remote repositories.
   - The app combines product and review data into a single object (`ProductWithReviews`), which is then displayed in a `RecyclerView`.

2. **Average Rating Calculation**:
   - The app calculates the average rating of each product based on the reviews associated with it.
   - This logic is currently encapsulated within the `GetProductsWithReviewsUseCase`, but ideally, it should be split into a dedicated class for single responsibility.

3. **Clean Architecture Implementation**:
   - The application follows a clean architecture approach, where the UI layer is decoupled from the business logic and data access layers.
   - Dependencies are injected into use cases, and repositories abstract the data source (e.g., API, database).

---

## Current Limitations

- **Large UseCase**: The `GetProductsWithReviewsUseCase` class currently handles too many responsibilities. It calculates the average rating of reviews and combines product and review data, which violates the **Single Responsibility Principle** (SRP). The average rating calculation and the data combining logic should be split into separate use cases or helper classes to better adhere to SOLID principles.
  
---

## Technologies Used

- **StateFlow**: Used for managing UI state reactively and efficiently.
- **Clean Architecture**: The app follows the Clean Architecture principles, separating concerns into different layers.
- **RecyclerView**: Used to display a dynamic list of products with reviews in the UI.
- **Kotlin**: The app is written in Kotlin, leveraging modern language features such as coroutines and extensions.
- **MVVM**: The app uses the Model-View-ViewModel (MVVM) pattern to separate the UI logic from the business logic.

---

## Setup Instructions

1. **Clone the Repository**:
   - Clone this repository to your local machine.

   ```bash
   git clone https://github.com/devsparkles/SephoraTest.git
   ```

2. **Open the Project**:
   - Open the project in Android Studio.

3. **Install Dependencies**:
   - Ensure that all dependencies are installed by syncing the project with Gradle.

   ```bash
   ./gradlew build
   ```

4. **Run the Application**:
   - Select your desired device or emulator and run the application.

---

## Possible Improvements

1. **Refactor Large UseCase**: 
   - Refactor the `GetProductsWithReviewsUseCase` to ensure it adheres to the **Single Responsibility Principle (SRP)**. The responsibility of calculating the average review rating can be extracted into a dedicated class or function.

2. **Error Handling**:
   - Improve error handling by providing more granular error messages for different failure scenarios (e.g., network failure, data parsing errors, etc.).

3. **Unit Tests**:
   - Write additional unit tests to ensure the correctness of business logic and data fetching mechanisms, especially for edge cases such as empty reviews or products.

4. **Code Coverage**:
   - Increase the test coverage to include tests for all layers, including domain logic and UI components.

---

## Contributing

Feel free to fork the repository, contribute improvements, or create an issue if you find a bug. Contributions are always welcome!

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Acknowledgements

- The codebase follows principles of **Clean Architecture** and **SOLID**.
- The app uses **StateFlow** and modern Kotlin features to ensure a reactive, lifecycle-aware UI.

--- 

### Conclusion

This documentation provides a high-level overview of the `SephoraTest` Android app. The application implements clean architecture with modern Android best practices such as `StateFlow` for state management and `RecyclerView` for displaying dynamic lists. However, there are opportunities for improvement, particularly in terms of adhering more strictly to SOLID principles by refactoring the `UseCase` to have a single responsibility.
