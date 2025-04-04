Student's name	: Dong Wentao
ID			: 14649801


## Book Tracker App 


### Application Introduction:
Book Tracker is an Android mobile app designed for book lovers to help users track and manage their reading progress. Users can add books, view book details, update reading progress and ratings, and get book recommendations. The app is built using modern Android development technologies, providing an intuitive user interface and smooth user experience.

### Core Features:
Book Tracker provides the following core features:

Adding books: Users can add new books through AddBookScreen, including information such as book title, author, description, etc.
Viewing book lists: In BookListScreen, users can view a list of all added books and click on a book to view details.
Book detail management: In BookDetailScreen, users can:
View detailed information of a book (title, author, description).
Update reading progress (0-100).
Rate a book (1-5).
Write or edit a book review.
Delete a book.
Get recommendations: Through RecommendationsScreen, users can get book recommendations (based on external API).
Local storage: Use SQLDelight to implement local persistent storage of book data.
Network function: Get book recommendation data from external API through Retrofit.
Intuitive UI: Use Jetpack Compose to build the user interface, providing a responsive and user-friendly experience.

### Technology stack used:
The Book Tracker app uses the following modern Android development technologies and libraries:

1. Kotlin: The main programming language, following the best practices of modern Android development.
2. Jetpack Compose: Used to build user interfaces, providing a declarative UI development approach, following the Material Design principles.
3. Jetpack Navigation: Implements navigation between screens and supports safe parameter passing.
4. ViewModel: Manages UI data and supports lifecycle management.
5. Koin: A lightweight dependency injection framework for managing dependencies.
6. SQLDelight: Used for local data storage, replacing Room, providing type-safe database operations.
7. Retrofit: Used for network requests, obtaining book recommendation data from external APIs.
8. Coroutines & Flow: Handles asynchronous operations to ensure that the UI thread is not blocked.
9. GitHub: Used for version control and recording development progress.

### Future Improvements:
Offline function: cache API data to the local database to support offline use.
Search and filter: add book search and classification functions.
UI optimization: add icons to buttons and add page switching animation.
Test coverage: add UI tests and integration tests to improve test coverage.
