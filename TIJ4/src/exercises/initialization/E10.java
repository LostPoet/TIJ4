package exercises.initialization;

class Final {
    // add try-finally block followed by API documentation of finalize()
    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
	try {
	    System.out.println("finalizing...");
	} finally {
	    super.finalize();
	}
    }
}

public class E10 {
    public static void main(String[] args) {
	new Final();
    }
}
