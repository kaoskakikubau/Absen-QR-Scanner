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

public class kalkulasiabsen extends Activity implements B4AActivity{
	public static kalkulasiabsen mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.AbsenBackUp", "b4a.AbsenBackUp.kalkulasiabsen");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (kalkulasiabsen).");
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
		activityBA = new BA(this, layout, processBA, "b4a.AbsenBackUp", "b4a.AbsenBackUp.kalkulasiabsen");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.AbsenBackUp.kalkulasiabsen", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (kalkulasiabsen) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (kalkulasiabsen) Resume **");
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
		return kalkulasiabsen.class;
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
        BA.LogInfo("** Activity (kalkulasiabsen) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            kalkulasiabsen mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (kalkulasiabsen) Resume **");
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
public static String _kls = "";
public static String _mtkl = "";
public static String _coba = "";
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_jmlmasuk = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcontent = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_foto = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _ac_spinnerkelas = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _ac_spinnermatkul = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_proses_kalkulasi = null;
public b4a.example3.customlistview _clv_namamhs_ = null;
public b4a.AbsenBackUp.main _main = null;
public b4a.AbsenBackUp.login _login = null;
public b4a.AbsenBackUp.barcodemahasiswa _barcodemahasiswa = null;
public b4a.AbsenBackUp.mahasiswa _mahasiswa = null;
public b4a.AbsenBackUp.profilkalkulasi _profilkalkulasi = null;
public b4a.AbsenBackUp.rekapmahasiswa _rekapmahasiswa = null;
public b4a.AbsenBackUp.dosen _dosen = null;
public b4a.AbsenBackUp.absen_hari_ini _absen_hari_ini = null;
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
 //BA.debugLineNum = 125;BA.debugLine="Sub AC_SpinnerKelas_ItemClick (Position As Int, Va";
 //BA.debugLineNum = 126;BA.debugLine="kls = Value";
