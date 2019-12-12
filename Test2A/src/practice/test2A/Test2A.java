package practice.test2A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test2A {

	private Test2A() {

	}

	public static char first(String s) {
		return s.charAt(0);
	}

	public static char second(String s) throws IllegalArgumentException {
		try {
			return s.charAt(1);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static int product (List<Integer> list){
		int result = 1;
		for  (int i = 0; i < list.size(); i++){
			result = result*list.get(i); 
		}
		return result;
	}

	public static String longest(Set<String> t) {
		String result = "";
		for (String s : t) {
			if (result == "" || s.length() > result.length())
				result = s;
		}
		return result;
	}

	public static Map<String, Set<String>> inverse(Map<String, String> map) {
		
		Map<String, Set<String>> newMap = new HashMap<String, Set<String>>();
		
		for (String s : map.keySet()){
			
			if (!newMap.containsKey(map.get(s))){
				Set<String> set = new HashSet<String>();
				set.add(s);
				newMap.put(map.get(s), set);
			}
			
			else {
				Set<String> set = newMap.get(map.get(s));
				set.add(s);
				newMap.put(map.get(s), set);
			}
		}
		return newMap;
		
	}

}
