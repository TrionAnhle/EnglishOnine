package com.cnpm.service;

import java.util.List;

import com.cnpm.model.ChiTietKhoaHocModel;

public interface IChiTietKhoaHocService {
	List<ChiTietKhoaHocModel> timTatCa(ChiTietKhoaHocModel model);
	List<Long> timBaiGiangTheoKhoaHocId(Long id);
	ChiTietKhoaHocModel timMot(Long id);
	void xoa(long[] ids);
	ChiTietKhoaHocModel capNhat(ChiTietKhoaHocModel model);
	int demTongSoChiTietKhoaHoc();
	ChiTietKhoaHocModel luu(ChiTietKhoaHocModel model);
}
