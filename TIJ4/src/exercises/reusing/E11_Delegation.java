package exercises.reusing;

class Cleanser {
	private String s = "Cleanser";

	public void append(String a) {
		s += a;
	}

	public void dilute() {
		append(" dilute()");
	}

	public void apply() {
		append(" apply()");
	}

	public void scrub() {
		append(" scrub()");
	}

	public String toString() {
		return s;
	}
}

public class E11_Delegation {
	private Cleanser cleanser = new Cleanser();

	public void dilute() {
		cleanser.dilute();
	}

	public void apply() {
		cleanser.apply();
	}

	public void scrub() {
		cleanser.scrub();
	}

	public String toString() {
		return cleanser.toString();
	}

	public static void main(String[] args) {
		E11_Delegation delegation = new E11_Delegation();
		delegation.dilute();
		delegation.apply();
		delegation.scrub();
		System.out.println(delegation);
	}
}
