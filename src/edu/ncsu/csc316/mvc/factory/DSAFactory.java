package edu.ncsu.csc316.mvc.factory;

//import edu.ncsu.csc316.dsa.data.Identifiable;
//import edu.ncsu.csc316.dsa.list.ArrayBasedList;
//import edu.ncsu.csc316.dsa.list.CircularlyLinkedList;
//import edu.ncsu.csc316.dsa.list.FrontSinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.TailSinglyLinkedList;
//import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
//import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
//import edu.ncsu.csc316.dsa.map.UnorderedArrayMap;
//import edu.ncsu.csc316.dsa.map.UnorderedLinkedMap;
//import edu.ncsu.csc316.dsa.queue.LinkedQueue;
//import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.sorter.MergeSorter;
//import edu.ncsu.csc316.dsa.sorter.QuickSorter;
//import edu.ncsu.csc316.dsa.sorter.RadixSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;
//import edu.ncsu.csc316.dsa.stack.LinkedStack;
//import edu.ncsu.csc316.dsa.stack.Stack;

/**
 * Factory for creating new data structure and algorithm instances
 * 
 * @author Dr. King
 *
 */
public class DSAFactory {
	
	/**
	 * Returns a data structure that implements an hash map
	 * 
	 * @param <K>
	 *            - the key type
	 * @param <V>
	 *            - the value type
	 * @return a data structure that implements an unordered map
	 */
	public static <K, V> Map<K, V> getMap() {
		return getLinearProbingHashMap();
	}

	/**
	 * Returns a data structure that implements an index-based list
	 * 
	 * @param <E>
	 *            - the element type
	 * @return an index-based list
	 */
	public static <E> List<E> getIndexedList() {
		return getTailSinglyLinkedList();
	}

//	/**
//	 * Returns a data structure that implements an positional list
//	 * 
//	 * @param <E>
//	 *            - the element type
//	 * @return a positional list
//	 */
//	public static <E> PositionalList<E> getPositionalList() {
//		return getPositionalLinkedList();
//	}

	/**
	 * Returns a comparison based sorter
	 * 
	 * @param <E>
	 *            - the element type
	 * @return a comparison based sorter
	 */
	public static <E extends Comparable<E>> Sorter<E> getComparisonSorter() {
		return getMergeSorter();
	}

//	/**
//	 * Returns a non-comparison based sorter
//	 * 
//	 * @param <E>
//	 *            - the element type
//	 * @return a non-comparison based sorter
//	 */
//	public static <E extends Identifiable> Sorter<E> getNonComparisonSorter() {
//		return getRadixSorter();
//	}

//	/**
//	 * Returns a data structure that implements a stack
//	 * 
//	 * @param <E>
//	 *            - the element type
//	 * @return a stack
//	 */
//	public static <E> Stack<E> getStack() {
//		return getLinkedStack();
//	}

//	/**
//	 * Returns a data structure that implements a queue
//	 * 
//	 * @param <E>
//	 *            - the element type
//	 * @return a stack
//	 */
//	public static <E> Queue<E> getQueue() {
//		return getLinkedQueue();
//	}

//	/**
//	 * Returns an unordered array-based map
//	 * 
//	 * @return an unordered array-based map
//	 */
//	private static <K, V> UnorderedArrayMap<K, V> getUnorderedArrayMap() {
//		return new UnorderedArrayMap<K, V>();
//	}

//	/**
//	 * Returns an unordered linked map
//	 * 
//	 * @return an unordered linked map
//	 */
//	private static <K, V> UnorderedLinkedMap<K, V> getUnorderedLinkedMap() {
//		return new UnorderedLinkedMap<K, V>();
//	}

//	/**
//	 * Returns a search table
//	 * 
//	 * @return a search table
//	 */
//	private static <K extends Comparable<K>, V> SearchTableMap<K, V> getSearchTableMap() {
//		return new SearchTableMap<K, V>();
//	}

//	/**
//	 * Returns a skip list map
//	 * 
//	 * @return a skip list map
//	 */
//	private static <K extends Comparable<K>, V> SkipListMap<K, V> getSkipListMap() {
//		return new SkipListMap<K, V>();
//	}
	
	/**
	 * returns a hash map using linear probing
	 * 
	 * @return linear probing hash map
	 */
	private static <K, V> LinearProbingHashMap<K, V> getLinearProbingHashMap() {
		return new LinearProbingHashMap<K, V>();
	}
	
//	/**
//	 * returns an avl tree map
//	 * 
//	 * @return avl tree map
//	 */
//	private static <K extends Comparable<K>, V> AVLTreeMap<K, V> getAVLTreeMap() {
//		return new AVLTreeMap<K, V>();
//	}

//	/**
//	 * Returns an array-based list
//	 * 
//	 * @return an array-based list
//	 */
//	private static <E> ArrayBasedList<E> getArrayBasedList() {
//		return new ArrayBasedList<E>();
//	}
//
//	/**
//	 * Returns a singly linked list with front pointer
//	 * 
//	 * @return a singly linked list with front pointer
//	 */
//	private static <E> FrontSinglyLinkedList<E> getFrontSinglyLinkedList() {
//		return new FrontSinglyLinkedList<E>();
//	}

	/**
	 * Returns a singly linked list with front and tail pointers
	 * 
	 * @return a singly linked list with front and tail pointers
	 */
	private static <E> TailSinglyLinkedList<E> getTailSinglyLinkedList() {
		return new TailSinglyLinkedList<E>();
	}

//	/**
//	 * Returns a circularly linked list with a tail pointer
//	 * 
//	 * @return a circularly linked list with a tail pointer
//	 */
//	private static <E> CircularlyLinkedList<E> getCircularlyLinkedList() {
//		return new CircularlyLinkedList<E>();
//	}

//	/**
//	 * Returns a positional linked list with a front pointer
//	 * 
//	 * @return a positional linked list with a front pointer
//	 */
//	private static <E> PositionalLinkedList<E> getPositionalLinkedList() {
//		return new PositionalLinkedList<E>();
//	}

	/**
	 * Returns a mergesorter
	 * 
	 * @return a mergesorter
	 */
	private static <E extends Comparable<E>> Sorter<E> getMergeSorter() {
		return new MergeSorter<E>();
	}
}
