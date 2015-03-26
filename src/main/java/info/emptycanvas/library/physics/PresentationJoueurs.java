package info.emptycanvas.library.physics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PresentationJoueurs extends DataPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3370978043835420098L;
	private JButton joueur1 = new JButton("");
	private JButton joueur2 = new JButton("");
	private JButton joueur3 = new JButton("");
	private JButton joueur4 = new JButton("");
	
	GameApplet gp;

	
	public PresentationJoueurs(final GameApplet gp) {
		super();
		this.gp = gp;
		
		joueur1.setText(gp.getJoueurs().get(0).nom);
		joueur2.setText(gp.getJoueurs().get(1).nom);
		joueur3.setText(gp.getJoueurs().get(2).nom);
		joueur4.setText(gp.getJoueurs().get(3).nom);
		joueur1.setBackground(gp.getJoueurs().get(0).color);
		joueur2.setBackground(gp.getJoueurs().get(1).color);
		joueur3.setBackground(gp.getJoueurs().get(2).color);
		joueur4.setBackground(gp.getJoueurs().get(3).color);
		joueur1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				gp.replaceGUI(new JoueurDescription(0, gp));
			}
			
		});
		joueur2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				gp.replaceGUI(new JoueurDescription(1, gp));
			}
			
		});
		joueur3.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				gp.replaceGUI(new JoueurDescription(2, gp));
			}
			
		});
		joueur4.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				gp.replaceGUI(new JoueurDescription(3, gp));
			}
			
		});
		

		add(joueur1);
		add(joueur2);
		add(joueur3);
		add(joueur4);
		
	}
	public void updateData()
	{
	joueur1 = new JButton(gp.getJoueurs().get(0).nom != null ? gp.getJoueurs().get(0).nom : "Joueur 1");
	joueur2 = new JButton(gp.getJoueurs().get(1).nom != null ? gp.getJoueurs().get(1).nom : "Joueur 2");
	joueur3 = new JButton(gp.getJoueurs().get(2).nom != null ? gp.getJoueurs().get(2).nom : "Joueur 3");
	joueur4 = new JButton(gp.getJoueurs().get(3).nom != null ? gp.getJoueurs().get(3).nom : "Joueur 4");
	joueur1.setBackground(gp.getJoueurs().get(0).color);
	joueur2.setBackground(gp.getJoueurs().get(1).color);
	joueur3.setBackground(gp.getJoueurs().get(2).color);
	joueur4.setBackground(gp.getJoueurs().get(3).color);
	}
	@Override
	public void buildGUI() {
		super.buildGUI();
	}
}
