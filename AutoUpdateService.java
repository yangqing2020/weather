package com.example.myweather.service;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import com.example.myweather.WeatherActivity;
import com.example.myweather.gson.NowWeather;
import com.example.myweather.gson.Weather;
import com.example.myweather.util.HttpUtil;
import com.example.myweather.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


//每隔8h，autoservice会重新执行，这样也就实现了后台定时更新的功能
//在这里我们将更新后的数据直接储存到shareprefreences中，因为app打开的时候都是优先从sharedpreference中读取数据
public class AutoUpdateService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    //onStartCommand()被重写用于执行更新天气的操作
    public int onStartCommand(Intent intent, int flags, int startId) {
        //调用updateWeather()方法和updateNowWeather()方法
        updateWeather();
        updateNowWeather();
        //AlarmManager设置定时任务
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);//定时任务的触发
        int anHour = 30 * 1000;    //八小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this,AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    //更新weather信息类
    private void updateWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null) {
            //有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);//缓存的天气数据解析成Weather对象
            String weatherId = weather.getForecasts().get(0).getAdcode();

            String weatherUrl = "https://restapi.amap.com/v3/weather/weatherInfo?key=e9331fcd93e5d7bac3b5778ca43b6fa5&city=" + weatherId + "&extensions=all&output=JSON";

            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {//用HttpUtil类来进行网络请求。
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {//将服务器响应的天气数据保存到缓存中
                    String responseText = response.body().string();
                    Weather weather = Utility.handleWeatherResponse(responseText);
                    if (weather != null && "1".equals(weather.getStatus())) {
                        //将查询到的weather存入缓存
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }

                }
            });

        }
    }

    //更新nowWeather信息类
    private void updateNowWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("nowWeather", null);
        if (weatherString != null) {
            //有缓存时直接解析天气数据
            NowWeather nowWeather = Utility.handleNowWeatherResponse(weatherString);
            String weatherId = nowWeather.getLives().get(0).getAdcode();

            String weatherUrl = "https://restapi.amap.com/v3/weather/weatherInfo?key=e9331fcd93e5d7bac3b5778ca43b6fa5&city=" + weatherId + "&output=JSON";

            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String responseText = response.body().string();
                    NowWeather nowWeather = Utility.handleNowWeatherResponse(responseText);
                    if (nowWeather != null && "1".equals(nowWeather.getStatus())) {
                        //将查询到的weather存入缓存
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("nowWeather", responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

}
