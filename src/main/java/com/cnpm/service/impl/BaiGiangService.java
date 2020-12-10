package com.cnpm.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.cnpm.dao.IBaiGiangDAO;
import com.cnpm.dao.impl.BaiGiangDAO;
import com.cnpm.model.BaiGiangModel;
import com.cnpm.service.IBaiGiangService;
import com.cnpm.utils.StringUtil;

public class BaiGiangService implements IBaiGiangService{

	@Inject
	private IBaiGiangDAO baiGiangDao;
	
	@Override
	public List<BaiGiangModel> timTatCa(BaiGiangModel model) {
		return baiGiangDao.timTatCa(model);
	}

	@Override
	public List<BaiGiangModel> timTatCaTheoKhoaHocId(Long id) {
		return baiGiangDao.timTatCaTheoKhoaHocId(id);
	}

	@Override
	public BaiGiangModel timMot(Long id) {
		return baiGiangDao.timMot(id);
	}

	@Override
	public void xoa(long[] ids) {
		for(long id : ids)
			baiGiangDao.xoa(id);
	}

	@Override
	public BaiGiangModel capNhat(BaiGiangModel model) {
		model.setMaBaiGiang(StringUtil.chuyenMa(model.getTenBaiGiang()));
		baiGiangDao.capNhat(model);
		return baiGiangDao.timMot(model.getId());
	}

	@Override
	public int demTongSoBaiGiang() {
		return baiGiangDao.demTongSoBaiGiang();
	}

	@Override
	public BaiGiangModel luu(BaiGiangModel model) {
		model.setMaBaiGiang(StringUtil.chuyenMa(model.getTenBaiGiang()));
		Long id = baiGiangDao.luu(model);
		return baiGiangDao.timMot(id);
	}
	
}
