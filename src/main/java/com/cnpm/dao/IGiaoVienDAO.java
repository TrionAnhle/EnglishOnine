package com.cnpm.dao;

import java.util.List;

import com.cnpm.model.GiaoVienModel;
import com.cnpm.paging.Pageble;

public interface IGiaoVienDAO extends GenericDAO<GiaoVienModel>{
	List<GiaoVienModel> timTatCa(Pageble pageble);
	GiaoVienModel timMot(Long id);
	GiaoVienModel timMotTheoTaiKhoanId(Long id);
	GiaoVienModel timMotTheoEmail(String email);
	void capNhat(GiaoVienModel model);
	int demTongSoGiaoVien();
	Long luu(GiaoVienModel model);
}
