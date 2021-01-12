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
	Dim hitung As Int
	Dim profil As String
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	'CLV
	Private CLV_RekapAbsen As CustomListView
	Private pnl_CLV As Panel
	Private lbl_JmlMasuk As Label
	Private lblTitle As Label
	Private lblContent As B4XView
	Private EditText1 As EditText
	Private img_foto As ImageView
	
	Dim img As B4XBitmap
	Dim xIV As B4XView
	Private pnl_foto As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_rekapAbsen")
	CountAbsen(Mahasiswa.npm)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'====================================== LAYOUT REKAP ABSEN ======================================='

Private Sub CreateItem(Width As Int, Title As String,Image As Bitmap, Content As String, lbls As String) As Panel
	Dim p As B4XView = xui.CreatePanel("")
	Dim height As Int = 80dip
	'If GetDeviceLayoutValues.ApproximateScreenSize < 4.5 Then height = 120dip
	p.SetLayoutAnimated(0, 0, 0, Width, height)
	p.LoadLayout("pnl_CLVRekapAbsen")
	lbl_JmlMasuk.Text = lbls
	lblTitle.Text = Title
	lblContent.Text = Content
	img = Image
	xIV = img_foto
	xIV.SetBitmap(CreateRoundBitmap(img, xIV.Width))
'	CircularImg(pnl_foto,Image,Colors.RGB(88,57,150),1dip) 
	Return p
End Sub

Sub CircularImg(Target As Panel, Images As Bitmap, Color As Int, bwidth As Int) 
	Dim civ As CircularImageView
	Dim imgs As BitmapDrawable
	civ.Initialize("")
	civ.Color = Colors.Transparent
	civ.BorderWidth = bwidth
	civ.BorderColor = Colors.Black
	civ.AddShadowLayer(4,4,4,Colors.Transparent)
	imgs.Initialize(Images)
	civ.SetDrawable(imgs)
	Target.AddView(civ,0,0,Target.Width,Target.Height)
End Sub
'====================================== SELESAI LAYOUT REKAP ABSEN =============================='

Sub ImageView1_Click
	StartActivity(Mahasiswa)
End Sub

Sub CountAbsen(npmss As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("CountAbsen",Array(npmss))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim title As Int
		CLV_RekapAbsen.clear
		For Each row() As Object In res.Rows
			title = title + 1
'			Dim Blob() As Byte
'			Blob = row(3)
'			Dim lbl() As String = Array As String("11","10","8","9")
'			Dim title() As String = Array As String("Arbi Pramana","Siti Nurhaliza","Pardede","Guntur")
'			Dim content() As String =  Array As String ("Sistem Informasi","Riset Operasional","Matematika Informatika 4","Kimia")
			CLV_RekapAbsen.Add(CreateItem(CLV_RekapAbsen.AsView.Width,row(2),req.BytesToImage(row(3)),row(1), row(4)),"")
			Log(row(3))
'			GetProfilDosen(lblTitle.Text)
		Next
'		GetProfilDosen(lblTitle.Text)
		GetMatkul(EditText1.Text,Mahasiswa.npm)
	End If
End Sub

Sub GetProfilDosen(namas As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetProfilDosen",Array(namas))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			
			Dim oBitmap As Bitmap
			Dim buffer() As Byte
			
			buffer = row(0)
			oBitmap = req.BytesToImage(buffer)
			img_foto.Bitmap = oBitmap
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

Sub GetMatkul(matkuls As String, npmss As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetMatkul",Array("%"&matkuls&"%",npmss))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim title As Int
		CLV_RekapAbsen.Clear
		For Each row() As Object In res.Rows
			title = title + 1
'			Dim Blob() As Byte
'			Blob = row(3)
'			Dim lbl() As String = Array As String("11","10","8","9")
'			Dim title() As String = Array As String("Arbi Pramana","Siti Nurhaliza","Pardede","Guntur")
'			Dim content() As String =  Array As String ("Sistem Informasi","Riset Operasional","Matematika Informatika 4","Kimia")
			CLV_RekapAbsen.Add(CreateItem(CLV_RekapAbsen.AsView.Width,row(2),req.BytesToImage(row(3)),row(1), row(4)),"")
'			GetProfilDosen(lblTitle.Text)
		Next
'		GetProfilDosen(lblTitle.Text)
	End If
End Sub

Sub EditText1_EnterPressed
'	GetProfilDosen(lblTitle.Text)
	GetMatkul(EditText1.Text,Mahasiswa.npm)
	Log(EditText1.Text)
End Sub