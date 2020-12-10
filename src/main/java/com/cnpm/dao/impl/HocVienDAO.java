package com.cnpm.dao.impl;

import java.util.List;

import com.cnpm.dao.IHocVienDAO;
import com.cnpm.mapper.HocVienMapper;
import com.cnpm.model.HocVienModel;

public class HocVienDAO extends AbstractDAO<HocVienModel> implements IHocVienDAO {

	@Override
	public List<HocVienModel> timTatCa(HocVienModel model) {
		String sql = "SELECT * FROM hocvien INNER JOIN taikhoan ON hocvien.taikhoan_id = taikhoan.id WHERE taikhoan.trangthai=1";
		return query(sql, new HocVienMapper());
	}

	@Override
	public List<HocVienModel> timTatCaTheoKhoaHocId(Long khoaHocId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM hocvien ");
		sql.append(" INNER JOIN taikhoan ON hocvien.taikhoan_id = taikhoan.id ");
		sql.append(" INNER JOIN dangkykhoahoc ON hocvien.id = dangkykhoahoc.hocvien_id ");
		sql.append(" WHERE taikhoan.trangthai=1 AND dangkykhoahoc.khoahoc_id= ?");
		return query(sql.toString(), new HocVienMapper(), khoaHocId);
	}

	@Override
	public HocVienModel timMot(Long id) {
		String sql = "SELECT * FROM hocvien INNER JOIN taikhoan ON hocvien.taikhoan_id = taikhoan.id WHERE hocvien.id = ? AND taikhoan.trangthai=1";
		List<HocVienModel> list = query(sql, new HocVienMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public HocVienModel timMotTheoTaiKhoanId(Long id) {
		String sql = "SELECT * FROM hocvien INNER JOIN taikhoan ON hocvien.taikhoan_id = taikhoan.id WHERE hocvien.taikhoan_id = ? AND taikhoan.trangthai=1";
		List<HocVienModel> list = query(sql, new HocVienMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public HocVienModel timMotTheoKhoaHocId(Long idHocVien, Long idKhoaHoc) {
		String sql = "SELECT * FROM hocvien INNER JOIN taikhoan ON hocvien.taikhoan_id = taikhoan.id INNER JOIN dangkykhoahoc ON hocvien.id = dangkykhoahoc.hocvien_id WHERE hocvien.id = ? AND dangkykhoahoc.khoahoc_id = ? AND taikhoan.trangthai=1";
		List<HocVienModel> list = query(sql, new HocVienMapper(), idHocVien, idKhoaHoc);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void capNhat(HocVienModel model) {
		StringBuilder sql = new StringBuilder("UPDATE hocvien SET ");
		sql.append(" hoten=?, taikhoan_id=?, gioitinh=?, email=?, diachi=?, sodienthoai=? WHERE id = ?");
		capNhat(sql.toString(), model.getHoTen(), model.getTaiKhoanId(), model.getGioiTinh(),
				model.getEmail(), model.getDiaChi(), model.getSoDienThoai(),model.getId());
	}

	@Override
	public int demTongSoHocVien() {
		String sql = "SELECT count(*) FROM hocvien";
		return dem(sql);
	}

	@Override
	public Long luu(HocVienModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO hocvien ");
		sql.append(" (hoten, taikhoan_id, gioitinh, email, diachi, sodienthoai) ");
		sql.append(" VALUES(?,?,?,?,?,?)");
		return them(sql.toString(), model.getHoTen(), model.getTaiKhoanId(), model.getGioiTinh(),
				model.getEmail(), model.getDiaChi(), model.getSoDienThoai());
	}

}
