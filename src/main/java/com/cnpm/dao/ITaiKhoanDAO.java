package com.cnpm.dao;

import java.util.List;

import com.cnpm.model.TaiKhoanModel;

public interface ITaiKhoanDAO extends GenericDAO<TaiKhoanModel> {
	List<TaiKhoanModel> timTatCa(TaiKhoanModel model);
	TaiKhoanModel timMot(Long id);
	TaiKhoanModel timMotTheoUserName(String userName);
	TaiKhoanModel timMotTheoUsernameVaPassword(String username,String password);
	void xoa(Long id);
	void capNhat(TaiKhoanModel model);
	Long luu(TaiKhoanModel model);
}
