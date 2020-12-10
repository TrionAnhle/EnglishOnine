package com.cnpm.dao.impl;

import java.util.List;

import com.cnpm.dao.IGiaoVienDAO;
import com.cnpm.mapper.GiaoVienMapper;
import com.cnpm.mapper.KhoaHocMapper;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.paging.Pageble;
import org.apache.commons.lang.StringUtils;

public class GiaoVienDAO extends AbstractDAO<GiaoVienModel> implements IGiaoVienDAO{

	@Override
	public List<GiaoVienModel> timTatCa(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM giaovien INNER JOIN taikhoan ON giaovien.taikhoan_id = taikhoan.id ");
		sql.append("WHERE taikhoan.trangthai=1 ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		return query(sql.toString(), new GiaoVienMapper());
	}

	@Override
	public GiaoVienModel timMot(Long id) {
		String sql = "SELECT  * FROM giaovien INNER JOIN taikhoan ON giaovien.taikhoan_id = taikhoan.id WHERE giaovien.id = ?";
		List<GiaoVienModel> list = query(sql, new GiaoVienMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public GiaoVienModel timMotTheoTaiKhoanId(Long id) {
		String sql = "SELECT  * FROM giaovien INNER JOIN taikhoan ON giaovien.taikhoan_id = taikhoan.id WHERE giaovien.taikhoan_id = ? AND taikhoan.trangthai=1";
		List<GiaoVienModel> list = query(sql, new GiaoVienMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public GiaoVienModel timMotTheoEmail(String email) {
		String sql = "SELECT  * FROM giaovien INNER JOIN taikhoan ON giaovien.taikhoan_id = taikhoan.id WHERE giaovien.email = ? AND taikhoan.trangthai=1";
		List<GiaoVienModel> list = query(sql, new GiaoVienMapper(), email);
		return list.isEmpty() ? null : list.get(0);
	}


	@Override
	public int demTongSoGiaoVien() {
		String sql = "SELECT count(*) FROM giaovien INNER JOIN taikhoan ON giaovien.taikhoan_id = taikhoan.id WHERE taikhoan.trangthai=1";
		return dem(sql);
	}

	@Override
	public Long luu(GiaoVienModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO giaovien ");
		sql.append(" (hoten, taikhoan_id, gioitinh, email, diachi, sodienthoai) ");
		sql.append(" VALUES(?,?,?,?,?,?)");
		return them(sql.toString(), model.getHoTen(), model.getTaiKhoanId(), model.getGioiTinh(),
				model.getEmail(), model.getDiaChi(), model.getSoDienThoai());
	}
	
	@Override
	public void capNhat(GiaoVienModel model) {
		StringBuilder sql = new StringBuilder("UPDATE giaovien SET ");
		sql.append(" hoten=?, taikhoan_id=?, gioitinh=?, email=?, diachi=?, sodienthoai=? WHERE id = ?");
		capNhat(sql.toString(), model.getHoTen(), model.getTaiKhoanId(), model.getGioiTinh(),
				model.getEmail(), model.getDiaChi(), model.getSoDienThoai(),model.getId());
	}

}
