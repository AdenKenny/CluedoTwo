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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.value1 == null) ? 0 : this.value1.hashCode());
		result = prime * result + ((this.value2 == null) ? 0 : this.value2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		if (this.value1 == null) {
			if (other.value1 != null) {
				return false;
			}
		} else if (!this.value1.equals(other.value1)) {
			return false;
		}
		if (this.value2 == null) {
			if (other.value2 != null) {
				return false;
			}
		} else if (!this.value2.equals(other.value2)) {
			return false;
		}
		return true;
	}
	
	

}
