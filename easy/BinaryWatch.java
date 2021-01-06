package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/*
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
    public static List<String> readBinaryWatch(int num) {
        List<String> list=new ArrayList<String>();
        for(int i=0;i<=num;i++) {
            List<String> h_list= new ArrayList<String>(), 
            		m_list=new ArrayList<String>();
            helper(4,i).forEach(item->
            {
            	if(item<12) h_list.add(item.toString());
            });
            helper(6,num-i).forEach(item->{
            	if(item<10) m_list.add("0"+item.toString());
            	else if(item<60) m_list.add(item.toString());
            });
            h_list.forEach(h_item->{
            	m_list.forEach(m_item->{
            		list.add(h_item+":"+m_item);
            	});
            });
        }
        return list;
    }
    public static  List<Integer> helper(int nled, int n){
    	List<Integer> list= new ArrayList<Integer>();
    	List<Integer> res= new ArrayList<Integer>();
    	if(nled<n) return res;
    	if(n==0) {res.add(0);}
    	else if(n==1) {
    		for(int i=0; i<nled; i++) {
    			res.add(1<<i);
    		}
    	}else if(n>1){
    		int temp=1<<(nled-1);
    		list=helper(nled-1,n-1);
    		list.forEach(item->res.add(temp+item));
    		res.addAll(list=helper(nled-1,n));
    	}
    	return res;
    }
    
    public static List<String> readBinaryWatchII(int num) {
    	List<String> list=new ArrayList<String>();
    	for(int h=0;h<12;h++) {
    		for(int m=0;m<60;m++) {
    			int h_count=0, m_count=0;
    			for(int i=0; i<4; i++) {
    				if(((1<<i)&h) != 0) h_count++;
    			}
    			for(int i=0; i<6; i++) {
    				if(((1<<i)&m) != 0) m_count++;
    			}
    			if((h_count+m_count) == num && (m>=10))
    				list.add(String.valueOf(h)+":"+String.valueOf(m));
    			else if((h_count+m_count) == num && (m<10))
    				list.add(String.valueOf(h)+":"+"0"+String.valueOf(m));
    		}
    	}
    	return list;
    }
	public static void main(String args[]) {
		System.out.println(readBinaryWatchII(1));
	}
}
