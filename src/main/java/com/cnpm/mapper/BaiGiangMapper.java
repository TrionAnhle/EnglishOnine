package com.cnpm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cnpm.model.BaiGiangModel;

public class BaiGiangMapper implements RowMapper<BaiGiangModel>{

	@Override
	public BaiGiangModel mapRow(ResultSet rs) {
		try {
			BaiGiangModel model = new BaiGiangModel();
			model.setId(rs.getLong("id"));
			model.setTenBaiGiang(rs.getString("tenbaigiang"));
			model.setMaBaiGiang(rs.getString("mabaigiang"));
			model.setNoiDung(rs.getString("noidung"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
