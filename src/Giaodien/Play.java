package Giaodien;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.CardLayout;
import java.awt.Font;
import javax.sound.sampled.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import CoreGame.Keyboard;
import Entities.Nguoichoi;
import CoreGame.Game;

public class Play extends JPanel {
    private Nguoichoi playerOne, playerTwo;
    private BufferedImage background;
    private boolean playingMusic = true;
    private boolean gameOverone = false;
    private boolean gameOvertwo = false;
    private Game game;
    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private String nameone; 
    private String nametwo;
    private boolean gamePaused = false;
    private BufferedImage[] iconbackground = new BufferedImage[4];
    private BufferedImage[] iconSolider = new BufferedImage[4];
    private int stopmusic = 0;
    static AudioInputStream audio;
    static Clip music;
    
    private BufferedImage[][] oneMove = new BufferedImage[3][8];
    private BufferedImage[][] oneAttack = new BufferedImage[3][3];
    private BufferedImage oneBullet;
    private BufferedImage oneBase ;
    
    private BufferedImage[][] twoMve = new BufferedImage[3][8];
    private BufferedImage[][] twoAttack = new BufferedImage[3][3];
    private BufferedImage twoBullet;
    private BufferedImage twoBase;
    
    public Play() {
        setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();
        importImages();
        try {
            background = ImageIO.read(this.getClass().getResource("../Assets/Backgrounds/Game.jpg"));
        } catch (Exception e) {
        }
        try {
            audio = AudioSystem.getAudioInputStream(this.getClass().getResource("../Assets/backgroundMusic.wav"));
            music = AudioSystem.getClip();
            music.open(audio);
        } catch (Exception e) {
        }
        nameone = Menu.nameone.getText();
        nametwo = Menu.nametwo.getText();
        this.playerOne = new Nguoichoi(1, nameone, oneMove, oneAttack, oneBullet, oneBase);
        this.playerTwo = new Nguoichoi(-1, nametwo, twoMve, twoAttack, twoBullet, twoBase);
        Keyboard Key = new Keyboard(playerOne, playerTwo, this);
        addKeyListener(Key);
    }
    public void runGame(Play pp) {
        playMusic();
        game = (Game) SwingUtilities.getWindowAncestor(this);
        cardsPanel = game.getCardsPanel();
        cardLayout = (CardLayout) cardsPanel.getLayout();
        final Timer thoigianve = new Timer(45, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                repaint();
            }
        });
        thoigianve.start();
        final Timer thoigiangoiquai = new Timer(1300, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!playerOne.getQueueSoldier().isEmpty() && !pp.getGamePaused()) {
                    playerOne.Summon();
                }
                if (!playerTwo.getQueueSoldier().isEmpty() && !pp.gamePaused) {
                    playerTwo.Summon();
                }
            }
        });
        thoigiangoiquai.start();
        final Timer vangspoint = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!pp.gamePaused) {
                    playerOne.gainGold();
                    playerTwo.gainGold();
                }
            }
        });
        vangspoint.start();
    }
    public void importImages() {
        try {
            for (int type = 0; type < 3; type++) {
            this.oneBase = ImageIO.read(this.getClass().getResource("../Assets/Entities/Left/Tower/1.png"));
            this.twoBase = ImageIO.read(this.getClass().getResource("../Assets/Entities/Right/Tower/1.png"));
                    this.iconSolider[type] = ImageIO.read(this.getClass().getResource("../Assets/Menu/iconlinh/" + type +".png"));
                    for (int hoatanh = 0; hoatanh < 8; hoatanh++) {
                        this.oneMove[type][hoatanh] = ImageIO.read(this.getClass().getResource("../Assets/Entities/Left/Move/" + type + "/" + hoatanh + ".png"));
                        this.twoMve[type][hoatanh] = ImageIO.read(this.getClass() .getResource("../Assets/Entities/Right/Move/" + type + "/"  + hoatanh + ".png"));
                    }
                    for (int hoatanh = 0; hoatanh < 3; hoatanh++) {
                        this.oneAttack[type][hoatanh] = ImageIO.read(this.getClass().getResource("../Assets/Entities/Left/Attack/" + type + "/" + hoatanh + ".png"));
                        this.twoAttack[type][hoatanh] = ImageIO.read(this.getClass().getResource("../Assets/Entities/Right/Attack/" + type + "/" + hoatanh + ".png"));
                    }
            } 
        
            this.oneBullet = ImageIO.read(this.getClass().getResource("../Assets/Entities/Left/Bullet/0.png"));
            this.twoBullet = ImageIO.read(this.getClass().getResource("../Assets/Entities/Right/Bullet/0.png"));
            for (int i = 0; i < 4; i++) {
                this.iconbackground[i] = ImageIO.read(this.getClass().getResource("../Assets/Menu/iconmenu/" + i + ".png"));
            }
        } catch (Exception e) {
            System.out.println("loi");
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!this.gamePaused) {
            g.drawImage(background, 0, 0, null);
            for (int j = 0; j < 4; j++) {
                g.drawImage(this.iconSolider[j],65 + (j * 2 * 25), 50, null);
                g.drawImage(this.iconSolider[j],945 + (j * 2 * 25), 50, null);
            }
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawImage(this.iconbackground[0], 700, 60, null);
            g.drawImage(this.iconbackground[0], 400, 60, null);
            g.drawString("DOI", 400, 100);
            g.drawString("DOI", 700, 100);
            g.drawString("Space", 540, 50);
            g.drawImage(this.iconbackground[1], 550, 60, null);
            g.drawString("B", 607, 50);
            if (this.playingMusic) {
                g.drawImage(this.iconbackground[2], 602, 60, null);
            } else {
                g.drawImage(this.iconbackground[3], 602, 60, null);
            }
            playerOne.auto(playerTwo);
            gameOvertwo = playerTwo.checkGameOver();
            if (gameOvertwo) {
                JPanel overPanel = new Over(nameone);
                this.cardsPanel.add(overPanel, "over");
                this.cardLayout.show(cardsPanel, "over");
            }
            playerTwo.auto(playerOne);
            gameOverone = playerOne.checkGameOver();
            if (gameOverone) {
                JPanel overPanel = new Over(nametwo);
                cardsPanel.add(overPanel, "over");
                cardLayout.show(cardsPanel, "over");
            }
            int playerTwoGained = playerOne.soldierdie();
            int playerOneGained = playerTwo.soldierdie();
            playerOne.gainGold(playerOneGained);
            playerTwo.gainGold(playerTwoGained);
            playerOne.draw(g);
            playerTwo.draw(g);
        } else {
            g.setFont(new Font("Tahoma", Font.BOLD, 100));
            g.drawString("Tam Dung", 400, 360);
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.drawString("nhan Space de tiep tuc", 400 , 460);
        }
    }
    public void playMusic() {
        music.setFramePosition(0);
        music.start();
        music.setFramePosition(stopmusic);
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopPlayingMusic() {
    	stopmusic = music.getFramePosition();
        music.stop();
    }
    public boolean getPlayingMusic() {
        return this.playingMusic;
    }
    public boolean getGamePaused() {
        return this.gamePaused;
    }
    public void setGamePaused(boolean set) {
        this.gamePaused = set;
    }
    public void setPlayingMusic(boolean set) {
        this.playingMusic = set;
    }
}