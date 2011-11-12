/**
 * @(#)SystemModule.java
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

import org.teaframework.services.config.ConfigService;
import org.teaframework.services.config.ConfigServiceImpl;
import org.teaframework.services.datasource.C3P0DataSourceServiceImpl;
import org.teaframework.services.datasource.ContainerDataSourceServiceImpl;
import org.teaframework.services.datasource.DataSourceService;

import com.google.inject.AbstractModule;
import com.google.inject.Stage;

/**
 * System Service Modules
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */

public class SystemModule extends AbstractModule {

	@Override
	protected void configure() {

		// Bind Configuration Service
		bind(ConfigService.class).to(ConfigServiceImpl.class);

		// Bind DataSource Service
		if (binder().currentStage().equals(Stage.PRODUCTION)) {
			bind(DataSourceService.class).to(
					ContainerDataSourceServiceImpl.class);
		} else {
			bind(DataSourceService.class).to(C3P0DataSourceServiceImpl.class);
		}
	}

}
