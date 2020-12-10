package com.cnpm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cnpm.model.GiaoVienModel;
import com.cnpm.utils.StringUtil;

public class GiaoVienMapper implements RowMapper<GiaoVienModel>{

	@Override
	public GiaoVienModel mapRow(ResultSet rs) {
		try {
			GiaoVienModel model = new GiaoVienModel();
			model.setId(rs.getLong("id"));
			model.setHoTen(rs.getString("hoten"));
			model.setTaiKhoanId(rs.getLong("taikhoan_id"));
			model.setGioiTinh(rs.getBoolean("gioitinh"));
			model.setEmail(new StringUtil().giaiMa(rs.getString("email")));
			model.setDiaChi(rs.getString("diachi"));
			model.setSoDienThoai(rs.getString("sodienthoai"));
			model.setPassword(new StringUtil().giaiMa(rs.getString("password")));
			model.setVaiTro(rs.getString("vaitro"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
