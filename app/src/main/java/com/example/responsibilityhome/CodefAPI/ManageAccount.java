package com.example.responsibilityhome.CodefAPI;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ManageAccount {
    private static final String TAG = "ManageAccount";

    // 계정 생성
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void create() throws InterruptedException, ExecutionException, JSONException, IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, ParseException {
        String urlPath = "https://api.codef.io/v1/account/create";

        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
        accountMap1.put("countryCode",	"KR");  // 국가코드
        accountMap1.put("businessType",	"BK");  // 업무구분코드
        accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
        accountMap1.put("organization",	"0003");// 기관코드
        accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)

        String password1 = "엔드유저의 인증서 비밀번호";
        accountMap1.put("password",  	RSAUtil.encryptRSA(password1, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */

        accountMap1.put("keyFile",      "BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열");
        accountMap1.put("derFile",      "BASE64로 Encoding된 엔드유저의 인증서 der파일 문자열");
        list.add(accountMap1);

        HashMap<String, Object> accountMap2 = new HashMap<String, Object>();
        accountMap2.put("countryCode",	"KR");
        accountMap2.put("businessType",	"BK");
        accountMap2.put("clientType",  	"P");
        accountMap2.put("organization",	"0004");
        accountMap2.put("loginType",  	"1");

        //String password2 = "엔드유저의 기관 로그인 비밀번호";
        String password2 = "1225YEOP";
        accountMap2.put("password",  	RSAUtil.encryptRSA(password2, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */

        accountMap2.put("id",  		"EKDH787");
        accountMap2.put("birthday",	"960113");
        //accountMap2.put("id",  		"엔드 유저의 기관 로그인 아이디");
        //accountMap2.put("birthday",	"YYMMDD");
        list.add(accountMap2);

        bodyMap.put("accountList", list);

        // CODEF API 호출
        String result = ApiRequest.reqeust(urlPath, bodyMap);
        Log.d(TAG, result);

        // 응답 문자열 인코딩, JSONObject 변환
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(URLDecoder.decode(result, "UTF-8"));
        JSONObject tokenJson = (JSONObject)obj;

        Log.d(TAG, tokenJson.get("connectedId").toString());

    }

    // 계정 추가
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void add() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InterruptedException, ExecutionException, JSONException, IOException {
        String urlPath = "https://api.codef.io/v1/account/add";

        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
        accountMap1.put("countryCode",	"KR");  // 국가코드
        accountMap1.put("businessType",	"BK");  // 업무구분코드
        accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
        accountMap1.put("organization",	"0020");// 기관코드
        accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)

        String password1 = "엔드유저의 인증서 비밀번호";
        accountMap1.put("password",  	RSAUtil.encryptRSA(password1, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */

        accountMap1.put("keyFile",      "BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열");
        accountMap1.put("derFile",      "BASE64로 Encoding된 엔드유저의 인증서 der파일 문자열");
        list.add(accountMap1);

        bodyMap.put("accountList", list);

        String connectedId = "엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 입력";
        bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);


        // CODEF API 호출
        String result = ApiRequest.reqeust(urlPath, bodyMap);
        Log.d(TAG, result);
    }

    // 계정 수정
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void update() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InterruptedException, ExecutionException, JSONException, IOException {
        String urlPath = "https://api.codef.io/v1/account/update";

        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
        accountMap1.put("countryCode",	"KR");  // 국가코드
        accountMap1.put("businessType",	"BK");  // 업무구분코드
        accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
        accountMap1.put("organization",	"0020");// 기관코드
        accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)

        String password1 = "엔드유저의 인증서 비밀번호";
        accountMap1.put("password",  	RSAUtil.encryptRSA(password1, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */

        accountMap1.put("keyFile",      "BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열");
        accountMap1.put("derFile",      "BASE64로 Encoding된 엔드유저의 인증서 der파일 문자열");
        list.add(accountMap1);

        bodyMap.put("accountList", list);

        String connectedId = "엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 입력";
        bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);

        // CODEF API 호출
        String result = ApiRequest.reqeust(urlPath, bodyMap);
        Log.d(TAG, result);
    }

    // 계정 삭제
    public static void delete() throws InterruptedException, ExecutionException, JSONException, IOException {
        String urlPath = "https://api.codef.io/v1/account/delete";

        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
        accountMap1.put("countryCode",	"KR");  // 국가코드
        accountMap1.put("businessType",	"BK");  // 업무구분코드
        accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
        accountMap1.put("organization",	"0020");// 기관코드
        accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)
        list.add(accountMap1);

        bodyMap.put("accountList", list);

        String connectedId = "엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 입력";
        bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);


        // CODEF API 호출
        String result = ApiRequest.reqeust(urlPath, bodyMap);
        Log.d(TAG, result);
    }


    // 계정 목록 조회
    public static void searchAccountList() throws InterruptedException, ExecutionException, JSONException, IOException {
        String urlPath = "https://api.codef.io/v1/account/list";

        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        String connectedId = "엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 입력";
        bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);

        // CODEF API 호출
        String result = ApiRequest.reqeust(urlPath, bodyMap);
        Log.d(TAG, result);
    }

    // connectedId 목록 조회
    public static void searchConnectedIdList() throws InterruptedException, ExecutionException, JSONException, IOException {
        String urlPath = "https://api.codef.io/v1/account/connectedId-list";

        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put(CommonConstant.PAGE_NO, 0);	// 페이지 번호. 생략시 1페이지 값(0)으로 설정

        // CODEF API 호출
        String result = ApiRequest.reqeust(urlPath, bodyMap);
        Log.d(TAG, result);
    }

}
