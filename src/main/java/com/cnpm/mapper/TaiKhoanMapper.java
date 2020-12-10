package com.cnpm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cnpm.model.TaiKhoanModel;

public class TaiKhoanMapper implements RowMapper<TaiKhoanModel> {

	@Override
	public TaiKhoanModel mapRow(ResultSet rs) {
		try {
			TaiKhoanModel model = new TaiKhoanModel();
			model.setId(rs.getLong("id"));
			model.setUsername(rs.getString("username"));
			model.setPassword(rs.getString("password"));
			model.setVaiTro(rs.getString("vaitro"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
