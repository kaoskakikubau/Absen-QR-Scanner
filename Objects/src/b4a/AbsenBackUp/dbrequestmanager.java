package b4a.AbsenBackUp;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class dbrequestmanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.AbsenBackUp.dbrequestmanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.AbsenBackUp.dbrequestmanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public Object _mtarget = null;
public String _link = "";
public float _version = 0f;
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
public b4a.AbsenBackUp.util _util = null;
public b4a.AbsenBackUp.httputils2service _httputils2service = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _bytestoimage(byte[] _bytes) throws Exception{
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
 //BA.debugLineNum = 113;BA.debugLine="Public Sub BytesToImage(bytes() As Byte) As Bitmap";
 //BA.debugLineNum = 114;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
 //BA.debugLineNum = 115;BA.debugLine="In.InitializeFromBytesArray(bytes, 0, bytes.Lengt";
_in.InitializeFromBytesArray(_bytes,(int) (0),_bytes.length);
 //BA.debugLineNum = 116;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 117;BA.debugLine="bmp.Initialize2(In)";
_bmp.Initialize2((java.io.InputStream)(_in.getObject()));
 //BA.debugLineNum = 118;BA.debugLine="Return bmp";
if (true) return _bmp;
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return null;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 4;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Private mTarget As Object";
_mtarget = new Object();
 //BA.debugLineNum = 6;BA.debugLine="Private link As String";
_link = "";
 //BA.debugLineNum = 7;BA.debugLine="Private VERSION As Float = 2";
_version = (float) (2);
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public b4a.AbsenBackUp.httpjob  _createjob() throws Exception{
b4a.AbsenBackUp.httpjob _j = null;
 //BA.debugLineNum = 33;BA.debugLine="Private Sub CreateJob As HttpJob";
 //BA.debugLineNum = 34;BA.debugLine="Dim j As HttpJob";
_j = new b4a.AbsenBackUp.httpjob();
 //BA.debugLineNum = 35;BA.debugLine="j.Initialize(\"DBRequest\", mTarget)";
_j._initialize /*String*/ (ba,"DBRequest",_mtarget);
 //BA.debugLineNum = 36;BA.debugLine="Return j";
if (true) return _j;
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return null;
}
public b4a.AbsenBackUp.httpjob  _executebatch(anywheresoftware.b4a.objects.collections.List _listofcommands,Object _tag) throws Exception{
b4a.AbsenBackUp.httpjob _j = null;
 //BA.debugLineNum = 42;BA.debugLine="Public Sub ExecuteBatch(ListOfCommands As List, Ta";
 //BA.debugLineNum = 43;BA.debugLine="Dim j As HttpJob = CreateJob";
_j = _createjob();
 //BA.debugLineNum = 44;BA.debugLine="ExecuteBatchImpl(j, ListOfCommands, Tag)";
_executebatchimpl(_j,_listofcommands,_tag);
 //BA.debugLineNum = 45;BA.debugLine="Return j";
if (true) return _j;
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return null;
}
public void  _executebatchimpl(b4a.AbsenBackUp.httpjob _job,anywheresoftware.b4a.objects.collections.List _listofcommands,Object _tag) throws Exception{
ResumableSub_ExecuteBatchImpl rsub = new ResumableSub_ExecuteBatchImpl(this,_job,_listofcommands,_tag);
rsub.resume(ba, null);
}
public static class ResumableSub_ExecuteBatchImpl extends BA.ResumableSub {
public ResumableSub_ExecuteBatchImpl(b4a.AbsenBackUp.dbrequestmanager parent,b4a.AbsenBackUp.httpjob _job,anywheresoftware.b4a.objects.collections.List _listofcommands,Object _tag) {
this.parent = parent;
this._job = _job;
this._listofcommands = _listofcommands;
this._tag = _tag;
}
b4a.AbsenBackUp.dbrequestmanager parent;
b4a.AbsenBackUp.httpjob _job;
anywheresoftware.b4a.objects.collections.List _listofcommands;
Object _tag;
anywheresoftware.b4a.randomaccessfile.B4XSerializator _ser = null;
boolean _success = false;
byte[] _bytes = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 49;BA.debugLine="Dim ser As B4XSerializator";
_ser = new anywheresoftware.b4a.randomaccessfile.B4XSerializator();
 //BA.debugLineNum = 50;BA.debugLine="ser.ConvertObjectToBytesAsync(CreateMap(\"commands";
_ser.ConvertObjectToBytesAsync(ba,(Object)(parent.__c.createMap(new Object[] {(Object)("commands"),(Object)(_listofcommands.getObject()),(Object)("version"),(Object)(parent._version)}).getObject()),"ser");
 //BA.debugLineNum = 51;BA.debugLine="Wait For (ser) ser_ObjectToBytes (Success As Bool";
parent.__c.WaitFor("ser_objecttobytes", ba, this, (Object)(_ser));
this.state = 5;
return;
case 5:
//C
this.state = 1;
_success = (Boolean) result[0];
_bytes = (byte[]) result[1];
;
 //BA.debugLineNum = 52;BA.debugLine="If Success = False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_success==parent.__c.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 53;BA.debugLine="Log(\"Error building command: \" & LastException)";
parent.__c.LogImpl("08257541","Error building command: "+BA.ObjectToString(parent.__c.LastException(parent.getActivityBA())),0);
 //BA.debugLineNum = 54;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 56;BA.debugLine="Dim ser As B4XSerializator = Sender";
_ser = (anywheresoftware.b4a.randomaccessfile.B4XSerializator)(parent.__c.Sender(parent.getActivityBA()));
 //BA.debugLineNum = 57;BA.debugLine="SendJob(Job, Bytes, Tag, \"batch2\")";
parent._sendjob(_job,_bytes,_tag,"batch2");
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _ser_objecttobytes(boolean _success,byte[] _bytes) throws Exception{
}
public b4a.AbsenBackUp.httpjob  _executecommand(b4a.AbsenBackUp.main._dbcommand _command,Object _tag) throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Public Sub ExecuteCommand(Command As DBCommand, Ta";
 //BA.debugLineNum = 63;BA.debugLine="Return ExecuteBatch(Array As DBCommand(Command),";
if (true) return _executebatch(anywheresoftware.b4a.keywords.Common.ArrayToList(new b4a.AbsenBackUp.main._dbcommand[]{_command}),_tag);
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return null;
}
public b4a.AbsenBackUp.httpjob  _executequery(b4a.AbsenBackUp.main._dbcommand _command,int _limit,Object _tag) throws Exception{
anywheresoftware.b4a.randomaccessfile.B4XSerializator _ser = null;
byte[] _data = null;
 //BA.debugLineNum = 21;BA.debugLine="Public Sub ExecuteQuery(Command As DBCommand, Limi";
 //BA.debugLineNum = 22;BA.debugLine="Dim ser As B4XSerializator";
_ser = new anywheresoftware.b4a.randomaccessfile.B4XSerializator();
 //BA.debugLineNum = 23;BA.debugLine="Dim data() As Byte = ser.ConvertObjectToBytes(Cre";
_data = _ser.ConvertObjectToBytes((Object)(__c.createMap(new Object[] {(Object)("command"),(Object)(_command),(Object)("limit"),(Object)(_limit),(Object)("version"),(Object)(_version)}).getObject()));
 //BA.debugLineNum = 24;BA.debugLine="Return SendJob(CreateJob, data, Tag, \"query2\")";
if (true) return _sendjob(_createjob(),_data,_tag,"query2");
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return null;
}
public byte[]  _filetobytes(String _dir,String _filename) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
 //BA.debugLineNum = 95;BA.debugLine="Public Sub FileToBytes(Dir As String, FileName As";
 //BA.debugLineNum = 96;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 97;BA.debugLine="out.InitializeToBytesArray(0)";
_out.InitializeToBytesArray((int) (0));
 //BA.debugLineNum = 98;BA.debugLine="Dim In As InputStream = File.OpenInput(Dir, FileN";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
_in = __c.File.OpenInput(_dir,_filename);
 //BA.debugLineNum = 99;BA.debugLine="File.Copy2(In, out)";
__c.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 100;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 101;BA.debugLine="Return out.ToBytesArray";
if (true) return _out.ToBytesArray();
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return null;
}
public String  _getoperation(b4a.AbsenBackUp.main._dbresult _res) throws Exception{
Object[] _row = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
Object _record = null;
 //BA.debugLineNum = 142;BA.debugLine="public Sub getOperation(res As DBResult)";
 //BA.debugLineNum = 143;BA.debugLine="For Each row() As Object In res.Rows";
{
final anywheresoftware.b4a.BA.IterableList group1 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_row = (Object[])(group1.Get(index1));
 //BA.debugLineNum = 144;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 145;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 146;BA.debugLine="For Each record As Object In row";
{
final Object[] group4 = _row;
final int groupLen4 = group4.length
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_record = group4[index4];
 //BA.debugLineNum = 147;BA.debugLine="sb.Append(record).Append(TAB)";
_sb.Append(BA.ObjectToString(_record)).Append(__c.TAB);
 }
};
 }
};
 //BA.debugLineNum = 151;BA.debugLine="Return sb.ToString";
