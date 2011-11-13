/**
 * @(#)EhcacheServiceImpl.java
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

package org.teaframework.services.cache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Install this module to add guice-cache library support for cache providers.
 * 
 * @author Peter Cheng (pc@agilesource.org)
 */

public class EhcacheServiceImpl implements EhcacheService {
	private String cacheConfig = "ehcache.xml";

	private CacheManager cacheManager;

	private final Log log = LogFactory.getLog(getClass());

	public EhcacheServiceImpl() {
	}

	@Override
	public Cache getCache(String cacheName) {
		return this.cacheManager.getCache(cacheName);
	}

	@Override
	public void start() {
		try {
			URL url = getClass().getResource(this.cacheConfig);
			this.cacheManager = new CacheManager(url);
		} catch (Exception e) {
			log.error("Init CacheManager with config file " + e.getMessage());
		}
	}

	@Override
	public void stop() {
		cacheManager.shutdown();
	}

}
