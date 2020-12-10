package com.cnpm.model;

public class BaiGiangModel extends AbstractModel<BaiGiangModel>{
	private String tenBaiGiang;
	private String maBaiGiang;
	private String noiDung;

	public String getTenBaiGiang() {
		return tenBaiGiang;
	}
	public void setTenBaiGiang(String tenBaiGiang) {
		this.tenBaiGiang = tenBaiGiang;
	}
	public String getMaBaiGiang() {
		return maBaiGiang;
	}
	public void setMaBaiGiang(String maBaiGiang) {
		this.maBaiGiang = maBaiGiang;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	
}
