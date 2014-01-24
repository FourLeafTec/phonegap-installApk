cordova.define("com.zx.phonegap.plugin.installApkPlugin.installApk", function(require, exports, module) {

var exec = require("cordova/exec");

var installApk = { 
	install: function(uri, s, f) {
//		cordova.exec.setJsToNativeBridgeMode(0);
//		cordova.exec.setNativeToJsBridgeMode(2);
	    window.setTimeout(function () {
	                      cordova.exec(s, f, "InstallApkPlugin", "install", [uri]);
	                      }, 0);
	}
};
module.exports = installApk;
});
