import java.util.ArrayList;

public class LinearMedian {
	public int mMedian(int[] raw) {
		if (raw.length == 1) return raw[0];
		int[] medianArray = new int[(raw.length - 1) / 5 + 1];
		for (int i = 0; i < (raw.length - 1) / 5 + 1; i++) {
			int start = i * 5;
			int end = i * 5 + 5;
			int[] temp;
			if (end <= raw.length)
				temp = new int[5];
			else {
				temp = new int[raw.length - start];
			}
			for (int j = start; j < end && j < raw.length; j++) {
				temp[j - start] = raw[j];
			}
			medianArray[i] = smallMedian(temp);
		}
		return findMedian(medianArray, medianArray.length / 2);
	}
	
	public int smallMedian(int[] raw) {
		for (int i = 0; i < raw.length - 1; i++) {
			for (int j = i + 1; j < raw.length; j++) {
				if (raw[i] < raw[j]) {
					int temp = raw[i];
					raw[i] = raw[j];
					raw[j] = temp;
				}
			}
		}
		return raw[raw.length / 2];
	}

	public int findMedian(int[] raw, int k) {
		if (raw.length == 1) return raw[0];
		int median = mMedian(raw);
		if (raw.length <= 5) return median;
		return partitionMedian(raw, median, k);
	}
	
	public int partitionMedian(int[] raw, int median, int k) {
		if (raw.length == 1) return raw[0];
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		for (int i = 0; i < raw.length; i++) {
			if (raw[i] < median) left.add(raw[i]);
			else if (raw[i] > median) right.add(raw[i]);
		}
		if (k > left.size() && k < raw.length - right.size())
			return median;
		else if (k < left.size()) {
			int[] leftArray = new int[left.size()];
			for (int i = 0; i < left.size(); i++) {
				leftArray[i] = left.get(i);
			}
			return findMedian(leftArray, k);
		}
		else {
			int[] rightArray = new int[right.size()];
			for (int i = 0; i < right.size(); i++) {
				rightArray[i] = right.get(i);
			}
			return findMedian(rightArray, k - left.size());
		}
	}

	public static void main(String[] args) {
		int[] input = new int[] {1, 2, 3, 4, 4, 4, 5};
		LinearMedian test = new LinearMedian();
		System.out.println(test.findMedian(input, input.length / 2));
	}
}
