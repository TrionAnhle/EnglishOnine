package com.cnpm.controller.admin.api;

import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.HocVienModel;
import com.cnpm.model.TaiKhoanModel;
import com.cnpm.service.IHocVienService;
import com.cnpm.utils.HttpUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-hocvien"})
public class HocVienAPI extends HttpServlet {
    @Inject
    private IHocVienService hocVienService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        HocVienModel model = HttpUtil.of(req.getReader()).toModel(HocVienModel.class);
        model = hocVienService.luu(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        HocVienModel model = HttpUtil.of(req.getReader()).toModel(HocVienModel.class);
        model = hocVienService.capNhat(model);

        mapper.writeValue(resp.getOutputStream(), model);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        HocVienModel model = HttpUtil.of(req.getReader()).toModel(HocVienModel.class);
        hocVienService.xoa(model.getIds());

        mapper.writeValue(resp.getOutputStream(), null);
    }
}
