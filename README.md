# The dungeon
 Choose your hero and explore the dungeon from your terminal.<br/>
 This simple game was developed for the sake of learning the basics of Java.<br/><br/>
 In the following sections you will find some information and some instructions about the game.

## Project classes
```
    .
    ├── Main                # Game loop and basic logic
    ├── Game                # Containst all the game logic
    └── MainStats           # Basic parameters (such as attack, defense, HP, ...)
        ├── Hero
        ├── Enemy
        ├── Weapon
        └── Artifact
```

## Run the game
`java Main <args>`
> *NOTE: no external libraries are needed to run the game.*

### Arguments
- `-h` or `--help` to display the game instructions
- `-l` or `--load` to load the last checkpoint of a previous saved game

### License
Source codes, expect game assets, are available under the MIT license.
