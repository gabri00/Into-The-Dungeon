# The dungeon
 Choose your hero and explore the dungeon from your terminal.<br/>
 This simple game was developed for the sake of learning the basics of Java.

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

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
Source codes, expect game assets, are available under the [GPL-3.0](https://choosealicense.com/licenses/gpl-3.0/) license.<br/>
All game assets (except for _slime.txt_, _lancer.txt_, and _warrior.txt_) are taken from [this website](https://www.asciiart.eu/).

## To Do
- [ ] Add more detailed instructions
- [ ] Set correct parameters for heroes, enemies, weapons
- [ ] Add a drop artifacts feature
- [ ] Fix the level up logic
- [ ] Implement colored text
- [ ] Optimize the autosave functionality
