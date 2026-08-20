package com.example.repository;

import com.example.model.Sach;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository class for managing Book data
 */
@ApplicationScoped
public class SachRepository implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Map<String, Sach> database;
    
    @PostConstruct
    public void init() {
        database = new ConcurrentHashMap<>();
        
        // Add some sample data
        Sach sach1 = new Sach("S001", "Java Programming", "James Gosling");
        sach1.setNhaXuatBan("O'Reilly Media");
        sach1.setNamXuatBan(2023);
        sach1.setGia(59.99);
        sach1.setSoLuong(50);
        sach1.setTheLoai("Công nghệ thông tin");
        
        Sach sach2 = new Sach("S002", "Clean Code", "Robert C. Martin");
        sach2.setNhaXuatBan("Prentice Hall");
        sach2.setNamXuatBan(2022);
        sach2.setGia(45.00);
        sach2.setSoLuong(30);
        sach2.setTheLoai("Công nghệ thông tin");
        
        Sach sach3 = new Sach("S003", "Design Patterns", "Gang of Four");
        sach3.setNhaXuatBan("Addison-Wesley");
        sach3.setNamXuatBan(2021);
        sach3.setGia(55.00);
        sach3.setSoLuong(25);
        sach3.setTheLoai("Công nghệ thông tin");
        
        database.put(sach1.getMaSach(), sach1);
        database.put(sach2.getMaSach(), sach2);
        database.put(sach3.getMaSach(), sach3);
    }
    
    public List<Sach> findAll() {
        return new ArrayList<>(database.values());
    }
    
    public Sach findById(String maSach) {
        return database.get(maSach);
    }
    
    public Sach save(Sach sach) {
        if (sach.getMaSach() != null && !sach.getMaSach().isEmpty()) {
            database.put(sach.getMaSach(), sach);
        } else {
            String newId = generateId();
            sach.setMaSach(newId);
            database.put(newId, sach);
        }
        return sach;
    }
    
    public Sach update(String maSach, Sach sach) {
        if (database.containsKey(maSach)) {
            sach.setMaSach(maSach);
            database.put(maSach, sach);
            return sach;
        }
        return null;
    }
    
    public boolean delete(String maSach) {
        return database.remove(maSach) != null;
    }
    
    public List<Sach> searchByKeyword(String keyword) {
        List<Sach> result = new ArrayList<>();
        for (Sach s : database.values()) {
            if (s.getTenSach().toLowerCase().contains(keyword.toLowerCase()) ||
                s.getTacGia().toLowerCase().contains(keyword.toLowerCase()) ||
                s.getMaSach().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }
    
    public List<Sach> findByTheLoai(String theLoai) {
        List<Sach> result = new ArrayList<>();
        for (Sach s : database.values()) {
            if (s.getTheLoai().equalsIgnoreCase(theLoai)) {
                result.add(s);
            }
        }
        return result;
    }
    
    private String generateId() {
        int count = database.size() + 1;
        return String.format("S%03d", count);
    }
}
