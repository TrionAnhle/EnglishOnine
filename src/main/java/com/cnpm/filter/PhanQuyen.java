package com.cnpm.filter;


import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.HocVienModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PhanQuyen implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        if(url.indexOf("/admin")!=-1){
            if(req.getSession().getAttribute("USERMODEL") instanceof GiaoVienModel) {
                GiaoVienModel giaoVien = (GiaoVienModel) req.getSession().getAttribute("USERMODEL");
                if (giaoVien.getVaiTro().equalsIgnoreCase(SystemConstant.ADMIN) ||
                        giaoVien.getVaiTro().equalsIgnoreCase(SystemConstant.GIAOVIEN)) {
                    filterChain.doFilter(servletRequest,servletResponse);
                }else if(giaoVien.getVaiTro().equalsIgnoreCase(SystemConstant.HOCVIEN)){
                    resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=dangnhap");
                }
            }else{
                resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=dangnhap");
            }
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {}
}
