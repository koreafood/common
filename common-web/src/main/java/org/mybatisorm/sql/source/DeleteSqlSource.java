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

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlCommandType;
import org.mybatisorm.Query;
import org.mybatisorm.annotation.SqlCommand;
import org.mybatisorm.sql.builder.DynamicSqlBuilder;
import org.oh.common.util.Utils;

@SqlCommand(SqlCommandType.DELETE)
public class DeleteSqlSource extends DynamicSqlBuilder {

	public DeleteSqlSource(SqlSourceBuilder sqlSourceParser, Class<?> clazz) {
		super(sqlSourceParser,clazz);
		staticSql = "DELETE FROM "+handler.getName();
	}

	public BoundSql getBoundSql(Object parameter) {
		// 모든 조건 적용 by skoh
//		String where = (parameter instanceof Query) ? ((Query)parameter).getCondition() :
//			handler.getNotNullColumnEqualFieldAnd(parameter);
		String where = "";
		if (parameter instanceof Query) {
			Query query = (Query) parameter;
			if (Utils.isValidate(query.getTable()))
				staticSql = Utils.replaceLastString(staticSql, "FROM", query.getTable());
			where += handler.getNotNullColumnEqualFieldAnd(query.getParameter(), Query.PARAMETER_PREFIX);
			if (query.hasCondition()) {
				where += ((where.length() > 0) ? " AND " : "") + query.getCondition();
			}
		} else {
			where += handler.getNotNullColumnEqualFieldAnd(parameter);
		}
		return makeWhere(where,parameter);
	}

}
