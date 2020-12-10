package com.cnpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.cnpm.dao.IChiTietKhoaHocDAO;
import com.cnpm.model.ChiTietKhoaHocModel;
import com.cnpm.service.IChiTietKhoaHocService;

public class ChiTietKhoaHocService implements IChiTietKhoaHocService{

	@Inject
	private IChiTietKhoaHocDAO chiTietKhoaHocDao;
	
	@Override
	public List<ChiTietKhoaHocModel> timTatCa(ChiTietKhoaHocModel model) {
		return chiTietKhoaHocDao.timTatCa(model);
	}

	@Override
	public List<Long> timBaiGiangTheoKhoaHocId(Long id) {
		List<Long> listBaiGiang = new ArrayList<>();
		List<ChiTietKhoaHocModel> chiTietKH = chiTietKhoaHocDao.timBaiGiangTheoKhoaHocId(id);
		for(ChiTietKhoaHocModel item : chiTietKH){
			listBaiGiang.add(item.getBaiGiangId());
		}
		return listBaiGiang;
	}

	@Override
	public ChiTietKhoaHocModel timMot(Long id) {
		return chiTietKhoaHocDao.timMot(id);
	}

	@Override
	public void xoa(long[] ids) {
		for(long id : ids)
			chiTietKhoaHocDao.xoa(id);
	}

	@Override
	public ChiTietKhoaHocModel capNhat(ChiTietKhoaHocModel model) {
		chiTietKhoaHocDao.capNhat(model);
		return chiTietKhoaHocDao.timMot(model.getId());
	}

	@Override
	public int demTongSoChiTietKhoaHoc() {
		return chiTietKhoaHocDao.demTongSoChiTietKhoaHoc();
	}

	@Override
	public ChiTietKhoaHocModel luu(ChiTietKhoaHocModel model) {
		Long id = chiTietKhoaHocDao.luu(model);
		return chiTietKhoaHocDao.timMot(id);
	}
	
}
