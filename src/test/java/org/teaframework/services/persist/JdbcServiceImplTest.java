/**
 * @(#)JdbcServiceImplTest.java
 * 
 * Tea Service-Oriented Java/JavaEE Application Framework
 * 
 * Copyright(c) Tea Framework Team
 *  
 * Licensed under the GNU LGPL, Version 2.1 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at  
 * 
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */

package org.teaframework.services.persist;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.teaframework.modules.SystemModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Unit Test for JdbcServiceImpl
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */

public class JdbcServiceImplTest {

	private Log log = LogFactory.getLog(this.getClass());

	private Injector injector;
	private JdbcService jdbcService;

	@Before
	public void setUp() throws Exception {
		log.info("Loading Services ");

		injector = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				// Bind ConfigService to the default ConfigServiceImpl.
				install(new SystemModule());
				install(new JdbcPersistModule());
			}
		});
	}

	@Test
	public void testGetQueryRunner() {
		jdbcService = injector.getInstance(JdbcService.class);
		jdbcService.start();
		QueryRunner queryRunner = jdbcService.getQueryRunner();
		try {
			queryRunner.update("DROP TABLE t");
			queryRunner.update("CREATE TABLE t(i VARCHAR(20), vc VARCHAR(20))");
			queryRunner.update("INSERT INTO t VALUES('1', 'one')");
			queryRunner.update("INSERT INTO t VALUES('2', 'two')");
			Map map = (Map) queryRunner.query("SELECT * FROM t",
					new KeyedHandler());
			log.info("value 1 = " + map.get("1"));
			log.info("value 2 = " + map.get("2"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
