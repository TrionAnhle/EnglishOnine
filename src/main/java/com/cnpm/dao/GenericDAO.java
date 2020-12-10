package com.cnpm.dao;

import java.util.List;

import com.cnpm.mapper.RowMapper;

public interface GenericDAO<T> {
	List<T> query(String sql,RowMapper<T> mapRow,Object... parameters);
	Long them(String sql, Object... parameters);
	void capNhat(String sql, Object... parameters);
	int dem(String sql, Object... parameters);
}
