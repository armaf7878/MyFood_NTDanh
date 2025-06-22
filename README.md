# 🍽️ MyFood App – Đặt Đồ Ăn Nhanh Chóng & Thuận Tiện
MyFood là ứng dụng Android cho phép người dùng đặt đồ ăn trực tuyến từ các quán ăn đã liên kết. Ứng dụng hỗ trợ quản lý tài khoản, giỏ hàng, đơn hàng, xem chi tiết món ăn và tương tác với hệ thống qua API và cơ sở dữ liệu cục bộ.
---
## 🚀 Tính Năng Chính
- **🔐 Đăng ký & Đăng nhập người dùng**  
- **🏠 Giao diện Trang chủ với danh sách quán ăn**
- **📄 Xem chi tiết món ăn**
- **🛒 Thêm món ăn trong giỏ hàng**
- **📦 Đặt đơn hàng**
- **🌍 Tích hợp API lấy dữ liệu tỉnh/thành phố (provinceAPI)**
- **💾 Lưu dữ liệu với SQLite (Room-like custom database)**  
--- 
## 🧩 Cấu Trúc Thư Mục Chính

📦 MyFood_NgoThanhDanh
 ┗━ 📂 app
     ┣━ 📂 src/main
     ┃  ┣━ 📂 java/com/example/myfood_ngothanhdanh/
     ┃  ┃  ┣━ MainActivity.java               
     ┃  ┃  ┣━ 📂 ACTIVITY_NTDanh              // Giao diện người dùng
     ┃  ┃  ┃  ┣━ Login_NTDanh.java            // Điểm khởi đầu của app
     ┃  ┃  ┃  ┣━ Register_NTDanh.java
     ┃  ┃  ┃  ┣━ Home_NTDanh.java
     ┃  ┃  ┃  ┣━ Food_NTDanh.java
     ┃  ┃  ┃  ┣━ Food_Detail_NTDanh.java
     ┃  ┃  ┃  ┣━ Cart_NTDanh.java
     ┃  ┃  ┃  ┗━ Order_NTDanh.java
     ┃  ┃  ┣━ 📂 ADAPTER_NTDanh              // Adapter cho RecyclerView
     ┃  ┃  ┣━ 📂 API                         // Kết nối API
     ┃  ┃  ┣━ 📂 DAO_NTDanh                  // Data Access Object (SQLite)
     ┃  ┃  ┣━ 📂 Database_NTDanh            // Khởi tạo và quản lý database
     ┃  ┃  ┗━ 📂 Modle_NTDanh               // Các lớp dữ liệu (Model)
     ┃  ┣━ 📂 res
     ┃  ┃  ┣━ 📂 drawable                    // Hình ảnh món ăn, biểu tượng
     ┃  ┃  ┗━ 📂 layout                      // Layout XML cho từng màn hình
     ┃  ┗━ AndroidManifest.xml              // Cấu hình và khai báo activity
---
## 🛠️ Công Nghệ Sử Dụng
💻 Java + Android SDK

🧱 SQLite cho lưu trữ cục bộ

🌐 Retrofit cho API

🧩 RecyclerView + Adapter

🎨 Material Design Components

🛜 API hành chính Việt Nam (provinceAPI)
---
## ▶️ Hướng Dẫn Chạy Ứng Dụng
Clone repo: git clone https://github.com/your-username/MyFood_NgoThanhDanh.git
Lưu ý: Sau khi khởi chạy app lần đầu, những lần tiếp theo hãy comment các dòng:
      (30->49 Home_NTDanh.Java)
      (55->87 Food_NTDanh.Java)
---
## 👨‍💻 Tác Giả
Ngô Thành Danh – GitHub
---
## 📄 License
App này được phát triển phục vụ mục đích học tập và demo cá nhân.
---
