# MochiJump
Build v0.2.5 (corresponds to MJLE-0.2.5)

Available to download through <https://mochijump.com>

## Notes

Any version older than 0.2.5 will no longer be able to import levels due to encryption

## Misc

This repository holds my first platformer game built with Java.

This project uses Gradle to manage it's dependencies

/dist holds a runnable jar of a recent version build of the game 

Quick link to download runnable jar: <https://github.com/AndoryuRenoa/MochiJump/blob/master/dist/MochiJump.jar?raw=true>

The MochiJump App now can import customer levels built on <http://mochijump.com>

Recently done:

Added version monitoring

Focused camera to zoom in on and follow Mochi (improved).

Mochi now bounces off certain NPC objects upon destruction.

YLimiter object now defines how far down Mochi can fall before the game ends

Compiled using gradle so dependencies are no longer an issue for reproduction form source code

created gradle built fat jar // this file is almost half the size

Finished Goose NPC

To Do:

Created Launch4J wrapper // tested and works, needs (maybe! .bmp for splash screen and) .ico for icon

remove stray JLabel from DogLogic Panel
