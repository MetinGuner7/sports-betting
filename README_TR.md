# Spor Bahis Uygulaması Projesi

Bu proje, kullanıcılara çeşitli spor etkinliklerine bahis yapma imkanı sunan modern bir Android uygulamasıdır. Jetpack Compose kullanılarak tamamen deklaratif bir UI ile geliştirilmiştir ve modern Android geliştirme prensiplerini takip eder.

## ✨ Temel Özellikler

* Kullanıcı girişi ve kayıt işlemleri
* Spor etkinliklerinin ve oranlarının listelendiği bülten görünümü
* Etkinlik detayları ve market/oran seçenekleri
* Bahis kuponu oluşturma ve yönetme
* Dinamik tema ve modern kullanıcı arayüzü
* Sayfalar arası akıcı geçiş animasyonları
* Firebase Analytics entegrasyonu

## 🛠️ Teknolojiler ve Kütüphaneler

* **Programlama Dili:** Kotlin
* **UI:** Jetpack Compose
* **Mimari Desen:** MVVM (Model-View-ViewModel) ve Clean Architecture prensipleri
* **Asenkron Programlama:** Kotlin Coroutines & Flow
* **Dependency Injection:** Hilt
* **Navigasyon:** Jetpack Navigation Compose
* **Veri Saklama (Yerel):** Jetpack DataStore (Proto DataStore tercih edilebilir)
* **Ağ İstekleri:** Retrofit & OkHttp (Projede API entegrasyonu varsa)
* **Analitik:** Firebase Analytics
* **Resim Yükleme:** Coil (Eğer kullanılıyorsa)
* **Test:** JUnit, Mockito, Turbine (Flow testleri için), Compose UI Testleri

## 🏛️ Mimari Yaklaşım

Proje, ölçeklenebilir, test edilebilir ve bakımı kolay bir yapı sunmak amacıyla katmanlı bir mimari ve MVVM tasarım desenini benimser. Clean Architecture prensiplerinden esinlenilmiştir.

### Katmanlar:

1.  **UI Katmanı (`presentation`):**
    * Jetpack Compose ile oluşturulmuş ekranları (Composable fonksiyonlar) ve UI bileşenlerini içerir.
    * Kullanıcı etkileşimlerini alır ve bunları ViewModel'a iletir.
    * ViewModel'dan gelen UI durumunu (state) gözlemleyerek arayüzü günceller.
    * Navigasyon mantığını yönetir.
    * **Ana Bileşenler:**
        * `Activity` (Genellikle tek bir `MainActivity`)
        * `Composable` Ekranlar (örn: `LoginScreen`, `BulletinListScreen`, `BasketScreen`)
        * `ViewModel`'lar (örn: `LoginViewModel`, `BulletinViewModel`, `BasketViewModel`)
        * `NavHost` ve navigasyon grafiği tanımları

