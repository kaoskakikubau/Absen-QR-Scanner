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

public class dosen extends Activity implements B4AActivity{
	public static dosen mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.AbsenBackUp", "b4a.AbsenBackUp.dosen");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (dosen).");
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
		activityBA = new BA(this, layout, processBA, "b4a.AbsenBackUp", "b4a.AbsenBackUp.dosen");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.AbsenBackUp.dosen", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (dosen) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (dosen) Resume **");
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
		return dosen.class;
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
        BA.LogInfo("** Activity (dosen) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            dosen mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (dosen) Resume **");
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
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static String _dosennn = "";
public static String _kelas = "";
public static byte[] _bufferi = null;
public static String _matkul = "";
public de.amberhome.objects.appcompat.ACSpinnerWrapper _ac_spinnerkelas = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _ac_spinnermatkul = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _acspinnerket = null;
public b4a.example3.customlistview _clv_namamhs = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_clvabsendosen = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_namamhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_nomor = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_ketmhs = null;
public static String[] _pic = null;
public static int _count = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_tanggaldosen = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_namadosen = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_selesaipengambilan = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_rekap_absen = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_kalkulasi = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_kalkulasi = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_rekap_absen = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_absen_harini = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _img = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _xiv = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_profil = null;
public b4a.AbsenBackUp.main _main = null;
public b4a.AbsenBackUp.login _login = null;
public b4a.AbsenBackUp.barcodemahasiswa _barcodemahasiswa = null;
public b4a.AbsenBackUp.mahasiswa _mahasiswa = null;
public b4a.AbsenBackUp.profilkalkulasi _profilkalkulasi = null;
public b4a.AbsenBackUp.rekapmahasiswa _rekapmahasiswa = null;
public b4a.AbsenBackUp.absen_hari_ini _absen_hari_ini = null;
public b4a.AbsenBackUp.kalkulasiabsen _kalkulasiabsen = null;
public b4a.AbsenBackUp.starter _starter = null;
public b4a.AbsenBackUp.barcodescanner _barcodescanner = null;
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
public static String  _ac_spinnerkelas_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub AC_SpinnerKelas_ItemClick (Position As Int, Va";
 //BA.debugLineNum = 109;BA.debugLine="CLV_NamaMHS.Clear";
mostCurrent._clv_namamhs._clear();
 //BA.debugLineNum = 110;BA.debugLine="GetMHS(Value)";
_getmhs(BA.ObjectToString(_value));
 //BA.debugLineNum = 111;BA.debugLine="kelas = Value";
_kelas = BA.ObjectToString(_value);
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _ac_spinnermatkul_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Sub AC_SpinnerMatkul_ItemClick (Position As Int, V";
 //BA.debugLineNum = 115;BA.debugLine="BarcodeScanner.matkul = Value";
mostCurrent._barcodescanner._matkul /*String*/  = BA.ObjectToString(_value);
 //BA.debugLineNum = 116;BA.debugLine="matkul = Value";
_matkul = BA.ObjectToString(_value);
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _acspinnerket_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 119;BA.debugLine="Sub ACSpinnerKet_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 120;BA.debugLine="Log(\"Spinner Item selected: \" & Position & \" - \"";
anywheresoftware.b4a.keywords.Common.LogImpl("04653057","Spinner Item selected: "+BA.NumberToString(_position)+" - "+BA.ObjectToString(_value),0);
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="Activity.LoadLayout(\"lay_AbsenDosen\")";
mostCurrent._activity.LoadLayout("lay_AbsenDosen",mostCurrent.activityBA);
 //BA.debugLineNum = 58;BA.debugLine="lbl_namadosen.Text = dosennn";
mostCurrent._lbl_namadosen.setText(BA.ObjectToCharSequence(_dosennn));
 //BA.debugLineNum = 61;BA.debugLine="GetDosen";
_getdosen();
 //BA.debugLineNum = 63;BA.debugLine="GetMatkul";
_getmatkul();
 //BA.debugLineNum = 71;BA.debugLine="lbl_TanggalDosen.Text = DateTime.Date(DateTime.No";
mostCurrent._lbl_tanggaldosen.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 73;BA.debugLine="GetProfil(lbl_namadosen.Text)";
_getprofil(mostCurrent._lbl_namadosen.getText());
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
int _i = 0;
 //BA.debugLineNum = 265;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 266;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 267;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 268;BA.debugLine="i = Msgbox2(\"Yakin Ingin keluar?\", \"Info\", \"Ok\",";
_i = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Yakin Ingin keluar?"),BA.ObjectToCharSequence("Info"),"Ok","Cancel"," ",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 269;BA.debugLine="Select i";
switch (BA.switchObjectToInt(_i,anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE,anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL)) {
case 0: {
 //BA.debugLineNum = 271;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 272;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 274;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
}
;
 };
 //BA.debugLineNum = 277;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static void  _changeimage(String _npm) throws Exception{
ResumableSub_ChangeImage rsub = new ResumableSub_ChangeImage(null,_npm);
rsub.resume(processBA, null);
}
public static class ResumableSub_ChangeImage extends BA.ResumableSub {
public ResumableSub_ChangeImage(b4a.AbsenBackUp.dosen parent,String _npm) {
this.parent = parent;
this._npm = _npm;
}
b4a.AbsenBackUp.dosen parent;
String _npm;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
String _date_now = "";
b4a.AbsenBackUp.main._dbresult _res = null;
byte _i = (byte)0;
Object[] _row = null;
String _ket = "";
String _kers = "";
anywheresoftware.b4a.BA.IterableList group9;
int index9;
int groupLen9;
int step15;
int limit15;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 174;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,dosen.getObject());
 //BA.debugLineNum = 175;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Change";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"ChangeImage",new Object[]{(Object)(_npm)});
 //BA.debugLineNum = 176;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 31;
return;
case 31:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 177;BA.debugLine="Dim date_now As String = DateTime.Date(DateTime.N";
_date_now = anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 178;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 30;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 29;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 179;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 180;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 32;
return;
case 32:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 181;BA.debugLine="Dim i As Byte= 0";
_i = (byte) (0);
 //BA.debugLineNum = 182;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 27;
group9 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index9 = 0;
groupLen9 = group9.getSize();
this.state = 33;
if (true) break;

case 33:
//C
this.state = 27;
if (index9 < groupLen9) {
this.state = 6;
_row = (Object[])(group9.Get(index9));}
if (true) break;

case 34:
//C
this.state = 33;
index9++;
if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 183;BA.debugLine="Dim ket As String = row(4)";
_ket = BA.ObjectToString(_row[(int) (4)]);
 //BA.debugLineNum = 184;BA.debugLine="Dim kers As String = row(2)";
_kers = BA.ObjectToString(_row[(int) (2)]);
 //BA.debugLineNum = 185;BA.debugLine="If lbl_TanggalDosen.Text = date_now And ket.ToL";
if (true) break;

case 7:
//if
this.state = 26;
if ((parent.mostCurrent._lbl_tanggaldosen.getText()).equals(_date_now) && (_ket.toLowerCase()).equals("masuk")) { 
this.state = 9;
}else if((parent.mostCurrent._lbl_tanggaldosen.getText()).equals(_date_now) && anywheresoftware.b4a.keywords.Common.Not((_ket.toLowerCase()).equals("masuk"))) { 
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 26;
 //BA.debugLineNum = 186;BA.debugLine="ACSpinnerKet.Add(\"Oke\")";
parent.mostCurrent._acspinnerket.Add(BA.ObjectToCharSequence("Oke"));
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 188;BA.debugLine="For i = 1 To 4";
if (true) break;

case 12:
//for
this.state = 25;
step15 = 1;
limit15 = (byte) (4);
_i = (byte) (1) ;
this.state = 35;
if (true) break;

case 35:
//C
this.state = 25;
if ((step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15)) this.state = 14;
if (true) break;

case 36:
//C
this.state = 35;
_i = ((byte)(0 + _i + step15)) ;
if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 189;BA.debugLine="If i = 1 Then";
if (true) break;

case 15:
//if
this.state = 24;
if (_i==1) { 
this.state = 17;
}else if(_i==2) { 
this.state = 19;
}else if(_i==3) { 
this.state = 21;
}else if(_i==4) { 
this.state = 23;
}if (true) break;

case 17:
//C
this.state = 24;
 //BA.debugLineNum = 190;BA.debugLine="ACSpinnerKet.Add2(\"\",toDrawable(LoadBitmap(F";
parent.mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence(""),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Group 1.png")).getObject()));
 if (true) break;

case 19:
//C
this.state = 24;
 //BA.debugLineNum = 192;BA.debugLine="ACSpinnerKet.Add2(\"Absen\",toDrawable(LoadBit";
parent.mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence("Absen"),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"absen.png")).getObject()));
 if (true) break;

