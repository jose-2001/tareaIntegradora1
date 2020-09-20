package model;

public class Restaurant {

	//attributes
	private String name;
	private String nit;
	private String adminName;
	
	//methods
	public Restaurant(String nm, String nt,String an){
		name=nm;
		nit=nt;
		adminName=an;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param nm the name to set
	 */
	public void setName(String nm) {
		name = nm;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param an the adminName to set
	 */
	public void setAdminName(String an) {
		adminName = an;
	}
	
	public String toString() {
		String msg="Name: "+getName()+". NIT: "+getNit()+". Name of the administrator: "+getAdminName();
		return msg;
	}
	
}
