# Simulations
This project was created as student assignment.

It contains three different simulations.

## Introduction
### Requirements
Project was created in **Java** version **14**.

### Source code
Source code for specific simulations is split between different packages.
Each simulation contain its own `main`.

| Simulation | Package | Main |
| --- | :---: | :---: |
| [Projectile motion](https://en.wikipedia.org/wiki/Projectile_motion) | ```projectile``` | ```projectile.ProjectileMotionSimulation``` |
| [Double pendulum](https://en.wikipedia.org/wiki/Double_pendulum) | ```doublependulum``` | ```doublependulum.DoublePendulumSimulation``` |
| [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) | ```gameoflife``` | ```gameoflife.GameOfLifeSimulation``` |

Part's of the source code is in common packages to allow for them to be reused.

## Projectile motion
Program allows simulating trajectory of [projectile motion](https://en.wikipedia.org/wiki/Projectile_motion). 

Multiple trajectories/throws can be simulated simultaneously.
Each new simulation can be started by `START` button, even when previous one is still running.

Every running simulation and trajectories of finished ones can be cleaned by `CLEAR` button.

## Double pendulum
Program allows simulating [double pendulum](https://en.wikipedia.org/wiki/Double_pendulum).

Pendulum leaves trace of the second bob.
This trace can be cleared by `CLEAR` button.

During the simulation you can modify the pendulum's parameters:
* length of both rods
* weights of both bobs
* friction of the medium

Changing the angle of either rod is possible only when simulation is not running.

## Conway's Game of Life
Program allows running [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

During the simulation you can change game rules, without the need to stop the execution.
You can also modify simulation speed.

During the run you can dynamically change state of each cell using left mouse button.

Default state of cells is random, however it can be cleared and prepared using the mouse.

Using `RANDOM` button you can create new, random cell state.

Using `SIZE` button you can change number of columns/rows of cells.
