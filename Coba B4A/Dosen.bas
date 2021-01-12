B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private xui As XUI
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	'ACSpinner
	Private AC_SpinnerKelas As ACSpinner
	Private AC_SpinnerMatkul As ACSpinner
	Private ACSpinnerKet As ACSpinner
	
	'CLVNamaMHS
	Private CLV_NamaMHS As CustomListView
	Private pnl_CLVAbsenDosen As Panel
	Private lbl_namaMHS As Label
	Private lbl_Nomor As Label
	Private img_KetMHS As ImageView
	
	Dim pic() As String
	Dim count As Int
	
'	'Scanner
'	Dim Button1 As Button
'	Dim myABBarcode As ABZxing
'	Dim Label1 As Label
'	Dim mResult As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_AbsenDosen")
	
	If FirstTime Then
		ACSpinnerKet.Initialize("InitializeSpinner")
	End If
	
	AC_SpinnerKelas.AddAll(Array As String("3IA18","3IA19","3IA20","3IA21","3IA22","3IA23"))
	AC_SpinnerMatkul.AddAll(Array As String("Matematika Informatika 4","Kimia","Riset operasional","Sistem Informasi"))
	
	
	'CLV
	Dim nama() As String = Array As String("Noer Rachmat","Rafi Mochamad","Bayu Setiadi","Alief Aziez","Servatius Adhi")
	
	
	For n = 1 To 5
		CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.Width, (n) , nama(n-1)),"")
	Next
	
	
End Sub


'===================== CLV Nama - nama mahasiswa ===================='
Private Sub CreateItem(Width As Int, nomor As String, nama As String) As Panel
	Dim p As B4XView = xui.CreatePanel("")
	Dim height As Int = 55dip
	'If GetDeviceLayoutValues.ApproximateScreenSize < 4.5 Then height = 120dip
	p.SetLayoutAnimated(0, 0, 0, Width, height)
	p.LoadLayout("pnl_CLVAbsenDosen")
	lbl_Nomor.Text = nomor
	lbl_namaMHS.Text = nama
	For i = 1 To 4
		If i = 1 Then
			ACSpinnerKet.Add2("",Null)'toDrawable(LoadBitmap(File.DirAssets,"Group 1.png")))
		Else If i = 2 Then
			ACSpinnerKet.Add2("Absen",toDrawable(LoadBitmap(File.DirAssets,"absen.png")))
		Else If i = 3 Then
			ACSpinnerKet.Add2("Izin",toDrawable(LoadBitmap(File.DirAssets,"izin (2).png")))
		Else If i = 4 Then
			ACSpinnerKet.Add2("Sakit",toDrawable(LoadBitmap(File.DirAssets,"sakit.png")))
		End If
	Next
	Return p
End Sub
' =========================== fnish CLV nama mahasiswa ================='

'======================== Spinner keterangan izin, absen, dll ================'
Sub toDrawable (bmp As Bitmap) As BitmapDrawable
	Dim b As BitmapDrawable
	b.Initialize(bmp)
	Return b
End Sub
'========================== selesai spinner keterangan ========================'
Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub AC_SpinnerKelas_ItemClick (Position As Int, Value As Object)
	Log("Spinner Item selected: " & Position & " - " & Value)
End Sub

Sub ACSpinnerKet_ItemClick (Position As Int, Value As Object)
	Log("Spinner Item selected: " & Position & " - " & Value)
End Sub

Sub Panel1_Click
	
End Sub