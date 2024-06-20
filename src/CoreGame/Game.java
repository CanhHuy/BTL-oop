package CoreGame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import Giaodien.Menu;

public class Game extends JFrame {

    JPanel cardsPanel, menu, play, over;
    public Game() {
        setTitle("Age of War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 715);
        setResizable(false);
        setFocusable(false);
        cardsPanel = new JPanel(new CardLayout());
        menu = new Menu(); 
        cardsPanel.add(menu, "menu"); 
        add(cardsPanel);
        setVisible(true);
    }
    public JPanel getCardsPanel() {
        return this.cardsPanel;
    }  
}
