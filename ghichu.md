tạo database:
example503_amqp
example503_product
example503_order
example503_user // không chứa user
example503_common // chứa inventories, users
example503_inventory // không chứa inventory
example503_file

start theo thứ tự:

eureka - port: 8761
api-gateway - port: 8889

product-service - port: 8080
inventoryservice - port: 8082
orderservice - port: 8081 // hiện tại đang lỗi chưa làm
userservice - port: 8084

fileservice - port: 8086

---

# Hệ thống hoạt động như sau: api-gateway sẽ quản lý các service thay vì phải thay đổi port (khi dùng postman) cho mỗi service thì bây giờ dùng 1 port cho tất cả service (port của api-gateway: 8889)

lưu ý khi postman: POST ở trong body -> raw

Danh sách api:

--- user service
database lưu trong example503_common

# POST http://localhost:8889/user/register

raw: {
"email":"bichthai@gmail.com",
"firstName":"bt",
"lastName":"bichthai",
"password":"12345678"
}

# POST http://localhost:8889/user/login

raw: {
"email":"bichthai@gmail.com",
"password":"12345678"
}

# GET http://localhost:8889/user/me

Authorization: Bearer <accessToken>

--- product service

# GET http://localhost:8889/v1/categories

# POST http://localhost:8889/v1/categories

raw: {
"name":"iphone"
}

# GET http://localhost:8889/v1/products/getAll

# POST http://localhost:8889/v1/products

raw: {
"name":"iphone 15",
"unitPrice":40000000,
"categoryId":1,
"description":"mo ta iphone 15",
"quantityInStock":1000,
"imageUrl":"http://example.com/image-iphone15.png" // thay đường dẫn khác ở đây
}

--- inventory service

Phần inventory: database lưu trong example503_common

# GET http://localhost:8889/v1/inventories/getAll

# POST http://localhost:8889/v1/inventories/create

raw: {
"productId":"41ad2a3e-056b-4a55-abf2-187b8e6f5cd0",
"quantity": 2000
}

# POST http://localhost:8889/v1/inventories/isInStock

raw: [
{
"productId":"ecde7b8f-c60a-4134-a167-ce22db0a944a",
"quantity":500
},
{
"productId":"41ad2a3e-056b-4a55-abf2-187b8e6f5cd0",
"quantity":4000
}
]

# http://localhost:8889/user/getById/d735114d-644b-4d12-b438-ff21a32399f1 (lấy id ở "http://localhost:8889/user/me"->"userId"),(lấy token ở "http://localhost:8889/user/login"->"accessToken")

# GET http://localhost:8889/user/resetpassword

Body: form-data
Key:email
Value:bichthai2412@gmail.com
Và token

# PUT http://localhost:8889/user/update

{
"email":"bichthai@gmail.com",
"firstName":"bt",
"lastName":"bichthai",
"password":"12345678",
"profileImageURL":"http://example503.com/bichthai-profile.png"
} Và token

# Login lại http://localhost:8889/user/login với password mới

# PUT http://localhost:8889/user/updatePassword

{
"currentPassword":"ohFfPA2BKn",
"newPassword":"12345678"
} Và token mới

# PUT http://localhost:8889/v1/products/d6950cc3-4877-43f7-96bc-3d92438e0432 "lấy id ở post"

{
"name":"iphone 10",
"unitPrice":42000000,
"categoryId":1,
"description":"mo ta iphone 10",
"imageUrl":"http://example.com/image-iphone10.png"
}

# DELETE http://localhost:8889/v1/products/d6950cc3-4877-43f7-96bc-3d92438e0432 "lấy id ở post"

# POST http://localhost:8889/v1/comments

{
"productId":"1b0e0bdb-594c-4ec5-a2a1-4a6d7b739dac","id ở post"
"text":"comment 1 in postman"
}

# GET http://localhost:8889/v1/products/1b0e0bdb-594c-4ec5-a2a1-4a6d7b739dac/comments "lấy id ở # POST http://localhost:8889/v1/comments"

# POST http://localhost:8889/file/saveImage

Body: form-data
Key: file->File
Value: bỏ file vào

# DELETE http://localhost:8889/file/removeImage

Body: form-data
Key: file
Value: lấy tên từ link chạy được "087ddeb4-aabb-4571-8b34-2fcd665a380f.jpg"

# --- order service ---

# POST http://localhost:8889/v1/orders/create

Headers:
Authorization: Bearer <AccessToken>
userId: <UserId>
authorities: ROLE_USER

Body -> raw:
{
"address":{
"city":"vung tau",
"district":"ba ria vung tau",
"addressDetail":"24a Nguyen Gia thieu"
},
"items": [
{
"productId":"41ad2a3e-056b-4a55-abf2-187b8e6f5cd0",
"quantity": 7
},
{
"productId":"ecde7b8f-c60a-4134-a167-ce22db0a944a",
"quantity": 14
}
]

}

# GET http://localhost:8889/v1/orders/getAll

Headers: <AccessToken>
