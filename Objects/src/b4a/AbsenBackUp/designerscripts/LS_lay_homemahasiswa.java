package b4a.AbsenBackUp.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_lay_homemahasiswa{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _gap="";
String _h_jml="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("img_profil").vw.setLeft((int)((views.get("panel1").vw.getWidth())/2d - (views.get("img_profil").vw.getWidth() / 2)));
views.get("pnl_search").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
views.get("label2").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(5d * scale)));
views.get("pnl_biodata").vw.setHeight((int)((views.get("panel5").vw.getTop() + views.get("panel5").vw.getHeight()/2)/2d));
views.get("img_search").vw.setLeft((int)((views.get("pnl_search").vw.getWidth())/2d - (views.get("img_search").vw.getWidth() / 2)));
views.get("img_barcode").vw.setLeft((int)((views.get("panel_barcode").vw.getWidth())/2d - (views.get("img_barcode").vw.getWidth() / 2)));
views.get("pnl_biodata").vw.setTop((int)((views.get("panel6").vw.getHeight())/2d - (views.get("pnl_biodata").vw.getHeight() / 2)));
views.get("lbl_aktifmhs").vw.setLeft((int)((views.get("label2").vw.getLeft())));
views.get("lbl_aktifmhs").vw.setWidth((int)((views.get("label2").vw.getWidth())));
views.get("panel2").vw.setLeft((int)((views.get("lbl_aktifmhs").vw.getLeft())-(10d * scale) - (views.get("panel2").vw.getWidth())));
_gap = BA.NumberToString((10d * scale));
_h_jml = BA.NumberToString(((views.get("pnl_biodata").vw.getHeight())-2d*Double.parseDouble(_gap))/4d);
views.get("label3").vw.setHeight((int)(Double.parseDouble(_h_jml)));
views.get("lbl_nama").vw.setHeight((int)(Double.parseDouble(_h_jml)));
views.get("label4").vw.setTop((int)((views.get("label3").vw.getTop() + views.get("label3").vw.getHeight())));
views.get("lbl_npm").vw.setTop((int)((views.get("lbl_nama").vw.getTop() + views.get("lbl_nama").vw.getHeight())));
views.get("label4").vw.setHeight((int)((views.get("label3").vw.getHeight())));
views.get("lbl_npm").vw.setHeight((int)((views.get("lbl_nama").vw.getHeight())));
views.get("label5").vw.setTop((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())));
views.get("lbl_status").vw.setTop((int)((views.get("lbl_npm").vw.getTop() + views.get("lbl_npm").vw.getHeight())));
views.get("label5").vw.setHeight((int)((views.get("label4").vw.getHeight())));
views.get("lbl_status").vw.setHeight((int)((views.get("lbl_npm").vw.getHeight())));
views.get("label6").vw.setTop((int)((views.get("label5").vw.getTop() + views.get("label5").vw.getHeight())));
views.get("lbl_jurusan").vw.setTop((int)((views.get("lbl_status").vw.getTop() + views.get("lbl_status").vw.getHeight())));
views.get("lbl_jurusan").vw.setHeight((int)((views.get("lbl_nama").vw.getHeight())));
views.get("label6").vw.setHeight((int)((views.get("label5").vw.getHeight())));

}
}