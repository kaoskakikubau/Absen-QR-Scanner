package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;

public class rdchandler extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4a.StandardBA("b4j.example", "b4j.example.rdchandler", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.rdchandler.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.collections.Map _datetimemethods = null;
public b4j.example.main _main = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private DateTimeMethods As Map";
_datetimemethods = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public String  _executebatch2(anywheresoftware.b4j.objects.SQL _con,anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in,anywheresoftware.b4j.object.JServlet.ServletResponseWrapper _resp) throws Exception{
anywheresoftware.b4a.randomaccessfile.B4XSerializator _ser = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.List _commands = null;
b4j.example.main._dbresult _res = null;
b4j.example.main._dbcommand _cmd = null;
byte[] _data = null;
 //BA.debugLineNum = 92;BA.debugLine="Private Sub ExecuteBatch2(con As SQL, in As InputS";
 //BA.debugLineNum = 93;BA.debugLine="Dim ser As B4XSerializator";
_ser = new anywheresoftware.b4a.randomaccessfile.B4XSerializator();
 //BA.debugLineNum = 94;BA.debugLine="Dim m As Map = ser.ConvertBytesToObject(Bit.Input";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ser.ConvertBytesToObject(__c.Bit.InputStreamToBytes((java.io.InputStream)(_in.getObject())))));
 //BA.debugLineNum = 95;BA.debugLine="Dim commands As List = m.Get(\"commands\")";
_commands = new anywheresoftware.b4a.objects.collections.List();
_commands.setObject((java.util.List)(_m.Get((Object)("commands"))));
 //BA.debugLineNum = 96;BA.debugLine="Dim res As DBResult";
_res = new b4j.example.main._dbresult();
 //BA.debugLineNum = 97;BA.debugLine="res.Initialize";
_res.Initialize();
 //BA.debugLineNum = 98;BA.debugLine="res.columns = CreateMap(\"AffectedRows (N/A)\": 0)";
_res.Columns = __c.createMap(new Object[] {(Object)("AffectedRows (N/A)"),(Object)(0)});
 //BA.debugLineNum = 99;BA.debugLine="res.Rows.Initialize";
_res.Rows.Initialize();
 //BA.debugLineNum = 100;BA.debugLine="res.Tag = Null";
_res.Tag = __c.Null;
 //BA.debugLineNum = 101;BA.debugLine="Try";
try { //BA.debugLineNum = 102;BA.debugLine="con.BeginTransaction";
_con.BeginTransaction();
 //BA.debugLineNum = 103;BA.debugLine="For Each cmd As DBCommand In commands";
{
final anywheresoftware.b4a.BA.IterableList group11 = _commands;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_cmd = (b4j.example.main._dbcommand)(group11.Get(index11));
 //BA.debugLineNum = 104;BA.debugLine="con.ExecNonQuery2(Main.rdcConnector1.GetCommand";
_con.ExecNonQuery2(_main._rdcconnector1._getcommand(_cmd.Name),anywheresoftware.b4a.keywords.Common.ArrayToList(_cmd.Parameters));
 }
};
 //BA.debugLineNum = 107;BA.debugLine="res.Rows.Add(Array As Object(0))";
_res.Rows.Add((Object)(new Object[]{(Object)(0)}));
 //BA.debugLineNum = 108;BA.debugLine="con.TransactionSuccessful";
_con.TransactionSuccessful();
 } 
       catch (Exception e17) {
			ba.setLastException(e17); //BA.debugLineNum = 110;BA.debugLine="con.Rollback";
_con.Rollback();
 //BA.debugLineNum = 111;BA.debugLine="Log(LastException)";
__c.Log(BA.ObjectToString(__c.LastException(ba)));
 //BA.debugLineNum = 112;BA.debugLine="resp.SendError(500, LastException.Message)";
_resp.SendError((int) (500),__c.LastException(ba).getMessage());
 };
 //BA.debugLineNum = 114;BA.debugLine="Dim data() As Byte = ser.ConvertObjectToBytes(res";
_data = _ser.ConvertObjectToBytes((Object)(_res));
 //BA.debugLineNum = 115;BA.debugLine="resp.OutputStream.WriteBytes(data, 0, data.Length";
_resp.getOutputStream().WriteBytes(_data,(int) (0),_data.length);
 //BA.debugLineNum = 116;BA.debugLine="Return $\"batch (size=${commands.Size})\"$";
if (true) return ("batch (size="+__c.SmartStringFormatter("",(Object)(_commands.getSize()))+")");
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public String  _executequery2(anywheresoftware.b4j.objects.SQL _con,anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in,anywheresoftware.b4j.object.JServlet.ServletResponseWrapper _resp) throws Exception{
anywheresoftware.b4a.randomaccessfile.B4XSerializator _ser = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
b4j.example.main._dbcommand _cmd = null;
int _limit = 0;
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _rs = null;
anywheresoftware.b4j.object.JavaObject _jrs = null;
anywheresoftware.b4j.object.JavaObject _rsmd = null;
int _cols = 0;
b4j.example.main._dbresult _res = null;
int _i = 0;
Object[] _row = null;
int _ct = 0;
byte[] _data = null;
 //BA.debugLineNum = 51;BA.debugLine="Private Sub ExecuteQuery2 (con As SQL, in As Input";
 //BA.debugLineNum = 52;BA.debugLine="Dim ser As B4XSerializator";
_ser = new anywheresoftware.b4a.randomaccessfile.B4XSerializator();
 //BA.debugLineNum = 53;BA.debugLine="Dim m As Map = ser.ConvertBytesToObject(Bit.Input";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ser.ConvertBytesToObject(__c.Bit.InputStreamToBytes((java.io.InputStream)(_in.getObject())))));
 //BA.debugLineNum = 54;BA.debugLine="Dim cmd As DBCommand = m.Get(\"command\")";
_cmd = (b4j.example.main._dbcommand)(_m.Get((Object)("command")));
 //BA.debugLineNum = 55;BA.debugLine="Dim limit As Int = m.Get(\"limit\")";
_limit = (int)(BA.ObjectToNumber(_m.Get((Object)("limit"))));
 //BA.debugLineNum = 56;BA.debugLine="Dim rs As ResultSet = con.ExecQuery2(Main.rdcConn";
_rs = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
_rs = _con.ExecQuery2(_main._rdcconnector1._getcommand(_cmd.Name),anywheresoftware.b4a.keywords.Common.ArrayToList(_cmd.Parameters));
 //BA.debugLineNum = 57;BA.debugLine="If limit <= 0 Then limit = 0x7fffffff 'max int";
if (_limit<=0) { 
_limit = (int) (0x7fffffff);};
 //BA.debugLineNum = 58;BA.debugLine="Dim jrs As JavaObject = rs";
_jrs = new anywheresoftware.b4j.object.JavaObject();
_jrs.setObject((java.lang.Object)(_rs.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="Dim rsmd As JavaObject = jrs.RunMethod(\"getMetaDa";
_rsmd = new anywheresoftware.b4j.object.JavaObject();
_rsmd.setObject((java.lang.Object)(_jrs.RunMethod("getMetaData",(Object[])(__c.Null))));
 //BA.debugLineNum = 60;BA.debugLine="Dim cols As Int = rs.ColumnCount";
_cols = _rs.getColumnCount();
 //BA.debugLineNum = 61;BA.debugLine="Dim res As DBResult";
_res = new b4j.example.main._dbresult();
 //BA.debugLineNum = 62;BA.debugLine="res.Initialize";
_res.Initialize();
 //BA.debugLineNum = 63;BA.debugLine="res.columns.Initialize";
_res.Columns.Initialize();
 //BA.debugLineNum = 64;BA.debugLine="res.Tag = Null 'without this the Tag properly wil";
_res.Tag = __c.Null;
 //BA.debugLineNum = 65;BA.debugLine="For i = 0 To cols - 1";
{
final int step14 = 1;
final int limit14 = (int) (_cols-1);
_i = (int) (0) ;
for (;_i <= limit14 ;_i = _i + step14 ) {
 //BA.debugLineNum = 66;BA.debugLine="res.columns.Put(rs.GetColumnName(i), i)";
_res.Columns.Put((Object)(_rs.GetColumnName(_i)),(Object)(_i));
 }
};
 //BA.debugLineNum = 68;BA.debugLine="res.Rows.Initialize";
_res.Rows.Initialize();
 //BA.debugLineNum = 69;BA.debugLine="Do While rs.NextRow And limit > 0";
while (_rs.NextRow() && _limit>0) {
 //BA.debugLineNum = 70;BA.debugLine="Dim row(cols) As Object";
_row = new Object[_cols];
{
int d0 = _row.length;
for (int i0 = 0;i0 < d0;i0++) {
_row[i0] = new Object();
}
}
;
 //BA.debugLineNum = 71;BA.debugLine="For i = 0 To cols - 1";
{
final int step20 = 1;
final int limit20 = (int) (_cols-1);
_i = (int) (0) ;
for (;_i <= limit20 ;_i = _i + step20 ) {
 //BA.debugLineNum = 72;BA.debugLine="Dim ct As Int = rsmd.RunMethod(\"getColumnType\",";
_ct = (int)(BA.ObjectToNumber(_rsmd.RunMethod("getColumnType",new Object[]{(Object)(_i+1)})));
 //BA.debugLineNum = 74;BA.debugLine="If ct = -2 Or ct = 2004 Or ct = -3 Or ct = -4 T";
if (_ct==-2 || _ct==2004 || _ct==-3 || _ct==-4) { 
 //BA.debugLineNum = 75;BA.debugLine="row(i) = rs.GetBlob2(i)";
_row[_i] = (Object)(_rs.GetBlob2(_i));
 }else if(_ct==2 || _ct==3) { 
 //BA.debugLineNum = 77;BA.debugLine="row(i) = rs.GetDouble2(i)";
_row[_i] = (Object)(_rs.GetDouble2(_i));
 }else if(_datetimemethods.ContainsKey((Object)(_ct))) { 
 //BA.debugLineNum = 79;BA.debugLine="row(i) = jrs.RunMethodJO(DateTimeMethods.Get(c";
_row[_i] = _jrs.RunMethodJO(BA.ObjectToString(_datetimemethods.Get((Object)(_ct))),new Object[]{(Object)(_i+1)}).RunMethod("getTime",(Object[])(__c.Null));
 }else {
 //BA.debugLineNum = 81;BA.debugLine="row(i) = jrs.RunMethod(\"getObject\", Array(i +";
_row[_i] = _jrs.RunMethod("getObject",new Object[]{(Object)(_i+1)});
 };
 }
};
 //BA.debugLineNum = 84;BA.debugLine="res.Rows.Add(row)";
_res.Rows.Add((Object)(_row));
 }
;
 //BA.debugLineNum = 86;BA.debugLine="rs.Close";
_rs.Close();
 //BA.debugLineNum = 87;BA.debugLine="Dim data() As Byte = ser.ConvertObjectToBytes(res";
_data = _ser.ConvertObjectToBytes((Object)(_res));
 //BA.debugLineNum = 88;BA.debugLine="resp.OutputStream.WriteBytes(data, 0, data.Length";
_resp.getOutputStream().WriteBytes(_data,(int) (0),_data.length);
 //BA.debugLineNum = 89;BA.debugLine="Return \"query: \" & cmd.Name";
if (true) return "query: "+_cmd.Name;
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public String  _handle(anywheresoftware.b4j.object.JServlet.ServletRequestWrapper _req,anywheresoftware.b4j.object.JServlet.ServletResponseWrapper _resp) throws Exception{
long _start = 0L;
String _q = "";
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
String _method = "";
anywheresoftware.b4j.objects.SQL _con = null;
 //BA.debugLineNum = 16;BA.debugLine="Sub Handle(req As ServletRequest, resp As ServletR";
 //BA.debugLineNum = 17;BA.debugLine="Dim start As Long = DateTime.Now";
_start = __c.DateTime.getNow();
 //BA.debugLineNum = 18;BA.debugLine="Dim q As String";
_q = "";
 //BA.debugLineNum = 19;BA.debugLine="Dim in As InputStream = req.InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
_in = _req.getInputStream();
 //BA.debugLineNum = 20;BA.debugLine="Dim method As String = req.GetParameter(\"method\")";
_method = _req.GetParameter("method");
 //BA.debugLineNum = 21;BA.debugLine="Dim con As SQL";
_con = new anywheresoftware.b4j.objects.SQL();
 //BA.debugLineNum = 22;BA.debugLine="Try";
try { //BA.debugLineNum = 24;BA.debugLine="con = Main.rdcConnector1.GetConnection";
_con = _main._rdcconnector1._getconnection();
 //BA.debugLineNum = 25;BA.debugLine="If method = \"query2\" Then";
if ((_method).equals("query2")) { 
 //BA.debugLineNum = 26;BA.debugLine="q = ExecuteQuery2(con, in, resp)";
_q = _executequery2(_con,_in,_resp);
 }else if((_method).equals("batch2")) { 
 //BA.debugLineNum = 36;BA.debugLine="q = ExecuteBatch2(con, in, resp)";
_q = _executebatch2(_con,_in,_resp);
 }else {
 //BA.debugLineNum = 38;BA.debugLine="Log(\"Unknown method: \" & method)";
__c.Log("Unknown method: "+_method);
 //BA.debugLineNum = 39;BA.debugLine="resp.SendError(500, \"unknown method\")";
_resp.SendError((int) (500),"unknown method");
 };
 } 
       catch (Exception e17) {
			ba.setLastException(e17); //BA.debugLineNum = 42;BA.debugLine="Log(LastException)";
__c.Log(BA.ObjectToString(__c.LastException(ba)));
 //BA.debugLineNum = 43;BA.debugLine="resp.SendError(500, LastException.Message)";
_resp.SendError((int) (500),__c.LastException(ba).getMessage());
 };
 //BA.debugLineNum = 45;BA.debugLine="If con <> Null And con.IsInitialized Then con.Clo";
if (_con!= null && _con.IsInitialized()) { 
_con.Close();};
 //BA.debugLineNum = 46;BA.debugLine="Log($\"Command: ${q}, took: ${DateTime.Now - start";
__c.Log(("Command: "+__c.SmartStringFormatter("",(Object)(_q))+", took: "+__c.SmartStringFormatter("",(Object)(__c.DateTime.getNow()-_start))+"ms, client="+__c.SmartStringFormatter("",(Object)(_req.getRemoteAddress()))+""));
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 12;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 13;BA.debugLine="DateTimeMethods = CreateMap(91: \"getDate\", 92: \"g";
_datetimemethods = __c.createMap(new Object[] {(Object)(91),(Object)("getDate"),(Object)(92),(Object)("getTime"),(Object)(93),(Object)("getTimestamp")});
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
