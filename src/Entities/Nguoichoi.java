package Entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import java.awt.Font;

public class Nguoichoi {

	private Base base;
	private int team;
	private LinkedList<Soldier> soldiers = new LinkedList<Soldier>();
	private Queue<Soldier> queueSolider = new LinkedList<Soldier>();
	private String name;
	private double gold;

	private int evonow = 0;
	private int costevo;

	private BufferedImage[][] hinhanhmove;
	private BufferedImage[][] hinhanhattack;
	private BufferedImage hinhanhbullet;
	private BufferedImage hinhanhbase;

	public Nguoichoi(int team, String name, BufferedImage[][] move, BufferedImage[][] attack,BufferedImage bullet, BufferedImage base) {
		this.team = team;
		this.name = name;
		this.hinhanhmove = move;
		this.hinhanhattack = attack;
		this.hinhanhbullet = bullet;
		this.hinhanhbase = base;
		
		this.gold = 200;
		this.costevo = 1000;
		this.base = new Base(team, 3, this.evonow, this.hinhanhbase);
		this.evonow = 0;
	}

	public void queueSolider(int cost, int type) {
		if (this.queueSolider.size() < 4) {
			this.gold -= cost;
			Soldier soldier = null;
			BufferedImage hinhanhbullet = null;
			if (type == 0 || type == 2) {
				soldier = new Soldier(team, type, evonow, hinhanhmove[type],hinhanhattack[type]);
			} else if (type == 1) {
					hinhanhbullet = this.hinhanhbullet;
				
				soldier = new Sniper(team, type, evonow, hinhanhmove[type],hinhanhattack[type], hinhanhbullet);
			}
			this.queueSolider.add(soldier);
		
	    }
	}
	public void Summon() {
		this.soldiers.add(this.queueSolider.poll());
	}
	public boolean evolve() {
		if (this.evonow == 10) {
			return false;
		} else {
		this.evonow++;
		this.gold -= this.costevo;
		this.costevo = this.evonow * 1000;
		this.base.setHinhanhnow(hinhanhbase);
		return true;
		}
	}
	public int soldierdie() {
		int costthem = 0;
		for (int i = 0; i < this.soldiers.size(); i++) {
			if (this.soldiers.get(i).getHealth() <= 0) {
				Soldier soldier = this.soldiers.remove(i);
				costthem += soldier.getCostkill();
			}
		}
		return costthem;
	}
	public void gainGold() {
		this.gold += 50;
	}

	public void gainGold(int amount) {
		this.gold += amount;
	}
	public void auto(Nguoichoi other) {
		Soldier soldier;
		Unit closestenemy;
		Soldier closestally = null;
		long timenow;
		long timeatt;
		for (int soldiertarget = 0; soldiertarget < this.soldiers.size(); soldiertarget++) {
			soldier = this.soldiers.get(soldiertarget);
			closestenemy = soldier.getClosestenemy(other);
			soldier.setTargetnow(closestenemy);
			if (soldiertarget != 0) {
				closestally = soldier.getClosestally(this);
			}
			if (soldier instanceof Sniper) {
				for (int i = 0; i < ((Sniper) soldier).getBullets().size(); i++) {
					Bullet tb = ((Sniper) soldier).getBullets().get(i);
					tb.move();
					if (tb.CheckVC(soldier.getTargetnow())) {
						soldier.getTargetnow().takeDamage(soldier.getDamage());
						((Sniper) soldier).getBullets().remove(i);
					}
					if (soldier.getTeam() == 1) {
						if (tb.getVitri().x - tb.getVitriban().x > ((Sniper) soldier).getHophitbox()
								.getHophitbox().getWidth()) {
							((Sniper) soldier).getBullets().remove(i);
						}
					} else {
						if (tb.getVitriban().x - tb.getVitri().x > ((Sniper) soldier).getHophitbox()
								.getHophitbox().getWidth()) {
							((Sniper) soldier).getBullets().remove(i);
						}
					}
				}
			}
			if (soldier.getHophitbox().checkvacham(closestenemy.getHitbox())) {
				timenow = System.currentTimeMillis();
				timeatt = timenow - soldier.getTimeattack();
			if (soldier.getTimeattack() == 0 || (timeatt >= soldier.getAttackSpeed())) {
					soldier.attack(closestenemy);
					soldier.setTrangthai(false);
				}
			} else {
				if (soldiertarget == 0) {
					soldier.move();
					soldier.setTrangthai(false);

				} else {
					if (this.getTeam() == 1) {
						if ((soldier.getVitri().x + soldier.getWidth()
								+ soldier.getSpeed() < closestally.getVitri().x)) {
							soldier.move();
							soldier.setTrangthai(false);
						} else {
							soldier.setTrangthai(true);
						}

					} else {
						if ((soldier.getVitri().x - soldier.getWidth()
								+ soldier.getSpeed() > closestally.getVitri().x)) {
							soldier.move();
							soldier.setTrangthai(false);
						} else {
							soldier.setTrangthai(true);
						}
					}
				}
			}
		}
			
     } 

	public void draw(Graphics g) {
		this.base.draw(g);
		for (Soldier soldier : this.soldiers) {
			soldier.draw(g);
		}
		Font guideFont = new Font("Tahoma", Font.BOLD, 21);
		g.setFont(guideFont);
		if (this.team == 1) {
			if (this.getGold() >= this.getCostEvo() && this.evonow<=9) {
				g.drawString("nhan A de len doi", 50, 714 - 50);
				g.drawString(Integer.toString(this.getCostEvo()), 275, 714 - 50);
			}
			g.drawString(this.name, 400, 20);
			g.drawString(Double.toString(this.gold), 430, 75);
			g.drawString(Integer.toString(this.evonow), 450, 100);		
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < this.queueSolider.size(); j++) {
					g.fillRect(4 + (j * 65) + 5, 10, 45, 25);
				}
				g.drawRect(4 + (i * 65) + 5, 10, 45, 25);
			}
		} else {
			if (this.getGold() >= this.getCostEvo() && this.evonow<=9) {
				g.drawString("nhan L de len doi", 950, 714 - 50);
				g.drawString(Integer.toString(this.getCostEvo()), 860, 714 - 50);
			}
			g.drawString(this.name, 700, 20);
			g.drawString(Double.toString(this.gold), 730, 75);
			g.drawString(Integer.toString(this.evonow), 750, 100);	
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < this.queueSolider.size(); j++) {
					g.fillRect(1200 - (25 * 2) - 215 + (j * 65), 10, 45, 25);
				}
				g.drawRect(1200 - (25 * 2) - 215 + (i * 65), 10, 45, 25);
			}
		}
	}
	public boolean checkGameOver() {
		if (this.base.getHealth() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public Base getBase() {
		return this.base;
	}

	public int getTeam() {
		return this.team;
	}

	public LinkedList<Soldier> getSoldiers() {
		return this.soldiers;
	}

	public Queue<Soldier> getQueueSoldier() {
		return queueSolider;
	}

	public double getGold() {
		return gold;
	}

	public String getName() {
		return this.name;
	}

	public int getEvonow() {
		return this.evonow;
	}

	public int getCostEvo() {
		return costevo;
	}
}
