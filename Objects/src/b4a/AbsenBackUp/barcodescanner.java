package b4a.AbsenBackUp;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class barcodescanner extends Activity implements B4AActivity{
	public static barcodescanner mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.AbsenBackUp", "b4a.AbsenBackUp.barcodescanner");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (barcodescanner).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.AbsenBackUp", "b4a.AbsenBackUp.barcodescanner");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.AbsenBackUp.barcodescanner", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (barcodescanner) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (barcodescanner) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return barcodescanner.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (barcodescanner) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            barcodescanner mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (barcodescanner) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean _frontcamera = false;
public static String _matkul = "";
public static String _val = "";
public static String _nm = "";
public qrcodereaderviewwrapper.qrCodeReaderViewWrapper _qrcodereaderview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _btn_startscan = null;
public b4a.AbsenBackUp.cameraexclass _camex = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgetmhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnama_mhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnpm_mhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblkelas_mhs = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_absen = null;
public b4a.AbsenBackUp.main _main = null;
public b4a.AbsenBackUp.login _login = null;
public b4a.AbsenBackUp.barcodemahasiswa _barcodemahasiswa = null;
public b4a.AbsenBackUp.mahasiswa _mahasiswa = null;
public b4a.AbsenBackUp.profilkalkulasi _profilkalkulasi = null;
public b4a.AbsenBackUp.rekapmahasiswa _rekapmahasiswa = null;
public b4a.AbsenBackUp.dosen _dosen = null;
public b4a.AbsenBackUp.absen_hari_ini _absen_hari_ini = null;
public b4a.AbsenBackUp.kalkulasiabsen _kalkulasiabsen = null;
public b4a.AbsenBackUp.starter _starter = null;
public b4a.AbsenBackUp.barcodemhs _barcodemhs = null;
public b4a.AbsenBackUp.rekap_absen_dosen _rekap_absen_dosen = null;
public b4a.AbsenBackUp.util _util = null;
public b4a.AbsenBackUp.httputils2service _httputils2service = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"QrScanner\")";
mostCurrent._activity.LoadLayout("QrScanner",mostCurrent.activityBA);
 //BA.debugLineNum = 36;BA.debugLine="lbl.Initialize(\"\")";
mostCurrent._lbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 77;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 78;BA.debugLine="QRCodeReaderView1.closeDriver";
mostCurrent._qrcodereaderview1.closeDriver();
 //BA.debugLineNum = 79;BA.debugLine="QRCodeReaderView1.stopScan";
mostCurrent._qrcodereaderview1.stopScan();
 //BA.debugLineNum = 80;BA.debugLine="StartActivity(Dosen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._dosen.getObject()));
 //BA.debugLineNum = 81;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="QRCodeReaderView1.closeDriver";
mostCurrent._qrcodereaderview1.closeDriver();
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 42;BA.debugLine="Log(QRCodeReaderView1.FrontFacingCamera)";
anywheresoftware.b4a.keywords.Common.LogImpl("07077889",BA.NumberToString(mostCurrent._qrcodereaderview1.getFrontFacingCamera()),0);
 //BA.debugLineNum = 43;BA.debugLine="Log(QRCodeReaderView1.BackFacingCamera)";
anywheresoftware.b4a.keywords.Common.LogImpl("07077890",BA.NumberToString(mostCurrent._qrcodereaderview1.getBackFacingCamera()),0);
 //BA.debugLineNum = 44;BA.debugLine="QRCodeReaderView1.CameraId = QRCodeReaderView1.CA";
