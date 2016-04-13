package CityPlanner.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by Benjamin on 19/03/16.
 */

public class Panel extends JPanel {
    public void paintComponent(Graphics g){

        //Définition de sa couleur de fond
        //Color blueTurquin = new Color(66,91,136); //Rouge,vert,bleu
        //this.setBackground(blueTurquin);



        try {
            // Image de fond
            Image img = ImageIO.read(new File("background.png"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

            //Bandeau en-tête
            //Image header = ImageIO.read(new File("header.jpg"));
            //g.drawImage(header, 0, 0, 500, 60, this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g); // Réexécute le code par défaut de Jpanel


    }
}


