# Matcha - Platform Layanan Pendamping Profesional

Matcha adalah sebuah sistem perangkat lunak yang dirancang untuk menjembatani, mengotomatisasi, dan mengelola entitas bisnis jasa layanan pendamping profesional. Proyek ini dikembangkan sebagai Tugas Besar mata kuliah Pemrograman Berorientasi Objek (PBO) di Telkom University Surabaya.

## Latar Belakang
Di era digital, pergeseran gaya hidup memunculkan kebutuhan baru seperti rekan bermain game online (mabar), teman diskusi, hingga pendamping fisik untuk acara formal atau menonton bioskop. Matcha hadir untuk mengatasi kendala administratif seperti kesalahan jadwal (double-booking) dan ketidakjelasan transparansi tarif melalui implementasi paradigma Object-Oriented Programming (OOP).

## Struktur Proyek (Arsitektur MVC)
Proyek ini menggunakan struktur package yang rapi untuk memisahkan logika bisnis dan data:

* **matcha.model**: Berisi kelas entitas data dan interface (User, Client, Talent, Booking, IPayable, INotifiable, dll).
* **matcha.controller**: Berisi logika alur program (AuthController, BookingController, PaymentController, dll).
* **matcha.view**: Berisi antarmuka pengguna (Main menu).
* **matcha.util**: Berisi kelas pendukung atau utilitas.

## Anggota Kelompok dan Pembagian Tugas
Pengembangan Matcha dibagi ke dalam 6 modul utama yang dikerjakan oleh mahasiswa Informatika angkatan 2026:

1.  **Hendra Dirga Dwi Saputra (103072430018)**
    * Modul 1: Manajemen Pengguna dan Aktor (User, Client, Talent, AuthController).
2.  **Duarte Sebastian N. (103072400152)**
    * Modul 2: Manajemen Profil dan Layanan (Profile, Service, CatalogController).
3.  **Muhammad Chaesar Pratama (103072400119)**
    * Modul 3: Pemesanan dan Penjadwalan (Booking, Schedule, BookingController).
4.  **Didit Septa Putra (103072400071)**
    * Modul 4: Kalkulasi Tarif dan Transaksi (IPayable, Invoice, Payment, PaymentController).
5.  **Alif Luthfan Adeefa (103072400163)**
    * Modul 5: Ulasan dan Reputasi (Review, Rating, ReviewController).
6.  **Rochmatul Choirul Anam (103072400024)**
    * Modul 6: Sistem Notifikasi dan Status (INotifiable, Notification, NotificationManager).

## Persyaratan Sistem
* Java Development Kit (JDK) 8 atau yang lebih baru.
* IDE Apache NetBeans.
* Git (Opsional untuk kontrol versi).

## Cara Menjalankan
1.  Clone repositori ini ke komputer lokal.
2.  Buka proyek menggunakan Apache NetBeans.
3.  Pastikan semua package di folder src terdeteksi dengan benar.
4.  Jalankan file Main.java yang berada di package matcha.view.
