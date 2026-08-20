package com.example.model;

import java.io.Serializable;

/**
 * Model class representing a Book (Sach)
 */
public class Sach implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String nhaXuatBan;
    private int namXuatBan;
    private double gia;
    private int soLuong;
    private String theLoai;
    
    public Sach() {
    }
    
    public Sach(String maSach, String tenSach, String tacGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
    }
    
    // Getters and Setters
    public String getMaSach() {
        return maSach;
    }
    
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
    
    public String getTenSach() {
        return tenSach;
    }
    
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
    
    public String getTacGia() {
        return tacGia;
    }
    
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
    
    public String getNhaXuatBan() {
        return nhaXuatBan;
    }
    
    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }
    
    public int getNamXuatBan() {
        return namXuatBan;
    }
    
    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }
    
    public double getGia() {
        return gia;
    }
    
    public void setGia(double gia) {
        this.gia = gia;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getTheLoai() {
        return theLoai;
    }
    
    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
    
    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacGia='" + tacGia + '\'' +
                '}';
    }
}
