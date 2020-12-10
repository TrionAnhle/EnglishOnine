package com.cnpm.dao;

import com.cnpm.model.BaiGiangModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.paging.Pageble;

import java.util.List;

public interface IKhoaHocDAO extends GenericDAO<KhoaHocModel>{
    List<KhoaHocModel> timTatCa(Pageble pageble);
    List<KhoaHocModel> timTatCaTheoHocVienId(Pageble pageble, Long id);
    List<KhoaHocModel> timTatCaTheoGiaoVienId(Pageble pageble, Long id);
    List<KhoaHocModel> timKhoaHocMoi();
    List<KhoaHocModel> timKhoaHocHot();
    KhoaHocModel timMot(Long id);
    KhoaHocModel timMotTheoTen(String tenKhoaHoc);
    void xoa(Long id);
    int demTongSoKhoaHoc(Long id);
    Long luu(KhoaHocModel model);
    void capNhat(KhoaHocModel model);
}
