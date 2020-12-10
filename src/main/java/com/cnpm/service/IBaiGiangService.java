package com.cnpm.service;

import java.util.List;

import com.cnpm.model.BaiGiangModel;

public interface IBaiGiangService {
	List<BaiGiangModel> timTatCa(BaiGiangModel model);
	List<BaiGiangModel> timTatCaTheoKhoaHocId(Long id);
	BaiGiangModel timMot(Long id);
	void xoa(long[] ids);
	BaiGiangModel capNhat(BaiGiangModel model);
	int demTongSoBaiGiang();
	BaiGiangModel luu(BaiGiangModel model);
}
