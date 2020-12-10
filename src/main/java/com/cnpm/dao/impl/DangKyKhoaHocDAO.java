package com.cnpm.dao.impl;

import java.util.List;

import com.cnpm.dao.IDangKyKhoaHocDAO;
import com.cnpm.mapper.DangKyKhoaHocMapper;
import com.cnpm.model.DangKyKhoaHocModel;

public class DangKyKhoaHocDAO extends AbstractDAO<DangKyKhoaHocModel> implements IDangKyKhoaHocDAO {

	@Override
	public List<DangKyKhoaHocModel> timTatCa(DangKyKhoaHocModel model) {
		String sql = "SELECT * FROM dangkykhoahoc";
		return query(sql, new DangKyKhoaHocMapper());
	}

	@Override
	public DangKyKhoaHocModel timMot(Long id) {
		String sql = "SELECT * FROM dangkykhoahoc WHERE id = ?";
		List<DangKyKhoaHocModel> list = query(sql, new DangKyKhoaHocMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void xoa(Long id, Long idKhoaHoc) {
		String sql = "DELETE FROM dangkykhoahoc WHERE hocvien_id = ? AND khoahoc_id =?";
		capNhat(sql, id, idKhoaHoc);
	}

	@Override
	public void capNhat(DangKyKhoaHocModel model) {
		StringBuilder sql = new StringBuilder("UPDATE dangkykhoahoc SET ");
		sql.append(" hocvien_id=?, khoahoc_id=? WHERE id = ?");
		capNhat(sql.toString(), model.getHocVienId(), model.getKhoaHocId(), model.getId());
	}

	@Override
	public int demTongSoDangKyKhoaHoc() {
		String sql = "SELECT count(*) FROM dangkykhoahoc";
		return dem(sql);
	}

	@Override
	public Long luu(DangKyKhoaHocModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO dangkykhoahoc ");
		sql.append(" (hocvien_id, khoahoc_id) ");
		sql.append(" VALUES(?,?)");
		return them(sql.toString(), model.getHocVienId(), model.getKhoaHocId());
	}

}
