package com.cnpm.controller.web;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.paging.PageRequest;
import com.cnpm.paging.Pageble;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.service.IKhoaHocService;
import com.cnpm.sort.Sorter;
import com.cnpm.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/khoa-hoc"})
public class KhoaHocController extends HttpServlet {

	private static final long serialVersionUID = -8845071341445666956L;

	@Inject
	private IKhoaHocService khoaHocService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KhoaHocModel model = FormUtil.toModel(KhoaHocModel.class, request);
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String view = "";

		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));

		if (type.equals(SystemConstant.SEARCH)){
			String key = request.getParameter("key");

			model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTaCaTheoKey(pageble,key));
			model.setTotalItem(khoaHocService.demTongSoKhoaHocTheoKey());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setType(SystemConstant.SEARCH);
			model.setKey(key);

			view = "/views/web/khoahoc/list.jsp";
		} else if (type.equals(SystemConstant.LIST)) {
			model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTatCa(pageble));
			model.setTotalItem(khoaHocService.demTongSoKhoaHoc(null));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setType(SystemConstant.LIST);

			view = "/views/web/khoahoc/list.jsp";
		} else if (type.equals(SystemConstant.OWN)) {
			model.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timTatCaTheoHocVienId(pageble, Long.parseLong(id)));
			model.setTotalItem(model.getListResult().size());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

			request.setAttribute("tongKhoaHoc", model.getListResult().size());
			view = "/views/web/khoahoc/mycourse.jsp";
		}

		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