2.  **Domain Katmanı (Opsiyonel ama önerilir, UseCase'ler aracılığıyla):**
    * Uygulamanın iş mantığını içerir. UI ve Data katmanlarından bağımsızdır.
    * `UseCase` (Interactor) sınıfları aracılığıyla belirli işlevleri yerine getirir.
    * Repository'leri orkestra eder.
    * Domain modellerini tanımlar.
    * **Ana Bileşenler:**
        * `UseCase`'ler (örn: `GetSportsUseCase`, `AddBetToBasketUseCase`, `LoginUserUseCase`)
        * Domain veri modelleri (Platformdan bağımsız saf Kotlin sınıfları)

3.  **Data Katmanı:**
    * Veri kaynaklarını (yerel ve uzak) yönetir ve soyutlar.
    * `Repository` deseni kullanılır.
    * Veri modellerini (DTO, Proto modelleri, Entity'ler) içerir.
    * **Ana Bileşenler:**
        * `Repository` arayüzleri ve implementasyonları (örn: `AuthRepository`, `BulletinRepository`, `BasketRepository`)
        * `DataSource`'lar:
            * `LocalDataSource` (örn: DataStore veya Room DAO kullanarak)
            * `RemoteDataSource` (örn: Retrofit servis arayüzleri kullanarak)
        * Veri eşleme (mapping) sınıfları (DTO'ları domain modellerine veya tam tersine dönüştürmek için).

## 📂 Proje Yapısı (Modül ve Paketler)

Proje, özellik bazlı (feature-based) ve katman bazlı (layer-based) paketleme stratejilerinin bir karışımını kullanır.

```
.
├── app (Ana uygulama modülü)
│   ├── src/main/java/com/sports/betting
│   │   ├── MainActivity.kt
│   │   ├── MainApplication.kt (HiltApplication)
│   │   ├── di (Hilt modülleri - App seviyesi)
│   │   ├── navigation (NavHost ve rota tanımları)
│   │   ├── ui (Genel UI temaları, MainApp Composable)
│   │
│   ├── auth (Kimlik doğrulama özelliği)
│   │   ├── di (Auth ile ilgili Hilt modülleri)
│   │   ├── data (AuthRepository, AuthLocalDataSource, AuthRemoteDataSource)
│   │   ├── domain (LoginUseCase, RegisterUseCase, AuthDomainModel)
│   │   └── ui
│   │       ├── login (LoginScreen, LoginViewModel)
│   │       └── register (RegisterScreen, RegisterViewModel)
│   │
│   ├── bulletin (Bülten özelliği)
│   │   ├── di
│   │   ├── data
│   │   ├── domain
│   │   └── ui
│   │       ├── list (BulletinListScreen, BulletinListViewModel)
│   │       └── detail (BulletinDetailScreen, BulletinDetailViewModel)
│   │
│   ├── basket (Sepet özelliği)
│   │   ├── di
│   │   ├── data (BasketRepository, BasketLocalDataSource - DataStore)
│   │   ├── domain (GetBasketItemsUseCase, AddBetToBasketUseCase)
│   │   └── ui (BasketScreen, BasketViewModel, component)
│   │
│   ├── splash (Açılış ekranı özelliği)
│   │   └── ui (SplashScreen, SplashViewModel)
│   │
│   ├── common (Tüm modüller/özellikler tarafından paylaşılan kodlar)
│   │   ├── base (BaseViewModel, BaseUseCase, Route arayüzü vb.)
│   │   ├── model (Genel veri modelleri)
│   │   └── util (Yardımcı fonksiyonlar, sabitler)
│   │
│   ├── designsystem (Uygulamanın tasarım sistemi)
│   │   ├── theme (Color.kt, Theme.kt, Type.kt, Shape.kt)
│   │   ├── component (AppButton, AppTextField gibi genel UI bileşenleri)
│   │   ├── icons (Özel ikonlar)
│   │   └── animations (Geçiş animasyonları)
│   │
│   ├── datastore (DataStore veya yerel veritabanı ile ilgili kodlar)
│   │   ├── di (DataStore Hilt modülü)
│   │   ├── model (Proto modelleri veya Entity'ler)
│   │   └── usecase (DataStore'a özel UseCase'ler - örn: SaveUserPreferencesUseCase)
│   │
│   └── analytics (Analitik ile ilgili kodlar)
│       ├── di (Analytics Hilt modülü)
│       └── AnalyticsHelper.kt, AnalyticsEvent.kt
│
└── build.gradle.kts (Proje seviyesi)
└── app/build.gradle.kts (Uygulama modülü seviyesi)
```

## 🔄 Önemli Akışlar

* **Kullanıcı Girişi:** `LoginScreen` -> `LoginViewModel` -> `LoginUserUseCase` -> `AuthRepository` -> (Remote/Local DataSource). Başarılı girişte ana ekrana (Bülten) yönlendirme.
* **Bülten Listeleme:** `BulletinListScreen` -> `BulletinListViewModel` -> `GetSportsUseCase` -> `BulletinRepository`.
* **Sepete Bahis Ekleme:** `BulletinDetailScreen` (veya `BulletinListScreen`) -> `BulletinDetailViewModel` -> `AddBetToBasketUseCase` (veya ViewModel içindeki çelişki çözme mantığı) -> `BasketRepository` -> `BasketLocalDataSource`. Sepet güncellemeleri `Flow` ile UI'a yansıtılır.
* **Sayfa Geçişleri:** `NavHost` ve tanımlı rotalar aracılığıyla, `designsystem.animations` içinde tanımlanan özel animasyonlar kullanılarak gerçekleştirilir.

## 🚀 Kurulum ve Çalıştırma

1.  Projeyi klonlayın: `git clone <repository_url>`
2.  Android Studio'nun en son stabil sürümünü açın.
3.  Projeyi Android Studio ile açın.
4.  Gerekli SDK ve araçların yüklü olduğundan emin olun.
5.  Firebase projesi oluşturup `google-services.json` dosyasını `app/` dizinine ekleyin (Firebase Analytics ve diğer Firebase servisleri için).
6.  Projeyi derleyip bir emülatörde veya fiziksel bir cihazda çalıştırın.

## 🎨 Tasarım Sistemi (`designsystem`)

Uygulama, tutarlı bir görünüm ve his sağlamak için merkezi bir tasarım sistemine sahiptir. Bu paket şunları içerir:
* **Tema:** Renkler, tipografi, şekiller (`MaterialTheme` üzerine kurulu).
* **Bileşenler:** Uygulama genelinde kullanılan özel `Composable` bileşenler (örn: `AppButton`, `AppTextField`).
* **İkonlar:** Özel SVG veya vektör ikonlar.
* **Animasyonlar:** Sayfa geçişleri ve diğer UI etkileşimleri için önceden tanımlanmış animasyon setleri.

## 📈 Analitik (`analytics`)

Kullanıcı davranışlarını ve uygulama performansını izlemek için Firebase Analytics entegrasyonu yapılmıştır. `AnalyticsHelper` arayüzü ve implementasyonları aracılığıyla standartlaştırılmış event gönderimi sağlanır.

---

Bu README dosyası, projenin temel yapısını ve mimarisini özetlemektedir. Daha detaylı bilgi için ilgili paket ve dosyalardaki kod içi belgelendirmelere başvurulabilir.
