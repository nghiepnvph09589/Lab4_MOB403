package com.example.buoi7;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InsertInterfacePrd {
    @FormUrlEncoded
    @POST("create_product.php")
    Call<ServerResPrd> insertPrd(@Field("name") String name,
                                 @Field("price") String price,
                                 @Field("description") String description);
}
