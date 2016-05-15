package CityPlanner.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import CityPlanner.Model.*;
import com.sun.org.apache.regexp.internal.RE;
import com.toedter.calendar.*;

import java.awt.Color;
import javax.swing.*;

/**
 * Created by Benjamin on 11/03/16.
 */

public class Window extends JFrame {

    public Window() throws SQLException {
        //Titre de la fenêtre
        this.setTitle("Plan your city trip");
        //Définit sa taille : 550 pixels de large et 420 pixels de haut
        this.setSize(550, 420);
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
        JDateChooser StartDate=new JDateChooser();
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
        JDateChooser EndDate=new JDateChooser();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
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

        JPanel Response = new JPanel();

        JButton Search = new JButton("Rechercher");
        Search.addActionListener(new SearchActionListener(CityComboBox, PersonComboBox, TagComboBox, Response));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 8;
        pan.add(Search, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 8;
        /*Font police = new Font("Arial", Font.ITALIC, 10);
        Response.setFont(police);*/
        Response.setPreferredSize(new Dimension(150, 180));
        pan.add(Response, gbc);

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

    private class SearchActionListener implements ActionListener {
        JComboBox cityComboBox;
        JComboBox personComboBox;
        JComboBox tagComboBox;
        JPanel responsePanel;

        Trip trip;

        public SearchActionListener(JComboBox cityComboBox, JComboBox personComboBox, JComboBox tagComboBox, JPanel responsePanel) {
            this.cityComboBox = cityComboBox;
            this.personComboBox = personComboBox;
            this.tagComboBox = tagComboBox;
            this.responsePanel = responsePanel;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                trip = new Trip((City)cityComboBox.getSelectedItem(), Integer.parseInt((String)personComboBox.getSelectedItem()), (Tag)tagComboBox.getSelectedItem());
                String[] columnNames = {"Activité",
                        "Description",
                        "Adresse",
                        "Durée",
                        "Prix",
                        "Ouverture"};

                ArrayList<Object> data = new ArrayList<Object>();
                for(Activity a: trip.getActivities()) {
                    data.add({
                            a.getName(), a.getDescription()
                    })
                }
                Object[][] data = {
                        {"Mary", "Campione",
                                "Snowboarding", "a", "b", "e", "e"}
                };

                JTable table = new JTable(data, columnNames);

                System.out.println("Try...");

                responsePanel.setLayout(new BorderLayout());
                responsePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
                responsePanel.add(table, BorderLayout.CENTER);
                responsePanel.revalidate();
                responsePanel.repaint();
            } catch(SQLException exception) {
                //responseTextField.setText("ERREUR SQL");
            }

        }
    }
}

