# 🧾 POS_Func-Production - Graduation Project

A Java-based Point of Sale (POS) system developed as a graduation project at FPT Polytechnic.
Built using a clean multi-layer architecture (DAO – DTO – Entity – UI), this system handles all retail operations like product management, invoicing, employee access, and revenue tracking.

---

## 🧠 Overview

- Built from scratch without drag-and-drop tools
- Designed for scalability and maintainability
- Developed by a dedicated team of students from FPT Polytechnic

---

## 👥 Team Members

- Nguyễn Quốc Anh *(a.k.a Nguyễn Quốc Trí – Team Lead / Core Developer)*
- Ngọc Thị Anh Thư *(UI/UX & Documentation)*
- Cao Hoàng Ý Nhi *(Product & Testing)*
- Trần Quang Huy *(Database Designer)*
- Vũ Đức Mạnh *(UI Support & Integration)*
- Đinh Diệu Thu *(Test Cases & Presentation Slides)*

> 👏 Each member contributed significantly to the success of this project.

---

## 🚀 Features

- 🔐 Login system with role-based access (Manager / Staff)
- 🧍 Employee management
- 🧾 Invoice creation, payment tracking
- 📦 Product and inventory management
- 📊 Revenue statistics and reporting
- 🔍 Real-time product search
- 🎁 Discount application support

---

## 📂 Project Structure

```
POS_Func-Production/
├── src/
│   ├── func/
│   │   ├── dao/             # DAO classes - interact directly with database (ProductDAO, InvoiceDAO, etc.)
│   │   ├── dto/             # Data Transfer Objects - pass data between layers (e.g., ProductDTO, InvoiceDTO)
│   │   ├── entity/          # Entity classes - mirror the structure of DB tables (e.g., Product, Employee)
│   │   ├── ui/              # Java Swing UI classes - all UI panels and forms (modular, not god-class)
│   │   ├── utils/           # Utility classes (e.g., Database connection handler, helper functions)
│   │   └── Main.java        # Application entry point (optional - or LoginUI.java is the real one)
│   └── ...                  # Other folders if present (e.g., test/ for unit tests)
├── database.sql             # SQL script to initialize schema (if included)
├── README.md                # Project documentation
├── build.xml                # Apache Ant build configuration (if used)
└── ...                      # Any additional config or resource folders
```

---

## 💻 Tech Stack

| Layer     | Tech                            |
|-----------|----------------------------------|
| Language  | Java 8                           |
| GUI       | Java Swing                       |
| Database  | SQL Server                       |
| Build     | Apache Ant / NetBeans Build Tool|
| IDE       | NetBeans 17                      |

---

## 🛠️ How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/quoctridev/POS_Func-Production.git
   ```

2. Open the project in NetBeans.

3. Set up the database:
   - Execute SQL scripts to create tables (if provided)
   - Update `Database.java` inside `func.utils` with your DB credentials

4. Run the application via `LoginUI.java`.

---

## 📸 Screenshots

> Add screenshots of:
> - Login page
> - Product panel
> - Invoice panel
> - Statistics chart

---

## 🔮 Future Plans

- Convert the system to a full-stack web app (Spring Boot + React)
- Write unit tests for DAO and services
- Add Excel/PDF export features
- Integrate barcode scanner and receipt printer

---

## 👤 Contact

**Nguyễn Quốc Anh (Nguyễn Quốc Trí)**  
📍 Hai Phong, Vietnam  
🎓 FPT Polytechnic – Software Development  
📧 quocanh1204.dev@gmail.com  
🔗 [https://github.com/quoctridev](https://github.com/quoctridev)  

<p>
  <a href="https://quoctri.dev">
    <img src="https://img.shields.io/badge/Website-000000?style=flat&logo=About.me&logoColor=white" alt="Website"/>
  </a>
  <a href="https://facebook.com/quoctris.dev/">
    <img src="https://img.shields.io/badge/Facebook-1877F2?style=flat&logo=facebook&logoColor=white" alt="Facebook"/>
  </a>
</p>

---

## 🇻🇳 Giới thiệu ngắn gọn bằng tiếng Việt

### 📚 Tên dự án:
**Hệ thống quản lý bán hàng POS_Func – Đồ án tốt nghiệp**

### 👨‍💻 Mô tả:
POS_Func là một ứng dụng quản lý bán hàng sử dụng Java Swing, kết nối SQL Server, được xây dựng theo mô hình nhiều lớp rõ ràng (DAO – DTO – Entity – UI).  
Ứng dụng phục vụ cho việc quản lý sản phẩm, hóa đơn, nhân viên, và thống kê doanh thu cho cửa hàng bán lẻ.

### 👥 Thành viên nhóm:
- Nguyễn Quốc Anh *(tên phụ: Nguyễn Quốc Trí – Trưởng nhóm, phụ trách chính về kiến trúc và lập trình)*
- Ngọc Thị Anh Thư *(Thiết kế giao diện, viết tài liệu)*
- Cao Hoàng Ý Nhi *(Kiểm thử sản phẩm và đánh giá nghiệp vụ)*
- Trần Quang Huy *(Thiết kế cơ sở dữ liệu)*
- Vũ Đức Mạnh *(Tích hợp giao diện và kết nối dữ liệu)*
- Đinh Diệu Thu *(Viết test case, làm slide thuyết trình)*

### 🔍 Chức năng chính:
- Đăng nhập & phân quyền (quản lý / nhân viên)
- Quản lý sản phẩm, loại sản phẩm
- Tạo và xử lý hóa đơn, cập nhật tồn kho tự động
- Quản lý nhân viên, thông tin khách hàng
- Tìm kiếm sản phẩm theo tên/mã
- Thống kê doanh thu theo ngày/tháng/năm
- Áp dụng khuyến mãi / chiết khấu

### ⚙️ Công nghệ sử dụng:
- Java 8 – Java Swing (giao diện)
- SQL Server – JDBC
- NetBeans IDE
- Mô hình nhiều tầng: UI ↔ DAO ↔ DTO ↔ Entity

### 🎯 Mục tiêu phát triển tiếp theo:
- Chuyển đổi lên phiên bản web với Spring Boot + ReactJS
- Viết unit test kiểm thử nghiệp vụ
- Tối ưu hiệu suất truy vấn
- Kết nối máy in hóa đơn và máy quét mã vạch

---

> 📌 Dự án được phát triển với tinh thần trách nhiệm cao và sự phối hợp chặt chẽ giữa các thành viên trong nhóm. Toàn bộ chức năng đều được lập trình thủ công nhằm hướng tới triển khai thực tế.
