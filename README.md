# MochiJump
Build v0.23 achieved.

This repository holds my first platformer game built with Java.

*Note: this project uses Jackson FasterXML. Currently you will need to include this manually if you want to compile this from source code as opposed to launching the runnable jar.*

/dist holds a runnable jar of a recent version build of the game (Web App needs to be updated before new dist is made. /dist currently holds an older version that runs correctly with current Web App API)

The MochiJump App now can import customer levels built on http://mochijump.com

Recently done:

Focused camera to zoom in on and follow Mochi (improved).

Mochi now bounces off HairClipNPC objects upon destruction.

YLimiter object now defines how far down Mochi can fall before the game ends

Should do:

Add gradle script to include jackson fasterxml // https://github.com/rholder/gradle-one-jar
