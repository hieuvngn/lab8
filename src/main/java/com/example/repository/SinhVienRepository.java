package com.example.repository;

import com.example.model.SinhVien;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository class for managing Student data
 * Using in-memory storage for demonstration
 */
@ApplicationScoped
public class SinhVienRepository implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Map<String, SinhVien> database;
    
    @PostConstruct
    public void init() {
        database = new ConcurrentHashMap<>();
        
        // Add some sample data
        SinhVien sv1 = new SinhVien("SV001", "Nguyen Van A", "vana@example.com");
        sv1.setDienThoai("0901234567");
        sv1.setDiaChi("Ha Noi");
        sv1.setLop("CNTT-K65");
        
        SinhVien sv2 = new SinhVien("SV002", "Tran Thi B", "thib@example.com");
        sv2.setDienThoai("0912345678");
        sv2.setDiaChi("Ho Chi Minh");
        sv2.setLop("CNTT-K65");
        
        SinhVien sv3 = new SinhVien("SV003", "Le Van C", "vanc@example.com");
        sv3.setDienThoai("0923456789");
        sv3.setDiaChi("Da Nang");
        sv3.setLop("CNTT-K65");
        
        database.put(sv1.getMaSV(), sv1);
        database.put(sv2.getMaSV(), sv2);
        database.put(sv3.getMaSV(), sv3);
    }
    
    public List<SinhVien> findAll() {
        return new ArrayList<>(database.values());
    }
    
    public SinhVien findById(String maSV) {
        return database.get(maSV);
    }
    
    public SinhVien save(SinhVien sinhVien) {
        if (sinhVien.getMaSV() != null && !sinhVien.getMaSV().isEmpty()) {
            database.put(sinhVien.getMaSV(), sinhVien);
        } else {
            // Generate new ID
            String newId = generateId();
            sinhVien.setMaSV(newId);
            database.put(newId, sinhVien);
        }
        return sinhVien;
    }
    
    public SinhVien update(String maSV, SinhVien sinhVien) {
        if (database.containsKey(maSV)) {
            sinhVien.setMaSV(maSV);
            database.put(maSV, sinhVien);
            return sinhVien;
        }
        return null;
    }
    
    public boolean delete(String maSV) {
        return database.remove(maSV) != null;
    }
    
    public List<SinhVien> searchByKeyword(String keyword) {
        List<SinhVien> result = new ArrayList<>();
        for (SinhVien sv : database.values()) {
            if (sv.getHoTen().toLowerCase().contains(keyword.toLowerCase()) ||
                sv.getMaSV().toLowerCase().contains(keyword.toLowerCase()) ||
                sv.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(sv);
            }
        }
        return result;
    }
    
    private String generateId() {
        int count = database.size() + 1;
        return String.format("SV%03d", count);
    }
}
