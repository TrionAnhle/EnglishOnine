package com.cnpm.service;

import com.cnpm.model.KhoaHocModel;
import com.cnpm.paging.Pageble;

import javax.servlet.http.Part;
import java.util.List;

public interface IKhoaHocService {
    List<KhoaHocModel> timTatCa(Pageble pageble);
    List<KhoaHocModel> timTaCaTheoKey(Pageble pageble, String key);
    List<KhoaHocModel> timTaCaTheoKeyVaGiaoVienId(Pageble pageble, String key, Long id);
    List<KhoaHocModel> timTatCaTheoHocVienId(Pageble pageble, Long id);
    List<KhoaHocModel> timTatCaTheoGiaoVienId(Pageble pageble, Long id);
    List<KhoaHocModel> timKhoaHocMoi();
    List<KhoaHocModel> timKhoaHocHot();
    KhoaHocModel timMot(Long id);
    void xoa(long[] ids);
    KhoaHocModel capNhat(KhoaHocModel model, Part filePart);
    int demTongSoKhoaHoc(Long id);
    int demTongSoKhoaHocTheoKey();
    KhoaHocModel luu(KhoaHocModel model, Part filePart);
}
