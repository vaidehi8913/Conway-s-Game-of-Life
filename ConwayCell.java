import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class ConwayCell {
	
private static final Logger log = Logger.getLogger( ConwayCell.class.getName());
	
private int livingStatus;
private int indexX;
private int indexY;
public final int CELL_ALIVE = -1;
public final int CELL_DEAD = -2;

/**Creates a conway cell at given x and y coordinates, it is dead by default
 * @param x x coordinate of cell on cell grid
 * @param y y coordinate of cell on cell grid*/
public ConwayCell(int x, int y)
{
	//System.out.println("ConwayCell constructor has been reached");
	livingStatus = CELL_DEAD;
	indexX = x;
	indexY = y;
	log.log(Level.INFO, "Cell has been made");
}

/**Returns true if the cell is alive and false if the cell is dead
 * @return whether the cell is alive
 */
public boolean isAlive()
{
	return (livingStatus == CELL_ALIVE);
}

/**Based off of it's ArrayList of neighbors (boolean), the cell decides whether or not to live
 * @param an ArrayList containing a boolean value for each of it's neighbors as to whether or not they are alive*/
public void cellGeneration(ArrayList<Boolean> neighbors)
{
	int lastArrayIndex = neighbors.size() - 1;
	int livingNeighbors = 0;
	
	for (int i = 0; i <= lastArrayIndex; i++)
	{
		if (neighbors.get(i))
		{
			livingNeighbors = livingNeighbors + 1;
		}
	}
	
	if (livingNeighbors >= 4)
		livingStatus = CELL_DEAD;
	else if (livingNeighbors == 3)
		livingStatus = CELL_ALIVE;
	else if (livingNeighbors <= 1)
		livingStatus = CELL_DEAD;
	//yes, I've thought about the case where livingNeighbors == 2 and I purposely want to do nothing
}


/**has the cell display itself
 * @param cellWidth width of the cell (and height)
 * @param screenCornerX this is the X coordinate of the top left corner of the whole grid
 * @param screenCornerY this is the Y coordinate of the top left corner of the whole grid
 * @param g2 the Graphics2D object
 */
public void displayCell(double cellWidth, double screenCornerX, double screenCornerY, Graphics2D g2)
{
	//System.out.println("displayCell has been reached");
	double cellCornerX = indexX * cellWidth;
	double cellCornerY = indexY * cellWidth;
	
	//System.out.println(cellWidth);
	
	Rectangle gridSpace = new Rectangle((int) cellCornerX, (int) cellCornerY, (int) cellWidth, (int) cellWidth);
	g2.draw(gridSpace);
	
	double cellCircleMargin = .1;
	double cellCircleWidth = (1 - cellCircleMargin * 2) * cellWidth;
	double cellCircleCornerX = cellCornerX + cellWidth * cellCircleMargin;
	double cellCircleCornerY = cellCornerY + cellWidth * cellCircleMargin;
	
	if (livingStatus == CELL_ALIVE)
	{
		Ellipse2D.Double cellCircle = new Ellipse2D.Double(cellCircleCornerX, cellCircleCornerY, cellCircleWidth, cellCircleWidth);
		g2.draw(cellCircle);
		g2.fill(cellCircle);
	}
	
	//Logger.global.info("dispalyCell has been reached");
}

/**brings a cell to life*/
public void bringToLife()
{
	livingStatus = CELL_ALIVE;
}

/**kills a cell*/
public void kill()
{
	livingStatus = CELL_DEAD;
}

/**returns the x value of the cell
 * @return the cells x value (as per the cell grid)
 */
public int getX()
{
	return indexX;
}

/**returns the y value of the cell
 * @return the cells y value (as per cell grid)
 */
public int getY()
{
	return indexY;
}

}
