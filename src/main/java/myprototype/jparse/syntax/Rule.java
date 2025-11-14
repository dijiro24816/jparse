package myprototype.jparse.syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.token.Token;

public class Rule {
	public Class<?> product;
	
	public Class<?>[] components;
	
	public Function<Stack<Token>, Object> compound;
	
	public Rule(int i) {
		System.out.println(i);
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println();
		
		Class<?> cls = Rule.class;
		Rule r = (Rule)cls.getDeclaredConstructor(int.class).newInstance(1);
	}
}
