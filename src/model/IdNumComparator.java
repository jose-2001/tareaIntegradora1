package model;

import java.util.Comparator;

public class IdNumComparator implements Comparator<Client> {

	@Override
	public int compare(Client c1, Client c2) {
		int comp=0;
		comp=c1.getIdNum().compareTo(c2.getIdNum());
		return comp;
	}

}
