meeting time: 15：00-19:00 2021.5.17

participator: Guanming, YiTao, Bill

What we do: 
- Co-writing codes time
- Improve the data exchange object for inner data exchange
- An external data interface(i/0) has been established

- 1. The initial game map data is imported by two hashmaps of the player
  
  2. Player.place is used as an intermediary to read and store all the information about the player's current position
  
  3. Change the map data in the game, modify the data in HashMap
  
  4. EP: for example, the information interaction of items occurs in player.bag and player.place.bag, and there may also be player.bag and player.place.npc_ Merchant interaction (trading), and update the bag of NPC in HashMap