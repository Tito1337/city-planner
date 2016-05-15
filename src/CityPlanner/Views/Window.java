package CityPlanner.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import CityPlanner.Model.City;
import CityPlanner.Model.Database;
import CityPlanner.Model.Tag;
import CityPlanner.Model.Trip;
import com.sun.org.apache.regexp.internal.RE;
import com.toedter.calendar.*;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.TableColumn;

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

        JPanel ResponsePanel = new JPanel();

        JButton Search = new JButton("Rechercher");
        Trip trip = new Trip();
        Search.addActionListener(new SearchActionListener(CityComboBox, PersonComboBox, TagComboBox, ResponsePanel, trip));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 8;
        pan.add(Search, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 8;
        /*Font police = new Font("Arial", Font.ITALIC, 10);
        Response.setFont(police);*/
        ResponsePanel.setPreferredSize(new Dimension(150, 180));
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

    private class SearchActionListener implements ActionListener {
        JComboBox cityComboBox;
        JComboBox personComboBox;
        JComboBox tagComboBox;
        JPanel responsePanel;
        Trip trip;

        public SearchActionListener(JComboBox cityComboBox, JComboBox personComboBox, JComboBox tagComboBox, JPanel responsePanel, Trip trip) {
            this.cityComboBox = cityComboBox;
            this.personComboBox = personComboBox;
            this.tagComboBox = tagComboBox;
            this.responsePanel = responsePanel;
            this.trip = trip;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                responsePanel.removeAll();
                
                trip.setCity((City) cityComboBox.getSelectedItem());
                trip.setPersonNumber(Integer.parseInt((String)personComboBox.getSelectedItem()));
                trip.setTag((Tag)tagComboBox.getSelectedItem());

                JTable table = new JTable(new ResultTableModel(trip.getActivities()));
                TableColumn column = null;
                for (int i = 0; i < 5; i++) {
                    column = table.getColumnModel().getColumn(i);
                    if (i == 2) {
                        column.setPreferredWidth(100); //third column is bigger
                    } else {
                        column.setPreferredWidth(50);
                    }
                }

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

    private class PrintActionListener implements ActionListener {
        JPanel responsePanel;

        public PrintActionListener(JPanel responsePanel) {
            this.responsePanel = responsePanel;
        }

        public void actionPerformed(ActionEvent e) {

        }
    }
}

