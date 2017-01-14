import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.ArrayList;

public class ConwayComponent extends JComponent{

	private static final long serialVersionUID = 1L;
public final int WIDTH_IN_CELLS = 30;
public final int HEIGHT_IN_CELLS = 20;
//private final int margin = 25;
private ConwayCell[][] arrayOfCells;
private double cornerX;
private double cornerY;
//private double height;
//private double width;
private int cellWidth;
private int generations = 0;
	
	public ConwayComponent()
	{
		arrayOfCells = new ConwayCell[WIDTH_IN_CELLS][HEIGHT_IN_CELLS];
		fillArrayWithCells(arrayOfCells);
		
		
		
		
		
		//ancient test cases
		
		//ConwayCell cell1 = arrayOfCells[15][10];
		//cell1.bringToLife();
		//ConwayCell cell2 = arrayOfCells[16][11];
		//cell2.bringToLife();
		//ConwayCell cell3 = arrayOfCells[16][12];
		//cell3.bringToLife();
		//ConwayCell cell4 = arrayOfCells[15][12];
		//cell4.bringToLife();
		//ConwayCell cell5 = arrayOfCells[14][12];
		//cell5.bringToLife();
		
		//generation();
		//generation();
		//generation();
		//generation();
		
	}

	public void paintComponent(Graphics g)
	{
		//System.out.println("paintComponent has been reached");
		Graphics2D g2 = (Graphics2D) g;
		
		//This section deals with the window resizing
		/**if (getWidth() > getHeight() * (HEIGHT_IN_CELLS / WIDTH_IN_CELLS))
		{
		height = getHeight() - (margin * 2);
		cornerY = margin;
		width = height * (HEIGHT_IN_CELLS / WIDTH_IN_CELLS);
		cornerX = (getWidth() - width) / 2;
		}
		else 
		{
		width = getWidth() - (margin * 2);
		cornerX = margin;
		height = width * (WIDTH_IN_CELLS / HEIGHT_IN_CELLS);
		cornerY = (getHeight() - height) / 2;
		}*/
		
		//cellWidth = width / HEIGHT_IN_CELLS;
		cellWidth = 25;
		cornerX = 0;
		cornerY = 0;
		
		
		//this part displays all of the cells
		for (int i = 0; i <= WIDTH_IN_CELLS - 1; i++)
		{
			for (int j = 0; j <= HEIGHT_IN_CELLS - 1; j++)
			{
				ConwayCell thisCell = arrayOfCells[i][j];
				thisCell.displayCell(cellWidth, cornerX, cornerY, g2);
			}
		}
		
		String clickHereLabel = new String ("Click anywhere outside of the grid to run a generation");
		String generationLabel = new String("Generations: " + generations);
		g2.drawString(clickHereLabel, (WIDTH_IN_CELLS * cellWidth + 25), 25);
		g2.drawString(generationLabel, (WIDTH_IN_CELLS * cellWidth + 25), 50);

	}
	
	/**fills the array with Conway cells*/
	public void fillArrayWithCells(ConwayCell[][] arr)
	{
		//System.out.println("fillArrayWithCells has been reached");
		for (int i = 0; i <= WIDTH_IN_CELLS - 1; i++)
		{
			for (int j = 0; j <= HEIGHT_IN_CELLS - 1; j++)
			{
				arr[i][j] = new ConwayCell(i, j);
			}
		}
	}
	
	/**assembles an arrayList of all the neighbors of a given cell
	 * @param cell the cell who's neighbors we want
	 * @return an arrayList of all the neighbors
	 */
	public ArrayList<Boolean> makeNeighborArrayList(ConwayCell cell)
	{
		int cellX = cell.getX();
		int cellY = cell.getY();
		ArrayList<Boolean> neighbors = new ArrayList<Boolean>();
		
		for (int i = cellX - 1; i <= cellX + 1; i++)
		{
			if (i >= 0 && i < WIDTH_IN_CELLS)
				{for (int j = cellY - 1; j <= cellY + 1; j++)
					{
						if (j >= 0 && j < HEIGHT_IN_CELLS)
						{
							if (!(i == cellX) || !(j == cellY))
							{
								ConwayCell thisCell = arrayOfCells[i][j];
								boolean aliveOrNot = thisCell.isAlive();
								neighbors.add(aliveOrNot);
							}
						}
					}
				}
		}
		
		return neighbors;
	}
	
	/**refreshes all of the cells based on their neighbors*/
	public void generation()
	{
		//makes an array of all of the neighbor ArrayLists
		//Ignore the yellow dotted line, it works anyway.
		ArrayList<Boolean>[][] arrayOfNeighbors = new ArrayList[WIDTH_IN_CELLS][HEIGHT_IN_CELLS];
		for (int i = 0; i < WIDTH_IN_CELLS; i++)
		{
			for (int j = 0; j < HEIGHT_IN_CELLS; j++)
			{
				ConwayCell thisCell = arrayOfCells[i][j];
				ArrayList<Boolean> neighbors = makeNeighborArrayList(thisCell);
				arrayOfNeighbors[i][j] = neighbors;
			}
		}
		
		//uses each of the neighbor ArrayLists to refresh its corresponding cell
		for (int i = 0; i < WIDTH_IN_CELLS; i++)
		{
			for (int j = 0; j < HEIGHT_IN_CELLS; j++)
			{
				ConwayCell thisCell = arrayOfCells[i][j];
				ArrayList<Boolean> neighbors = arrayOfNeighbors[i][j];
				thisCell.cellGeneration(neighbors);
			}
		}
		
		generations++;
		repaint();
	}
	
	/**returns the number of generations so far
	 * @return number of generations so far*/
	public int getGenerations()
	{
		return generations;
	}
	
	/**deals with mouse clicks
	 * @param x the x coordinate of the mouse click
	 * @param y the y coordinate of the mouse click
	 */
	public void mouseClick(int x, int y)
	{
		if (x > WIDTH_IN_CELLS * cellWidth || y > HEIGHT_IN_CELLS * cellWidth)
		{
			generation();
		}
		else
		{
		ConwayCell clickedCell = getCellFromCoordinates(x, y);
		if (clickedCell.isAlive())
		{
			clickedCell.kill();
			repaint();
		}
		else 
		{
			clickedCell.bringToLife();
			repaint();
		}
		}
	}
	
	/**Given raw x and y coordinates, this gives the cell that was clicked
	 * @param x the x coordinate that was clicked
	 * @param y the y coordinate that was clicked
	 */
	public ConwayCell getCellFromCoordinates(int x, int y)
	{
		int cellX = x / cellWidth;
		int cellY = y / cellWidth;
		ConwayCell cell = arrayOfCells[cellX][cellY];
		return cell;
	}
	
}
