package myprototype.jparse.syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.token.Token;

class Compounder<T> {
	public Class<?>[] components;
	
	public Compounder(Class<?>... components) {
		this.components = components;
	}
	
	public Function<Stack<Token>, T> compound;
}

public class Rule<T> {
	public Class<T> product;
	
	public Compounder<T>[] compounders; 
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println();
		
		
		
		Class<?> cls = Rule.class;
		Rule r = (Rule)cls.getDeclaredConstructor(int.class).newInstance(1);
	}
}
