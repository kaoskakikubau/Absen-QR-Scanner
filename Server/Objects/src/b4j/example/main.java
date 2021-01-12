package b4j.example;


import anywheresoftware.b4a.BA;

public class main extends Object{
public static main mostCurrent = new main();

public static BA ba;
static {
		ba = new  anywheresoftware.b4a.StandardBA("b4j.example", "b4j.example.main", null);
		ba.loadHtSubs(main.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.main", ba);
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}

 
    public static void main(String[] args) throws Exception{
        try {
            anywheresoftware.b4a.keywords.Common.LogDebug("Program started.");
            initializeProcessGlobals();
            ba.raiseEvent(null, "appstart", (Object)args);
        } catch (Throwable t) {
			BA.printException(t, true);
		
        } finally {
            anywheresoftware.b4a.keywords.Common.LogDebug("Program terminated (StartMessageLoop was not called).");
        }
    }
public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.object.ServerWrapper _srvr = null;
public static b4j.example.rdcconnector _rdcconnector1 = null;
public static float _version = 0f;
public static class _dbcommand{
public boolean IsInitialized;
public String Name;
public Object[] Parameters;
public void Initialize() {
IsInitialized = true;
Name = "";
Parameters = new Object[0];
{
int d0 = Parameters.length;
for (int i0 = 0;i0 < d0;i0++) {
Parameters[i0] = new Object();
}
}
;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _dbresult{
public boolean IsInitialized;
public Object Tag;
public anywheresoftware.b4a.objects.collections.Map Columns;
public anywheresoftware.b4a.objects.collections.List Rows;
public void Initialize() {
IsInitialized = true;
Tag = new Object();
Columns = new anywheresoftware.b4a.objects.collections.Map();
Rows = new anywheresoftware.b4a.objects.collections.List();
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _appstart(String[] _args) throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub AppStart (Args() As String)";
 //BA.debugLineNum = 20;BA.debugLine="srvr.Initialize(\"\")";
_srvr.Initialize(ba,"");
 //BA.debugLineNum = 21;BA.debugLine="rdcConnector1.Initialize";
_rdcconnector1._initialize(ba);
 //BA.debugLineNum = 22;BA.debugLine="srvr.Port = rdcConnector1.serverPort";
_srvr.setPort(_rdcconnector1._serverport);
 //BA.debugLineNum = 23;BA.debugLine="srvr.AddHandler(\"/test\", \"TestHandler\", False)";
_srvr.AddHandler("/test","TestHandler",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 24;BA.debugLine="srvr.AddHandler(\"/rdc\", \"RDCHandler\", False)";
_srvr.AddHandler("/rdc","RDCHandler",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 25;BA.debugLine="srvr.Start";
_srvr.Start();
 //BA.debugLineNum = 26;BA.debugLine="Log($\"jRDC is running (version = $1.2{VERSION})\"$";
anywheresoftware.b4a.keywords.Common.Log(("jRDC is running (version = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_version))+")"));
 //BA.debugLineNum = 27;BA.debugLine="StartMessageLoop";
anywheresoftware.b4a.keywords.Common.StartMessageLoop(ba);
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}

private static boolean processGlobalsRun;
public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 12;BA.debugLine="Public srvr As Server";
_srvr = new anywheresoftware.b4j.object.ServerWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Public rdcConnector1 As RDCConnector";
_rdcconnector1 = new b4j.example.rdcconnector();
 //BA.debugLineNum = 14;BA.debugLine="Public const VERSION As Float = 2.21";
_version = (float) (2.21);
 //BA.debugLineNum = 15;BA.debugLine="Type DBCommand (Name As String, Parameters() As O";
;
 //BA.debugLineNum = 16;BA.debugLine="Type DBResult (Tag As Object, Columns As Map, Row";
;
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
}
