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

## graveyards.yml Tutorial

- The main section must be named the same as the name of the grave yard otherwise it will not work correctly!

- The 'world:' Section is for the name of the world that the graveyard is in

- The 'ID:' key can be any number that is not the same as the others. 

- And for the x, y, and z sections are the corresponding coordinates. 

```
Pixar_Memorial_Grave: #This must be the same as the Name!!!
  name: Pixar_Memorial_Grave  #Name of the grave yard
  world: world  #World that it is located in must be the name of the world
  ID: 3 #ID of the graveyard
  x: 136
  y: 64
  z: 225
Memorial_Park:  #This must be the same as the name
  name: Memorial_Park  #This is the name of the graveyard 
  world: world #This is the name of the world the graveyard is in
  ID: 2 #This is the id it can not have the same id as another
  x: 17
  y: 70   #These are the coordinates
  z: 404
```
