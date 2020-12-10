package com.cnpm.dao;

import java.util.List;

import com.cnpm.model.HocVienModel;

public interface IHocVienDAO extends GenericDAO<HocVienModel> {
	List<HocVienModel> timTatCa(HocVienModel model);
	List<HocVienModel> timTatCaTheoKhoaHocId(Long khoaHocId);
	HocVienModel timMot(Long id);
	HocVienModel timMotTheoTaiKhoanId(Long id);
	HocVienModel timMotTheoKhoaHocId(Long idHocVien, Long idKhoaHoc);
	void capNhat(HocVienModel model);
	int demTongSoHocVien();
	Long luu(HocVienModel model);
}
