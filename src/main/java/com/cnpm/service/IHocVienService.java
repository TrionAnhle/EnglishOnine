package com.cnpm.service;

import java.util.List;

import com.cnpm.model.HocVienModel;

public interface IHocVienService {
	List<HocVienModel> timTatCa(HocVienModel model);
	List<HocVienModel> timTatCaTheoKhoaHocId(Long khoaHocId);
	HocVienModel timMot(Long id);
	HocVienModel timMotTheoTaiKhoanId(Long id);
	HocVienModel timMotTheoKhoaHocId(Long idHocVien, Long idKhoaHoc);
	void xoa(long[] ids);
	HocVienModel capNhat(HocVienModel model);
	int demTongSoHocVien();
	HocVienModel luu(HocVienModel model);
}
