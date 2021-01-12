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

public class profilkalkulasi extends Activity implements B4AActivity{
	public static profilkalkulasi mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.AbsenBackUp", "b4a.AbsenBackUp.profilkalkulasi");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (profilkalkulasi).");
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
		activityBA = new BA(this, layout, processBA, "b4a.AbsenBackUp", "b4a.AbsenBackUp.profilkalkulasi");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.AbsenBackUp.profilkalkulasi", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (profilkalkulasi) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (profilkalkulasi) Resume **");
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
		return profilkalkulasi.class;
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
        BA.LogInfo("** Activity (profilkalkulasi) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            profilkalkulasi mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (profilkalkulasi) Resume **");
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
public static String _nama = "";
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnama_mhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnpm_mhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblkelas_mhs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_nomor = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_namamhs_kalkulasi = null;
public b4a.example3.customlistview _clv_namamhs_ = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _img = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _xiv = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_profil = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_total = null;
public b4a.AbsenBackUp.main _main = null;
public b4a.AbsenBackUp.login _login = null;
public b4a.AbsenBackUp.barcodemahasiswa _barcodemahasiswa = null;
public b4a.AbsenBackUp.mahasiswa _mahasiswa = null;
public b4a.AbsenBackUp.rekapmahasiswa _rekapmahasiswa = null;
public b4a.AbsenBackUp.dosen _dosen = null;
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
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"lay_ProfilKalkulasi\")";
mostCurrent._activity.LoadLayout("lay_ProfilKalkulasi",mostCurrent.activityBA);
 //BA.debugLineNum = 34;BA.debugLine="GetProfile(nama)";
_getprofile(_nama);
 //BA.debugLineNum = 35;BA.debugLine="Log(nama)";
anywheresoftware.b4a.keywords.Common.LogImpl("02686980",_nama,0);
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
int _i = 0;
 //BA.debugLineNum = 125;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 126;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 127;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 128;BA.debugLine="StartActivity(KalkulasiAbsen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._kalkulasiabsen.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _createitem(int _width,String _nomor,String _namas) throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
int _height = 0;
 //BA.debugLineNum = 94;BA.debugLine="Private Sub CreateItem(Width As Int, nomor As Stri";
 //BA.debugLineNum = 95;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 96;BA.debugLine="Dim height As Int = 55dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55));
 //BA.debugLineNum = 98;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, Width, height)";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
 //BA.debugLineNum = 99;BA.debugLine="p.LoadLayout(\"pnl_CLV_ProfilKalkulasi\")";
_p.LoadLayout("pnl_CLV_ProfilKalkulasi",mostCurrent.activityBA);
 //BA.debugLineNum = 100;BA.debugLine="lbl_Nomor.Text = nomor";
mostCurrent._lbl_nomor.setText(BA.ObjectToCharSequence(_nomor));
 //BA.debugLineNum = 101;BA.debugLine="lbl_namaMHS_Kalkulasi.Text = namas";
mostCurrent._lbl_namamhs_kalkulasi.setText(BA.ObjectToCharSequence(_namas));
 //BA.debugLineNum = 103;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getObject()));
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper  _createroundbitmap(anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _input,int _size) throws Exception{
int _l = 0;
anywheresoftware.b4a.objects.B4XCanvas _c = null;
anywheresoftware.b4a.objects.B4XViewWrapper _xview = null;
anywheresoftware.b4a.objects.B4XCanvas.B4XPath _path = null;
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _res = null;
 //BA.debugLineNum = 71;BA.debugLine="Sub CreateRoundBitmap (Input As B4XBitmap, Size As";
 //BA.debugLineNum = 72;BA.debugLine="If Input.Width <> Input.Height Then";
if (_input.getWidth()!=_input.getHeight()) { 
 //BA.debugLineNum = 74;BA.debugLine="Dim l As Int = Min(Input.Width, Input.Height)";
_l = (int) (anywheresoftware.b4a.keywords.Common.Min(_input.getWidth(),_input.getHeight()));
 //BA.debugLineNum = 75;BA.debugLine="Input = Input.Crop(Input.Width / 2 - l / 2, Inpu";
_input = _input.Crop((int) (_input.getWidth()/(double)2-_l/(double)2),(int) (_input.getHeight()/(double)2-_l/(double)2),_l,_l);
 };
 //BA.debugLineNum = 77;BA.debugLine="Dim c As B4XCanvas";
_c = new anywheresoftware.b4a.objects.B4XCanvas();
 //BA.debugLineNum = 78;BA.debugLine="Dim xview As B4XView = xui.CreatePanel(\"\")";
_xview = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xview = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 79;BA.debugLine="xview.SetLayoutAnimated(0, 0, 0, Size, Size)";
_xview.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_size,_size);
 //BA.debugLineNum = 80;BA.debugLine="c.Initialize(xview)";
