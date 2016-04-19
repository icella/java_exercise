package exercise.com.leo.base.math;

import java.util.List;

import com.google.common.collect.Lists;

public class Combinations {
    public static void main(String[] args){
    	/*String[] str = {"a" , "b" ,"c"};
        int n = str.length;                                  //元素个数。
        //求出位图全组合的结果个数：2^n
        int nbit = 1<<n;                                     // “<<” 表示 左移:各二进位全部左移若干位，高位丢弃，低位补0。:即求出2^n=2Bit。
        System.out.println("全组合结果个数为："+nbit);
        
        for(int i=0 ;i<nbit ; i++) {                        //结果有nbit个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n。
            System.out.print("组合数值  "+i + " 对应编码为： ");
            for(int j=0; j<n ; j++) {                        //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
                int tmp = 1<<j ;        
                if((tmp & i)!=0) {                            //& 表示与。两个位都为1时，结果才为1
                    System.out.print(str[j]);
                }
            }
            System.out.println();
        }*/
    	
    	List<ContactInfo> contacts = Lists.newArrayList();
        contacts.add(new ContactInfo().setPhone("a").setImei("354273059932330").setImsi("460010085093067"));
        contacts.add(new ContactInfo().setPhone("b").setImei("866962023554722").setImsi("460021739187681"));
        contacts.add(new ContactInfo().setPhone("c").setImei("A00000554138D8").setImsi("460030767116888"));
        contacts.add(new ContactInfo().setPhone("d").setImei("862802029413925").setImsi("460019708510470"));
        contacts.add(new ContactInfo().setPhone("e").setImei("358180052735097").setImsi("460016682315269"));
//    	getContacts(contacts);
    	
    	List<List<ContactInfo>> temp2 = getContacts2(contacts, 1);
    	for (List<ContactInfo> list : temp2) {
			for (ContactInfo contactInfo : list) {
				System.out.print(contactInfo);
			}
			System.out.println();
		}
    }
    
    static void getContacts(List<ContactInfo> contacts){
        
        int n = contacts.size();
        int nbit = 1<<n;
        
        System.out.println("全组合结果个数为：" + nbit);
        for (int i = 0; i < nbit; i++) {
        	 System.out.print("组合数值  "+i + " 对应编码为： ");
        	 for (int j = 0; j < n; j++) {
				int tmp = 1<<j;
				if((tmp & i) != 0){
					System.out.print(contacts.get(j));
				}
			}
        	 
        	System.out.println();
		}
    }
    
    static List<List<ContactInfo>> getContacts2(List<ContactInfo> contacts, int k){
    	List<List<ContactInfo>> result = Lists.newArrayList();
    	
        int n = contacts.size();
        int nbit = 1<<n;
        
        for (int i = 0; i < nbit; i++) {
        	 List<ContactInfo> tmpList =Lists.newArrayList();
        	 for (int j = 0; j < n; j++) {
				int tmp = 1<<j;
				if((tmp & i) != 0){
					ContactInfo tempData = contacts.get(j);
					ContactInfo info = new ContactInfo();
					info.setPhone(tempData.getPhone()).setImsi(tempData.getImsi()).setImei(tempData.getImei());
					tmpList.add(info);
				}
			}
        	
        	 if(tmpList.size() == k){
        		 result.add(tmpList);
        	 }
		}
        
        return result;
    }
}
