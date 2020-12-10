package com.cnpm.mapper;

import com.cnpm.model.KhoaHocModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhoaHocMapper implements RowMapper<KhoaHocModel> {
    @Override
    public KhoaHocModel mapRow(ResultSet rs) {
        try{
            KhoaHocModel model = new KhoaHocModel();
            model.setId(rs.getLong("id"));
            model.setTenKhoaHoc(rs.getString("tenkhoahoc"));
            model.setMaKhoaHoc(rs.getString("makhoahoc"));
            model.setGiaoVienId(rs.getLong("giaovien_id"));
            model.setCapDo(rs.getString("capdo"));
            model.setAnh(rs.getString("anh"));
            model.setMoTaNgan(rs.getString("motangan"));
            model.setThoiGianTao(rs.getTimestamp("thoigiantao"));
            try {
                model.setTenGiaoVien(rs.getString("hoten"));
            }catch (SQLException e){

            }
            return model;
        }catch (SQLException e){
            return null;
        }
    }
}
