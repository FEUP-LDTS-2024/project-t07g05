# LDTS_T07_G05 - CRYSTAL CLASH
## Game Description 

## 🛠️ Technologies Used

- **Java:** Core programming language for game logic and mechanics.
- **Lanterna:** Lightweight library for creating terminal-based games.
- **JUnit:** For unit testing to ensure code reliability and functionality.

## 🚀 Implemented Features

- [x] **Randomized board** - Each game begins with a unique, randomized arrangement of tiles, ensuring a fresh and unpredictable experience every time you play.
- [x] **Tile movement** - Navigate effortlessly across the board in all directions using intuitive keyboard controls, while staying within the board's boundaries.
- [x] **Tile swapping** - The player has the capability of swapping adjacent tiles to create exciting combinations and set up strategic plays.
- [x] **Match detection** - Automatic identification of matches with three or more tiles in a row or column.
- [x] **Tile popping** - Matches are cleared from the board, making space for new tiles to fall into place.
- [x] **Gravity-powered tile shifting** - Tiles cascade downward to fill empty spaces, creating opportunity for chain reactions.
- [x] **Time-based gameplay** - A timer adds urgency, challenging players to score as many points as possible within the time limit.
- [x] **High score system** - Tracks the player’s best score, encouraging replayability and competition.
- [x] **Bombs** - Special tiles that clear surrounding tiles when matched, allowing for strategic moves and massive combos.
- [x] **Dynamic scoring system** - Reward players for creating larger matches and performing combo moves, with bonus points for speed and precision.
- [x] **Menus** - Easy-to-navigate menus for starting the game, viewing high scores, and accessing instructions.


## 🚀 Planned Features

All the planned features were successfully implemented.

## 🧩 Design

![UML diagram presenting project's design patterns and classes](./images/CrystalClashDiagramUML.png)

### 🔧 Model-View-Controller Architecture


* **Why MVC?** Match-three puzzle games often involve complex mechanics like power-ups, animations, and custom rules. To simplify adding new features and ensure maintainability, we chose the Model-View-Controller (MVC) architecture. Its modular structure enhances clarity and makes the project more scalable.


* **What is MVC?** The Model-View-Controller pattern is a design approach that organizes the application into three distinct components: the model (manages the core data), the controller (acts as an intermediary for both the model and the view) and the view (handles the visual representation of the game).


* **How We Applied MVC:** As for the model, it includes the game’s mechanics, such as detecting matches, managing tile movements, and handling board state changes. As for the controller, it manages user interactions, like selecting and swapping tiles, and triggers updates to the model and view based on user actions. And as for the view, it ensures the game is visually represented by reflecting changes in the model and keeping the user engaged with a responsive interface.


### 🔧 Factory Design Pattern

* **Why Factory?**
  In a match-three puzzle game, different tile types (e.g., gems, bombs, empty tiles) may have unique behaviors, properties, and visuals. Managing the creation of these tiles directly within the game logic would lead to repetitive code and make future changes inconvenient. The Factory Design Pattern allows us to centralize and simplify the creation of tile objects while adhering to the *Single Responsibility Principle*.

* **What is Factory?**
  The Factory pattern is a creational design pattern that provides an interface or method to instantiate objects based on specified criteria without exposing the instantiation logic. This helps in producing objects while keeping the creation logic modular and maintainable.

* **How We Applied Factory:** Although the Factory pattern is not yet implemented, we plan to introduce it in the Tile class. Currently, tiles are created directly during the initialization of the game board. By applying the Factory pattern, we aim to facilitate the addition of new tile types in the future with minimal changes to the existing code and enhance code readability and maintainability by separating the instantiation logic from the game mechanics.

### Game Loop Pattern

* **Why Game Loop?** In games, actions like rendering visuals, processing player input, and updating the game state need to happen repeatedly and consistently to maintain a smooth and interactive experience. The Game Loop pattern allows us to manage these tasks in a structured way, ensuring the game progresses frame by frame while maintaining a steady rhythm regardless of input frequency or system performance.

* **What is Game Loop?** Game Loop is a design pattern that provides the structure for a game's main cycle. It repeatedly performs three main steps: processing input, updating game state and rendering.

* **How we Applied Game Loop:** In our project, the Game Loop manages the game's flow by continuously checking for player input, updating the game's internal state based on user actions (such as moving from the current tile or swapping tiles), and then rendering the board and its tiles.
