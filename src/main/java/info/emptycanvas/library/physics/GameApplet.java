package info.emptycanvas.library.physics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Action;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Popup;


public class GameApplet extends JApplet implements Runnable

{

	class ButtonStart extends GamePanel
	{

		public ButtonStart(final GameApplet gp) {
			super(gp);
			startGame = new JButton("D�marrer la partie");

			add(startGame);

			startGame.addActionListener(new ActionListener() {
				

				@Override
				public void actionPerformed(ActionEvent e) {
					GamePanel ga = new GamePanel(gp);
					ga.setGameApplet(gp);
					replaceGUI(ga);
					
				}
			});
		}
		
		
	}
	
	
	private GameApplet gp = this;
	private JPanel old;
	protected Jeu jeu;
	protected List<Joueur> joueurs;
	protected Hashtable<Joueur, Inventaire> inventaires = new Hashtable<Joueur, Inventaire>();
	private JButton startGame;
	private BallePanel bp;
	private boolean done = false;
	private long interval = 200;
	private boolean pause = false;
	private Thread flow;
	public void replaceGUI(DataPanel newP)
	{
		if(old!=null)
			remove(old);
		
		newP.updateData();
		
		old = newP;
		add(newP);

		//newP.buildGUI();
		
		paintComponents(getGraphics());
	
		repaint();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2729176474797816893L;

	
	public void init()
	{
		joueurs = new ArrayList<Joueur>();
		
		joueurs.add(new Joueur("Joueur 1", Color.RED));
		joueurs.add(new Joueur("Joueur 2", Color.GREEN));
		joueurs.add(new Joueur("Joueur 3", Color.BLUE));
		joueurs.add(new Joueur("Joueur 4", Color.YELLOW));
		
		inventaires.put(joueurs.get(0), new Inventaire());
		inventaires.put(joueurs.get(1), new Inventaire());
		inventaires.put(joueurs.get(2), new Inventaire());
		inventaires.put(joueurs.get(3), new Inventaire());
		
		add ( old = new PresentationJoueurs(this));
		JPopupMenu pp =new JPopupMenu("D�marrer");
		pp.add(new JMenuItem(new Action()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				gp.add(new ButtonStart(gp));
				
			}

			@Override
			public Object getValue(String key) {
				return null;
			}

			@Override
			public void putValue(String key, Object value) {
				
			}

			@Override
			public void setEnabled(boolean b) {
				
			}

			@Override
			public boolean isEnabled() {
				return false;
			}

			@Override
			public void addPropertyChangeListener(
					PropertyChangeListener listener) {
				
			}

			@Override
			public void removePropertyChangeListener(
					PropertyChangeListener listener) {
				
			}}));
		final JButton demarrer = new JButton("D�marrer");
		
		demarrer.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(demarrer);
				bp = new BallePanel();
				replaceGUI(bp);
				new Thread(bp).start();
				
			}
			
		});

		
		add(demarrer);
	}

	public synchronized Jeu getJeu() {
		return jeu;
	}


	public synchronized List<Joueur> getJoueurs() {

		return joueurs;
	}


	public Hashtable<Joueur, Inventaire> getInventaires() {
		
		return inventaires;
	}

	 public void run() {
	        try {
	            while (!done) {
	               Thread.sleep(interval); 
	            }
	        }
	        catch (InterruptedException e){};
	    }
	 
	    public void start() {
	        done = false;
	        pause = false;
	        flow = new Thread(this);
	        flow.start();
	    }

	    public void stop() {
	       done = true;
	       flow.stop();
	    }

}
