/**
 * @(#)C3P0DataSourceServiceImpl.java
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

package org.teaframework.services.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.teaframework.services.config.ConfigService;

import com.google.inject.Inject;
import com.mchange.v2.c3p0.DataSources;

/**
 * C3P0 DB connection pool service implementation.
 * 
 * @author Peter Cheng (pc@agilesource.org)
 * 
 */

public class C3P0DataSourceServiceImpl implements DataSourceService {

	private final Log log = LogFactory.getLog(getClass());

	private ConfigService configService;

	private Configuration configuration;

	private DataSource dataSource;

	@Inject
	public C3P0DataSourceServiceImpl(ConfigService configurationService) {
		this.configService = configurationService;
		try {
			dataSource = initPooledDataSource();
		} catch (Exception e) {
			log.fatal("Init C3P0 Pooled DataSource Failed " + e.getMessage());
		}
	}

	@Override
	public DataSource getDataSource() throws RuntimeException {
		return dataSource;
	}

	private DataSource initPooledDataSource() throws SQLException,
			ConfigurationException {
		configuration = configService.getConfig();

		// Load JDBC Driver
		String jdbcDriverClass = (String) configuration
				.getProperty(DatabaseConstant.JDBC_DRIVER_CLASS_NAME);
		if (StringUtils.isEmpty(jdbcDriverClass)) {
			throw new ConfigurationException(
					"No JDBC Driver class was specified by database.properties : "
							+ DatabaseConstant.JDBC_DRIVER_CLASS_NAME);
		} else {
			try {
				Class.forName(jdbcDriverClass);
			} catch (ClassNotFoundException e) {
				String errorMsg = "JDBC Driver class not found: "
						+ jdbcDriverClass;
				log.fatal(errorMsg);
				throw new ConfigurationException(errorMsg, e);
			}
		}

		// Init Pooled DataSource with configuration
		String jdbcURL = (String) configuration
				.getProperty(DatabaseConstant.JDBC_URL);
		if (StringUtils.isEmpty(jdbcURL)) {
			log.error("No JDBC url was specified by database.properties");
			throw new ConfigurationException("No JDBC url was specified");
		} else {
			String jdbcUsername = (String) configuration
					.getProperty(DatabaseConstant.JDBC_USERNAME);
			String jdbcPassword = (String) configuration
					.getProperty(DatabaseConstant.JDBC_PASSWORD);

			DataSource unpooledDataSource = DataSources.unpooledDataSource(
					jdbcURL, jdbcUsername, jdbcPassword);
			return DataSources.pooledDataSource(unpooledDataSource);
		}
	}

}
