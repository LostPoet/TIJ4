package exercises.reusing;

import reusing.Detergent;

public class E02_InheritFromDetergent extends Detergent {
	@Override
	public void scrub() {
		super.scrub();
		append(" E02_InheritFromDetergent.scrub()");
	}

	public void sterilize() {
		append(" sterilize()");
	}

	public static void main(String[] args) {
		E02_InheritFromDetergent inheritFromDetergent = new E02_InheritFromDetergent();
		inheritFromDetergent.dilute();
		inheritFromDetergent.apply();
		inheritFromDetergent.scrub();
		inheritFromDetergent.foam();
		inheritFromDetergent.sterilize();
		System.out.println(inheritFromDetergent);
	}
}
