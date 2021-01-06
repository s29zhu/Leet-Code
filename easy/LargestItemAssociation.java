package leetcode.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class PairString{
	String first;
	String second;
	PairString(String first, String second){
		this.first=first;
		this.second=second;
	}
};	

public class LargestItemAssociation {
	List<List<String>> lists;
	List<PairString> associations;
	public static void union(PairString ps, List<List<String>> lists) {
		List<String> l=new ArrayList<String>();
		l.add(ps.first);
		l.add(ps.second);
		l.stream().sorted().collect(Collectors.toList());
		int index1=-1, index2=-1;
		if(lists.isEmpty()) {
			List<String> list=new ArrayList<String>();
			list.add(ps.first);
			list.add(ps.second);
			lists.stream().sorted().collect(Collectors.toList());
			lists.add(list);
			return;
		}
		/*
		 * scenarioes:
		 * 1. merge ps into one list
		 * 2. ps connect two lists, merge two lists
		 * 3. add a new list into lists
		 */
		//scenario 1
		int i=0;
		for(List<String> list: lists) {
			if(list.contains(ps.first)&&!list.contains(ps.second)) {
				list.add(ps.second);
				l.clear();
				index1=i;
			}
			else if(list.contains(ps.second)&&!list.contains(ps.first)) {
				list.add(ps.first);
				l.clear();
				index2=i;
			}
			i++;
		}
		//scenario 2
		if(index1!=-1 && index2!=-1) {
			List<String> l1=lists.get(index1), l2=lists.get(index2);
			for(String str: l1) {
				if(!l2.contains(str)) l2.add(str);
			}
			l2.stream().sorted().collect(Collectors.toList());
			lists.remove(index1);
		}
		//scenraio3
		if(!l.isEmpty()) {
			lists.add(l);
		}
	}
	public static List<String> find(List<List<String>> lists){
		int max=0;
		HashMap<Integer, ArrayList<List<String>>> map=new HashMap<Integer, ArrayList<List<String>>>();
		for(List<String> list: lists) {
			ArrayList<List<String>> _nls=new ArrayList<List<String>>(), nls;
			int size=list.size();
			nls=map.getOrDefault(size, new ArrayList<List<String>>());
			if(nls.get(0).get(0).compareTo(list.get(0))>0) {
				_nls.add(list);
				_nls.addAll(nls);
			}else {
				_nls.addAll(nls);
				_nls.add(list);
			}
			map.put(size,_nls);
			if(size>max) max=size;
		}
		return map.get(max).get(0);
	}
	List<String> largestItemAssociation(List<PairString> itemAssociation)
	{
		for(PairString ps: itemAssociation) {
			union(ps, lists);
		}

		return find(lists);
	}
}