case 21:
//C
this.state = 24;
 //BA.debugLineNum = 194;BA.debugLine="ACSpinnerKet.Add2(\"Izin\",toDrawable(LoadBitm";
parent.mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence("Izin"),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"izin (2).png")).getObject()));
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 196;BA.debugLine="ACSpinnerKet.Add2(\"Sakit\",toDrawable(LoadBit";
parent.mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence("Sakit"),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sakit.png")).getObject()));
 if (true) break;

case 24:
//C
this.state = 36;
;
 if (true) break;
if (true) break;

case 25:
//C
this.state = 26;
;
 //BA.debugLineNum = 199;BA.debugLine="Log(\"bukan\")";
anywheresoftware.b4a.keywords.Common.LogImpl("04980762","bukan",0);
 if (true) break;

case 26:
//C
this.state = 34;
;
 if (true) break;
if (true) break;

case 27:
//C
this.state = 30;
;
 if (true) break;

case 29:
//C
this.state = 30;
 //BA.debugLineNum = 203;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("04980766",_j._errormessage /*String*/ ,0);
 if (true) break;

case 30:
//C
this.state = -1;
;
 //BA.debugLineNum = 205;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(b4a.AbsenBackUp.httpjob _j) throws Exception{
}
public static void  _req_result(b4a.AbsenBackUp.main._dbresult _res) throws Exception{
}
public static anywheresoftware.b4a.objects.PanelWrapper  _createitem(int _width,String _nomor,String _nama) throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
int _height = 0;
 //BA.debugLineNum = 79;BA.debugLine="Private Sub CreateItem(Width As Int, nomor As Stri";
 //BA.debugLineNum = 80;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 81;BA.debugLine="Dim height As Int = 55dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55));
 //BA.debugLineNum = 83;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, Width, height)";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
 //BA.debugLineNum = 84;BA.debugLine="p.LoadLayout(\"pnl_CLVAbsenDosen\")";