mostCurrent._qrcodereaderview1.setCameraId(mostCurrent._qrcodereaderview1.CAMERA_BACK);
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _btn_absen_click() throws Exception{
 //BA.debugLineNum = 165;BA.debugLine="Sub btn_absen_Click";
 //BA.debugLineNum = 166;BA.debugLine="If lblKelas_mhs.Text == Dosen.kelas Then";
if ((mostCurrent._lblkelas_mhs.getText()).equals(mostCurrent._dosen._kelas /*String*/ )) { 
 //BA.debugLineNum = 167;BA.debugLine="CheckAbsen(DateTime.Date(DateTime.Now), lblNpm_m";
_checkabsen(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),mostCurrent._lblnpm_mhs.getText(),_matkul,mostCurrent._lblnama_mhs.getText(),mostCurrent._dosen._dosennn /*String*/ );
 }else {
 //BA.debugLineNum = 169;BA.debugLine="ToastMessageShow(\"Maaf Nama Mahasiswa yang anda";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Maaf Nama Mahasiswa yang anda masukkan tidak tertera pada kelas "+mostCurrent._dosen._kelas /*String*/ +anywheresoftware.b4a.keywords.Common.CRLF+"Melainkan pada kelas "+mostCurrent._lblkelas_mhs.getText()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _btn_startscan_click() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub btn_startScan_Click";
 //BA.debugLineNum = 52;BA.debugLine="Log(btn_startScan)";
anywheresoftware.b4a.keywords.Common.LogImpl("07208961",BA.ObjectToString(mostCurrent._btn_startscan),0);
 //BA.debugLineNum = 53;BA.debugLine="QRCodeReaderView1.startScan";
mostCurrent._qrcodereaderview1.startScan();
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _camera1_ready(boolean _success) throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub Camera1_Ready (Success As Boolean)";
 //BA.debugLineNum = 57;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 58;BA.debugLine="camEx.SetJpegQuality(90)";
mostCurrent._camex._setjpegquality /*String*/ ((int) (90));
 //BA.debugLineNum = 59;BA.debugLine="camEx.CommitParameters";
mostCurrent._camex._commitparameters /*String*/ ();
 //BA.debugLineNum = 60;BA.debugLine="camEx.StartPreview";
mostCurrent._camex._startpreview /*String*/ ();
 }else {
 //BA.debugLineNum = 62;BA.debugLine="ToastMessageShow(\"Cannot open camera.\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Cannot open camera."),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static void  _checkabsen(String _tgl,String _npmss,String _matkulss,String _nama,String _dosenn) throws Exception{
ResumableSub_CheckAbsen rsub = new ResumableSub_CheckAbsen(null,_tgl,_npmss,_matkulss,_nama,_dosenn);
rsub.resume(processBA, null);
}
public static class ResumableSub_CheckAbsen extends BA.ResumableSub {
public ResumableSub_CheckAbsen(b4a.AbsenBackUp.barcodescanner parent,String _tgl,String _npmss,String _matkulss,String _nama,String _dosenn) {
this.parent = parent;
this._tgl = _tgl;
this._npmss = _npmss;
this._matkulss = _matkulss;
this._nama = _nama;
this._dosenn = _dosenn;
}
b4a.AbsenBackUp.barcodescanner parent;
String _tgl;
String _npmss;
String _matkulss;
String _nama;
String _dosenn;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 86;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,barcodescanner.getObject());
 //BA.debugLineNum = 87;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"CekAbs";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"CekAbsen",new Object[]{(Object)(_tgl),(Object)(_npmss),(Object)(_matkulss),(Object)(_nama),(Object)(_dosenn)});
 //BA.debugLineNum = 88;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 13;
return;
case 13:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 89;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 12;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 11;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 90;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 91;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 14;
return;
case 14:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 92;BA.debugLine="If res.Rows.Size > 0 Then";
if (true) break;

case 4:
//if
this.state = 9;
if (_res.Rows /*anywheresoftware.b4a.objects.collections.List*/ .getSize()>0) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 93;BA.debugLine="ToastMessageShow(\"Maaf Anda Sudah Absen\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Maaf Anda Sudah Absen"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 95;BA.debugLine="InsertAbsen(DateTime.Date(DateTime.Now),lblNpm_";
_insertabsen(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),parent.mostCurrent._lblnpm_mhs.getText(),parent._matkul,"Masuk",parent.mostCurrent._lblnama_mhs.getText(),parent.mostCurrent._lblkelas_mhs.getText(),parent.mostCurrent._dosen._dosennn /*String*/ ,parent.mostCurrent._dosen._bufferi /*byte[]*/ );
 if (true) break;

case 9:
//C
this.state = 12;
;
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 98;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("07471117",_j._errormessage /*String*/ ,0);
 if (true) break;

