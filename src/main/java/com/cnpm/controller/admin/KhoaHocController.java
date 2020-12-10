package com.cnpm.controller.admin;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.BaiGiangModel;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.paging.PageRequest;
import com.cnpm.paging.Pageble;
import com.cnpm.service.IBaiGiangService;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.service.IKhoaHocService;
import com.cnpm.sort.Sorter;
import com.cnpm.utils.FormUtil;
import com.cnpm.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin-khoahoc"})
public class KhoaHocController extends HttpServlet {

	private static final long serialVersionUID = -8845071341445666956L;
	@Inject
	private IGiaoVienService giaoVienService;

	@Inject
	private IKhoaHocService khoaHocService;

	@Inject
	private IBaiGiangService baiGiangService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KhoaHocModel model = FormUtil.toModel(KhoaHocModel.class, request);
		BaiGiangModel baiGiangModel = new BaiGiangModel();
		String id = request.getParameter("id");
		String view = "";
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));

		GiaoVienModel modelGiaoVien = (GiaoVienModel) request.getSession().getAttribute(SystemConstant.USERMODEL);
		if (model.getType().equals(SystemConstant.LIST)) {
			if(modelGiaoVien.getVaiTro().equals(SystemConstant.ADMIN)){
				model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTatCa(pageble));
				model.setTotalItem(khoaHocService.demTongSoKhoaHoc(null));
			}else if(modelGiaoVien.getVaiTro().equals(SystemConstant.GIAOVIEN)){
				model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTatCaTheoGiaoVienId(pageble,modelGiaoVien.getId()));
				model.setTotalItem(khoaHocService.demTongSoKhoaHoc(modelGiaoVien.getId()));
			}
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/khoahoc/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = khoaHocService.timMot(model.getId());
				baiGiangModel.setListResult((ArrayList<BaiGiangModel>) baiGiangService.timTatCaTheoKhoaHocId(model.getId()));
			}

			if(modelGiaoVien.getVaiTro().equals(SystemConstant.ADMIN)){
				modelGiaoVien.setListResult((ArrayList<GiaoVienModel>) giaoVienService.timTatCa(pageble));
				request.setAttribute("modelGiaoVien",modelGiaoVien);
			}
			view = "/views/admin/khoahoc/edit.jsp";
		} else if (model.getType().equals(SystemConstant.OWN)) {
			model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTatCaTheoHocVienId(pageble, Long.parseLong(id)));
			model.setTotalItem(model.getListResult().size());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

			view = "/views/admin/hocvien/mycourse.jsp";
		} else if(model.getType().equals(SystemConstant.SEARCH)){
			String key = request.getParameter("key");

			if(modelGiaoVien.getVaiTro().equals(SystemConstant.ADMIN)){

				model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTaCaTheoKey(pageble,key));
				model.setTotalItem(khoaHocService.demTongSoKhoaHocTheoKey());

			}else if(modelGiaoVien.getVaiTro().equals(SystemConstant.GIAOVIEN)){

				model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTaCaTheoKeyVaGiaoVienId(pageble,key,modelGiaoVien.getId()));
				model.setTotalItem(khoaHocService.demTongSoKhoaHocTheoKey());

			}
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setType(SystemConstant.SEARCH);
			model.setKey(key);
			view = "/views/admin/khoahoc/list.jsp";
		}
		MessageUtil.hienThiThongBao(request);
		request.setAttribute(SystemConstant.MODEL, model);
		request.setAttribute("tongKhoaHoc", model.getTotalItem());
		request.setAttribute("modelBaiGiang", baiGiangModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
