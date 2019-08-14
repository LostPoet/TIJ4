package exercises.access;

class ProtectedData {
	protected String data = "secret";
}

public class E06 {
	void manipulate() {
		ProtectedData pd = new ProtectedData();
		System.out.println(pd.data);
	}

	public static void main(String[] args) {
		new E06().manipulate();
	}
}
