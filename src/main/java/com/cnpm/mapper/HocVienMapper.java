package com.cnpm.mapper;

import com.cnpm.model.HocVienModel;
import com.cnpm.utils.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;



public class HocVienMapper implements RowMapper<HocVienModel> {

	@Override
	public HocVienModel mapRow(ResultSet rs) {
		try {
			HocVienModel model = new HocVienModel();
			model.setId(rs.getLong("id"));
			model.setHoTen(rs.getString("hoten"));
			model.setTaiKhoanId(rs.getLong("taikhoan_id"));
			model.setGioiTinh(rs.getBoolean("gioitinh"));
			model.setEmail(new StringUtil().giaiMa(rs.getString("email")));
			model.setPassword(new StringUtil().maHoa(rs.getString("password")));
			model.setDiaChi(rs.getString("diachi"));
			model.setSoDienThoai(rs.getString("sodienthoai"));
			model.setVaiTro(rs.getString("vaitro"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
