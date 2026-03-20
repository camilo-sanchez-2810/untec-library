package com.untec.shared.infrastructure.db;

import java.sql.Connection;

public interface Database {

	Connection getConnection();
}
