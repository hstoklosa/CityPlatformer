<!-- <p style="text-align: center;"><img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/character.png?raw=true" alt="Button A" height="200" /></p>

# <p style="text-align: center;">CityPlatformer | 2D Game</p> -->

# 👾 CityPlatformer | 2D Game

## Concept

The game is a 2D platformer developed in Java, utilising a physics engine to stimulate the game world. It presents a dynamic and engaging experience, mixing typical platforming elements with a focus on creativity.

The main idea of the game is that the player should navigate through various levels, each presenting unique environments with its own challenges, by using portals that are found on the pre-defined locations.

The environments consists of entities such as

-   various types of <a href="enemies">enemies</a> that could be static/dynamic
-   <a href="collectibles">collectibles</a> to be picked up by the player
-   interactive and non-interactive <a href="blocks">blocks</a>

Your objective is to collect as many coins as possible by playing this character <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/character.png?raw=true" width="20" style="vertical-align: center;"/>, using the following <a href="controls">controls</a> in game. The character is equipped with a name, health and stamina, and it can be recharged throughout the game.

In order to make the game more modern and visually pleasing, I have decided to implement a system (i.e., with [SpriteLoader](https://github.com/hstoklosa/CityPlatformer/blob/main/src/game/utils/SpriteLoader.java) and [AnimationHandler](https://github.com/hstoklosa/CityPlatformer/blob/main/src/game/handler/animation/AnimationHandler.java)) which applies appropriate animations to the bodies defined in the physics library using spritesheets.

Animation Examples:

-   Idle character
-   Moving character (including walk and run)
-   Jump (and double jump)
-   Spinning coin
-   Portal enchantment

## Preview

### Main Screen & Other

<img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/mm2.png?raw=true" alt="Button A" style="width: 49%" /> <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/end2.png?raw=true" alt="Button A" style="width: 49%;" />

### Gameplay

<img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/levels/1.png?raw=true" alt="Level 1 Gameplay" width="100%"/>

<img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/levels/2.png?raw=true" alt="Gameplay" width="100%"/>

<img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/levels/3.png?raw=true" alt="Gameplay" width="100%"/>

<a name="controls"></a>

## Controls

The game's controls are hard-coded, however you can use `ESC` button to pause the game and access some of the available options.

|                                                                   Key                                                                   |  Action  | Description                                                                           |
| :-------------------------------------------------------------------------------------------------------------------------------------: | :------: | :------------------------------------------------------------------------------------ |
|     <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/a.png?raw=true" alt="Button A" height="25" />     | Movement | Move to the left.                                                                     |
|     <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/d.png?raw=true" alt="Button D" height="25" />     | Movement | Move to the right.                                                                    |
|  <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/ctrl.png?raw=true" alt="Button CTRL" height="20" />  |  Sprint  | Sprint on left/right movement with stamina decaying over time.                        |
| <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/space.png?raw=true" alt="Button SPACE" height="20" /> |   Jump   | `Space` - normal jump <br> `Space` x 2 - double jump                                  |
|     <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/s.png?raw=true" alt="Button S" height="25" />     |   Save   | Save the current game state by overrwriting the most recent save in `saves.txt` file. |
|     <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/l.png?raw=true" alt="Button L" height="25" />     |   Load   | Load most recent save from `saves.txt` file.                                          |
|     <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/r.png?raw=true" alt="Button R" height="25" />     | Restart  | Restart the current level by restoring factory options.                               |
|                                                                  `LMB`                                                                  |  Shoot   | Shoot a slow, short range bullet that deals a **_big amount_** of damage.             |
|                                                                  `RMB`                                                                  |  Shoot   | Shoot a quick, long range bullet that deals a **_small amount_** of damage.           |

<a name="enemies"></a>

## Enemies

The enemies inserted onto each level are the biggest threat to the player since their main objective is to prevent the player from progressing onto further levels by dealing damage.

As the enemy is destroyed, a <a href="collectibles">coin</a> is spawned and it can be collected by the player to increase game score.

### Types

|                                                                    Image                                                                     |   Name    | Description                                                                                                                                                                                                         |
| :------------------------------------------------------------------------------------------------------------------------------------------: | :-------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/bunny.png?raw=true" alt="Bunny Enemy Image" height="50"/>  | **BUNNY** | A dynamic enemy that deals damage using melee attacks on close range. <br><br> The enemy is aware of the main player's presence and it will automatically go towards the player's location if presence is detected. |
| <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/slime.png?raw=true" alt="Slime Enemy Image" height="40" /> | **SLIME** | A static enemy that has a shooting ability. <br><br>The enemy is aware whenever a player enters its specified range in order to start dealing damage with bullets.                                                  |
| <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/spike.png?raw=true" alt="Slime Enemy Image" height="25" /> | **SPIKE** | A static enemy dealing damage on contact. The spike deals damage on contact and bounces the player until the player runs out of health/manages to get out.                                                          |

<a name="collectibles"></a>

## Collectibles

### Collectible Spawning

-   Collectibles are placed at pre-defined coordatinates - randomising this process can be a future objective.
-   Collectibles have a chance to spawn when an enemy is destroyed. The choice of item is random, but the chance of an item spawning is the same for all collectibles.

|                                                                                   Image                                                                                    |   Name    | Description                   |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-------: | :---------------------------- |
| <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/coin.png?raw=true" alt="Coin Collectible Image" style="margin: 0.75rem 0;" height="15"/> | **COIN**  | Increases score by 5 points.  |
|             <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/melon.png?raw=true" alt="Melon Collectible Image" height="40" />             | **MELON** | Regenerates 15 health points. |

<a name="blocks"></a>

## Blocks

|                                                                                                                                                                                                                           Image                                                                                                                                                                                                                            |                   Type                   |      Name      | Description                                                                                                                                 |
| :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------: | :------------: | :------------------------------------------------------------------------------------------------------------------------------------------ |
| <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/green_tile.png?raw=true" alt="Green Basic Tile" height="48" /> <br> <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/purple_tile.png?raw=true" alt="Purple Basic Tile" height="50" /> <br> <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/red_tile.png?raw=true" alt="Red Basic Tile" height="48" /> | **NON-INTERACTIVE** <br>/<br> **STATIC** |    **TILE**    | Static tile that is part of the terrain and its design.                                                                                     |
|                                                                                                                                                     <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/platform.png?raw=true" alt="Moving Platform Tile" height="15" />                                                                                                                                                     |             **INTERACTIVE**              |  **PLATFORM**  | A moving platform that transports the player by jumping onto it. <br><br> It can be moving in the **horizontal** or **vertical** direction. |
|                                                                                                                                                         <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/portal.png?raw=true" alt="Platform Block" height="60" />                                                                                                                                                         |             **INTERACTIVE**              |   **PORTAL**   | Portal that takes the player onto the next level unless an enemy is still alive on the current level.                                       |
|                                                                                                                                                           <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/chest.png?raw=true" alt="Chest Block" height="50" />                                                                                                                                                           |             **INTERACTIVE**              |   **CHEST**    | A chest holds a random <a href="collectibles">collectible</a>. The collectible can be discovered by hitting the chest 3 times.              |
|                                                                                                                                                <img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/trampoline.png?raw=true" alt="Trampoline Block" height="50" width="50" />                                                                                                                                                 |             **INTERACTIVE**              | **TRAMPOLINE** | Allows the player to bounce **_e.g.,_** onto a higher platform. <br><br> The higher the person falls, the higher the bounce rate.           |

## Saving & High score

### Saving

In order to save the current state of the game, the player must use the `S` control/keybind to overwrite the `CityPlatformer/saves.txt` file.

This data can be later loaded in the game using either the main menu button or the `L` control. This will appropriately read the file using streams and load the data into the respective world.

### High score

<img src="https://github.com/hstoklosa/hstoklosa/blob/main/assets/city-platformer/highscores.png?raw=true" alt="Highscore Game Screen" style="width: 100%;" />

As soon as the player finishes the last level, the game will look into the `CityPlatformer/scores.txt` file to find the appropriate position for the final score and name of the player.

This data can be later accessed using the high score screen within the game.

## Planned features

-   [ ] Use MySQL to store scores and saves (partially done)

## Copyright & Licenses

### Code

All code is written by [me](https://github.com/hstoklosa), unless it has been referenced.

Additionally, the project includes a physics engine library `city.cs.engine` that was provided by CUoL (unknown license).

### Font

The font used in this project is PressStart2P, which was designed by [CodeMan38](https://fonts.google.com/?query=CodeMan38). It is publicly available at [Google Fonts](https://fonts.google.com/specimen/Press+Start+2P), licensed under the [SIL Open Font License](https://openfontlicense.org/).

### Art

The art in this game came from [Pixel Adventure 1](https://pixelfrog-assets.itch.io/pixel-adventure-1) and [Pixel Adventure 2](https://pixelfrog-assets.itch.io/pixel-adventure-2), created by [Pixel Frog](https://pixelfrog-assets.itch.io/) and released under a [Creative Commons Zero (CC0) license](https://creativecommons.org/publicdomain/zero/1.0/).

Some of the graphics were slightly modified by me using the GIMP software, which is approved by the license even for commercial purposes.

### Sounds

The music track and sound effects were gathered from [Nosoapradio](https://www.facebook.com/freegamemusic/) and [FreeSound](https://freesound.org/) sites, licensed under the [Creative Commons License](https://creativecommons.org/licenses/by/4.0/).

<br>

© 2024 H. Stoklosa - hubert.stoklosa23@gmail.com

https://github.com/hstoklosa
