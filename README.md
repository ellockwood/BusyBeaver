# BusyBeaver
Simple, functional. No GUI. Built in Eclipse

This is a small project to create a fun-to-use program to play the 'Busy Beaver' game.
It simulates large amounts of tape going either way instead of setting an amount at the beginning,
and has configurable rest times between steps making it practical for complex sets of states (as practical as the set anyway!)



Busy Beaver explanation:

Firstly, you will be asked about the number of states. Your response should be a number with a value of 1 or greater

Next, you will be asked for the behavior for 0 and for 1. Your response should be a list of 3 numbers, the first 2 ranging from (0-1) and the third ranging from (0-number of states). The meaning of these numbers is described below.

Each state can be shown as taking the form of an instruction card:

The first number is either a 0 or 1. If the computer reads a 0 in the current place it will move to the "0" instructions and vice versa
You will not have to input this first number, but rather you will have to provide different 3-number instructions for each.

The second number tells the computer what to write to the current place. This is the first number you input as part of the behavior.

The third number tells the computer which direction to move the tape (0 for left, 1 for right). This is the second number you input as part of the behavior.

The fourth number tells the computer what state to go to next (0 to halt program, any other state for a different set of instructions). This is the third number you input as part of the behavior.


The point of this game is to find the maximum number of 1's you can write given a finite number of states and a finite run time

To understand more about the nature of the Busy Beaver game, watch Computerphile's explanation: https://www.youtube.com/watch?v=CE8UhcyJS0I




Contributors Could:
- Add GUI and modify "back-end" code to accomodate
- Condense or clarify code with the end-goal being an easily understandable and efficient program
