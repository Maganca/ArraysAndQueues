import java.util.Objects;

/**
 * Name: Ethan Quimpo
 * Class: CSC 210
 * Description:
 * Class that creates a Dynamic array, an array in which the size will increase in the event that the 
 * @author ethan
 *
 */
class DynamicArray
	{
	private Patient[] array;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	/**
	 * Constructor creating the array with the initial parameters, giving it
	 * an initial size.
	 * Complexity: O(1)
	 */
	DynamicArray()
	{
		array = new Patient[DEFAULT_CAPACITY];
		size=0;
	}
	/**
	 * adds the item into an array and doubles the size of the array if the 
	 * array is currently too small
	 * Complexity: O(1)
	 */
	public void add(Patient value)
	{
		if (size >= array.length-1) {
			resize(array.length + 5);
		//-1
		}
		array[size] = value;
		size++;
	}
	/**
	 * returns the number that is in the position of the input index in the array
	 * Complexity: O(1)
	 */
	public Patient get(int index)
	{
		return array[index];
	}
	/**
	 * removes an item at a specific index
	 * complexity: O(n)
	 */
	public void remove(int index)
	{
		if(index >= size || index < 0) {
			System.out.print("No element at this index");
		}
		else {
			for(int i = index; i<size-1;i++) {
				array[i] = array[i+1];
			}
			size--;		
		}
	}
	/**
	 * resizes the length of the dynamic array by a specific amount
	 * Complexity: O(n)
	 */
	public void resize(int capacity)
	{
		Patient[] temp = new Patient[capacity];
		size = capacity < size ? capacity:size;
		for (int i=0; i < size; i++) {
			temp[i] = array[i];
		}
		array = temp;
	}
	/**
	 * returns the size of the array at the moment
	 * Complexity: O(1)
	 */
	public int size()
	{
		return size;
	}
	/**
	 * deterimines if this dynamic array is equal to 
	 * another dynamic array placed in the parameter.
	 * Complexity: O(1)
	 */
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DynamicArray DA = (DynamicArray) obj;
		return Objects.equals(array, DA.array) && 
				Objects.equals(size, DA.size);

	}
	/**
	 * converts the array to a string
	 * Complexity: O(n)
	 */
	public String toString() 
	{
		String str = "{";
		for (int i = 0; i < size; i++) {
			str += (i == 0 ? "" : ",") + array[i];
		}
		str += "}";
		return str;
	}
	
}