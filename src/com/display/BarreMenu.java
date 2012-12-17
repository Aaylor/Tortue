package com.display;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.controleur.Controleur;

/**
 *  Barre de menu
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 *  @author Gauthier Lo
 *  @version 1.0
 */
@SuppressWarnings("serial")
public class BarreMenu extends JMenuBar{
	
	private static Controleur controleur;
	//JMenu "Fichier"
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem nouveau = new JMenuItem("Nouveau");
	private JMenuItem ouvrirImage = new JMenuItem("Ouvrir une image");
	private JMenuItem ouvrirHistorique = new JMenuItem("Ouvrir un historique");
	private JMenuItem enregistrerImage = new JMenuItem("Enregistrer l'image");
	private JMenuItem enregistrerHistorique = new JMenuItem("Enregistrer l'historique");
	private JMenuItem quitter = new JMenuItem("Quitter");
    
	//JMenu Edition
	private JMenu menuEdition = new JMenu("Edition");
	private JMenuItem precedent = new JMenuItem("Précédent");
	private JMenuItem suivant = new JMenuItem("Suivant");
	
	//JMenu "Affichage"
	private JMenu menuAffichage = new JMenu("Affichage");
	private static JMenuItem activerLaGrille = new JMenuItem("Afficher la grille");
	private static JMenuItem desactiverLaGrille = new JMenuItem("Désactiver la grille");
	private static JMenuItem magnetisme = new JMenuItem("Magnetisme à la grille");
	private static JMenuItem modePixelArt = new JMenuItem("Mode Pixel Art");
	private static JMenuItem desactiverModePixelArt = new JMenuItem("Désactiver le mode Pixel Art");
	
    //JMenu "Outils"
	private JMenu menuOutils = new JMenu("Outils");
	private JMenuItem leverPoserOutil = new JMenuItem("Lever/Poser l'outil");
	private JMenuItem gommeCrayon = new JMenuItem("Gomme/Crayon");
	private JMenuItem changerFormeOutil = new JMenuItem("Changer la forme de l'outil");
	
	private JMenuItem options = new JMenuItem("Paramètres");
	private JMenuItem aPropos = new JMenuItem("A propos");
	
	//Partie Informative
	private JMenuItem separateur = new JMenuItem();
	private static JMenu positionTortue = new JMenu("");
	
