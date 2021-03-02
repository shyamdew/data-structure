package learning.java.edu.forEach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
/*
 * Whenever we need to traverse through a Collection, we need to create an Iterator
 * whose whole purpose is to iterate over and then we have business logic in a loop
 * for each of the elements in the Collection. We might get ConcurrentModificationException
 * if iterator is not used properly.
 *   
 * Java 8 has introduced forEach method in java.lang.Iterable interface so that while
 * writing code we focus on business logic only.forEach method takes java.util.function.Consumer
 * object as argument, so it helps in having our business logic at a separate location that we
 * can reuse. Let’s see forEach usage with simple example.
 */
public class ForEachDemo {
	public static void main(String args[]) {
	//		creating sample Collection
			List<Integer> myList = new ArrayList<Integer>();
			for(int i=0; i<10; i++) myList.add(i);
			
			//traversing using Iterator
			Iterator<Integer> it = myList.iterator();
			while(it.hasNext()){
				Integer i = it.next();
				System.out.println("Iterator Value::"+i);
			}
			
			//traversing through forEach method of Iterable with anonymous class
			myList.forEach(new Consumer<Integer>() {

				public void accept(Integer t) {
					System.out.println("forEach anonymous class Value::"+t);
				}

			});
			
			//traversing with Consumer interface implementation
			/* The number of lines might increase but forEach method helps in having the logic for
			   iteration and business logic at separate place resulting in higher separation of concern
			   and cleaner code.
			 */
			MyConsumer actionT = new MyConsumer();
			myList.forEach(actionT);
			
			//traversing with lambda expresstion
			Consumer action = (i)->{
				System.out.println("Lambda implementation"+i);
			};
			myList.forEach(action);
	}
}
class MyConsumer implements Consumer<Integer>{
	public void accept(Integer t) {
		System.out.println("Consumer impl Value::"+t);
	}


}
