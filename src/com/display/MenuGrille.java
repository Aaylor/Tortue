package com.display;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.controleur.Controleur;

/**
 *  Menu de la fonction grid
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 *  @author Gauthier Lo
 *  @version 1.0
 */
public class MenuGrille extends JDialog {	
	private JLabel labWidth = new JLabel("Largeur des cases de la grille :"); 
	private JLabel labHeight = new JLabel("Hauteur Horizontal des cases de la grille :");
	private JFormattedTextField textFieldWidth;
	private JFormattedTextField textFieldHeight;
	private JButton buttonEnregistrer = new JButton("Enregistrer");
	private JButton buttonAnnuler = new JButton("Annuler");
	
	public static boolean itWorked;
	public static int widthCaseDefined;
	public static int heightCaseDefined;
	private static boolean pixelArtDisplay;
	
	/**Constructeur de la boite de dialogue demandant les caracteristiques de la grille a afficher
	 * @param parent La JFrame mere
	 * @param modal
	 */
	public MenuGrille(JFrame parent, boolean modal){
		super(parent, "Afficher la grille", modal);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
		initComponent();
		this.pack();
		this.setVisible(true);
		
	}
	
	
	/**Initialise les composant de la boite de dialogue**/
	public void initComponent(){
		itWorked = false;
		
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setMaximumIntegerDigits(3);
		textFieldWidth = new JFormattedTextField(format); textFieldWidth.setMaximumSize(new Dimension(55, 25));
		textFieldHeight = new JFormattedTextField(format); textFieldHeight.setMaximumSize(new Dimension(55, 25));
		
		Box form = Box.createVerticalBox();
		form.add(labWidth);
		form.add(textFieldWidth);
		if(!pixelArtDisplay){
			form.add(labHeight);
			form.add(textFieldHeight);
		}
		JScrollPane jScroll = new JScrollPane(form, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		Box validerAnnuler = Box.createHorizontalBox();
		validerAnnuler.add(buttonEnregistrer);
		validerAnnuler.add(buttonAnnuler);
		
		Box total = Box.createVerticalBox();
		total.add(jScroll);
		total.add(validerAnnuler);
		
		this.getContentPane().add(total);
		this.pack();

		  /////////////////////////////////////////////////
		 //       INTERACTIONS AVEC LES BOUTONS         //
		/////////////////////////////////////////////////
		
		buttonEnregistrer.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  verificationDesValeurs();
		      }      
		    });
		buttonAnnuler.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	pixelArtDisplay = false;
		        setVisible(false);
		      }      
		    });
	}
	
	/**Fonction vérifiant les valeurs saisies par l'utilisateur**/
	public void verificationDesValeurs(){
		if(!pixelArtDisplay){
			if(!textFieldWidth.getText().equals("") && !textFieldHeight.getText().equals("") && Integer.parseInt(textFieldWidth.getText()) >= 1  && Integer.parseInt(textFieldHeight.getText()) >= 1){
				widthCaseDefined = Integer.parseInt(textFieldWidth.getText());
				heightCaseDefined = Integer.parseInt(textFieldHeight.getText());
				itWorked = true;
				
				setVisible(false);
			}
			else{
	    		JOptionPane.showMessageDialog(null, "Erreur", "Valeurs saisies incorrectes", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			if(!textFieldWidth.getText().equals("") && Integer.parseInt(textFieldWidth.getText()) >= 1){
				widthCaseDefined = Integer.parseInt(textFieldWidth.getText());
				heightCaseDefined = Integer.parseInt(textFieldWidth.getText());
				itWorked = true;
				
				setVisible(false);
			}
			else{
	    		JOptionPane.showMessageDialog(null, "Valeurs saisies incorrectes", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**Accesseur de la largeur des cases de la grille
	 * @return Largeur des cases de la grille
	 */
	public static int getWidthCaseDefined(){
		return widthCaseDefined;
	}
	
	/**Accesseur de la hauteur des cases de la grille
	 * @return Hauteur des cases de la grille
	 */
	public int getHeightCaseDefined(){
		return heightCaseDefined;
	}
	
	/**Modifieur du booleen activant l'affichage adapté au mode pixel art
	 * @param a Booleen representant l'affichage ou non du mode pixel art*/
	public static void setPixelArtDisplay(boolean a){
		pixelArtDisplay = a;
	}
	
	/**Accesseur du boolean represantant l'affichage adapté au mode pixel art
	 * @return Booleen representant l'affichage ou non du mode pixel art
	 */
	public static boolean getPixelArtDisplay(){
		return pixelArtDisplay;
	}
}
