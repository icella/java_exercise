package exercise.com.leo.base.math;

import java.util.List;

import com.google.common.collect.Lists;

public class Permutation2 {

	public static void main(String[] args) {
        List<ContactInfo> contacts = Lists.newArrayList();
        contacts.add(new ContactInfo().setPhone("a").setImei("354273059932330").setImsi("460010085093067"));
        contacts.add(new ContactInfo().setPhone("b").setImei("866962023554722").setImsi("460021739187681"));
        contacts.add(new ContactInfo().setPhone("c").setImei("A00000554138D8").setImsi("460030767116888"));
        contacts.add(new ContactInfo().setPhone("d").setImei("862802029413925").setImsi("460019708510470"));
        contacts.add(new ContactInfo().setPhone("e").setImei("358180052735097").setImsi("460016682315269"));
        
        printCombination(contacts, contacts.size(), 2);
	}

	static void combinationUtil(List<ContactInfo> list, int n, int r, int index, List<ContactInfo> data, int i) {
		// Current combination is ready to be printed, print it
		if (index == r) {
			for (int j = 0; j < r; j++)
				System.out.print(data.get(j) + " ");
			System.out.println("");
			return;
		}

		// When no more elements are there to put in data
		if (i >= n)
			return;

		// current is included, put next at next location
		ContactInfo info = list.get(i);
		data.get(index).setPhone(info.getPhone()).setImei(info.getImei()).setImsi(info.getImsi());
		combinationUtil(list, n, r, index + 1, data, i + 1);

		// current is excluded, replace it with next (Note that
		// i+1 is passed, but index is not changed)
		combinationUtil(list, n, r, index, data, i + 1);
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


    static void printCombination(List<ContactInfo> list, int n, int r)
    {
        // A temporary list to store all combination one by one
        List<ContactInfo> data = Lists.newArrayList();
        for (int i = 0; i < r; i++) {
			data.add(new ContactInfo());
		}
 
        // Print all combination using temprary linked list 'data'
        combinationUtil(list, n, r, 0, data, 0);
        
//        combinationUtil(list, data, 0, n-1, 0, r);
    }
}
