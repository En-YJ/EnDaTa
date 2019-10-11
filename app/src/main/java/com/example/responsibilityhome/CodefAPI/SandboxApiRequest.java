package com.example.responsibilityhome.CodefAPI;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * API 요청 템플릿 클래스
 */
public class SandboxApiRequest {
    private static final String TAG = "SandboxApiRequest";
    private static ObjectMapper mapper = new ObjectMapper();


    public static String reqeust(String urlPath, HashMap<String, Object> bodyMap) throws IOException, InterruptedException, ExecutionException {
        String accessToken = CommonConstant.ACCESS_TOKEN;
        if (accessToken.isEmpty()) {    // 액세스토큰을 발급받은 적이 없다면 토큰 발급 요청 수행
            System.out.println("====    토큰 발급 요청 수행");
            accessToken = new RequestTokenTask()
                    .execute(CommonConstant.SANDBOX_CLIENT_ID, CommonConstant.SANDBOX_SECERET_KEY).get();
            CommonConstant.ACCESS_TOKEN = accessToken;    // 재사용을 위한 발급받은 액세스 토큰 저장
        }

        if (accessToken != null) {
            Log.d(TAG, accessToken);
        } else {
            Log.d(TAG, "토큰 발급 오류");
        }


        // POST요청을 위한 리퀘스트바디 생성(UTF-8 인코딩)
        Log.d(TAG, bodyMap.toString());
        String bodyString = mapper.writeValueAsString(bodyMap);
        bodyString = URLEncoder.encode(bodyString, "UTF-8");

        // API 요청
        System.out.println("====    API 요청 : " + urlPath);
        JSONObject json = (JSONObject) new HttpRequestTask()
                .execute(urlPath, accessToken, bodyString).get();

        System.out.println("====    API 요청 결과 확인");
        String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        System.out.println(result);

        // 액세스 토큰 유효기간 만료시
        if ("invalid_token".equals(json.get("error"))) {
            System.out.println("====    유효하지 않은 토큰인 경우 토큰 재발급 요청");
            // 토큰 재발급 요청 수행
            accessToken = new RequestTokenTask()
                    .execute(CommonConstant.SANDBOX_CLIENT_ID, CommonConstant.SANDBOX_SECERET_KEY).get();
            CommonConstant.ACCESS_TOKEN = accessToken;    // 재사용을 위한 발급받은 액세스 토큰 저장

            // API 재요청
            System.out.println("====    API 요청 : " + urlPath);
            json = (JSONObject) new HttpRequestTask()
                    .execute(urlPath, accessToken, bodyString).get();

            System.out.println("====    API 요청 결과 확인");
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            System.out.println(result);
        } else if ("access_denied".equals(json.get("error"))) {
            System.out.println("access_denied은 API 접근 권한이 없는 경우입니다.");
            System.out.println("코드에프 대시보드의 API 설정을 통해 해당 업무 접근 권한을 설정해야 합니다.");
        }

        return result;

    }
}
