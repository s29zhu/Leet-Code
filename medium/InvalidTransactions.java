package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.

Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.

 

Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]
 

Constraints:

transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.
 */
public class InvalidTransactions {
    public static List<String> invalidTransactions(String[] transactions) {
        List<String> list=new ArrayList<String>();	
        int l=transactions.length;
        String [][]ts=new String[l][4];
        for(int i=0; i<l;i++) {
        	String str=transactions[i];
        	int c=0, index=0;
        	while(!str.isEmpty()) {
        		index=str.indexOf(',');
        		if(index<0) {
        			ts[i][c]=str;
        			break;
        		}
            	ts[i][c]=str.substring(0,index);
            	str=str.substring(index+1);
            	c++;
        	}
        }
		Arrays.parallelSort(ts, (String []s1,String []s2)->{
			int res=s1[0].compareTo(s2[0]);
			int c=1;
			while(res==0) {
				if(c==1 || c==2) res=Integer.valueOf(s1[c])-Integer.valueOf(s2[c]);
				else res=s1[c].compareTo(s2[c]);
				if(res!=0 || c==3) return res;
				c++;
			}
			return res;
		});
		int new_name_index=0;
		String str="";
		for(int i=0;i<l;i++) {
			if(i==0 && Integer.valueOf(ts[i][2])>1000) {
				str=ts[i][0]+","+ts[i][1]+","+ts[i][2]+","+ts[i][3];
				list.add(str);
			}
			for(int j=new_name_index; j<i; j++) {
				if(!ts[i][0].equals(ts[j][0])) { new_name_index=i; j=i;}
				//amount > 1000
				if(Integer.valueOf(ts[i][2])>1000) {
					str=ts[i][0]+","+ts[i][1]+","+ts[i][2]+","+ts[i][3];
					if(!list.contains(str))list.add(str);
				}
				//name is the same, time within 60 mins and in different city
				if(ts[i][0].equals(ts[j][0])
						&& Integer.valueOf(ts[i][1])-Integer.valueOf(ts[j][1])<=60
						&& !ts[i][3].equals(ts[j][3])) {
					str=ts[i][0]+","+ts[i][1]+","+ts[i][2]+","+ts[i][3];
					if(!list.contains(str)) list.add(str);
					str=ts[j][0]+","+ts[j][1]+","+ts[j][2]+","+ts[j][3];
					if(!list.contains(str)) list.add(str);
				}					
			}
		}
        return list;
    }
    public static void main(String args[]) {
    	String []transactions={"alex,262,537,barcelona","lee,361,1114,beijing","alex,44,1186,barcelona","xnova,152,1310,budapest","bob,155,395,barcelona","chalicefy,815,748,bangkok","bob,917,614,amsterdam","chalicefy,211,919,beijing","lee,996,207,bangkok","chalicefy,424,634,bangkok"};
    	invalidTransactions(transactions);
    }
}
