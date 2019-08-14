package exercises.access;

class Connection {
	private static final int MAX_SIZE = 10;
	private static int count = 0;

	private Connection() {
		count++;
	}

	public static Connection establishConnection() {
		if (count == MAX_SIZE)
			return null;
		return new Connection();
	}
}

public class E08_ConnectionManager {
	public static void main(String[] args) {
		Connection[] connections = new Connection[12];
		for (Connection connection : connections) {
			connection = Connection.establishConnection();
			System.out.println(connection);
		}
	}
}
