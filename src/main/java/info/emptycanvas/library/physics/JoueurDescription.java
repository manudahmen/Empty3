package info.emptycanvas.library.physics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class JoueurDescription extends DataPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3560368014780722435L;
	private GameApplet gp;
	int n;
	
	public JoueurDescription(final int n, final GameApplet gp)
	{
		this.n = n;
		this.gp = gp;
		
		final JColorChooser cc = new JColorChooser();
		
		cc.setColor(gp.getJoueurs().get(n).color);
		
		add(cc);
		add(gp.getInventaires().get(gp.getJoueurs().get(n)));
		gp.getInventaires().get(gp.getJoueurs().get(n)).updateData();
		//gp.getInventaires().get(gp.getJoueurs().get(n)).buildGUI();
		
		final JTextArea nomJ = new JTextArea(gp.getJoueurs().get(n).nom);
		
		
		
		add(nomJ);
		
		JButton enregistrer = new JButton("Enregistrer");
		
		JButton annuler = new JButton("Annuler");
		
		add(enregistrer);
		
		add(annuler);
		
		
		enregistrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gp.replaceGUI(new PresentationJoueurs(gp));
				gp.getJoueurs().get(n)
					.nom =  nomJ.getText();
				gp.getJoueurs().get(n)
					.color =  cc.getColor();
				
				gp.replaceGUI(new PresentationJoueurs(gp));
			}
		});
		annuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gp.replaceGUI(new PresentationJoueurs(gp));
				
			}
		});
	}
}
