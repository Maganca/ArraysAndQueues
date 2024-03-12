/**
 * Name: Ethan Quimpo
 * Class: CSC 210
 * Description: Patient Queue is a priority queue that allows for the input and removal of 
 * "Patients", objects with a string name and an integer priority. The smaller the priority number
 * the closer the patient is to the beginning of the "line" or ArrayList
 * @author ethan
 * 4/14/2023
 */
public class PatientQueue {
	public Heap h = new Heap();
	/**
	 * Constructor, adds 10 null items to heap
	 */
	public PatientQueue() {
		
		for(int i = 0; i > 10; i++) 
			h.add(null);
	}		
	/**
	 * adds the given person into your 
	 *patient queue with the given priority. You must ”bubble up” 
	 *appropriately to keep your array in proper heap order.
	 * @param name
	 * @param priority
	 */
	public void enqueue(String name, int priority) {
		h.add(new Patient(name, priority));
	}
	/** 
	 * does the same thing but inserts the patients directly
	 * @param patient
	 */
	public void enqueue(Patient patient) {
		h.add(patient);
	}
	/**
	 * removes the front most patient in your
	 *queue, and return their name as a string. throws an
	 *exception if the queue is empty.
	 */
	public String dequeue() throws Exception {
		if (h.isEmpty() == true || h.getPatient(1) == null) {
			throw new Exception();
		}
		// return exception if queue empty
		return h.remove().name;
	/**
	 * returns the name of the front most pa-
	 * tient in the queue, without removing or altering the state of the
	 * queue.throws an exception if the queue is empty.
	 */
	}
	public String peek() throws Exception {
		if (h.isEmpty() == true || h.getPatient(1) == null) {
			throw new Exception();
		}
		// return exception if queue empty
		return h.getPatientName(1);
		
	/**
	 * return the integer priority of the
	 *front most patient in the queue without removing it or altering
	 *the state of the queue. You should throw an exception if the
	 *queue is empty.
	 * @return
	 */
	}
	public int peekPriority() throws Exception {
		if (h.isEmpty() == true || h.getPatient(1) == null) {
			throw new Exception();
		}
		// return exception if queue empty
		return h.getPatientPriority(1);
	}
	/**
	 * modifies the priority of a given exist-
	 *ing patient in your queue by creating a new heap, adding
	 *all patients except the old one, then adding the patient with the 
	 *modified priority
	 * @param name
	 * @param newPriority
	 */
	public void changePriority(String name, int newPriority){
		Heap tempHeap = new Heap();
		
		for (int i = 0;i <= h.size;i++) {
			if (h.getPatient(i) != null) {
				if (!h.getPatientName(i).equals(name)) {
					tempHeap.add(h.getPatient(i));
				}
			}
		}
		Patient tempPatient = new Patient(name, newPriority);
		
		tempHeap.add(tempPatient);
		h = tempHeap;
	}
	
	/** 
	 * returns boolean based on whether the heap is empty
	 * @return
	 */
	public boolean isEmpty() {return h.size == 0;}
	/**
	 * returns size of the heap
	 * @return
	 */
	public int size() {return h.size;}
	/**
	 * clears the entirety of the heap, removing items from the front until nothing is left
	 */
	public void clear() {
		h.clear();
	}
	/** 
	 * Converts heap to string and prints to console
	 */
	public String toString() {
		return h.toString();	
	}
	
}
/**
 * heap class, sets up the bones of the data structure for the 
 * patient queue. 
 * @author ethan
 */
