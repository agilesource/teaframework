/**
 * @(#)PropertiesHelper.java
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

package org.teaframework.utils.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * A help class to access to property file.
 * 
 * @author pc@agilesource.org (Peter Cheng)
 */

public class PropertiesHelper {

	public static boolean getBoolean(String property, Properties properties) {
		return Boolean.valueOf(properties.getProperty(property)).booleanValue();
	}

	public static boolean getBoolean(String property, Properties properties,
			boolean defaultValue) {
		String setting = properties.getProperty(property);
		return (setting == null) ? defaultValue : Boolean.valueOf(setting)
				.booleanValue();
	}

	public static int getInt(String property, Properties properties,
			int defaultValue) {
		String propValue = properties.getProperty(property);
		return (propValue == null) ? defaultValue : Integer.parseInt(propValue);
	}

	public static Integer getInteger(String property, Properties properties) {
		String propValue = properties.getProperty(property);
		return (propValue == null) ? null : Integer.valueOf(propValue);
	}

	public static String getString(String property, Properties properties) {
		String propValue = properties.getProperty(property);
		return propValue;
	}

	public static String getString(String property, Properties properties,
			String defaultValue) {
		String propValue = properties.getProperty(property);
		return (propValue == null) ? defaultValue : propValue;
	}

	public static Map toMap(String property, String delim, Properties properties) {
		Map map = new HashMap();
		String propValue = properties.getProperty(property);
		if (propValue != null) {
			StringTokenizer tokens = new StringTokenizer(propValue, delim);
			while (tokens.hasMoreTokens()) {
				map.put(tokens.nextToken(),
						tokens.hasMoreElements() ? tokens.nextToken()
								: StringHelper.EMPTY_STRING);
			}
		}
		return map;
	}

	public static String[] toStringArray(String propValue, String delim) {
		return StringHelper.split(delim, propValue);
	}

	public static String[] toStringArray(String property, String delim,
			Properties properties) {
		return toStringArray(properties.getProperty(property), delim);
	}

	public PropertiesHelper() {
	}

}