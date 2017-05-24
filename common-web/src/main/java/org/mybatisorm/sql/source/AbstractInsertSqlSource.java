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
package org.mybatisorm.sql.source;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlCommandType;
import org.mybatisorm.Query;
import org.mybatisorm.annotation.SqlCommand;
import org.mybatisorm.annotation.handler.FieldList;
import org.mybatisorm.annotation.handler.TokenMaker;
import org.mybatisorm.sql.builder.DynamicSqlBuilder;
import org.mybatisorm.util.StringUtil;

import com.nemustech.common.util.ReflectionUtil;
import com.nemustech.common.util.Utils;

@SqlCommand(SqlCommandType.INSERT)
public abstract class AbstractInsertSqlSource extends DynamicSqlBuilder {

	private static Log logger = LogFactory.getLog(AbstractInsertSqlSource.class);

	public AbstractInsertSqlSource(SqlSourceBuilder sqlSourceParser, Class<?> clazz) {
		super(sqlSourceParser, clazz);
		staticSql = "INSERT INTO " + handler.getName() + "(";
	}

	public BoundSql getBoundSql(final Object parameter, FieldList fieldList) {
		StringBuilder sb = new StringBuilder(staticSql);
		sb.append(StringUtil.join(fieldList.getColumnNames(), ",")).append(") VALUES (");
		List<String> fields = new ArrayList<String>();
		// parameter 구분 by skoh
		if (parameter instanceof Query) {
			Query query = (Query) parameter;
			if (Utils.isValidate(query.getTable()))
				sb.replace(0, sb.length(), Utils.replaceString(sb.toString(), "INTO", "(", query.getTable()));
			for (String fname : fieldList.getFieldNames()) {
				fields.add(TokenMaker.mybatisToken(fname, fieldList.getTypeHandler(fname), Query.PARAMETER_PREFIX,
						ReflectionUtil.getValue(query.getParameter(), fname)));
			}
		} else {
			for (String fname : fieldList.getFieldNames()) {
				// if (fieldList.hasTypeHandler(fname)) {
				// fields.add("#{" + fname + ",typeHandler=" + fieldList.getTypeHandler(fname).value().getName() + ",jdbcType=" + fieldList.getTypeHandler(fname).jdbcType().toString() + "}");
				// } else {
				// fields.add("#{" + fname + "}");
				// }
				// 변수 바인딩 by skoh
	//			fields.add(TokenMaker.mybatisToken(fname, fieldList.getTypeHandler(fname), null));
				fields.add(TokenMaker.mybatisToken(fname, fieldList.getTypeHandler(fname), null,
						ReflectionUtil.getValue(parameter, fname)));
			}
		}
		sb.append(StringUtil.join(fields, ","));
		sb.append(")");
		// .append(StringUtil.join(fieldList.getFieldNames(), "#{%1$s}", ",")).append(")");
		// logger.debug(sql);

		// 주석 처리 by skoh
//		if (logger.isDebugEnabled()) logger.debug("Build Sql: " + sb.toString());

		return getBoundSql(sb.toString(), parameter);
	}
}
