package Giaodien;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CoreGame.Mouse;

public class Over extends JPanel {
    String winner;
    public Over(String winner) {
        setLayout(null);
        ImageIcon background = new ImageIcon(this.getClass().getResource("../Assets/Backgrounds/GameOver.png"));
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        this.winner = winner;
        JLabel win = new JLabel("Win: " + winner );
        win.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        win.setForeground(Color.BLUE);
        JLabel Overp = new JLabel("Game Over ");
        Overp.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        Overp.setForeground(Color.RED);
     
        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("quit");
        JButton menuButton= new JButton("Menu");
        menuButton.setActionCommand("menu");
        
        add(menuButton);
        add(quitButton);
        add(win);
        add(Overp);
        add(backgroundLabel);

        backgroundLabel.setBounds(0, 0, 1200, 675);
        Overp.setBounds(350, 295, 750, 150);
        win .setBounds(500, 395, 750, 150);
        menuButton.setBounds(380, 500, 200, 70);
        quitButton.setBounds(630, 500, 200, 70);
        
        quitButton.addActionListener(new Mouse(this));
        menuButton.addActionListener(new Mouse(this));
    }

}
