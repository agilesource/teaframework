/**
 * @(#)CacheModule.java
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

import com.google.inject.AbstractModule;

/**
 * Install this module to add guice-cache library support for cache providers.
 * 
 * @author Peter Cheng (pc@agilesource.org)
 */

public abstract class CacheModule extends AbstractModule {

	@Override
	protected final void configure() {
		configureCache();
		requireBinding(EhcacheService.class);
	}

	protected abstract void configureCache();

}