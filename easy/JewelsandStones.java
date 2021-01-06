package leetcode.easy;
import java.util.HashSet;
import java.lang.Character;

public class JewelsandStones {
	 /*
	  You're given strings J representing the types of stones that are jewels,
	  and S representing the stones you have.
	  Each character in S is a type of stone you have.
	  You want to know how many of the stones you have are also jewels.

	The letters in J are guaranteed distinct,
	and all characters in J and S are letters.
	 Letters are case sensitive,
	 so "a" is considered a different type of stone from "A".
	  */
	  //Solution: create a Hashset and put J char into the hashset
	  public static int count_jewel(String J, String S){
	    int count=0;
	    HashSet<Character> jewels = new HashSet<Character>();
	    for (Character c: J.toCharArray())
	      jewels.add(c);
	    for(Character c: S.toCharArray())
	    {
	      if(jewels.contains(c))
	        count++;
	    }
	    return count;
	  }
	  public static void main(String[] args) {
	    String J=new String("ABaGHG");
	    String S=new String("ABBGHHefsfF");
	    int count=count_jewel(J,S);
	    System.out.print(count);
	  }
}
