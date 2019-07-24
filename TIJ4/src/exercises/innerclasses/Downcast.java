// Exercise 11
package exercises.innerclasses;

//public interface CommonInterface {
//	void generalMethod();  
//}

public class Downcast {
	private class InnerToBeDowncasted implements CommonInterface{
		private int id = 1;
		private int getId() { return id; }
		public void generalMethod() {
			System.out.println("Inside generalMethod()");
		}
	}
	CommonInterface getInner() {
		return new InnerToBeDowncasted();
	}
	InnerToBeDowncasted getInner1() {
		return new InnerToBeDowncasted();
	}
	public static void main(String[] args) {
		CommonInterface ci = new Downcast().getInner();
		((InnerToBeDowncasted)ci).generalMethod();
		System.out.println(((InnerToBeDowncasted)ci).getId());
		System.out.println("Code from TestDowncast.main(args):");
		TestDowncast.main(args);
	}
}

class TestDowncast {
	public static void main(String[] args) {
		Downcast dc = new Downcast();
		CommonInterface ci = dc.getInner();
		ci = dc.getInner1();
		ci.generalMethod();
		// Can't downcast for 'InnerToBeDowncasted' is not visible:
		//System.out.println(((InnerToBeDowncasted)ci).id);
		//System.out.println(((InnerToBeDowncasted)ci).getId());
	}
}