_c.Initialize(_xview);
 //BA.debugLineNum = 81;BA.debugLine="Dim path As B4XPath";
_path = new anywheresoftware.b4a.objects.B4XCanvas.B4XPath();
 //BA.debugLineNum = 82;BA.debugLine="path.InitializeOval(c.TargetRect)";
_path.InitializeOval(_c.getTargetRect());
 //BA.debugLineNum = 83;BA.debugLine="c.ClipPath(path)";
_c.ClipPath(_path);
 //BA.debugLineNum = 84;BA.debugLine="c.DrawBitmap(Input.Resize(Size, Size, False), c.T";
_c.DrawBitmap((android.graphics.Bitmap)(_input.Resize(_size,_size,anywheresoftware.b4a.keywords.Common.False).getObject()),_c.getTargetRect());
 //BA.debugLineNum = 85;BA.debugLine="c.RemoveClip";
_c.RemoveClip();
 //BA.debugLineNum = 87;BA.debugLine="c.Invalidate";
_c.Invalidate();
 //BA.debugLineNum = 88;BA.debugLine="Dim res As B4XBitmap = c.CreateBitmap";
_res = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_res = _c.CreateBitmap();
 //BA.debugLineNum = 89;BA.debugLine="c.Release";
_c.Release();
 //BA.debugLineNum = 90;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return null;
}
public static void  _getabsentoday(String _nm,String _kelas,String _npm,String _mtkul,String _dosenn) throws Exception{
ResumableSub_GetAbsenToday rsub = new ResumableSub_GetAbsenToday(null,_nm,_kelas,_npm,_mtkul,_dosenn);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetAbsenToday extends BA.ResumableSub {
public ResumableSub_GetAbsenToday(b4a.AbsenBackUp.profilkalkulasi parent,String _nm,String _kelas,String _npm,String _mtkul,String _dosenn) {
this.parent = parent;
this._nm = _nm;
this._kelas = _kelas;
this._npm = _npm;
this._mtkul = _mtkul;
this._dosenn = _dosenn;
}
b4a.AbsenBackUp.profilkalkulasi parent;
String _nm;
String _kelas;
String _npm;
String _mtkul;
String _dosenn;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
int _no = 0;
Object[] _row = null;
anywheresoftware.b4a.BA.IterableList group9;
int index9;
int groupLen9;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 108;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,profilkalkulasi.getObject());
 //BA.debugLineNum = 109;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"getDat";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"getDateProfil",new Object[]{(Object)(_nm),(Object)(_kelas),(Object)(_npm),(Object)(_mtkul),(Object)(_dosenn)});
 //BA.debugLineNum = 110;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 111;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 112;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 113;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 114;BA.debugLine="Dim no As Int = 0";
_no = (int) (0);
 //BA.debugLineNum = 115;BA.debugLine="CLV_NamaMHS_.Clear";
parent.mostCurrent._clv_namamhs_._clear();
 //BA.debugLineNum = 116;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group9 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index9 = 0;
groupLen9 = group9.getSize();
this.state = 11;
if (true) break;

case 11:
//C
this.state = 7;
if (index9 < groupLen9) {
this.state = 6;
_row = (Object[])(group9.Get(index9));}
if (true) break;

case 12:
//C
this.state = 11;
index9++;
if (true) break;

case 6:
//C
this.state = 12;
 //BA.debugLineNum = 117;BA.debugLine="no = no + 1";
_no = (int) (_no+1);
 //BA.debugLineNum = 118;BA.debugLine="CLV_NamaMHS_.Add(CreateItem(CLV_NamaMHS_.AsView";
parent.mostCurrent._clv_namamhs_._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_createitem(parent.mostCurrent._clv_namamhs_._asview().getWidth(),BA.NumberToString(_no),BA.ObjectToString(_row[(int) (0)])).getObject())),(Object)(""));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 8;
;
 //BA.debugLineNum = 121;BA.debugLine="lbl_total.Text = no";
