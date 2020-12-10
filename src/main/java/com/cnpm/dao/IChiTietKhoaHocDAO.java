package com.cnpm.dao;

import java.util.List;

import com.cnpm.model.ChiTietKhoaHocModel;

public interface IChiTietKhoaHocDAO extends GenericDAO<ChiTietKhoaHocModel>{
	List<ChiTietKhoaHocModel> timTatCa(ChiTietKhoaHocModel model);
	List<ChiTietKhoaHocModel> timBaiGiangTheoKhoaHocId(Long id);
	ChiTietKhoaHocModel timMot(Long id);
	void xoa(Long id);
	void capNhat(ChiTietKhoaHocModel model);
	int demTongSoChiTietKhoaHoc();
	Long luu(ChiTietKhoaHocModel model);
}
