package cn.fkj233.blockmiui;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdexecRunner {

    public CmdexecRunner() {

    }

    /**
     * @param cmd
     *
     * 执行命令
     * */

    public static StringBuilder exec(String cmd){

        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();

        try {
            process = Runtime.getRuntime().exec(cmd);
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while (( line = successResult.readLine()) != null) {
                successMsg.append(line).append("\n");
            }
            while (( line = errorResult.readLine()) != null) {
                errorMsg.append(line).append("\n");
            }

        }catch (Exception e){
            Log.e("error",e.getLocalizedMessage());
        }finally {
            try {
                errorResult.close();
                successResult.close();
                process.destroy();
            }catch (IOException ignored){}
        }
        return successMsg;
    }
}
