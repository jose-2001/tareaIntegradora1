package model;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Client> {

	@Override
	public int compare(Client c1, Client c2) {
		int comp=0;
		comp=c1.getFirstName().compareTo(c2.getFirstName());
		return comp;
	}

}
