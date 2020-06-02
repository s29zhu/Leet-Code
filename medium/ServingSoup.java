package leetcode.medium;
/*
 * There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

Serve 100 ml of soup A and 0 ml of soup B
Serve 75 ml of soup A and 25 ml of soup B
Serve 50 ml of soup A and 50 ml of soup B
Serve 25 ml of soup A and 75 ml of soup B
When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.  

Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.

 

Example:
Input: N = 50
Output: 0.625
Explanation: 
If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.

 */
public class ServingSoup {
	static double  [][] matrix=new double[200][200];
    public static double soupServings(int N) {
        double res=0;
        if(N>=4800) return 1.0; // the probability will tend to be 1 
        res=helper((N+24)/25,(N+24)/25);
        return res;
    }
    public static double helper(int A, int B) {
    	if(A<=0 && B<=0) return 0.5;
    	if(A<=0) return 1;
    	if(B<=0) return 0;
    	if(matrix[A][B]>0) return matrix[A][B];
    	matrix[A][B]=0.25*(helper(A-4,B)+helper(A-3,B-1)+helper(A-2,B-2)+helper(A-1,B-3));
    	return matrix[A][B];
    }
	/***************************************************************/
    public static double soupServingsI(int N) {
        double res=0;
        res=helper(N,N);
        return res;
    }
      
    //brutal force, time limit exceed
    public static double helperI(int A, int B) {
    	double res=0;
    	if(A<=0 && B>0)res=1;
    	else if(A<=0 && B<=0) res=0.5;
    	else if(A>0 && B>0) {
    		res=0.25*(helper(A-100,B)+helper(A-75,B-25)+helper(A-50,B-50)+helper(A-25,B-75));
    	}
    	else res=0;
    	return res;
    }
    public static void main(String args[]) {
    	System.out.println(soupServings(4700));
    }
}
