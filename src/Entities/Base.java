package Entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Base extends Unit {
   
    private BufferedImage hinhanhBase;
    private BufferedImage hinhanhnow;
    private int health;
    private int width, height;
    public Base(int team, int type, int evo, BufferedImage hinhanhbase) {
        super(team, type, evo);
        this.hinhanhBase = hinhanhbase;     
        this.hinhanhnow = this.hinhanhBase; 
    }
    public void draw(Graphics g) {
        int healthlegth;
        int percent;
        int healthlegthX;
        double fullheath = (double) 100;
        if (this.getTeam() == 1) {
            percent = (int) ((this.getHealth() * 100.0f) / 500);
            healthlegth = (int) (fullheath * percent / 100.0);
            g.setColor(Color.RED);
            g.fillRect(this.getVitri().x, this.getVitri().y - 30, healthlegth, 25);
        } else {
            percent = (int) ((this.getHealth() * 100.0f) / 500);
            healthlegth = (int) (fullheath * percent / 100.0);
            healthlegthX = this.getVitri().x + 105 - healthlegth - 2;
            g.setColor(Color.RED);
            g.fillRect(healthlegthX, this.getVitri().y - 30, healthlegth - 2, 25);
        }
        g.drawImage(this.hinhanhnow, this.getVitri().x, this.getVitri().y, null);    
    } 
    public BufferedImage getHinhanhnow() {
        return this.hinhanhnow;
    }
    public void setHinhanhnow(BufferedImage hinhanhnow) {
        this.hinhanhnow = hinhanhnow;
    }
  
}
