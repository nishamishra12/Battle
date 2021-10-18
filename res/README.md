# Readme Documentation

## About/Overview
The Jumptastic Games is designing and implementing a new role-playing game that allows the gamers to build player that can enter the arena for one-on-one combat. 

The project creates a battle arena which will allow two players to enter into the arena with their basic abilities Strength, Dexterity, Charisma, and Constitution. The players are equipped with different gears and are provided weapons to fight with each other. The reuslt of the battle is dependent on the striking power o the attacker and the avoidance ability of the defendant. 

## List of Features
1. The arena starts by taking two players from the user.
2. Players can equip themselves from a bag of gears containing 5 headgear, 5 footwear, 15 potion, and 15 belts. The gears are assigned to both the players randomly.
3. The players are randomly assigned any weapon from the bag of weapons consisting of weapon types Axe, Flail, Katana, Broadsword, and Two handed sword.
4. The gears provided to the player changes their abilities positively or negatively.
5. The effect of the gears are temporary for certain moves and once the gears are removed the effects are diminished.
6. The players can strike each other, dodge, and battle based on the striking power and avoidance ability of each player.
7. The health of the player is reduced if the strike by the opponent is successful.
8. The entire game can be reset and the players get back to their initial basic abilities and health.

## How to Run
1. Open Terminal
2. Navigate to where jar file is
3. Type the following: java -jar .\Battle.jar

## How to use the program
1. Enter the name of both the players to create new player objects
2. Equip both the players with all the gears
3. Assign a weapon to each player
4. Display the description of both the players consisting of basic abilities, list of gears sorted in lexicographical order, and the weapon in hand of the player.
5. Start the battle between both the players
6. The attack details of the player is printed
7. Check for resetting the battle. Enter 'Y' to reset the battle and return the players to their initial ability values.

## Description of Examples
### Run 1 – RUN 1.txt
1. Create 2 new players in the battle arena
2. Printing the description of both the players consisting of Player Name, Basic Abilities, List of Gears, and the Weapon.
3. Printing the attack description of each move in the battle. Consists of details of the attacker name and defender name, striking power of the attacker, avoidance ability of the defender, health of both the players before strike, and attack successful or not and the damage done.
4. Start the battle between the players
5. Printing the final result of the battle, whether win or draw.
6. Check if the user wants to reset the battle
7. Enter 'Y' to resent and 'N' to end the battle 
### Run 2 – RUN 2.txt
1. Create 2 new players in the battle arena
2. Printing the description of both the players consisting of Player Name, Basic Abilities, List of Gears, and the Weapon.
3. Printing the attack description of each move in the battle. Consists of details of the attacker name and defender name, striking power of the attacker, avoidance ability of the defender, health of both the players before strike, and attack successful or not and the damage done.
4. Start the battle between the players
5. Printing the final result of the battle, whether win or draw.
6. Check if the user wants to reset the battle
7. Enter 'Y' and reset the battle
8. Printing the description and attack details of the new run
9. Printing the result of the reset battle
### Run 3 – RUN 3.txt
1. Create 2 new players in the battle arena
2. Printing the description of both the players consisting of Player Name, Basic Abilities, List of Gears, and the Weapon.
3. Printing the attack description of each move in the battle. Consists of details of the attacker name and defender name, striking power of the attacker, avoidance ability of the defender, attack successful or not and the damage done.
4. Start the battle between the players
5. Charisma of the players is same, battle result in draw
6. Printing the result of the battle as draw
### Run 4 - RUN 4.txt
1. Create 2 new players in the battle arena
2. Printing the description of both the players consisting of Player Name, Basic Abilities, List of Gears, and the Weapon.
3. Printing the attack description of each move in the battle. Consists of details of the attacker name and defender name, striking power of the attacker, avoidance ability of the defender, attack successful or not and the damage done.
4. Start the battle between the players
5. Charisma of the players is same, battle result in draw
6. Printing the result of the battle

## Design/Model Changes
1. Initially all the gears were directly implementing the Equipment interface to ensure the highest level of abstraction. In the new design, the equipment class extends an abstract class which in turn implements the Equipment interface. The abstract class is created to ensure double dispatch of compareTo between the gear classes since the gears need to be sorted in lexicographical order.
2. Initially the random generator was a concrete class, in the new design random generator interface is created wit two concrete classes Random Generator and Fixed Generator. The fixed generator is a mock class which helps in testing.
3. Enum for Equipment Type: Initial design did not contain enumerations for Equipment Type. It was added in the new design because each time a string is created, instead of using this string that have precise value it is better design to use enumerations

## Assumptions
1. The gears of the players are set temporary basis on the move of the player. The move till which any gear if valid is randomly generated
2. The player is assigned only one weapon per battle
3. The weapons do not change mid-battle and are assigned at the very start
4. The players cannot ask for new gears after the battle has been started, the gears can be removed but not added to the player

## Limitations
1. The program doesn't allow more than 2 players to play the battle

## Citations
1.	https://dzone.com/articles/random-number-generation-in-java
2.	https://www.baeldung.com/ddd-double-dispatch

