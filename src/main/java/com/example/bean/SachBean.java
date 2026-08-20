package com.example.bean;

import com.example.model.Sach;
import com.example.repository.SachRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean for Book operations
 */
@ManagedBean(name = "sachBean")
@RequestScoped
public class SachBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private SachRepository sachRepository;
    
    private Sach sach = new Sach();
    private List<Sach> danhSachSach;
    private String searchKeyword;
    private String selectedTheLoai;
    
    public String loadDanhSach() {
        danhSachSach = sachRepository.findAll();
        return "/sach-list.xhtml?faces-redirect=true";
    }
    
    public String addSach() {
        try {
            sachRepository.save(sach);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Thêm thành công", "Sách đã được thêm vào danh sách"));
            sach = new Sach();
            return "/sach-list.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Thêm thất bại", "Có lỗi xảy ra: " + e.getMessage()));
            return null;
        }
    }
    
    public String editSach(String maSach) {
        sach = sachRepository.findById(maSach);
        if (sach != null) {
            return "/sach-form.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    public String updateSach() {
        try {
            sachRepository.update(sach.getMaSach(), sach);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Cập nhật thành công", "Thông tin sách đã được cập nhật"));
            return "/sach-list.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Cập nhật thất bại", "Có lỗi xảy ra: " + e.getMessage()));
            return null;
        }
    }
    
    public String deleteSach(String maSach) {
        try {
            sachRepository.delete(maSach);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Xóa thành công", "Sách đã được xóa khỏi danh sách"));
            return "/sach-list.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Xóa thất bại", "Có lỗi xảy ra: " + e.getMessage()));
            return null;
        }
    }
    
    public String search() {
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            danhSachSach = sachRepository.findAll();
        } else {
            danhSachSach = sachRepository.searchByKeyword(searchKeyword);
        }
        return "/sach-list.xhtml?faces-redirect=true";
    }
    
    public String filterByTheLoai() {
        if (selectedTheLoai == null || selectedTheLoai.isEmpty()) {
            danhSachSach = sachRepository.findAll();
        } else {
            danhSachSach = sachRepository.findByTheLoai(selectedTheLoai);
        }
        return "/sach-list.xhtml?faces-redirect=true";
    }
    
    // Getters and Setters
    public Sach getSach() {
        return sach;
    }
    
    public void setSach(Sach sach) {
        this.sach = sach;
    }
    
    public List<Sach> getDanhSachSach() {
        if (danhSachSach == null) {
            danhSachSach = sachRepository.findAll();
        }
        return danhSachSach;
    }
    
    public void setDanhSachSach(List<Sach> danhSachSach) {
        this.danhSachSach = danhSachSach;
    }
    
    public String getSearchKeyword() {
        return searchKeyword;
    }
    
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    
    public String getSelectedTheLoai() {
        return selectedTheLoai;
    }
    
    public void setSelectedTheLoai(String selectedTheLoai) {
        this.selectedTheLoai = selectedTheLoai;
    }
}