class Heap
{
	/**
	 * start by making a heap from an arraylist
	 */
	private DynamicArray heap;
	/**
	 * size int
	 */
	protected int size;
	/**
	 * constructor, sets up the array list the heap works on
	 */
	public Heap(){
		heap = new DynamicArray();
		heap.add(null); // the element at index 0
		size = 0;
	}
	/**
	 * getPatient returns the patient at specified index
	 * @param i - index
	 */
	public Patient getPatient(int i) {
		return heap.get(i);
	}
	/**
	 * getPatientName returns name of the patient at specified index
	 * @param i - index
	 */
	public String getPatientName(int i) {
		return heap.get(i).name;
	}
	/**
	 * getPatientPrioirity returns the patient priority of patient with specified name
	 * @param name - name of desired patient
	 */
	public int getPatientPriority(String name) {
		for(int i = 0; i < heap.size();i++ ) {
			if (heap.get(i) != null) {
				if (getPatientName(i).equals(name)) {
					return getPatientPriority(i);
				}
			}
				
		}
		return 0;
	}
	/**
	 * getPatientPrioirity returns the patient priority at specified index
	 * @param i - index
	 */
	public int getPatientPriority(int i) {
		return heap.get(i).priority;
	}
	/**
	 * sets patient priority of patient at specified indexc
	 * @param i - index
	 * @param newPriority - new desired priority
	 */
	public void setPatientPriority(int i, int newPriority) {
		heap.get(i-1).priority = newPriority; 
	}
	/**
	 * compareTo compares two patient and returns 1 if patient a's priority is bigger, 
	 * -1 if a's priority is smaller, and 0 if they are equal
	 * @param a - patient to be compared
	 * @param b - patient to be compared
	 */
	private int compareTo(Patient a, Patient b) {
		if(a.priority > b.priority ) {
			return 1;
		}
		else if (a.priority < b.priority) {
			return -1;
		}
		else {
			return 0;
		}
	}
	/**
	 * compare returns true if a is less than b
	 * @param a - patient to be compared
	 * @param b - patient to be compared
	 */
	private boolean compare(Patient a, Patient b) {
		return (compareTo(a,b) < 0);
	}
	/**
	 * These three methods return the parents/children of the input index.
	 * @param i - index
	 * 
	 */
	private int parent(int i) {return i/2;}
	
	private int left(int i) {return 2*i;}
	
	private int right(int i) {return 2*i + 1;}
	
	/**
	 * bubbles up the patient at the index recursively until desired area is reached
	 * @param i - index
	 */
	protected void bubbleUp(int i){
		if (i > 1){
			if ( compare(heap.get(i), heap.get(parent(i))) ){
				Patient e = set(parent(i), heap.get(i));
				set(i, e);
				bubbleUp(parent(i));
			}
		}
	}
	/**
	 * bubbles down the patient at the index recursively until desired area is reached
	 * @param i - index
	 */
	protected void bubbleDown(int i)
	{
	       if (left(i) <= size)
	       {
	           // find higher priority child
	           int higherpriorityChild = left(i);
	           if (right(i) <= size && compare(heap.get(right(i)), heap.get(left(i))))
	               higherpriorityChild = right(i);
	           // check if we need to swap
	           if (compare(heap.get(higherpriorityChild), heap.get(i)))
	           {
	               // swap with child of higher priority
	               Patient e = heap.get(higherpriorityChild);
	               set(higherpriorityChild, heap.get(i));
	               set(i, e);
	               // recurse
	               bubbleDown(higherpriorityChild);
	           }
	       }
	   }
	/**
	 * clears the heap
	 */
	public void clear() {
		heap = new DynamicArray();
	}
	/**
	 * adds the input patient
	 * @param e - patient to be input
	 */
	public void add(Patient e)
	{
		size++;
		heap.add(e);
		bubbleUp(size);
	}
	/**
	 * removes patient at end 
	 * @return
	 */
	public Patient remove() throws Exception
	{
		if (isEmpty()|| getPatient(1) == null)
			throw new Exception();
		Patient e = heap.get(1);
		set(1, heap.get(size));
		size--;
		bubbleDown(1);
		return e;
	}
	/**
	 * @return - returns true if heap is empty
	 */
	public boolean isEmpty() {return size == 0;}
	/**
	 *
	 * @return - returns size of heap
	 */
	public int size() {return size;}
	/**
	 * replaces element at specified index and replaces it wih newPatient, 
	 * returns previous item there.
	 * @param index
	 * @param newPatient
	 * @return
	 */
	public Patient set(int index, Patient newPatient) {
		Patient retPatient = new Patient(heap.get(index).name,heap.get(index).priority);
		heap.get(index).name = newPatient.name;
		heap.get(index).priority = newPatient.priority;
		return retPatient;
	}
	/**
	 * converts heap into string
	 */
	public String toString()
	{
		String s = "{";
		for (int i = 1; i <= size; i++)
			s += (i == 1 ? "" : ", ") + heap.get(i);
		return s + "}";
	}

}
