package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
 */
/*
 * Analysis:
 * Dynamic Programming 
 * dp[2][s.length()] to store the till index i, 
 * dp[0][i] is the max start of the range
 * dp[1][i] is potentially the new max start of the range
 */
public class MinimumWindowSubsequence { 
	//Solution from Leetcode
    public static String minWindow(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        int[][] nxt = new int[N][26];
        Arrays.fill(last, -1);

        for (int i = N - 1; i >= 0; --i) {
            last[S.charAt(i) - 'a'] = i;
            for (int k = 0; k < 26; ++k) {
                nxt[i][k] = last[k];
            }
        }

        List<int[]> windows = new ArrayList();
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == T.charAt(0))
                windows.add(new int[]{i, i});
        }
        for (int j = 1; j < T.length(); ++j) {
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window: windows) {
                if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {
                    window[1] = nxt[window[1]+1][letterIndex];
                }
                else {
                    window[0] = window[1] = -1;
                    break;
                }
            }
        }

        int[] ans = {-1, S.length()};
        for (int[] window: windows) {
            if (window[0] == -1) break;
            if (window[1] - window[0] < ans[1] - ans[0]) {
                ans = window;
            }

        }
        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }
    //time limit exceeds
    public static String minWindowI(String S, String T) {
        String res="", sub="";
        int l=S.length()+1;
        String start=String.valueOf(T.charAt(0));
        if(S.indexOf(start)>=0)sub=S.substring(S.indexOf(start));
        int index=findSubsequence(sub,T);
       	while(index!=-2) 
       	{
       		if(index<l-1) {res=sub.substring(0,index+1);l=index+1;}
       		if(sub.length()>=1) sub=sub.substring(1);
       		if(sub.indexOf(start)!=-1) {
       			sub=sub.substring(sub.indexOf(start));
       		}
       		index=findSubsequence(sub,T);
       	}
        return res;
    }
    
    public static int findSubsequence(String a, String b) {
    	int res=-1;
    	if(a.length()<b.length()) return -2;
    	if(a.equals(b)) return a.length()-1;
    	if(b.contentEquals("")) return -1;
    	while(b.length()!=0 && a.length()>=b.length()) {
    		if(a.charAt(0)==b.charAt(0)) b=b.substring(1);
			res++;
			a=a.substring(1);
    	}
    	if(a.length()<b.length()) res=-2;
		return res;
    }
    public static void main(String args[]) {
    	String S = "bddebcde";
    	String T="bde";
    	//String test="jlptqxswtezmxwssasjksgtifuvwsjsgvkbmkjqybbmqmnbtowtopobimaqqlcyqgvvidzzsegoituxznknjmkcmyqnmpjuhhjptvzvvkrvcxgmkkiearftyehscvlqvgzvhazymoihoykefsxpfbdfdaijeixulztrhfcfcnvbrxjbcqesfbmjhwekvfbdzkmllhaqbriipaqrflircskwbzideewmudctatullbjayrifdivustyczkqmxrorvhstciclscukvfyvoqularvfbyfvgzzvwhskvsbstsezfcfvbozmrirefnckgpllfynkukywieqpkhmdkczhkzsuenkedxempawuldsvovarkgvbwjyynyohswbkelnwvzxszqfgyofqemvfmurfumtgqcumegndsrmycthtjkjouwvgbxiobwpfippmvszxdxwctdwoghpmksnmwrfhzafowyhcfqkagabhkpiskvesibfagguuublwbdamxznvkcmduqafesgxblevdtcdpooijjcmylseqtsaksnfwjvyqwmxriinbinndzzbnvihdvofeaadwvhcqtkwnlzhdjkpqbixpbnqhtfdngtazwnxuhfeesafphvzlyqdhpcvswokftxxwysbroanqgpkztmjmgsidjkxswxrdgjypwycsutygdpyyurwsullyfmcmerlolrjgnphfoizossvuxoiensfickdtynkdhrwpkuepifxslthbpqjeejoilrtlzcqedzngnjebvnjdmncxadysrhmjtnngcirmoqzjhubfstuogccrrefoyihyguznhmpbyjjabonxbolijzzahsjcxvvrmygbuykfotaztbnuvpzcmfgwvdqigeqekiixomchndcbegcvvgtzhfodwddnelfmhqaqjkdidemcnlzxwkbsajtisgayfnnckxojexrljpebaffyfdqekkbkdqcxamhiukbjsykphkwjxkbqdsxkkskqqojglqlxyrfhxozumwzplexuzrptxcuvrxmofspejtcsojgsupnofovrwrbxgjzfkxtshggqiwribzssvbciytaneomptohytmjzffvcfgidhvbgyokmoediwajzoamxjkgbpointuxyrrrkybgzvgaqbvnpcauwfwayrzvheiggyklzinhwbksdvgvpwnhskngzzaavwudwtnslpztrjcyvcaetixwxgowgrayijcyzafgrlpxphcuscxevahiiwbcbbpljaevtvaaeqqxdohqxjczjosgeeplgiwnjtpjnuuetvurwfyygrvuybnjrhsgvzwxmulwyheibxwgrzpmnwdohxfwgrehkozvovurlkmxedfzhrplabtgeojykjesuujxbzbefezlzjkguncqybcefmmuqmiqznbbojmehvelqqoofkirmagmsrxyqaxbeeqgghsaslfqpwmtrqcmgsygcqxcfrcuusxuqogvraxyxlkukkqezhzsxwnkfnycpmqumftpvtpheiwiuskxynkyrxfawayvuqrkecjoiyuerkxbjivrkkqeokjfsftnckqernpegjsifpbkqlwfhpztthovhyxkpfhbmydesmnnyxxikpwtxthpsmoefyjonnjhojirxtrjnaoobtomzxzujcwrykrpzgdslrxbauluzcbbbmtmhkspmuehdnhdeptiwwtypquiznozhtnglbyrbbelzbvihkpumcryzmrbjagsntozlombiahvdgtmehffomccrevlvqulivxaoqdacjpvwodmnuopfeldscrzoxpgmwodzfonfpujmoubqfvvl";
    	//System.out.println(findSubsequence(S,T));
    	System.out.println(minWindow(S,T));
    	//System.out.println(test.length());
    }
}
