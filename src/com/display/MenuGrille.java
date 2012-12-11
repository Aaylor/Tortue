package com.display;

import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MenuGrille extends JDialog {

	private JLabel labWidth = new JLabel("Largeur des cases de la grille :"); 
	private JLabel labHeight = new JLabel("Hauteur Horizontal des cases de la grille :");
	private JFormattedTextField textFieldWidth;
	private JFormattedTextField textFieldHeight;
	private JButton buttonEnregistrer = new JButton("Enregistrer");
	private JButton buttonAnnuler = new JButton("Annuler");
	
	
	public MenuGrille(){
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setMaximumIntegerDigits(3);
		textFieldWidth = new JFormattedTextField(format);
		textFieldHeight = new JFormattedTextField(format);
		
		Box form = Box.createVerticalBox();
		form.add(labWidth);
		form.add(textFieldWidth);
		form.add(labHeight);
		form.add(textFieldHeight);
		
		Box validerAnnuler = Box.createHorizontalBox();
		validerAnnuler.add(buttonEnregistrer);
		validerAnnuler.add(buttonAnnuler);
		
		JScrollPane jScroll = new JScrollPane(form, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(form);
		this.add(validerAnnuler);
		this.pack();
		this.setVisible(true);		
	}
}
