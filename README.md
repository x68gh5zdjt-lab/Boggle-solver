# Boggle-solver
This is a game called boggle!

<img width="225" height="225" alt="image" src="https://github.com/user-attachments/assets/beed6ccd-d766-4325-a9d6-8bef5aea75f1" />

Boggle is a word game where you must select letters that attach to eachother to make words, each word is assigned a point value based off of how
long the word is. This program is intended to take an array of letters like you would have in boggle, and find every possible combination of words, the rules of boggle are quite complicated so please take a minute to play the online version attached below 
https://www.puzzle-words.com/boggle-4x4/ 

This program will return every possible boggle word, aswell as the associated points, and sort that by length of word. EX
 
"
6 LETTER - 
Grapes, skates, plates

7 LETTER - 
apteryx, aqueous, abbozzo
" 

# How to run? 
Go to codespaces, either create new or use the one in the project, when you get there go to terminal and run \
sdk install java 21.0.3-tem \
javac *.java (if not compiled or you dont have the files downloaded if locally) \
java main \
Please come contact us if you cannot get it to work. 

# MVC Architecture!
BoggleWords.txt - the word dataset \
main.java – Loader \
trienode.java – stores the data for trie nodes \
triemanager.java – managers trienodes, handles storing all of the separate tries \
solver.java – Takes all the trie node, figures out words and returns them 

# Design Patterns! 
Iterator - We iterate over our own data structure (the trie data structure). This requires multiple custom made methods. \
Template Method - We have a fixed and set algorithm that is responsible for finding all words in the board.


# Coverage! 
<img width="1598" height="899" alt="image" src="https://github.com/user-attachments/assets/883d58bb-b953-4658-9af0-ad6282fcfd3c" />
