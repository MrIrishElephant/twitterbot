package ibcs.ia;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class BetsAPIService {
    private final String apiKey;
    private final OkHttpClient client;
    private final Gson gson;

    public BetsAPIService(String apiKey) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public BetsAPIResponse getEvents(String leagueId) throws IOException {
        String url = "https://api.betsapi.com/v2/events/?token=" + apiKey + "&league_id=" + leagueId;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            return gson.fromJson(responseBody, BetsAPIResponse.class);
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
