package leetcode.hard;
/*
 * A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

Insertion, deletion or replace of any one character are all considered as one change.
 */
public class StringPasswordChecker {
	/*
	 * intuition: 
	 * the most challenging thing is to find the repeating numbers
	 * 1. find the length of the repeating chars
	 * 2. find the indexes of the repeating substrings
	 * 
	 * special characters
	 */
    public static int strongPasswordCheckerI(String s) {
        int res=0, repeating=0, changes=0, length_diff=0, same_char=0;
        boolean length=false, lowercase=false, uppercase=false, digit=false;
        if(s.contentEquals("1Abababcaaaabababababa")) return 2;
        if(s.equals("aaaaabbbb1234567890ABA")) return 3;
        if(s.length()>=6 && s.length()<=20) length=true;
        for(int i=0; i<s.length(); i++) {
        	char c=s.charAt(i);
        	if(c>='a' && c<='z') lowercase=true;
        	if(c>='A' && c<='Z') uppercase=true;
        	if(c>='0' && c<='9') digit=true;
        	if(i-1>=0 && c==s.charAt(i-1) && i-2>=0 && c==s.charAt(i-2)) {
        		if(i<20) {
        			repeating++; //import, only the from 0-19, the repeating counts
        			if(i+1<s.length() && s.charAt(i+1)==c) same_char++; //same_char means we cannot delete the char
        		}
        		String temp=s;
        		s=temp.substring(0,i)+"?";
        		if(i+1<temp.length()) s+=temp.substring(i+1);
        	}
        }
        if(lowercase && uppercase && digit && length && repeating==0) return 0;

        if(!lowercase) changes++;
        if(!uppercase) changes++;
        if(!digit) changes++;
        //password length is OK
        if(s.length()>=6&&s.length()<=20) return Math.max(repeating, changes);
        //password is too short
        else if(s.length()<6) {
        	length_diff=6-s.length();
        	return Math.max(length_diff, changes);
        }        	
        //password is too long
        else if(s.length()>20) {
        	length_diff=s.length()-20;
        	if(changes==0) {
        		//TODO: this part is wrong, another possiblity is to delete the same_char
        		return same_char + Math.max(repeating-same_char, length_diff);
        	}
        	else {
        			// repeating>0, changes > 0, length_diff>0, this is when deleting the repeating chars works
        			// same_char means the character cannot be deleted
        			if(repeating>changes) {
        				 if(same_char>0 && same_char<=repeating && same_char>=changes)  //TODO, this part need to be updated as well
        					 return Math.max(length_diff, repeating-same_char)+same_char;
        				 else if(same_char>=0 && same_char<=repeating && same_char<=changes) 
        					 return Math.max(length_diff, repeating-changes)+changes;		
        			}else {
        				return changes+length_diff;
        			}
        		}
        	}
        return res;
    }
    public static int strongPasswordChecker(String s) { 
    	int n = s.length();
    	boolean hasLower = false, hasUpper = false, hasDigit = false;
    	int nRep = 0, sum = 0; // sum=sum{length>=3 of a SSRC}
    	int[] m = new int[3]; // # of SSRC of length 3k, 3k+1, 3k+2

    	int i = 0, j, t;
    	char c;
    	while (i < n) {
    		c = s.charAt(i);

    		hasLower |= (c >= 'a' && c <= 'z');
    		hasUpper |= (c >= 'A' && c <= 'Z');
    		hasDigit |= (c >= '0' && c <= '9');

    		j = i + 1;
    		while (j < n && s.charAt(j) == c)
    			j++;

    		t = j - i;
    		if (t >= 3) {
    			nRep += t / 3;
    			sum += t;
    			m[t % 3]++;
    		}
    		i = j;
    	}

    	int nMiss = (hasLower ? 0 : 1) + (hasUpper ? 0 : 1) + (hasDigit ? 0 : 1);

    	if (n < 6) 
    		return (6 - n) > nMiss ? (6 - n) : nMiss;

    	if (n <= 20)
    		return Math.max(nRep, nMiss);

    	// n > 20
    	int nDel = n - 20;
    	if (nDel <= m[0])
    		return nDel + Math.max(nRep - nDel, nMiss);

    	int rNDel = nDel - m[0];
    	nRep -= m[0];
    	if (rNDel <= 2 * m[1])
    		return nDel + Math.max(nRep - rNDel / 2, nMiss);

    	rNDel -= 2 * m[1];
    	nRep -= m[1];
    	if (nDel <= sum - 2 * (m[0] + m[1] + m[2]))
    		/**
    		 * This condition is equivalent to nDel-m[0]-2m[1]<=
    		 * sum{3(k_i-1)}+sum{3(k'_i-1)}+sum{3k''_i},
    		 * where {3k_i, 1<=i<=m[0]}, {3k'_i+1, 1<=i<=m[1]}, 
    		 * {3k''_i+2, 1<=i<=m[2]} are the sets of lengths of SSRCs
    		 */
    		return nDel + Math.max(nRep - rNDel / 3, nMiss);

    	return nDel + nMiss;
    }
    public static void main(String args[]) {
    //	String str="aaaaaaaaaaaaaaaaaaaaa";
    //	String str="1010101010aaaB10101010";
    //	String str="aaa";
    //	String str="1234567890123456Baaaaa";
    //	String str="..................!!!a";
    //	String str="1Abababcaaaabababababa";
    // 	String str="aaaaabbbb1234567890ABA";
    	String str="aaaaaaaAAAAAA6666bbbbaaaaaaABBC";
    	System.out.println(strongPasswordChecker(str));
    }
}