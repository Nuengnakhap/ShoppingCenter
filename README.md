# ShoppingCenter
## Introduction
Shopping Center เป็นระบบที่ให้บริการในด้านของการซื้อ-ขายสินค้า ซึ่งจะทำหน้าที่ในการให้ร้านค้าแต่ร้านมาลงทะเบียนกับะบบและขายสินค้าของร้านค้าตนเอง และให้ลูกค้ามาซื้อสินค้าของแต่ละร้านได้
## User role
  - ระบบนี้จะแบ่งผู้ใช้งานเป็น 2 ฝ่าย
    - ฝ่ายลูกค้า สามารถที่จะซื้อสินค้าของแต่ละร้านค้า และสามารถให้คะแนนกับสินค้าที่ได้ซื้อไปแล้ว
    - ฝ่ายร้านค้า สามารถลงทะเบียนกับระบบเพื่อขายสินค้าของร้านค้าตนเอง และจัดการสินค้าของร้านตนเองได้
## Features
 - Account Service -> ทำหน้าที่ ให้ผู้ใช้ลงทะเบียนหรือเข้าสู่ระบบเพื่อเข้าใช้งาน
 - Shopping Service -> ทำหน้าที่ แสดงร้านค้า สินค้า รายละเอียดของสินค้า
 - Order Service -> ทำหน้าที่ ให้ผู้ใช้สั่งซื้อสินค้า
 - ShopRegister Service -> ทำหน้าที่ ให้ร้านค้ามาลงทะเบียนกับระบบ และจัดการสินค้าร้านค้าของตนเอง
 - Biling Service -> เมื่อผู้ใช้ชำระสินค้า จะทำหน้าที่เก็บรายละเอียดของการชำระสินค้า
 - Rating Service -> ทำหหน้าที่ให้ผู้ใช้ได้ให้คะเนนกับสินค้าที่ตนเองได้ซื้อไปแล้ว และวิเคราห์คะแนนของแต่ละร้านค้า
<img src="./gitImg/MicroService.png">

## Presentation
  <a href="./Shopping Center.pptx">Shopping Center</a>

## How to use
### Auth Service
  - In this repo -> https://github.com/Nuengnakhap/Microservice-Oauth2/tree/master/AuthorizationService
### Account
  - POST http://onezlinks.jvmhost.net/shop/customer สมัครสมาชิก
    - {
	      "email": "",
	      "firstName": "",
	      "lastName": "",
	      "phone": "",
	      "address": "",
	      "street": "",
	      "city": "",
	      "state": "",
	      "zipCode": "",
	      "password": ""
      }
  - POST http://onezlinks.jvmhost.net/auth-service/login เข้าสู่ระบบ
    - Basic Auth -> Username: cilent_id || Password: client_password
    - Body with x-www-form-urlencoded -> username: "Your email", password: "Your password", grant_type: "password"
### Product
  - GET http://onezlinks.jvmhost.net/shop/category ดูหมวดหมู่ของสินค้าทั้งหมด
  - GET http://onezlinks.jvmhost.net/shop/product ดูสินค้าทั้งหมด
### Store
  - GET http://onezlinks.jvmhost.net/shop/store ดูร้านค้าทั้งหมด
  - GET http://onezlinks.jvmhost.net/shop/store/1/product ดูสินค้าของร้านค้าทั้งหมด
  - POST with Token http://onezlinks.jvmhost.net/shop/store/1 สร้างสินค้าของร้านค้า
    - [{
        "name": "",
        "stock_quantity": 0,
        "price": 0,
        "images": [],
        "category_id": 1
      }]
  - PUT with OAuth Token http://onezlinks.jvmhost.net/shop/store/1/product/1 แก้ไขสินค้าในร้านค้า
    - {
        "name": "",
        "stock_quantity": 0,
        "price": 0,
        "images": [],
        "category_id": 1
      }
### Order
  - GET with token http://onezlinks.jvmhost.net/shop/order ดูภาพรวมรายการการสั่งซื้อของตัวเองทั้งหมด
  - GET with token http://onezlinks.jvmhost.net/shop/order/1 ดูรายละเอียดของรายการการสั่งซื้อของตัวเอง
  - POST with token http://onezlinks.jvmhost.net/shop/order สร้างรายการการสั่งซื้อของตัวเอง
    - [
        {
          "product_id": 3,
          "quantity": 10
        }
      ]
  - DELETE with token http://onezlinks.jvmhost.net/shop/order/1 ลบรายการการสั่งซื้อของตัวเอง
### Billing
  - GET with token http://onezlinks.jvmhost.net/shop/billing ดูรายการที่ชำระเงินแล้วของตัวเอง
  - POST with token http://onezlinks.jvmhost.net/shop/billing สร้างรายการการชำระเงิน
    - {
        "order_id": 1
      }
### Rating
  - GET with token http://onezlinks.jvmhost.net/shop/rating ดูรายการที่ตัวเองเคยให้คะแนนกับสินค้า
  - POST with token http://onezlinks.jvmhost.net/shop/rating ให้คะแนนกับสินค้าที่ตัวเองซื้อ
    - {
        "stars": 4.5,
        "billing_id": 1,
        "orderDetail_id": 1
      }
