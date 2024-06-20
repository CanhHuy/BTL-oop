package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Soldier extends Unit {
	private int speed;
	private Hitbox tatt;
	private int damage;
	private double costkill;
	private long attackSpeed;
	private int activitynow;
	private long timeatt;
	private Unit targetnow;
	private BufferedImage[] hinhanhMove;
	private BufferedImage[] hinhanhAttack;
	private BufferedImage hinhanhNow;
	private int hoatanhmove;
	private int hoatanhattack;
	private boolean trangthai = false;
	private boolean attacked = false;
	public Soldier(int team, int type, int evo, BufferedImage[] move, BufferedImage[] attack) {
		super(team, type, evo);
		this.hinhanhMove = move;
		this.hinhanhAttack = attack;
		this.hinhanhNow = this.hinhanhMove[0];
		this.hoatanhmove =0;
		int range = 0;
		switch (type) {
			case 0:
				this.damage = 20 + (evo * 10);
				this.speed = 4 * this.getTeam();
				this.attackSpeed = 600;
				this.costkill = 100;
				range = 7;
				break;
			case 1:
				this.damage = 30 + (evo * 8);
				this.speed = 4 * this.getTeam();
				this.attackSpeed = 600;
				this.costkill = 125;
				range = 120 + (evo * 10);
				break;
			case 2:
				this.damage = 50 + (evo * 10);
				this.speed = 4 * this.getTeam();
				this.attackSpeed = 700;
				this.costkill = 250;
				range = 5;
				break;		
		}
		if (team == 1) {
			Point toado = new Point(this.getVitri().x + this.getWidth(), this.getVitri().y);
			this.tatt = new Hitbox(toado, range, range);
		} else {
			Point toado = new Point(this.getVitri().x - range, this.getVitri().y);
			this.tatt = new Hitbox(toado, range, range);
		}
	}
	public void move() {
		if (this.hoatanhmove == 7) {
			this.hoatanhmove = 0;
		} else {
			this.hoatanhmove++;
		}
		this.hinhanhNow = hinhanhMove[hoatanhmove];
		this.updateVT();
	}
    public void attack(Unit target) {
		this.targetnow = target;
		if (this.hoatanhattack == 2) {
			this.hoatanhattack = 0;
		} else {
			this.hoatanhattack++;
		}
		this.hinhanhNow = hinhanhAttack[hoatanhattack];
		target.takeDamage(this.damage);
		this.timeatt = System.currentTimeMillis();
	}
	public void updateVT() {
		Point VTsoldier = this.getVitri();
		Point Vthitbox = this.getHitbox().getVitri();
		Point Vttamdanh = this.tatt.getVitri();
		VTsoldier.setLocation(VTsoldier.x + this.speed, VTsoldier.y);
		Vthitbox.setLocation(Vthitbox.x + this.speed, Vthitbox.y);
		Vttamdanh.setLocation(Vttamdanh.x + this.speed, Vttamdanh.y);
		this.setVitri(VTsoldier);
		this.getHitbox().update(Vthitbox);
		this.getHophitbox().update(Vttamdanh);
	}
	public Soldier getClosestally(Nguoichoi min) {
		LinkedList<Soldier> soldiers = min.getSoldiers();
		int thisIndex = soldiers.indexOf(this);
		return soldiers.get(thisIndex - 1);
	}
	 public void takeDamage(int damage) {
	        super.takeDamage(damage);
	        this.attacked=true;
	        
	    }
	public void draw(Graphics g) {
		if (this.trangthai) {
			this.hinhanhNow = this.hinhanhMove[0];
		}
		if(this.attacked) {
		int healthlegth;
        int percent;
        double fullheath = (double) 25;
        if (this.getTeam() == 1) {
        	if (this.getType()==0) {
            percent = (int) ((this.getHealth() * 100.0f) / (200 + (getEvo() * 50)));
            healthlegth = (int) (fullheath * percent / 100.0);
            g.setColor(Color.RED);
            g.fillRect(this.getVitri().x+15, this.getVitri().y - 15, healthlegth, 5);
            }  else if (this.getType()==2) {
                 percent = (int) ((this.getHealth() * 100.0f) / (300 + (getEvo() * 70)));
                 healthlegth = (int) (fullheath * percent / 100.0);
                 g.setColor(Color.RED);
                 g.fillRect(this.getVitri().x+15, this.getVitri().y - 15, healthlegth, 5);
             }
        } else {
        	if (this.getType()==0) {
                percent = (int) ((this.getHealth() * 100.0f) / (200 + (getEvo() * 50)));
                healthlegth = (int) (fullheath * percent / 100.0);
                g.setColor(Color.RED);
                g.fillRect(this.getVitri().x+15, this.getVitri().y - 15, healthlegth, 5);
                
                } else if (this.getType()==2) {
                     percent = (int) ((this.getHealth() * 100.0f) / (300 + (getEvo() * 70)));
                     healthlegth = (int) (fullheath * percent / 100.0);
                     g.setColor(Color.RED);
                     g.fillRect(this.getVitri().x+15, this.getVitri().y - 15, healthlegth, 5);
                 }
        }
		}
		g.drawImage(this.hinhanhNow, this.getVitri().x, this.getVitri().y, null);
	}
	public int getDamage() {
		return this.damage;
	}
	public Unit getTargetnow() {
		return this.targetnow;
	}
	public void setTargetnow(Unit target) {
		this.targetnow = target;
	}
	public long getTimeattack() {
		return this.timeatt;
	}
	public void setTimeattack(long startTime) {
		this.timeatt = startTime;
	}
	public int getSpeed() {
		return this.speed;
	}
	public Hitbox getHophitbox() {
		return this.tatt;
	}
	public BufferedImage[] getHinhanhmove() {
		return this.hinhanhMove;
	}
	public BufferedImage[] getHinhanhattack() {
		return this.hinhanhAttack;
	}
	public BufferedImage getHinhanhnow() {
		return this.hinhanhNow;
	}
	public void setHinhanhnow(BufferedImage currentSprite) {
		this.hinhanhNow = currentSprite;
	}
	public void setHoatanh(int hoatanh) {
		this.hoatanhmove = hoatanh;
	}
	public double getCostkill() {
		return costkill;
	}
	public long getAttackSpeed() {
		return attackSpeed;
	}
	public int getActivityNow() {
		return activitynow;
	}
	public void setActivityNow(int activitynow) {
		this.activitynow = activitynow;
	}
	public int getHoatanhmove() {
		return hoatanhmove;
	}
	public void setHoatanhmove(int hoatanhmove) {
		this.hoatanhmove = hoatanhmove;
	}
	public int getHoatanhAttack() {
		return hoatanhattack;
	}
	public void setHoatanhAttack(int hoatanhAttack) {
		this.hoatanhattack = hoatanhAttack;
	}
	public boolean getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
		if (trangthai) {
			this.setHinhanhnow(this.getHinhanhmove()[0]);
		}
	}

}