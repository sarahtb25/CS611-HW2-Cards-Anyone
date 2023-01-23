# CS611-HW2
## Cards Anyone?
---------------------------------------------------------------------------
Harsh Khatri		Sarah Taaher Bonna
harsh242@bu.edu         sbonna@bu.edu
U08897332		U86084310

## Files
---------------------------------------------------------------------------
1. Main.java: The file that calls the methods necessary to play the Trianta Ena game
2. Banker.java: Banker is a specific class implemented for the Trianta Ena game. Banker extends TECardPlayer class.
3. CardHand.java: CardHand is a generic class for any type of playing card game. CardHand can hold any type of card hand i.e. set of playing cards.
4. CardPlayer.java: CardPlayer is a player i.e. it extends player. It can be used for any type of playing card game.
5. Game.java: A generic interface containing methods needed to play any game.
6. GameRules.java: Generic GameRules interface. Any game must implement the GameRule interface and return a list of winners.
7. *Piece_HW1.java: Adopted from HW1. Represents a game piece, which can be a board game piece or can be extended to be a card.
8. *Player_HW1.java: Adopted from HW1. Represents a player.
9. PlayingCard.java: A generic class which represents a playing card. Can be used for any playing card game.
10. PlayingCardDeck.java: A generic class, can be used for any playing card game. Represents deck(s) of playing cards.
11. RoundHistory.java: A generic class which stores player details for each round for statistics calculation. Stores the details of all the players for each round. 
12. Statistics.java: Used to calculate how much a player has won or lost in each round and also overall after players choose to cash out.
13. Table.java: Represents a table on which cards are played. Contains the deck of cards from which we draw cards.
14. TECardPlayer.java: A specific class used for TE game. Represents a Trianta Ena player
15. TEGame.java: Contains the logic of the Trianta Ena game, as well as the objects needed to play the game, such as players, table, etc.
16. TEPlayingCard.java: A specific class defined for TE game. Represents a Trianta Ena playing card (values of cards are different for different playing card games).
17. TEPlayingCardDeck.java: A specific class defined for TE game. Represents 2 decks of Trianta Ena playing cards. 
18. Utility.java: Contains methods used frequently during the game, such as printing a new line, checking a user input (yes or no), etc..
19. UML.png: Contains the UML diagram illustrating the structure of Trianta Ena implementation

Note: Classes marked with * are reused from HW1.

## Justification of Structure
---------------------------------------------------------------------------
We chose Harsh’s TTT design infrastructure over Sarah’s because some of the classes were more generalized and therefore could be reused in Trianta Ena. We could have used Players class from Sarah’s structure but Harsh’s Player class was more suitable for TE game.
The classes reused from TTT are Piece.java and Player.java. This is because these classes were general enough to be used for a playing card game. We have extended Piece class to create a Playing card and extended Player to create a CardPlayer, which can be used for any playing card game.

Benefits of the current structure for Trianta Ena:
1. Many classes used in this game  are generalized enough to be used for other types of playing card games. Classes that can be used for other playing card games are: CardHand, CardPlayer, Game, GameRules, PlayingCard.
2. Our current structure allows us to display various statistics, such as which player won/lost how much after each round, provide a round summary for each round played and overall summary of how much each player won/lost (if at all) after the players choose to cash out. There is a lot more that can be achieved using the current structure in terms of displaying various statistics and making predictions, but the above mentioned are the ones we have managed to accomplish in this short time.
3. Implemented generic Game and GameRules Interface, which can be used in any type of game.
4. Concepts used from lecture: Generics, Inheritance, Interface, Comparator, Overriding methods, Abstraction, Single Responsibility Principle, Open-closed Principle, Dependency Injection, etc

Flaws of the current structure and what could have been done instead:
1. Piece class has a displayChar of type char, which is meant to display the type of card eg. Ace, Joker, etc.. This caused issues in Trianta Ena, because card 10 cannot be represented as a character. Therefore, a String would be a better choice for displaying a Piece on the terminal. The piece class should have declared displayChar as a String instead.

2. Some methods are dependent on one another in a few cases. Hence, quite a bit of effort has to be spent if any refactoring is needed. However, each method should only be doing one task, as per the Single Responsibility Principle, which implies that much effort should not be needed if any refactoring has to be done.

Lessons learnt from HW1 and (hopefully) corrected in HW2:
1. Implemented rules of the game using interface structure instead of implementing inside methods.
2. Created more classes compared to HW1, which represents better extendibility of the design.
3. Extensive use of inheritance.
4. Advanced the various principles learned in class from HW1, such as Single Responsibility Principle and more.

