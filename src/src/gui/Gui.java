package gui;

import javax.swing.*;
import java.awt.*;
// contributed by Yixiang Yin
public class Gui {
    public static void main(String[] args) {

        // the start frame
        ImageIcon image = new ImageIcon("logo.png");
        JLabel label = new JLabel();
        label.setText("Welcome to the world ...");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x00FF00));
        label.setFont(new Font("MV Boli",Font.PLAIN,20));
        label.setBackground(Color.black);
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0,0,250,250);



        Myframe startFrame = new Myframe();
        startFrame.setLayout(null);
        startFrame.add(label);

    }
}
