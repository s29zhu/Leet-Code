package leetcode.easy;

public class LongPressedNmae {
    public static boolean isLongPressedName(String name, String typed) {
        //alex , balex return false
        //alex, alexb return false
        //alex, alexx return true
        //alex, alexa false
        //alexx, alex false
        int n=name.length();
        int t=typed.length();
        if(t<n) return false;
        char pre=' ';
        int i=0, j=0;
        for(i=0, j=0; i<n && j<t; ){
            if(name.charAt(i)==typed.charAt(j)) {
                i++;
                j++;
                pre=name.charAt(i);
            }else if(typed.charAt(j)==pre ) j++;
            else if(name.charAt(i)!=typed.charAt(j)) return false;
        }
        while(i==n && j<t){
            if(pre==typed.charAt(j)) j++;
            else return false;
        }
        if(i<n && j==t) return false;
        return true;
    }
    public static void main(String args[]) {
    	System.out.print(isLongPressedName("alex","aaleex"));
    }
}
