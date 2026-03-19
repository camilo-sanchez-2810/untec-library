package com.untec.shared.infrastructure.db;

public interface Database {

	Database getInstance();
	void connect();
	void disconnect();
}
