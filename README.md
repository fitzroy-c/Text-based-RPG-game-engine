# 2100 Group Project

# We created a Java text based, single player adventure game.
## In the game, you can:

1. walk around freely on our map!
2. explore different items and treasure on our map!
3. defeat powerful monsters and bosses to make you stronger!
4. talk to the guide if you get lost and don't know where to start.
5. get all your needs from wandering merchants scattering on the map!
6. and a lot more for you to explore!!!

## For more detailed information about the program, please go to gitlab wiki page

# Other documentation
- Development log is in Develop package.
- Project summary, game engine design, quick guide for player and game designer, references and required additonal library are all on **Wiki** page
- The card package, map package, gui package and DesignForFutureExtension package are used for game's future extension.


# Team structure and role
Guanming Ou (u7130469):
Designed card, rooms, deck, and other class’s structure.  Brainstormed on how to create a text card rpg game.
Mainly in charge of user command parser (recognize user input and call the right method), 
npc talk class, which can do conversation with player.  Active in modifying and brainstorming the game logical 
framework and class structure, involves in discussion with group mates.

Yixiang Yin:
- Allow the player to load the old game from corresponding json file(see load in player.class)…
- Allow the player to change the map or other initalisations of the game by editing json file.
- Initialize the merchant and items on the map by loading the corresponding json file.
- Allow the player to interact with item(see item.class). For instance, use item to heal
- Allow the player to interact with bag(see bag.class). For instance, drop thing out of bag, put things into the bag, show the bag…
- Allow the player to buy things from merchant (see merchant.class and buyCommand Handler in Player.class). 
…(include many contributions to various classes) and fixed a lot of bugs (for more, see gitlab history)

Yitao Chen (u7004563):
- Write some small basic runnable textbase-game case (without giving user too much options).
  - Check the feedback
  - Design the battle function
  - Design what attributes a monster/player should have. (entity.class, abnormalPoint.class, monster.class)
- Designed the logical framework of the game process, as well as the main frame of the project. (which should inherit from which etc.)
- Proposed the data i/o objects (and their structure) to make inner data transfer and inside-outside data transfer. (Mostly the basic player.class structure and place.class.)
- Designed the navigation package (to mark the player's current coordinate and store current info at this position)
- Create the monsters system
  - How they create
  - How can player react with them, the player's choice/data-change/result. All the related functions.
- Designed autoTest.java
- Designed the system to check whether the json fits our game engine or not. (JsonTestNpcTalk.java, JsonTestPlayerAttribute.java)


Bill yuan:
