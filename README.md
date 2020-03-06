# system I set up in project to easy use of them

## How you should use this system :
for having your ads system and updating system 
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
            }

            @Override
            public void onReceive() {
                interstitialAd.show();
            }

            @Override
            public void onFail(int errorCode, String errorMessage) {
            }

            @Override
            public void onClose() {
            }
        });
        interstitialAd.load("your hash code");
    }
```

and in your gradle  add below dependecies : 
```groovy
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.+'
```
for you magnet service ads add above magnet-service.jar  to your lib project directory.
