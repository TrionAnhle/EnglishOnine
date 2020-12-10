package com.cnpm.model;

public class DangKyKhoaHocModel extends AbstractModel<DangKyKhoaHocModel> {
	private Long hocVienId;
	private Long khoaHocId;
	
	public Long getKhoaHocId() {
		return khoaHocId;
	}
	public void setKhoaHocId(Long khoaHocId) {
		this.khoaHocId = khoaHocId;
	}
	public Long getHocVienId() {
		return hocVienId;
	}
	public void setHocVienId(Long hocVienId) {
		this.hocVienId = hocVienId;
	}
	
}
