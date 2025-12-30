# Quiz Game (Java)

What This Project Does
----------------------
This is a console-based Quiz Game built in Java. It allows users to test their general knowledge through randomly ordered multiple-choice questions. Each question must be answered within a fixed time limit, making the game both challenging and fun. A score is displayed at the end based on the correct answers.

The game includes:

- Multiple-choice questions
- Timer (20 seconds per question)
- Score tracking
- Graceful handling of user input
- JDBC-based database setup for future scalability

How to Run the Project
----------------------
Prerequisites:

- Install Java JDK (11+ recommended).
- Verify installation: run `java -version`.

Compile and run:

1. Open a terminal in the project directory.
2. Compile the game:

	javac QuizGame.java

3. Run the game:

	java QuizGame

Notes
-----
- The project currently uses console I/O and a simple JDBC-ready structure for future database integration.
- Questions and timing logic are implemented in the source; adjust the timer value in code if needed.

See the repository files for the Java source and any additional assets.