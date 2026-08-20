package com.example.bean;

import com.example.model.SinhVien;
import com.example.repository.SinhVienRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean for Student operations
 */
@ManagedBean(name = "sinhVienBean")
@RequestScoped
public class SinhVienBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private SinhVienRepository sinhVienRepository;
    
    private SinhVien sinhVien = new SinhVien();
    private List<SinhVien> danhSachSinhVien;
    private String searchKeyword;
    
    public SinhVienBean() {
    }
    
    public String loadDanhSach() {
        danhSachSinhVien = sinhVienRepository.findAll();
        return "/sinhvien-list.xhtml?faces-redirect=true";
    }
    
    public String addSinhVien() {
        try {
            sinhVienRepository.save(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Thêm thành công", "Sinh viên đã được thêm vào danh sách"));
            sinhVien = new SinhVien();
            return "/sinhvien-list.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Thêm thất bại", "Có lỗi xảy ra: " + e.getMessage()));
            return null;
        }
    }
    
    public String editSinhVien(String maSV) {
        sinhVien = sinhVienRepository.findById(maSV);
        if (sinhVien != null) {
            return "/sinhvien-form.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    public String updateSinhVien() {
        try {
            sinhVienRepository.update(sinhVien.getMaSV(), sinhVien);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Cập nhật thành công", "Thông tin sinh viên đã được cập nhật"));
            return "/sinhvien-list.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Cập nhật thất bại", "Có lỗi xảy ra: " + e.getMessage()));
            return null;
        }
    }
    
    public String deleteSinhVien(String maSV) {
        try {
            sinhVienRepository.delete(maSV);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Xóa thành công", "Sinh viên đã được xóa khỏi danh sách"));
            return "/sinhvien-list.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Xóa thất bại", "Có lỗi xảy ra: " + e.getMessage()));
            return null;
        }
    }
    
    public String search() {
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            danhSachSinhVien = sinhVienRepository.findAll();
        } else {
            danhSachSinhVien = sinhVienRepository.searchByKeyword(searchKeyword);
        }
        return "/sinhvien-list.xhtml?faces-redirect=true";
    }
    
    // Getters and Setters
    public SinhVien getSinhVien() {
        return sinhVien;
    }
    
    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
    
    public List<SinhVien> getDanhSachSinhVien() {
        if (danhSachSinhVien == null) {
            danhSachSinhVien = sinhVienRepository.findAll();
        }
        return danhSachSinhVien;
    }
    
    public void setDanhSachSinhVien(List<SinhVien> danhSachSinhVien) {
        this.danhSachSinhVien = danhSachSinhVien;
    }
    
    public String getSearchKeyword() {
        return searchKeyword;
    }
    
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
