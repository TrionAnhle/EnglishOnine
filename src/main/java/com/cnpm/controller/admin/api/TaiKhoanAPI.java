package com.cnpm.controller.admin.api;

import com.cnpm.model.TaiKhoanModel;
import com.cnpm.service.ITaiKhoanService;
import com.cnpm.utils.HttpUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-taikhoan"})
public class TaiKhoanAPI extends HttpServlet {
    @Inject
    private ITaiKhoanService taiKhoanService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // biding những key/value vào các thuộc tính taiKhoanModel
        TaiKhoanModel model = HttpUtil.of(req.getReader()).toModel(TaiKhoanModel.class);
        model = taiKhoanService.luu(model);

        // trả data ra client -> phải chuyển chuỗi data thành json
        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        TaiKhoanModel model = HttpUtil.of(req.getReader()).toModel(TaiKhoanModel.class);
        model = taiKhoanService.capNhat(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        TaiKhoanModel model = HttpUtil.of(req.getReader()).toModel(TaiKhoanModel.class);
        taiKhoanService.xoa(model.getIds());

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
