/**
 * @(#)ConfigServiceImplTest.java
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

package org.teaframework.services.config;

import static org.junit.Assert.assertEquals;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test for ConfigServiceImpl
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */

public class ConfigServiceImplTest {

	private ConfigService configurationService;
	private Log log = LogFactory.getLog(this.getClass());

	@Before
	public void setUp() throws Exception {
		configurationService = new ConfigServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetConfiguration() {
		try {
			Configuration config = configurationService.getConfig();
			assertEquals("jdbc:hsqldb:mem.", config.getProperty("jdbc.url"));
			log.info("jdbc.url : " + config.getProperty("jdbc.url"));
		} catch (ConfigurationException e) {
			log.error(e.getMessage());
		}
	}
}
