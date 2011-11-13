/**
 * @(#)JdbcPersistService.java
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

package org.teaframework.services.persist;

import org.apache.commons.dbutils.QueryRunner;
import org.teaframework.services.datasource.DataSourceService;

import com.google.inject.Inject;

public class JdbcPersistService implements JdbcService {

	private QueryRunner queryRunner;
	private DataSourceService dataSourceService;

	@Inject
	public JdbcPersistService(DataSourceService dataSourceService) {
		this.dataSourceService = dataSourceService;

	}

	@Override
	public QueryRunner getQueryRunner() {
		return this.queryRunner;
	}

	@Override
	public void start() {
		queryRunner = new QueryRunner(this.dataSourceService.getDataSource());
	}

	@Override
	public void stop() {
	}

}
