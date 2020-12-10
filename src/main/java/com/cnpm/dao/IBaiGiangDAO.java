package com.cnpm.dao;

import java.util.List;

import com.cnpm.model.BaiGiangModel;

public interface IBaiGiangDAO extends GenericDAO<BaiGiangModel>{
	List<BaiGiangModel> timTatCa(BaiGiangModel model);
	List<BaiGiangModel> timTatCaTheoKhoaHocId(Long id);
	BaiGiangModel timMot(Long id);
	void xoa(Long id);
	void capNhat(BaiGiangModel model);
	int demTongSoBaiGiang();
	Long luu(BaiGiangModel model);
}
