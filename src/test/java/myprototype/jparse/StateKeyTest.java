package myprototype.jparse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class StateKeyTest {

	@Test
	void test() {
		Rule rule = new Rule("Exp", "*", "Exp", "Exp");
		
		ArrayList<Item> items = new ArrayList<>();
		items.add(new Item(rule));
		
		StateKey sk = new StateKey(null, items, new ArrayList<>());
		
		
//		ArrayList<Item> items2 = new ArrayList<>();
//		items2.add(new Item(rule));
//		
//		StateKey sk2 = new StateKey(null, items, new ArrayList<>());
//
//		assertEquals(sk, sk2);
//		assertEquals(sk.hashCode(), sk2.hashCode());
//		
		HashMap<StateKey, Integer> keyStates = new HashMap<>();
		keyStates.put(sk, 0);
		StateKey sk2 = StateKey.create(items);
		System.out.println(sk);
		for (Item item : items)
			item.increaseDot();
		
		System.out.println(sk);
		System.out.println(sk);
		
		keyStates.put(sk, 1);
//		keyStates.put(sk2, 0);
		
		assertTrue(keyStates.containsKey(sk2));
		
//		fail("Not yet implemented");
		
//		String[] arr = {"hello", "world", "nice"};
		List<String> list = Arrays.asList("hello", "world", "nice");
		List<String> subList = list.subList(1, 2);
		
		String s = subList.get(2);
		s = "world";
		
	}

}
