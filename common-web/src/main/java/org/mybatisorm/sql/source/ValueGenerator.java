/*
 *    Copyright 2012 The MyBatisORM Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatisorm.sql.source;

import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.session.Configuration;

public interface ValueGenerator {
	// keyPrefix 추가 by skoh
//	public void generate(Builder builder, String parentId, Class<?> clazz);
	public void generate(Builder builder, String parentId, Class<?> clazz, String keyPrefix);
	public void setConfiguration(Configuration configuration);
}
