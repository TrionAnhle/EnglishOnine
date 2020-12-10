package com.cnpm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cnpm.model.ChiTietKhoaHocModel;

public class ChiTietKhoaHocMapper implements RowMapper<ChiTietKhoaHocModel>{

	@Override
	public ChiTietKhoaHocModel mapRow(ResultSet rs) {
		try {
			ChiTietKhoaHocModel model = new ChiTietKhoaHocModel();
			model.setId(rs.getLong("id"));
			model.setKhoaHocId(rs.getLong("khoahoc_id"));
			model.setBaiGiangId(rs.getLong("baigiang_id"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
