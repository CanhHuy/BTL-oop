package Entities;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.*;

public class Bullet {

    private Hitbox hitbox;
    private Point vitri;
    private Point bandau;
    private int damage;
    private int speed;
    private BufferedImage hinhanh;
    
    public Bullet(int team, int damage, Point vitri, BufferedImage hinhanh) {
        this.vitri = vitri;
        this.bandau = new Point(this.vitri);
        this.hinhanh = hinhanh;
        this.damage = damage;
        this.speed = 5 * team;
        this.hitbox = new Hitbox(this.vitri, 15, 10);
    }
    public void move() {
        this.vitri.x += this.speed;
        this.hitbox.update(this.vitri);
    }
    public boolean CheckVC(Unit enemy) {
        return this.hitbox.checkvacham(enemy.getHitbox());
    }
    public void draw(Graphics g) {
        g.drawImage(this.hinhanh, this.vitri.x, this.vitri.y, null);
    }
    public Hitbox getHitbox() {
        return hitbox;
    }
    public Point getVitri() {
        return this.vitri;
    }
    public Point getVitriban() {
        return this.bandau;
    }
    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
    public BufferedImage getHinhanh() {
        return this.hinhanh;
    }
}
