public class QuickSort {


	public static void quickSort(int[] arr, int start, int end) {
		if (start >= end) return;
		int pivot = partition(arr, start, end);
		quickSort(arr, start, pivot - 1);
		quickSort(arr, pivot + 1, end);
	}

	public static int partition(int[] arr, int start, int end) {
		int i = start;
		int j = end;

		int pivot = arr[end];

		while (i <= j) {
			
		}
	}
}