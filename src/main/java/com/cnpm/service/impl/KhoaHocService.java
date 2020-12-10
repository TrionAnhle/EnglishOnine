package com.cnpm.service.impl;

import com.cnpm.constant.SystemConstant;
import com.cnpm.dao.IKhoaHocDAO;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.paging.Pageble;
import com.cnpm.service.IChiTietKhoaHocService;
import com.cnpm.service.IKhoaHocService;
import com.cnpm.utils.StringUtil;

import javax.inject.Inject;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocService implements IKhoaHocService {
    @Inject
    private IKhoaHocDAO khoaHocDAO;
    @Inject
    private IChiTietKhoaHocService chiTietKhoaHocService;
    @Inject
    private BaiGiangService baiGiangService;
    @Inject
    private HocVienService hocVienService;

    private Integer tongSoKhoaHocTheoKey;
    @Override
    public List<KhoaHocModel> timTatCa(Pageble pageble) {
        return khoaHocDAO.timTatCa(pageble);
    }

    @Override
    public List<KhoaHocModel> timTaCaTheoKey(Pageble pageble, String key) {
        Integer limit = pageble.getLimit();
        pageble.setLimit(null);
        key = StringUtil.chuyenMa(key).toLowerCase();

        List<KhoaHocModel> tatCaKhoaHoc= khoaHocDAO.timTatCa(pageble);
        List<KhoaHocModel> khoaHoc = new ArrayList<>();
        for(int i=0;i<tatCaKhoaHoc.size();i++){
            if(tatCaKhoaHoc.get(i).getMaKhoaHoc().indexOf(key)!=-1){
                khoaHoc.add(tatCaKhoaHoc.get(i));
            }
        }
        tatCaKhoaHoc.clear();
        tatCaKhoaHoc.addAll(khoaHoc);
        khoaHoc.clear();
        this.tongSoKhoaHocTheoKey = tatCaKhoaHoc.size();
        pageble.setLimit(limit);
        for(int i=pageble.getOffset();i<pageble.getOffset()+limit && i<tatCaKhoaHoc.size();i++){
            khoaHoc.add(tatCaKhoaHoc.get(i));
        }
        return khoaHoc;
    }

    @Override
    public List<KhoaHocModel> timTaCaTheoKeyVaGiaoVienId(Pageble pageble, String key, Long id) {
        Integer limit = pageble.getLimit();
        pageble.setLimit(null);
        key = StringUtil.chuyenMa(key).toLowerCase();

        List<KhoaHocModel> tatCaKhoaHoc= khoaHocDAO.timTatCa(pageble);
        List<KhoaHocModel> khoaHoc = new ArrayList<>();
        for(int i=0;i<tatCaKhoaHoc.size();i++){
            if(tatCaKhoaHoc.get(i).getMaKhoaHoc().indexOf(key)!=-1 && tatCaKhoaHoc.get(i).getGiaoVienId()==id){
                khoaHoc.add(tatCaKhoaHoc.get(i));
            }
        }
        tatCaKhoaHoc.clear();
        tatCaKhoaHoc.addAll(khoaHoc);
        khoaHoc.clear();
        this.tongSoKhoaHocTheoKey = tatCaKhoaHoc.size();
        pageble.setLimit(limit);
        for(int i=pageble.getOffset();i<pageble.getOffset()+limit && i<tatCaKhoaHoc.size();i++){
            khoaHoc.add(tatCaKhoaHoc.get(i));
        }
        return khoaHoc;
    }

    @Override
    public List<KhoaHocModel> timTatCaTheoHocVienId(Pageble pageble, Long id) {
        return khoaHocDAO.timTatCaTheoHocVienId(pageble, id);
    }

    @Override
    public List<KhoaHocModel> timTatCaTheoGiaoVienId(Pageble pageble, Long id) {
        return khoaHocDAO.timTatCaTheoGiaoVienId(pageble,id);
    }

    @Override
    public List<KhoaHocModel> timKhoaHocMoi() {
        return khoaHocDAO.timKhoaHocMoi();
    }

    @Override
    public List<KhoaHocModel> timKhoaHocHot() {
        return khoaHocDAO.timKhoaHocHot();
    }

    @Override
    public KhoaHocModel timMot(Long id) {
        return khoaHocDAO.timMot(id);
    }

    @Override
    public void xoa(long[] ids) {
        for (Long id : ids){
            List<Long> listBaiGiang = chiTietKhoaHocService.timBaiGiangTheoKhoaHocId(id);
            long[] baigiang = new long[listBaiGiang.size()];
            for(int i =0;i<listBaiGiang.size();i++){
                baigiang[i] = listBaiGiang.get(i);
            }

            int soLuongHocVienTrongKhoaHoc = hocVienService.timTatCaTheoKhoaHocId(id).size();
            if (soLuongHocVienTrongKhoaHoc == 0) {
                baiGiangService.xoa(baigiang);
                khoaHocDAO.xoa(id);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }

    @Override
    public KhoaHocModel capNhat(KhoaHocModel model, Part filePart) {
        try{
            File fileSaveDir = new File(SystemConstant.PATHIMAGE);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            filePart.write( SystemConstant.PATHIMAGE+ filePart.getSubmittedFileName());
        }catch (IOException e) {
            return null;
        }

        KhoaHocModel oldModel = khoaHocDAO.timMot(model.getId());
        model.setThoiGianTao(oldModel.getThoiGianTao());
        model.setMaKhoaHoc(StringUtil.chuyenMa(model.getTenKhoaHoc()));
        model.setAnh(filePart.getSubmittedFileName());
        khoaHocDAO.capNhat(model);
        return khoaHocDAO.timMot(model.getId());
    }

    @Override
    public int demTongSoKhoaHoc(Long id) {
        return khoaHocDAO.demTongSoKhoaHoc(id);
    }

    @Override
    public int demTongSoKhoaHocTheoKey() {
        return (this.tongSoKhoaHocTheoKey==null? 0 : this.tongSoKhoaHocTheoKey);
    }

    @Override
    public KhoaHocModel luu(KhoaHocModel model, Part filePart) {
        try{
            File pathImage = new File(SystemConstant.PATHIMAGE);
            if(!pathImage.exists()){
                pathImage.mkdirs();
            }
            filePart.write( SystemConstant.PATHIMAGE + filePart.getSubmittedFileName());
        }catch (IOException e){
            return null;
        }
        model.setAnh(filePart.getSubmittedFileName());
        model.setThoiGianTao(new Timestamp(System.currentTimeMillis()));
        model.setMaKhoaHoc(StringUtil.chuyenMa(model.getTenKhoaHoc()));
        Long id = khoaHocDAO.luu(model);
        return khoaHocDAO.timMot(id);
    }
}
