package com.cnpm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cnpm.model.DangKyKhoaHocModel;

public class DangKyKhoaHocMapper implements RowMapper<DangKyKhoaHocModel> {

	@Override
	public DangKyKhoaHocModel mapRow(ResultSet rs) {
		try {
			DangKyKhoaHocModel model = new DangKyKhoaHocModel();
			model.setId(rs.getLong("id"));
			model.setHocVienId(rs.getLong("hocvien_id"));
			model.setKhoaHocId(rs.getLong("khoahoc_id"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
