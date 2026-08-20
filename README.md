# Lab 8 - Chuyển một form sang JSF, thêm validation và message

## Môn học: IT3242 Công nghệ Java

## Cấu trúc project đã thực hiện

```
lab8-jsf/
├── pom.xml                                    # Maven configuration với JSF, PrimeFaces
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── model/
│   │   │   │   ├── SinhVien.java              # Model sinh viên
│   │   │   │   └── Sach.java                  # Model sách
│   │   │   ├── repository/
│   │   │   │   ├── SinhVienRepository.java    # Repository sinh viên
│   │   │   │   └── SachRepository.java        # Repository sách
│   │   │   └── bean/
│   │   │       ├── SinhVienBean.java          # Managed Bean sinh viên
│   │   │       ├── SachBean.java              # Managed Bean sách
│   │   │       └── LoginBean.java             # Managed Bean đăng nhập
│   │   ├── resources/
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml                    # FacesServlet configuration
│   │       │   └── templates/
│   │       │       └── layout.xhtml           # Template layout chung
│   │       ├── resources/css/
│   │       │   └── style.css                  # CSS styles
│   │       ├── index.xhtml                    # Trang chủ
│   │       ├── sinhvien-form.xhtml            # Form sinh viên
│   │       ├── sinhvien-list.xhtml            # Danh sách sinh viên
│   │       ├── sach-form.xhtml                # Form sách
│   │       ├── sach-list.xhtml                # Danh sách sách
│   │       └── login.xhtml                    # Form đăng nhập
```

## Các bài tập đã hoàn thành

### ✅ Bài 1: Tạo trang JSF đầu tiên (index.xhtml)
- Sử dụng `<h:link>` để điều hướng
- Giao diện chào mừng với các link đến chức năng

### ✅ Bài 2: Tạo Model và Repository
- `SinhVien.java`: Model với các thuộc tính maSV, hoTen, email, dienThoai, diaChi, ngaySinh, gioiTinh, lop
- `SinhVienRepository.java`: Repository với các phương thức CRUD và search
- `Sach.java`: Model sách với các thuộc tính maSach, tenSach, tacGia, nhaXuatBan, namXuatBan, gia, soLuong, theLoai
- `SachRepository.java`: Repository sách với CRUD và filter theo thể loại

### ✅ Bài 3: Tạo Managed Bean
- `SinhVienBean.java`: @ManagedBean với các method add, edit, delete, search
- `SachBean.java`: Managed Bean cho sách với filter theo thể loại
- `LoginBean.java`: @SessionScoped bean cho đăng nhập/đăng xuất

### ✅ Bài 4: Chuyển form sang JSF có validation
- `sinhvien-form.xhtml`: Form với validations:
  - `required="true"`: Trường bắt buộc
  - `<f:validateLength>`: Độ dài tối thiểu/tối đa
  - `<f:validateRegex>`: Pattern cho email, số điện thoại
  - `<h:message>`: Hiển thị lỗi cho từng field
  - `<p:messages>`: Hiển thị message tổng quát

### ✅ Bài 5: Hiển thị danh sách bằng h:dataTable
- `sinhvien-list.xhtml`: DataTable với các cột STT, mã SV, họ tên, email, thao tác
- `sach-list.xhtml`: DataTable với filter theo thể loại sử dụng `<h:selectOneMenu>`

### ✅ Bài 6: Chuyển form Sách/Sản phẩm sang JSF
- `sach-form.xhtml`: Form thêm/sửa sách với validation
- `sach-list.xhtml`: Danh sách sách với tìm kiếm và lọc theo thể loại

### ✅ Bài 7: Tạo form đăng nhập
- `login.xhtml`: Form đăng nhập với username/password
- `LoginBean.java`: Xử lý đăng nhập/đăng xuất với session scope
- Tài khoản demo: admin/admin

### ✅ Bài 8: Bổ sung chức năng sửa sinh viên
- Method `editSinhVien()` trong SinhVienBean
- Link "Sửa" trong dataTable để chuyển đến form với dữ liệu đã điền

### ✅ Bài 9: Tìm kiếm sinh viên
- Method `search()` với từ khóa tìm kiếm
- Tìm kiếm theo maSV, hoTen, email

### ✅ Bài 10: Tạo layout dùng chung
- `WEB-INF/templates/layout.xhtml`: Template với header, footer, navigation
- Sử dụng `<ui:composition>` và `<ui:define>` cho các trang con

### ✅ Bài 11: Sử dụng selectOneMenu
- Trong `sach-form.xhtml`: Chọn thể loại sách
- Trong `sach-list.xhtml`: Lọc sách theo thể loại

## So sánh Servlet/JSP và JSF

| Tiêu chí | Servlet/JSP | JSF |
|----------|-------------|-----|
| **Mô hình** | MVC thủ công | MVC tự động |
| **Quản lý state** | Thủ công (session, request) | Tự động (view scope, session scope) |
| **Validation** | Thủ công trong servlet | Built-in validators |
| **Navigation** | Hard-coded URLs | Navigation rules, outcomes |
| **Component** | HTML thuần | UI Components phong phú |
| **Event handling** | Xử lý form thủ công | Event-driven programming |
| **Expression Language** | JSP EL đơn giản | JSF EL mạnh mẽ |
| **Ajax support** | Thủ công với JavaScript | Built-in Ajax support |

## Cách chạy project

1. Cài đặt Maven và Java EE server (GlassFish, Payara, hoặc WildFly)
2. Build project: `mvn clean package`
3. Deploy file `lab8-jsf.war` lên server
4. Truy cập: `http://localhost:8080/lab8-jsf/index.xhtml`

## Tài khoản demo
- Đăng nhập: username = `admin`, password = `admin`

## Công nghệ sử dụng
- Java EE / Jakarta EE
- JSF 2.3 (JavaServer Faces)
- PrimeFaces 10.0 (UI Components)
- Maven (Dependency Management)
- CDI (Context and Dependency Injection)
