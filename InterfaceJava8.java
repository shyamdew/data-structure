//  interfaces are enhanced to have method with implementation. We can use default and static keyword to create interfaces with method implementation.
public interface Interface1 {

	void method1(String str);
	
	default void log(String str){
		System.out.println("I1 logging::"+str);
	}
}

public interface Interface2 {

	void method2();
	
	default void log(String str){
		System.out.println("I2 logging::"+str);
	}

}

public class MyClass implements Interface1, Interface2 {
	@Override
	public void method2() {
	}

	@Override
	public void method1(String str) {
	}

	@Override
	public void log(String str){
		System.out.println("MyClass logging::"+str);
		Interface1.print("abc");
	}
}
// Java interface default methods will help us in extending interfaces without having the fear of breaking implementation classes.
// Java interface default methods has bridge down the differences between interfaces and abstract classes.
// Java 8 interface default methods will help us in avoiding utility classes, such as all the Collections class method can be provided in the interfaces itself.
// Java interface default methods will help us in removing base implementation classes, we can provide default implementation and the implementation classes can chose which one to override.
// One of the major reason for introducing default methods in interfaces is to enhance the Collections API in Java 8 to support lambda expressions.
// If any class in the hierarchy has a method with same signature, then default methods become irrelevant. A default method cannot override a method from java.lang.Object. The reasoning is very simple, it’s because Object is the base class for all the java classes. So even if we have Object class methods defined as default methods in interfaces, it will be useless because Object class method will always be used. That’s why to avoid confusion, we can’t have default methods that are overriding Object class methods.
// Java interface default methods are also referred to as Defender Methods or Virtual extension methods.
