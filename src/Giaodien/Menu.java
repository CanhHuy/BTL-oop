package Giaodien;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CoreGame.Mouse;


public class Menu extends JPanel {

    static JTextField nameone, nametwo;

    public Menu() {
        setLayout(null);
        ImageIcon background = new ImageIcon(this.getClass().getResource("../Assets/Backgrounds/Login.jpg"));
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);

        JLabel firstLoginLabel = new JLabel("Nguoi choi 1: ");
        JLabel secondLoginLabel = new JLabel("Nguoi choi 2: ");
        firstLoginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        secondLoginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        firstLoginLabel.setForeground(Color.WHITE);
        secondLoginLabel.setForeground(Color.WHITE);
        nameone = new JTextField(50);
        nametwo = new JTextField(50);
 
        JButton playButton = new JButton("Start Game");
        playButton.setActionCommand("start");
        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("quit");
        
        add(playButton);
        add(quitButton);
        add(firstLoginLabel);
        add(secondLoginLabel);
        add(nameone);
        add(nametwo);
        add(backgroundLabel);
        
        backgroundLabel.setBounds(0, 0, 1200, 675);
        firstLoginLabel.setBounds(350, 295, 350, 50);
        nameone.setBounds(670, 303, 250, 40);

        secondLoginLabel.setBounds(350, 365, 350, 50);
        nametwo.setBounds(670, 373, 250, 40);
        playButton.setBounds(650, 550, 270, 60);
        playButton.addActionListener(new Mouse(this));
        quitButton.setBounds(350, 550, 270, 60);
        quitButton.addActionListener(new Mouse(this));
    }
}
