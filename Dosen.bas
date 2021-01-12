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
	Dim dosennn As String
	Dim kelas As String
	Dim bufferI () As Byte
	Dim matkul as String
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
	Private lbl_TanggalDosen As Label
	Private lbl_namadosen As Label
	Private btn_selesaipengambilan As Button
	Private btn_rekap_absen As Button
	Private btn_kalkulasi As Button
	Private pnl_kalkulasi As Panel
	Private pnl_rekap_absen As Panel
	Private pnl_absen_harini As Panel
	
	Dim img As B4XBitmap
	Dim xIV As B4XView
	Private img_profil As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_AbsenDosen")
		
	lbl_namadosen.Text = dosennn
	
	
	GetDosen
	'AC_SpinnerKelas.AddAll(Array As String("3IA18","3IA19","3IA20","3IA21","3IA22","3IA23"))
	GetMatkul
'	AC_SpinnerMatkul.AddAll(Array As String("Matematika Informatika 4","Kimia","Riset operasional","Sistem Informasi")
	'CLV
	'Dim nama() As String = Array As String("Noer Rachmat","Rafi Mochamad","Bayu Setiadi","Alief Aziez","Servatius Adhi")
	
'	For n = 1 To 5
'		CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.Width, (n) , nama(n-1)),"")
'	Next
	lbl_TanggalDosen.Text = DateTime.Date(DateTime.Now)
	
	GetProfil(lbl_namadosen.Text)
	
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
	CLV_NamaMHS.Clear
	GetMHS(Value)
	kelas = Value
End Sub

Sub AC_SpinnerMatkul_ItemClick (Position As Int, Value As Object)
	BarcodeScanner.matkul = Value
	matkul = Value
End Sub

Sub ACSpinnerKet_ItemClick (Position As Int, Value As Object)
	Log("Spinner Item selected: " & Position & " - " & Value)
End Sub
'
'Sub btn_scan_Click
'	myABBarcode.ABGetBarcode("myabbarcode", "")
'End Sub

Sub Panel1_Click
	StartActivity(BarcodeScanner)
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

Sub GetMHS(kelass As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("SelectMHS",Array(kelass))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim no As Int = 0
		For Each row() As Object In res.Rows
			no = no + 1
			CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.Width, no , row(1)),"")
'			ChangeImage(row(0))	
		Next
	End If
End Sub

Sub ChangeImage(npm As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("ChangeImage",Array(npm))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	Dim date_now As String = DateTime.Date(DateTime.Now)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim i As Byte= 0
		For Each row() As Object In res.Rows
			Dim ket As String = row(4)
			Dim kers As String = row(2)
			If lbl_TanggalDosen.Text = date_now And ket.ToLowerCase = "masuk" Then
				ACSpinnerKet.Add("Oke")
			Else if lbl_TanggalDosen.Text = date_now And Not(ket.ToLowerCase = "masuk") Then
				For i = 1 To 4
					If i = 1 Then
						ACSpinnerKet.Add2("",toDrawable(LoadBitmap(File.DirAssets,"Group 1.png")))
					Else If i = 2 Then
						ACSpinnerKet.Add2("Absen",toDrawable(LoadBitmap(File.DirAssets,"absen.png")))
					Else If i = 3 Then
						ACSpinnerKet.Add2("Izin",toDrawable(LoadBitmap(File.DirAssets,"izin (2).png")))
					Else If i = 4 Then
						ACSpinnerKet.Add2("Sakit",toDrawable(LoadBitmap(File.DirAssets,"sakit.png")))
					End If
				Next
				Log("bukan")
			End If
		Next
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub

Sub CreateRequest As DBRequestManager
	Dim req As DBRequestManager
	req.Initialize(Me, Main.exportrdcLink)
	Return req
End Sub

Sub GetProfil(namas As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("getPhotoDosen",Array(namas))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			Log(row(0))
			
			Dim gambar As Bitmap 
			Dim buffer() As Byte
			
			buffer = row(0)
			gambar = req.BytesToImage(buffer)
			img = gambar
			xIV = img_profil
			xIV.SetBitmap(CreateRoundBitmap(img, xIV.Width))
			
			bufferI = CreateRequest.ImageToBytes(img_profil.Bitmap)
		Next
		
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub


Sub CreateRoundBitmap (Input As B4XBitmap, Size As Int) As B4XBitmap
	If Input.Width <> Input.Height Then
		'if the image is not square then we crop it to be a square.
		Dim l As Int = Min(Input.Width, Input.Height)
		Input = Input.Crop(Input.Width / 2 - l / 2, Input.Height / 2 - l / 2, l, l)
	End If
	Dim c As B4XCanvas
	Dim xview As B4XView = xui.CreatePanel("")
	xview.SetLayoutAnimated(0, 0, 0, Size, Size)
	c.Initialize(xview)
	Dim path As B4XPath
	path.InitializeOval(c.TargetRect)
	c.ClipPath(path)
	c.DrawBitmap(Input.Resize(Size, Size, False), c.TargetRect)
	c.RemoveClip
'	c.DrawCircle(c.TargetRect.CenterX, c.TargetRect.CenterY, c.TargetRect.Width / 2 - 2dip, xui.Color_Red, False, 5dip) 'comment this line to remove the border
	c.Invalidate
	Dim res As B4XBitmap = c.CreateBitmap
	c.Release
	Return res
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	Dim i As Int
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		i = Msgbox2("Yakin Ingin keluar?", "Info", "Ok", "Cancel"," ",Null)
		Select i
			Case DialogResponse.POSITIVE
				Activity.Finish
				StartActivity(Main)
			Case DialogResponse.CANCEL
				Return True
		End Select
	End If
End Sub

Sub pnl_absen_harini_Click
	StartActivity(absen_hari_ini)
End Sub

Sub pnl_rekap_absen_Click
	StartActivity(Rekap_Absen_Dosen)
End Sub

Sub pnl_kalkulasi_Click
	StartActivity(KalkulasiAbsen)
End Sub

