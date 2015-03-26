package info.emptycanvas.library.physics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GamePanel extends DataPanel {
	protected JSlider [] s;
	private GameApplet gp;
	private int joueur;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1552746400473185110L;

	
	public GamePanel(GameApplet gp)

	{
		this.gp = gp;
	}
	
	public void updateData( )
	{
		
		for(int i= 0; i<3; i++)
		{
			add(new JLabel("Joueur: "+gp.getJoueurs().get(i)));
			add(s[0] = new JSlider(gp.getJoueurs().get(i).billes));
		}
		final JSlider mise ;
		add(new JLabel("Mise pour:"));
		add(mise = new JSlider(0,100));
		mise.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(gp.getJoueurs().get(joueur()).billes > mise.getValue())
				{
					gp.getJoueurs().get(joueur()).billes -= mise.getValue();
				}
				
			}

		});
		
		JButton suiv = new JButton("Suivant");
		suiv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finTour();
			}
		});
		add(suiv);
		
	}
	public int joueur() {
		return joueur;
	}
	
	public void finTour()
	{
		joueur = (joueur++%4);
	}
}
