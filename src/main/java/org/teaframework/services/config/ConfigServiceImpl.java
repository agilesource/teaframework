/**
 * @(#)ConfigServiceImpl.java
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

package org.teaframework.services.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationBuilder;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Configuration Wrapper for Apache Commmon-Configuration.
 * 
 * @author Peter Cheng (pc@agilesource.org)
 * 
 */

public class ConfigServiceImpl implements ConfigService {

	private Log log = LogFactory.getLog(this.getClass());

	public static final String CONFIG_FILE = "tea-config.xml";

	private ConfigurationBuilder configBuilder;

	public ConfigServiceImpl() {
		try {
			configure();
		} catch (ConfigurationException e) {
			log.fatal("Can't init tea-config.xml " + e.getMessage());
		}
	}

	private void configure() throws ConfigurationException {
		configBuilder = new DefaultConfigurationBuilder(CONFIG_FILE);
	}

	public Configuration getConfig() throws ConfigurationException {
		return configBuilder.getConfiguration();
	}

}
