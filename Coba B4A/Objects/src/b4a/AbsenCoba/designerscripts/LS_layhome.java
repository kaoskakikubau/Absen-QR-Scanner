package b4a.AbsenCoba.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layhome{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("btn_fingerprint").vw.setTop((int)((35d * scale)));
views.get("btn_fingerprint").vw.setLeft((int)((59d / 100 * width) - (views.get("btn_fingerprint").vw.getWidth() / 2)));
views.get("btn_fingerprint").vw.setWidth((int)((75d * scale)));
views.get("btn_fingerprint").vw.setHeight((int)((75d * scale)));
views.get("label1").vw.setTop((int)((views.get("btn_fingerprint").vw.getTop() + views.get("btn_fingerprint").vw.getHeight())+(25d * scale)));
views.get("label1").vw.setLeft((int)((50d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));
views.get("label2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())-(20d * scale)));
views.get("label2").vw.setLeft((int)((50d / 100 * width) - (views.get("label2").vw.getWidth() / 2)));
views.get("btn_skip").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())+(10d * scale)));
views.get("btn_skip").vw.setLeft((int)((views.get("panel1").vw.getLeft() + views.get("panel1").vw.getWidth())-(100d * scale)));
views.get("image").vw.setLeft((int)((50d / 100 * width) - (views.get("image").vw.getWidth() / 2)));
views.get("image").vw.setHeight((int)((200d * scale)));
views.get("image").vw.setTop((int)((50d / 100 * height)-(100d * scale) - (views.get("image").vw.getHeight() / 2)));
views.get("image").vw.setWidth((int)((300d * scale)));
views.get("image").vw.setLeft((int)((50d / 100 * width) - (views.get("image").vw.getWidth() / 2)));
views.get("label3").vw.setLeft((int)((50d / 100 * width) - (views.get("label3").vw.getWidth() / 2)));
views.get("label3").vw.setTop((int)((views.get("image").vw.getTop())-(50d * scale) - (views.get("label3").vw.getHeight())));

}
}