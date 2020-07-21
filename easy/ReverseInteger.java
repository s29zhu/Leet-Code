package leetcode.easy;

public class ReverseInteger {
    public static int reverse(int x) {
        int res=0;
        long temp=0l;
        boolean neg=false;
        if(x<0) neg=true;
        temp=(long)Math.abs((long)x);
        StringBuilder str= new StringBuilder();
        str.append(temp);
        str=str.reverse();
        temp=Long.valueOf(str.toString());
        if(temp>Integer.MAX_VALUE) res=0;
        else res=(int)temp;
        if(neg) res=-1*res;
        return res;
    }
    
    public static void main(String args[]) {
    	int x=Integer.MIN_VALUE;
    	System.out.print(reverse(1230));
    }
}
