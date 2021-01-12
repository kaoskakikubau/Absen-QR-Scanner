package b4a.AbsenCoba.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_lay_homemahasiswa{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setTop((int)((200d * scale) - (views.get("panel1").vw.getHeight())));
views.get("panel2").vw.setTop((int)((125d * scale)));
views.get("panel2").vw.setLeft((int)((50d / 100 * width)/1.15d - (views.get("panel2").vw.getWidth() / 2)));
views.get("lbl_aktifmhs").vw.setTop((int)((98d * scale)));
views.get("lbl_aktifmhs").vw.setLeft((int)((views.get("panel2").vw.getLeft() + views.get("panel2").vw.getWidth())+(10d * scale)));
views.get("label1").vw.setTop((int)((70d * scale)));
views.get("label2").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())));
views.get("label2").vw.setTop((int)(+(70d * scale)));
views.get("label1").vw.setLeft((int)((35d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));
views.get("label2").vw.setLeft((int)((65d / 100 * width) - (views.get("label2").vw.getWidth() / 2)));
views.get("imageview1").vw.setLeft((int)((50d / 100 * width)/1.4d - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview7").vw.setLeft((int)((50d / 100 * width)/2.1d - (views.get("imageview7").vw.getWidth() / 2)));
views.get("pnl_profilmhs").vw.setLeft((int)((50d / 100 * width) - (views.get("pnl_profilmhs").vw.getWidth() / 2)));

}
}