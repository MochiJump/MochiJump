# MochiJump
Build v0.23 achieved.

This repository holds my first platformer game built with Java.

*Note: this project uses Jackson FasterXML. You will need to include this if you want to compile this from source code as opposed to launching the runnable jar.*

/dist holds a runnable jar of a recent version build of the game (Web App needs to be updated before new dist is made. /dist currently holds an older version that runs correctly with current Web App API)

The MochiJump App now can import customer levels built on http://mochijump.com

Recently done:

Focused camera to zoom in on and follow Mochi.

Should do:

Add a cuttoff y limit based off a value combining bottom object's y value

Look into occasional strange collision (looks like teleportation) for npc units both alive and on destruction animation
Note: teleportation on destruction seems to be device dependent. Perhaps it has something to do width/height ratio?

Add gradle script to include jackson fasterxml