if (true) return _sb.ToString();
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public b4a.AbsenBackUp.main._dbresult  _handlejob(b4a.AbsenBackUp.httpjob _job) throws Exception{
anywheresoftware.b4a.randomaccessfile.B4XSerializator _ser = null;
byte[] _data = null;
b4a.AbsenBackUp.main._dbresult _res = null;
 //BA.debugLineNum = 68;BA.debugLine="Public Sub HandleJob(Job As HttpJob) As DBResult";
 //BA.debugLineNum = 69;BA.debugLine="Dim ser As B4XSerializator";
_ser = new anywheresoftware.b4a.randomaccessfile.B4XSerializator();
 //BA.debugLineNum = 70;BA.debugLine="Dim data() As Byte = Bit.InputStreamToBytes(Job.G";
_data = __c.Bit.InputStreamToBytes((java.io.InputStream)(_job._getinputstream /*anywheresoftware.b4a.objects.streams.File.InputStreamWrapper*/ ().getObject()));
 //BA.debugLineNum = 71;BA.debugLine="Dim res As DBResult = ser.ConvertBytesToObject(da";
_res = (b4a.AbsenBackUp.main._dbresult)(_ser.ConvertBytesToObject(_data));
 //BA.debugLineNum = 72;BA.debugLine="res.Tag = Job.Tag";
_res.Tag /*Object*/  = _job._tag /*Object*/ ;
 //BA.debugLineNum = 73;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return null;
}
public void  _handlejobasync(b4a.AbsenBackUp.httpjob _job,String _eventname) throws Exception{
ResumableSub_HandleJobAsync rsub = new ResumableSub_HandleJobAsync(this,_job,_eventname);
rsub.resume(ba, null);
}
public static class ResumableSub_HandleJobAsync extends BA.ResumableSub {
public ResumableSub_HandleJobAsync(b4a.AbsenBackUp.dbrequestmanager parent,b4a.AbsenBackUp.httpjob _job,String _eventname) {
this.parent = parent;
this._job = _job;
this._eventname = _eventname;
}
b4a.AbsenBackUp.dbrequestmanager parent;
b4a.AbsenBackUp.httpjob _job;
String _eventname;
anywheresoftware.b4a.randomaccessfile.B4XSerializator _ser = null;
byte[] _data = null;
boolean _success = false;
Object _newobject = null;
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
 //BA.debugLineNum = 78;BA.debugLine="Dim ser As B4XSerializator";
_ser = new anywheresoftware.b4a.randomaccessfile.B4XSerializator();
 //BA.debugLineNum = 79;BA.debugLine="Dim data() As Byte = Bit.InputStreamToBytes(Job.G";
_data = parent.__c.Bit.InputStreamToBytes((java.io.InputStream)(_job._getinputstream /*anywheresoftware.b4a.objects.streams.File.InputStreamWrapper*/ ().getObject()));
 //BA.debugLineNum = 80;BA.debugLine="ser.ConvertBytesToObjectAsync(data, \"ser\")";
_ser.ConvertBytesToObjectAsync(ba,_data,"ser");
 //BA.debugLineNum = 81;BA.debugLine="Wait For (ser) ser_BytesToObject (Success As Bool";
parent.__c.WaitFor("ser_bytestoobject", ba, this, (Object)(_ser));
this.state = 5;
return;
case 5:
//C
this.state = 1;
_success = (Boolean) result[0];
_newobject = (Object) result[1];
;
 //BA.debugLineNum = 82;BA.debugLine="If Success = False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_success==parent.__c.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 83;BA.debugLine="Log(\"Error reading response: \" & LastException)";
parent.__c.LogImpl("08454150","Error reading response: "+BA.ObjectToString(parent.__c.LastException(parent.getActivityBA())),0);
 //BA.debugLineNum = 84;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 86;BA.debugLine="Dim res As DBResult = NewObject";
_res = (b4a.AbsenBackUp.main._dbresult)(_newobject);
 //BA.debugLineNum = 87;BA.debugLine="res.Tag = Job.Tag";
_res.Tag /*Object*/  = _job._tag /*Object*/ ;
 //BA.debugLineNum = 88;BA.debugLine="CallSubDelayed2(mTarget, EventName & \"_result\", r";
parent.__c.CallSubDelayed2(ba,parent._mtarget,_eventname+"_result",(Object)(_res));
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _ser_bytestoobject(boolean _success,Object _newobject) throws Exception{
}
public byte[]  _imagetobytes(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _image) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 105;BA.debugLine="Public Sub ImageToBytes(Image As Bitmap) As Byte()";
 //BA.debugLineNum = 106;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 107;BA.debugLine="out.InitializeToBytesArray(0)";
_out.InitializeToBytesArray((int) (0));
 //BA.debugLineNum = 108;BA.debugLine="Image.WriteToStream(out, 100, \"PNG\")";
_image.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 109;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 110;BA.debugLine="Return out.ToBytesArray";
if (true) return _out.ToBytesArray();
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _target,String _connectorlink) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 12;BA.debugLine="Public Sub Initialize (Target As Object, Connector";
 //BA.debugLineNum = 13;BA.debugLine="mTarget = Target";
