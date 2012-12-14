package com.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import com.controleur.Controleur;


public class BarreOutilsVignette extends JButton {
	Curseur c;
	Controleur controleur;
	
	/**Constructeur de la Vignette representant la couleur du curseur*/
	BarreOutilsVignette(Curseur c){
		super();
		this.setSize(15, 15);
		this.c = c;
		
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Color couleur = JColorChooser.showDialog(null, "Choix de la couleur", Color.WHITE);
				if(couleur != null)
					controleur.commande("setcolor " + couleur.getRed() + " " + couleur.getGreen() + " " + couleur.getBlue() + " " + couleur.getAlpha(), true, true, true);
			}
		});
	}
	
	public void paintComponent(Graphics g){
		Color couleur = new Color(c.getCouleur().getRed(), c.getCouleur().getGreen(), c.getCouleur().getBlue());
		g.setColor(couleur);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.gray);
		g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
	}
	
	public void setControleur(Controleur c){
		this.controleur = c;
	}
}
