package b4a.AbsenCoba;


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

public class main extends android.support.v7.app.AppCompatActivity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.AbsenCoba", "b4a.AbsenCoba.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "b4a.AbsenCoba", "b4a.AbsenCoba.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.AbsenCoba.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public b4a.AbsenCoba.dosen _dosen = null;
public b4a.AbsenCoba.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (dosen.mostCurrent != null);
return vis;}
public static String  _ac_spinnerkelas_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub AC_SpinnerKelas_ItemClick (Position As Int, Va";
 //BA.debugLineNum = 114;BA.debugLine="Log(\"Spinner Item selected: \" & Position & \" - \"";
anywheresoftware.b4a.keywords.Common.LogImpl("0851969","Spinner Item selected: "+BA.NumberToString(_position)+" - "+BA.ObjectToString(_value),0);
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _acspinnerket_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub ACSpinnerKet_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 118;BA.debugLine="Log(\"Spinner Item selected: \" & Position & \" - \"";
anywheresoftware.b4a.keywords.Common.LogImpl("0917505","Spinner Item selected: "+BA.NumberToString(_position)+" - "+BA.ObjectToString(_value),0);
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
String[] _nama = null;
int _n = 0;
 //BA.debugLineNum = 49;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 51;BA.debugLine="Activity.LoadLayout(\"lay_AbsenDosen\")";
mostCurrent._activity.LoadLayout("lay_AbsenDosen",mostCurrent.activityBA);
 //BA.debugLineNum = 53;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 54;BA.debugLine="ACSpinnerKet.Initialize(\"InitializeSpinner\")";
mostCurrent._acspinnerket.Initialize(mostCurrent.activityBA,"InitializeSpinner");
 };
 //BA.debugLineNum = 57;BA.debugLine="AC_SpinnerKelas.AddAll(Array As String(\"3IA18\",\"3";
mostCurrent._ac_spinnerkelas.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"3IA18","3IA19","3IA20","3IA21","3IA22","3IA23"}));
 //BA.debugLineNum = 58;BA.debugLine="AC_SpinnerMatkul.AddAll(Array As String(\"Matemati";
mostCurrent._ac_spinnermatkul.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Matematika Informatika 4","Kimia","Riset operasional","Sistem Informasi"}));
 //BA.debugLineNum = 62;BA.debugLine="Dim nama() As String = Array As String(\"Noer Rach";
_nama = new String[]{"Noer Rachmat","Rafi Mochamad","Bayu Setiadi","Alief Aziez","Servatius Adhi"};
 //BA.debugLineNum = 65;BA.debugLine="For n = 1 To 5";
{
final int step8 = 1;
final int limit8 = (int) (5);
_n = (int) (1) ;
for (;_n <= limit8 ;_n = _n + step8 ) {
 //BA.debugLineNum = 66;BA.debugLine="CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.Wi";
mostCurrent._clv_namamhs._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_createitem(mostCurrent._clv_namamhs._asview().getWidth(),BA.NumberToString((_n)),_nama[(int) (_n-1)]).getObject())),(Object)(""));
 }
};
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _createitem(int _width,String _nomor,String _nama) throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
int _height = 0;
int _i = 0;
 //BA.debugLineNum = 74;BA.debugLine="Private Sub CreateItem(Width As Int, nomor As Stri";
 //BA.debugLineNum = 75;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 76;BA.debugLine="Dim height As Int = 55dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55));
 //BA.debugLineNum = 78;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, Width, height)";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
 //BA.debugLineNum = 79;BA.debugLine="p.LoadLayout(\"pnl_CLVAbsenDosen\")";
_p.LoadLayout("pnl_CLVAbsenDosen",mostCurrent.activityBA);
 //BA.debugLineNum = 80;BA.debugLine="lbl_Nomor.Text = nomor";
mostCurrent._lbl_nomor.setText(BA.ObjectToCharSequence(_nomor));
 //BA.debugLineNum = 81;BA.debugLine="lbl_namaMHS.Text = nama";
mostCurrent._lbl_namamhs.setText(BA.ObjectToCharSequence(_nama));
 //BA.debugLineNum = 82;BA.debugLine="For i = 1 To 4";
{
final int step7 = 1;
final int limit7 = (int) (4);
_i = (int) (1) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 83;BA.debugLine="If i = 1 Then";
if (_i==1) { 
 //BA.debugLineNum = 84;BA.debugLine="ACSpinnerKet.Add2(\"\",Null)'toDrawable(LoadBitma";
mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence(""),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 }else if(_i==2) { 
 //BA.debugLineNum = 86;BA.debugLine="ACSpinnerKet.Add2(\"Absen\",toDrawable(LoadBitmap";
mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence("Absen"),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"absen.png")).getObject()));
 }else if(_i==3) { 
 //BA.debugLineNum = 88;BA.debugLine="ACSpinnerKet.Add2(\"Izin\",toDrawable(LoadBitmap(";
mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence("Izin"),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"izin (2).png")).getObject()));
 }else if(_i==4) { 
 //BA.debugLineNum = 90;BA.debugLine="ACSpinnerKet.Add2(\"Sakit\",toDrawable(LoadBitmap";
mostCurrent._acspinnerket.Add2(BA.ObjectToCharSequence("Sakit"),(android.graphics.drawable.Drawable)(_todrawable(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sakit.png")).getObject()));
 };
 }
};
 //BA.debugLineNum = 93;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getObject()));
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 28;BA.debugLine="Private AC_SpinnerKelas As ACSpinner";
mostCurrent._ac_spinnerkelas = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private AC_SpinnerMatkul As ACSpinner";
mostCurrent._ac_spinnermatkul = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private ACSpinnerKet As ACSpinner";
mostCurrent._acspinnerket = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private CLV_NamaMHS As CustomListView";
mostCurrent._clv_namamhs = new b4a.example3.customlistview();
 //BA.debugLineNum = 34;BA.debugLine="Private pnl_CLVAbsenDosen As Panel";
mostCurrent._pnl_clvabsendosen = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lbl_namaMHS As Label";
mostCurrent._lbl_namamhs = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private lbl_Nomor As Label";
mostCurrent._lbl_nomor = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private img_KetMHS As ImageView";
mostCurrent._img_ketmhs = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim pic() As String";
mostCurrent._pic = new String[(int) (0)];
java.util.Arrays.fill(mostCurrent._pic,"");
 //BA.debugLineNum = 40;BA.debugLine="Dim count As Int";
_count = 0;
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_click() throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Sub Panel1_Click";
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
dosen._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.BitmapDrawable  _todrawable(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp) throws Exception{
anywheresoftware.b4a.objects.drawable.BitmapDrawable _b = null;
 //BA.debugLineNum = 98;BA.debugLine="Sub toDrawable (bmp As Bitmap) As BitmapDrawable";
 //BA.debugLineNum = 99;BA.debugLine="Dim b As BitmapDrawable";
_b = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 100;BA.debugLine="b.Initialize(bmp)";
_b.Initialize((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 101;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return null;
}
}
