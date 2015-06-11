public class InsertionSort {

	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i - 1;
			int temp = arr[i];

			while (j >= 0 && arr[j] > temp) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
	}

	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 2, 4};
		insertionSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}
}