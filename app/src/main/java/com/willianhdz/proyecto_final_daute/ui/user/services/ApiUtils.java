package com.willianhdz.proyecto_final_daute.ui.user.services;

public class ApiUtils {

    private ApiUtils(){
    }

    public static final String API_URL = "http://192.168.108.2/webservices/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

    public static CategoryService getCategoryService(){
        return RetrofitClient.getClient(API_URL).create(CategoryService.class);
    }

    public static ProductService getProductService(){
        return RetrofitClient.getClient(API_URL).create(ProductService.class);
    }

}
