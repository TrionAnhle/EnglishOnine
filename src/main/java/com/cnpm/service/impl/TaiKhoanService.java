package com.cnpm.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.cnpm.dao.ITaiKhoanDAO;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.service.ITaiKhoanService;
import com.cnpm.utils.StringUtil;

public class TaiKhoanService implements ITaiKhoanService {

	@Inject
	private ITaiKhoanDAO taiKhoanDao;
	
	@Override
	public List<TaiKhoanModel> timTatCa(TaiKhoanModel model) {
		return taiKhoanDao.timTatCa(model);
	}

	@Override
	public TaiKhoanModel timMot(Long id) {
		return taiKhoanDao.timMot(id);
	}

	@Override
	public void xoa(long[] ids) {
		for (long id : ids) 
			taiKhoanDao.xoa(id);
	}

	@Override
	public TaiKhoanModel capNhat(TaiKhoanModel model) {
		taiKhoanDao.capNhat(model);
		return taiKhoanDao.timMot(model.getId());
	}

	@Override
	public TaiKhoanModel luu(TaiKhoanModel model) {
		Long id = taiKhoanDao.luu(model);
		return taiKhoanDao.timMot(id);
	}

	@Override
	public TaiKhoanModel timMotTheoUserName(String userName) {
		return taiKhoanDao.timMotTheoUserName(userName);
	}

	@Override
	public TaiKhoanModel kiemTraDangNhap(String userName, String password) {
		StringUtil util = new StringUtil();
		return taiKhoanDao.timMotTheoUsernameVaPassword(util.maHoa(userName),util.maHoa(password));
	}

}
