package leetcode.medium;

import java.util.Arrays;

/*
 * You have an array of logs.  Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs. 
 * It is guaranteed that each log has at least one word after its identifier.
 * Reorder the logs so that all of the letter-logs come before any digit-log.  
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used
 * in case of ties.  The digit-logs should be put in their original order.
 * Return the final order of the logs.
 * 
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * Constraints:
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderDataInLogFiles {
    public static String[] reorderLogFiles(String[] logs) {
    	String [] res=new String[logs.length];
    	Arrays.fill(res, "");
        for(int i=0; i<logs.length; i++) {
        	int temp=-1;
        	for(int j=0; j<logs.length; j++) {
        		String str=logs[j];
        		if(str.equals("")) continue;
        		if(str.charAt(str.indexOf(' ')+1)>='0'&& str.charAt(str.indexOf(' ')+1)<='9' ) continue;
        		res[i]=(res[i].equals(""))?logs[j]:res[i];
        		if(logs[j].substring(logs[j].indexOf(' ')+1).compareTo(res[i].substring(res[i].indexOf(' ')+1))<=0) {
        			res[i]=logs[j];
        			temp=j;
        		}        		
        	}  
        	if(temp!=-1)logs[temp]="";
        }
        for(int i=0;i<logs.length;i++) {
        	for(int j=0;j<logs.length;j++) {
        		if(!res[i].isBlank()||logs[j].isBlank()) continue;
        		res[i]=logs[j]; 
        		logs[j]="";
        		break;
        	}
        }
        return res;
    }
    /*
     * Solution from leetcode
     *  sort function, return 1 if para1 is bigger than para2, return 0 if para1==para2, return -1 if para1<para2
     */
    public String[] reorderLogFilesI(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
    public static void main(String args[]) {
    	String [] logs={"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
    	reorderLogFiles(logs);
    }
}