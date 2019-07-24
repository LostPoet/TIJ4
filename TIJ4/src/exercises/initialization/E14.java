package exercises.initialization;

public class E14 {
	static String str1 = "str1";
	static String str2;
	static {
		str2 = "str2";
	}

	static void printStr() {
		System.out.println(str1);
		System.out.println(str2);
	}

	public static void main(String[] args) {
		printStr();
	}
}
