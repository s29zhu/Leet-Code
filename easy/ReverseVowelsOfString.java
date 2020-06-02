package leetcode.easy;

public class ReverseVowelsOfString {
    public static String reverseVowels(String s) {
        StringBuilder str=new StringBuilder(s);
        boolean head=false, end=false;
        for(int i=0,j=s.length()-1;i<=j;) {
        	//a e i o u 
        	char ci=str.charAt(i), cj=str.charAt(j);
        	if(ci=='a' || ci=='e'|| ci=='i' || ci=='o' || ci=='u' || ci=='A' || ci=='E'|| ci=='I' || ci=='O' || ci=='U') {
        		head=true;
        	}
    		if(cj=='a' || cj=='e'|| cj=='i' || cj=='o' || cj=='u' || cj=='A' || cj=='E'|| cj=='I' || cj=='O' || cj=='U') {
    			end=true;
    		}
    		if(head&&end) {
    			str.setCharAt(i, cj);
    			str.setCharAt(j, ci);
    			head=false;
    			end=false;
    			i++;
    			j--;
    		}else if(head&&!end) {
    			j--;
    		}else if(!head&&end) {
    			i++;
    		}else if(!head&&!end) {
    			i++;
    			j--;
    		}
        }
        return str.toString();
    }
	public static void main(String args[]) {
		String s="AaoO";
		System.out.print(reverseVowels(s));
	}
}
