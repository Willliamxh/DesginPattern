package _2designPattern._7strategy.e;

interface Weapon {
	public void hit();
}

class Sword implements Weapon {
	@Override
	public void hit() {
		System.out.println("六脉神剑");
	}
}

class Kinfe implements Weapon {
	@Override
	public void hit() {
		System.out.println("我掐死你");
	}
}

class Fist implements Weapon {
	@Override
	public void hit() {
		System.out.println("降龙十八掌");
	}
}

class Role {
	public String name;
	public int hp;
	
	public Weapon weapon;
	
	public void attck() {
		weapon.hit();
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}



class King extends Role {
	public King() {
		weapon = new Sword();
	}
}

class Queen extends Role {
	public Queen() {
		weapon = new Kinfe();
	}
}


public class Test {

	public static void main(String[] args) {
		Queen q = new Queen();
		q.attck();
		
		q.setWeapon(new Sword());
		
		q.attck();
	}

}
