package com.cnpm.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.cnpm.dao.IDangKyKhoaHocDAO;
import com.cnpm.model.DangKyKhoaHocModel;
import com.cnpm.service.IDangKyKhoaHocService;

public class DangKyKhoaHocService implements IDangKyKhoaHocService {

	@Inject
	private IDangKyKhoaHocDAO dangKyKhoaHocDao;
	
	@Override
	public List<DangKyKhoaHocModel> timTatCa(DangKyKhoaHocModel model) {
		return dangKyKhoaHocDao.timTatCa(model);
	}

	@Override
	public DangKyKhoaHocModel timMot(Long id) {
		return dangKyKhoaHocDao.timMot(id);
	}

	@Override
	public void xoa(long[] ids,long idKhoaHoc) {
		for (long idHocVien : ids)
			dangKyKhoaHocDao.xoa(idHocVien, idKhoaHoc);
	}

	@Override
	public void capNhat(DangKyKhoaHocModel model) {
		dangKyKhoaHocDao.capNhat(model);
	}

	@Override
	public int demTongSoDangKyKhoaHoc() {
		return dangKyKhoaHocDao.demTongSoDangKyKhoaHoc();
	}

	@Override
	public DangKyKhoaHocModel luu(DangKyKhoaHocModel model) {
		Long id = dangKyKhoaHocDao.luu(model);
		return dangKyKhoaHocDao.timMot(id);
	}

}
