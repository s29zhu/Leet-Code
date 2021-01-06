package leetcode.medium;

import java.util.*;

public class SumFraction {
	//greated common factor
	public static int GCF(int a, int b) {
		int res=1;
		int min=Math.min(a, b);
		int max=Math.max(a, b);
		for(int i=1; i<=min; i++) {
			if(min%i==0 && max%i==0 && i>res) res=i;
		}
		return res;
	}
	// Least Common Multiplier
	public static int LCM(int []Y) {
		int res=1;
		for(int y: Y) {
			int gcf=GCF(res,y);
			res=res*y/gcf;
		}
		return res;
	}
	
    public static int solution(int[] X, int[] Y) {
    	int res=0;
    	int modulo=(int)Math.pow(10, 9)+7;
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	//first find the LCM of array Y
    	int lcm=LCM(Y);
        for(int i=0; i<X.length; ++i) {
            X[i] *= lcm/Y[i];
            res += map.getOrDefault(lcm - X[i], 0);
            res %= modulo;
            map.put(X[i], map.getOrDefault(X[i], 0)+1);
        }
    	return res;
    }
    
    public static void main(String args[]) {
    	int []X= {1,1,1,2,2};
    	int []Y= {3,3,2,3,3};
    	System.out.print(solution(X,Y));
    }
}
