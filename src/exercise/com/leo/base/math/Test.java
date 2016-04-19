package exercise.com.leo.base.math;

import java.util.List;

import com.google.common.collect.Lists;

public class Test {
	public static class Permutation {
		List<List<ContactInfo>> datas = Lists.newArrayList();
		
		public List<List<ContactInfo>> getConbinations(List<ContactInfo> contacts) {
			return getCombination(contacts, contacts.size(), 2);
		}

		List<List<ContactInfo>> getCombination(List<ContactInfo> list, int n, int r) {
			List<ContactInfo> data = Lists.newArrayList();
			for (int i = 0; i < r; i++) {
				data.add(new ContactInfo());
			}

			combinationUtil(list, n, r, 0, data, 0);

			return datas;
		}

		void combinationUtil(List<ContactInfo> list, int n, int r, int index, List<ContactInfo> data, int i) {
			if (index == r) {
				List<ContactInfo> tempList = Lists.newArrayList();
				for (int j = 0; j < r; j++) {
					ContactInfo info = new ContactInfo();
					info.setPhone(data.get(j).getPhone()).setImsi(data.get(j).getImsi()).setImei(data.get(j).getImei());
					tempList.add(info);
					System.out.print(info + " ");
				}
				datas.add(tempList);
				System.out.println("");

				return;
			}

			if (i >= n)
				return;

			data.get(index).setPhone(list.get(i).getPhone());
			
			combinationUtil(list, n, r, index + 1, data, i + 1);
			combinationUtil(list, n, r, index, data, i + 1);
		}
	}

	public static void main(String[] args) {
		Permutation permutation = new Permutation();

		List<ContactInfo> contacts = Lists.newArrayList();
		contacts.add(new ContactInfo().setPhone("a").setImei("354273059932330").setImsi("460010085093067"));
		contacts.add(new ContactInfo().setPhone("b").setImei("866962023554722").setImsi("460021739187681"));
		contacts.add(new ContactInfo().setPhone("c").setImei("A00000554138D8").setImsi("460030767116888"));
		contacts.add(new ContactInfo().setPhone("d").setImei("862802029413925").setImsi("460019708510470"));
		contacts.add(new ContactInfo().setPhone("e").setImei("358180052735097").setImsi("460016682315269"));

		List<List<ContactInfo>> datas = permutation.getConbinations(contacts);
		System.out.println(datas.size());
		for (List<ContactInfo> ele : datas) {
			for (ContactInfo info : ele) {
				System.out.print(info.getPhone() + " ");
			}

			System.out.println();
		}
	}
}
