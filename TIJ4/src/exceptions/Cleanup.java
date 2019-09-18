//: exceptions/Cleanup.java
package exceptions; /* Added by Eclipse.py */

// Guaranteeing proper cleanup of a resource.

public class Cleanup {
    public static void main(String[] args) {
	try {
	    InputFile in = new InputFile("Cleanup.java");
	    try {
		String s;
		int i = 1;
		while ((s = in.getLine()) != null)
		    ; // Perform line-by-line processing here...
	    } // OK to delete catch block if the processing doesn't throw exceptions
	    catch (Exception e) {
		System.out.println("Caught Exception in main");
		e.printStackTrace(System.out);
	    } finally {
		in.dispose();
	    }
	} catch (Exception e) {
	    System.out.println("InputFile construction failed");
	}
    }
} /*
   * Output: dispose() successful
   */// :~
