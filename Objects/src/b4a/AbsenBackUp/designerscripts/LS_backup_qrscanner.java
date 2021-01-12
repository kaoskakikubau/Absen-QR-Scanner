package b4a.AbsenBackUp.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_backup_qrscanner{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[BackUp_QrScanner/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="QRCodeReaderView1.HorizontalCenter = 50%x"[BackUp_QrScanner/General script]
views.get("qrcodereaderview1").vw.setLeft((int)((50d / 100 * width) - (views.get("qrcodereaderview1").vw.getWidth() / 2)));
//BA.debugLineNum = 4;BA.debugLine="btn_startScan.HorizontalCenter = 50%x"[BackUp_QrScanner/General script]
views.get("btn_startscan").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_startscan").vw.getWidth() / 2)));

}
}