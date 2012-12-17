package com.term;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color; //TEMPORAIRE, juste pour le positionnement
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

import com.controleur.Controleur;
import com.display.*;
import com.error.*;
import com.stockage.StockageDonnee;
import com.term.Terminal;
import com.utilitary.*;

/**
 *  Terminal permettant à l'utilisateur de rentrer les commandes
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 *  @author Gauthier Lo
 *  @version 1.0
 */
@SuppressWarnings("serial")
public class Terminal extends JPanel implements KeyListener{

    private static final JTextArea historique = new JTextArea("Bienvenue sur Carapuce, le logiciel de type Tortue.\n");
    private static final JScrollPane scroll_pane = new JScrollPane(historique,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    private Controleur controleur;
    private int compteur_commandes = -1;
    private JTextField champ_de_commande;

    private static final int[] bgColor = new int[] { 0, 0, 0 };
    private static final int[] fontColor = new int[] { 255, 255, 255 };
    private static final int[] cursorColor = new int[] { 255, 255, 255 };

    /**
     *  Constructeur du Terminal
     */
	public Terminal()
    {

		this.setBackground(Color.BLACK);//TEMPORAIRE, juste pour le positionnement
        addTerminal();
        this.setLayout(new BorderLayout());
        
        this.add(this.champ_de_commande, BorderLayout.SOUTH);
        this.add(Terminal.scroll_pane, BorderLayout.CENTER);
        
        this.champ_de_commande.addKeyListener(this);
    }
    

    /**
     *  Evenement lors de la pression des touches
     *  @param keyEvent Event.
     */
    public void keyPressed(KeyEvent keyEvent)
    {
        if ( keyEvent.getKeyCode() == KeyEvent.VK_ENTER
                && !this.champ_de_commande.getText().equals(""))
        {
            controleur.commande(this.champ_de_commande.getText(), true, true, true);
            
            this.champ_de_commande.setText("");
        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_UP)
        {
            
            if ( compteur_commandes - 1 >= 0 )
            {
                compteur_commandes--;
                this.champ_de_commande.setText(StockageDonnee.getLCEG(compteur_commandes));
            }        
            
        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_DOWN )
        {

            if ( compteur_commandes + 1 < StockageDonnee.getSize_LCEG() )
            {
                compteur_commandes++;
                this.champ_de_commande.setText(StockageDonnee.getLCEG(compteur_commandes));
            }
            else
            {
                if ( compteur_commandes + 1 < StockageDonnee.getSize_LCEG()+1 )
                {
                    compteur_commandes++;
                }
                this.champ_de_commande.setText("");
            }

        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_TAB 
                    && !this.champ_de_commande.getText().equals("") )
        {
            String s = "";
            String original = this.champ_de_commande.getText().trim();
            String s_replace = original;
            String display_prop = "";
       
            if ( s_replace.indexOf(" ") >= 0 
                    && ( original.toLowerCase().startsWith("repeat")
                        || original.toLowerCase().startsWith("man") ) )
            {
                s_replace = original.trim()
                    .replaceAll("\\[", "[ ").replaceAll("\\;", " ; ").replaceAll("\\]", " ] ").replaceAll("\\s{2,}", " ");
                String[] tmp = s_replace.split(" ");

                if ( original.toLowerCase().startsWith("man") && tmp.length > 2 )
                {
                    return;
                }

                s_replace = tmp[ tmp.length-1 ];

                s = original.substring( 0, original.length()-tmp[tmp.length-1].length());
            }

            ArrayList<String> proposition_completion = auto_completion( s_replace );

            if ( proposition_completion.size() == 1 )
            {
                this.champ_de_commande.setText(s + proposition_completion.get(0));
            }
            else if ( proposition_completion.size() > 1 )
            {
                String display_proposition = " > " + this.champ_de_commande.getText() + "\n";
                
                for ( String proposition : proposition_completion )
                {
                    display_proposition += "  " + proposition;
                }
                
                this.addMessage(display_proposition);
            }
            else;

        }

        else;

    }

    /**
     *  (IGNORE)
     *  @param keyEvent Event.
     */
    public void keyReleased(KeyEvent keyEvent)
    {
        /*  IGNORE  */
    }

    /**
     *  (IGNORE)
     *  @param keyEvent Event.
     */
    public void keyTyped(KeyEvent keyEvent)
    {
        /*  IGNORE  */
    }

    /**
     *  Ajoute le terminal, et ajoute les valeurs par défaut
     */
    private void addTerminal()
    {
        this.champ_de_commande = new JTextField(50);
        this.champ_de_commande.setFocusable(true);
        this.champ_de_commande.setFocusTraversalKeysEnabled(false);
        this.champ_de_commande.requestFocusInWindow();
        this.champ_de_commande.setBackground( new Color( bgColor[0], bgColor[1], bgColor[2] ) );
        this.champ_de_commande.setForeground( new Color( fontColor[0], fontColor[1], fontColor[2] ) );
        this.champ_de_commande.setCaretColor( new Color( cursorColor[0], cursorColor[1], cursorColor[2] ) );
        this.champ_de_commande.setSize( this.getWidth(), 20 );

        Terminal.historique.setLineWrap(true);
        Terminal.historique.setWrapStyleWord(true);
        Terminal.historique.setBackground( new Color( bgColor[0], bgColor[1], bgColor[2] ) );
        Terminal.historique.setForeground( new Color( fontColor[0], fontColor[1], fontColor[2] ) );
        Terminal.historique.setEnabled(false);
    }

    /**
     *  Fonction d'auto completion
     *  @param s Debut de commande entré par l'utilisateur
     *  @return ArrayList de tous les choix possibles [ de 0 jusqu'à n choix ]
     */
    ArrayList<String> auto_completion(String s)
    {
        ArrayList<String> proposition = new ArrayList<String>();
        Enumeration<String> commandes = StockageDonnee.getEnumerationListeCommandes();

        while ( commandes.hasMoreElements() )
        {
            String commande_courante = commandes.nextElement();
            if ( (s.length() < commande_courante.length()) 
                    && s.equals(commande_courante.substring(0, s.length())) )
            {
                proposition.add( commande_courante );
            }
        }

        return proposition;
    }

    /**
     *  Modifie le controleur
     *  @param c nouveau controleur
     */
    public void setControleur(Controleur c)
    {
        this.controleur = c;
    }

    /**
     *  Ajoute un message sur l'historique
     *  @param message Message à ajouter.
     */
    public void addMessage(String message)
    {
        Terminal.historique.append("\n" + message);
        this.replaceHistorique();
    }

    /**
     *  Replace l'historique vers le bas
     */
    void replaceHistorique()
    {
        Terminal.historique.setCaretPosition(Terminal.historique.getDocument().getLength());
    }

    /**
     *  Replace le compteur à l'endroit initial pour les touches UP and DOWN
     */
    public void replaceCompteur()
    {
        this.compteur_commandes = StockageDonnee.getSize_LCEG();
    }

    /**
     *  Supprime l'affichage courant de l'historique (mais garde en
     *  mémoire les commandes).
     */
    public void clear()
    {
        Terminal.historique.setText("");
    }

    /**
     *  Retoune la position de la chaîne de caractère donnée
     *  @param s String à rechercher.
     *  @return Index
     */
    public int getLastIndexOf(String s)
    {
        String term_text = historique.getText().toLowerCase();
        return term_text.lastIndexOf(s.toLowerCase());
    }

    /**
     *  Remplace par une chaîne de caractère, à la position donnée, dans l'historique du terminal
     *  @param remplacement Chaîne de caractère qui va remplacer l'ancienne
     *  @param pos Position a partir de laquelle elle va changer
     */
    public void remplace(String remplacement, int pos)
    {
        Terminal.historique.replaceRange(remplacement, pos, Terminal.historique.getText().length());
    }

    /**
     *  Renvoie le champ de commande
     *  @return champ_de_commande
     */
    public JTextField getChampDeCommande()
    {
        return champ_de_commande;
    }

    /**
     *  Change la couleur de fond rouge
     *  @param red Correspond à la couleur rouge
     */
    public static void setBgColorRed(int red)
    {
        bgColor[0] = red;
    }

    /**
     *  Change la couleur de fond verte
     *  @param green Correspond à la couleur verte
     */
    public static void setBgColorGreen(int green)
    {
        bgColor[1] = green;
    }

    /**
     *  Change la couleur de fond bleue
     *  @param blue Correspond à la couleur bleue
     */
    public static void setBgColorBlue(int blue)
    {
        bgColor[2] = blue;
    }

    /**
     *  Change la couleur de l'écriture rouge
     *  @param red Correspond à la couleur rouge
     */
    public static void setFontColorRed(int red)
    {
        fontColor[0] = red;
    }

    /**
     *  Change la couleur de l'écriture verte
     *  @param green Correspond à la couleur verte
     */
    public static void setFontColorGreen(int green)
    {
        fontColor[1] = green;
    }

    /**
     *  Change la couleur de l'écriture bleue
     *  @param blue Correspond à la couleur bleue
     */
    public static void setFontColorBlue(int blue)
    {
        fontColor[2] = blue;
    }

    /**
     *  Change la couleur du curseur rouge
     *  @param red Correspond à la couleur rouge
     */
    public static void setCursorColorRed(int red)
    {
        cursorColor[0] = red;
    }

    /**
     *  Change la couleur du curseur verte
     *  @param green Correspond à la couleur verte
     */
    public static void setCursorColorGreen(int green)
    {
        cursorColor[1] = green;
    }

    /**
     *  Change la couleur du curseur bleue
     *  @param blue Correspond à la couleur bleue
     */
    public static void setCursorColorBlue(int blue)
    {
        cursorColor[2] = blue;
    }

}
