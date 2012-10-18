import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color; //TEMPORAIRE, juste pour le positionnement
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class Terminal extends JPanel implements KeyListener{

    private static JTextArea histo = new JTextArea("Bienvenue sur Carapuce ! Le logiciel fait pour les tortues !\n");
    private static JScrollPane pane = new JScrollPane(histo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    private static ArrayList<String> all_cmd = new ArrayList<String>();
    private static String[] commande = generationTableauCommande();
    private int compteur_commandes = -1;
    private JTextField champ_de_commande;
    private Controleur controleur;
    private String message_erreur = "";

    /**
     *  Constructeur du Terminal
     */
	Terminal()
    {

		this.setBackground(Color.BLACK);//TEMPORAIRE, juste pour le positionnement
        addTerminal();
        this.setLayout(new BorderLayout());
        
        this.add(this.champ_de_commande, BorderLayout.SOUTH);
        this.add(this.pane, BorderLayout.CENTER);
        
        this.champ_de_commande.addKeyListener(this);

    }
    

    /**
     *  Evenement lors de la pression des touches
     *  @param keyEvent
     */
    public void keyPressed(KeyEvent keyEvent)
    {
        if ( keyEvent.getKeyCode() == KeyEvent.VK_ENTER
                && !this.champ_de_commande.getText().equals(""))
        {
            controleur.commande(this.champ_de_commande.getText());
            this.histo.append("\n > "+this.champ_de_commande.getText());
            if ( !this.message_erreur.equals("") )
            {
                this.histo.append("\n   --" + this.message_erreur);
                this.message_erreur = "";
            }
            
            this.histo.setCaretPosition(this.histo.getDocument().getLength());
            this.all_cmd.add(this.champ_de_commande.getText());
            
            this.champ_de_commande.setText("");
            this.compteur_commandes = all_cmd.size();
        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_UP)
        {
            
            if ( compteur_commandes - 1 >= 0 )
            {
                compteur_commandes--;
                this.champ_de_commande.setText(all_cmd.get(compteur_commandes));
            }        
            
        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_DOWN )
        {

            if ( compteur_commandes + 1 < all_cmd.size() )
            {
                compteur_commandes++;
                this.champ_de_commande.setText(all_cmd.get(compteur_commandes));
            }

        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_TAB 
                    && !this.champ_de_commande.getText().equals("") )
        {

            String[] proposition_completion = auto_completion(this.champ_de_commande.getText());

            if ( proposition_completion.length == 1 )
                this.champ_de_commande.setText(proposition_completion[0]);
            else if ( proposition_completion.length > 1 )
            {
                String display_proposition = "\n > " + this.champ_de_commande.getText() + "\n";
                for ( int i = 0; i < proposition_completion.length; i++)
                    display_proposition += "  " + proposition_completion[i];
                this.histo.append(display_proposition);
            }
            else;

        }

        else;

    }

    /**
     *  (IGNORE)
     *  @param keyEvent
     */
    public void keyReleased(KeyEvent keyEvent)
    {
        /*  IGNORE  */
    }

    /**
     *  (IGNORE)
     *  @param keyEvent
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
        this.champ_de_commande.setBackground(Color.black);
        this.champ_de_commande.setForeground(Color.white);
        this.champ_de_commande.setSize( this.getWidth(), 20 );
        this.champ_de_commande.setFocusable(true);
        this.champ_de_commande.requestFocus();
        this.champ_de_commande.setFocusTraversalKeysEnabled(false);

        this.histo.setLineWrap(true);
        this.histo.setWrapStyleWord(true);
        this.histo.setBackground(Color.black);
        this.histo.setForeground(Color.white);
        this.histo.setEnabled(false);


    }

    /**
     *  Fonction d'auto completion
     *  @param s Debut de commande entré par l'utilisateur
     *  @return Tableau de tous les choix possibles [ de 0 jusqu'à n choix ]
     */
    public String[] auto_completion(String s)
    {

        String[] proposition;
        int nbProp = 0;

        for (int i = 0; i < commande.length; i++)
        {
            if ( (s.length() < commande[i].length()) && s.equals(commande[i].substring(0, s.length())) )
                nbProp++;
        }

        proposition = new String[nbProp];
        int compteur = 0;
        for (int i = 0; i < commande.length; i++)
        {

            if ( (s.length() < commande[i].length()) && s.equals(commande[i].substring(0, s.length())) )
            {
                proposition[compteur] = commande[i];
                compteur++;
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
     *  Ajoute le message d'erreur
     *  @param message_erreur le message d'erreur
     */
    public void afficheErreur(String message_erreur)
    {
        this.message_erreur = message_erreur;
    }

    /**
     *  Génère le tableau d'autocompletion
     *  @return Tableau de toutes les commandes
     */
    public static String[] generationTableauCommande()
    {

        String[] tableauCommande = new String[7];
        tableauCommande[0] = "pendown";
        tableauCommande[1] = "penup";
        tableauCommande[2] = "eraser";
        tableauCommande[3] = "up";
        tableauCommande[4] = "down";
        tableauCommande[5] = "left";
        tableauCommande[6] = "right";

        return tableauCommande;

    }

}
