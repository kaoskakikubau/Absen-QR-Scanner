package b4a.AbsenBackUp.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_qrscanner{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("qrcodereaderview1").vw.setLeft((int)((50d / 100 * width) - (views.get("qrcodereaderview1").vw.getWidth() / 2)));
views.get("btn_startscan").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_startscan").vw.getWidth() / 2)));
views.get("pnl_kelas").vw.setTop((int)((views.get("pnl_tanggal").vw.getTop() + views.get("pnl_tanggal").vw.getHeight())));

}
}