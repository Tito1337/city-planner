package CityPlanner.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import CityPlanner.Model.City;
import CityPlanner.Model.Database;
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
        String[] cityItems = {"Bruxelles", "Paris"};
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


        JLabel TypeLabel = new JLabel("Séjour :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pan.add(TypeLabel, gbc);


        String[] Type = {"Familial", "Couple", "Adulte", "Divertissement", "Sport"};
        JComboBox TypeComboBox = new JComboBox(Type);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        pan.add(TypeComboBox, gbc);

        JButton Search = new JButton("Rechercher");
        Search.addActionListener(new SearchActionListener());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 8;
        pan.add(Search, gbc);

        JTextField Response = new JTextField("Votre programme de city trip ici...");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 8;
        Font police = new Font("Arial", Font.ITALIC, 10);
        Response.setFont(police);
        Response.setPreferredSize(new Dimension(150, 180));
        pan.add(Response, gbc);

        JButton Print = new JButton("Imprimer");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10,1,1,1);
        pan.add(Print, gbc);



        /*
        String[] cityItems = {"Bruxelles", "Paris"};
        JComboBox CityComboBox = new JComboBox(cityItems);
        JDateChooser StartDate=new JDateChooser();
        JDateChooser EndDate=new JDateChooser();

        this.setLayout(new GridLayout(1, 1));
        this.getContentPane().add(new JLabel("City : "));
        this.getContentPane().add(CityComboBox);
        this.getContentPane().add(new JLabel("Du: "));
        this.getContentPane().add(StartDate);
        this.getContentPane().add(new JLabel("Au: "));
        this.getContentPane().add(EndDate);
        */


        //On prévient notre JFrame que notre JPanel sera son content pane
        this.add(pan, BorderLayout.NORTH);

        this.setVisible(true); // Rendre la fenêtre visible
    }

    private class SearchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("TROP COOL MON GARS");
        }
    }


}

