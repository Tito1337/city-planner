package CityPlanner.Views;

import CityPlanner.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.toedter.calendar.*;

/**
 * Created by Benjamin on 11/03/16.
 */
public class Window extends JFrame {
    Trip trip;

    public Window(Trip trip) throws SQLException {
        this.trip = trip;

        //Titre de la fenêtre
        this.setTitle("Plan your city trip");
        //Définit sa taille : 550 pixels de large et 420 pixels de haut
        this.setSize(1024, 420);
        //Positionnement de la fenêtre au centre de l'écran
        this.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on ferme la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Permet de redimensionner la fenêtre
        this.setResizable(true);



        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel(new GridBagLayout());       // Le layout manager sera une grille
        GridBagConstraints gbc = new GridBagConstraints();  // Objet pour le positionnement des composants
        gbc.insets= new Insets(5,2,5,2); // Espaces autour des composants
       // pan.setBackground(new Color(66,91,136));            // Couleur de fond



        // Positionnement du label "City"
        JLabel CityLabel = new JLabel("Ville : ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.weightx = 1;
        pan.add(CityLabel, gbc);

        //Positionnement du sélecteur de choix de la ville
        ArrayList<City> cities = Database.getAllCities();
        JComboBox CityComboBox = new JComboBox(cities.toArray());
        gbc.gridx = 1;
        gbc.gridy = 0 ;
        gbc.gridwidth = 1 ;
        pan.add(CityComboBox, gbc);

        // Choix des dates de séjour
        JPanel DuPanel = new JPanel();
        DuPanel.setLayout(new BoxLayout(DuPanel, BoxLayout.LINE_AXIS));

        JLabel DuLabel = new JLabel("Du");
        DuPanel.add(DuLabel);

        // Sélection de la date de début
        JDateChooser StartDate=new JDateChooser(new Date());
        DuPanel.add(StartDate);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1 ;
        pan.add(DuPanel, gbc);

        JLabel AuLabel = new JLabel("Au");
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1 ;
        pan.add(AuLabel, gbc);

        // Sélection de la date de fin
        JDateChooser EndDate=new JDateChooser(new Date());
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        pan.add(EndDate, gbc);


        JLabel NbLabel = new JLabel("Voyageurs :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        pan.add(NbLabel, gbc);

        String[] PersonNumber = {"1", "2", "3", "4", "5", "6"};
        JComboBox PersonComboBox = new JComboBox(PersonNumber);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        pan.add(PersonComboBox, gbc);


        JLabel TagLabel = new JLabel("Séjour :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pan.add(TagLabel, gbc);


        ArrayList<Tag> tags = Database.getAllTags();
        JComboBox TagComboBox = new JComboBox(tags.toArray());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pan.add(TagComboBox, gbc);

        JPanel ResponsePanel = new JPanel();

        JButton Search = new JButton("Rechercher");
        Search.addActionListener(new SearchActionListener(CityComboBox, PersonComboBox, TagComboBox, ResponsePanel, trip, StartDate, EndDate));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 8;
        pan.add(Search, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 8;
        /*Font police = new Font("Arial", Font.ITALIC, 10);
        Response.setFont(police);*/
        ResponsePanel.setPreferredSize(new Dimension(1000, 180));
        pan.add(ResponsePanel, gbc);

        JButton Print = new JButton("Imprimer");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10,1,1,1);
        pan.add(Print, gbc);

        //On prévient notre JFrame que notre JPanel sera son content pane
        this.add(pan, BorderLayout.NORTH);

        this.setVisible(true); // Rendre la fenêtre visible
    }

    /**
     * Listener pour réagir au clic du bouton "Rechercher"
     */
    private class SearchActionListener implements ActionListener {
        JComboBox cityComboBox;
        JComboBox personComboBox;
        JComboBox tagComboBox;
        JPanel responsePanel;
        JDateChooser startDateChooser;
        JDateChooser endDateChooser;
        Trip trip;

        /**
         * Constructeur
         * @param cityComboBox sélecteur de ville
         * @param personComboBox sélecteur de nombre de personnes
         * @param tagComboBox sélecteur de tags
         * @param responsePanel zone où afficher le résultat de la recherche
         * @param trip instance de trip qui sera manipulée pour calculer le résultat
         * @param startDateChooser sélecteur de date de début de séjour
         * @param endDateChooser sélecteur de date de fin de séjour
         */
        public SearchActionListener(JComboBox cityComboBox, JComboBox personComboBox, JComboBox tagComboBox, JPanel responsePanel, Trip trip, JDateChooser startDateChooser, JDateChooser endDateChooser) {
            this.cityComboBox = cityComboBox;
            this.personComboBox = personComboBox;
            this.tagComboBox = tagComboBox;
            this.responsePanel = responsePanel;
            this.startDateChooser = startDateChooser;
            this.endDateChooser = endDateChooser;
            this.trip = trip;
        }

        /**
         * Méthode appelée au clic du bouton "Rechercher"
         */
        public void actionPerformed(ActionEvent e) {
            try {
                // Nettoyage des vieilles recherches
                responsePanel.removeAll();

                // Mettre à jour l'instance de Trip avec les informations du formulaire
                // Trip recalcule automatiquement toutes les activitiés disponibles.
                trip.setCity((City) cityComboBox.getSelectedItem());
                trip.setPersonNumber(Integer.parseInt((String)personComboBox.getSelectedItem()));
                trip.setTag((Tag)tagComboBox.getSelectedItem());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                trip.setStartDate(df.format(startDateChooser.getDate()));
                trip.setEndDate(df.format(startDateChooser.getDate()));

                // Largeur par défaut des colonnes
                JTable table = new JTable(new ResultTableModel(trip.getActivities()));
                table.getColumnModel().getColumn(0).setPreferredWidth(150);
                table.getColumnModel().getColumn(1).setPreferredWidth(350);
                table.getColumnModel().getColumn(2).setPreferredWidth(200);
                table.getColumnModel().getColumn(3).setPreferredWidth(50);
                table.getColumnModel().getColumn(4).setPreferredWidth(50);
                table.getColumnModel().getColumn(5).setPreferredWidth(100);
                table.getColumnModel().getColumn(6).setPreferredWidth(100);

                // Afficher le résultat
                responsePanel.setLayout(new BorderLayout());
                responsePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
                responsePanel.add(table, BorderLayout.CENTER);
                responsePanel.revalidate();
                responsePanel.repaint();
            } catch(SQLException exception) {
                System.err.printf("ERREUR SQL : %s\n", exception);
                exception.printStackTrace();
            }
        }
    }
}

