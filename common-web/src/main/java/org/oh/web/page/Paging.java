package org.oh.web.page;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Paging {
	/**
	 * 페이지 번호
	 */
	protected int page_number = 1;

	/**
	 * 전체 건수
	 */
	protected int total_sise = 0;

	/**
	 * 페이지당 건수
	 */
	protected int rows_per_page = PageNavigator.ROWS_PER_PAGE;

	/**
	 * 화면당 페이지 수
	 */
	protected int page_group_count = PageNavigator.PAGE_GROUP_COUNT;

	public int getPage_number() {
		return page_number;
	}

	public void setPage_number(int page_number) {
		this.page_number = page_number;
	}

	public int getTotal_sise() {
		return total_sise;
	}

	public void setTotal_sise(int total_sise) {
		this.total_sise = total_sise;
	}

	public int getRows_per_page() {
		return rows_per_page;
	}

	public void setRows_per_page(int rows_per_page) {
		this.rows_per_page = rows_per_page;
	}

	public int getPage_group_count() {
		return page_group_count;
	}

	public void setPage_group_count(int page_group_count) {
		this.page_group_count = page_group_count;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}