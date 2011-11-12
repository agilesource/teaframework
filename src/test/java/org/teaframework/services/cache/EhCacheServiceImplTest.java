/**
 * @(#)EhCacheServiceImplTest.java
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

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Unit Test for EhcacheServiceImpl
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */

public class EhCacheServiceImplTest {

	private final Log log = LogFactory.getLog(getClass());

	@Test
	public void testEhcache() {
		Injector injector = Guice.createInjector(new EhcacheModule());

		EhcacheService cacheService = injector
				.getInstance(EhcacheService.class);
		cacheService.start();

		Cache sampleCache1 = cacheService.getCache("sampleCache1");
		sampleCache1.put(new Element("key1", "value1"));
		Element element = sampleCache1.get("key1");
		log.info("Key1: Value = " + element.getObjectValue().toString());
		cacheService.stop();
	}

}
