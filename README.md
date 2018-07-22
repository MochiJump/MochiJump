# MochiJump
Build v0.23 achieved.

This repository holds my first platformer game built with Java.

The MochiJump App will now load the last created level at MochiJump.com as a playable level:
From Start Menu select "Load lvl" and then "Start" 

To Do:

Create NPCs

Consider refacorting JPanel Classes:

DogLogic class requires new game characters to be added in three different places in the code. This must happen at compilation currently (at run time is currently not possible) This could be changed if we used an arrayList of GameCharacter, animation, used polymorphism, and changed dependencies.

Look into using callbacks to ensure proper initialization

Consider plan for reducing if statements for GameCharacter state

contemplating having the level selection menu include a 3d dial, however, it may be better to give it a place holder for now

updated source code has not been built into executable yet

Small delay while importing levels, maybe create a loading animation or feature.
https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation

The POM.XLM has not been tested for dependency issues by launching with maven or compiling to a JAR yet

Try using Grandle in place of Maven for shipping.

Create UI and code for the option to select a level from the user made ones on MochiJump.com by level name, random level etc

Update collision rules so Mochi sprite behaves better on uneven surfaces (Suspect it has to do with the fact that it is looking at individual rectangles for collision despite the fact that many are overlayed ontop of one another in user generated levels) - Take a look at whether the sprite dimensions update for collision detection during jumping animation.

remove println("bark") in game thread

Further reading on how to create a jar w/ dependencies:
https://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven
