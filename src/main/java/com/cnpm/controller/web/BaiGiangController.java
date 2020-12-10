package com.cnpm.controller.web;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.BaiGiangModel;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.HocVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.service.IBaiGiangService;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.service.IHocVienService;
import com.cnpm.service.IKhoaHocService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/bai-giang"})
public class BaiGiangController extends HttpServlet {

    @Inject
    private IBaiGiangService baiGiangService;

    @Inject
    private IKhoaHocService khoaHocService;

    @Inject
    private IHocVienService hocVienService;

    @Inject
    private IGiaoVienService giaoVienService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idKhoaHoc = req.getParameter("idKH");
        String type = req.getParameter("type");
        String index = req.getParameter("index");
        Long idBaiGiang;
        String view = "";

        KhoaHocModel khoaHocModel = khoaHocService.timMot(Long.parseLong(idKhoaHoc));
        BaiGiangModel baiGiangModel = new BaiGiangModel();
        BaiGiangModel bgModel;
        baiGiangModel.setListResult((ArrayList<BaiGiangModel>) baiGiangService.timTatCaTheoKhoaHocId(khoaHocModel.getId()));

        // để hiển thị ra tên giáo viên trong thông tin khóa học
        GiaoVienModel giaoVienModel = giaoVienService.timMot(khoaHocModel.getGiaoVienId());

        if (type.equals("show")) {
            view = "/views/web/khoahoc/course.jsp";
            String id = req.getParameter("idUSER");

            HocVienModel hocVienModel = hocVienService.timMotTheoKhoaHocId(Long.parseLong(id), Long.parseLong(idKhoaHoc));
            if (hocVienModel != null) {
                req.setAttribute("hocVien", "tonTai");
            } else {
                req.setAttribute("hocVien", "khongTonTai");
            }
        } else if (type.equals("learn")){
            idBaiGiang = Long.parseLong(req.getParameter("idBG"));
            bgModel = baiGiangService.timMot(idBaiGiang);
            view = "/views/web/baigiang/show.jsp";

            // model này dùng để hiển thị nội dung bài giảng
            req.setAttribute("modelBaiGiang", bgModel);
        }

        // model này dùng để hiển thị tên tất cả bài giảng
        req.setAttribute(SystemConstant.MODEL, baiGiangModel);

        // model này dùng để hiển thị tên giáo viên
        req.setAttribute("tenGiaoVien", giaoVienModel.getHoTen());

        // model này dùng để hiển thị thông tin khóa học
        req.setAttribute("modelKhoaHoc", khoaHocModel);

        req.setAttribute("index", index);

        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, resp);
    }
}
