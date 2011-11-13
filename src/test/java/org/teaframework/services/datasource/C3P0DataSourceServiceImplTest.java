/**
 * @(#)C3P0DataSourceServiceImplTest.java
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
 * 
 * For more information, please visit:
 * http://github.com/agilesource/teaframework
 */

package org.teaframework.services.datasource;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.teaframework.services.config.ConfigService;
import org.teaframework.services.config.ConfigServiceImpl;

/**
 * Unit Test for C3P0DataSourceServiceImpl
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */

public class C3P0DataSourceServiceImplTest {

	private ConfigService configurationService;

	private DataSourceService dataSourceService;

	private Log log = LogFactory.getLog(this.getClass());

	@Before
	public void setUp() throws Exception {
		configurationService = new ConfigServiceImpl();
		dataSourceService = new C3P0DataSourceServiceImpl(configurationService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDataSource() {
		DataSource dataSource = dataSourceService.getDataSource();
		assertNotNull(dataSource);
	}

	@Test
	public void testHSQLDataSource() {
		DataSource dataSource = dataSourceService.getDataSource();
		// get hold of a Connection an do stuff, in the usual way
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE t(i BIGINT, vc VARCHAR(20))");
			stmt.execute("INSERT INTO t VALUES(1, 'one')");
			stmt.execute("INSERT INTO t VALUES(2, 'two')");
			rs = stmt.executeQuery("SELECT * FROM t");
			while (rs.next()) {
				log.info(rs.getString("i"));
				log.info(rs.getString("vc"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());

		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}

		}
	}

}
