# Conway-s-Game-of-Life

Conway's Game of Life is a "zero-player" game.  It begins with a grid.  Each 
square on the grid can potentially have a "cell".  If the cell has too many 
living cells around it, it dies of overcrowding.  If it has too few, it dies
of loneliness. If a an empty spot on the grid is surrounded by the right number 
of cells, it comes to life.  For more information, see 
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life .

This implementation of the Game of Life allows the user to add and delete cells 
from the grid by clicking on the corresponding grid square.  The user then runs 
iterations of the game by clicking outside the grid area.