_kls = BA.ObjectToString(_value);
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _ac_spinnermatkul_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub AC_SpinnerMatkul_ItemClick (Position As Int, V";
 //BA.debugLineNum = 130;BA.debugLine="mtkl = Value";
_mtkl = BA.ObjectToString(_value);
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"lay_KalkulasiAbsen\")";
mostCurrent._activity.LoadLayout("lay_KalkulasiAbsen",mostCurrent.activityBA);
 //BA.debugLineNum = 33;BA.debugLine="GetMatkul";
_getmatkul();
 //BA.debugLineNum = 35;BA.debugLine="GetDosen";
_getdosen();
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
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
public static String  _btn_proses_kalkulasi_click() throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Sub btn_proses_Kalkulasi_Click";
 //BA.debugLineNum = 134;BA.debugLine="CountAbsenDosen(kls, mtkl, Dosen.dosennn)";
_countabsendosen(_kls,_mtkl,mostCurrent._dosen._dosennn /*String*/ );
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _circularimg(anywheresoftware.b4a.objects.PanelWrapper _target,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _images,int _color,int _bwidth) throws Exception{
com.maximussoft.circularimageview.CircularImageViewWrapper _civ = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _img = null;
 //BA.debugLineNum = 66;BA.debugLine="Sub CircularImg(Target As Panel, Images As Bitmap,";
 //BA.debugLineNum = 67;BA.debugLine="Dim civ As CircularImageView";
_civ = new com.maximussoft.circularimageview.CircularImageViewWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Dim img As BitmapDrawable";
_img = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 69;BA.debugLine="civ.Initialize(\"\")";
_civ.Initialize(processBA,"");
 //BA.debugLineNum = 70;BA.debugLine="civ.Color = Colors.Transparent";
_civ.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 71;BA.debugLine="civ.BorderWidth = bwidth";
_civ.setBorderWidth(_bwidth);
 //BA.debugLineNum = 72;BA.debugLine="civ.BorderColor = Colors.White";
_civ.setBorderColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 73;BA.debugLine="civ.AddShadowLayer(4,4,4,Colors.Transparent)";
_civ.AddShadowLayer((float) (4),(float) (4),(float) (4),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 74;BA.debugLine="img.Initialize(Images)";
_img.Initialize((android.graphics.Bitmap)(_images.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="civ.SetDrawable(img)";
_civ.SetDrawable(_img);
 //BA.debugLineNum = 76;BA.debugLine="Target.AddView(civ,0,0,Target.Width,Target.Height";
_target.AddView((android.view.View)(_civ.getObject()),(int) (0),(int) (0),_target.getWidth(),_target.getHeight());
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _clv_namamhs__itemclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub CLV_NamaMHS__ItemClick (Index As Int, Value As";
 //BA.debugLineNum = 47;BA.debugLine="ProfilKalkulasi.nama = Value";
mostCurrent._profilkalkulasi._nama /*String*/  = BA.ObjectToString(_value);
 //BA.debugLineNum = 48;BA.debugLine="StartActivity(ProfilKalkulasi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profilkalkulasi.getObject()));
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static void  _countabsendosen(String _kelas,String _matkul,String _dosenn) throws Exception{
ResumableSub_CountAbsenDosen rsub = new ResumableSub_CountAbsenDosen(null,_kelas,_matkul,_dosenn);
rsub.resume(processBA, null);
}
public static class ResumableSub_CountAbsenDosen extends BA.ResumableSub {
public ResumableSub_CountAbsenDosen(b4a.AbsenBackUp.kalkulasiabsen parent,String _kelas,String _matkul,String _dosenn) {
this.parent = parent;
this._kelas = _kelas;
this._matkul = _matkul;
this._dosenn = _dosenn;
}
b4a.AbsenBackUp.kalkulasiabsen parent;
String _kelas;
String _matkul;
String _dosenn;
b4a.AbsenBackUp.dbrequestmanager _req = null;
b4a.AbsenBackUp.main._dbcommand _cmd = null;
b4a.AbsenBackUp.httpjob _j = null;
b4a.AbsenBackUp.main._dbresult _res = null;
int _title = 0;
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
 //BA.debugLineNum = 81;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,kalkulasiabsen.getObject());
 //BA.debugLineNum = 82;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"CountA";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"CountAbsenDosen",new Object[]{(Object)(_kelas),(Object)(_matkul),(Object)(_dosenn)});
 //BA.debugLineNum = 83;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 84;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 85;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 86;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 87;BA.debugLine="Dim title As Int";
_title = 0;
 //BA.debugLineNum = 88;BA.debugLine="CLV_NamaMHS_.Clear";
parent.mostCurrent._clv_namamhs_._clear();
 //BA.debugLineNum = 89;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 90;BA.debugLine="title = title + 1";
_title = (int) (_title+1);
 //BA.debugLineNum = 94;BA.debugLine="CLV_NamaMHS_.Add(CreateItem(CLV_NamaMHS_.AsView";
parent.mostCurrent._clv_namamhs_._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_createitem(parent.mostCurrent._clv_namamhs_._asview().getWidth(),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gunadarma.jpg"),BA.ObjectToString(_row[(int) (1)]),BA.ObjectToString(_row[(int) (2)])).getObject())),_row[(int) (1)]);
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
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(b4a.AbsenBackUp.httpjob _j) throws Exception{
}
public static void  _req_result(b4a.AbsenBackUp.main._dbresult _res) throws Exception{
}
public static anywheresoftware.b4a.objects.PanelWrapper  _createitem(int _width,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _image,String _content,String _lbls) throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
int _height = 0;
 //BA.debugLineNum = 53;BA.debugLine="Private Sub CreateItem(Width As Int, Image As Bitm";
 //BA.debugLineNum = 54;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 55;BA.debugLine="Dim height As Int = 80dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
 //BA.debugLineNum = 57;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, Width, height)";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
 //BA.debugLineNum = 58;BA.debugLine="p.LoadLayout(\"pnl_CLV_KalkulasiAbsen\")";
_p.LoadLayout("pnl_CLV_KalkulasiAbsen",mostCurrent.activityBA);
 //BA.debugLineNum = 59;BA.debugLine="lbl_JmlMasuk.Text = lbls";
mostCurrent._lbl_jmlmasuk.setText(BA.ObjectToCharSequence(_lbls));
 //BA.debugLineNum = 61;BA.debugLine="lblContent.Text = Content";
mostCurrent._lblcontent.setText(BA.ObjectToCharSequence(_content));
 //BA.debugLineNum = 62;BA.debugLine="CircularImg(pnl_foto,Image,Colors.RGB(88,57,150),";
_circularimg(mostCurrent._pnl_foto,_image,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (88),(int) (57),(int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 //BA.debugLineNum = 63;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getObject()));
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return null;
}
public static void  _getdosen() throws Exception{
ResumableSub_GetDosen rsub = new ResumableSub_GetDosen(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetDosen extends BA.ResumableSub {
public ResumableSub_GetDosen(b4a.AbsenBackUp.kalkulasiabsen parent) {
this.parent = parent;
}
b4a.AbsenBackUp.kalkulasiabsen parent;
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
 //BA.debugLineNum = 100;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,kalkulasiabsen.getObject());
 //BA.debugLineNum = 101;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectDosen",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 102;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 103;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 104;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 105;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 106;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 107;BA.debugLine="AC_SpinnerKelas.AddAll(Array As String(row(0)))";
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
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
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
public ResumableSub_GetMatkul(b4a.AbsenBackUp.kalkulasiabsen parent) {
this.parent = parent;
}
b4a.AbsenBackUp.kalkulasiabsen parent;
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
 //BA.debugLineNum = 113;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*b4a.AbsenBackUp.dbrequestmanager*/ (mostCurrent.activityBA,kalkulasiabsen.getObject());
 //BA.debugLineNum = 114;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Select";
_cmd = parent.mostCurrent._util._createcommand /*b4a.AbsenBackUp.main._dbcommand*/ (mostCurrent.activityBA,"SelectMatkul",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 115;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*b4a.AbsenBackUp.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 9;
return;
case 9:
//C
this.state = 1;
_j = (b4a.AbsenBackUp.httpjob) result[0];
;
 //BA.debugLineNum = 116;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 117;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 118;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 10;
return;
case 10:
//C
this.state = 4;
_res = (b4a.AbsenBackUp.main._dbresult) result[0];
;
 //BA.debugLineNum = 119;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 120;BA.debugLine="AC_SpinnerMatkul.AddAll(Array As String(row(0))";
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
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private lblTitle As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lbl_JmlMasuk As Label";
mostCurrent._lbl_jmlmasuk = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private lblContent As Label";
mostCurrent._lblcontent = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private pnl_foto As Panel";
mostCurrent._pnl_foto = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private AC_SpinnerKelas As ACSpinner";
mostCurrent._ac_spinnerkelas = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private AC_SpinnerMatkul As ACSpinner";
mostCurrent._ac_spinnermatkul = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private btn_proses_Kalkulasi As Button";
mostCurrent._btn_proses_kalkulasi = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private CLV_NamaMHS_ As CustomListView";
mostCurrent._clv_namamhs_ = new b4a.example3.customlistview();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 10;BA.debugLine="Dim kls As String";
_kls = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim mtkl As String";
_mtkl = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim coba As String";
_coba = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
}
