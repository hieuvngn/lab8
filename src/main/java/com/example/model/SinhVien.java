package com.example.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class representing a Student (SinhVien)
 */
public class SinhVien implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String maSV;
    private String hoTen;
    private String email;
    private String dienThoai;
    private String diaChi;
    private Date ngaySinh;
    private boolean gioiTinh; // true: Nam, false: Nu
    private String lop;
    
    public SinhVien() {
    }
    
    public SinhVien(String maSV, String hoTen, String email) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.email = email;
    }
    
    // Getters and Setters
    public String getMaSV() {
        return maSV;
    }
    
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDienThoai() {
        return dienThoai;
    }
    
    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public Date getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public boolean isGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public String getLop() {
        return lop;
    }
    
    public void setLop(String lop) {
        this.lop = lop;
    }
    
    @Override
    public String toString() {
        return "SinhVien{" +
                "maSV='" + maSV + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
