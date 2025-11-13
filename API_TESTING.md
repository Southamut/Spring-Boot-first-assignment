# 📝 Note API Testing Guide

## Base URL
```
http://localhost:8080/api/notes
```

---

## 🔹 1. CREATE - สร้าง Note ใหม่

**Endpoint:** `POST /api/notes`

**Request Body:**
```json
{
  "title": "My First Note",
  "content": "This is the content of my first note.",
  "imageUrl": "https://example.com/image.jpg"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "title": "My First Note",
  "content": "This is the content of my first note.",
  "imageUrl": "https://example.com/image.jpg"
}
```

**cURL:**
```bash
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"My First Note\",\"content\":\"This is the content of my first note.\",\"imageUrl\":\"https://example.com/image.jpg\"}"
```

**PowerShell:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/notes" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"title":"My First Note","content":"This is the content of my first note.","imageUrl":"https://example.com/image.jpg"}'
```

---

## 🔹 2. READ ALL - ดึงข้อมูล Note ทั้งหมด

**Endpoint:** `GET /api/notes`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "title": "My First Note",
    "content": "This is the content of my first note.",
    "imageUrl": "https://example.com/image.jpg"
  },
  {
    "id": 2,
    "title": "Another Note",
    "content": "More content here.",
    "imageUrl": null
  }
]
```

**cURL:**
```bash
curl -X GET http://localhost:8080/api/notes
```

**PowerShell:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/notes" -Method GET
```

---

## 🔹 3. READ ONE - ดึงข้อมูล Note ตาม ID

**Endpoint:** `GET /api/notes/{id}`

**Example:** `GET /api/notes/1`

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "My First Note",
  "content": "This is the content of my first note.",
  "imageUrl": "https://example.com/image.jpg"
}
```

**cURL:**
```bash
curl -X GET http://localhost:8080/api/notes/1
```

**PowerShell:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/notes/1" -Method GET
```

---

## 🔹 4. UPDATE - แก้ไข Note ตาม ID

**Endpoint:** `PUT /api/notes/{id}`

**Example:** `PUT /api/notes/1`

**Request Body:**
```json
{
  "title": "Updated Note Title",
  "content": "Updated content goes here.",
  "imageUrl": "https://example.com/new-image.jpg"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "Updated Note Title",
  "content": "Updated content goes here.",
  "imageUrl": "https://example.com/new-image.jpg"
}
```

**cURL:**
```bash
curl -X PUT http://localhost:8080/api/notes/1 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Updated Note Title\",\"content\":\"Updated content goes here.\",\"imageUrl\":\"https://example.com/new-image.jpg\"}"
```

**PowerShell:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/notes/1" `
  -Method PUT `
  -ContentType "application/json" `
  -Body '{"title":"Updated Note Title","content":"Updated content goes here.","imageUrl":"https://example.com/new-image.jpg"}'
```

---

## 🔹 5. DELETE - ลบ Note ตาม ID

**Endpoint:** `DELETE /api/notes/{id}`

**Example:** `DELETE /api/notes/1`

**Response:** `204 No Content`

**cURL:**
```bash
curl -X DELETE http://localhost:8080/api/notes/1
```

**PowerShell:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/notes/1" -Method DELETE
```

---

## ⚠️ Error Responses

### Validation Error (400 Bad Request)
```json
{
  "timestamp": "2025-11-13T17:00:00",
  "status": 400,
  "errors": {
    "title": "Title is required"
  }
}
```

### Note Not Found (404 Not Found)
```json
{
  "timestamp": "2025-11-13T17:00:00",
  "status": 404,
  "message": "Note not found with id: 999"
}
```

---

## 🧪 ทดสอบด้วย Postman / Thunder Client

1. Import collection หรือสร้าง requests ตาม endpoints ข้างต้น
2. Set Base URL: `http://localhost:8080`
3. ทดสอบตามลำดับ: CREATE → READ ALL → UPDATE → DELETE

---

## 📊 Test Data Examples

**Example 1: Simple Note**
```json
{
  "title": "Shopping List",
  "content": "Milk, Eggs, Bread"
}
```

**Example 2: Note with Image**
```json
{
  "title": "Travel Plan",
  "content": "Visit Bangkok, Chiang Mai, Phuket",
  "imageUrl": "https://images.unsplash.com/photo-1552465011-b4e21bf6e79a"
}
```

**Example 3: Long Note**
```json
{
  "title": "Meeting Notes",
  "content": "Discussed Q4 strategy, team goals, and budget allocation. Action items: 1) Review proposals, 2) Schedule follow-up, 3) Update documentation.",
  "imageUrl": null
}
```

