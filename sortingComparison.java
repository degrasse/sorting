import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class sortingComparison {

	//Bubble Sort
	public static void bubbleSort(int[] L, Comparator<Integer> c){
			int i = 0;
			while( i < L.length){
				int j =i + 1;
				while( j < L.length){
					if(c.compare(L[i], L[j]) > 0){
						int temp = L[i];
						L[i] = L[j];
						L[j] = temp;
					}
					j++;
				}
				i++;
			}
		}
		

	//Selection Sort
	public static void selectionSort(int[] L, Comparator<Integer> c ){
		int i = 0;
		while( i < L.length-1){
			int k = i; int j = i + 1;
			while( j < L.length){
				if(c.compare(L[j], L[k])<0) k=j;	
				j++;
				}	
			i++;
			int small = L[k];
			L[k] = L[i];
			L[i] = small;
		}
	}
		
		
	//Insertion Sort
	public static void insertionSort(int[] L, Comparator<Integer> c ){
		
			int hole=0;
			int val=0;
			for(int i = 1; i < L.length; i++){
				val = L[i];
				hole = i;
				
				while(hole > 0 && c.compare(L[hole-1], val) > 0){
					L[hole] = L[hole-1];
					hole--;
				}
				
				L[hole] = val;
			
			}
	
		}
	

	//Merge Sort 
	public static void mergeSort(int[] L, Comparator<Integer> c){
		int z = L.length;
		if (z < 2) return; //if one item in array then it is already sorted
		//else divide and conquer 
		int half = z/2;
		int[] L1 = Arrays.copyOfRange(L, 0, half); //copy L from zero to half way
		int[] L2 = Arrays.copyOfRange(L,  half, z); //copy L from half to the end	
		
		//recursion
		mergeSort(L1, c);
		mergeSort(L2, c);
		
		merge(L1, L2, L, c); //put back together 
	}

	//Merge step: takes the halves of original array outputs the two combined in new array
	public static void merge(int[] L1, int[] L2, int[] L, Comparator<Integer> c ){
		int x = 0; //number of elements in L2
		int y = 0; //number of elements in L1
		// x+y elements in L
		while( x + y < L.length){
			//if x is at the end of L2 or if  y is not at the end of L1 and is less than x
			if(x == L2.length || ( y < L1.length && c.compare(L1[y], L2[x]) <0)){
				L[x+y] = L1[y++]; //set the value of L at index x+y equal to the yth element of L1 and add one to y	
			}
			else{
				L[x+y] = L2[x++]; //set value of L at index x+y to the xth element of L2 and and one ti x
			}
		}
		
		
	}

	//Quick Sort 
	public static void quickSort(int[] L, Comparator<Integer> c, int lo, int hi){
		if( lo > hi) return;
		int left = lo;
		int right = hi;
		int piv = L[hi];
		int temp;
		
		do{
			while(c.compare(L[left], piv) < 0)
				left++;
			while(c.compare(L[right], piv) > 0)
				right--;
	
			if ( left <= right){
				temp = L[left];
				L[left] = L[right];
				L[right] = temp;
				left++;
				right--;	
			}	
			}while(left <= right);   
		
		/*temp = L[left];
		L[left] = L[hi];
		L[hi] = temp;
		*/
		if(lo < right)
		quickSort(L, c, lo, right);
		if( left < hi)
		quickSort(L, c, left, hi);
			
		}

	public static class MyComparator<Integer> implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return ((java.lang.Integer) o1).compareTo((java.lang.Integer) o2);
		}
	}


