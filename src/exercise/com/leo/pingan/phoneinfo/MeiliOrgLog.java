package exercise.com.leo.pingan.phoneinfo;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 *
 * 申请人(手机号)染黑
 * 申请人(IMEI)染黑, IMEI是指用户输入的IMEI
 * 联系人染黑
 *
 * SELECT DISTINCT 'START', phone, name, mobile_imsi, mobile_imei, bank, query_time, 'END'
 * FROM tb_org_log WHERE app_key = '120160805003' AND query_time >= '2017-04-01 00:00:00';
 *  //Appliant 107824, Applicant and imei 107824, contact 50972
 * Created by lla on 17-4-6.
 */
public class MeiliOrgLog {
    private static final String path = "/home/lla/Documents/test_daily/2017/4/";
    private static final String inputFile = "tdjr.txt";
    private static final String applicantFile = "applicant.txt";
    private static final String applicantAndImeiFile = "applicantAndImei.txt";
    private static final String contactFile = "contact.txt";

    private static final String colSeprator = "\t";


    public static void main(String[] args){
        try {
            List<String> sqlContents = FileUtils.readLines(new File(path + inputFile));

            List<String> applicantList = Lists.newArrayList();
            List<String> applicantAndImeiList = Lists.newArrayList();
            List<String> contactList = Lists.newArrayList();
            for (String line : sqlContents) {
                String[] cols = line.split(colSeprator);

                if(cols.length != 8){
                    continue;
                }

                String phone = cols[1];
                String name = cols[2];
                String mobileImsi = cols[3];
                String mobileImei = cols[4];
                String contact = cols[5];
                String queryTime = cols[6];


                //Applicants
                applicantList.add(phone);

                //Applicants and IMEI
                String colApplicantAndImei = phone + colSeprator + mobileImei;
                applicantAndImeiList.add(colApplicantAndImei);

                //Contacts, IMEI
                String pureContact = contact.replace(",", "");
                if(StringUtils.isNumeric(pureContact)){
                    String[] contactCols = contact.split(",", -1);
                    List<String> contacts = Lists.newArrayList();
                    for (int i = 0; i < contactCols.length; i++) {
                        String iValue = contactCols[i];
                        if(StringUtils.isNotEmpty(iValue)){
                            if(i == 0 || i == 3){
                                contacts.add(iValue);
                            }
                        }
                    }

                    contactList.addAll(contacts);
                }

            }

            System.out.println(String.format("Appliant %s, Applicant and imei %s, contact %s",
                applicantList.size(), applicantAndImeiList.size(), contactList.size()));


            FileUtils.writeLines(new File(path + applicantFile), applicantList);
            FileUtils.writeLines(new File(path + applicantAndImeiFile), applicantAndImeiList);
            FileUtils.writeLines(new File(path + contactFile), contactList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
