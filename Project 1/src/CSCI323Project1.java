import java.util.Arrays;

/***
 * CSCI323 Project 1
 * Project Creators:
 * 	Brian Aguilar
 *  Tristen Aguilar
 *  Danish Bokhari
 * 
 * 
 ***/
class CSCI323Project1{
	static int COUNTER = 0;
  	public static void main(String[] args) {
  		int [] arr = createArray(100000);
		int k = arr.length/2;
		System.out.println(COUNTER);
  	}


  	public static void findMedian(int arr[]){
        int median = findMedianUtil(arr,(arr.length)/2 + 1,0,arr.length-1);
        System.out.println("Median = " + median);
    }
    
    private static int findMedianUtil(int arr[],int k,int low,int high)
    {
        if(low == high) return arr[low];
        // sort the mth largest element in the given array
        int m = partition(arr,low,high);
        // Adjust position relative to the current subarray being processed
        int length = m - low + 1;
        // If mth element is the median, return it
        if(length == k) return arr[m];
        // If mth element is greater than median, search in the left subarray
        if(length > k) return findMedianUtil(arr,k,low,m-1);
        else return findMedianUtil(arr,k-length,m+1,high);
    }
    
    
    private static int partition(int arr[],int low, int high){
        // Get pivotvalue by finding median of medians
        int pivotValue = getPivotValue(arr, low, high);        
        // Find the sorted position for pivotVale and return it's index
        while(low < high){
        	COUNTER+=1;
            while(arr[low] < pivotValue) {
            	COUNTER+=1;
            	low++;
            }
            
            while(arr[high] > pivotValue) {
            	COUNTER+=1;
            	high--;
            }
            
            if(arr[low] == arr[high]){
            	COUNTER+=1;
                low++;
            }
            else if(low < high){
            	COUNTER+=1;
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }         
        }
        return high;
    }
    
    // Find pivot value, such the it is always 'closer' to the actual median
    private static int getPivotValue(int arr[],int low,int high){
        COUNTER +=1;
        if(high-low+1 <= 9){
            Arrays.sort(arr);
            return arr[arr.length/2];
        }
        int temp[] = null;
        int medians[] = new int[(int)Math.ceil((double)(high-low+1)/5)];
        int medianIndex = 0;
        while(low <= high){
            temp = new int[Math.min(5,high-low+1)];
            for(int j=0;j<temp.length && low <= high;j++){
                temp[j] = arr[low];
                low++;
            }
            Arrays.sort(temp);
            medians[medianIndex] = temp[temp.length/2];
            medianIndex++;
        }
        return getPivotValue(medians,0,medians.length-1);
    }

    	/**
	 * Sorts an array using insertion sort
	 * @param arr input array
	 * @param n the size of the array
	 */
	public static void insertionSort(int [] arr, int n) {
		for (int i = 1; i < n; i++) {
			int key = arr[i], j = i-1;
			while (j >=0 && key < arr[j]) {
                COUNTER+=2;
				arr[j+1] = arr[j];
				j -=1;
			}
			arr[j+1] = key; 
		}
	}
	
	/**
	 * Sorts an array using selection sort
	 * @param arr input array
	 * @param n the size of the array
	 */
	public static void selectionSort(int [] arr, int n) {
		for (int i = 0; i < n-1; i++) {
			int min = i;
			for (int j = i+1; j < n; j++) {
				if (arr[min] > arr[j]){
          COUNTER+=1;
          min = j;
        }
			}
			swap(arr,min,i);
		}
	}
	
	/**
	 * Calls on an auxiliary method to actually perfrom merge sort
	 * @param arr the input array
	 * @param n the size of the array
	 * @param k the kth smallest element you want to find
	 */
	public static void mergeSort(int [] arr, int n, int k) {
		mergeSort(arr,0,n-1,k);
	}
	
	/**
	 * Sorts an array using merge sort
	 * @param arr the input array
	 * @param start the start index of the array
	 * @param end the end index of the array
	 * @param k the kth smallest element that you want to find
	 */
	public static void mergeSort(int [] arr, int start, int end,int k) {
		if (start < end) {
			COUNTER+=1;
			int mid = (start+end)/2;
			mergeSort(arr,start,mid,k);
			mergeSort(arr,mid+1,end,k);
			merge(arr,start,mid,end,k);
		}
	}
	
	/**
	 * Merges two arrays into one sorted array
	 * @param arr the input array
	 * @param l the beginning index of the current split array
	 * @param m the middle index of the current split array
	 * @param r the ending index of the current array array
	 * @param min the kth smallest element you want to find in the array
	 */
	public static void merge(int [] arr, int l, int m, int r, int min) {
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 

        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j];

