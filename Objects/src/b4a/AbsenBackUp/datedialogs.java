package b4a.AbsenBackUp;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class datedialogs extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.AbsenBackUp.datedialogs");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.AbsenBackUp.datedialogs.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public long _dateselected = 0L;
public int _colore = 0;
public int _colorlabelnow = 0;
public anywheresoftware.b4a.objects.ActivityWrapper _myact = null;
public long _dateret = 0L;
public int _ret = 0;
public anywheresoftware.b4a.objects.PanelWrapper _pa = null;
public anywheresoftware.b4a.objects.PanelWrapper _cale = null;
public anywheresoftware.b4a.objects.PanelWrapper _testa = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttocancel = null;
public anywheresoftware.b4a.objects.LabelWrapper _labelnow = null;
public anywheresoftware.b4a.objects.LabelWrapper _lday = null;
public anywheresoftware.b4a.objects.LabelWrapper _lyear = null;
public anywheresoftware.b4a.objects.LabelWrapper _lmounth = null;
public anywheresoftware.b4a.objects.collections.List _listdays = null;
public anywheresoftware.b4a.objects.collections.List _listmonths = null;
public int _dayshift = 0;
public boolean _sundayhdrred = false;
public boolean _sundaydaysred = false;
public boolean _saturdayhdrred = false;
public boolean _saturdaydaysred = false;
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
public String  _buttoncancel_click() throws Exception{
 //BA.debugLineNum = 400;BA.debugLine="private Sub ButtonCancel_Click";
 //BA.debugLineNum = 401;BA.debugLine="Ret=DialogResponse.NEGATIVE";
_ret = __c.DialogResponse.NEGATIVE;
 //BA.debugLineNum = 402;BA.debugLine="End Sub";
return "";
}
public String  _buttonok_click() throws Exception{
 //BA.debugLineNum = 395;BA.debugLine="private Sub ButtonOk_Click";
 //BA.debugLineNum = 396;BA.debugLine="DateSelected=DateRet";
_dateselected = _dateret;
 //BA.debugLineNum = 397;BA.debugLine="Ret=DialogResponse.POSITIVE";
_ret = __c.DialogResponse.POSITIVE;
 //BA.debugLineNum = 398;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Public DateSelected As Long";
_dateselected = 0L;
 //BA.debugLineNum = 4;BA.debugLine="Private Colore As Int = Colors.RGB(0,150,136)";
_colore = __c.Colors.RGB((int) (0),(int) (150),(int) (136));
 //BA.debugLineNum = 5;BA.debugLine="Private ColorLabelNow As Int";
_colorlabelnow = 0;
 //BA.debugLineNum = 6;BA.debugLine="Private MyAct As Activity";
_myact = new anywheresoftware.b4a.objects.ActivityWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private DateRet As Long = 0";
_dateret = (long) (0);
 //BA.debugLineNum = 8;BA.debugLine="Private Ret As Int = 0";
_ret = (int) (0);
 //BA.debugLineNum = 10;BA.debugLine="Private Pa As Panel";
_pa = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private Cale As Panel";
_cale = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private Testa As Panel";
_testa = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private ButtonOk As Button";
_buttonok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private ButtoCancel As Button";
_buttocancel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private LabelNow As Label";
_labelnow = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private LDay As Label";
_lday = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private LYear As Label";
_lyear = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private LMounth As Label";
_lmounth = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private listDays As List";
_listdays = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 21;BA.debugLine="Private listMonths As List";
_listmonths = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 23;BA.debugLine="Private DayShift As Int=0";
_dayshift = (int) (0);
 //BA.debugLineNum = 24;BA.debugLine="Private SundayHdrRed As Boolean=True";
_sundayhdrred = __c.True;
 //BA.debugLineNum = 25;BA.debugLine="Private SundayDaysRed As Boolean=False";
_sundaydaysred = __c.False;
 //BA.debugLineNum = 26;BA.debugLine="Private SaturdayHdrRed As Boolean=False";
_saturdayhdrred = __c.False;
 //BA.debugLineNum = 27;BA.debugLine="Private SaturdayDaysRed As Boolean=False";
_saturdaydaysred = __c.False;
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.ColorDrawable  _corner(int _color) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdb = null;
 //BA.debugLineNum = 80;BA.debugLine="private Sub Corner(Color As Int) As ColorDrawable";
 //BA.debugLineNum = 81;BA.debugLine="Dim cdb As ColorDrawable";
_cdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 82;BA.debugLine="cdb.Initialize(Color, 20dip)";
_cdb.Initialize(_color,__c.DipToCurrent((int) (20)));
 //BA.debugLineNum = 83;BA.debugLine="Return cdb";
if (true) return _cdb;
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return null;
}
public String  _fillcalendar() throws Exception{
int _gday = 0;
int _d = 0;
int _m = 0;
int _a = 0;
int _y = 0;
int _x = 0;
long _datestart = 0L;
String _currentdateformat = "";
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _lab = null;
 //BA.debugLineNum = 150;BA.debugLine="private Sub FillCalendar";
 //BA.debugLineNum = 151;BA.debugLine="Dim GDay,D,M,A,Y,X As Int";
_gday = 0;
_d = 0;
_m = 0;
_a = 0;
_y = 0;
_x = 0;
 //BA.debugLineNum = 152;BA.debugLine="Dim DateStart As Long";
_datestart = 0L;
 //BA.debugLineNum = 153;BA.debugLine="Dim CurrentDateFormat As String";
_currentdateformat = "";
 //BA.debugLineNum = 155;BA.debugLine="Cale.RemoveAllViews";
_cale.RemoveAllViews();
 //BA.debugLineNum = 157;BA.debugLine="D = DateTime.GetDayOfMonth(DateRet)";
_d = __c.DateTime.GetDayOfMonth(_dateret);
 //BA.debugLineNum = 158;BA.debugLine="M = DateTime.GetMonth(DateRet)";
_m = __c.DateTime.GetMonth(_dateret);
 //BA.debugLineNum = 159;BA.debugLine="A = DateTime.GetYear(DateRet)";
_a = __c.DateTime.GetYear(_dateret);
 //BA.debugLineNum = 161;BA.debugLine="CurrentDateFormat = DateTime.DateFormat";
_currentdateformat = __c.DateTime.getDateFormat();
 //BA.debugLineNum = 162;BA.debugLine="DateTime.DateFormat=\"MM/dd/yyy\"";
__c.DateTime.setDateFormat("MM/dd/yyy");
 //BA.debugLineNum = 163;BA.debugLine="DateStart=DateTime.DateParse(M & \"/01/\" & A)";
_datestart = __c.DateTime.DateParse(BA.NumberToString(_m)+"/01/"+BA.NumberToString(_a));
 //BA.debugLineNum = 164;BA.debugLine="GDay=DateTime.GetDayOfWeek(DateStart)-DayShift";
_gday = (int) (__c.DateTime.GetDayOfWeek(_datestart)-_dayshift);
 //BA.debugLineNum = 165;BA.debugLine="If GDay=0 Then GDay=7";
if (_gday==0) { 
_gday = (int) (7);};
 //BA.debugLineNum = 166;BA.debugLine="DateTime.DateFormat = CurrentDateFormat";
__c.DateTime.setDateFormat(_currentdateformat);
 //BA.debugLineNum = 168;BA.debugLine="For i=0 To 6";
{
final int step14 = 1;
final int limit14 = (int) (6);
_i = (int) (0) ;
for (;_i <= limit14 ;_i = _i + step14 ) {
 //BA.debugLineNum = 169;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 170;BA.debugLine="Lab.Initialize(\"\")";
_lab.Initialize(ba,"");
 //BA.debugLineNum = 171;BA.debugLine="Lab.Text=NomeGiorno(i)";
_lab.setText(BA.ObjectToCharSequence(_nomegiorno(_i)));
 //BA.debugLineNum = 172;BA.debugLine="Lab.Color=Colors.Transparent";
_lab.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 173;BA.debugLine="Lab.Textcolor=Colors.Black";
_lab.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 174;BA.debugLine="If ((i=6 And DayShift=0) Or (i=6 And DayShift=1)";
if (((_i==6 && _dayshift==0) || (_i==6 && _dayshift==1))) { 
 //BA.debugLineNum = 175;BA.debugLine="If SundayHdrRed Then Lab.Textcolor=Colors.Red";
if (_sundayhdrred) { 
_lab.setTextColor(__c.Colors.Red);};
 }else if(((_i==0 && _dayshift==0) || (_i==5 && _dayshift==1))) { 
 //BA.debugLineNum = 177;BA.debugLine="If SaturdayHdrRed Then Lab.Textcolor=Colors.Red";
if (_saturdayhdrred) { 
_lab.setTextColor(__c.Colors.Red);};
 };
 //BA.debugLineNum = 180;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 181;BA.debugLine="Lab.Typeface=Typeface.DEFAULT_BOLD";
_lab.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 182;BA.debugLine="Lab.TextSize=14";
_lab.setTextSize((float) (14));
 //BA.debugLineNum = 183;BA.debugLine="Cale.AddView(Lab, i*40dip, 0dip, 40dip, 40dip)";
_cale.AddView((android.view.View)(_lab.getObject()),(int) (_i*__c.DipToCurrent((int) (40))),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (40)),__c.DipToCurrent((int) (40)));
 }
};
 //BA.debugLineNum = 186;BA.debugLine="Y=40dip";
