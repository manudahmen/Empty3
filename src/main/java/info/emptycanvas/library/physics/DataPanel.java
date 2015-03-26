package info.emptycanvas.library.physics;
import javax.swing.JPanel;


public class DataPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8643829020005475144L;
	private GameApplet gp;
	public void setGameApplet(GameApplet gp)
	{
		this.gp = gp;
	}
	public void updateData() {
	}
	public void buildGUI() {
		paintComponent(getGraphics());
	}
}
