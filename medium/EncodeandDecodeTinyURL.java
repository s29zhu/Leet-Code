package leetcode.medium;
import java.util.HashMap;
/*
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
*/
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url)); 

public class EncodeandDecodeTinyURL {
		HashMap<String, String> map;
		
		EncodeandDecodeTinyURL(){	
			map=new HashMap<String, String>();
		}
		
	    // Encodes a URL to a shortened URL.
	    public String encode(String longUrl) {
	        String res="http://tinyurl.com/";
	        String temp=new Integer(Math.abs(longUrl.hashCode())).toString();
	       	while(temp.length()<6) temp+="0";
	       	res=temp.substring(0,6);
	        map.put(res, longUrl);
	        return res;
	    }

	    // Decodes a shortened URL to its original URL.
	    public String decode(String shortUrl) {
	        String res="";
	        if(map.containsKey(shortUrl)) res=map.get(shortUrl);
	        else {
	        	System.out.println("Cannot decode the short url.");
	        }
	        return res;
	    }
	    
	    public static void main(String args[]) {
	    	String res="https://leetcode.com/problems/design-tinyurl";
	    	EncodeandDecodeTinyURL url=new EncodeandDecodeTinyURL();
	    	System.out.print(url.encode(res));
	    }

}
