package com.cnpm.service;

import java.util.List;

import com.cnpm.model.DangKyKhoaHocModel;

public interface IDangKyKhoaHocService {
	List<DangKyKhoaHocModel> timTatCa(DangKyKhoaHocModel model);
	DangKyKhoaHocModel timMot(Long id);
	void xoa(long[] ids, long idKhoaHoc);
	void capNhat(DangKyKhoaHocModel model);
	int demTongSoDangKyKhoaHoc();
	DangKyKhoaHocModel luu(DangKyKhoaHocModel model);
}