parent.mostCurrent._lbl_total.setText(BA.ObjectToCharSequence(_no));
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(b4a.AbsenBackUp.httpjob _j) throws Exception{
}
public static void  _req_result(b4a.AbsenBackUp.main._dbresult _res) throws Exception{
}
public static void  _getprofile(String _namas) throws Exception{
ResumableSub_GetProfile rsub = new ResumableSub_GetProfile(null,_namas);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetProfile extends BA.ResumableSub {
public ResumableSub_GetProfile(b4a.AbsenBackUp.profilkalkulasi parent,String _namas) {
this.parent = parent;
this._namas = _namas;
}
b4a.AbsenBackUp.profilkalkulasi parent;
String _namas;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
Object[] _row = null;
String _nm = "";
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _gambar = null;
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
 //BA.debugLineNum = 47;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,profilkalkulasi.getObject());
 //BA.debugLineNum = 48;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"getPro";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"getProfil",new Object[]{(Object)(_namas)});
 //BA.debugLineNum = 49;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 50;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 51;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 52;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 12;
return;
case 12:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 53;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 54;BA.debugLine="Dim nm As String";
_nm = "";
 //BA.debugLineNum = 55;BA.debugLine="lblNama_mhs.Text = row(1)";
parent.mostCurrent._lblnama_mhs.setText(BA.ObjectToCharSequence(_row[(int) (1)]));
 //BA.debugLineNum = 56;BA.debugLine="lblNpm_mhs.Text = row(0)";
parent.mostCurrent._lblnpm_mhs.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
 //BA.debugLineNum = 57;BA.debugLine="lblKelas_mhs.Text = row(2)";
parent.mostCurrent._lblkelas_mhs.setText(BA.ObjectToCharSequence(_row[(int) (2)]));
 //BA.debugLineNum = 59;BA.debugLine="Dim gambar As Bitmap = req.BytesToImage(row(3))";
_gambar = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_gambar = _req._bytestoimage /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ ((byte[])(_row[(int) (3)]));
 //BA.debugLineNum = 60;BA.debugLine="img = gambar";
parent.mostCurrent._img.setObject((android.graphics.Bitmap)(_gambar.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="xIV = img_profil";
parent.mostCurrent._xiv.setObject((java.lang.Object)(parent.mostCurrent._img_profil.getObject()));
 //BA.debugLineNum = 62;BA.debugLine="xIV.SetBitmap(CreateRoundBitmap(img, xIV.Width)";
parent.mostCurrent._xiv.SetBitmap((android.graphics.Bitmap)(_createroundbitmap(parent.mostCurrent._img,parent.mostCurrent._xiv.getWidth()).getObject()));
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
 //BA.debugLineNum = 65;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("02883603",_j._errormessage /*String*/ ,0);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 67;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 68;BA.debugLine="GetAbsenToday(lblNama_mhs.Text, lblKelas_mhs.Text";
_getabsentoday(parent.mostCurrent._lblnama_mhs.getText(),parent.mostCurrent._lblkelas_mhs.getText(),parent.mostCurrent._lblnpm_mhs.getText(),parent.mostCurrent._kalkulasiabsen._mtkl /*String*/ ,parent.mostCurrent._dosen._dosennn /*String*/ );
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private lblNama_mhs As Label";
mostCurrent._lblnama_mhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private lblNpm_mhs As Label";
mostCurrent._lblnpm_mhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private lblKelas_mhs As Label";
mostCurrent._lblkelas_mhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lbl_Nomor As Label";
mostCurrent._lbl_nomor = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private lbl_namaMHS_Kalkulasi As Label";
mostCurrent._lbl_namamhs_kalkulasi = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private CLV_NamaMHS_ As CustomListView";
mostCurrent._clv_namamhs_ = new b4a.example3.customlistview();
 //BA.debugLineNum = 24;BA.debugLine="Dim img As B4XBitmap";
mostCurrent._img = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim xIV As B4XView";
mostCurrent._xiv = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private img_profil As ImageView";
mostCurrent._img_profil = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lbl_total As Label";
mostCurrent._lbl_total = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim nama As String";
_nama = "";
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
}
