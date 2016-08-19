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

	public E first() {
		return this.value1;
	}

	public T second() {
		return this.value2;
	}

}
