package b4a.AbsenBackUp.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pnl_clv_kalkulasiabsen{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnl_clv").vw.setHeight((int)((75d * scale)));
views.get("pnl_foto").vw.setTop((int)((6d * scale)));
views.get("pnl_foto").vw.setLeft((int)((40d * scale) - (views.get("pnl_foto").vw.getWidth())));
views.get("pnl_foto").vw.setHeight((int)((60d * scale)));
views.get("pnl_foto").vw.setWidth((int)((60d * scale)));
views.get("lbltitle").vw.setHeight((int)((30d * scale)));
views.get("lbltitle").vw.setTop((int)((10d * scale)));
views.get("lbltitle").vw.setLeft((int)((views.get("pnl_foto").vw.getLeft() + views.get("pnl_foto").vw.getWidth())+(10d * scale)));
views.get("lblcontent").vw.setHeight((int)((30d * scale)));
views.get("lblcontent").vw.setTop((int)((views.get("pnl_clv").vw.getHeight())/2d - (views.get("lblcontent").vw.getHeight() / 2)));
views.get("lblcontent").vw.setLeft((int)((views.get("pnl_foto").vw.getLeft() + views.get("pnl_foto").vw.getWidth())+(10d * scale)));

}
}