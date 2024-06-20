package CoreGame;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Giaodien.Play;

public class Mouse implements ActionListener {
    Game startgame;
    JPanel Dk;
    JPanel cardsPanel;
    String source;
    CardLayout layout;
    public Mouse(JPanel target) {
        this.Dk = target;
    }
    public void actionPerformed(ActionEvent event) {
        this.source = ((JButton) event.getSource()).getActionCommand();
        startgame = (Game) SwingUtilities.getRoot(this.Dk);
        cardsPanel = startgame.getCardsPanel();
        layout = (CardLayout) cardsPanel.getLayout();
        if (this.source.equals("start")) {
            Play play = new Play();
            cardsPanel.add(play, "play");
            layout.show(cardsPanel, "play");
            play.requestFocusInWindow();
            play.runGame(play);
        } else if (this.source.equals("quit")) {
            startgame.dispose();
        } else if (this.source.equals("menu")) {
        	layout.show(cardsPanel, "menu");
        }
    }
}