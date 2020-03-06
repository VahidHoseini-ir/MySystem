package ir.vahidhoseini.callrecorder.RetrofitResult;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ManagingApps {
    @SerializedName("Update")
    public List<update> appupdate;
    @SerializedName("AppAds")
    public AppAds appads;

    public ManagingApps(List<update> appupdate, AppAds appads) {
        this.appupdate = appupdate;
        this.appads = appads;
    }

    public List<update> getAppUpdate() {
        return appupdate;
    }

    public AppAds getAppads() {
        return appads;
    }

    public class update {
        @SerializedName("app_id")
        public String app_id;
        @SerializedName("updateable")
        public boolean updateable;
        @SerializedName("title")
        public String title;
        @SerializedName("desc")
        public String desc;
        @SerializedName("url_download")
        public String url_download;

        public update(String app_id, boolean updateable, String title, String desc, String url_download) {
            this.app_id = app_id;
            this.updateable = updateable;
            this.title = title;
            this.desc = desc;
            this.url_download = url_download;
        }

        public String getApp_id() {
            return app_id;
        }

        public boolean getUpdateable() {
            return updateable;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getUrl_download() {
            return url_download;
        }
    }

    public class AppAds {
        @SerializedName("ActivatingExAds")
        public boolean ActivatingExAds;
        @SerializedName("ActivatingMyAds")
        public boolean ActivatingMyAds;
        @SerializedName("ActivatingGlobalAd")
        public boolean ActivatingGlobalAd;
        @SerializedName("MyAds")
        public MyAds myads;

        public AppAds(boolean ActivatingExAds, boolean ActivatingMyAds, boolean ActivatingGlobalAd, MyAds myads) {
            this.ActivatingExAds = ActivatingExAds;
            this.ActivatingMyAds = ActivatingMyAds;
            this.ActivatingGlobalAd = ActivatingGlobalAd;
            this.myads = myads;
        }

        public boolean getActivatingExAds() {
            return ActivatingExAds;
        }

        public boolean getActivatingMyAds() {
            return ActivatingMyAds;
        }

        public boolean getActivatingGlobalAd() {
            return ActivatingGlobalAd;
        }

        public MyAds getMyads() {
            return myads;
        }


        public class MyAds {
            @SerializedName("WhichApp")
            public List<WhichApp> whichApps;
            @SerializedName("ListAds")
            public List<ListAds> listAds;
            @SerializedName("GlobalAd")
            public GlobalAds globalAds;


            public MyAds(List<WhichApp> whichApps, List<ListAds> listAds, GlobalAds globalAds) {
                this.whichApps = whichApps;
                this.listAds = listAds;
                this.globalAds = globalAds;
            }

            public List<WhichApp> getWhichApps() {
                return whichApps;
            }

            public List<ListAds> getListAds() {
                return listAds;
            }

            public GlobalAds getGlobalAds() {
                return globalAds;
            }

            public class WhichApp {
                @SerializedName("app_id")
                public String app_id;
                @SerializedName("activating")
                public boolean activating;

                public WhichApp(String app_id, boolean activating) {
                    this.app_id = app_id;
                    this.activating = activating;
                }

                public String getApp_id() {
                    return app_id;
                }

                public boolean getActivating() {
                    return activating;
                }
            }

            public class ListAds {
                @SerializedName("id")
                public String id;
                @SerializedName("title")
                public String title;
                @SerializedName("color_title")
                public String color_title;
                @SerializedName("url_photo")
                public String url_photo;
                @SerializedName("color_btn")
                public String color_btn;
                @SerializedName("url_action")
                public String url_action;
                @SerializedName("text_url_action")
                public String text_url_action;

                public ListAds(String id, String title, String color_title, String url_photo, String color_btn, String url_action, String text_url_action) {
                    this.id = id;
                    this.title = title;
                    this.color_title = color_title;
                    this.url_photo = url_photo;
                    this.color_btn = color_btn;
                    this.url_action = url_action;
                    this.text_url_action = text_url_action;
                }

                public String getid() {
                    return id;
                }

                public String gettitle() {
                    return title;
                }

                public String getColorTitle() {
                    return color_title;
                }

                public String geturl_photo() {
                    return url_photo;
                }

                public String getColorBtn() {
                    return color_btn;
                }

                public String geturl_action() {
                    return url_action;
                }

                public String gettext_url_action() {
                    return text_url_action;
                }


            }

            public class GlobalAds {
                @SerializedName("id")
                public String id;
                @SerializedName("title")
                public String title;
                @SerializedName("color_title")
                public String color_title;
                @SerializedName("url_photo")
                public String url_photo;
                @SerializedName("color_btn")
                public String color_btn;
                @SerializedName("url_action")
                public String url_action;
                @SerializedName("text_url_action")
                public String text_url_action;

                public GlobalAds(String id, String title, String desc, String url_photo, String url_gif, String url_action, String text_url_action) {
                    this.id = id;
                    this.title = title;
                    this.color_title = desc;
                    this.url_photo = url_photo;
                    this.color_btn = url_gif;
                    this.url_action = url_action;
                    this.text_url_action = text_url_action;
                }

                public String getid() {
                    return id;
                }

                public String gettitle() {
                    return title;
                }

                public String getColor_title() {
                    return color_title;
                }

                public String geturl_photo() {
                    return url_photo;
                }

                public String getColor_btn() {
                    return color_btn;
                }

                public String geturl_action() {
                    return url_action;
                }

                public String gettext_url_action() {
                    return text_url_action;
                }


            }
        }


    }
}
