package com.untec.user.domain;

public enum Status {
	ACTIVE("active", "Activo"), 
	SUSPENDED("suspended", "Suspendido");
	
	private final String status;
	private final String name;	
	
	private Status(String status, String name) {
		this.status = status;
		this.name = name;
	}
	
	public static Status fromStatus(String status) {
		for(Status s : Status.values()) {
			if(s.status.equalsIgnoreCase(status)) return s;
		}
		throw new IllegalArgumentException("El estado " + status + " no existe");
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getName() {
		return this.name;
	}
	
}
