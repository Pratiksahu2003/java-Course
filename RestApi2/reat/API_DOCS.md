# REST API Documentation

Base URL: `http://localhost:8081`

Content-Type: `application/json`

---

## Users API

Base path: `/api/users`

### Data Models

#### CreateUserDto (request body for create)

| Field    | Type   | Required | Description      |
|----------|--------|----------|------------------|
| name     | string | yes      | User display name |
| email    | string | yes      | Unique email      |
| password | string | yes      | User password     |

#### UserDto (response / update body)

| Field     | Type     | Description                          |
|-----------|----------|--------------------------------------|
| id        | long     | User ID                              |
| name      | string   | User display name                    |
| email     | string   | Unique email                         |
| password  | string   | User password                        |
| createdAt | datetime | Created timestamp (ISO-8601)         |
| updatedAt | datetime | Last updated timestamp (ISO-8601)    |
| deletedAt | datetime | Soft-delete timestamp, or `null`     |
| isActive  | boolean  | Whether the user is active           |

---

### 1. Get All Users

```
GET /api/users
```

**Response:** `200 OK`

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "password": "secret123",
    "createdAt": "2026-09-06T10:00:00",
    "updatedAt": "2026-09-06T10:00:00",
    "deletedAt": null,
    "isActive": true
  }
]
```

---

### 2. Create User

```
POST /api/users
```

**Request body:**

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secret123"
}
```

**Response:** `200 OK` — returns the created `UserDto`

---

### 3. Get User by ID

```
GET /api/users/{id}
```

**Path parameters:**

| Name | Type | Description |
|------|------|-------------|
| id   | long | User ID     |

**Response:** `200 OK` — returns `UserDto`

**Error:** `500` if user not found (`ResourceNotFoundException`)

---

### 4. Update User

```
PUT /api/users/{id}
```

**Path parameters:**

| Name | Type | Description |
|------|------|-------------|
| id   | long | User ID     |

**Request body:** `UserDto`

```json
{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "newpassword",
  "isActive": true
}
```

**Response:** `200 OK` — returns updated `UserDto`

---

### 5. Delete User (Hard Delete)

```
DELETE /api/users/{id}
```

**Path parameters:**

| Name | Type | Description |
|------|------|-------------|
| id   | long | User ID     |

**Response:** `200 OK` (empty body)

---

### 6. Activate User

```
PATCH /api/users/{id}/activate
```

Sets `isActive` to `true` and clears `deletedAt`.

**Response:** `200 OK` — returns updated `UserDto`

---

### 7. Deactivate User

```
PATCH /api/users/{id}/deactivate
```

Sets `isActive` to `false`.

**Response:** `200 OK` — returns updated `UserDto`

---

### 8. Soft Delete User

```
PATCH /api/users/{id}/soft-delete
```

Sets `deletedAt` to current time and `isActive` to `false`. Record remains in the database.

**Response:** `200 OK` — returns updated `UserDto`

---

## Products API

Base path: `/api/products`

### Data Models

#### CreateProductDto (request body for create)

| Field       | Type   | Required | Description                    |
|-------------|--------|----------|--------------------------------|
| name        | string | yes      | Product name                   |
| description | string | no       | Product description            |
| price       | number | yes      | Product price                  |
| userId      | long   | yes      | ID of the owning user          |

#### ProductDto (response / update body)

| Field       | Type     | Description                          |
|-------------|----------|--------------------------------------|
| id          | long     | Product ID                           |
| name        | string   | Product name                         |
| description | string   | Product description                  |
| price       | number   | Product price                        |
| createdAt   | datetime | Created timestamp (ISO-8601)         |
| updatedAt   | datetime | Last updated timestamp (ISO-8601)    |
| deletedAt   | datetime | Soft-delete timestamp, or `null`     |
| isActive    | boolean  | Whether the product is active        |
| user        | UserDto  | Owner user details                   |

---

### 1. Get All Products

```
GET /api/products
```

**Response:** `200 OK`

```json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "Gaming laptop",
    "price": 999.99,
    "createdAt": "2026-09-06T10:00:00",
    "updatedAt": "2026-09-06T10:00:00",
    "deletedAt": null,
    "isActive": true,
    "user": {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "password": "secret123",
      "createdAt": "2026-09-06T10:00:00",
      "updatedAt": "2026-09-06T10:00:00",
      "deletedAt": null,
      "isActive": true
    }
  }
]
```

