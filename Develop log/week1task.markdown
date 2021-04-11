Pokemon data structure 

1）存放地图类的 map包
2）存放NPC、商店的 npc包
3）存放玩家数据与战斗方法的 player包
4）存放宝可梦与宝可梦技能的 pokemon包
5）存放流程逻辑主函数的 pokemonmain包

Pokemon：

  Name:空白 （game engine 输入）
  Gender
    percentage_male/female/no gender
  Capture_rate
  Exp_required_per_level
  Character/type
  Against_? (represent (extra) damage to the specific type)
  HP
  Attack
  Defense
  Speed
  Skills



Week 1 (05/04/2021 - 11/04/2021) (search information of game engine and game)
Guanming Ou:
Game engine:
https://interestingengineering.com/how-game-engines-work
Game engine have these components:
game logic program
rendering engine to generate graphics (interaction windows)
audio engine which consists of algorithm which are related to sound (background music)
physic engine to implement physic law within the system
AI module

https://www.smashingmagazine.com/2018/12/multiplayer-text-adventure-engine-node-js/
Game feature;
Able to create a new game
New game instance
Interact with scene
Check inventory
Combat system
drop loot
action (attack, defend, escape, use items from inventory)
General game steps:
Request scene (request metadata from this scene)
Return the meta data (general description, plot, found enemy ect.)
send command (Player’s action they want to perform)
return reaction of the command (What will player’s action going to affect?)
Function of client application;
Create new game
Join an existing game 
Parse game definition files (the client should be able to understand these file and know how to use the data)
Interact with the adventure
Maintain an inventory for each player (in-memory list of items)
JSON files:
See website for example of how they write it
Basically contain following
Graph(the structure of the file)
Game (win/lose condition (killed by enemy or you win))
Rooms (id key), plot with graphs
Description (items condition, room environment)
Items (its property, damage, durance, )
Exits
NPC inside the room
More of this (part 2, 3, 4)
https://www.smashingmagazine.com/2019/10/multiplayer-text-adventure-engine-node-js-part-2/
https://www.smashingmagazine.com/2019/10/multiplayer-text-adventure-engine-node-js-part-3/
https://www.smashingmagazine.com/2019/11/multiplayer-text-adventure-engine-node-js-part-4/
https://www.youtube.com/watch?v=EpB9u4ItOYU
This basically demonstrates how combat systems may work for our game engine.

Text base game:
Zork: https://www.youtube.com/watch?v=PWQDccL0aXM
This is a game play through that shows how text base game may work.




YiTao:



Bill:
What is game engine:
A game engine could be defined as a architecture that developers use to run the game
A (text) game engine should provide follow features:
physics
input
scripting
collision detection
artificial intelligence
Players can read or view descriptions of rooms, objects, other players, non-player characters, and actions performed in the virtual world
(https://www.gamedesigning.org/career/video-game-engines/ )

Example:
Twine
Quest
Adrift
...
(https://www.makeuseof.com/tag/3-tools-to-create-your-own-text-adventure-games/ )

Test game example:
Crimesquad
MUD (Multi-User Dungeon)
Your Chronicle
Features:
Has friendly user interface
Planty actions could go to
Comprehensive user description





Yixiang Yin:

text game engine example:
http://twinery.org   (open source text game engine - Twine)
https://www.youtube.com/watch?v=SJjCnfRwcmE (someone’s the text game engine and game)
What should our game engine do:
Build the framework for the game
Implement the logic of the game
Initialize and run the game
           (e.g. Load the configuration file(e.g. map, pokemon info) of the game)
(optional) include images
(optional) include musics

Game example:
        https://www.dropbox.com/s/dl/7foqahr5tq2zfus/57North_Twine.html 
        (Download the html file and click on it to play!) 
or
        https://mightycoconut.com/57north/   scroll down,click “download twine file” and open the file to play

Some discoveries: 
      At each stage, the game engine will show some options to the user. Different                                                       options chosen by the user will lead the user to different stages.
E.g.


Week 2:  (12/04/2021 - 18/04/2021)
Week 3: (19/04/2021 - 25/04/2021)
Week 4: (26/04/2021 - 02/05/2021)
Week 5: (3/05/2021 - 09/05/2021) 
Week 6: (10/05/2021 - 16/05/2021) (Wrap up and finish the game)
Week 7: (17/05/2021 - 21/05/2021) (GP due)








References&Resources:
https://www.heywhale.com/mw/dataset/5d8ad720e3ffb2002c454def
https://www.dtmao.cc/news_show_826043.shtml

https://www.smashingmagazine.com/2018/12/multiplayer-text-adventure-engine-node-js/
https://www.smashingmagazine.com/2019/10/multiplayer-text-adventure-engine-node-js-part-2/
https://www.smashingmagazine.com/2019/10/multiplayer-text-adventure-engine-node-js-part-3/
https://www.smashingmagazine.com/2019/11/multiplayer-text-adventure-engine-node-js-part-4/
http://twinery.org/2/#!/st
