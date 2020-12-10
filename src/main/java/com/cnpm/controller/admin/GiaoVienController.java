package com.cnpm.controller.admin;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.paging.PageRequest;
import com.cnpm.paging.Pageble;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.sort.Sorter;
import com.cnpm.utils.FormUtil;
import com.cnpm.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin-giaovien"})
public class GiaoVienController extends HttpServlet {

    private static final long serialVersionUID = -8845071341445666956L;

    @Inject
    private IGiaoVienService giaoVienService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GiaoVienModel model = FormUtil.toModel(GiaoVienModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult((ArrayList<GiaoVienModel>) giaoVienService.timTatCa(pageble));
            model.setTotalItem(giaoVienService.demTongSoGiaoVien());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            view = "/views/admin/giaovien/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = giaoVienService.timMot(model.getId());
            }
            view = "/views/admin/giaovien/edit.jsp";
        }

        MessageUtil.hienThiThongBao(request);
        request.setAttribute(SystemConstant.MODEL, model);
        request.setAttribute("tongGiaoVien", model.getTotalItem());
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