case 12:
//C
this.state = -1;
;
 //BA.debugLineNum = 100;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(b4a.AbsenBackUp.httpjob _j) throws Exception{
}
public static void  _req_result(b4a.AbsenBackUp.main._dbresult _res) throws Exception{
}
public static void  _getabsen(String _npmss) throws Exception{
ResumableSub_GetAbsen rsub = new ResumableSub_GetAbsen(null,_npmss);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetAbsen extends BA.ResumableSub {
public ResumableSub_GetAbsen(b4a.AbsenBackUp.barcodescanner parent,String _npmss) {
this.parent = parent;
this._npmss = _npmss;
}
b4a.AbsenBackUp.barcodescanner parent;
String _npmss;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
Object[] _row = null;
anywheresoftware.b4a.BA.IterableList group7;
int index7;
int groupLen7;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 105;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,barcodescanner.getObject());
 //BA.debugLineNum = 106;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectAbsen",new Object[]{(Object)(_npmss)});
 //BA.debugLineNum = 107;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 108;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 8;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 109;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 110;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 111;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group7 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index7 = 0;
groupLen7 = group7.getSize();
this.state = 11;
if (true) break;

case 11:
//C
this.state = 7;
if (index7 < groupLen7) {
this.state = 6;
_row = (Object[])(group7.Get(index7));}
if (true) break;

case 12:
//C
this.state = 11;
index7++;
if (true) break;

case 6:
//C
this.state = 12;
 //BA.debugLineNum = 112;BA.debugLine="lblNpm_mhs.Text = row(0)";
parent.mostCurrent._lblnpm_mhs.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 8;
;
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 115;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _getkelas(String _npmssss) throws Exception{
ResumableSub_GetKelas rsub = new ResumableSub_GetKelas(null,_npmssss);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetKelas extends BA.ResumableSub {
public ResumableSub_GetKelas(b4a.AbsenBackUp.barcodescanner parent,String _npmssss) {
this.parent = parent;
this._npmssss = _npmssss;
}
b4a.AbsenBackUp.barcodescanner parent;
String _npmssss;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
Object[] _row = null;
anywheresoftware.b4a.BA.IterableList group7;
int index7;
int groupLen7;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 150;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,barcodescanner.getObject());
 //BA.debugLineNum = 151;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"GetKel";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"GetKelas",new Object[]{(Object)(_npmssss)});
 //BA.debugLineNum = 152;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 153;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 8;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 154;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 155;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 156;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group7 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index7 = 0;
groupLen7 = group7.getSize();
this.state = 11;
if (true) break;

case 11:
//C
this.state = 7;
if (index7 < groupLen7) {
this.state = 6;
_row = (Object[])(group7.Get(index7));}
if (true) break;

case 12:
//C
this.state = 11;
index7++;
if (true) break;

case 6:
//C
this.state = 12;
 //BA.debugLineNum = 157;BA.debugLine="lblKelas_mhs.Text = row(0)";
parent.mostCurrent._lblkelas_mhs.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 8;
;
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 161;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _getnama(String _npmsss) throws Exception{
ResumableSub_GetNama rsub = new ResumableSub_GetNama(null,_npmsss);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetNama extends BA.ResumableSub {
public ResumableSub_GetNama(b4a.AbsenBackUp.barcodescanner parent,String _npmsss) {
this.parent = parent;
this._npmsss = _npmsss;
}
b4a.AbsenBackUp.barcodescanner parent;
String _npmsss;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
Object[] _row = null;
anywheresoftware.b4a.BA.IterableList group7;
int index7;
int groupLen7;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 135;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,barcodescanner.getObject());
 //BA.debugLineNum = 136;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"GetNam";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"GetNama",new Object[]{(Object)(_npmsss)});
 //BA.debugLineNum = 137;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 138;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 8;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 139;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 140;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 141;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group7 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index7 = 0;
groupLen7 = group7.getSize();
this.state = 11;
if (true) break;

case 11:
//C
this.state = 7;
if (index7 < groupLen7) {
this.state = 6;
_row = (Object[])(group7.Get(index7));}
if (true) break;

case 12:
//C
this.state = 11;
index7++;
if (true) break;

case 6:
//C
this.state = 12;
 //BA.debugLineNum = 142;BA.debugLine="lblNama_mhs.Text = row(0)";
parent.mostCurrent._lblnama_mhs.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 8;
;
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 146;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private QRCodeReaderView1 As QRCodeReaderView";
mostCurrent._qrcodereaderview1 = new qrcodereaderviewwrapper.qrCodeReaderViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private btn_startScan As Panel";
mostCurrent._btn_startscan = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private camEx As CameraExClass";
mostCurrent._camex = new b4a.AbsenBackUp.cameraexclass();
 //BA.debugLineNum = 22;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim lbl As Label";
mostCurrent._lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private lblGetMHS As Label";
mostCurrent._lblgetmhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lblNama_mhs As Label";
mostCurrent._lblnama_mhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lblNpm_mhs As Label";
mostCurrent._lblnpm_mhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lblKelas_mhs As Label";
mostCurrent._lblkelas_mhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private btn_absen As Button";
mostCurrent._btn_absen = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static void  _insertabsen(String _tgl,String _npmss,String _matkuls,String _statuss,String _nama,String _kelas,String _dsn,byte[] _profil) throws Exception{
ResumableSub_InsertAbsen rsub = new ResumableSub_InsertAbsen(null,_tgl,_npmss,_matkuls,_statuss,_nama,_kelas,_dsn,_profil);
rsub.resume(processBA, null);
}
public static class ResumableSub_InsertAbsen extends BA.ResumableSub {
public ResumableSub_InsertAbsen(b4a.AbsenBackUp.barcodescanner parent,String _tgl,String _npmss,String _matkuls,String _statuss,String _nama,String _kelas,String _dsn,byte[] _profil) {
this.parent = parent;
this._tgl = _tgl;
this._npmss = _npmss;
this._matkuls = _matkuls;
this._statuss = _statuss;
this._nama = _nama;
this._kelas = _kelas;
this._dsn = _dsn;
this._profil = _profil;
}
b4a.AbsenBackUp.barcodescanner parent;
String _tgl;
String _npmss;
String _matkuls;
String _statuss;
String _nama;
String _kelas;
String _dsn;
byte[] _profil;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 120;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Insert";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"InsertAbsen",new Object[]{(Object)(_tgl),(Object)(_npmss),(Object)(_matkuls),(Object)(_statuss),(Object)(_nama),(Object)(_kelas),(Object)(_dsn),(Object)(_profil)});
 //BA.debugLineNum = 121;BA.debugLine="Dim j As HttpJob = util.CreateRequest(Me).Execute";
_j = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,barcodescanner.getObject())._executebatch /*b4a.AbsenBackUp.httpjob*/ (anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_cmd)}),anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 122;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_j));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 123;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 124;BA.debugLine="ToastMessageShow(\"Terima Kasih Sudah Absen\", Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Terima Kasih Sudah Absen"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 125;BA.debugLine="StartActivity(Dosen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._dosen.getObject()));
 //BA.debugLineNum = 126;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 128;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("07602185",_j._errormessage /*String*/ ,0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 130;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private frontCamera As Boolean = False";
_frontcamera = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 10;BA.debugLine="Dim matkul As String";
_matkul = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim val As String";
_val = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim nm As String";
_nm = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _qrcodereaderview1_result_found(String _retval) throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub QRCodeReaderView1_result_found(retval As Strin";
 //BA.debugLineNum = 67;BA.debugLine="GetAbsen(retval)";
_getabsen(_retval);
 //BA.debugLineNum = 68;BA.debugLine="GetNama(retval)";
_getnama(_retval);
 //BA.debugLineNum = 69;BA.debugLine="GetKelas(retval)";
_getkelas(_retval);
 //BA.debugLineNum = 70;BA.debugLine="val = retval";
_val = _retval;
 //BA.debugLineNum = 71;BA.debugLine="QRCodeReaderView1.stopScan";
mostCurrent._qrcodereaderview1.stopScan();
 //BA.debugLineNum = 72;BA.debugLine="Msgbox(val,nm)";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(_val),BA.ObjectToCharSequence(_nm),mostCurrent.activityBA);
 //BA.debugLineNum = 73;BA.debugLine="lblNpm_mhs.Text = retval";
mostCurrent._lblnpm_mhs.setText(BA.ObjectToCharSequence(_retval));
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
}
