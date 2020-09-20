package model;

import java.util.Comparator;

public class PhoneComparator implements Comparator<Client> {

	@Override
	public int compare(Client c1, Client c2) {
		int comp=0;
		comp=c1.getPhone().compareTo(c2.getPhone());
		if (comp < 0) {
			comp = 1;
		} else {
			if (comp > 0) {
				comp = -1;
			}
		}
		return comp;
	}

}