_mtarget = _target;
 //BA.debugLineNum = 14;BA.debugLine="link = ConnectorLink";
_link = _connectorlink;
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public String  _printtable(b4a.AbsenBackUp.main._dbresult _table) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
Object _col = null;
Object[] _row = null;
Object _record = null;
 //BA.debugLineNum = 123;BA.debugLine="Public Sub PrintTable(Table As DBResult)";
 //BA.debugLineNum = 124;BA.debugLine="Log(\"Tag: \" & Table.Tag & \", Columns: \" & Table.C";
__c.LogImpl("08716289","Tag: "+BA.ObjectToString(_table.Tag /*Object*/ )+", Columns: "+BA.NumberToString(_table.Columns /*anywheresoftware.b4a.objects.collections.Map*/ .getSize())+", Rows: "+BA.NumberToString(_table.Rows /*anywheresoftware.b4a.objects.collections.List*/ .getSize()),0);
 //BA.debugLineNum = 125;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 126;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 127;BA.debugLine="For Each col In Table.Columns.Keys";
{
final anywheresoftware.b4a.BA.IterableList group4 = _table.Columns /*anywheresoftware.b4a.objects.collections.Map*/ .Keys();
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_col = group4.Get(index4);
 //BA.debugLineNum = 128;BA.debugLine="sb.Append(col).Append(TAB)";
_sb.Append(BA.ObjectToString(_col)).Append(__c.TAB);
 }
};
 //BA.debugLineNum = 130;BA.debugLine="Log(sb.ToString)";
