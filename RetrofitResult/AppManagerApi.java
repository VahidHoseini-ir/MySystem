package ir.vahidhoseini.callrecorder.RetrofitResult;

import com.google.gson.JsonElement;

import ir.vahidhoseini.callrecorder.BuildConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppManagerApi {
    /*
    Get request to fetch city weather.Takes in two parameter-city name and API key.
    */
//    + BuildConfig.app_management
    @GET("management/" + BuildConfig.app_management_file)
    Call<ManagingApps> getManagmentData();
}