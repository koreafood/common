package org.oh.sample.model;

import org.mybatisorm.annotation.Column;
import org.oh.web.page.Paging;

/**
 * DDL : 테스트 테이블
 * 
 * <pre>
 * -- Mysql
 * DROP TABLE IF EXISTS test;
 * CREATE TABLE test
 * (
 *   id INT(20) NOT NULL AUTO_INCREMENT
 *  ,name VARCHAR(100)
 *  ,sample_id INT(20) NOT NULL
 *  ,reg_id VARCHAR(100)
 *  ,reg_dt VARCHAR(14)
 *  ,mod_id VARCHAR(100)
 *  ,mod_dt VARCHAR(14)
 *  ,PRIMARY KEY
 * (
 *   id
 * )) CHARSET=utf8;
 * 
 * -- Oracle
 * DROP TABLE test CASCADE CONSTRAINTS PURGE;
 * CREATE TABLE test
 * (
 *   id NUMBER(20) NOT NULL
 *  ,name VARCHAR2(100)
 *  ,sample_id NUMBER(20) NOT NULL
 *  ,reg_id VARCHAR2(100)
 *  ,reg_dt VARCHAR2(14)
 *  ,mod_id VARCHAR2(100)
 *  ,mod_dt VARCHAR2(14)
 *  ,CONSTRAINT test_pk PRIMARY KEY
 * (
 *   id
 * ));
 * DROP SEQUENCE test_seq;
 * CREATE SEQUENCE test_seq INCREMENT BY 1 START WITH 1;
 * 
 * SELECT 
 *     sample_.reg_id sample_reg_id,
 *     sample_.reg_dt sample_reg_dt,
 *     sample_.mod_id sample_mod_id,
 *     sample_.mod_dt sample_mod_dt,
 *     sample_.id sample_id,
 *     sample_.name sample_name,
 *     test_.reg_id test_reg_id,
 *     test_.reg_dt test_reg_dt,
 *     test_.mod_id test_mod_id,
 *     test_.mod_dt test_mod_dt,
 *     test_.id test_id,
 *     test_.name test_name,
 *     test_.sample_id test_sample_id
 * FROM
 *     Sample sample_
 *         INNER JOIN
 *     Test test_ ON (sample_.id = test_.sample_id)
 * WHERE
 *     (test_.name LIKE 't%')
 * ORDER BY sample_.id DESC , test_.id DESC;
 * </pre>
 * 
 * @author skoh
 */
public class Test extends Paging {
	@Column(primaryKey = true) // , sequence = "test_seq") // oracle
	protected Long id;

	@Column
	protected String name;

	@Column(references = "Sample.id")
	protected Long sample_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSample_id() {
		return sample_id;
	}

	public void setSample_id(Long sample_id) {
		this.sample_id = sample_id;
	}
}