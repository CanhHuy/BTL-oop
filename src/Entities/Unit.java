package Entities;
import java.awt.Point;

public abstract class Unit extends Entity {
    private int health;
    private Hitbox hitbox;
    private int width;
    private int height;
    public Unit(int team, int type, int evo) {
        super(team, type, evo);
        switch (type) {    
                case 0:
                    this.health = 200 + (evo * 50);
                    this.width = 19;
                    this.height = 45;
                    break;
                case 1:
                    this.health = 150 + (evo * 25);
                    this.width = 19;
                    this.height = 45;
                    break;
                case 2:
                    this.health = 300 + (evo * 70);
                    this.width = 19;
                    this.height = 45;
                    break;
                case 3:
                    this.health = 500 + (evo * 100);
                    this.width = 100;
                    this.height = 190; 
                break;
            }
        this.hitbox = new Hitbox(new Point(this.getVitri()), this.width, this.height);
    }
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public int getHealth() {
        return this.health;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}