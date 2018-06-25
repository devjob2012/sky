

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public static void main(String[] args) {

		List<Integer> ls = Arrays.asList(1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 6, 2, 1, 8);
		List<Integer> ls1 = Arrays.asList(53, 800, 0, 0, 0, 356, 8988, 1, 1);
		// List<Integer> ls1 = Arrays.asList(0,1,1,-1,0,-1,1,0);
		// List<Integer> ls1 = Arrays.asList(1, 1, 1, 6, 2, 2, 1, 3, 2, 2, 6, 2, 1, 8);
		// List<Integer> ls1 = Arrays.asList(9, 8, 9, 8, 7, 6, 7, 6);

		// System.out.println(ls);
		// System.out.println(maxCommonArraySlice(ls));
		// System.out.println(maxCommonArraySlice1(ls));
		System.out.println(maxCommonArraySlice1(ls));
	}

	public static int maxCommonArraySlice1(List<Integer> inputList) {
		int arraySlice = 0;
		int startCounter = 0;
		List<Integer> twoNums = new ArrayList<>();
		if (inputList.size() <= 2) {
			return inputList.size();
		} else {
			while (startCounter < inputList.size()) {
				twoNums.add(inputList.get(startCounter));
				for (int j = startCounter; j < inputList.size(); j++) {
					Integer entry = inputList.get(j);
					if (twoNums.contains(entry)) {
						arraySlice = Math.max(arraySlice, j - startCounter + 1);
						if (j == inputList.size() - 1) {
							startCounter = inputList.size();
							break;
						}
					} else {
						if (twoNums.size() < 2) {
							twoNums.add(entry);
							arraySlice = Math.max(arraySlice, j - startCounter + 1);
							if (j == inputList.size() - 1) {
								startCounter = inputList.size();
								break;
							}
						} else {
							for (int p = j - 1; p >= 0; p--) {
								if (inputList.get(p) != inputList.get(j - 1)) {
									startCounter = p + 1;
									break;
								}
							}
							twoNums.clear();
							break;
						}
					}
				}
			}
		}

		return arraySlice;
	}

}
