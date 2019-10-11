package com.example.responsibilityhome.CodefAPI;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class CodefApi {
    private static final String TAG = "CodefApi";

    // 은행 개인 보유계좌
    public static void KR_BK_1_P_001() throws InterruptedException, IOException, ExecutionException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_001;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시

        bodyMap.put("organization", "0004");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 은행 개인 수시입출 거래내역
    public static void KR_BK_1_P_002() throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_002;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시

        bodyMap.put("organization", "0004");

        bodyMap.put("account", "06170204160000");
        bodyMap.put("startDate", "20190401");
        bodyMap.put("endDate", "20190619");
        bodyMap.put("orderBy", "0");
        bodyMap.put("inquiryType", "1");

        bodyMap.put("accountPassword", "RSA암호화된 비밀번호");		// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 은행 개인 적금 거래내역
    public static void KR_BK_1_P_003() throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_003;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시

        bodyMap.put("organization", "0004");

        bodyMap.put("account", "54780300020000");
        bodyMap.put("startDate", "20190301");
        bodyMap.put("endDate", "20190619");
        bodyMap.put("orderBy", "0");
        bodyMap.put("inquiryType", "1");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 은행 개인 대출 거래내역
    public static void KR_BK_1_P_004() throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_004;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시

        bodyMap.put("organization", "0004");

        bodyMap.put("account", "7526090400000");
        bodyMap.put("startDate", "20190601");
        bodyMap.put("endDate", "20190708");
        bodyMap.put("orderBy", "0");
        bodyMap.put("inquiryType", "1");
        bodyMap.put("accountLoanExecNo", "");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 은행 개인 빠른 계좌
    public static void KR_BK_1_P_005() throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_005;

        // 요청 파라미터 설정 시작 - 은행사별로 요구되는 요청 파라미터가 상이합니다. 비밀번호 관련 필드가 사용되는 경우 RSA 암호화가 필요합니다.
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        bodyMap.put("fastId", "");
        bodyMap.put("fastPassword", "RSA암호화된 비밀번호");		// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.
        bodyMap.put("id", "");
        bodyMap.put("password", "RSA암호화된 비밀번호");			// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.

        bodyMap.put("organization", "0004");

        bodyMap.put("account", "06170204160000");
        bodyMap.put("accountPassword", "RSA암호화된 비밀번호");		// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.
        bodyMap.put("startDate", "20190301");
        bodyMap.put("endDate", "20190619");
        bodyMap.put("orderBy", "0");
        bodyMap.put("identity", "820123");

        bodyMap.put("smsAuthNo", "");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 카드 개인 보유내역 조회
    public static void KR_CD_P_001 () throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_CD_P_001;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시

        bodyMap.put("organization", "0309");
        bodyMap.put("birthDate", "");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 카드 개인 승인 내역 조회
    public static void KR_CD_P_002 () throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_CD_P_002;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
        bodyMap.put("organization", "0309");

        bodyMap.put("birthDate", "");

        bodyMap.put("startDate", "20190601");
        bodyMap.put("endDate", "20190619");
        bodyMap.put("orderBy", "0");
        bodyMap.put("inquiryType", "1");
        bodyMap.put("cardNo", "6253-****-****-0000");
        bodyMap.put("cardName", "");
        bodyMap.put("duplicateCardSelect", "");
        bodyMap.put("duplicateCardIdx", "");
        bodyMap.put("memberStoreInfoType" ,"0");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 카드 개인 청구내역 조회
    public static void KR_CD_P_003 () throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_CD_P_003;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
        bodyMap.put("organization", "0309");

        bodyMap.put("birthDate", "");
        bodyMap.put("startDate", "201905");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 카드 개인 한도조회
    public static void KR_CD_P_004 () throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_CD_P_004;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시

        bodyMap.put("organization", "0309");

        bodyMap.put("birthDate", "");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }

    // 부동산등기 열람/발급
    public static void KR_PB_A_002  () throws InterruptedException, ExecutionException, IOException {
        // 요청 URL 설정
        String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_PB_CK_001;

        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        bodyMap.put("organization", "0002");

        bodyMap.put("phoneNo", "01011112222");
        bodyMap.put("password", "RSA암호화된 비밀번호");
        bodyMap.put("inquiryType", "3");
        bodyMap.put("uniqueNo", "");
        bodyMap.put("realtyType", "1");
        bodyMap.put("addr_sido", "서울특별시");
        bodyMap.put("address", "");
        bodyMap.put("recordStatus", "0");

        bodyMap.put("addr_dong", "");
        bodyMap.put("addr_lotNumber", "");
        bodyMap.put("inputSelect", "");
        bodyMap.put("buildingName", "");
        bodyMap.put("dong", "801");
        bodyMap.put("ho", "804");
        bodyMap.put("addr_sigungu", "OO구");
        bodyMap.put("addr_roadName", "OOOO로5길");
        bodyMap.put("addr_buildingNumber", "62");
        bodyMap.put("jointMortgageJeonseYN", "1");
        bodyMap.put("tradingYN", "1");
        bodyMap.put("listNumber", "");
        bodyMap.put("electronicClosedYN", "");
        bodyMap.put("ePrepayNo", "V74117000000");
        bodyMap.put("ePrepayPass", "password1234");
        bodyMap.put("issueType", "1");
        bodyMap.put("startPageNo", "");
        bodyMap.put("pageCount", "");
        bodyMap.put("originData", "");
        bodyMap.put("originDataYN", "");
        bodyMap.put("warningSkipYN", "");
        bodyMap.put("registerSummaryYN", "1");
        bodyMap.put("applicationType", "");
        // 요청 파라미터 설정 종료

        // API 요청
        String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
        Log.d(TAG, result);
    }
}