## Notes
---------------------------------------------------------------------------
1. N/A
2. Implemented additional feature (Statistics), to display statistics (money won/lost) of each player after each round, and overall.
3. The Java version used during development is Java 14. Abbreviations were used for the display of playing cards e.g. S-A means Ace of Spades, D-J means Diamond Joker, H-K means Heart King, C-Q means Club Queen and *-* means the card is face down.

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "CS611_HW2" after unzipping the files
2. Run the following instructions:
javac -d classes src/*.java
java -cp classes Main

## Input/Output Example
---------------------------------------------------------------------------
Output: Please enter total number of players playing the game (min. 3 and max. 10): Input: 3
Output: Enter player 1 name: Input: Harry
Output: Enter player 2 name: Input: William
Output: Enter player 3 name: Input: Jerry
Output: 
Welcome to Trianta Ena!
Please enter how much you would like to be assigned as balance:
Input: 1000
Output:
Assigning a banker randomly...

Here's the player information:
Banker William  Balance: 3000

Players:
Harry    Balance: 1000
Jerry    Balance: 1000

It's time to place bets
Banker William
H-J

Jerry
*-*,

Your (Harry) cards:
S-7

Current value of Hand: 7

Harry would you like to place a bet (Y/N)? Input: Y
Output: 
Your current balance: 1000
Harry place your bets please:
Input: 400
Output: 
Banker William
H-J

Harry
*-*,

Your (Jerry) cards:
C-6

Current value of Hand: 6

Jerry would you like to place a bet (Y/N)? Input: Y
Output:
Your current balance: 1000
Jerry place your bets please:
Input: 200
Output:
Harry Cards:
S-7, C-K, C-Q


Current value of Hand: 27
Harry Hit (Y/N)?
Input: N
Output:
Jerry Cards:
C-6, D-9, S-4


Current value of Hand: 19
Jerry Hit (Y/N)?
Input: Y
Output:
Your (Jerry) cards:
C-6, D-9, S-4, C-8

Current value of Hand: 27
Jerry Hit again (Y/N)?
Input: N
Output:
Banker is taking a Hit
Your (Banker William) cards:
H-J, C-4, S-J, S-2

Current value of Hand: 26
Taking a Hit again
Your (Banker William) cards:
H-J, C-4, S-J, S-2, D-4

Current value of Hand: 30
Banker William
        Initial Balance: 3000
        Bet: 0
        Final Balance: 3600
Harry
        Initial Balance: 1000
        Bet: 400
        Final Balance: 600
Jerry
        Initial Balance: 1000
        Bet: 200
        Final Balance: 800

Round Summary:
William has won 600
Harry has lost 400
Jerry has lost 200

Would you like to play another round (Y/N)?
Input: Y
Output: William would you like to be the banker in next round (Y/N)?
Input: N
Output: Jerry would you like to be the banker in next round (Y/N)?
Input: Y
Output:
For the next round Jerry will be the banker
Here's the player information:
Banker Jerry    Balance: 800

Players:
William  Balance: 3600
Harry    Balance: 600

It's time to place bets
Banker Jerry
D-10

Harry
*-*,

Your (William) cards:
C-10

Current value of Hand: 10

William would you like to place a bet (Y/N)? Input: Y
Output:
Your current balance: 3600
William place your bets please:
Input: 300
Output:
Banker Jerry
D-10

William
*-*,

Your (Harry) cards:
D-K

Current value of Hand: 10

Harry would you like to place a bet (Y/N)? Input: Y
Output:
Your current balance: 600
Harry place your bets please:
Input: 400
Output: William do you want value of this Ace to be 1 (Y/N)?
Input: N
Output: William do you want value of this Ace to be 1 (Y/N)?
Input: N
Output:
Harry Cards:
D-K, S-9, H-10


Current value of Hand: 29
Harry Hit (Y/N)?
Input: N
Output:
Banker can't take a hit!
Your (Banker Jerry) cards:
D-10, H-8, D-9

Current value of Hand: 27
Banker Jerry
        Initial Balance: 800
        Bet: 0
        Final Balance: 700
William
        Initial Balance: 3600
        Bet: 300
        Final Balance: 3300
Harry
        Initial Balance: 600
        Bet: 400
        Final Balance: 1000

Round Summary:
Jerry has lost 100
William has lost 300
Harry has won 400

Would you like to play another round (Y/N)?
Input: N
Output:
Round 1:

Round Summary:
William has won 600
Harry has won 400
Jerry has lost 200

Round 2:

Round Summary:
Jerry has lost 100
William has lost 300
Harry has won 400


Overall,
        William has won 300
        Harry has won 800
        Jerry has lost 300