---

### 2. Create Product

```
POST /api/products
```

**Request body:**

```json
{
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 999.99,
  "userId": 1
}
```

**Response:** `200 OK` — returns the created `ProductDto`

**Error:** `500` if `userId` does not exist (`ResourceNotFoundException`)

---

### 3. Get Product by ID

```
GET /api/products/{id}
```

**Path parameters:**

| Name | Type | Description |
|------|------|-------------|
| id   | long | Product ID  |

**Response:** `200 OK` — returns `ProductDto`

**Error:** `500` if product not found (`ResourceNotFoundException`)

---

### 4. Update Product

```
PUT /api/products/{id}
```

**Path parameters:**

| Name | Type | Description |
|------|------|-------------|
| id   | long | Product ID  |

**Request body:** `ProductDto`

```json
{
  "name": "Laptop Pro",
  "description": "Updated gaming laptop",
  "price": 1299.99,
  "isActive": true,
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

**Response:** `200 OK` — returns updated `ProductDto`

---

### 5. Delete Product (Hard Delete)

```
DELETE /api/products/{id}
```

**Path parameters:**

| Name | Type | Description |
|------|------|-------------|
| id   | long | Product ID  |

**Response:** `200 OK` (empty body)

---

### 6. Activate Product

```
PATCH /api/products/{id}/activate
```

Sets `isActive` to `true` and clears `deletedAt`.

**Response:** `200 OK` — returns updated `ProductDto`

---

### 7. Deactivate Product

```
PATCH /api/products/{id}/deactivate
```

Sets `isActive` to `false`.

**Response:** `200 OK` — returns updated `ProductDto`

---

### 8. Soft Delete Product

```
PATCH /api/products/{id}/soft-delete
```

Sets `deletedAt` to current time and `isActive` to `false`. Record remains in the database.

**Response:** `200 OK` — returns updated `ProductDto`

---

## Quick Reference

| Resource | Method | Endpoint                        | Description        |
|----------|--------|---------------------------------|--------------------|
| Users    | GET    | `/api/users`                    | List all users     |
| Users    | POST   | `/api/users`                    | Create user        |
| Users    | GET    | `/api/users/{id}`               | Get user by ID     |
| Users    | PUT    | `/api/users/{id}`               | Update user        |
| Users    | DELETE | `/api/users/{id}`               | Hard delete user   |
| Users    | PATCH  | `/api/users/{id}/activate`      | Activate user      |
| Users    | PATCH  | `/api/users/{id}/deactivate`    | Deactivate user    |
| Users    | PATCH  | `/api/users/{id}/soft-delete`   | Soft delete user   |
| Products | GET    | `/api/products`                 | List all products  |
| Products | POST   | `/api/products`                 | Create product     |
| Products | GET    | `/api/products/{id}`            | Get product by ID  |
| Products | PUT    | `/api/products/{id}`            | Update product     |
| Products | DELETE | `/api/products/{id}`            | Hard delete product|
| Products | PATCH  | `/api/products/{id}/activate`   | Activate product   |
| Products | PATCH  | `/api/products/{id}/deactivate` | Deactivate product |
| Products | PATCH  | `/api/products/{id}/soft-delete`| Soft delete product|

---

## cURL Examples

### Create a user

```bash
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"password\":\"secret123\"}"
```

### Create a product

```bash
curl -X POST http://localhost:8081/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Laptop\",\"description\":\"Gaming laptop\",\"price\":999.99,\"userId\":1}"
```

### Soft delete a user

```bash
curl -X PATCH http://localhost:8081/api/users/1/soft-delete
```

### Get all products

```bash
curl http://localhost:8081/api/products
```

---

## Notes

- **Database:** PostgreSQL (`restapi` database on `localhost:5432`)
- **Server port:** `8081` (configured in `application.properties`)
- **Timestamps:** Returned as ISO-8601 local datetime strings (e.g. `2026-09-06T10:00:00`)
- **Relationships:** Each product belongs to one user via `userId` / `user`
- **Soft delete vs hard delete:** Soft delete keeps the record and sets `deletedAt` + `isActive=false`. Hard delete (`DELETE`) removes the record from the database.
- **Errors:** `ResourceNotFoundException` is thrown when a user or product ID is not found. A global exception handler is not yet configured, so these currently return HTTP 500.
