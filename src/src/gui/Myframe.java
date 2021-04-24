package gui;

import javax.swing.*;
import java.awt.*;

// contributed by Yixiang Yin
public class Myframe extends JFrame {
    Myframe(){
        this.setTitle("Text Game Name");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("logo.png");   // logo goes here
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.BLACK);

    }
}
