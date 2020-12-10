package com.cnpm.controller.admin.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cnpm.model.KhoaHocModel;
import com.cnpm.service.IKhoaHocService;
import com.cnpm.utils.FormUtil;
import com.cnpm.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-khoahoc"})
@MultipartConfig(maxFileSize = 16177215)
public class KhoaHocAPI  extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3976680169627572567L;
	@Inject
    private IKhoaHocService khoaHocService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        Part filePart = req.getPart("anh");
        KhoaHocModel model = FormUtil.toModel(KhoaHocModel.class, req);
        model = khoaHocService.luu(model,filePart);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        Part filePart = req.getPart("anh");
        KhoaHocModel model = FormUtil.toModel(KhoaHocModel.class, req);
        model = khoaHocService.capNhat(model,filePart);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");


        KhoaHocModel model = HttpUtil.of(req.getReader()).toModel(KhoaHocModel.class);
        khoaHocService.xoa(model.getIds());;

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
