# MochiJump
Build v0.23 achieved.

This repository holds my first platformer game built with Java.

The MochiJump App will now load the last created level at MochiJump.com as a playable level:
From Start Menu select "Load lvl" and then "Start" 

To Do:

**Refractor Mochi Class partially complete, still more to be done here**

Find a solution to not having static variables in LevelMap - Observation Pattern?

contemplating having the level selection menu include a 3d dial, however, it may be better to give it a place holder for now

updated source code has not been built into executable yet

Small delay while importing levels, maybe create a loading animation or feature.

The POM.XLM has not been tested for dependency issues by launching with maven or compiling to a JAR yet

Try using Grandle in place of Maven for shipping.

Create UI and code for the option to select a level from the user made ones on MochiJump.com by level name, random level etc

Update collision rules so Mochi sprite behaves better on uneven surfaces (Suspect it has to do with the fact that it is looking at individual rectangles for collision despite the fact that many are overlayed ontop of one another in user generated levels) - Take a look at whether the sprite dimensions update for collision detection during jumping animation.

remove println("bark") in game thread

Further reading on how to create a jar w/ dependencies:
https://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven
