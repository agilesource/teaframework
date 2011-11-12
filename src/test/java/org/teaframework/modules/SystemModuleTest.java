/**
 * @(#)SystemModuleTest.java
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

package org.teaframework.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.teaframework.services.datasource.DataSourceService;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Unit Test for System Module
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */
public class SystemModuleTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Log log = LogFactory.getLog(this.getClass());

	@Test
	public void testConfigure() {
		Injector injector = Guice.createInjector(new SystemModule());
		loadData(injector);
	}

	private void loadData(Injector injector) {
		DataSourceService dss = injector.getInstance(DataSourceService.class);
		DataSource dataSource = dss.getDataSource();
		// get hold of a Connection an do stuff, in the usual way
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			stmt.execute("DROP TABLE t");
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
