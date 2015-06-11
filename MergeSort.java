public class MergeSort {

	public static void mergeSort(int[] arr) {
		int n = arr.length;
		mergeSort(arr, 0, n - 1);
	}

	public static void mergeSort(int[] arr, int start, int end) {
		if (start >= end) return;
		int mid = (start + end) / 2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid + 1, end);
		merge(arr, start, mid, end);
	}

	public static void merge(int[] arr, int start, int mid, int end) {
		int i = 0, j = 0, k = 0;
		int n1 = mid - start + 1;
		int n2 = end - mid;
		int[] left = new int[n1];
		int[] right = new int[n2];

		for (k = 0; k < n1; k++) {
			left[k] = arr[start + k];
		}

		for (k = 0; k < n2; k++) {
			right[k] = arr[mid + k + 1];
		}

		k = 0;
		while (i < n1 && j < n2) {
			if (left[i] <= right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		while (i < n1) {
			arr[k++] = left[i++];
		}

		while (j < n2) {
			arr[k++] = right[j++];
		}
	}

	public static void main(String[] args) {

		int[] arr = { 1, 5, 3, 2, 4};
		
		mergeSort(arr);

		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}