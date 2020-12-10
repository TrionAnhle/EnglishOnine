package com.cnpm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import com.cnpm.constant.SystemConstant;
import com.cnpm.dao.IHocVienDAO;
import com.cnpm.dao.ITaiKhoanDAO;
import com.cnpm.dao.impl.HocVienDAO;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.HocVienModel;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.service.IHocVienService;
import com.cnpm.utils.StringUtil;
import org.omg.CORBA.portable.ApplicationException;

public class HocVienService implements IHocVienService {

	@Inject
	private IHocVienDAO hocVienDao;

	@Inject
	private ITaiKhoanDAO taiKhoanDao;
	
	@Override
	public List<HocVienModel> timTatCa(HocVienModel model) {
		return hocVienDao.timTatCa(model);
	}

	@Override
	public List<HocVienModel> timTatCaTheoKhoaHocId(Long khoaHocId) {
		return hocVienDao.timTatCaTheoKhoaHocId(khoaHocId);
	}

	@Override
	public HocVienModel timMot(Long id) {
		return hocVienDao.timMot(id);
	}

	@Override
	public HocVienModel timMotTheoTaiKhoanId(Long id) {
		return hocVienDao.timMotTheoTaiKhoanId(id);
	}

	@Override
	public HocVienModel timMotTheoKhoaHocId(Long idHocVien, Long idKhoaHoc) {
		return hocVienDao.timMotTheoKhoaHocId(idHocVien, idKhoaHoc);
	}

	@Override
	public void xoa(long[] ids) {
		for(long id : ids) {
			HocVienModel model = hocVienDao.timMot(id);
			taiKhoanDao.xoa(model.getTaiKhoanId());
		}
	}

	@Override
	public HocVienModel capNhat(HocVienModel model) {
		HocVienModel oldModel = hocVienDao.timMot(model.getId());
		model.setTaiKhoanId(oldModel.getTaiKhoanId());
		model.setVaiTro(oldModel.getVaiTro());
		// cap nhat lại thong tin tai khoan
		TaiKhoanModel taiKhoanModel = taiKhoanDao.timMot(model.getTaiKhoanId());
		taiKhoanModel.setPassword(new StringUtil().maHoa(model.getPassword()));
		model.setEmail(taiKhoanModel.getUsername());
		taiKhoanDao.capNhat(taiKhoanModel);
		hocVienDao.capNhat(model);
		return hocVienDao.timMot(model.getId());
	}

	@Override
	public int demTongSoHocVien() {
		return hocVienDao.demTongSoHocVien();
	}

	@Override
	public HocVienModel luu(HocVienModel model) {
		String userName = model.getEmail().trim();
		StringUtil maHoaDuLieu = new StringUtil();

		TaiKhoanModel taiKhoan = taiKhoanDao.timMotTheoUserName(maHoaDuLieu.maHoa(userName));
		if(taiKhoan != null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		TaiKhoanModel taiKhoanModel = new TaiKhoanModel();
		taiKhoanModel.setUsername(maHoaDuLieu.maHoa(userName));
		taiKhoanModel.setPassword(maHoaDuLieu.maHoa(model.getPassword()));
		taiKhoanModel.setVaiTro(SystemConstant.HOCVIEN);
		taiKhoanModel.setTrangThai(1);

		Long idTaiKhoan = taiKhoanDao.luu(taiKhoanModel);
		model.setTaiKhoanId(idTaiKhoan);
		model.setEmail(maHoaDuLieu.maHoa(model.getEmail()));


		Long id = hocVienDao.luu(model);
		return hocVienDao.timMot(id);
	}

}
