package com.cnpm.service;

import java.util.List;

import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.paging.Pageble;

public interface IGiaoVienService {
	List<GiaoVienModel> timTatCa(Pageble pageble);
	GiaoVienModel timMot(Long id);
	GiaoVienModel timMotTheoTaiKhoanId(Long id);
	Long timIdTheoEmail(String email);
	void xoa(long[] ids);
	GiaoVienModel capNhat(GiaoVienModel model);
	int demTongSoGiaoVien();
	GiaoVienModel luu(GiaoVienModel model);
}
