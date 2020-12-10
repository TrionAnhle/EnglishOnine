package com.cnpm.controller.admin.api;

import com.cnpm.model.DangKyKhoaHocModel;
import com.cnpm.model.HocVienModel;
import com.cnpm.service.IDangKyKhoaHocService;
import com.cnpm.utils.HttpUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-dangkykhoahoc"})
public class DangKyKhoaHocAPI extends HttpServlet {
    @Inject
    private IDangKyKhoaHocService dangKyKhoaHocService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        DangKyKhoaHocModel model = HttpUtil.of(req.getReader()).toModel(DangKyKhoaHocModel.class);
        model = dangKyKhoaHocService.luu(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        DangKyKhoaHocModel model = HttpUtil.of(req.getReader()).toModel(DangKyKhoaHocModel.class);
        dangKyKhoaHocService.xoa(model.getIds(), Long.parseLong(req.getParameter("id")));

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
