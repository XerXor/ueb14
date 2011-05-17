package de.base;
import java.util.Comparator;

public class IntegerComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		if ((Integer) o1 < (Integer) o2)
			return -1;
		if ((Integer) o1 > (Integer) o2)
			return 1;
		return 0;
	}

}
