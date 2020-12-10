package com.cnpm.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.cnpm.dao.IGiaoVienDAO;
import com.cnpm.dao.ITaiKhoanDAO;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.paging.Pageble;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.utils.StringUtil;

public class GiaoVienService implements IGiaoVienService{

	@Inject
	private IGiaoVienDAO giaoVienDao;
	
	@Inject
	private ITaiKhoanDAO taiKhoanDao;
	
	@Override
	public List<GiaoVienModel> timTatCa(Pageble pageble) {
		return giaoVienDao.timTatCa(pageble);
	}

	@Override
	public GiaoVienModel timMot(Long id) {
		return giaoVienDao.timMot(id);
	}

	@Override
	public GiaoVienModel timMotTheoTaiKhoanId(Long id) {
		return giaoVienDao.timMotTheoTaiKhoanId(id);
	}

	@Override
	public Long timIdTheoEmail(String email) {
		return giaoVienDao.timMotTheoEmail(new StringUtil().maHoa(email)).getId();
	}

	@Override
	public void xoa(long[] ids) {
		for(long id : ids){
			GiaoVienModel model = giaoVienDao.timMot(id);
			taiKhoanDao.xoa(model.getTaiKhoanId());
		}
	}

	@Override
	public GiaoVienModel capNhat(GiaoVienModel model) {
		// cao nhat lai thong tin giao vien
		GiaoVienModel oldModel = giaoVienDao.timMot(model.getId());
		model.setTaiKhoanId(oldModel.getTaiKhoanId());
		model.setVaiTro(oldModel.getVaiTro());
		// cap nhat lại thong tin tai khoan
		TaiKhoanModel taiKhoanModel = taiKhoanDao.timMot(model.getTaiKhoanId());
		taiKhoanModel.setPassword(new StringUtil().maHoa(model.getPassword()));
		model.setEmail(taiKhoanModel.getUsername());
		taiKhoanDao.capNhat(taiKhoanModel);
		giaoVienDao.capNhat(model);
		return giaoVienDao.timMot(model.getId());
		
	}

	@Override
	public int demTongSoGiaoVien() {
		return giaoVienDao.demTongSoGiaoVien();
	}

	@Override
	public GiaoVienModel luu(GiaoVienModel model) {
		String userName = model.getEmail().trim();
		StringUtil maHoaDuLieu = new StringUtil();
		TaiKhoanModel taiKhoan = taiKhoanDao.timMotTheoUserName(maHoaDuLieu.maHoa(userName));
		if(taiKhoan != null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		TaiKhoanModel taiKhoanModel = new TaiKhoanModel();
		taiKhoanModel.setUsername(maHoaDuLieu.maHoa(userName));
		taiKhoanModel.setPassword(maHoaDuLieu.maHoa(model.getPassword()));
		taiKhoanModel.setVaiTro(model.getVaiTro());
		taiKhoanModel.setTrangThai(1);

		
		Long idTaiKhoan = taiKhoanDao.luu(taiKhoanModel);
		model.setTaiKhoanId(idTaiKhoan);
		model.setEmail(maHoaDuLieu.maHoa(model.getEmail()));

		Long id = giaoVienDao.luu(model);
		return giaoVienDao.timMot(id);
	}

}
