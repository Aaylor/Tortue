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
import javax.swing.JScrollPane;

public class MenuGrille extends JDialog {

	private JLabel labWidth = new JLabel("Largeur des cases de la grille :"); 
	private JLabel labHeight = new JLabel("Hauteur Horizontal des cases de la grille :");
	private JFormattedTextField textFieldWidth;
	private JFormattedTextField textFieldHeight;
	private JButton buttonEnregistrer = new JButton("Enregistrer");
	private JButton buttonAnnuler = new JButton("Annuler");
	public static boolean itWorked;
	
	static int widthCaseDefined;
	static int heightCaseDefined;
	
	public MenuGrille(JFrame parent, boolean modal){
		super(parent, "Afficher la grille", modal);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
		initComponent();
		this.pack();
		this.setVisible(true);
		
	}
	
	public void initComponent(){
		itWorked = false;
		
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setMaximumIntegerDigits(3);
		textFieldWidth = new JFormattedTextField(format); textFieldWidth.setMaximumSize(new Dimension(55, 25));
		textFieldHeight = new JFormattedTextField(format); textFieldHeight.setMaximumSize(new Dimension(55, 25));
		
		Box form = Box.createVerticalBox();
		form.add(labWidth);
		form.add(textFieldWidth);
		form.add(labHeight);
		form.add(textFieldHeight);
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
		        setVisible(false);
		      }      
		    });
	}
	
	public void verificationDesValeurs(){
		if(!textFieldWidth.getText().equals("") && !textFieldHeight.getText().equals("") && Integer.parseInt(textFieldWidth.getText()) < 1  && Integer.parseInt(textFieldHeight.getText()) < 1){
			widthCaseDefined = Integer.parseInt(textFieldWidth.getText());
			heightCaseDefined = Integer.parseInt(textFieldHeight.getText());
			
			itWorked = true;
		}
	}
}
