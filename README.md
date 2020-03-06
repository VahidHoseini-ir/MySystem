# MySystem
# system i set up in project to easy use of them

## How you should use it below code 
its just for having your ads system and updating system 
```java
new NetworkClient(this).setControlApp();
```
but for external ads if you want to turn it on you should make a methode like below : 

```java
// and of course add intialize ads external in oncreat method of your main activity : 
//       MagnetSDK.initialize(getApplicationContext());
//       MagnetSDK.getSettings();

    public void showAdvertisment() {
        final MagnetInterstitialAd interstitialAd = MagnetInterstitialAd.create(getApplicationContext());
        interstitialAd.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int price, String currency) {
                // این تابع برای میانی فراخوانی نمی‌شود.
            }

            @Override
            public void onReceive() {
                //زمانی که تبلیغ دریافت می‌شود این تابع فراخوانی می‌شود و از این زمان می‌توانید تبلیغ را نمایش دهید.
                interstitialAd.show();
            }

            @Override
            public void onFail(int errorCode, String errorMessage) {
                //زمانی که دریافت و نمایش تبلیغ به مشکل برمی‌خورد این تابع فراخوانی می‌شود .
            }

            @Override
            public void onClose() {
                // زمانی که تبلیغ توسط کاربر بسته می‌شود، این تابع فراخوانی می‌شود.
            }
        });
        interstitialAd.load("455f68157d9e08d78613c9808cac5bb5");
    }
```

and in your gradle  add below dependecies : 
```groovy
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.+'
```
for you magnet service ads add above magnet-service.jar  to your lib project directory.
