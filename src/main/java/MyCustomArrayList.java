import java.util.Arrays;

/**
 * Resizable-array implementation of the List interface.
 * Implements all optional list operations, and permits all elements, including null.
 * In addition to implementing the List interface,
 * this class provides methods to manipulate the size of the array that is used internally to store the list.
 * @author Terentev Oleg (https://github.com/TerentevOleg)
 */
public class MyCustomArrayList<T> {
    private Object[] array;
    private int size;

    public MyCustomArrayList() {
        array = new Object[10];
        size = 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param t - element to be appended to this list.
     */
    public void add(T t) {
        if (size == array.length) {
            newCapacity();
        }
        array[size++] = t;
    }

    /**
     * Inserts an element at the specified index position.
     * Shifts the following elements (if any) to the right.
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size()).
     */
    public void add(int index, T element) {
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        size += 1;
        array[index] = element;
    }

    /**
     * @param index - index of the element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size()).
     */
    public T get(int index) {
        return (T) array[index];
    }

    /**
     * @param index - index of the element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size()).
     */
    public void set(int index, T element) {
        array[index] = element;
    }

    /**
     * Removes an element by index. Shifts subsequent elements to the left.
     * @param index - the index of the element to be removed.
     * @return the element that was removed from the list.
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size()).
     */
    public T remove(int index) {
        T removedElement = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removedElement;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        array = new Object[0];
        size = 0;
    }

    /**
     * @param element - the index to be found.
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int indexOf(T element) {
        for(int i = 0; i < size; i++) {
            if(element == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param element - element whose presence in this list is to be tested.
     * @return true if this list contains the specified element
     */
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    /**
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this ArrayList instance as needed.
     * Increases current capacity by 50%.
     */
    private void newCapacity() {
        int newIncreasedCapacity = (int) (array.length * 1.5 + 1);
        array = Arrays.copyOf(array, newIncreasedCapacity);
    }

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size.
     */
    public void trimToSize(){
        Object[] trimArray = new Object[size];
        System.arraycopy(array, 0, trimArray, 0, size);
        array = trimArray;
    }

    /**
     *
     * @return a character string describing the object.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(array[i]);
            if (i < size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Sorts the elements in an arraylist according to the specified order.
     */
    public void sort() {
        quickSort(array, 0, size() - 1);
    }

    /**
     * Step-by-step description of the sorting algorithm:
     * 1. Select the reference element from the array. As a rule, this is the middle element.
     * 2. Divide the array into 2 subarrays. Elements that are less than
     * the pivot and elements that are greater than the pivot.
     * 3. Recursively apply sorting to both subarrays.
     * O(n * log(n))
     * Description quickSort: https://tproger.ru/articles/algoritmy-sortirovki-na-java-s-primerami/
     *
     * @param sortArray - sortable array.
     * @param startIndex - first element of the array.
     * @param endIndex - last element of the array.
     */

    private void quickSort(Object[] sortArray, int startIndex, int endIndex) {
        //terminate if array is empty or has nothing to divide
        if (sortArray.length == 0 || startIndex >= endIndex) {
            return;
        }

        //select border element
        int middle = startIndex + (endIndex - startIndex) / 2;
        Object border = sortArray[middle];

        //split into subarrays and swap
        int i = startIndex, j = endIndex;
        while (i <= j) {
            while (compare(sortArray[i], border) < 0) {
                i++;
            }
            while (compare(sortArray[j], border) > 0) {
                j--;
            }
            if (i <= j) {
                Object swap = sortArray[i];
                sortArray[i] = sortArray[j];
                sortArray[j] = swap;
                i++;
                j--;
            }
        }

        //recursion to sort left and right side
        if (startIndex < j) {
            quickSort(sortArray, startIndex, j);
        }
        if (endIndex > i) {
            quickSort(sortArray, i, endIndex);
        }
    }

    /**
     * Compares objects to each other.
     * Compares:
     * a negative value means that this object is less than the compared (parameter) object
     * zero means the objects are equal
     * a positive value means that this object is greater than the compared (parameter) object
     * The String class implements the Comparable interface.
     * Its compareTo method compares string values using lexicographical comparison,
     * which is a generalization of alphabetical order.
     * @param o1 - first object.
     * @param o2 - second object.
     */
    private int compare(Object o1, Object o2) {
        if (o1 instanceof Comparable && o2 instanceof Comparable) {
            return ((Comparable<T>) o1).compareTo((T) o2);
        } else return Integer.compare(o1.hashCode(), o2.hashCode());
    }
}
