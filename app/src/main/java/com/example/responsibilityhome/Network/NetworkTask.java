package com.example.responsibilityhome.Network;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONObject;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;
    private int selection;
    ProgressDialog asyncDialog;

    public NetworkTask(Context _context, String url, String data, int action){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        if(_context != null)
            this.asyncDialog = new ProgressDialog(_context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;

        try {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.Request(url, data);

        }catch (Exception e){
            result = "Error";
        }
        return result;
    }

    @Override
    protected void onPreExecute(){
        //로딩
        if(asyncDialog != null) {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("Loading");
            asyncDialog.show();
            asyncDialog.setCanceledOnTouchOutside(false);
        }
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String result) {

        try {
            //경우에 따라 결과 값을 받아 일어났으면 하는 작업
            switch (this.selection) {
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String user_info = jsonObject.getString("result");
                        if (user_info.equals("fail")) {

                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }
            //로딩 종료
            if(asyncDialog!=null) {
                asyncDialog.setCanceledOnTouchOutside(true);
                asyncDialog.dismiss();
            }

        } catch (Exception e) {
            e.printStackTrace();
            //로딩 종료
            if(asyncDialog!=null) {
                asyncDialog.setCanceledOnTouchOutside(true);
                asyncDialog.dismiss();
            }
        }
    }
}
