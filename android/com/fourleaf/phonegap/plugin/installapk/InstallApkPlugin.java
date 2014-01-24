package com.fourleaf.phonegap.plugin.installapk;

import java.io.IOException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class InstallApkPlugin extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		if("install".equals(action)){
			String rawUri = args.getString(0);
			Uri uri = Uri.parse(rawUri);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(uri,
					"application/vnd.android.package-archive");
			Process p;
			try {
				// [文件夹705:drwx---r-x]
				String u = rawUri.replace("file://", "");
				String[] args1 = { "chmod", "705", u.substring(0, u.lastIndexOf("/")) };
				p = Runtime.getRuntime().exec(args1);
				p.waitFor();
		        // [文件604:-rw----r--]
		        String[] args2 = { "chmod", "604", u };
		        p = Runtime.getRuntime().exec(args2);
		        p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cordova.getActivity().startActivity(intent);
			return true;
		}
		return super.execute(action, args, callbackContext);
	}
}
