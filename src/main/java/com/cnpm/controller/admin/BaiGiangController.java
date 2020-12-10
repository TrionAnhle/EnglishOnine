package com.cnpm.controller.admin;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.BaiGiangModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.service.IBaiGiangService;
import com.cnpm.service.IKhoaHocService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-baigiang"})
public class BaiGiangController extends HttpServlet {

    @Inject
    private IBaiGiangService baiGiangService;

    @Inject
    private IKhoaHocService khoaHocService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idKhoaHoc = req.getParameter("idKH");
        String idBaiGiang = req.getParameter("idBG");

        KhoaHocModel khoaHocModel = khoaHocService.timMot(Long.parseLong(idKhoaHoc));
        BaiGiangModel baiGiangModel = new BaiGiangModel();
        if (idBaiGiang != null) {
            baiGiangModel = baiGiangService.timMot(Long.parseLong(idBaiGiang));
        }

        req.setAttribute(SystemConstant.MODEL, baiGiangModel);
        req.setAttribute("modelKhoaHoc", khoaHocModel);
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/baigiang/edit.jsp");
        rd.forward(req, resp);
    }
}
