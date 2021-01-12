B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.801
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private xui As XUI
	Dim kls As String
	Dim mtkl As String
	Dim coba As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private lblTitle As Label
	Private lbl_JmlMasuk As Label
	Private lblContent As Label
	Private pnl_foto As Panel
	Private AC_SpinnerKelas As ACSpinner
	Private AC_SpinnerMatkul As ACSpinner
	Private btn_proses_Kalkulasi As Button
	Private CLV_NamaMHS_ As CustomListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_KalkulasiAbsen")

	GetMatkul
	
	GetDosen
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub CLV_NamaMHS__ItemClick (Index As Int, Value As Object)
	ProfilKalkulasi.nama = Value
	StartActivity(ProfilKalkulasi)
End Sub

'====================================== LAYOUT REKAP ABSEN ======================================='

Private Sub CreateItem(Width As Int, Image As Bitmap, Content As String, lbls As String) As Panel
	Dim p As B4XView = xui.CreatePanel("")
	Dim height As Int = 80dip
	'If GetDeviceLayoutValues.ApproximateScreenSize < 4.5 Then height = 120dip
	p.SetLayoutAnimated(0, 0, 0, Width, height)
	p.LoadLayout("pnl_CLV_KalkulasiAbsen")
	lbl_JmlMasuk.Text = lbls
'	lblTitle.Text = Title
	lblContent.Text = Content
	CircularImg(pnl_foto,Image,Colors.RGB(88,57,150),1dip)
	Return p
End Sub

Sub CircularImg(Target As Panel, Images As Bitmap, Color As Int, bwidth As Int)
	Dim civ As CircularImageView
	Dim img As BitmapDrawable
	civ.Initialize("")
	civ.Color = Colors.Transparent
	civ.BorderWidth = bwidth
	civ.BorderColor = Colors.White
	civ.AddShadowLayer(4,4,4,Colors.Transparent)
	img.Initialize(Images)
	civ.SetDrawable(img)
	Target.AddView(civ,0,0,Target.Width,Target.Height)
End Sub
'====================================== SELESAI LAYOUT REKAP ABSEN =============================='

Sub CountAbsenDosen(kelas As String, matkul As String, dosenn As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("CountAbsenDosen",Array(kelas, matkul, dosenn))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim title As Int
		CLV_NamaMHS_.Clear
		For Each row() As Object In res.Rows
			title = title + 1
'			Dim lbl() As String = Array As String("11","10","8","9")
'			Dim title() As String = Array As String("Arbi Pramana","Siti Nurhaliza","Pardede","Guntur")
'			Dim content() As String =  Array As String ("Sistem Informasi","Riset Operasional","Matematika Informatika 4","Kimia")
			CLV_NamaMHS_.Add(CreateItem(CLV_NamaMHS_.AsView.Width, LoadBitmap(File.DirAssets,"gunadarma.jpg"), row(1), row(2)), row(1))
		Next
	End If
End Sub

Sub GetDosen
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("SelectDosen",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			AC_SpinnerKelas.AddAll(Array As String(row(0)))
		Next
	End If
End Sub

Sub GetMatkul
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("SelectMatkul",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			AC_SpinnerMatkul.AddAll(Array As String(row(0)))
		Next
	End If
End Sub

Sub AC_SpinnerKelas_ItemClick (Position As Int, Value As Object)
	kls = Value
End Sub

Sub AC_SpinnerMatkul_ItemClick (Position As Int, Value As Object)
	mtkl = Value
End Sub

Sub btn_proses_Kalkulasi_Click
	CountAbsenDosen(kls, mtkl, Dosen.dosennn)
End Sub

