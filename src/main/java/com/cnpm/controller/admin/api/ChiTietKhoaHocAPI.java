package com.cnpm.controller.admin.api;

import com.cnpm.model.ChiTietKhoaHocModel;
import com.cnpm.service.IChiTietKhoaHocService;
import com.cnpm.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-chitietkhoahoc"})
public class ChiTietKhoaHocAPI  extends HttpServlet {
    @Inject
    private IChiTietKhoaHocService chiTietKhoaHocService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ChiTietKhoaHocModel model = HttpUtil.of(req.getReader()).toModel(ChiTietKhoaHocModel.class);
        model = chiTietKhoaHocService.luu(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ChiTietKhoaHocModel model = HttpUtil.of(req.getReader()).toModel(ChiTietKhoaHocModel.class);
        model = chiTietKhoaHocService.capNhat(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ChiTietKhoaHocModel model = HttpUtil.of(req.getReader()).toModel(ChiTietKhoaHocModel.class);
        chiTietKhoaHocService.xoa(model.getIds());;

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