/// TESTING ///
	public static  void main(String args[]){
		

		Comparator<Integer> c = new MyComparator<>();
		
		int[] smallSorted = {1,2,3,4,4,5,6,7,8,9,9};
		int[] smallUnsorted = {7,9,3,4,2,8,4,1,6,9,5};
		
	 	int[] largeSorted = IntStream.range(1, 100).toArray();
	    int[] largeUnsorted = new int[100];
	    
	    int[] largerUnsorted = new int[1000];
	    
	    int[] veryLargeUnsorted = new int[10000];
	    
	
	 
	    for(int i = 0; i <  largeUnsorted.length; i++) {
	        largeUnsorted[i] = (int) (Math.random()*100);
	    }
	    
	    for(int i = 0; i <  largerUnsorted.length; i++) {
	        largerUnsorted[i] = (int) (Math.random()*1000);
	    }
	
	    
	    for(int i = 0; i < veryLargeUnsorted.length; i++){
	    	veryLargeUnsorted[i] = (100) + (int)(Math.random()*(10000)); 
	    }
	
	
		
		//Insertion Sort Test
		System.out.println("Insertion Sort, large unsorted: ");
		Long start_time;
		start_time = System.nanoTime();
		insertionSort(largeUnsorted, c);
		Long diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Insertion, large sorted: ");
		start_time = System.nanoTime();
		insertionSort(largeSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("InsertionSort,, small unsorted: ");
		start_time = System.nanoTime();
		insertionSort(smallUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Insertion Sort, small sorted: ");
		start_time = System.nanoTime();
		insertionSort(smallSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Insertion Sort, larger unsorted: ");
		start_time = System.nanoTime();
		insertionSort(largerUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Insertion Sort, very large unsorted: ");
		start_time = System.nanoTime();
		insertionSort(veryLargeUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		
	
		
		//Bubble Sort Test
		System.out.println("Bubble Sort: ");
		//Long start_time;
		start_time = System.nanoTime();
		
		bubbleSort(largeUnsorted, c);
		
		 diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Bubble Sort, large sorted: ");
		start_time = System.nanoTime();
		bubbleSort(largeSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Bubble Sort,, small unsorted: ");
		start_time = System.nanoTime();
		bubbleSort(smallUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Bubble Sort, small sorted: ");
		start_time = System.nanoTime();
		bubbleSort(smallSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Bubble Sort, larger unsorted: ");
		start_time = System.nanoTime();
		bubbleSort(largerUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Bubble Sort, very large unsorted: ");
		start_time = System.nanoTime();
		bubbleSort(veryLargeUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		

		
		//Selection Sort Test
		System.out.println("Selection Sort, large unsorted: ");
		start_time = System.nanoTime();
		selectionSort(largeUnsorted, c);	
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");

		System.out.println("Selection Sort, large sorted: ");
		start_time = System.nanoTime();
		selectionSort(largeSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Selection Sort, small unsorted: ");
		start_time = System.nanoTime();
		selectionSort(smallUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Selection Sort, small sorted: ");
		start_time = System.nanoTime();
		selectionSort(smallSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Selection Sort, larger unsorted: ");
		start_time = System.nanoTime();
		selectionSort(largerUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Selection Sort, very large unsorted: ");
		start_time = System.nanoTime();
		selectionSort(veryLargeUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		

		//Merge Sort Test
		System.out.println("Merge Sort, large unsorted: ");
		
		start_time = System.nanoTime();
		
		mergeSort(largeUnsorted, c);
		
		 diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Merge Sort, large sorted: ");
		start_time = System.nanoTime();
		mergeSort(largeSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Merge Sort, small unsorted: ");
		start_time = System.nanoTime();
		mergeSort(smallUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Merge Sort, small sorted: ");
		start_time = System.nanoTime();
		mergeSort(smallSorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Merge Sort, larger unsorted: ");
		start_time = System.nanoTime();
		mergeSort(largerUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Merge Sort, very large unsorted: ");
		start_time = System.nanoTime();
		mergeSort(veryLargeUnsorted, c);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		
		
		//Quick Sort Test
		System.out.println("Quick Sort, large unsorted: ");
		start_time = System.nanoTime();
		
		quickSort(largeUnsorted, c, 0, largeUnsorted.length-1);
		
		 diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		
		System.out.println("Quick Sort, large sorted: ");
		start_time = System.nanoTime();
		quickSort(largeSorted, c, 0, largeSorted.length-1);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Quick Sort, small unsorted: ");
		start_time = System.nanoTime();
		quickSort(smallUnsorted, c, 0, smallUnsorted.length-1);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Quick Sort, small sorted: ");
		start_time = System.nanoTime();
		quickSort(smallSorted, c, 0, smallSorted.length-1);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Quick Sort, larger unsorted: ");
		start_time = System.nanoTime();
		quickSort(largerUnsorted, c, 0, largerUnsorted.length-1);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");
		
		System.out.println("Quick Sort, very large unsorted: ");
		start_time = System.nanoTime();
		quickSort(veryLargeUnsorted, c, 0, veryLargeUnsorted.length-1);
		diff_time = System.nanoTime() - start_time;
		System.out.println("Took " + diff_time + " nano seconds");

	}
	}

