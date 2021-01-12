package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;

public class rdcconnector extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4a.StandardBA("b4j.example", "b4j.example.rdcconnector", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.rdcconnector.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.object.ConnectionPool _pool = null;
public boolean _debugqueries = false;
public anywheresoftware.b4a.objects.collections.Map _commands = null;
public int _serverport = 0;
public b4j.example.main _main = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private pool As ConnectionPool";
_pool = new anywheresoftware.b4j.object.ConnectionPool();
 //BA.debugLineNum = 4;BA.debugLine="Private DebugQueries As Boolean";
_debugqueries = false;
 //BA.debugLineNum = 5;BA.debugLine="Private commands As Map";
_commands = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 6;BA.debugLine="Public serverPort As Int";
_serverport = 0;
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public String  _getcommand(String _key) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Public Sub GetCommand(Key As String) As String";
 //BA.debugLineNum = 28;BA.debugLine="If commands.ContainsKey(\"sql.\" & Key) = False The";
if (_commands.ContainsKey((Object)("sql."+_key))==__c.False) { 
 //BA.debugLineNum = 29;BA.debugLine="Log(\"*** Command not found: \" & Key)";
__c.Log("*** Command not found: "+_key);
 };
 //BA.debugLineNum = 31;BA.debugLine="Return commands.Get(\"sql.\" & Key)";
if (true) return BA.ObjectToString(_commands.Get((Object)("sql."+_key)));
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4j.objects.SQL  _getconnection() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Public Sub GetConnection As SQL";
 //BA.debugLineNum = 35;BA.debugLine="If DebugQueries Then LoadSQLCommands(LoadConfigMa";
if (_debugqueries) { 
_loadsqlcommands(_loadconfigmap());};
 //BA.debugLineNum = 36;BA.debugLine="Return pool.GetConnection";
if (true) return _pool.GetConnection();
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
anywheresoftware.b4a.objects.collections.Map _config = null;
 //BA.debugLineNum = 10;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 11;BA.debugLine="Dim config As Map = LoadConfigMap";
_config = new anywheresoftware.b4a.objects.collections.Map();
_config = _loadconfigmap();
 //BA.debugLineNum = 12;BA.debugLine="pool.Initialize(config.Get(\"DriverClass\"), config";
_pool.Initialize(BA.ObjectToString(_config.Get((Object)("DriverClass"))),BA.ObjectToString(_config.Get((Object)("JdbcUrl"))),BA.ObjectToString(_config.Get((Object)("User"))),BA.ObjectToString(_config.Get((Object)("Password"))));
 //BA.debugLineNum = 17;BA.debugLine="DebugQueries = False";
_debugqueries = __c.False;
 //BA.debugLineNum = 19;BA.debugLine="serverPort = config.Get(\"ServerPort\")";
_serverport = (int)(BA.ObjectToNumber(_config.Get((Object)("ServerPort"))));
 //BA.debugLineNum = 20;BA.debugLine="LoadSQLCommands(config)";
_loadsqlcommands(_config);
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.Map  _loadconfigmap() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Private Sub LoadConfigMap As Map";
 //BA.debugLineNum = 24;BA.debugLine="Return File.ReadMap(File.DirAssets, \"config.prope";
if (true) return __c.File.ReadMap(__c.File.getDirAssets(),"config.properties");
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return null;
}
public String  _loadsqlcommands(anywheresoftware.b4a.objects.collections.Map _config) throws Exception{
anywheresoftware.b4a.objects.collections.Map _newcommands = null;
String _k = "";
 //BA.debugLineNum = 40;BA.debugLine="Private Sub LoadSQLCommands(config As Map)";
 //BA.debugLineNum = 41;BA.debugLine="Dim newCommands As Map";
_newcommands = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 42;BA.debugLine="newCommands.Initialize";
_newcommands.Initialize();
 //BA.debugLineNum = 43;BA.debugLine="For Each k As String In config.Keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _config.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_k = BA.ObjectToString(group3.Get(index3));
 //BA.debugLineNum = 44;BA.debugLine="If k.StartsWith(\"sql.\") Then";
if (_k.startsWith("sql.")) { 
 //BA.debugLineNum = 45;BA.debugLine="newCommands.Put(k, config.Get(k))";
_newcommands.Put((Object)(_k),_config.Get((Object)(_k)));
 };
 }
};
 //BA.debugLineNum = 48;BA.debugLine="commands = newCommands";
_commands = _newcommands;
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
