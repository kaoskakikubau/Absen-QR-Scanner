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

public class rekap_absen_dosen extends Activity implements B4AActivity{
	public static rekap_absen_dosen mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.AbsenBackUp", "b4a.AbsenBackUp.rekap_absen_dosen");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (rekap_absen_dosen).");
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
		activityBA = new BA(this, layout, processBA, "b4a.AbsenBackUp", "b4a.AbsenBackUp.rekap_absen_dosen");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.AbsenBackUp.rekap_absen_dosen", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (rekap_absen_dosen) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (rekap_absen_dosen) Resume **");
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
		return rekap_absen_dosen.class;
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
        BA.LogInfo("** Activity (rekap_absen_dosen) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            rekap_absen_dosen mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (rekap_absen_dosen) Resume **");
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
public static String _kelas = "";
public static String _mtkl = "";
public de.amberhome.objects.appcompat.ACSpinnerWrapper _ac_spinnerkelas = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _ac_spinnermatku = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltanggal = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public static int _satoption = 0;
public static int _sunoption = 0;
public static boolean _startmonday = false;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_nomor = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_namamhs_rekap = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_clvabsendosen = null;
public b4a.example3.customlistview _clv_namamhs = null;
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
public b4a.AbsenBackUp.barcodescanner _barcodescanner = null;
public b4a.AbsenBackUp.barcodemhs _barcodemhs = null;
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
 //BA.debugLineNum = 51;BA.debugLine="Sub AC_SpinnerKelas_ItemClick (Position As Int, Va";
 //BA.debugLineNum = 52;BA.debugLine="kelas = Value";
_kelas = BA.ObjectToString(_value);
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _ac_spinnermatku_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub AC_SpinnerMatku_ItemClick (Position As Int, Va";
 //BA.debugLineNum = 57;BA.debugLine="mtkl = Value";
_mtkl = BA.ObjectToString(_value);
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"lay_RekapAbsenDosen\")";
mostCurrent._activity.LoadLayout("lay_RekapAbsenDosen",mostCurrent.activityBA);
 //BA.debugLineNum = 37;BA.debugLine="GetDosen";
_getdosen();
 //BA.debugLineNum = 39;BA.debugLine="GetMatkul";
_getmatkul();
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 140;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 141;BA.debugLine="CLV_NamaMHS.Clear";
mostCurrent._clv_namamhs._clear();
 //BA.debugLineNum = 142;BA.debugLine="GetAbsenDate(kelas, mtkl,lblTanggal.Text, Dosen.d";
_getabsendate(_kelas,_mtkl,mostCurrent._lbltanggal.getText(),mostCurrent._dosen._dosennn /*String*/ );
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _createitem(int _width,String _nomor,String _nama) throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
int _height = 0;
 //BA.debugLineNum = 61;BA.debugLine="Private Sub CreateItem(Width As Int, nomor As Stri";
 //BA.debugLineNum = 62;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 63;BA.debugLine="Dim height As Int = 55dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55));
 //BA.debugLineNum = 65;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, Width, height)";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
 //BA.debugLineNum = 66;BA.debugLine="p.LoadLayout(\"pnl_CLV_RekapAbsenDosen\")";
_p.LoadLayout("pnl_CLV_RekapAbsenDosen",mostCurrent.activityBA);
 //BA.debugLineNum = 67;BA.debugLine="lbl_Nomor.Text = nomor";
mostCurrent._lbl_nomor.setText(BA.ObjectToCharSequence(_nomor));
 //BA.debugLineNum = 68;BA.debugLine="lbl_namaMHS_rekap.Text = nama";
mostCurrent._lbl_namamhs_rekap.setText(BA.ObjectToCharSequence(_nama));
 //BA.debugLineNum = 70;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getObject()));
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return null;
}
public static void  _getabsendate(String _kls,String _matkul,String _tanggal,String _dosennn) throws Exception{
ResumableSub_GetAbsenDate rsub = new ResumableSub_GetAbsenDate(null,_kls,_matkul,_tanggal,_dosennn);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetAbsenDate extends BA.ResumableSub {
public ResumableSub_GetAbsenDate(b4a.AbsenBackUp.rekap_absen_dosen parent,String _kls,String _matkul,String _tanggal,String _dosennn) {
this.parent = parent;
this._kls = _kls;
this._matkul = _matkul;
this._tanggal = _tanggal;
this._dosennn = _dosennn;
}
b4a.AbsenBackUp.rekap_absen_dosen parent;
String _kls;
String _matkul;
String _tanggal;
String _dosennn;
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
 //BA.debugLineNum = 124;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,rekap_absen_dosen.getObject());
 //BA.debugLineNum = 125;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"GetAbs";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"GetAbsenDate",new Object[]{(Object)(_kls),(Object)(_matkul),(Object)(_tanggal),(Object)(_dosennn)});
 //BA.debugLineNum = 126;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 127;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 128;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 129;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 130;BA.debugLine="Dim no As Int = 0";
_no = (int) (0);
 //BA.debugLineNum = 131;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 132;BA.debugLine="no = no + 1";
_no = (int) (_no+1);
 //BA.debugLineNum = 133;BA.debugLine="CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.W";
parent.mostCurrent._clv_namamhs._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_createitem(parent.mostCurrent._clv_namamhs._asview().getWidth(),BA.NumberToString(_no),BA.ObjectToString(_row[(int) (0)])).getObject())),(Object)(""));
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
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(b4a.AbsenBackUp.httpjob _j) throws Exception{
}
public static void  _req_result(b4a.AbsenBackUp.main._dbresult _res) throws Exception{
}
public static void  _getdosen() throws Exception{
ResumableSub_GetDosen rsub = new ResumableSub_GetDosen(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetDosen extends BA.ResumableSub {
public ResumableSub_GetDosen(b4a.AbsenBackUp.rekap_absen_dosen parent) {
this.parent = parent;
}
b4a.AbsenBackUp.rekap_absen_dosen parent;
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
 //BA.debugLineNum = 75;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,rekap_absen_dosen.getObject());
 //BA.debugLineNum = 76;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectDosen",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 77;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 78;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 79;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 80;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 81;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 82;BA.debugLine="AC_SpinnerKelas.AddAll(Array As String(row(0)))";
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
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
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
public ResumableSub_GetMatkul(b4a.AbsenBackUp.rekap_absen_dosen parent) {
this.parent = parent;
}
b4a.AbsenBackUp.rekap_absen_dosen parent;
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
 //BA.debugLineNum = 88;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,rekap_absen_dosen.getObject());
 //BA.debugLineNum = 89;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectMatkul",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 90;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 91;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 92;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 93;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 94;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 95;BA.debugLine="AC_SpinnerMatku.AddAll(Array As String(row(0)))";
parent.mostCurrent._ac_spinnermatku.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.ObjectToString(_row[(int) (0)])}));
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
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private AC_SpinnerKelas As ACSpinner";
mostCurrent._ac_spinnerkelas = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private AC_SpinnerMatku As ACSpinner";
mostCurrent._ac_spinnermatku = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private lblTanggal As Label";
mostCurrent._lbltanggal = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private satOption, sunOption As Int";
_satoption = 0;
_sunoption = 0;
 //BA.debugLineNum = 25;BA.debugLine="Private startMonday As Boolean";
