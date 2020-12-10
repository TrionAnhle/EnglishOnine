package com.cnpm.controller.admin.api;

import com.cnpm.model.BaiGiangModel;
import com.cnpm.service.IBaiGiangService;
import com.cnpm.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-baigiang"})
public class BaiGiangAPI  extends HttpServlet {
    @Inject
    private IBaiGiangService baiGiangService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BaiGiangModel model = HttpUtil.of(req.getReader()).toModel(BaiGiangModel.class);
        model = baiGiangService.luu(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BaiGiangModel model = HttpUtil.of(req.getReader()).toModel(BaiGiangModel.class);
        model = baiGiangService.capNhat(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BaiGiangModel model = HttpUtil.of(req.getReader()).toModel(BaiGiangModel.class);
        baiGiangService.xoa(model.getIds());;

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