_p.LoadLayout("pnl_CLVAbsenDosen",mostCurrent.activityBA);
 //BA.debugLineNum = 85;BA.debugLine="lbl_Nomor.Text = nomor";
mostCurrent._lbl_nomor.setText(BA.ObjectToCharSequence(_nomor));
 //BA.debugLineNum = 86;BA.debugLine="lbl_namaMHS.Text = nama";
mostCurrent._lbl_namamhs.setText(BA.ObjectToCharSequence(_nama));
 //BA.debugLineNum = 88;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getObject()));
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return null;
}
public static b4a.AbsenBackUp.dbrequestmanager  _createrequest() throws Exception{
b4a.AbsenBackUp.dbrequestmanager _req = null;
 //BA.debugLineNum = 208;BA.debugLine="Sub CreateRequest As DBRequestManager";
 //BA.debugLineNum = 209;BA.debugLine="Dim req As DBRequestManager";
_req = new b4a.AbsenBackUp.dbrequestmanager();
 //BA.debugLineNum = 210;BA.debugLine="req.Initialize(Me, Main.exportrdcLink)";
_req._initialize /*String*/ (processBA,dosen.getObject(),mostCurrent._main._exportrdclink /*String*/ );
 //BA.debugLineNum = 211;BA.debugLine="Return req";
if (true) return _req;
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper  _createroundbitmap(anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _input,int _size) throws Exception{
int _l = 0;
anywheresoftware.b4a.objects.B4XCanvas _c = null;
anywheresoftware.b4a.objects.B4XViewWrapper _xview = null;
anywheresoftware.b4a.objects.B4XCanvas.B4XPath _path = null;
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _res = null;
 //BA.debugLineNum = 243;BA.debugLine="Sub CreateRoundBitmap (Input As B4XBitmap, Size As";
 //BA.debugLineNum = 244;BA.debugLine="If Input.Width <> Input.Height Then";
if (_input.getWidth()!=_input.getHeight()) { 
 //BA.debugLineNum = 246;BA.debugLine="Dim l As Int = Min(Input.Width, Input.Height)";
_l = (int) (anywheresoftware.b4a.keywords.Common.Min(_input.getWidth(),_input.getHeight()));
 //BA.debugLineNum = 247;BA.debugLine="Input = Input.Crop(Input.Width / 2 - l / 2, Inpu";
_input = _input.Crop((int) (_input.getWidth()/(double)2-_l/(double)2),(int) (_input.getHeight()/(double)2-_l/(double)2),_l,_l);
 };
 //BA.debugLineNum = 249;BA.debugLine="Dim c As B4XCanvas";
_c = new anywheresoftware.b4a.objects.B4XCanvas();
 //BA.debugLineNum = 250;BA.debugLine="Dim xview As B4XView = xui.CreatePanel(\"\")";
_xview = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xview = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 251;BA.debugLine="xview.SetLayoutAnimated(0, 0, 0, Size, Size)";
_xview.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_size,_size);
 //BA.debugLineNum = 252;BA.debugLine="c.Initialize(xview)";
_c.Initialize(_xview);
 //BA.debugLineNum = 253;BA.debugLine="Dim path As B4XPath";
_path = new anywheresoftware.b4a.objects.B4XCanvas.B4XPath();
 //BA.debugLineNum = 254;BA.debugLine="path.InitializeOval(c.TargetRect)";
_path.InitializeOval(_c.getTargetRect());
 //BA.debugLineNum = 255;BA.debugLine="c.ClipPath(path)";
_c.ClipPath(_path);
 //BA.debugLineNum = 256;BA.debugLine="c.DrawBitmap(Input.Resize(Size, Size, False), c.T";
_c.DrawBitmap((android.graphics.Bitmap)(_input.Resize(_size,_size,anywheresoftware.b4a.keywords.Common.False).getObject()),_c.getTargetRect());
 //BA.debugLineNum = 257;BA.debugLine="c.RemoveClip";
_c.RemoveClip();
 //BA.debugLineNum = 259;BA.debugLine="c.Invalidate";
_c.Invalidate();
 //BA.debugLineNum = 260;BA.debugLine="Dim res As B4XBitmap = c.CreateBitmap";
_res = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_res = _c.CreateBitmap();
 //BA.debugLineNum = 261;BA.debugLine="c.Release";
_c.Release();
 //BA.debugLineNum = 262;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 263;BA.debugLine="End Sub";
return null;
}
public static void  _getdosen() throws Exception{
ResumableSub_GetDosen rsub = new ResumableSub_GetDosen(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetDosen extends BA.ResumableSub {
public ResumableSub_GetDosen(b4a.AbsenBackUp.dosen parent) {
this.parent = parent;
}
b4a.AbsenBackUp.dosen parent;
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
 //BA.debugLineNum = 145;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,dosen.getObject());
 //BA.debugLineNum = 146;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectDosen",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 147;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 148;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 149;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 150;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 151;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 152;BA.debugLine="AC_SpinnerKelas.AddAll(Array As String(row(0)))";
parent.mostCurrent._ac_spinnerkelas.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.ObjectToString(_row[(int) (0)])}));
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
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _getmatkul() throws Exception{
ResumableSub_GetMatkul rsub = new ResumableSub_GetMatkul(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetMatkul extends BA.ResumableSub {
public ResumableSub_GetMatkul(b4a.AbsenBackUp.dosen parent) {
this.parent = parent;
}
b4a.AbsenBackUp.dosen parent;
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
 //BA.debugLineNum = 132;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,dosen.getObject());
 //BA.debugLineNum = 133;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectMatkul",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 134;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 135;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 136;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 137;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 138;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 139;BA.debugLine="AC_SpinnerMatkul.AddAll(Array As String(row(0))";
parent.mostCurrent._ac_spinnermatkul.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.ObjectToString(_row[(int) (0)])}));
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
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _getmhs(String _kelass) throws Exception{
ResumableSub_GetMHS rsub = new ResumableSub_GetMHS(null,_kelass);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetMHS extends BA.ResumableSub {
public ResumableSub_GetMHS(b4a.AbsenBackUp.dosen parent,String _kelass) {
this.parent = parent;
this._kelass = _kelass;
}
b4a.AbsenBackUp.dosen parent;
String _kelass;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
int _no = 0;
Object[] _row = null;
anywheresoftware.b4a.BA.IterableList group8;
int index8;
int groupLen8;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 158;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,dosen.getObject());
 //BA.debugLineNum = 159;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectMHS",new Object[]{(Object)(_kelass)});
 //BA.debugLineNum = 160;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 161;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 162;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 163;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 164;BA.debugLine="Dim no As Int = 0";
_no = (int) (0);
 //BA.debugLineNum = 165;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group8 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index8 = 0;
groupLen8 = group8.getSize();
this.state = 11;
if (true) break;

case 11:
//C
this.state = 7;
if (index8 < groupLen8) {
this.state = 6;
_row = (Object[])(group8.Get(index8));}
if (true) break;

case 12:
//C
this.state = 11;
index8++;
if (true) break;

case 6:
//C
this.state = 12;
 //BA.debugLineNum = 166;BA.debugLine="no = no + 1";
_no = (int) (_no+1);
 //BA.debugLineNum = 167;BA.debugLine="CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.W";
parent.mostCurrent._clv_namamhs._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_createitem(parent.mostCurrent._clv_namamhs._asview().getWidth(),BA.NumberToString(_no),BA.ObjectToString(_row[(int) (1)])).getObject())),(Object)(""));
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
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _getprofil(String _namas) throws Exception{
ResumableSub_GetProfil rsub = new ResumableSub_GetProfil(null,_namas);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetProfil extends BA.ResumableSub {
public ResumableSub_GetProfil(b4a.AbsenBackUp.dosen parent,String _namas) {
this.parent = parent;
this._namas = _namas;
}
b4a.AbsenBackUp.dosen parent;
String _namas;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
Object[] _row = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _gambar = null;
byte[] _buffer = null;
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
 //BA.debugLineNum = 215;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,dosen.getObject());
 //BA.debugLineNum = 216;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"getPho";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"getPhotoDosen",new Object[]{(Object)(_namas)});
 //BA.debugLineNum = 217;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 218;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 10;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 219;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 220;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 12;
return;
case 12:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 221;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group7 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index7 = 0;
groupLen7 = group7.getSize();
this.state = 13;
if (true) break;

case 13:
//C
this.state = 7;
if (index7 < groupLen7) {
this.state = 6;
_row = (Object[])(group7.Get(index7));}
if (true) break;

case 14:
//C
this.state = 13;
index7++;
if (true) break;

case 6:
//C
this.state = 14;
 //BA.debugLineNum = 222;BA.debugLine="Log(row(0))";
anywheresoftware.b4a.keywords.Common.LogImpl("05111816",BA.ObjectToString(_row[(int) (0)]),0);
 //BA.debugLineNum = 224;BA.debugLine="Dim gambar As Bitmap";
_gambar = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 225;BA.debugLine="Dim buffer() As Byte";
_buffer = new byte[(int) (0)];
;
 //BA.debugLineNum = 227;BA.debugLine="buffer = row(0)";
_buffer = (byte[])(_row[(int) (0)]);
 //BA.debugLineNum = 228;BA.debugLine="gambar = req.BytesToImage(buffer)";
_gambar = _req._bytestoimage /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (_buffer);
 //BA.debugLineNum = 229;BA.debugLine="img = gambar";
parent.mostCurrent._img.setObject((android.graphics.Bitmap)(_gambar.getObject()));
 //BA.debugLineNum = 230;BA.debugLine="xIV = img_profil";
parent.mostCurrent._xiv.setObject((java.lang.Object)(parent.mostCurrent._img_profil.getObject()));
 //BA.debugLineNum = 231;BA.debugLine="xIV.SetBitmap(CreateRoundBitmap(img, xIV.Width)";
parent.mostCurrent._xiv.SetBitmap((android.graphics.Bitmap)(_createroundbitmap(parent.mostCurrent._img,parent.mostCurrent._xiv.getWidth()).getObject()));
 //BA.debugLineNum = 233;BA.debugLine="bufferI = CreateRequest.ImageToBytes(img_profil";
parent._bufferi = _createrequest()._imagetobytes /*byte[]*/ ((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(parent.mostCurrent._img_profil.getBitmap())));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 237;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("05111831",_j._errormessage /*String*/ ,0);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 239;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Private AC_SpinnerKelas As ACSpinner";
mostCurrent._ac_spinnerkelas = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private AC_SpinnerMatkul As ACSpinner";
mostCurrent._ac_spinnermatkul = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private ACSpinnerKet As ACSpinner";
mostCurrent._acspinnerket = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private CLV_NamaMHS As CustomListView";
mostCurrent._clv_namamhs = new b4a.example3.customlistview();
 //BA.debugLineNum = 27;BA.debugLine="Private pnl_CLVAbsenDosen As Panel";
mostCurrent._pnl_clvabsendosen = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lbl_namaMHS As Label";
mostCurrent._lbl_namamhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lbl_Nomor As Label";
mostCurrent._lbl_nomor = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private img_KetMHS As ImageView";
mostCurrent._img_ketmhs = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim pic() As String";
mostCurrent._pic = new String[(int) (0)];
java.util.Arrays.fill(mostCurrent._pic,"");
 //BA.debugLineNum = 33;BA.debugLine="Dim count As Int";
_count = 0;
 //BA.debugLineNum = 40;BA.debugLine="Private lbl_TanggalDosen As Label";
mostCurrent._lbl_tanggaldosen = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lbl_namadosen As Label";
mostCurrent._lbl_namadosen = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private btn_selesaipengambilan As Button";
mostCurrent._btn_selesaipengambilan = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private btn_rekap_absen As Button";
mostCurrent._btn_rekap_absen = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private btn_kalkulasi As Button";
mostCurrent._btn_kalkulasi = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private pnl_kalkulasi As Panel";
mostCurrent._pnl_kalkulasi = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private pnl_rekap_absen As Panel";
mostCurrent._pnl_rekap_absen = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private pnl_absen_harini As Panel";
mostCurrent._pnl_absen_harini = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Dim img As B4XBitmap";
mostCurrent._img = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Dim xIV As B4XView";
mostCurrent._xiv = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private img_profil As ImageView";
mostCurrent._img_profil = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_click() throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Sub Panel1_Click";
 //BA.debugLineNum = 128;BA.debugLine="StartActivity(BarcodeScanner)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._barcodescanner.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return "";
}
public static String  _pnl_absen_harini_click() throws Exception{
 //BA.debugLineNum = 279;BA.debugLine="Sub pnl_absen_harini_Click";
 //BA.debugLineNum = 280;BA.debugLine="StartActivity(absen_hari_ini)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._absen_hari_ini.getObject()));
 //BA.debugLineNum = 281;BA.debugLine="End Sub";
return "";
}
public static String  _pnl_kalkulasi_click() throws Exception{
 //BA.debugLineNum = 287;BA.debugLine="Sub pnl_kalkulasi_Click";
 //BA.debugLineNum = 288;BA.debugLine="StartActivity(KalkulasiAbsen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._kalkulasiabsen.getObject()));
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}
public static String  _pnl_rekap_absen_click() throws Exception{
 //BA.debugLineNum = 283;BA.debugLine="Sub pnl_rekap_absen_Click";
 //BA.debugLineNum = 284;BA.debugLine="StartActivity(Rekap_Absen_Dosen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._rekap_absen_dosen.getObject()));
 //BA.debugLineNum = 285;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 10;BA.debugLine="Dim dosennn As String";
_dosennn = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim kelas As String";
_kelas = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim bufferI () As Byte";
_bufferi = new byte[(int) (0)];
;
 //BA.debugLineNum = 13;BA.debugLine="Dim matkul as String";
_matkul = "";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.BitmapDrawable  _todrawable(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp) throws Exception{
anywheresoftware.b4a.objects.drawable.BitmapDrawable _b = null;
 //BA.debugLineNum = 93;BA.debugLine="Sub toDrawable (bmp As Bitmap) As BitmapDrawable";
 //BA.debugLineNum = 94;BA.debugLine="Dim b As BitmapDrawable";
_b = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 95;BA.debugLine="b.Initialize(bmp)";
_b.Initialize((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 96;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return null;
}
}