_startmonday = false;
 //BA.debugLineNum = 27;BA.debugLine="Private lbl_Nomor As Label";
mostCurrent._lbl_nomor = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lbl_namaMHS_rekap As Label";
mostCurrent._lbl_namamhs_rekap = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private pnl_CLVAbsenDosen As Panel";
mostCurrent._pnl_clvabsendosen = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private CLV_NamaMHS As CustomListView";
mostCurrent._clv_namamhs = new b4a.example3.customlistview();
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _lbltanggal_click() throws Exception{
long _i = 0L;
b4a.AbsenBackUp.datedialogs _d = null;
String _s = "";
String _a = "";
long _t = 0L;
 //BA.debugLineNum = 100;BA.debugLine="Sub lblTanggal_Click";
 //BA.debugLineNum = 101;BA.debugLine="Dim I As Long";
_i = 0L;
 //BA.debugLineNum = 102;BA.debugLine="Dim D As DateDialogs";
_d = new b4a.AbsenBackUp.datedialogs();
 //BA.debugLineNum = 103;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 104;BA.debugLine="Dim a As String";
_a = "";
 //BA.debugLineNum = 106;BA.debugLine="Log(DateTime.DateFormat)";
anywheresoftware.b4a.keywords.Common.LogImpl("013565958",anywheresoftware.b4a.keywords.Common.DateTime.getDateFormat(),0);
 //BA.debugLineNum = 107;BA.debugLine="D.Initialize(Activity, DateTime.Now)";
_d._initialize /*String*/ (mostCurrent.activityBA,mostCurrent._activity,anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 108;BA.debugLine="D.RedSaturday=satOption";
_d._setredsaturday(_satoption);
 //BA.debugLineNum = 109;BA.debugLine="D.RedSunday=sunOption";
_d._setredsunday(_sunoption);
 //BA.debugLineNum = 110;BA.debugLine="D.StartOnMonday=startMonday";
_d._setstartonmonday(_startmonday);
 //BA.debugLineNum = 111;BA.debugLine="I=D.Show(\"Select Date\")";
_i = (long) (_d._show /*int*/ ("Select Date"));
 //BA.debugLineNum = 113;BA.debugLine="a = DateTime.GetMonth(D.DateSelected) & \"/\" & Dat";
_a = BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_d._dateselected /*long*/ ))+"/"+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(_d._dateselected /*long*/ ))+"/"+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetYear(_d._dateselected /*long*/ ));
 //BA.debugLineNum = 115;BA.debugLine="DateTime.DateFormat = \"MM/dd/yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("MM/dd/yyyy");
 //BA.debugLineNum = 116;BA.debugLine="Dim t As Long";
_t = 0L;
 //BA.debugLineNum = 117;BA.debugLine="t = DateTime.DateParse(a)";
_t = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_a);
 //BA.debugLineNum = 118;BA.debugLine="DateTime.DateFormat = \"MM/dd/yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("MM/dd/yyyy");
 //BA.debugLineNum = 119;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 120;BA.debugLine="lblTanggal.Text = DateTime.Date(t)";
mostCurrent._lbltanggal.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(_t)));
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 11;BA.debugLine="Dim kelas As String";
_kelas = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim mtkl As String";
_mtkl = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
}
