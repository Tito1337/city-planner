package CityPlanner.Views;

import javax.swing.*;
import java.awt.*;

import java.awt.Color;
import javax.swing.*;

/**
 * Created by Benjamin on 11/03/16.
 */

public class Window extends JFrame {

    public Window() {

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
        JPanel pan = new JPanel();
        Color blueTurquin = new Color(66,91,136); //Rouge,vert,bleu
        pan.setBackground(blueTurquin);
        //On prévient notre JFrame que notre JPanel sera son content pane
        this.setContentPane(pan);
        JButton bouton = new JButton("Rechercher");
        JLabel label = new JLabel("City : ");
        String[] items = {"Bruxelles", "Paris"};
        JComboBox CityComboBox = new JComboBox(items);
        pan.add(label);
        pan.add(bouton);
        pan.add(CityComboBox);





        this.setVisible(true); // Rendre la fenêtre visible



    }


}

