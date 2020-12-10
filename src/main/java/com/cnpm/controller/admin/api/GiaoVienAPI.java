package com.cnpm.controller.admin.api;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-giaovien"})
public class GiaoVienAPI extends HttpServlet {
    
	private static final long serialVersionUID = -9026984681407511140L;
	
	@Inject
	private IGiaoVienService giaoVienService;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        GiaoVienModel giaoVienModel = HttpUtil.of(req.getReader()).toModel(GiaoVienModel.class);
        giaoVienModel = giaoVienService.luu(giaoVienModel);
        
        mapper.writeValue(resp.getOutputStream(), giaoVienModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        GiaoVienModel giaoVienModel = HttpUtil.of(req.getReader()).toModel(GiaoVienModel.class);
        if(giaoVienModel.getId()==null){
            giaoVienModel.setId(giaoVienService.timIdTheoEmail(giaoVienModel.getEmail()));
        }
        giaoVienModel = giaoVienService.capNhat(giaoVienModel);
        req.getSession().removeAttribute(SystemConstant.USERMODEL);
        req.getSession().setAttribute(SystemConstant.USERMODEL,giaoVienModel);

        mapper.writeValue(resp.getOutputStream(), giaoVienModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        GiaoVienModel giaoVienModel = HttpUtil.of(req.getReader()).toModel(GiaoVienModel.class);
        giaoVienService.xoa(giaoVienModel.getIds());

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