        int i = 0, j = 0; 
 
        int k = l; 
        int counter = 0;
        while (i < n1 && j < n2) 
        { 
            COUNTER+=1;
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
            counter++;
            if(counter == min && l == 0 && r == arr.length-1)
            	return;
        } 
  
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
            counter++;
            if(counter == min && l == 0 && r == arr.length-1)
            	return;
        } 
  
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
            counter++;
            if(counter == min && l == 0 && r == arr.length-1)
            	return;
        } 
	}
	
	/**
	 * Sorts an array by building an array into a heap
	 * and call heapify on each element
	 * @param arr the input array
	 * @param n the size of the array
	 */
	public static void heapSort(int [] arr, int n) {
		for (int i = (n/2)-1; i >=0; i--) {
			heapify(arr,n,i);
		}
		
		for (int i = n-1; i >= 0; i--) {
			swap(arr,i,0);
			heapify(arr,i,0);
		}
		
	}
	
	/**
	 * A helper method to put a certain element in the right
	 * position of the heap
	 * @param arr the input array
	 * @param n the size of the array
	 * @param i the current index of the element that you are checking
	 */
	public static void heapify(int [] arr, int n, int i) {
		int largest = i, left = (2*i) +1, right = (2*i)+2;
		
		if (left < n && arr[largest] < arr[left]) {
			COUNTER+=1;
			largest = left;	
		}
		if (right < n && arr[largest] < arr[right]) {
			COUNTER+=1;	
			largest = right;
		}
		if (largest != i) {
			COUNTER+=1;
			swap(arr,largest,i);
			heapify(arr,n,largest);
		}
	}
	
	
	public static void quickSelect(int [] arr, int n, int k) {
		quickSelect(arr,0,n,k);
	}
	
	public static void quickSelect(int [] arr, int l, int r, int k) {
		  // If k is smaller than number of  
	    // elements in array
       
	    if (k > 0 && k <= r - l + 1) { 
	    	COUNTER+=2;
	        // Partition the array around last  
	        // element and get position of pivot  
	        // element in sorted array 
	        int index = partition(arr, l, r); 
	  
	        // If position is same as k 
	        if (index - l == k - 1) {
	        	 COUNTER+=1;
	        	 return;
	        }
	           
	        // If position is more, recur  
	        // for left subarray 
          
	        if (index - l > k - 1)  {
	        	COUNTER+=1;
	        	quickSelect(arr, l, index - 1, k); 
	        }
	            
	  
	        // Else recur for right subarray 
	        quickSelect(arr, index + 1, r,  
	                            k - index + l - 1); 
	    } 
	  
	}
	
	/**
	 * Swaps two elements in an array
	 * @param arr the given input array
	 * @param i an array index
	 * @param j an array index
	 */
	public static void swap(int [] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Prints out elements in an array
	 * @param arr given input array
	 */
	public static void printArray(int [] arr) {
		System.out.println("Current State Of The Array: ");
		System.out.print("[ ");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}
	
	
	/**
	 * Finds the Kth smallest element in a given integer array
	 * @param arr input array
	 * @param k the kth smallest element you want to find
	 * @param sort the sorting algorithm you want to use
	 * 1 = Insertion Sort
	 * 2 = Merge Sort
	 * 3 = Selection Sort
	 * 4 = Heap Sort
	 * 5 = Quick Select
	 * @return the kth smallest element in a given array
	 */
	public static int findKthOrderStatistic(int [] arr, int k, int sort) {
		
		if (arr.length < k || k < 0) {
			System.out.println("Can't find kth element");
			return -1;
		}
		
		switch(sort) {
			case 1:
				System.out.println("Sorting Array Using Insertion Sort");
				insertionSort(arr, arr.length);
				break;
			case 2:
				System.out.println("Sorting Array Using Merge Sort");
				mergeSort(arr,arr.length,k);
				break;
			case 3:
				System.out.println("Sorting Array Using Selection Sort");
				selectionSort(arr,k);
				break;
			case 4:
				System.out.println("Sorting Array Using Heap Sort");
				heapSort(arr,k);
				break;
			case 5:
				System.out.println("Sorting Array Using Quick Select");
				quickSelect(arr,arr.length-1,k);
				break;
		}
		
		
		return arr[k-1];
	}
	
	/**
	 * Creates a random integer array 
	 * @param n the size of the array
	 * @return an array of random integers
	 */
	public static int[] createArray(int n) {
		int [] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int num = (int) (Math.random()*1000 + 1);
			arr[i] = num;
		}
		return arr;
	}
}