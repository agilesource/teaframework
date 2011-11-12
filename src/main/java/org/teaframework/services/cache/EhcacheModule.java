/**
 * @(#)EhcacheModule.java
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

package org.teaframework.services.cache;

/**
 * Ehcache Module
 * 
 * @author Peter Cheng (pc@agilesource.org)
 */

public class EhcacheModule extends CacheModule {

	public EhcacheModule() {
	}

	@Override
	protected void configureCache() {
		bind(EhcacheService.class).to(EhcacheServiceImpl.class);
	}

}
