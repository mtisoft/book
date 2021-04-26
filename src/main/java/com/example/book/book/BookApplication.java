package com.example.book.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;


import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class BookApplication extends JpaBaseConfiguration {

protected BookApplication(DataSource dataSource, JpaProperties properties,ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
	super(dataSource, properties, jtaTransactionManager);
}

@Override // ces redefinitions permettent d indiquer a spring d lutiliser eclipsejpa au lieu de hibernate
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
	return new EclipseLinkJpaVendorAdapter();
}

@Override  // ces redefinitions permettent d indiquer a spring d lutiliser eclipsejpa au lieu de hibernate
	protected Map<String, Object> getVendorProperties() {
	// Turn off dynamic weaving to disable LTW lookup in static weaving mode
	return Collections.singletonMap("eclipselink.weaving", "false");
}

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
