package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MostCommonWord {
    public static String mostCommonWord(String paragraph, String[] banned) {
        String res="";
        HashMap<String, Integer> map=new HashMap<String, Integer>();
        List<String> list=new ArrayList<String>();
        list=Arrays.asList(banned);
        String str="";
        int max=0;
        char c=' ';
        paragraph=paragraph.toLowerCase();
        for(int i=0; i<paragraph.length(); i++) {
        	c=paragraph.charAt(i);
        	if(c>'z' || c<'a') paragraph=paragraph.replace(c, ' ');
        }
        
        String temp=new String(paragraph);
        while(temp.length()>0){
            int index=temp.indexOf(' ');
            if(index!=-1) { 
                str=temp.substring(0, index);
                temp=temp.substring(index+1);
            }
            else {
                str=temp;
                temp="";
            }
            if(!str.equals("")&&!str.equals(" ")&&!list.contains(str)) map.put(str, map.getOrDefault(str, 0)+1);                     
        }
        
        for(String item: map.keySet()){
            if(map.get(item)>max) {
                max=map.get(item);
                res=item;
            } 
        }
        return res;
    }
    
    public static void main(String args[]) {
    	String para="bob hit." ;
    	String []banned= {"hit"};
    	//String str="hit.";
    	//System.out.print(str.substring(0, str.length()-1));
    	System.out.print(mostCommonWord(para, banned));
    }
}
