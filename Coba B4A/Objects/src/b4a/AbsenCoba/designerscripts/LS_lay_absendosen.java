package b4a.AbsenCoba.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_lay_absendosen{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _gap="";
String _w="";
String _w_4="";
String _w_jml="";
//BA.debugLineNum = 1;BA.debugLine="AutoScaleAll"[lay_AbsenDosen/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="pnl_headKetDosen.HorizontalCenter = pnl_homeDosen.Width / 2"[lay_AbsenDosen/General script]
views.get("pnl_headketdosen").vw.setLeft((int)((views.get("pnl_homedosen").vw.getWidth())/2d - (views.get("pnl_headketdosen").vw.getWidth() / 2)));
//BA.debugLineNum = 6;BA.debugLine="gap = 10dip"[lay_AbsenDosen/General script]
_gap = BA.NumberToString((10d * scale));
//BA.debugLineNum = 8;BA.debugLine="w = (pnl_headKetDosen.Width -  4 * gap) / 3"[lay_AbsenDosen/General script]
_w = BA.NumberToString(((views.get("pnl_headketdosen").vw.getWidth())-4d*Double.parseDouble(_gap))/3d);
//BA.debugLineNum = 10;BA.debugLine="Label2.Width = w"[lay_AbsenDosen/General script]
views.get("label2").vw.setWidth((int)(Double.parseDouble(_w)));
//BA.debugLineNum = 12;BA.debugLine="Label4.Left = Label2.Right + gap"[lay_AbsenDosen/General script]
views.get("label4").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 13;BA.debugLine="Label4.Width = w"[lay_AbsenDosen/General script]
views.get("label4").vw.setWidth((int)(Double.parseDouble(_w)));
//BA.debugLineNum = 15;BA.debugLine="Label5.Left = Label4.Right + gap"[lay_AbsenDosen/General script]
views.get("label5").vw.setLeft((int)((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 16;BA.debugLine="Label5.Width = w"[lay_AbsenDosen/General script]
views.get("label5").vw.setWidth((int)(Double.parseDouble(_w)));
//BA.debugLineNum = 18;BA.debugLine="lbl_TanggalDosen.Left = Label2.Left"[lay_AbsenDosen/General script]
views.get("lbl_tanggaldosen").vw.setLeft((int)((views.get("label2").vw.getLeft())));
//BA.debugLineNum = 19;BA.debugLine="lbl_TanggalDosen.Width = Label2.Width"[lay_AbsenDosen/General script]
views.get("lbl_tanggaldosen").vw.setWidth((int)((views.get("label2").vw.getWidth())));
//BA.debugLineNum = 21;BA.debugLine="lbl_WaktuDosen.Left = Label4.Left"[lay_AbsenDosen/General script]
views.get("lbl_waktudosen").vw.setLeft((int)((views.get("label4").vw.getLeft())));
//BA.debugLineNum = 22;BA.debugLine="lbl_WaktuDosen.Width = Label4.Width"[lay_AbsenDosen/General script]
views.get("lbl_waktudosen").vw.setWidth((int)((views.get("label4").vw.getWidth())));
//BA.debugLineNum = 24;BA.debugLine="lbl_NamaDosen.Left = Label5.Left"[lay_AbsenDosen/General script]
views.get("lbl_namadosen").vw.setLeft((int)((views.get("label5").vw.getLeft())));
//BA.debugLineNum = 25;BA.debugLine="lbl_NamaDosen.Width = Label5.Width"[lay_AbsenDosen/General script]
views.get("lbl_namadosen").vw.setWidth((int)((views.get("label5").vw.getWidth())));
//BA.debugLineNum = 29;BA.debugLine="w_4 = (pnl_headKetDosen.Width - 6 * gap) / 4"[lay_AbsenDosen/General script]
_w_4 = BA.NumberToString(((views.get("pnl_headketdosen").vw.getWidth())-6d*Double.parseDouble(_gap))/4d);
//BA.debugLineNum = 32;BA.debugLine="Label6.Width = w_4"[lay_AbsenDosen/General script]
views.get("label6").vw.setWidth((int)(Double.parseDouble(_w_4)));
//BA.debugLineNum = 34;BA.debugLine="Label7.Left = Label6.Right + gap"[lay_AbsenDosen/General script]
views.get("label7").vw.setLeft((int)((views.get("label6").vw.getLeft() + views.get("label6").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 35;BA.debugLine="Label7.Width = w_4"[lay_AbsenDosen/General script]
views.get("label7").vw.setWidth((int)(Double.parseDouble(_w_4)));
//BA.debugLineNum = 37;BA.debugLine="Label8.Left = Label7.Right + gap"[lay_AbsenDosen/General script]
views.get("label8").vw.setLeft((int)((views.get("label7").vw.getLeft() + views.get("label7").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 38;BA.debugLine="Label8.Width = w_4"[lay_AbsenDosen/General script]
views.get("label8").vw.setWidth((int)(Double.parseDouble(_w_4)));
//BA.debugLineNum = 40;BA.debugLine="Label9.Left = Label8.Right + gap"[lay_AbsenDosen/General script]
views.get("label9").vw.setLeft((int)((views.get("label8").vw.getLeft() + views.get("label8").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 41;BA.debugLine="Label9.Width = w_4"[lay_AbsenDosen/General script]
views.get("label9").vw.setWidth((int)(Double.parseDouble(_w_4)));
//BA.debugLineNum = 43;BA.debugLine="img_hadir.HorizontalCenter = Label6.HorizontalCenter"[lay_AbsenDosen/General script]
views.get("img_hadir").vw.setLeft((int)((views.get("label6").vw.getLeft() + views.get("label6").vw.getWidth()/2) - (views.get("img_hadir").vw.getWidth() / 2)));
//BA.debugLineNum = 44;BA.debugLine="img_absen.HorizontalCenter = Label7.HorizontalCenter"[lay_AbsenDosen/General script]
views.get("img_absen").vw.setLeft((int)((views.get("label7").vw.getLeft() + views.get("label7").vw.getWidth()/2) - (views.get("img_absen").vw.getWidth() / 2)));
//BA.debugLineNum = 45;BA.debugLine="img_izin.HorizontalCenter = Label8.HorizontalCenter"[lay_AbsenDosen/General script]
views.get("img_izin").vw.setLeft((int)((views.get("label8").vw.getLeft() + views.get("label8").vw.getWidth()/2) - (views.get("img_izin").vw.getWidth() / 2)));
//BA.debugLineNum = 46;BA.debugLine="img_sakit.HorizontalCenter = Label9.HorizontalCenter"[lay_AbsenDosen/General script]
views.get("img_sakit").vw.setLeft((int)((views.get("label9").vw.getLeft() + views.get("label9").vw.getWidth()/2) - (views.get("img_sakit").vw.getWidth() / 2)));
//BA.debugLineNum = 49;BA.debugLine="w_jml = (pnl_homeDosen.Width - 4 * gap) / 4"[lay_AbsenDosen/General script]
_w_jml = BA.NumberToString(((views.get("pnl_homedosen").vw.getWidth())-4d*Double.parseDouble(_gap))/4d);
//BA.debugLineNum = 51;BA.debugLine="lbl_jumlahHadir.Width = w_jml"[lay_AbsenDosen/General script]
views.get("lbl_jumlahhadir").vw.setWidth((int)(Double.parseDouble(_w_jml)));
//BA.debugLineNum = 53;BA.debugLine="lbl_jumlahAbsen.Left = lbl_jumlahHadir.Right + gap"[lay_AbsenDosen/General script]
views.get("lbl_jumlahabsen").vw.setLeft((int)((views.get("lbl_jumlahhadir").vw.getLeft() + views.get("lbl_jumlahhadir").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 54;BA.debugLine="lbl_jumlahAbsen.Width = w_jml"[lay_AbsenDosen/General script]
views.get("lbl_jumlahabsen").vw.setWidth((int)(Double.parseDouble(_w_jml)));
//BA.debugLineNum = 56;BA.debugLine="lbl_jumlahizin.Left = lbl_jumlahAbsen.Right + gap"[lay_AbsenDosen/General script]
views.get("lbl_jumlahizin").vw.setLeft((int)((views.get("lbl_jumlahabsen").vw.getLeft() + views.get("lbl_jumlahabsen").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 57;BA.debugLine="lbl_jumlahizin.Width = w_jml"[lay_AbsenDosen/General script]
views.get("lbl_jumlahizin").vw.setWidth((int)(Double.parseDouble(_w_jml)));
//BA.debugLineNum = 59;BA.debugLine="lbl_jumlahsakit.Left = lbl_jumlahizin.Right + gap"[lay_AbsenDosen/General script]
views.get("lbl_jumlahsakit").vw.setLeft((int)((views.get("lbl_jumlahizin").vw.getLeft() + views.get("lbl_jumlahizin").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 60;BA.debugLine="lbl_jumlahsakit.Width = w_jml"[lay_AbsenDosen/General script]
views.get("lbl_jumlahsakit").vw.setWidth((int)(Double.parseDouble(_w_jml)));
//BA.debugLineNum = 62;BA.debugLine="CLV_NamaMHS.HorizontalCenter = pnl_homeDosen.Width/2"[lay_AbsenDosen/General script]
views.get("clv_namamhs").vw.setLeft((int)((views.get("pnl_homedosen").vw.getWidth())/2d - (views.get("clv_namamhs").vw.getWidth() / 2)));
//BA.debugLineNum = 64;BA.debugLine="ImageView2.VerticalCenter = Panel1.Height/2"[lay_AbsenDosen/General script]
views.get("imageview2").vw.setTop((int)((views.get("panel1").vw.getHeight())/2d - (views.get("imageview2").vw.getHeight() / 2)));
//BA.debugLineNum = 65;BA.debugLine="ImageView2.HorizontalCenter = Panel1.Width/2"[lay_AbsenDosen/General script]
views.get("imageview2").vw.setLeft((int)((views.get("panel1").vw.getWidth())/2d - (views.get("imageview2").vw.getWidth() / 2)));

}
}