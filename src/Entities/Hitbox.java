package Entities;
import java.awt.Point;
import java.awt.Rectangle;

public class Hitbox {

    private Point vitri;
    private int height, width;
    private Rectangle hophitbox; 

    public Hitbox(Point vitri, int w, int h) {
        this.vitri = vitri;
        this.width = w;
        this.height = h;
        this.hophitbox = new Rectangle(vitri.x, vitri.y, width, height);
    }
    public boolean checkvacham(Hitbox other) {
        if (this.hophitbox.intersects(other.getHophitbox())) {
            return true;
        } else {
            return false;
        }
    }
    public void update(Point point) {
        this.vitri = point;
        this.hophitbox.setLocation(this.vitri);
    }
    public Point getVitri() {
        return vitri;
    }

    public void setVitri(Point vitri) {
        this.vitri = vitri;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public Rectangle getHophitbox() {
        return hophitbox;
    }

}
