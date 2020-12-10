package com.cnpm.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.HocVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.paging.PageRequest;
import com.cnpm.paging.Pageble;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.service.IHocVienService;
import com.cnpm.service.IKhoaHocService;
import com.cnpm.service.ITaiKhoanService;
import com.cnpm.service.impl.KhoaHocService;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -8845071341445666956L;
	@Inject
	private IKhoaHocService khoaHocService;

	@Inject
	private ITaiKhoanService taiKhoanService;

	@Inject
	private IGiaoVienService giaoVienService;

	@Inject
	private IHocVienService hocVienService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		KhoaHocModel modelMoi =  new KhoaHocModel();modelMoi.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timKhoaHocMoi());
		KhoaHocModel modelHot =  new KhoaHocModel();modelHot.setListResult((ArrayList<KhoaHocModel>) khoaHocService.timKhoaHocHot());
		request.setAttribute("modelMoi",modelMoi);
		request.setAttribute("modelHot",modelHot);

		if(action!=null && action.equalsIgnoreCase("dangnhap")){
			String idKh = request.getParameter("idKH");
			String type = request.getParameter("type");
			request.setAttribute("idKH",idKh);
			request.setAttribute("type",type);
			request.getRequestDispatcher("/views/login.jsp").forward(request,response);
		}
		else if(action!=null && action.equalsIgnoreCase("thoat")){
			request.getSession().removeAttribute("USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		}
		else{
			request.getRequestDispatcher("/views/web/home.jsp").forward(request,response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String idKH = request.getParameter("idKH");
		String type = request.getParameter("type");
		if(action!= null && action.equalsIgnoreCase("dangnhap")){
			TaiKhoanModel taiKhoan = taiKhoanService.kiemTraDangNhap(username,password);
			if(taiKhoan!=null){
				Long id = taiKhoan.getId();
				GiaoVienModel giaoVien = giaoVienService.timMotTheoTaiKhoanId(id);
				HocVienModel hocVien = hocVienService.timMotTheoTaiKhoanId(id);
				if(giaoVien != null && (giaoVien.getVaiTro().equalsIgnoreCase(SystemConstant.ADMIN) ||
										giaoVien.getVaiTro().equalsIgnoreCase(SystemConstant.GIAOVIEN))){
					giaoVien.setVaiTro(giaoVien.getVaiTro().trim().replace(" ",""));
					request.getSession().setAttribute("USERMODEL",giaoVien);
					response.sendRedirect(request.getContextPath()+"/admin-trang-chu");
				} else {
					request.getSession().setAttribute("USERMODEL",hocVien);

					List<Long> idKhoaHocHocVien = new ArrayList<>();
					for(KhoaHocModel item :khoaHocService.timTatCaTheoHocVienId(null,hocVien.getId())){
						idKhoaHocHocVien.add(item.getId());
					}
					request.getSession().setAttribute("DSKHOAHOC",idKhoaHocHocVien);

					if(idKH!=null&& !idKH.equals("")){
						response.sendRedirect(request.getContextPath()+"/bai-giang?idUSER="+hocVien.getId()+
								"&idKH="+idKH+"&type="+type);
					}else response.sendRedirect(request.getContextPath()+"/trang-chu");
				}
			} else {
				request.setAttribute("messageResponse", "Sai email hoặc mật khẩu");
				request.setAttribute("alert", "danger");

				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);
			}
		}
	}
	
}
