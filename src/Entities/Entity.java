package Entities;
import java.awt.Point;
import java.util.LinkedList;

public abstract class Entity {
    private Point vitri;
    private Hitbox hitbox;
    private int team;
    private int type;
    private int evo;
    public Entity(int team, int type, int evo) {
        this.type = type;
        this.team = team;
        this.evo = evo;
        if (type == 3) {
            if (team == 1) {
                this.vitri = new Point(40, 442);
            } else {
                this.vitri = new Point(1040, 442);
            }
        } else {
            if (team == 1) {
                this.vitri = new Point(0, 582);
            } else {
                this.vitri = new Point(1150, 582);
            }
        }
    }
    public Unit getClosestenemy(Nguoichoi other) {
        LinkedList<Soldier> soldiers = other.getSoldiers();
        if (soldiers.isEmpty()) {
            return other.getBase();
        } else {
            if (this.team == 1) {
                if (soldiers.getFirst().getVitri().x < other.getBase().getVitri().x) {
                    return soldiers.getFirst();
                } else {
                    return other.getBase();
                }
            } else {
                if (soldiers.getFirst().getVitri().x > other.getBase().getVitri().x) {
                    return soldiers.getFirst();
                } else {
                    return other.getBase();
                }
            }
        }
    }
    public Point getVitri() {
        return this.vitri;
    }
    public void setVitri(Point Vitri) {
        this.vitri = Vitri;
    }
    public Hitbox getHitbox() {
        return this.hitbox;
    }
    public int getTeam() {
        return team;
    }
    public int getType() {
        return type;
    }
    public int getEvo() {
        return evo;
    }
}