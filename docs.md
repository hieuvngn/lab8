Lab 8 - Chuyển một form sang JSF, thêm validation và message
3. Cấu trúc project yêu cầu
Cấu trúc thư mục gợi ý
lab08-jsf-validation/
├── pom.xml
└── src/main/
├── java/vn/edu/eaut/lab8/
│ ├── bean/
│ │ └── SinhVienBean.java
│ ├── model/
│ │ └── SinhVien.java
│ └── repository/
│ └── SinhVienRepository.java
└── webapp/
├── index.xhtml
├── sinhvien-form.xhtml
├── sinhvien-list.xhtml
├── WEB-INF/web.xml
└── WEB-INF/beans.xml
4. File cấu hình Maven và FacesServlet
pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<groupId>vn.edu.eaut</groupId>
<artifactId>lab08-jsf-validation</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>war</packaging>
<properties>
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
<dependencies>
<dependency>
<groupId>org.glassfish</groupId>
<artifactId>jakarta.faces</artifactId>
<version>4.0.7</version>
</dependency>
<dependency>
<groupId>org.jboss.weld.servlet</groupId>
<artifactId>weld-servlet-shaded</artifactId>
<version>5.1.2.Final</version>
</dependency>
<dependency>
<groupId>org.hibernate.validator</groupId>
<artifactId>hibernate-validator</artifactId>
<version>8.0.1.Final</version>
</dependency>
</dependencies>
<build><finalName>lab08-jsf-validation</finalName></build>
</project>
WEB-INF/web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

Công nghệ Java | Lab 8 - Chương 3

Công nghệ Java - IT3242

xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
version="6.0">
<context-param>
<param-name>jakarta.faces.PROJECT_STAGE</param-name>
<param-value>Development</param-value>
</context-param>
<servlet>
<servlet-name>Faces Servlet</servlet-name>
<servlet-class>jakarta.faces.webapp.FacesServlet</servlet-class>
<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
<servlet-name>Faces Servlet</servlet-name>
<url-pattern>*.xhtml</url-pattern>
</servlet-mapping>
</web-app>
5. Bài tập có code gợi ý
Bài 1. Tạo trang JSF đầu tiên
Yêu cầu: tạo index.xhtml và kiểm tra ứng dụng chạy qua FacesServlet.
index.xhtml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:h="jakarta.faces.html">
<h:head><title>Lab 8 - JSF</title></h:head>
<h:body>
<h2>Lab 8 - Chuyển form sang JSF, validation và message</h2>
<h:link outcome="sinhvien-form" value="Mở form sinh viên JSF" />
<br/>
<h:link outcome="sinhvien-list" value="Xem danh sách sinh viên" />
</h:body>
</html>
Bài 2. Tạo Model và Repository dùng lại từ Lab 7
Yêu cầu: tạo SinhVien và SinhVienRepository. Dữ liệu vẫn lưu trong bộ nhớ bằng List để tập trung vào JSF.
SinhVien.java
package vn.edu.eaut.lab8.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class SinhVien {
private int id;
@NotBlank(message = "Mã sinh viên không được để trống")
private String maSinhVien;
@NotBlank(message = "Họ tên không được để trống")
@Size(min = 5, message = "Họ tên tối thiểu 5 ký tự")
private String hoTen;
@Email(message = "Email không đúng định dạng")
private String email;
@NotBlank(message = "Lớp không được để trống")
private String lop;
public SinhVien() {}
public SinhVien(int id, String maSinhVien, String hoTen, String email, String lop) {
this.id = id; this.maSinhVien = maSinhVien; this.hoTen = hoTen; this.email = email; this.lop = lop;
}
public int getId() { return id; }
public void setId(int id) { this.id = id; }
public String getMaSinhVien() { return maSinhVien; }
public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }
public String getHoTen() { return hoTen; }
public void setHoTen(String hoTen) { this.hoTen = hoTen; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getLop() { return lop; }
public void setLop(String lop) { this.lop = lop; }

Công nghệ Java | Lab 8 - Chương 3

Công nghệ Java - IT3242

}
SinhVienRepository.java
package vn.edu.eaut.lab8.repository;
import vn.edu.eaut.lab8.model.SinhVien;
import java.util.*;
public class SinhVienRepository {
private static final List<SinhVien> data = new ArrayList<>();
private static int autoId = 3;
static {
data.add(new SinhVien(1, "20240001", "Nguyễn Văn An", "an@gmail.com", "DCCNTT15.10.1"));
data.add(new SinhVien(2, "20240002", "Trần Thị Bình", "binh@gmail.com", "DCCNTT15.10.2"));
}
public List<SinhVien> findAll() { return data; }
public void add(SinhVien sv) { sv.setId(autoId++); data.add(sv); }
public void delete(int id) { data.removeIf(x -> x.getId() == id); }
}
Bài 3. Tạo Managed Bean xử lý form JSF
Yêu cầu: Bean nhận dữ liệu từ form, gọi repository để lưu và tạo message thông báo kết quả.
SinhVienBean.java
package vn.edu.eaut.lab8.bean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.SinhVien;
import vn.edu.eaut.lab8.repository.SinhVienRepository;
import java.io.Serializable;
import java.util.List;
@Named("sinhVienBean")
@SessionScoped
public class SinhVienBean implements Serializable {
private SinhVien sinhVien = new SinhVien();
private final SinhVienRepository repo = new SinhVienRepository();
public String save() {
repo.add(sinhVien);
FacesContext.getCurrentInstance().addMessage(null,
new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã lưu sinh viên"));
sinhVien = new SinhVien();
return null;
}
public void delete(int id) {
repo.delete(id);
FacesContext.getCurrentInstance().addMessage(null,
new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã xóa sinh viên"));
}
public List<SinhVien> getDsSinhVien() { return repo.findAll(); }
public SinhVien getSinhVien() { return sinhVien; }
public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }
}
Bài 4. Chuyển form JSP sang form JSF có validation và message
Yêu cầu: dùng h:form, h:inputText, h:message, h:messages. Khi nhập sai, JSF hiển thị lỗi; khi lưu đúng, hiển
thị thông báo thành công.
sinhvien-form.xhtml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:h="jakarta.faces.html">
<h:head><title>Form sinh viên JSF</title></h:head>
<h:body>
<h2>Form sinh viên bằng JSF</h2>
<h:messages globalOnly="true" style="color:green" />
<h:form>

Công nghệ Java | Lab 8 - Chương 3

Công nghệ Java - IT3242

<p>Mã SV:
<h:inputText id="ma" value="#{sinhVienBean.sinhVien.maSinhVien}" required="true"
requiredMessage="Mã sinh viên bắt buộc nhập" />
<h:message for="ma" style="color:red" />
</p>
<p>Họ tên:
<h:inputText id="hoten" value="#{sinhVienBean.sinhVien.hoTen}" />
<h:message for="hoten" style="color:red" />
</p>
<p>Email:
<h:inputText id="email" value="#{sinhVienBean.sinhVien.email}" />
<h:message for="email" style="color:red" />
</p>
<p>Lớp:
<h:inputText id="lop" value="#{sinhVienBean.sinhVien.lop}" />
<h:message for="lop" style="color:red" />
</p>
<h:commandButton value="Lưu sinh viên" action="#{sinhVienBean.save}" />
</h:form>
<p><h:link outcome="sinhvien-list" value="Xem danh sách" /></p>
</h:body>
</html>
Bài 5. Hiển thị danh sách bằng h:dataTable và xóa dữ liệu
Yêu cầu: thay bảng JSP/JSTL bằng h:dataTable của JSF. Nút xóa gọi trực tiếp method của Bean.
sinhvien-list.xhtml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:h="jakarta.faces.html">
<h:head><title>Danh sách sinh viên JSF</title></h:head>
<h:body>
<h2>Danh sách sinh viên</h2>
<h:messages globalOnly="true" style="color:green" />
<h:form>
<h:dataTable value="#{sinhVienBean.dsSinhVien}" var="sv" border="1" cellpadding="6">
<h:column><f:facet name="header">ID</f:facet>#{sv.id}</h:column>
<h:column><f:facet name="header">Mã SV</f:facet>#{sv.maSinhVien}</h:column>
<h:column><f:facet name="header">Họ tên</f:facet>#{sv.hoTen}</h:column>
<h:column><f:facet name="header">Email</f:facet>#{sv.email}</h:column>
<h:column><f:facet name="header">Lớp</f:facet>#{sv.lop}</h:column>
<h:column>
<f:facet name="header">Thao tác</f:facet>
<h:commandButton value="Xóa" action="#{sinhVienBean.delete(sv.id)}" />
</h:column>
</h:dataTable>
</h:form>
<p><h:link outcome="sinhvien-form" value="Thêm sinh viên" /></p>
</h:body>
</html>
Lưu ý sửa namespace
Trang sinhvien-list.xhtml dùng thẻ f:facet nên cần khai báo thêm namespace xmlns:f="jakarta.faces.core" ở thẻ
html.
6. Bài tập không có code gợi ý
Bài 6. Chuyển form Sách sang JSF
Tạo Sach, SachBean, sach-form.xhtml; validate tên sách không rỗng, tác giả không rỗng, năm xuất bản hợp lệ.
Bài 7. Chuyển form Sản phẩm sang JSF
Tạo ProductBean và form sản phẩm; validate tên không rỗng, giá > 0, số lượng >= 0; hiển thị message
lỗi/thành công.
Bài 8. Tạo form đăng nhập JSF
Tạo login.xhtml và LoginBean; nếu sai tài khoản hiển thị FacesMessage, nếu đúng chuyển đến index.xhtml.

Công nghệ Java | Lab 8 - Chương 3

Công nghệ Java - IT32421

Bài 9. Bổ sung chức năng sửa sinh viên bằng JSF
Từ bảng danh sách, chọn sinh viên cần sửa, đưa dữ liệu lên form, cập nhật lại danh sách.
Bài 10. Tìm kiếm sinh viên bằng JSF
Thêm ô keyword trên sinhvien-list.xhtml, lọc danh sách theo họ tên hoặc lớp.
Bài 11. Tạo layout dùng chung
Tạo header, menu, footer dùng chung cho các trang JSF. Có thể dùng ui:composition và ui:include.
Bài 12. Sử dụng selectOneMenu
Trong form sinh viên, trường lớp dùng h:selectOneMenu thay vì nhập text tự do.
Bài 13. Báo cáo so sánh Servlet/JSP và JSF
Viết bảng so sánh quy trình xử lý form, validation, message, điều hướng và mức độ tách mã giữa Lab 7 và Lab
8.