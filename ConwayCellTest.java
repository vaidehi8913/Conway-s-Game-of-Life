import static org.junit.Assert.*;

import org.junit.Test;

public class ConwayCellTest {

	@Test
	public void testIsAlive() 
	{
		//fail("Not yet implemented");
			
		int x = 3;
		int y = 4;
		ConwayCell cell = new ConwayCell(x, y);
		assertTrue(cell.isAlive());
	}

}
