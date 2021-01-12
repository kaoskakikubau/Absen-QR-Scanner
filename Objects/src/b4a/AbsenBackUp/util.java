package b4a.AbsenBackUp;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class util {
private static util mostCurrent = new util();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
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
public b4a.AbsenBackUp.rekap_absen_dosen _rekap_absen_dosen = null;
public b4a.AbsenBackUp.httputils2service _httputils2service = null;
public static b4a.AbsenBackUp.main._dbcommand  _createcommand(anywheresoftware.b4a.BA _ba,String _name,Object[] _parameters) throws Exception{
b4a.AbsenBackUp.main._dbcommand _cmd = null;
 //BA.debugLineNum = 15;BA.debugLine="public Sub CreateCommand(Name As String, Parameter";
 //BA.debugLineNum = 16;BA.debugLine="Dim cmd As DBCommand";
_cmd = new b4a.AbsenBackUp.main._dbcommand();
 //BA.debugLineNum = 17;BA.debugLine="cmd.Initialize";
_cmd.Initialize();
 //BA.debugLineNum = 18;BA.debugLine="cmd.Name = Name";
_cmd.Name /*String*/  = _name;
 //BA.debugLineNum = 19;BA.debugLine="If Parameters <> Null Then cmd.Parameters = Param";
if (_parameters!= null) { 
_cmd.Parameters /*Object[]*/  = _parameters;};
 //BA.debugLineNum = 20;BA.debugLine="Return cmd";
if (true) return _cmd;
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return null;
}
public static b4a.AbsenBackUp.dbrequestmanager  _createrequest(anywheresoftware.b4a.BA _ba,Object _modul) throws Exception{
b4a.AbsenBackUp.dbrequestmanager _req = null;
 //BA.debugLineNum = 9;BA.debugLine="public Sub CreateRequest(modul As Object) As DBReq";
 //BA.debugLineNum = 10;BA.debugLine="Dim req As DBRequestManager";
_req = new b4a.AbsenBackUp.dbrequestmanager();
 //BA.debugLineNum = 11;BA.debugLine="req.Initialize(modul, Main.rdcLink)";
_req._initialize /*String*/ ((_ba.processBA == null ? _ba : _ba.processBA),_modul,mostCurrent._main._rdclink /*String*/ );
 //BA.debugLineNum = 12;BA.debugLine="Return req";
if (true) return _req;
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
