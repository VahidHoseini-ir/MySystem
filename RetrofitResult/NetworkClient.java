package ir.vahidhoseini.callrecorder.RetrofitResult;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import ir.vahidhoseini.callrecorder.BuildConfig;
import ir.vahidhoseini.callrecorder.MainActivity;
import ir.vahidhoseini.callrecorder.R;
import ir.vahidhoseini.callrecorder.Splash_Activity;
import ir.vahidhoseini.callrecorder.SqliteDatabase.DatabaseHelper;
import ir.vahidhoseini.callrecorder.adapter.IncommingAdapter;
import ir.vahidhoseini.callrecorder.conf;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    public Retrofit retrofit;
    public Activity currentActivity;

    public NetworkClient(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    public Retrofit getRetrofitClient() {
        //If condition to ensure we don't create multiple retrofit instances in a single application
        if (retrofit == null) {
            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.app_management) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build();
        }
        return retrofit;
    }

    public void setControlApp() {
        Retrofit retrofit = getRetrofitClient();
        AppManagerApi managerApi = retrofit.create(AppManagerApi.class);
        Call<ManagingApps> call = managerApi.getManagmentData();
        call.enqueue(new Callback<ManagingApps>() {
            @Override
            public void onResponse(Call<ManagingApps> call, Response<ManagingApps> response) {
                if (response.body() != null) {
                    ManagingApps managingApps = response.body();
                    ManagingApps.AppAds appAds = managingApps.getAppads();
                    List<ManagingApps.update> app_update_list = managingApps.getAppUpdate();
                    for (int i = 0; i < app_update_list.size(); i++) {
                        if (BuildConfig.APPLICATION_ID.equals(app_update_list.get(i).app_id)) {
                            showNotifUpdate(app_update_list.get(i).getTitle(), app_update_list.get(i).getDesc(), app_update_list.get(i).getUpdateable(), app_update_list.get(i).getUrl_download());
                        }
                    }
                    conf.ActivatingExAds = appAds.getActivatingExAds();
                    conf.ActivatingGlobalAd = appAds.getActivatingGlobalAd();
                    conf.ActivatingMyAds = appAds.getActivatingMyAds();
                    if (conf.ActivatingMyAds) {
                        List<ManagingApps.AppAds.MyAds.WhichApp> whichAppShowAds = appAds.getMyads().getWhichApps();
                        List<ManagingApps.AppAds.MyAds.ListAds> ListAds = appAds.getMyads().getListAds();
                        for (int i = 0; i < whichAppShowAds.size(); i++) {
                            if (BuildConfig.APPLICATION_ID.equals(whichAppShowAds.get(i).getApp_id())) {
                                conf.ActivatingMyAds = whichAppShowAds.get(i).getActivating();
                            }
                        }
                        for (int i = 0; i < ListAds.size(); i++) {
                            if (BuildConfig.APPLICATION_ID.equals(ListAds.get(i).getid())) {
                                ListAds.remove(i);
                            }
                        }
                        int randomAds = new Random().nextInt(ListAds.size());
                        showMyAds(ListAds.get(randomAds).gettitle(), ListAds.get(randomAds).getColorTitle(), ListAds.get(randomAds).geturl_photo(), ListAds.get(randomAds).getColorBtn(), ListAds.get(randomAds).geturl_action(), ListAds.get(randomAds).gettext_url_action());
                    } else if (conf.ActivatingGlobalAd) {
                        ManagingApps.AppAds.MyAds.GlobalAds globalAds = appAds.getMyads().getGlobalAds();
                        showMyAds(globalAds.gettitle(), globalAds.getColor_title(), globalAds.geturl_photo(), globalAds.getColor_btn(), globalAds.geturl_action(), globalAds.gettext_url_action());

                    } else if (conf.ActivatingExAds) {
                        if (conf.currentActivity == currentActivity)
                            ((MainActivity) currentActivity).showAdvertisment();
                    }
//                    ManagingApps.AppAds appads = managingApps.getAppads();
//                    ManagingApps.AppAds.MyAds appAds = appads.getMyads();
//                    Log.e("tag", "app_management data:" + appads.getMyads().listAds.get(1).getdesc());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag", "app_management data: " + t.toString());

            }
        });

    }

    private void showMyAds(String gettitle, String getColorTitle, String geturl_photo, String getColorBtn, String geturl_action, String gettext_url_action) {
        final Dialog dialog = new Dialog(conf.currentActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_app_ads);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        TextView txt_title = dialog.findViewById(R.id.ads_title);
        TextView ads_btn = dialog.findViewById(R.id.ads_btn);
        ImageView ads_background = dialog.findViewById(R.id.ads_background);
        ImageView ads_close = dialog.findViewById(R.id.ads_close);
        txt_title.setText(gettitle);
        txt_title.setTextColor(Color.parseColor(getColorTitle));
        ads_btn.setText(gettext_url_action);
        ads_btn.setBackgroundColor(Color.parseColor(getColorBtn));
        ads_btn.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geturl_action));
            conf.currentActivity.startActivity(browserIntent);
        });
        ads_close.setOnClickListener(v -> {
            dialog.dismiss();

        });


        Picasso.with(conf.context).load(geturl_photo).into(ads_background, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                dialog.show();
            }

            @Override
            public void onError() {

            }
        });


    }

    private void showNotifUpdate(String title, String desc, boolean updateable, String url_download) {
        if (updateable) {
            final Dialog dialog = new Dialog(conf.currentActivity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_app_update);
            dialog.setCancelable(true);
            dialog.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();
            TextView txt_title = dialog.findViewById(R.id.txt_title);
            TextView txt_desc = dialog.findViewById(R.id.txt_desc);
            TextView btn_netgativ = dialog.findViewById(R.id.btn_netgativ);
            TextView btn_positive = dialog.findViewById(R.id.btn_positive);
            txt_title.setText(title);
            txt_desc.setText(desc);
            btn_netgativ.setText("IGNORE");
            btn_positive.setText("UPDATE");
            btn_netgativ.setOnClickListener(v -> dialog.cancel());
            btn_positive.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_download));
                conf.currentActivity.startActivity(browserIntent);
            });
        }
    }

}