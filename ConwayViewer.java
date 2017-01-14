import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ConwayViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ConwayComponent component = new ConwayComponent();
		
		//mouse commands
		class MousePressListener implements MouseListener
		{
			public void mousePressed(MouseEvent event) 
			{
				int x = event.getX();
				int y = event.getY();
				component.mouseClick(x, y);
			}
					
			public void mouseReleased(MouseEvent event) {}
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		}
				
		MouseListener listener = new MousePressListener();
		component.addMouseListener(listener);

		
		JFrame frame = new JFrame();
		final int FRAME_WIDTH = 300;
		final int FRAME_HEIGHT = 400;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Conway's Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.add(component);
		frame.setVisible(true);
		
		
	}

}