__c.LogImpl("08716295",_sb.ToString(),0);
 //BA.debugLineNum = 131;BA.debugLine="For Each row() As Object In Table.Rows";
{
final anywheresoftware.b4a.BA.IterableList group8 = _table.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_row = (Object[])(group8.Get(index8));
 //BA.debugLineNum = 132;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 133;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 134;BA.debugLine="For Each record As Object In row";
{
final Object[] group11 = _row;
final int groupLen11 = group11.length
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_record = group11[index11];
 //BA.debugLineNum = 135;BA.debugLine="sb.Append(record).Append(TAB)";
_sb.Append(BA.ObjectToString(_record)).Append(__c.TAB);
 }
};
 //BA.debugLineNum = 137;BA.debugLine="Log(sb.ToString)";
__c.LogImpl("08716302",_sb.ToString(),0);
 }
};
 //BA.debugLineNum = 139;BA.debugLine="Return sb.ToString";
if (true) return _sb.ToString();
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public b4a.AbsenBackUp.httpjob  _sendjob(b4a.AbsenBackUp.httpjob _j,byte[] _data,Object _tag,String _method) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Private Sub SendJob(j As HttpJob, Data() As Byte,";
 //BA.debugLineNum = 28;BA.debugLine="j.Tag = Tag";
_j._tag /*Object*/  = _tag;
 //BA.debugLineNum = 29;BA.debugLine="j.PostBytes(link & \"?method=\" & Method , Data)";
_j._postbytes /*String*/ (_link+"?method="+_method,_data);
 //BA.debugLineNum = 30;BA.debugLine="Return j";
if (true) return _j;
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return null;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
