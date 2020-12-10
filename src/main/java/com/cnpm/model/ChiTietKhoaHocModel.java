package com.cnpm.model;

public class ChiTietKhoaHocModel extends AbstractModel<ChiTietKhoaHocModel>{
	private Long khoaHocId;
	private Long baiGiangId;


	public Long getKhoaHocId() {
		return khoaHocId;
	}

	public void setKhoaHocId(Long khoaHocId) {
		this.khoaHocId = khoaHocId;
	}

	public Long getBaiGiangId() {
		return baiGiangId;
	}

	public void setBaiGiangId(Long baiGiangId) {
		this.baiGiangId = baiGiangId;
	}
}
