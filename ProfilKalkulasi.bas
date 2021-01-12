B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.801
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim nama As String
	Private xui As XUI
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private lblNama_mhs As Label
	Private lblNpm_mhs As Label
	Private lblKelas_mhs As Label
	Private lbl_Nomor As Label
	Private lbl_namaMHS_Kalkulasi As Label
	Private CLV_NamaMHS_ As CustomListView
	
	Dim img As B4XBitmap
	Dim xIV As B4XView
	Private img_profil As ImageView
	
	Private lbl_total As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_ProfilKalkulasi")
	GetProfile(nama)
	Log(nama)
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub GetProfile(namas As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("getProfil",Array(namas))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			Dim nm As String
			lblNama_mhs.Text = row(1)
			lblNpm_mhs.Text = row(0)
			lblKelas_mhs.Text = row(2)
			
			Dim gambar As Bitmap = req.BytesToImage(row(3))
			img = gambar
			xIV = img_profil
			xIV.SetBitmap(CreateRoundBitmap(img, xIV.Width))
		Next
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
	GetAbsenToday(lblNama_mhs.Text, lblKelas_mhs.Text, lblNpm_mhs.Text, KalkulasiAbsen.mtkl, Dosen.dosennn)
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

'===================== CLV Nama - nama mahasiswa ===================='
Private Sub CreateItem(Width As Int, nomor As String, namas As String) As Panel
	Dim p As B4XView = xui.CreatePanel("")
	Dim height As Int = 55dip
	'If GetDeviceLayoutValues.ApproximateScreenSize < 4.5 Then height = 120dip
	p.SetLayoutAnimated(0, 0, 0, Width, height)
	p.LoadLayout("pnl_CLV_ProfilKalkulasi")
	lbl_Nomor.Text = nomor
	lbl_namaMHS_Kalkulasi.Text = namas
	
	Return p
End Sub
' =========================== fnish CLV nama mahasiswa ================='

Sub GetAbsenToday(nm As String, kelas  As String, npm  As String, mtkul As String, dosenn)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("getDateProfil",Array(nm, kelas, npm, mtkul, dosenn))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim no As Int = 0
		CLV_NamaMHS_.Clear
		For Each row() As Object In res.Rows
			no = no + 1
			CLV_NamaMHS_.Add(CreateItem(CLV_NamaMHS_.AsView.Width, no , row(0)),"")
'			ChangeImage(row(0))	
		Next
		lbl_total.Text = no
	End If
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	Dim i As Int
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		StartActivity(KalkulasiAbsen)
		Activity.Finish
	End If
End Sub