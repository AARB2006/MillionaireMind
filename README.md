# Millionaire Mind

Millionaire Mind is a Java quiz game inspired by *Who Wants to Be a
Millionaire?* The project currently includes a simple command-line interface
(CLI) and a JavaFX graphical interface (GUI).

The quiz question system is not implemented yet. The current version focuses
on the basic screen flow and shared game state.

## Current Features

- Main menu
- Play screen
- Instructions placeholder
- Quit-game confirmation
- Congratulations message
- Basic winnings tracking
- CLI navigation
- JavaFX navigation
- Maven build configuration

## Requirements

- Java Development Kit (JDK) 21
- Apache Maven

Check the installed versions with:

```text
java -version
mvn -version
```

## Project Structure

```text
demo/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/millionairemind/
        │       ├── Main.java
        │       ├── cli/
        │       │   ├── CliMain.java
        │       │   └── ConsoleUI.java
        │       ├── gui/
        │       │   ├── Main.java
        │       │   ├── GameUI.java
        │       │   └── screens/
        │       │       ├── TitleUI.java
        │       │       ├── PlayUI.java
        │       │       └── InstructionsUI.java
        │       └── model/
        │           └── GameSession.java
        └── resources/
            └── com/millionairemind/gui/game.css
```

## Build the Project

Open a terminal in the directory containing `pom.xml`:

```powershell
cd C:\Users\AARB\Downloads\test\demo
```

Build and test the project:

```powershell
mvn clean test
```

Create the packaged JAR:

```powershell
mvn clean package
```

## Run the CLI

From the project directory, run:

```powershell
mvn compile exec:java
```

The CLI uses simple numbered menus:

```text
MILLIONAIRE MIND

1. Play
2. Instructions
3. Quit
Choose an option:
```

The current CLI flow is:

1. Select **Play**, **Instructions**, or **Quit**.
2. From **Play**, choose **Quit game** or **Back to menu**.
3. Confirm whether you want to walk away.
4. View the current winnings.

## Run the JavaFX GUI

From the project directory, run:

```powershell
mvn javafx:run
```

The GUI currently includes a title screen, play screen, instructions screen,
and quit-game confirmation popup.

## Game State

`GameSession` currently stores the player's winnings. The initial value is
`$0.00`.

The game state is shared by the user-interface flow and can later be expanded
with questions, prize levels, lifelines, and game status.

## Technologies

- Java 21
- JavaFX 21
- Maven
- CSS

## Planned Work

- Add the question model and question bank
- Add multiple-choice answers
- Add answer validation
- Add the prize ladder
- Add lifelines
- Add completed-game and game-over states
- Improve the instructions screen
- Expand the GUI and CLI gameplay screens

## Design Goals

- Keep game logic separate from the CLI and GUI.
- Reuse the same game state across both interfaces.
- Keep each screen and component easy to maintain.
- Add new game features without rewriting the application structure.
