Markdown
# 🎁 Taşcı Hediyelik ve Mutluluk | Spring Boot & Security Final Projesi

Bu proje, modern bir e-ticaret (Shopify) arayüzüne sahip, Spring Boot ve Spring Security altyapısı ile güçlendirilmiş, katmanlı mimari (Layered Architecture) prensiplerine uygun olarak geliştirilmiş bir **Butik Hediye & Takı Otomasyonu** projesidir.

Projede **15 benzersiz ürün** veritabanından dinamik olarak çekilmekte, ön yüzde gelişmiş koleksiyon filtreleme ve kuruşu kuruşuna hesaplama yapan akıllı bir alışveriş sepeti motoru barındırmaktadır.

---

## 🛠️ Kullanılan Teknolojiler & Mimari Yapı

### Backend (Arka Plan)
* **Java 17 / Spring Boot:** Projenin ana çekirdeği ve backend motoru.
* **Spring Data JPA & Hibernate:** SQL yazmadan Nesne-İlişkisel Eşleme (ORM) sağlayan veritabanı yönetim katmanı.
* **H2 Database / MySQL:** Geliştirme ve test süreçleri için hafif ve hızlı gömülü veritabanı.
* **Spring Security:** Rol Tabanlı Erişim Denetimi (RBAC) ve endpoints güvenliği.

### Frontend (Ön Yüz)
* **HTML5 & CSS3:** Apple/Shopify esintili modern, minimalist ve duyarlı (responsive) arayüz tasarımı.
* **Vanilla JavaScript:** `Fetch API` ile asenkron veri transferi, dinamik DOM manipülasyonu ve istemci tabanlı sepet/filtreleme lojikleri.

---

## 🏗️ Katmanlı Mimari (Layered Architecture) Şeması

Proje, yazılımda sürdürülebilirlik ve kod kalitesi açısından 4 ana katman üzerine inşa edilmiştir:

[ Ön Yüz / Tarayıcı (index.html) ]
│  ▲
▼  │ (HTTP Requests / JSON Data via Fetch API)
[ Controller Katmanı ]  --> Dış dünyayı ve istekleri karşılayan kapı.
│  ▲
▼  │
[ Service Katmanı ]     --> İş lojikleri, kurallar ve hesaplama merkezi.
│  ▲
▼  │
[ Repository Katmanı ]  --> Veritabanı ile doğrudan (SQL/JPA) konuşan katman.
│  ▲
▼  │
[ Entity / Model ]      --> Veritabanındaki tabloların Java dünyasındaki ikizleri.


---

## 📂 Proje Dosya Yapısı & Satır Satır Analiz

### 1. Model Katmanı (`GiftItem.java`)
Veritabanındaki `gift_items` tablosunu modeller.
* `@Entity`: Bu sınıfın bir veritabanı tablosu olduğunu Spring'e bildirir.
* `@Id` & `@GeneratedValue`: Her ürüne benzersiz otomatik artan bir anahtar (Primary Key) tanımlar.
* **Kapsülleme (Encapsulation):** Veriler `private` tutularak güvenli `Getter/Setter` metotları ile dışarı açılmıştır.

### 2. Veri Erişim Katmanı (`GiftItemRepository.java`)
* `extends JpaRepository<GiftItem, Long>` mirası sayesinde içerisine tek satır kod yazılmadan `findAll()`, `save()`, `delete()` gibi tüm kritik SQL fonksiyonları otomatik olarak yüklenir.

### 3. İş Mantığı Katmanı (`GiftItemService.java`)
* `@Service` anotasyonu ile işaretlenmiştir. Controller ile Repository arasında köprü görevi görür. `@Autowired` ile repository nesnesini güvenli bir şekilde enjekte eder (Dependency Injection) ve verileri Controller'a paslar.

### 4. Sunum/Web Katmanı (`GiftItemController.java`)
* `@RestController`: Dış dünyaya HTML yerine saf **JSON** verisi döneceğini belirtir.
* `@RequestMapping("/gifts")`: `http://localhost:8080/gifts` adresine gelen istekleri dinler.
* `@CrossOrigin(origins = "*")`: Tarayıcı tabanlı güvenlik engellerini (CORS) aşarak ön yüzün backend ile konuşmasını sağlar.

---

## 🚀 Öne Çıkan Fonksiyonel Özellikler

1. **Dinamik Ürün Vitrini:** `data.sql` üzerinden yüklenen 15 farklı takı ve hediye ürünü, JavaScript döngüsüyle asenkron olarak ekrana basılır.
2. **Akıllı Görsel Eşleştirme:** Ürünler listelenirken her ürünün ID'sine göre telif hakkı bulunmayan şık resimler atanır. **"Gümüş Halhal"** ürünü tespit edildiğinde, doğrudan hedeflenen özel telkari görseli dinamik olarak render edilir.
3. **Pürüzsüz Navigasyon & Rota Ayrımı:** * **Ana Sayfa** butonu aktif filtreleri sıfırlar ve sayfayı en üstteki giriş vitrinine kaydırır.
   * **Koleksiyonlar** butonu doğrudan filtreleme alanına nokta atışı odaklanır.
4. **Gelişmiş Koleksiyon Filtreleme:** Kullanıcı; *Kadın, Erkek, Gümüş, Altın, Bronz/Broş* butonlarına bastığında backend'e tekrar istek atıp sunucuyu yormadan, tarayıcı hafızasındaki veriyi saliseler içinde süzer.
5. **Shopify Tarzı Kayar Sepet (Drawer) & Dinamik Fiyatlandırma:**
   * Sepete eklenen ürünlerin miktarı dinamik artırılıp azaltılabilir.
   * **Ara Toplam**, **Kargo Ücreti** ve **Genel Toplam** anlık hesaplanır.
   * *1500 TL üzeri alışverişlerde kargo ücreti (75 TL) otomatik sıfırlanarak "Bedava" olarak yansıtılır.*

---

## 🛠️ Kurulum ve Çalıştırma Talimatları

1. **Projeyi Klonlayın veya Açın:** Proje dosyalarını IntelliJ IDEA veya Eclipse IDE üzerine aktarın.
2. **Veritabanını Kontrol Edin:** `src/main/resources/data.sql` dosyasında 15 ürünlük INSERT komutlarının hazır olduğundan emin olun.
3. **Uygulamayı Başlatın:** `TaskmanagerApplication.java` (veya ana Spring Boot sınıfı) üzerinden projeyi **Run** edin.
4. **Tarayıcıdan Erişin:** Tarayıcınızın adres çubuğuna şu URL'i yazarak uygulamayı gizli sekmede test edin:
   ```http
   http://localhost:8080/index.html