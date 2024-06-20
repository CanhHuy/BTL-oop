package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Sniper extends Soldier {

    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private BufferedImage hinhanhBullet;
    private boolean attacked = false;
    public Sniper(int team, int type, int evo, BufferedImage[] move, BufferedImage[] attack, BufferedImage hinhanhDBullet) {
        super(team, type, evo, move, attack);
        this.hinhanhBullet = hinhanhDBullet;   
    }
    public void attack(Unit target) {
        this.setTargetnow(target);
        Point vtshoot;
        if (this.getHoatanhAttack() == 2) {
            this.setHoatanhAttack(0);
        } else {
            this.setHoatanhAttack(this.getHoatanhAttack() + 1);
        }
        this.setHinhanhnow(this.getHinhanhattack()[this.getHoatanhAttack()]);
        if (this.getTeam() == 1) {
            vtshoot = new Point(this.getVitri().x + 19, this.getVitri().y + 13);
            Bullet bullet = new Bullet(this.getTeam(), this.getDamage(), vtshoot,
                    hinhanhBullet);
            this.bullets.add(bullet);
            this.setTimeattack(System.currentTimeMillis());
        } else {
            vtshoot = new Point(this.getVitri().x, this.getVitri().y + 13);
            Bullet bullet = new Bullet(this.getTeam(), this.getDamage(), vtshoot,
                    hinhanhBullet);
            this.bullets.add(bullet);
            this.setTimeattack(System.currentTimeMillis());
        }
    }
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        this.attacked=true;
    }
    public void draw(Graphics g) {
    	if(this.attacked) {
    	int healthlegth;
        int percent;
        double fullheath = (double) 25;
        if (this.getTeam() == 1) {
            percent = (int) ((this.getHealth() * 100.0f) / 150);
            healthlegth = (int) (fullheath * percent / 100.0);
            g.setColor(Color.RED);
            g.fillRect(this.getVitri().x, this.getVitri().y - 15, healthlegth, 5);
        } else {
        	percent = (int) ((this.getHealth() * 100.0f) / 150);
            healthlegth = (int) (fullheath * percent / 100.0);
            g.setColor(Color.RED);
            g.fillRect(this.getVitri().x+20, this.getVitri().y - 15, healthlegth, 5);
        }
    	}
        g.drawImage(this.getHinhanhnow(), this.getVitri().x, this.getVitri().y, null);
        for (int i = 0; i < this.bullets.size(); i++) {
            this.bullets.get(i).draw(g);
        }
    }
    public LinkedList<Bullet> getBullets() {
        return this.bullets;
    }

}
