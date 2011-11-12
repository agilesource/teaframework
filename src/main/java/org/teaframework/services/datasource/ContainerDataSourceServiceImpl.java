/**
 * @(#)ContainerDataSourceServiceImpl.java
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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.teaframework.services.config.ConfigService;

import com.google.inject.Inject;

/**
 * Container-based DataSource Service Implementation.
 * 
 * @author Peter Cheng (pc@agilesource.org)
 * 
 */

public class ContainerDataSourceServiceImpl implements DataSourceService {

	private final Log log = LogFactory.getLog(getClass());

	private ConfigService configService;

	private Configuration config;

	private Context context;

	@Inject
	public ContainerDataSourceServiceImpl(ConfigService configService) {
		this.configService = configService;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			log.error("Init Container DataSource Context " + e.getMessage());
		}
	}

	@Override
	public DataSource getDataSource() throws RuntimeException {
		try {
			config = configService.getConfig();
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}

		try {
			return (DataSource) context.lookup((String) config
					.getProperty(DatabaseConstant.JDBC_DATASOURCE));
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

}
