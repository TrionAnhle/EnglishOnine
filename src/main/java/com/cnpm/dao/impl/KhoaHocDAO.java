package com.cnpm.dao.impl;

import com.cnpm.dao.IKhoaHocDAO;
import com.cnpm.mapper.GiaoVienMapper;
import com.cnpm.mapper.KhoaHocMapper;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class KhoaHocDAO extends AbstractDAO<KhoaHocModel> implements IKhoaHocDAO {
    @Override
    public List<KhoaHocModel> timTatCa(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM khoahoc,giaovien ");
        sql.append(" WHERE khoahoc.giaovien_id = giaovien.id ");
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
        }
        return query(sql.toString(), new KhoaHocMapper());
    }

    @Override
    public List<KhoaHocModel> timTatCaTheoHocVienId(Pageble pageble, Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM khoahoc,dangkykhoahoc WHERE khoahoc.id = dangkykhoahoc.khoahoc_id AND hocvien_id = ?");
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
        }
        return query(sql.toString(), new KhoaHocMapper(), id);
    }

    @Override
    public List<KhoaHocModel> timTatCaTheoGiaoVienId(Pageble pageble, Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM khoahoc,giaovien WHERE khoahoc.giaovien_id = giaovien.id AND giaovien_id = ?");
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
        }
        return query(sql.toString(), new KhoaHocMapper(), id);
    }

    @Override
    public List<KhoaHocModel> timKhoaHocMoi() {
        String sql = "select * from khoahoc,giaovien " +
                " where khoahoc.giaovien_id = giaovien.id "+
                " order by khoahoc.thoigiantao desc " +
                " limit 0,4";
        return query(sql,new KhoaHocMapper());
    }

    @Override
    public List<KhoaHocModel> timKhoaHocHot() {
        String sql = "select *,count(*) from khoahoc,dangkykhoahoc,giaovien" +
                " where khoahoc.id = dangkykhoahoc.khoahoc_id " +
                "   and khoahoc.giaovien_id = giaovien.id "+
                " group by khoahoc.id" +
                " order by count(*) desc" +
                " limit 0,4 ;";
        return query(sql,new KhoaHocMapper());
    }

    @Override
    public KhoaHocModel timMot(Long id) {
        String sql = "SELECT  * FROM khoahoc WHERE id = ?";
        List<KhoaHocModel> list = query(sql, new KhoaHocMapper(), id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public KhoaHocModel timMotTheoTen(String tenKhoaHoc) {
        String sql = "SELECT  * FROM khoahoc WHERE tenkhoahoc = ?";
        List<KhoaHocModel> list = query(sql, new KhoaHocMapper(), tenKhoaHoc);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void xoa(Long id) {
        String sql = "DELETE FROM khoahoc WHERE id = ?";
        capNhat(sql, id);
    }

    @Override
    public int demTongSoKhoaHoc(Long id) {
        String sql = "SELECT count(*) FROM khoahoc";
        if(id!=null){
            sql+=" WHERE giaovien_id = "+id;
        }
        return dem(sql);
    }

    @Override
    public Long luu(KhoaHocModel model) {
        StringBuilder sql = new StringBuilder("INSERT INTO khoahoc ");
        sql.append(" (tenkhoahoc, makhoahoc, giaovien_id, capdo, anh, motangan, thoigiantao)");
        sql.append(" VALUES(?,?,?,?,?,?,?)");
        return them(sql.toString(), model.getTenKhoaHoc(), model.getMaKhoaHoc(), model.getGiaoVienId(),
                model.getCapDo(),model.getAnh(), model.getMoTaNgan(), model.getThoiGianTao());
    }

    @Override
    public void capNhat(KhoaHocModel model) {
        StringBuilder sql = new StringBuilder("UPDATE khoahoc SET ");
        sql.append(" tenkhoahoc=?, makhoahoc=?, giaovien_id=?, capdo=?, anh=?, motangan=?, thoigiantao=? WHERE id = ?");
        capNhat(sql.toString(), model.getTenKhoaHoc(), model.getMaKhoaHoc(), model.getGiaoVienId(),
                model.getCapDo(), model.getAnh(), model.getMoTaNgan(), model.getThoiGianTao(), model.getId());
    }
}
