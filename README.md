# FXCalendar

Kullanıcı Türleri:
- Admin
- Normal Kullanıcı

Normal Kullanıcıların Yetkileri:
- Kayıt Olma: Normal kullanıcılar uygulamaya kaydolabilirler.
- Giriş Yapma: Normal kullanıcılar kaydettikleri bilgileri kullanarak uygulamaya giriş yapabilirler.
- Takvim Görünümü Ekranına Erişim: Normal kullanıcılar ekledikleri olayları interaktif bir şekilde takvim şeklindeki arayüzden görebilirler.
- Olay Ekleme: Seçtikleri tarih için bir olay ekleyebilirler.
- Olay Güncelleme: Ekledikleri olayları güncelleyebilirler.
- Olay Silme: Ekledikleri olayları silebilirler.
- Zaman Uyarısı Alma: Ekledikleri olaylarda girdikleri zaman geldiğinde bip sesi ile uyarı alabilirler.

Admin'lerin Yetkileri:
- Kullanıcı Silme: Admin'ler diğer kullanıcıları uygulamadan silebilirler.

Notlar:
- Her normal kullanıcı sadece kendi eklediği olayları görüntüleyebilir, güncelleyebilir veya silebilir.
- Uyarıları alma, normal kullanıcılar standart olarak aktif bir şekilde gelen uyarı alma ile ayarladıkları zaman geldiğinde sesli bildirim alır.
- Admin'lerin silme işlemi geri alınamaz ve uyarı olmadan gerçekleşir. Bu nedenle, bu işlem özenli bir şekilde yapılmalıdır.

Gerekli Kütüphaneler:
- JLayer
