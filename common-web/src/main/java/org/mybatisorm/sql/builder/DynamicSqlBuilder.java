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
package org.mybatisorm.sql.builder;

import java.util.Map;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.log4j.Logger;
import org.mybatisorm.Query;
import org.oh.common.util.ParserUtil;
import org.oh.common.util.ReflectionUtil;
import org.oh.common.util.Utils;

public abstract class DynamicSqlBuilder extends SqlBuilder {

	private static Logger logger = Logger.getLogger(DynamicSqlBuilder.class);

	protected String staticSql;

	public DynamicSqlBuilder(SqlSourceBuilder sqlSourceParser, Class<?> targetClass) {
		super(sqlSourceParser,targetClass);
	}

	// 주석 처리 by skoh1
//	public abstract BoundSql getBoundSql(Object parameterObject);

	protected BoundSql getBoundSql(String sql, Object parameterObject) {
		logger.debug(sql);
		// by skoh1
		sql = parserVariable(sql, parameterObject);
		return getSqlSourceParser().parse(sql, parameterObject.getClass()).getBoundSql(parameterObject); // null 추가 by skoh
//		return getSqlSourceParser().parse(sql, parameterObject.getClass(), null).getBoundSql(parameterObject); // mybatis ver 3.2.0 이상
	}

	protected BoundSql makeWhere(String where, Object parameter) {
		return getBoundSql(
				where != null && where.length() > 0 ?
						staticSql + " WHERE " + where : staticSql,
						parameter);
	}

	// 메소드 추가 by skoh
	protected String makeCondition(String where, Query query) {
		return where + (query.hasCondition() ? ((where.length() > 0) ? " AND " : "") + query.getCondition() : "");
	}

	// 메소드 추가 by skoh1
	protected String parserVariable(String sql, Object parameterObject) {
		Map<String, String> variables2;
		if (parameterObject instanceof Query) {
			Map<String, Object> variables = ReflectionUtil.convertObjectToMap(parameterObject);
			variables2 = Utils.convertMapToMap(variables);
		} else {
			Map<String, Object> variables = ReflectionUtil.convertObjectToMap(parameterObject);
			variables2 = Utils.convertMapToMap(variables);
		}
		return ParserUtil.parse(sql, variables2);
	}
}
