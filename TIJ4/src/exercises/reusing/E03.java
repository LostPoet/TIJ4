package exercises.reusing;

class Art {
	Art() {
		System.out.println("Art constructor");
	}
}

class Drawing extends Art {
	Drawing() {
		System.out.println("Drawing constructor");
	}
}

public class E03 extends Drawing {
	public static void main(String[] args) {
		new E03();
	}
}
