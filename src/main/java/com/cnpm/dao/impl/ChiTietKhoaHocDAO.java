package com.cnpm.dao.impl;

import java.util.List;

import com.cnpm.dao.IChiTietKhoaHocDAO;
import com.cnpm.mapper.ChiTietKhoaHocMapper;
import com.cnpm.model.ChiTietKhoaHocModel;

public class ChiTietKhoaHocDAO extends AbstractDAO<ChiTietKhoaHocModel> implements IChiTietKhoaHocDAO{

	@Override
	public List<ChiTietKhoaHocModel> timTatCa(ChiTietKhoaHocModel model) {
		String sql = "SELECT * FROM chitietkhoahoc";
		return query(sql, new ChiTietKhoaHocMapper());
	}

	@Override
	public List<ChiTietKhoaHocModel> timBaiGiangTheoKhoaHocId(Long id) {
		String sql = "SELECT * FROM chitietkhoahoc WHERE khoahoc_id = ?";
		return query(sql,new ChiTietKhoaHocMapper(), id);
	}

	@Override
	public ChiTietKhoaHocModel timMot(Long id) {
		String sql = "SELECT * FROM chitietkhoahoc WHERE id = ?";
		List<ChiTietKhoaHocModel> list = query(sql, new ChiTietKhoaHocMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void xoa(Long id) {
		String sql = "DELETE FROM chitietkhoahoc WHERE id = ?";
		capNhat(sql, id);
		
	}

	@Override
	public int demTongSoChiTietKhoaHoc() {
		String sql = "SELECT count(*) FROM chitietkhoahoc";
		return dem(sql);
	}

	@Override
	public Long luu(ChiTietKhoaHocModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO chitietkhoahoc ");
		sql.append(" (khoahoc_id, baigiang_id) ");
		sql.append(" VALUES(?,?)");
		return them(sql.toString(), model.getKhoaHocId(), model.getBaiGiangId());
	}
	
	@Override
	public void capNhat(ChiTietKhoaHocModel model) {
		StringBuilder sql = new StringBuilder("UPDATE chitietkhoahoc SET ");
		sql.append(" khoahoc_id=?, baigiang_id=? WHERE id = ?");
		capNhat(sql.toString(), model.getKhoaHocId(), model.getBaiGiangId(), model.getId());
	}
	
}
