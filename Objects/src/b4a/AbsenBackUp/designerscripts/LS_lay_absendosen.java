package b4a.AbsenBackUp.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_lay_absendosen{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _gap="";
String _w="";
String _w_jml="";
String _b_4="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
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
//BA.debugLineNum = 24;BA.debugLine="lbl_namadosen.Left = Label5.Left"[lay_AbsenDosen/General script]
views.get("lbl_namadosen").vw.setLeft((int)((views.get("label5").vw.getLeft())));
//BA.debugLineNum = 25;BA.debugLine="lbl_namadosen.Width = Label5.Width"[lay_AbsenDosen/General script]
views.get("lbl_namadosen").vw.setWidth((int)((views.get("label5").vw.getWidth())));
//BA.debugLineNum = 49;BA.debugLine="w_jml = (pnl_homeDosen.Width - 4 * gap) / 4"[lay_AbsenDosen/General script]
_w_jml = BA.NumberToString(((views.get("pnl_homedosen").vw.getWidth())-4d*Double.parseDouble(_gap))/4d);
//BA.debugLineNum = 51;BA.debugLine="CLV_NamaMHS.HorizontalCenter = pnl_homeDosen.Width/2"[lay_AbsenDosen/General script]
views.get("clv_namamhs").vw.setLeft((int)((views.get("pnl_homedosen").vw.getWidth())/2d - (views.get("clv_namamhs").vw.getWidth() / 2)));
//BA.debugLineNum = 53;BA.debugLine="img_scanner.VerticalCenter = Panel1.Height/2"[lay_AbsenDosen/General script]
views.get("img_scanner").vw.setTop((int)((views.get("panel1").vw.getHeight())/2d - (views.get("img_scanner").vw.getHeight() / 2)));
//BA.debugLineNum = 54;BA.debugLine="img_scanner.HorizontalCenter = Panel1.Width/2"[lay_AbsenDosen/General script]
views.get("img_scanner").vw.setLeft((int)((views.get("panel1").vw.getWidth())/2d - (views.get("img_scanner").vw.getWidth() / 2)));
//BA.debugLineNum = 57;BA.debugLine="B_4 = (pnl_homeDosen.Width - 4 * gap) / 3.5"[lay_AbsenDosen/General script]
_b_4 = BA.NumberToString(((views.get("pnl_homedosen").vw.getWidth())-4d*Double.parseDouble(_gap))/3.5d);
//BA.debugLineNum = 59;BA.debugLine="pnl_kalkulasi.Width = B_4"[lay_AbsenDosen/General script]
views.get("pnl_kalkulasi").vw.setWidth((int)(Double.parseDouble(_b_4)));
//BA.debugLineNum = 60;BA.debugLine="Label6.Width = pnl_kalkulasi.Width"[lay_AbsenDosen/General script]
views.get("label6").vw.setWidth((int)((views.get("pnl_kalkulasi").vw.getWidth())));
//BA.debugLineNum = 61;BA.debugLine="ImageView1.HorizontalCenter = pnl_kalkulasi.Width/2"[lay_AbsenDosen/General script]
views.get("imageview1").vw.setLeft((int)((views.get("pnl_kalkulasi").vw.getWidth())/2d - (views.get("imageview1").vw.getWidth() / 2)));
//BA.debugLineNum = 63;BA.debugLine="pnl_rekap_absen.Left = pnl_kalkulasi.Right + gap"[lay_AbsenDosen/General script]
views.get("pnl_rekap_absen").vw.setLeft((int)((views.get("pnl_kalkulasi").vw.getLeft() + views.get("pnl_kalkulasi").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 64;BA.debugLine="pnl_rekap_absen.Width = B_4"[lay_AbsenDosen/General script]
views.get("pnl_rekap_absen").vw.setWidth((int)(Double.parseDouble(_b_4)));
//BA.debugLineNum = 65;BA.debugLine="Label7.Width = pnl_rekap_absen.Width"[lay_AbsenDosen/General script]
views.get("label7").vw.setWidth((int)((views.get("pnl_rekap_absen").vw.getWidth())));
//BA.debugLineNum = 66;BA.debugLine="ImageView2.HorizontalCenter = pnl_rekap_absen.Width/2"[lay_AbsenDosen/General script]
views.get("imageview2").vw.setLeft((int)((views.get("pnl_rekap_absen").vw.getWidth())/2d - (views.get("imageview2").vw.getWidth() / 2)));
//BA.debugLineNum = 68;BA.debugLine="pnl_absen_harini.Left = pnl_rekap_absen.Right + gap"[lay_AbsenDosen/General script]
views.get("pnl_absen_harini").vw.setLeft((int)((views.get("pnl_rekap_absen").vw.getLeft() + views.get("pnl_rekap_absen").vw.getWidth())+Double.parseDouble(_gap)));
//BA.debugLineNum = 69;BA.debugLine="pnl_absen_harini.Width = B_4"[lay_AbsenDosen/General script]
views.get("pnl_absen_harini").vw.setWidth((int)(Double.parseDouble(_b_4)));
//BA.debugLineNum = 70;BA.debugLine="Label8.Width = pnl_absen_harini.Width"[lay_AbsenDosen/General script]
views.get("label8").vw.setWidth((int)((views.get("pnl_absen_harini").vw.getWidth())));
//BA.debugLineNum = 71;BA.debugLine="ImageView3.HorizontalCenter = pnl_absen_harini.Width/2"[lay_AbsenDosen/General script]
views.get("imageview3").vw.setLeft((int)((views.get("pnl_absen_harini").vw.getWidth())/2d - (views.get("imageview3").vw.getWidth() / 2)));

}
}