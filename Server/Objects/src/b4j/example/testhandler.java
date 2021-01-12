package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;

public class testhandler extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4a.StandardBA("b4j.example", "b4j.example.testhandler", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.testhandler.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public b4j.example.main _main = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="End Sub";
return "";
}
public String  _handle(anywheresoftware.b4j.object.JServlet.ServletRequestWrapper _req,anywheresoftware.b4j.object.JServlet.ServletResponseWrapper _resp) throws Exception{
anywheresoftware.b4j.objects.SQL _con = null;
 //BA.debugLineNum = 10;BA.debugLine="Sub Handle(req As ServletRequest, resp As ServletR";
 //BA.debugLineNum = 11;BA.debugLine="resp.ContentType = \"text/html\"";
_resp.setContentType("text/html");
 //BA.debugLineNum = 12;BA.debugLine="resp.Write($\"RemoteServer is running ($DateTime{D";
_resp.Write(("RemoteServer is running ("+__c.SmartStringFormatter("datetime",(Object)(__c.DateTime.getNow()))+")<br/>"));
 //BA.debugLineNum = 13;BA.debugLine="Try";
try { //BA.debugLineNum = 14;BA.debugLine="Dim con As SQL = Main.rdcConnector1.GetConnectio";
_con = _main._rdcconnector1._getconnection();
 //BA.debugLineNum = 15;BA.debugLine="resp.Write(\"Connection successful.\")";
_resp.Write("Connection successful.");
 //BA.debugLineNum = 16;BA.debugLine="con.Close";
_con.Close();
 } 
       catch (Exception e8) {
			ba.setLastException(e8); //BA.debugLineNum = 18;BA.debugLine="resp.Write(\"Error fetching connection.\")";
_resp.Write("Error fetching connection.");
 };
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 6;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
