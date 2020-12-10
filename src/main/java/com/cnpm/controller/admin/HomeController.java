package com.cnpm.controller.admin;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.service.ITaiKhoanService;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin-trang-chu"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -8845071341445666956L;
	@Inject
	private ITaiKhoanService taiKhoanService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String type = request.getParameter("type");
		if(type!=null){
			if(type.equalsIgnoreCase("thongtin")) {
				GiaoVienModel giaoVienModel = (GiaoVienModel) request.getSession().getAttribute(SystemConstant.USERMODEL);

//				TaiKhoanModel modelTaiKhoan = taiKhoanService.timMotTheoUserName(giaoVienModel.getEmail());
//				request.setAttribute(SystemConstant.MODEL, modelTaiKhoan);
				request.getRequestDispatcher("/views/admin/thongtincanhan.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
