package application.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DownLoadService {
    public String download(String userRequest){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(userRequest)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                return body.string();
            } else {
                return "";
            }

        } catch (IOException e) {
            return "";
        }

    }
}
