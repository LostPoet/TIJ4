//: generics/MultipleInterfaceVariants.java
package generics; /* Added by Eclipse.py */
// {CompileTimeError} (Won't compile)

interface Payable<T> {}

//! Employee is already been defined:
// class Employee implements Payable<Employee> {}

// class Hourly extends Employee
//  implements Payable<Hourly> {} ///:~