    /**
     *  Constructeur de la Barre d'Outils
     */
	BarreMenu(){
		super();
		
		//Menu "Fichier"
		this.add(menuFichier);
		menuFichier.add(nouveau);
		menuFichier.add(ouvrirHistorique);
		menuFichier.add(ouvrirImage);
        menuFichier.add(enregistrerHistorique);
        menuFichier.add(enregistrerImage);
		menuFichier.add(quitter);
		
		//Menu Edition
		this.add(menuEdition);
		menuEdition.add(precedent);
		menuEdition.add(suivant);
		
		//Menu Affichage
		this.add(menuAffichage);
		menuAffichage.add(activerLaGrille);
		menuAffichage.add(desactiverLaGrille);
		desactiverLaGrille.setEnabled(false);
		menuAffichage.add(magnetisme);
		magnetisme.setEnabled(false);
		menuAffichage.addSeparator();
		menuAffichage.add(modePixelArt);
		menuAffichage.add(desactiverModePixelArt);
		desactiverModePixelArt.setEnabled(false);
		
		//Menu "Outils"
		this.add(menuOutils);
		menuOutils.add(leverPoserOutil);
		menuOutils.add(gommeCrayon);
		menuOutils.add(changerFormeOutil);
		menuOutils.addSeparator();
		menuOutils.add(options);
		menuOutils.add(aPropos);
		
		//Partie informative
		this.add(separateur);
		this.add(positionTortue);
		
		//Ajout des raccourcis
		//menuFichier.setMnemonic('F');
		menuOutils.setMnemonic('O');
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		ouvrirHistorique.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		ouvrirImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		enregistrerHistorique.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		enregistrerImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		aPropos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, KeyEvent.CTRL_MASK));
		suivant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		precedent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		leverPoserOutil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));
		gommeCrayon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		changerFormeOutil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		
		//Ajout des Action Listener
		nouveau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				nouveau();
			}
		});
		ouvrirImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				ouvrirImage();
			}
		});
		ouvrirHistorique.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				ouvrirHistorique();
			}
		});
		enregistrerImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				enregistrerImage();
			}
		});
		enregistrerHistorique.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				enregistrerHistorique();
			}
		});
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				quitter();
			}
		});
		options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				MenuOption menuOption = new MenuOption(null, "Option", true);
			}
		});
		aPropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				aPropos();
			}
		});
		activerLaGrille.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				activerGrille();
			}
		});
		magnetisme.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				activerMagnetisme();
			}
		});
		modePixelArt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				activerPixelArtMode();
			}
		});
		precedent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.commande("undo", true, false, true);
			}
		});
		suivant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.commande("redo", true, false, true);
			}
		});
		leverPoserOutil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.penUpOrPenDown();
			}
		});
		gommeCrayon.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.pencilOrEraser();
			}
		});
		changerFormeOutil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.commande("shape", true, false, true);
			}
		});
		desactiverLaGrille.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.commande("disablegrid", true, false, true);
			}
		});
		desactiverModePixelArt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controleur.commande("disablepixelart", true, false, true);
			}
		});
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Nouveau" */
	private void nouveau(){
		controleur.commande("new", true, false, true);
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Ouvrir une Image" */
	private void ouvrirImage(){
		controleur.commande("open", true, false, true);
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Ouvrir un Historique" */
	private void ouvrirHistorique(){
		controleur.commande("exec", true, false, true);
	}	
	
	/**Fonction appelée lors du clic sur le JMenuItem "Enregistrer une Image" */
	private void enregistrerImage(){
		controleur.commande("save", true, false, true);
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Ouvrir un Historique" */
	private void enregistrerHistorique(){
		controleur.commande("savehistory", true, false, true);
	}
	
	/**Entre la commande "exit" dans le terminal, pour fermer le programme*/
	public void quitter(){
        controleur.commande("exit", false, false, true);
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "A Propos" */
	private void aPropos(){
		JOptionPane.showMessageDialog(null,
								"Carapuce est un projet Universitaire développé par 4 étudiants :\n\n" +
								"Mehdi Khelifi\n" +
								"Loïc Runarvot\n" +
								"Gauthier Lo\n",
								"A propos",
								JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Activer la grille" */
	private void activerGrille(){
		controleur.commande("grid", true, false, true);
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Activer le magnetisme a la grille" */
	private void activerMagnetisme(){
		controleur.alternateMagnetism();
	}
	
	/**Fonction appelée lors du clic sur le JMenuItem "Activer le mode pixel art" */
	private void activerPixelArtMode(){
		controleur.commande("pixelart", true, false, true);
	}
	
	/**Fonction affichant l'icone "check" devant "Afficher la grille" selon le boolean passe en parametre
	 * @param active Boolean definissant l'affichage de l'icone "check" ou non
	 * */
	public void affichageGrille(boolean  active){
		if(active){
			activerLaGrille.setIcon(new ImageIcon("../img/ok.png"));
		}
		else activerLaGrille.setIcon(new ImageIcon(""));
	}
	
	/**Fonction activant ou non le JMenuItem "Desactiver la grille"
	 * @param active : Booleen definissant l'activation du JMenuItem "Desactiver la grille */
	public void affichageDesactiverLaGrille(boolean  active){
		desactiverLaGrille.setEnabled(active);
	}
	
	/**Fonction affichant l'icone "check" devant "Magnetisme a la grille" selon le boolean passe en parametre
	 * @param active Boolean definissant l'affichage de l'icone "check" ou non */
	public void affichageMagnetisme(boolean  active){
		if(active){
			magnetisme.setIcon(new ImageIcon("../img/ok.png"));
		}
		else magnetisme.setIcon(new ImageIcon(""));
	}
	
	/**Fonction activant ou non le JMenuItem "Desactiver le magnetisme"
	 * @param active : Booleen definissant l'activation du JMenuItem "Desactiver le magnetisme" */
	public void setMagnetismeDisponible(boolean disponible){
		if(disponible){
			magnetisme.setEnabled(true);
		}
		else{
			magnetisme.setEnabled(false);
			affichageMagnetisme(false);
		}
	}
	
	/**Fonction affichant l'icone "check" devant "Mode Pixel Art" selon le boolean passe en parametre
	 * @param active Boolean definissant l'affichage de l'icone "check" ou non */
	public void affichagePixelArt(boolean  active){
		if(active){
			modePixelArt.setIcon(new ImageIcon("../img/ok.png"));
		}
		else modePixelArt.setIcon(new ImageIcon(""));
	}
	
	/**Fonction affichant l'icone "check" devant "Magnetisme a la grille" selon le boolean passe en parametre
	 * @param active Boolean definissant l'affichage de l'icone "check" ou non */
	public void affichageDesactiverPixelArt(boolean  active){
		desactiverModePixelArt.setEnabled(active);
	}
	


	  ////////////////////////////////////////////
	 //              ACCESSEURS                //        
	////////////////////////////////////////////
	public JMenuItem getPrecedent() {
		return precedent;
	}

	public JMenuItem getSuivant() {
		return suivant;
	}
	
	  ////////////////////////////////////////////
	 //              MODIFIEURS                //
	////////////////////////////////////////////
	/**Modifieur du controleur
     *  @param c nouveau controleur */
    public void setControleur(Controleur c){
        this.controleur = c;
    }
    
	public static void setTextPositionTortue(int x, int y){
		positionTortue.setText("Position  X : " + x + " Y : " + y);
	}
}
