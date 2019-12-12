package practice.test2A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map <String, String> map = new HashMap <String, String>();
		map.put("donkey", "kong");
		map.put("diddy", "kong");
		map.put("dixie", "kong");
		map.put("mario", "bros");
		map.put("luigi", "bros");
		map.put("princess", "peach");
		System.out.println(map);
		System.out.println(Test2A.inverse(map));
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(7);
		list.add(2);
		list.add(1);
		Object[] a = list.toArray();
		System.out.println(Test2A.product(list));
	}

}
