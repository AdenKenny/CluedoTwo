package util;

/**
 * A class that holds a pair of generic objects. Used for utility purposes.
 *
 * @author Aden kenny and Simon Pope.
 *
 * @param <E> The first generic parameter.
 * @param <T> The second generic parameter.
 */

public class Pair<E, T> {

	private E value1;
	private T value2;

	public Pair(E value1, T value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public E getValue1() {
		return this.value1;
	}

	public void setValue1(E value1) {
		this.value1 = value1;
	}

	public T getValue2() {
		return this.value2;
	}

	public void setValue2(T value2) {
		this.value2 = value2;
	}

}
