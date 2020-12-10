package com.cnpm.controller.admin;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.HocVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.service.IHocVienService;
import com.cnpm.service.IKhoaHocService;
import com.cnpm.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin-hocvien")
public class HocVienController extends HttpServlet {
    @Inject
    private IHocVienService hocVienService;
    @Inject
    private IKhoaHocService khoaHocService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String id = req.getParameter("id");
        String view = "";
        HocVienModel model = new HocVienModel();

        if (type.equalsIgnoreCase(SystemConstant.LIST)){
            KhoaHocModel modelKhoaHoc = new KhoaHocModel();

            if (id != null) {
                model.setListResult((ArrayList<HocVienModel>) hocVienService.timTatCaTheoKhoaHocId(Long.parseLong(id)));
                modelKhoaHoc = khoaHocService.timMot(Long.parseLong(id));
            }
            MessageUtil.hienThiThongBao(req);
            req.setAttribute("modelKhoaHoc",modelKhoaHoc);
            view = "/views/admin/khoahoc/danhsachhocvien.jsp";
        } else if (type.equalsIgnoreCase(SystemConstant.ALL)) {
            model.setListResult((ArrayList<HocVienModel>) hocVienService.timTatCa(model));

            view = "/views/admin/hocvien/list.jsp";
        }

        req.setAttribute(SystemConstant.MODEL,model);
        req.getRequestDispatcher(view).forward(req,resp);
    }
}
