# GraveYardz

## Description

A plugin in which you get teleported to the nearest graveyard from where you died, You can create, delete, and change locations of them. Originally Developed for Inclusive Rec server but will work for others.

## Introduction 

In this plugin you can create, change, and remove Graveyards from your world. Graveyards are locations stored in the plugin that aren't protected you will have to protect them

with either WorldGuard, GriefPrevention or some other external means. 

You can edit the grave-yard files directly or you can used the commands provided to change and add grave yards given you follow the syntax. 

### Commands

  - /gv create (name) (id) - creates a grave yard
  - /gv set (name) - changes the location of a grave yard
  - /gv del (name) - deletes a graveyard
  - /gv reload - reload the main config file
  - /gv info - displays plugin information
  - /gv help - displays all these commands and this information
  - /gv tp - teleports you to the nearest grave yard

## Permissions 

  - graveyard.admin - Gives access to all the commands.
  - graveyard.create - Gives access to '/gv create'.
  - graveyard.set - gives access to the '/gv set' command.
  - graveyard.reload - gives access to the '/gv reload' command to reload the main config file. 
  - graveyard.tp - gives access to the '/gv tp' command. 
  - graveyard.del - gives access to the '/gv del' command. 
  - graveyard.exempt - Players with this permission will just respawn normally and not respawn at the nearest graveyard.

## graveyards.yml Tutorial

- This isn't necessary, but might be useful to some, as commands do the same thing automatically, but some people may want to know

- The main section must be named the same as the name of the grave yard otherwise it will not work correctly!

- The 'world:' Section is for the name of the world that the graveyard is in

- The 'ID:' key can be any number that is not the same as the others. 

- And for the x, y, and z sections are the corresponding coordinates. 

```
memorial_park: # Name must be the same as the section
  name: memorial_park  # Name of the graveyard
  location:    # Location section containing data for the location 
    world: world
    x: 23.754032567142353
    y: 71.0
    z: 336.5333318830833
    pitch: 11.265702
    yaw: 68.88659
  ID: 1   # Can be anything just a unique number for this graveyard
```

## Main Config

This is just the default config file, as you can see there are some default values.

- 'respawnMessage:' Is just the message the player gets sent when they respawn at a grave yard
- 'teleportMessage:' This is the message the player gets sent when they use the /gv tp command to teleport to a grave yard 
  
Traditional Essentials based color codes will work as well. 

```
#<name> is the name of the grave yard they spawn at
respawnMessage: '&cRespawned at <name> Grave yard'
teleportMessage: '&aTeleported to <name>'
```
