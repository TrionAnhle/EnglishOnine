package com.cnpm.dao;

import java.util.List;

import com.cnpm.model.DangKyKhoaHocModel;

public interface IDangKyKhoaHocDAO extends GenericDAO<DangKyKhoaHocModel> {
	List<DangKyKhoaHocModel> timTatCa(DangKyKhoaHocModel model);
	DangKyKhoaHocModel timMot(Long id);
	void xoa(Long id, Long idKhochoc);
	void capNhat(DangKyKhoaHocModel model);
	int demTongSoDangKyKhoaHoc();
	Long luu(DangKyKhoaHocModel model);
}
