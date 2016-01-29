/**
 * Given a sorted array, remove the duplicates in place such that element appear
 * only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * For example,
 * Given input array nums = [1, 1, 2],
 * Your function should return length = 2, with the first two elements of nums 
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new 
 * length.
 */

package exercise.com.leo.base.leetcode.arrayandstring;

/**
 * Remove duplicates elements in a array.
 * 
 * @author admin
 *
 */
public class RemoveDuplicate {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 2, 3, 4, 4, 5 };
		int[] nums2 = { 1, 2, 2, 3, 4, 4, 5 };
		int[] nums3 = { 1, 2, 2, 3, 4, 4, 5 };
		System.out.println(removeDumlicates1(nums1));
		System.out.println(removeDumlicates2(nums2));
		System.out.println(removeDumlicates3(nums3));
	}

	public static int removeDumlicates1(int[] nums) {
		int i = 1; // iterator thru array
		int j = 0; // current index
		for (; i < nums.length; i++) {
			if (nums[i] != nums[j]) { // new number
				j++; // move current index
				nums[j] = nums[i]; // fill current index with new number
			}
		}
		return j + 1;
	}

	public static int removeDumlicates2(int[] nums) {
		int dupes = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1])
				dupes++;
			nums[i - dupes] = nums[i];
			
		}

		return nums.length - dupes;
	}

	public static int removeDumlicates3(int[] nums) {
		int result = 0;
		for (int j = 0; j < nums.length; j++) {
			int i = nums[j];
			int k = i - 1;
			if (j + 1 < nums.length) {
				k = nums[j + 1];
			}

			if (i == k) {
				for (int k2 = j + 1; k2 < nums.length - 1; k2++) {
					nums[k2] = nums[k2 + 1];
				}
				nums[nums.length - 1] = k;
			}
		}

		for (int i = 0; i < nums.length; i++) {
			int j = nums[i];
			int k = j - 1;
			if (i + 1 < nums.length) {
				k = nums[i + 1];
			}
			if (j < k) {
				result++;
			}
		}

		return result;
	}
}
