package b4a.AbsenCoba.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_laylogin{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setHeight((int)((400d * scale)));
views.get("label1").vw.setTop((int)((40d * scale)));
views.get("imageview1").vw.setLeft((int)((50d / 100 * width)/2d+(60d * scale) - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview1").vw.setTop((int)((50d / 100 * height) - (views.get("imageview1").vw.getHeight() / 2)));
views.get("imageview1").vw.setTop((int)((views.get("label3").vw.getTop() + views.get("label3").vw.getHeight())+(60d * scale)));
views.get("imageview1").vw.setHeight((int)((210d * scale)));
views.get("imageview1").vw.setWidth((int)((250d * scale)));

}
}