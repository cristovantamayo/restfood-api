package com.cristovantamayo.restfoodapi.config;

import org.hibernate.dialect.MySQL57Dialect;

public class restfoodMysql5InnodbUTF8Dialect extends MySQL57Dialect{

	public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
	
}
