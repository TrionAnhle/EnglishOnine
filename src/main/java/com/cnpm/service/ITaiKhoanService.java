package com.cnpm.service;

import java.util.List;

import com.cnpm.model.TaiKhoanModel;

public interface ITaiKhoanService {
	List<TaiKhoanModel> timTatCa(TaiKhoanModel model);
	TaiKhoanModel timMot(Long id);
	TaiKhoanModel timMotTheoUserName(String userName);
	TaiKhoanModel kiemTraDangNhap(String userName,String password);
	void xoa(long[] ids);
	TaiKhoanModel capNhat(TaiKhoanModel model);
	TaiKhoanModel luu(TaiKhoanModel model);
}
