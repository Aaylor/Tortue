import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color; //TEMPORAIRE, juste pour le positionnement
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Terminal extends JPanel implements KeyListener{

    private static JTextArea historique = new JTextArea("Bienvenue sur Carapuce ! Le logiciel fait pour les tortues !\n");
    
    private static JScrollPane pane = new JScrollPane(historique, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    private Controleur controleur;
    private int compteur_commandes = -1;
    private String message_erreur = "";
    private JTextField champ_de_commande;

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
            this.historique.append("\n > "+this.champ_de_commande.getText());
            if ( !this.message_erreur.equals("") )
            {
                this.historique.append("\n   --" + this.message_erreur);
                this.message_erreur = "";
            }
            
            this.historique.setCaretPosition(this.historique.getDocument().getLength());
            StockageDonnee.liste_commande_entree_generale.add(this.champ_de_commande.getText());
            
            this.champ_de_commande.setText("");
            this.compteur_commandes = StockageDonnee.liste_commande_entree_generale.size();
        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_UP)
        {
            
            if ( compteur_commandes - 1 >= 0 )
            {
                compteur_commandes--;
                this.champ_de_commande.setText(StockageDonnee.liste_commande_entree_generale.get(compteur_commandes));
            }        
            
        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_DOWN )
        {

            if ( compteur_commandes + 1 < StockageDonnee.liste_commande_entree_generale.size() )
            {
                compteur_commandes++;
                this.champ_de_commande.setText(StockageDonnee.liste_commande_entree_generale.get(compteur_commandes));
            }
            else
                this.champ_de_commande.setText("");

        }

        else if ( keyEvent.getKeyCode() == KeyEvent.VK_TAB 
                    && !this.champ_de_commande.getText().equals("") )
        {

            ArrayList<String> proposition_completion = auto_completion( this.champ_de_commande.getText() );

            if ( proposition_completion.size() == 1 )
                this.champ_de_commande.setText(proposition_completion.get(0));
            else if ( proposition_completion.size() > 1 )
            {
                String display_proposition = "\n > " + this.champ_de_commande.getText() + "\n";
                for ( int i = 0; i < proposition_completion.size(); i++)
                    display_proposition += "  " + proposition_completion.get(i);
                this.historique.append(display_proposition);
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

        this.historique.setLineWrap(true);
        this.historique.setWrapStyleWord(true);
        this.historique.setBackground(Color.black);
        this.historique.setForeground(Color.white);
        this.historique.setEnabled(false);


    }

    /**
     *  Fonction d'auto completion
     *  @param s Debut de commande entré par l'utilisateur
     *  @return Tableau de tous les choix possibles [ de 0 jusqu'à n choix ]
     */
    public ArrayList<String> auto_completion(String s)
    {

        ArrayList<String> proposition = new ArrayList<String>();
        Enumeration<String> commandes = StockageDonnee.liste_des_commandes.keys();

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
     *  Ajoute le message d'erreur
     *  @param message_erreur le message d'erreur
     */
    public void afficheErreur(String message_erreur)
    {
        this.message_erreur = message_erreur;
    }

}
