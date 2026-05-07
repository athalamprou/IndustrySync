# IndustrySync: Full-Stack Maintenance Management

An Industrial IoT (IIoT) solution designed to bridge the gap between factory floor operations and cloud-based data management. This project features a robust **.NET 8 Web API** acting as the central intelligence for a **Kotlin Android** client and a **Supabase (PostgreSQL)** cloud database. 

*Inspired by the course "Special Topics in Digital Industrial Systems"*

---

## .NET Backend
The .NET 8 API handles the critical business logic, data and network security.

### Backend Technical Highlights:
* **Asynchronous Architecture:** Implemented `async/await` patterns across all Controller actions to ensure high-concurrency and non-blocking I/O operations.
* **Entity Framework Core:** Utilized EF Core with the **Npgsql** provider for seamless Object-Relational Mapping (ORM) to a remote PostgreSQL database.
* **Database Migrations:** Managed schema evolution through EF Core Migrations, ensuring data integrity across development and production environments.
* **Cross-Device Communication:** Configured **Kestrel** to bind to `0.0.0.0`, enabling a local industrial network to communicate with the server without requiring a public static IP.
* **Security & CORS:** Implemented custom **Cross-Origin Resource Sharing (CORS)** policies to authorize requests from mobile clients while maintaining server-side security.
* **API Documentation:** Integrated **Swagger/OpenAPI** to provide interactive documentation for testing and third-party integration.

---

## Mobile Client (Android)
The Android client serves as the technician's portal, built with a focus on performance and offline-to-online synchronization.

* **Networking:** Powered by **Retrofit2** and **Gson** for efficient JSON parsing.
* **UI/UX:** Features `RecyclerView`s with Material Design 3 components, color-coding tasks by priority (Critical/High/Medium/Low).
* **State Management:** Real-time UI updates triggered by server-side callbacks.

---

## Tech Stack

| Component | Technology |
| :--- | :--- |
| **Backend** | .NET 8, C#, ASP.NET Core |
| **Database** | PostgreSQL (Supabase Cloud) |
| **ORM** | Entity Framework Core (EF Core) |
| **Mobile** | Android Studio, Kotlin, XML |
| **API Client** | Retrofit2, OkHttp |

---

## Setup & Installation

### 1. Backend Setup (.NET)
1. Ensure you have the **.NET 8 SDK** installed on your machine.
2. Navigate to the API directory:
   ```powershell
   cd backend/IndustrySyncApi
3. Run the server:
   ```powershell
   dotnet run
**Note:** The server is configured to listen on `http://0.0.0.0:5105` to allow network access from external devices.

**2. Mobile Setup (Android)**
1. Open the `app` folder in Android Studio.

2. Locate `RetrofitClient.kt` and update the `BASE_URL` with your computer's local IP address:

Example: `http://192.168.1.99:5105/`

3. Ensure your Android device and PC are connected to the same Wi-Fi network.

4. Build and run the app on a physical device or emulator.
   
