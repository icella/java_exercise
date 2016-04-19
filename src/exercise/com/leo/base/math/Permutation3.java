package exercise.com.leo.base.math;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * print all combination of size r in an array of size n. Given an array of size
 * n, generate and print all possible combinations of r elements in array. For
 * example, if input array is {1, 2, 3, 4} and r is 2, then output should be {1,
 * 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.
 */
public class Permutation3 {
	List<ContactInfo> datas = Lists.newArrayList();
	public static void main(String[] args) {
		List<ContactInfo> contacts = Lists.newArrayList();
        contacts.add(new ContactInfo().setPhone("a").setImei("354273059932330").setImsi("460010085093067"));
        contacts.add(new ContactInfo().setPhone("b").setImei("866962023554722").setImsi("460021739187681"));
        contacts.add(new ContactInfo().setPhone("c").setImei("A00000554138D8").setImsi("460030767116888"));
        contacts.add(new ContactInfo().setPhone("d").setImei("862802029413925").setImsi("460019708510470"));
        contacts.add(new ContactInfo().setPhone("e").setImei("358180052735097").setImsi("460016682315269"));
		
        printCombination(contacts, contacts.size(), 2);
	}

	static void combinationUtil(List<ContactInfo> list, List<ContactInfo> data, int start, int end, int index, int r) {
		// Current combination is ready to be printed, print it
		if (index == r) {
			for (int j = 0; j < r; j++)
				System.out.print(data.get(j) + " ");
			System.out.println("");
			return;
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			ContactInfo info = list.get(i);
			data.get(index).setPhone(info.getPhone()).setImsi(info.getImsi()).setImei(info.getImei());
			combinationUtil(list, data, i + 1, end, index + 1, r);
		}
	}

	// The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(List<ContactInfo> list, int n, int r)
    {
        // A temporary array to store all combination one by one
        List<ContactInfo> data = Lists.newArrayList();
        for (int i = 0; i < r; i++) {
			data.add(new ContactInfo());
		}
 
        // Print all combination using temprary array 'data[]'
        combinationUtil(list, data, 0, n-1, 0, r);
    }
}
