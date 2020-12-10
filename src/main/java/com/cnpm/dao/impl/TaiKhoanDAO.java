package com.cnpm.dao.impl;

import java.util.List;

import com.cnpm.dao.ITaiKhoanDAO;
import com.cnpm.mapper.TaiKhoanMapper;
import com.cnpm.model.TaiKhoanModel;

public class TaiKhoanDAO extends AbstractDAO<TaiKhoanModel> implements ITaiKhoanDAO {

	@Override
	public List<TaiKhoanModel> timTatCa(TaiKhoanModel model) {
		String sql = "SELECT * FROM taikhoan";
		return query(sql, new TaiKhoanMapper());
	}

	@Override
	public TaiKhoanModel timMot(Long id) {
		String sql = "SELECT * FROM taikhoan WHERE id = ?";
		List<TaiKhoanModel> taiKhoan = query(sql, new TaiKhoanMapper(), id);
		return taiKhoan.isEmpty() ? null : taiKhoan.get(0);
	}



	@Override
	public TaiKhoanModel timMotTheoUsernameVaPassword(String username, String password) {
		String sql = "SELECT * FROM taikhoan WHERE username=? and password=?";
		List<TaiKhoanModel> taiKhoan = query(sql, new TaiKhoanMapper(), username,password);
		return taiKhoan.isEmpty() ? null : taiKhoan.get(0);
	}

	@Override
	public void xoa(Long id) {
		String sql = "UPDATE taikhoan SET trangthai = 0 WHERE id = ?";
		capNhat(sql, id);
	}

	@Override
	public void capNhat(TaiKhoanModel model) {
		StringBuilder sql = new StringBuilder("UPDATE taikhoan SET ");
		sql.append(" username=?, password=?, vaitro=? WHERE id = ?");
		capNhat(sql.toString(), model.getUsername(), model.getPassword(), model.getVaiTro(), model.getId());
	}

	@Override
	public Long luu(TaiKhoanModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO taikhoan(username, password, vaitro, trangthai)");
		sql.append(" VALUES(?, ?, ?, ?)");
		return them(sql.toString(), model.getUsername(), model.getPassword(), model.getVaiTro(),model.getTrangThai());
	}

	@Override
	public TaiKhoanModel timMotTheoUserName(String userName) {
		String sql = "SELECT * FROM taikhoan WHERE username = ?";
		List<TaiKhoanModel> taiKhoan = query(sql, new TaiKhoanMapper(), userName);
		return taiKhoan.isEmpty() ? null : taiKhoan.get(0);
	}

}
