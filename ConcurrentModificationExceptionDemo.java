package learning.java.edu.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExceptionDemo {

	public static void main(String args[]) {
//		withException(); // Has ConcurrentModificationException
//		Solution for the concurrent modification exception
//		We can use ConcurrentHashMap and CopyOnWriteArrayList classes.
//		This is the recommended approach to avoid concurrent modification exception.
//		withoutException();
		concurrentModificationExceptionWithArrayListSubList();
		
	}
	static void concurrentModificationExceptionWithArrayListSubList(){
		List<String> names = new ArrayList<>();
		names.add("Java");
		names.add("PHP");
		names.add("SQL");
		names.add("Angular 2");

		List<String> first2Names = names.subList(0, 2);

		System.out.println(names + " , " + first2Names);

		names.set(1, "JavaScript");
		// check the output below. :)
		System.out.println(names + " , " + first2Names);

		// Let's modify the list size and get ConcurrentModificationException
		names.add("NodeJS");
		System.out.println(names);
		System.out.println(names + " , " + first2Names); // this line throws exception
		/*
		 * According to the ArrayList subList documentation, structural modifications is
		 * allowed only on the list returned by subList method. All methods on the returned
		 * list first check to see if the actual modCount of the backing list is equal to
		 * its expected value and throw a ConcurrentModificationException if it is not.
		 */
	}
	private static void withoutException() {
		List<String> myList = new CopyOnWriteArrayList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		Iterator<String> it = myList.iterator();
		while (it.hasNext()) {
			String value = it.next();
			System.out.println("List Value:" + value);
			if (value.equals("3")) {
				myList.remove("4");
				myList.add("6");
				myList.add("7");
			}
		}
//		Concurrent Collection classes can be modified safely, they will not throw ConcurrentModificationException.
//		In case of CopyOnWriteArrayList, iterator doesn’t accommodate the changes in the list and works on the original list.
		System.out.println("List Size:" + myList.size());
		
//		In case of ConcurrentHashMap, the behavior is not always the same.For condition:
		Map<String, String> myMap = new ConcurrentHashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "2");
		myMap.put("3", "3");

		Iterator<String> it1 = myMap.keySet().iterator();
		while (it1.hasNext()) {
			String key = it1.next();
			System.out.println("Map Value:" + myMap.get(key));
			if (key.equals("2")) {
				myMap.remove("3");
				myMap.put("4", "4");
				myMap.put("5", "5");
			}
		}

		System.out.println("Map Size:" + myMap.size());
	}

	private static void withException() {
		List<String> myList = new ArrayList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		Iterator<String> it = myList.iterator();
		while (it.hasNext()) {
			String value = it.next();
			/*
			 *Q Why this line throwing exception ? 
			 *A it’s implementation is present in the AbstractList class, where
			 *  an int variable modCount is defined. The modCount provides the
			 *  number of times list size has been changed. The modCount value is
			 *  used in every next() call to check for any modifications in a function checkForComodification()
			 */
			System.out.println("List Value:" + value);
			if (value.equals("3")) {
//				myList.remove(value);
			}
		}

		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "2");
		myMap.put("3", "3");

		Iterator<String> it1 = myMap.keySet().iterator();
		while (it1.hasNext()) {
			String key = it1.next();
			/*
			 *Q Why this above throwing exception ? 
			 *A it’s implementation is present in the AbstractList class, where
			 *  an int variable modCount is defined. The modCount provides the
			 *  number of times list size has been changed. The modCount value is
			 *  used in every next() call to check for any modifications in a function checkForComodification()
			 */
			System.out.println("Map Value:" + myMap.get(key));
			if (key.equals("2")) {
				myMap.put("1", "4");
				 myMap.put("4", "4");
			}
		}
	}
}
