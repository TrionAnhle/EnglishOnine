package com.cnpm.dao.impl;

import java.util.List;

import com.cnpm.dao.IBaiGiangDAO;
import com.cnpm.mapper.BaiGiangMapper;
import com.cnpm.model.BaiGiangModel;

public class BaiGiangDAO extends AbstractDAO<BaiGiangModel> implements IBaiGiangDAO{

	@Override
	public List<BaiGiangModel> timTatCa(BaiGiangModel model) {
		String sql = "SELECT * FROM baigiang";
		return query(sql, new BaiGiangMapper());
	}

	@Override
	public List<BaiGiangModel> timTatCaTheoKhoaHocId(Long id) {
		String sql = "SELECT * FROM baigiang,chitietkhoahoc WHERE baigiang.id = chitietkhoahoc.baigiang_id and khoahoc_id = " + id;
		return query(sql, new BaiGiangMapper());
	}

	@Override
	public BaiGiangModel timMot(Long id) {
		String sql = "SELECT  * FROM baigiang WHERE id = ?";
		List<BaiGiangModel> list = query(sql, new BaiGiangMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void xoa(Long id) {
		String sql = "DELETE FROM baigiang WHERE id = ?";
		capNhat(sql, id);
		
	}

	@Override
	public int demTongSoBaiGiang() {
		String sql = "SELECT count(*) FROM baigiang";
		return dem(sql);
	}

	@Override
	public Long luu(BaiGiangModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO baigiang ");
		sql.append(" (tenbaigiang, mabaigiang, noidung) ");
		sql.append(" VALUES(?,?,?)");
		return them(sql.toString(), model.getTenBaiGiang(), model.getMaBaiGiang(), model.getNoiDung());
	}
	
	@Override
	public void capNhat(BaiGiangModel model) {
		StringBuilder sql = new StringBuilder("UPDATE baigiang SET ");
		sql.append(" tenbaigiang=?, mabaigiang=?, noidung=? WHERE id = ?");
		capNhat(sql.toString(), model.getTenBaiGiang(), model.getMaBaiGiang(), model.getNoiDung(),model.getId());
	}
	
}
