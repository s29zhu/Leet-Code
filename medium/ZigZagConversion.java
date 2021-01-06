package leetcode.medium;

/*
 *   
 *    n=3
 *        0     4     8   K*2(n-1)=0 
 *        1  3  5  7  9   K*2(n-1)-i   
 *        2     6         (2K-1)*(n-1)
 */
public class ZigZagConversion {
	 public static String convert(String s, int numRows) {
		 String res="";
		 if(numRows==1) return s;
		 int l=s.length();
		 for(int i=0; i<numRows; i++) {
			 for(int j=0; j+i<l; j=j+2*(numRows-1)) {
				 res=res.concat(s.substring(j+i,j+i+1));
				 if(i!=0 && i!=(numRows-1) && (j+2*(numRows-1)-i)<l) {
					res=res.concat(s.substring(j+2*(numRows-1)-i,j+2*(numRows-1)-i+1));
				 }			
			 }
		 }
		 return res;
	 }
	 
	public static void main(String[] args) {
		String s="0123456789";
		System.out.print(convert(s,3));
	}
}