_y = __c.DipToCurrent((int) (40));
 //BA.debugLineNum = 187;BA.debugLine="X=(GDay-1)*40dip";
_x = (int) ((_gday-1)*__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 188;BA.debugLine="For i=1 To 31";
{
final int step32 = 1;
final int limit32 = (int) (31);
_i = (int) (1) ;
for (;_i <= limit32 ;_i = _i + step32 ) {
 //BA.debugLineNum = 189;BA.debugLine="If DateTime.GetMonth(DateStart)=m Then";
if (__c.DateTime.GetMonth(_datestart)==_m) { 
 //BA.debugLineNum = 190;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 192;BA.debugLine="Lab.Initialize(\"Giorno\")";
_lab.Initialize(ba,"Giorno");
 //BA.debugLineNum = 193;BA.debugLine="If d=i Then";
if (_d==_i) { 
 //BA.debugLineNum = 194;BA.debugLine="Lab.Background=Corner(Colore)";
_lab.setBackground((android.graphics.drawable.Drawable)(_corner(_colore).getObject()));
 //BA.debugLineNum = 195;BA.debugLine="Lab.Textcolor=Colors.White";
_lab.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 196;BA.debugLine="LabelNow=Lab";
_labelnow = _lab;
 //BA.debugLineNum = 197;BA.debugLine="If (((SundayDaysRed And (DateTime.GetDayOfWeek";
if ((((_sundaydaysred && (__c.DateTime.GetDayOfWeek(_datestart)==1))) || ((_saturdaydaysred && (__c.DateTime.GetDayOfWeek(_datestart)==7))))) { 
 //BA.debugLineNum = 198;BA.debugLine="ColorLabelNow = Colors.Red";
_colorlabelnow = __c.Colors.Red;
 }else {
 //BA.debugLineNum = 200;BA.debugLine="ColorLabelNow = Colors.Black";
_colorlabelnow = __c.Colors.Black;
 };
 }else {
 //BA.debugLineNum = 203;BA.debugLine="Lab.Color=Colors.Transparent";
_lab.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 204;BA.debugLine="If (((SundayDaysRed And (DateTime.GetDayOfWeek";
if ((((_sundaydaysred && (__c.DateTime.GetDayOfWeek(_datestart)==1))) || ((_saturdaydaysred && (__c.DateTime.GetDayOfWeek(_datestart)==7))))) { 
 //BA.debugLineNum = 205;BA.debugLine="Lab.TextColor = Colors.Red";
_lab.setTextColor(__c.Colors.Red);
 }else {
 //BA.debugLineNum = 207;BA.debugLine="Lab.TextColor = Colors.Black";
_lab.setTextColor(__c.Colors.Black);
 };
 };
 //BA.debugLineNum = 210;BA.debugLine="Lab.Text=i";
_lab.setText(BA.ObjectToCharSequence(_i));
 //BA.debugLineNum = 211;BA.debugLine="Lab.Tag=DateStart";
_lab.setTag((Object)(_datestart));
 //BA.debugLineNum = 212;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 213;BA.debugLine="Lab.TextSize=14";
_lab.setTextSize((float) (14));
 //BA.debugLineNum = 214;BA.debugLine="Cale.AddView(Lab,X,Y,40dip,40dip)";
_cale.AddView((android.view.View)(_lab.getObject()),_x,_y,__c.DipToCurrent((int) (40)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 216;BA.debugLine="GDay=GDay+1";
_gday = (int) (_gday+1);
 //BA.debugLineNum = 217;BA.debugLine="If GDay>7 Then";
if (_gday>7) { 
 //BA.debugLineNum = 218;BA.debugLine="GDay=1";
_gday = (int) (1);
 //BA.debugLineNum = 219;BA.debugLine="Y=Y+35dip";
_y = (int) (_y+__c.DipToCurrent((int) (35)));
 //BA.debugLineNum = 220;BA.debugLine="X=0dip";
_x = __c.DipToCurrent((int) (0));
 }else {
 //BA.debugLineNum = 222;BA.debugLine="X=X+40dip";
_x = (int) (_x+__c.DipToCurrent((int) (40)));
 };
 };
 //BA.debugLineNum = 225;BA.debugLine="DateStart=DateTime.Add(DateStart, 0, 0, 1)";
_datestart = __c.DateTime.Add(_datestart,(int) (0),(int) (0),(int) (1));
 }
};
 //BA.debugLineNum = 229;BA.debugLine="ButtonOk.Initialize(\"ButtonOk\")";
_buttonok.Initialize(ba,"ButtonOk");
 //BA.debugLineNum = 230;BA.debugLine="ButtonOk.Color=Colors.Transparent";
_buttonok.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 231;BA.debugLine="ButtonOk.TextColor=Colore";
_buttonok.setTextColor(_colore);
 //BA.debugLineNum = 232;BA.debugLine="ButtonOk.Gravity=Gravity.CENTER";
_buttonok.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 233;BA.debugLine="ButtonOk.Typeface=Typeface.DEFAULT_BOLD";
_buttonok.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 234;BA.debugLine="ButtonOk.Text=\"OK\"";
_buttonok.setText(BA.ObjectToCharSequence("OK"));
 //BA.debugLineNum = 235;BA.debugLine="ButtonOk.Textsize=14";
_buttonok.setTextSize((float) (14));
 //BA.debugLineNum = 236;BA.debugLine="Cale.AddView(ButtonOk, 180dip, 240dip, 80dip, 40d";
_cale.AddView((android.view.View)(_buttonok.getObject()),__c.DipToCurrent((int) (180)),__c.DipToCurrent((int) (240)),__c.DipToCurrent((int) (80)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 238;BA.debugLine="ButtoCancel.Initialize(\"ButtonCancel\")";
_buttocancel.Initialize(ba,"ButtonCancel");
 //BA.debugLineNum = 239;BA.debugLine="ButtoCancel.Color=Colors.Transparent";
_buttocancel.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 240;BA.debugLine="ButtoCancel.TextColor=Colore";
_buttocancel.setTextColor(_colore);
 //BA.debugLineNum = 241;BA.debugLine="ButtoCancel.Gravity=Gravity.CENTER";
_buttocancel.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 242;BA.debugLine="ButtoCancel.Typeface=Typeface.DEFAULT_BOLD";
_buttocancel.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 243;BA.debugLine="ButtoCancel.Text=\"CANCEL\"";
_buttocancel.setText(BA.ObjectToCharSequence("CANCEL"));
 //BA.debugLineNum = 244;BA.debugLine="ButtoCancel.Textsize=14";
_buttocancel.setTextSize((float) (14));
 //BA.debugLineNum = 245;BA.debugLine="Cale.AddView(ButtoCancel,20dip,240dip,80dip,40dip";
_cale.AddView((android.view.View)(_buttocancel.getObject()),__c.DipToCurrent((int) (20)),__c.DipToCurrent((int) (240)),__c.DipToCurrent((int) (80)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 246;BA.debugLine="End Sub";
return "";
}
public String  _filltesta(String _title) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lab = null;
 //BA.debugLineNum = 248;BA.debugLine="private Sub FillTesta(Title As String)";
 //BA.debugLineNum = 250;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 251;BA.debugLine="Lab.Initialize(\"\")";
_lab.Initialize(ba,"");
 //BA.debugLineNum = 252;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 253;BA.debugLine="Lab.Text=Title";
_lab.setText(BA.ObjectToCharSequence(_title));
 //BA.debugLineNum = 254;BA.debugLine="Lab.TextSize=14";
_lab.setTextSize((float) (14));
 //BA.debugLineNum = 255;BA.debugLine="Lab.Textcolor=Colors.White";
_lab.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 256;BA.debugLine="Lab.Color=Colors.ARGB(150,0,0,0)";
_lab.setColor(__c.Colors.ARGB((int) (150),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 257;BA.debugLine="Testa.AddView(Lab,0dip,0dip,280dip,30dip)";
_testa.AddView((android.view.View)(_lab.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (30)));
 //BA.debugLineNum = 260;BA.debugLine="LMounth.Initialize(\"\")";
_lmounth.Initialize(ba,"");
 //BA.debugLineNum = 261;BA.debugLine="LMounth.Gravity=Gravity.CENTER";
_lmounth.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 262;BA.debugLine="LMounth.Text=NomeMese(DateTime.GetMonth(DateRet))";
_lmounth.setText(BA.ObjectToCharSequence(_nomemese(__c.DateTime.GetMonth(_dateret))));
 //BA.debugLineNum = 263;BA.debugLine="LMounth.TextSize=25";
_lmounth.setTextSize((float) (25));
 //BA.debugLineNum = 264;BA.debugLine="LMounth.Textcolor=Colors.White";
_lmounth.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 265;BA.debugLine="LMounth.Color=Colors.Transparent";
_lmounth.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 266;BA.debugLine="Testa.AddView(LMounth,0dip,30dip,280dip,60dip)";
_testa.AddView((android.view.View)(_lmounth.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (30)),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (60)));
 //BA.debugLineNum = 268;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 269;BA.debugLine="Lab.Initialize(\"PrevM\")";
_lab.Initialize(ba,"PrevM");
 //BA.debugLineNum = 270;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 271;BA.debugLine="Lab.Text=\"<\"";
_lab.setText(BA.ObjectToCharSequence("<"));
 //BA.debugLineNum = 272;BA.debugLine="Lab.TextSize=30";
_lab.setTextSize((float) (30));
 //BA.debugLineNum = 273;BA.debugLine="Lab.Textcolor=Colors.ARGB(200,255,255,255)";
_lab.setTextColor(__c.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 274;BA.debugLine="Lab.Color=Colors.Transparent";
_lab.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 275;BA.debugLine="Testa.AddView(Lab,10dip,30dip,40dip,40dip)";
_testa.AddView((android.view.View)(_lab.getObject()),__c.DipToCurrent((int) (10)),__c.DipToCurrent((int) (30)),__c.DipToCurrent((int) (40)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 277;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 278;BA.debugLine="Lab.Initialize(\"NextM\")";
_lab.Initialize(ba,"NextM");
 //BA.debugLineNum = 279;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 280;BA.debugLine="Lab.Text=\">\"";
_lab.setText(BA.ObjectToCharSequence(">"));
 //BA.debugLineNum = 281;BA.debugLine="Lab.TextSize=30";
_lab.setTextSize((float) (30));
 //BA.debugLineNum = 282;BA.debugLine="Lab.Textcolor=Colors.ARGB(200,255,255,255)";
_lab.setTextColor(__c.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 283;BA.debugLine="Lab.Color=Colors.Transparent";
_lab.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 284;BA.debugLine="Testa.AddView(Lab,230dip,30dip,40dip,40dip)";
_testa.AddView((android.view.View)(_lab.getObject()),__c.DipToCurrent((int) (230)),__c.DipToCurrent((int) (30)),__c.DipToCurrent((int) (40)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 287;BA.debugLine="LDay.Initialize(\"\")";
_lday.Initialize(ba,"");
 //BA.debugLineNum = 288;BA.debugLine="LDay.Gravity=Gravity.CENTER";
_lday.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 289;BA.debugLine="LDay.Text=DateTime.GetDayOfMonth(DateRet)";
_lday.setText(BA.ObjectToCharSequence(__c.DateTime.GetDayOfMonth(_dateret)));
 //BA.debugLineNum = 290;BA.debugLine="LDay.TextSize=100";
_lday.setTextSize((float) (100));
 //BA.debugLineNum = 291;BA.debugLine="LDay.Textcolor=Colors.White";
_lday.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 292;BA.debugLine="LDay.Typeface=Typeface.SANS_SERIF";
_lday.setTypeface(__c.Typeface.SANS_SERIF);
 //BA.debugLineNum = 293;BA.debugLine="LDay.Color=Colors.Transparent";
_lday.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 294;BA.debugLine="Testa.AddView(LDay,0dip,70dip,280dip,120dip)";
_testa.AddView((android.view.View)(_lday.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (70)),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (120)));
 //BA.debugLineNum = 297;BA.debugLine="LYear.Initialize(\"\")";
_lyear.Initialize(ba,"");
 //BA.debugLineNum = 298;BA.debugLine="LYear.Gravity=Gravity.CENTER";
_lyear.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 299;BA.debugLine="LYear.Text=DateTime.GetYear(DateRet)";
_lyear.setText(BA.ObjectToCharSequence(__c.DateTime.GetYear(_dateret)));
 //BA.debugLineNum = 300;BA.debugLine="LYear.TextSize=30";
_lyear.setTextSize((float) (30));
 //BA.debugLineNum = 301;BA.debugLine="LYear.Textcolor=Colors.ARGB(200,255,255,255)";
_lyear.setTextColor(__c.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 302;BA.debugLine="LYear.Color=Colors.Transparent";
_lyear.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 303;BA.debugLine="Testa.AddView(LYear,0dip,190dip,280dip,40dip)";
_testa.AddView((android.view.View)(_lyear.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (190)),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 305;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 306;BA.debugLine="Lab.Initialize(\"PrevY\")";
_lab.Initialize(ba,"PrevY");
 //BA.debugLineNum = 307;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 308;BA.debugLine="Lab.Text=\"<\"";
_lab.setText(BA.ObjectToCharSequence("<"));
 //BA.debugLineNum = 309;BA.debugLine="Lab.TextSize=30";
_lab.setTextSize((float) (30));
 //BA.debugLineNum = 310;BA.debugLine="Lab.Textcolor=Colors.ARGB(200,255,255,255)";
_lab.setTextColor(__c.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 311;BA.debugLine="Lab.Color=Colors.Transparent";
_lab.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 312;BA.debugLine="Testa.AddView(Lab,10dip,190dip,40dip,40dip)";
_testa.AddView((android.view.View)(_lab.getObject()),__c.DipToCurrent((int) (10)),__c.DipToCurrent((int) (190)),__c.DipToCurrent((int) (40)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 314;BA.debugLine="Dim Lab As Label";
_lab = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 315;BA.debugLine="Lab.Initialize(\"NextY\")";
_lab.Initialize(ba,"NextY");
 //BA.debugLineNum = 316;BA.debugLine="Lab.Gravity=Gravity.CENTER";
_lab.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 317;BA.debugLine="Lab.Text=\">\"";
_lab.setText(BA.ObjectToCharSequence(">"));
 //BA.debugLineNum = 318;BA.debugLine="Lab.TextSize=30";
_lab.setTextSize((float) (30));
 //BA.debugLineNum = 319;BA.debugLine="Lab.Textcolor=Colors.ARGB(200,255,255,255)";
_lab.setTextColor(__c.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 320;BA.debugLine="Lab.Color=Colors.Transparent";
_lab.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 321;BA.debugLine="Testa.AddView(Lab,230dip,190dip,40dip,40dip)";
_testa.AddView((android.view.View)(_lab.getObject()),__c.DipToCurrent((int) (230)),__c.DipToCurrent((int) (190)),__c.DipToCurrent((int) (40)),__c.DipToCurrent((int) (40)));
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public String  _giorno_click() throws Exception{
 //BA.debugLineNum = 358;BA.debugLine="private Sub Giorno_Click";
 //BA.debugLineNum = 359;BA.debugLine="LabelNow.Color=Colors.Transparent";
_labelnow.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 360;BA.debugLine="LabelNow.TextColor=ColorLabelNow";
_labelnow.setTextColor(_colorlabelnow);
 //BA.debugLineNum = 362;BA.debugLine="LabelNow=Sender";
_labelnow.setObject((android.widget.TextView)(__c.Sender(ba)));
 //BA.debugLineNum = 363;BA.debugLine="ColorLabelNow=LabelNow.Textcolor";
_colorlabelnow = _labelnow.getTextColor();
 //BA.debugLineNum = 364;BA.debugLine="LabelNow.Background=Corner(Colore)";
_labelnow.setBackground((android.graphics.drawable.Drawable)(_corner(_colore).getObject()));
 //BA.debugLineNum = 365;BA.debugLine="LabelNow.Textcolor=Colors.White";
_labelnow.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 367;BA.debugLine="DateRet=LabelNow.Tag";
_dateret = BA.ObjectToLongNumber(_labelnow.getTag());
 //BA.debugLineNum = 368;BA.debugLine="LDay.Text=LabelNow.Text";
_lday.setText(BA.ObjectToCharSequence(_labelnow.getText()));
 //BA.debugLineNum = 369;BA.debugLine="End Sub";
return "";
}
public String  _initgiorno() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Private Sub InitGiorno()";
 //BA.debugLineNum = 87;BA.debugLine="Select Lingua";
switch (BA.switchObjectToInt(_lingua(),"it","fr","ru","es","de","pt","ko")) {
case 0: {
 //BA.debugLineNum = 89;BA.debugLine="listDays = Array As String (\"D\",\"L\",\"M\",\"M\",\"G\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"D","L","M","M","G","V","S","D"});
 break; }
case 1: {
 //BA.debugLineNum = 91;BA.debugLine="listDays = Array As String (\"D\",\"L\",\"M\",\"M\",\"J\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"D","L","M","M","J","V","S","D"});
 break; }
case 2: {
 //BA.debugLineNum = 93;BA.debugLine="listDays = Array As String (\"В\",\"П\",\"В\",\"С\",\"Ч\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"В","П","В","С","Ч","П","С","B"});
 break; }
case 3: {
 //BA.debugLineNum = 95;BA.debugLine="listDays = Array As String (\"D\",\"L\",\"M\",\"M\",\"J\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"D","L","M","M","J","V","S","D"});
 break; }
case 4: {
 //BA.debugLineNum = 97;BA.debugLine="listDays = Array As String (\"S\",\"M\",\"D\",\"M\",\"D\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"S","M","D","M","D","F","S","S"});
 break; }
case 5: {
 //BA.debugLineNum = 99;BA.debugLine="listDays = Array As String (\"D\",\"S\",\"T\",\"Q\",\"Q\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"D","S","T","Q","Q","S","S","D"});
 break; }
case 6: {
 //BA.debugLineNum = 101;BA.debugLine="listDays = Array As String (\"일\",\"월\",\"화\",\"수\",\"목\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"일","월","화","수","목","금","토","일"});
 break; }
default: {
 //BA.debugLineNum = 103;BA.debugLine="listDays = Array As String (\"S\",\"M\",\"T\",\"W\",\"T\"";
_listdays = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"S","M","T","W","T","F","S","S"});
 break; }
}
;
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _myactivity,long _date) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 31;BA.debugLine="Public Sub Initialize(Myactivity As Activity, Date";
 //BA.debugLineNum = 32;BA.debugLine="MyAct=Myactivity";
_myact = _myactivity;
 //BA.debugLineNum = 33;BA.debugLine="DateSelected=Date";
_dateselected = _date;
 //BA.debugLineNum = 34;BA.debugLine="DateRet=Date";
_dateret = _date;
 //BA.debugLineNum = 35;BA.debugLine="InitGiorno";
_initgiorno();
 //BA.debugLineNum = 36;BA.debugLine="InitMese";
_initmese();
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public String  _initmese() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Private Sub InitMese()";
 //BA.debugLineNum = 108;BA.debugLine="Select Lingua";
switch (BA.switchObjectToInt(_lingua(),"it","fr","ru","es","de","pt","ko")) {
case 0: {
 //BA.debugLineNum = 110;BA.debugLine="listMonths = Array As String (\"Gennaio\",\"Febbra";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre"});
 break; }
case 1: {
 //BA.debugLineNum = 112;BA.debugLine="listMonths = Array As String (\"Janvier\",\"Févrie";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"});
 break; }
case 2: {
 //BA.debugLineNum = 114;BA.debugLine="listMonths = Array As String (\"Январь\",\"Февраль";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"});
 break; }
case 3: {
 //BA.debugLineNum = 116;BA.debugLine="listMonths = Array As String (\"Enero\",\"Febrero\"";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio​​","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"});
 break; }
case 4: {
 //BA.debugLineNum = 118;BA.debugLine="listMonths = Array As String (\"Januar\",\"Februar";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Januar","Februar","März","April","Mai","June","Juli","August","September","October","November","December"});
 break; }
case 5: {
 //BA.debugLineNum = 120;BA.debugLine="listMonths = Array As String (\"Janeiro\",\"fevere";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Janeiro","fevereiro","March"," Abril","pode","June","Julho","August","September","Outubro","November","dezembro"});
 break; }
case 6: {
 //BA.debugLineNum = 122;BA.debugLine="listMonths = Array As String (\"1월\",\"2월\",\"3월\",\"4";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"});
 break; }
default: {
 //BA.debugLineNum = 124;BA.debugLine="listMonths = Array As String (\"January\",\"Februa";
_listmonths = anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"});
 break; }
}
;
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public String  _lingua() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
String _s = "";
 //BA.debugLineNum = 136;BA.debugLine="Private Sub Lingua As String";
 //BA.debugLineNum = 137;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 138;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 139;BA.debugLine="r.Target = r.RunStaticMethod(\"java.util.Locale\",";
_r.Target = _r.RunStaticMethod("java.util.Locale","getDefault",(Object[])(__c.Null),(String[])(__c.Null));
 //BA.debugLineNum = 140;BA.debugLine="s = r.RunMethod(\"getLanguage\")";
_s = BA.ObjectToString(_r.RunMethod("getLanguage"));
 //BA.debugLineNum = 141;BA.debugLine="Return s.SubString2(0, 2)";
if (true) return _s.substring((int) (0),(int) (2));
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public String  _movedate(int _deltamonth,int _deltayear) throws Exception{
 //BA.debugLineNum = 371;BA.debugLine="Private Sub MoveDate(deltaMonth As Int, deltaYear";
 //BA.debugLineNum = 372;BA.debugLine="DateRet = DateTime.Add(DateRet, deltaYear, deltaM";
_dateret = __c.DateTime.Add(_dateret,_deltayear,_deltamonth,(int) (0));
 //BA.debugLineNum = 373;BA.debugLine="FillCalendar";
_fillcalendar();
 //BA.debugLineNum = 374;BA.debugLine="LMounth.Text=NomeMese(DateTime.GetMonth(DateRet))";
_lmounth.setText(BA.ObjectToCharSequence(_nomemese(__c.DateTime.GetMonth(_dateret))));
 //BA.debugLineNum = 375;BA.debugLine="LYear.Text=DateTime.GetYear(DateRet)";
_lyear.setText(BA.ObjectToCharSequence(__c.DateTime.GetYear(_dateret)));
 //BA.debugLineNum = 376;BA.debugLine="LDay.Text=DateTime.GetDayOfMonth(DateRet)";
_lday.setText(BA.ObjectToCharSequence(__c.DateTime.GetDayOfMonth(_dateret)));
 //BA.debugLineNum = 377;BA.debugLine="End Sub";
return "";
}
public String  _nextm_click() throws Exception{
 //BA.debugLineNum = 383;BA.debugLine="Private Sub NextM_Click";
 //BA.debugLineNum = 384;BA.debugLine="MoveDate(1, 0)";
_movedate((int) (1),(int) (0));
 //BA.debugLineNum = 385;BA.debugLine="End Sub";
return "";
}
public String  _nexty_click() throws Exception{
 //BA.debugLineNum = 391;BA.debugLine="Private Sub NextY_Click";
 //BA.debugLineNum = 392;BA.debugLine="MoveDate(0, 1)";
_movedate((int) (0),(int) (1));
 //BA.debugLineNum = 393;BA.debugLine="End Sub";
return "";
}
public String  _nomegiorno(int _d) throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Private Sub NomeGiorno(D As Int) As String";
 //BA.debugLineNum = 129;BA.debugLine="Return listDays.Get(D+DayShift)";
if (true) return BA.ObjectToString(_listdays.Get((int) (_d+_dayshift)));
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public String  _nomemese(int _m) throws Exception{
 //BA.debugLineNum = 132;BA.debugLine="private Sub NomeMese(M As Int) As String";
 //BA.debugLineNum = 133;BA.debugLine="Return listMonths.Get(M-1)";
if (true) return BA.ObjectToString(_listmonths.Get((int) (_m-1)));
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public String  _prevm_click() throws Exception{
 //BA.debugLineNum = 379;BA.debugLine="Private Sub PrevM_Click";
 //BA.debugLineNum = 380;BA.debugLine="MoveDate(-1, 0)";
_movedate((int) (-1),(int) (0));
 //BA.debugLineNum = 381;BA.debugLine="End Sub";
return "";
}
public String  _prevy_click() throws Exception{
 //BA.debugLineNum = 387;BA.debugLine="Private Sub PrevY_Click";
 //BA.debugLineNum = 388;BA.debugLine="MoveDate(0, -1)";
_movedate((int) (0),(int) (-1));
 //BA.debugLineNum = 389;BA.debugLine="End Sub";
return "";
}
public String  _setdate(long _date) throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Public Sub SetDate(Date As Long)";
 //BA.debugLineNum = 146;BA.debugLine="DateSelected=Date";
_dateselected = _date;
 //BA.debugLineNum = 147;BA.debugLine="DateRet=Date";
_dateret = _date;
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public String  _setredsaturday(int _isred) throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Public Sub setRedSaturday(IsRed As Int)";
 //BA.debugLineNum = 66;BA.debugLine="Select Case IsRed";
switch (_isred) {
case 0: {
 //BA.debugLineNum = 68;BA.debugLine="SaturdayHdrRed=False";
_saturdayhdrred = __c.False;
 //BA.debugLineNum = 69;BA.debugLine="SaturdayDaysRed=False";
_saturdaydaysred = __c.False;
 break; }
case 1: {
 //BA.debugLineNum = 71;BA.debugLine="SaturdayHdrRed=True";
_saturdayhdrred = __c.True;
 //BA.debugLineNum = 72;BA.debugLine="SaturdayDaysRed=False";
_saturdaydaysred = __c.False;
 break; }
case 2: {
 //BA.debugLineNum = 74;BA.debugLine="SaturdayHdrRed=True";
_saturdayhdrred = __c.True;
 //BA.debugLineNum = 75;BA.debugLine="SaturdayDaysRed=True";
_saturdaydaysred = __c.True;
 break; }
}
;
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public String  _setredsunday(int _isred) throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Public Sub setRedSunday(IsRed As Int)";
 //BA.debugLineNum = 49;BA.debugLine="Select Case IsRed";
switch (_isred) {
case 0: {
 //BA.debugLineNum = 51;BA.debugLine="SundayHdrRed=False";
_sundayhdrred = __c.False;
 //BA.debugLineNum = 52;BA.debugLine="SundayDaysRed=False";
_sundaydaysred = __c.False;
 break; }
case 1: {
 //BA.debugLineNum = 54;BA.debugLine="SundayHdrRed=True";
_sundayhdrred = __c.True;
 //BA.debugLineNum = 55;BA.debugLine="SundayDaysRed=False";
_sundaydaysred = __c.False;
 break; }
case 2: {
 //BA.debugLineNum = 57;BA.debugLine="SundayHdrRed=True";
_sundayhdrred = __c.True;
 //BA.debugLineNum = 58;BA.debugLine="SundayDaysRed=True";
_sundaydaysred = __c.True;
 break; }
}
;
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public String  _setstartonmonday(boolean _startonmonday) throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Public Sub setStartOnMonday(StartOnMonday As Boole";
 //BA.debugLineNum = 42;BA.debugLine="If StartOnMonday Then DayShift=1";
if (_startonmonday) { 
_dayshift = (int) (1);};
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public int  _show(String _title) throws Exception{
 //BA.debugLineNum = 324;BA.debugLine="Public Sub Show(Title As String) As Int";
 //BA.debugLineNum = 326;BA.debugLine="Pa.Initialize(\"Pa\")";
_pa.Initialize(ba,"Pa");
 //BA.debugLineNum = 327;BA.debugLine="Pa.Color=Colors.ARGB(200,0,0,0)";
_pa.setColor(__c.Colors.ARGB((int) (200),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 329;BA.debugLine="Cale.Initialize(\"Cale\")";
_cale.Initialize(ba,"Cale");
 //BA.debugLineNum = 330;BA.debugLine="Cale.Color=Colors.White";
_cale.setColor(__c.Colors.White);
 //BA.debugLineNum = 332;BA.debugLine="Testa.Initialize(\"Testa\")";
_testa.Initialize(ba,"Testa");
 //BA.debugLineNum = 333;BA.debugLine="Testa. Color=Colore";
_testa.setColor(_colore);
 //BA.debugLineNum = 335;BA.debugLine="FillCalendar";
_fillcalendar();
 //BA.debugLineNum = 336;BA.debugLine="FillTesta(Title)";
_filltesta(_title);
 //BA.debugLineNum = 338;BA.debugLine="If 100%y>100%x Then";
if (__c.PerYToCurrent((float) (100),ba)>__c.PerXToCurrent((float) (100),ba)) { 
 //BA.debugLineNum = 340;BA.debugLine="Pa.AddView(Testa,50%x-140dip,50%y-265dip,280dip,";
_pa.AddView((android.view.View)(_testa.getObject()),(int) (__c.PerXToCurrent((float) (50),ba)-__c.DipToCurrent((int) (140))),(int) (__c.PerYToCurrent((float) (50),ba)-__c.DipToCurrent((int) (265))),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (250)));
 //BA.debugLineNum = 341;BA.debugLine="Pa.AddView(Cale,50%x-140dip,50%y-15dip,280dip,28";
_pa.AddView((android.view.View)(_cale.getObject()),(int) (__c.PerXToCurrent((float) (50),ba)-__c.DipToCurrent((int) (140))),(int) (__c.PerYToCurrent((float) (50),ba)-__c.DipToCurrent((int) (15))),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (280)));
 //BA.debugLineNum = 342;BA.debugLine="MyAct.AddView(Pa,0dip,0dip,100%x,100%y)";
_myact.AddView((android.view.View)(_pa.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 }else {
 //BA.debugLineNum = 345;BA.debugLine="Pa.AddView(Testa,50%x-280dip,50%y-140dip,280dip,";
_pa.AddView((android.view.View)(_testa.getObject()),(int) (__c.PerXToCurrent((float) (50),ba)-__c.DipToCurrent((int) (280))),(int) (__c.PerYToCurrent((float) (50),ba)-__c.DipToCurrent((int) (140))),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (280)));
 //BA.debugLineNum = 346;BA.debugLine="Pa.AddView(Cale,50%x,50%y-140dip,280dip,280dip)";
_pa.AddView((android.view.View)(_cale.getObject()),__c.PerXToCurrent((float) (50),ba),(int) (__c.PerYToCurrent((float) (50),ba)-__c.DipToCurrent((int) (140))),__c.DipToCurrent((int) (280)),__c.DipToCurrent((int) (280)));
 //BA.debugLineNum = 347;BA.debugLine="MyAct.AddView(Pa,0dip,0dip,100%x,100%y)";
_myact.AddView((android.view.View)(_pa.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 };
 //BA.debugLineNum = 350;BA.debugLine="Do While Ret=0";
while (_ret==0) {
 //BA.debugLineNum = 351;BA.debugLine="DoEvents";
__c.DoEvents();
 }
;
 //BA.debugLineNum = 354;BA.debugLine="Pa.RemoveView";
_pa.RemoveView();
 //BA.debugLineNum = 355;BA.debugLine="Return Ret";
if (true) return _ret;
 //BA.debugLineNum = 356;BA.debugLine="End Sub";
return 0;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
