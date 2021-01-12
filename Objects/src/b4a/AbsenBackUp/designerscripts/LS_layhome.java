package b4a.AbsenBackUp.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layhome{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("imageview1").vw.setWidth((int)((views.get("panel2").vw.getWidth())/2d));
views.get("imageview1").vw.setHeight((int)((views.get("panel3").vw.getHeight())/2d));
views.get("imageview1").vw.setLeft((int)((views.get("panel3").vw.getWidth())/2d - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview1").vw.setTop((int)((views.get("panel3").vw.getHeight())/2d - (views.get("imageview1").vw.getHeight() / 2)));
views.get("btn_fingerprint").vw.setHeight((int)((views.get("panel1").vw.getHeight())/4d));
views.get("btn_fingerprint").vw.setWidth((int)((views.get("label1").vw.getWidth())/3d));
views.get("btn_fingerprint").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_fingerprint").vw.getWidth() / 2)));

}
}