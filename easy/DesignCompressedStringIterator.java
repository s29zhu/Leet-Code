package leetcode.easy;
/*
 * Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
 */
public class DesignCompressedStringIterator {
	int index,next_i;
	String s;
	Character ch;
	int counter;
	
    public DesignCompressedStringIterator(String compressedString) {
        this.s=compressedString;
        this.ch=s.charAt(0);
        this.index=0;
       	int i=index+1;
       	while(i<s.length()&&s.charAt(i)>='0'&&s.charAt(i)<='9') i++;
       	this.counter=Integer.valueOf(s.substring(index+1, i));
       	next_i=i;
    }
    
    public char next() {
    	if(index<this.counter) {index++; return ch;}
    	else {    		
    		if(next_i>=s.length()) return ' ';
    		index=next_i;
    		ch=s.charAt(index);
           	int i=index+1;
           	while(i<s.length()&&s.charAt(i)>='0'&&s.charAt(i)<='9') i++;
           	this.counter=Integer.valueOf(s.substring(index+1,i))+index-1;
           	next_i=i;
    	}
    	return ch;
    }
    
    public boolean hasNext() {
        if(index<this.counter || next_i<s.length()) return true;
        else  return false;

    }
    
    public static void main(String args[]) {
    	DesignCompressedStringIterator test=new DesignCompressedStringIterator("L2E3");
    	System.out.println(test.next());
    	System.out.println(test.next());
    	System.out.println(test.next());
    	System.out.println(test.next());
    	System.out.println(test.next());
    	System.out.println(test.next());
    	System.out.println(test.hasNext());
    	System.out.println(test.next());
    	System.out.println(test.hasNext());
    }
}
