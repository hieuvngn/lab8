# Tổng quan cách làm Project Lab 8 - JSF

## Mục tiêu
Chuyển đổi form từ JSP/Servlet sang JSF (JavaServer Faces), thêm validation và hiển thị message.

## Cấu trúc project

```
lab8-jsf/
├── pom.xml                          # Maven config với JSF, PrimeFaces dependencies
├── src/main/
│   ├── java/com/example/
│   │   ├── model/                   # Các class dữ liệu (POJO)
│   │   │   ├── SinhVien.java        # Model sinh viên
│   │   │   └── Sach.java            # Model sách
│   │   ├── repository/              # Lớp xử lý dữ liệu (CRUD)
│   │   │   ├── SinhVienRepository.java
│   │   │   └── SachRepository.java
│   │   └── bean/                    # Managed Beans (xử lý logic)
│   │       ├── SinhVienBean.java
│   │       ├── SachBean.java
│   │       └── LoginBean.java
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml              # Cấu hình FacesServlet
│       │   └── templates/
│       │       └── layout.xhtml     # Template layout chung
│       ├── resources/css/style.css
│       ├── index.xhtml              # Trang chủ
│       ├── sinhvien-form.xhtml      # Form thêm/sửa sinh viên
│       ├── sinhvien-list.xhtml      # Danh sách sinh viên
│       ├── sach-form.xhtml          # Form thêm/sửa sách
│       ├── sach-list.xhtml          # Danh sách sách
│       └── login.xhtml              # Form đăng nhập
```

## Các bước thực hiện

### 1. Cấu hình Maven (pom.xml)
- Thêm dependencies: JSF, PrimeFaces, CDI
- Cấu hình packaging là `war`

### 2. Cấu hình web.xml
- Khai báo `FacesServlet` để xử lý các request `.xhtml`
- Thiết lập project stage là `Development`

### 3. Tạo Model (POJO)
- Tạo các class Java đại diện cho dữ liệu (SinhVien, Sach)
- Có thể thêm annotation validation như `@NotBlank`, `@Email`, `@Size`

### 4. Tạo Repository
- Lớp quản lý dữ liệu trong memory (List)
- Implement các phương thức CRUD: `findAll()`, `add()`, `delete()`, `update()`, `search()`

### 5. Tạo Managed Bean
- Annotation `@Named` để đặt tên bean
- Annotation scope: `@SessionScoped`, `@ViewScoped`
- Properties để bind với UI components
- Methods xử lý action: `save()`, `delete()`, `edit()`, `search()`
- Sử dụng `FacesMessage` để hiển thị thông báo

### 6. Tạo trang XHTML
- Sử dụng JSF taglibs: `<h:form>`, `<h:inputText>`, `<h:commandButton>`, `<h:dataTable>`
- Bind data với bean qua Expression Language: `#{bean.property}`
- Thêm validation: `required="true"`, `<f:validateLength>`, `<f:validateRegex>`
- Hiển thị lỗi: `<h:message>`, `<p:messages>`

### 7. Tạo layout dùng chung
- Sử dụng `<ui:composition>` và `<ui:define>`
- Tạo template với header, menu, footer

## So sánh Servlet/JSP vs JSF

| Tiêu chí | Servlet/JSP | JSF |
|----------|-------------|-----|
| Mô hình | MVC thủ công | MVC tự động |
| Validation | Thủ công trong servlet | Built-in validators |
| State management | Thủ công (session/request) | Tự động (view/session scope) |
| Navigation | Hard-coded URLs | Navigation outcomes |
| Event handling | Xử lý form thủ công | Event-driven |

## Cách chạy project

1. Cài đặt Maven và Java EE server (GlassFish, Payara, WildFly)
2. Build: `mvn clean package`
3. Deploy file `.war` lên server
4. Truy cập: `http://localhost:8080/lab8-jsf/index.xhtml`
5. Đăng nhập demo: admin/admin

## Công nghệ sử dụng
- Java EE / Jakarta EE
- JSF 2.3 (JavaServer Faces)
- PrimeFaces 10.0
- Maven
- CDI (Context and Dependency Injection)
