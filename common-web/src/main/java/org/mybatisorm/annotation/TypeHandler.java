/* Copyright 2012 The MyBatisORM Team
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
package org.mybatisorm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.type.JdbcType;

/**
 * for TypeHandler
 * 
 * @author yangkun7@gmail.com
 * @see org.apache.ibatis.type.TypeHandler
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TypeHandler {
	Class<? extends org.apache.ibatis.type.TypeHandler<?>> value();

	JdbcType jdbcType() default JdbcType.VARCHAR;
}
