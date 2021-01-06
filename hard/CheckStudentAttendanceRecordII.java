package leetcode.hard;
/*
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.
 */
/*
 * 1 1 -- all P  done
 * 2 n  1 A  done
 * 2 n*(n-1) 1 A 1 L  done
 * 0 n*(n-1)(n-1)/2 1 A 2 L  done
 * 2 n  1 L 1 done
 * 1 n*(n-1)/2 2 L done
 */

/*
 *  2A, (n*(n-1)/2)*3*3     54
 *  3L, n*(n-1)(n-2)/3!		4*3*2/6=4
 *  
 */
public class CheckStudentAttendanceRecordII {
	/*
	 * If L is not continuous, checkRecordII would work
	 */
    public static int checkRecordII(int n) {
    	long res=1;
    	if(n==1) return 3;
    	int mod=1000000007;
    	res+=(2*n)%mod;
    	res=(res+(n*(n-1))%mod)%mod;
    	res=(res+(n*(n-1)/2)%mod)%mod;
    	res=(res+(n*(n-1)*(n-2)/2)%mod)%mod;
    	return (int)res;        
    }
    /*
     * P[i]: for string of length i+1, number of rewarded strings end with P, 
     * L[i]: for string of length i+1, number of rewarded strings end with L,
     * A[i]: for string of length i+1, number of rewarded strings end with A. 
     *
     * P1[i]: for string of length i+1, number of rewarded strings end with P, without any A in prefix
     * L1[i]: for string of length i+1, number of rewarded strings end with L, without any A in prefix
     * 
     * P[i]=A[i-1]+L[i-1]+P[i-1]
     * L[i]=A[i-1]+P[i-1]+A[i-2]+P[i-2] //No continuous 2 Ls
     * 
     * A[i]=P1[i-1]+L1[i-1]
     * P1[i]=P1[i-1]+L1[i-1]  ====> A[i]=A[i-1]+A[i-2]+A[i-3]
     * L1[i]=P1[i-1]+P1[i-2]
     */
    public static int checkRecord(int n) {
    	int mod=1000000007;
    	int [] P=new int[n], L=new int[n], A=new int[n];
    	if(n==1) return 3;
    	P[0]=1; 
    	L[0]=1; L[1]=3;
    	A[0]=1; A[1]=2; A[2]=4;
    	for(int i=1; i<n; i++) {
    		P[i]=((A[i-1]+L[i-1])%mod+P[i-1])%mod;
    		if(i>1) {
    			L[i]=((A[i-1]+P[i-1])%mod+(A[i-2]+P[i-2])%mod)%mod;
    		}
    		if(i>2) {
    			A[i]=((A[i-1]+A[i-2])%mod+A[i-3])%mod;
    		}   			
    	}
    	System.out.println("A:"+A[n-1]+" P:"+P[n-1]+" L:"+L[n-1]);
    	return ((A[n-1]+P[n-1])%mod+L[n-1])%mod;
    }
    public static void main(String args[]) {
    	System.out.println(checkRecord(100));
    }
}
