import retrofit2.Call;
import retrofit2.http.*;

public interface TrelloApi {
    @POST("/1/boards")
    Call<Params> createBoard(@Body Params params, @Query("name") String name);
    @POST("/1/lists")
    Call<Params> createList(@Body Params params, @Query("name") String name, @Query("idBoard") String idBoard);
    @POST("/1/cards")
    Call<Params> createCard(@Body Params params, @Query("idList") String idList);
    @GET("/1/cards/{id}")
    Call<Params> getCard(@Path("id") String id, @Query("key") String key, @Query("token") String token);
    @PUT("/1/cards/{id}")
    Call<Params> upgradeCard(@Path("id") String id, @Query("key") String key, @Query("token") String token,@Query("name") String name);
    @DELETE("/1/cards/{id}")
    Call<Params> deleteCard(@Path("id") String id, @Query("key") String key, @Query("token") String token);
}