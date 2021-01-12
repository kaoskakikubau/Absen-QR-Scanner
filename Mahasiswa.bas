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
	Dim nama As String
	Dim npm As Int
	Dim kelas As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim xui As XUI
	Dim jumlah As Int
	Private Panel3 As Panel
	Private ListView1 As ListView
	
	Private Label2 As Label
	Private lbl_nama As Label
	Private lbl_npm As Label
	
	Dim img As B4XBitmap
	Dim xIV As B4XView
	Private img_profil As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_HomeMahasiswa")
	Label2.Text = nama
	lbl_nama.Text = npm
	lbl_npm.Text = kelas
	
	getPhoto(Label2.Text, lbl_nama.Text, lbl_npm.Text)

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub getPhoto(nm As String, npms As String, kls As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("getPhoto",Array(nm, npms, kls))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			Dim gambar As Bitmap = req.BytesToImage(row(0))
			img = gambar
			xIV = img_profil
			xIV.SetBitmap(CreateRoundBitmap(img, xIV.Width))
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

Sub ImageView2_Click
	StartActivity(Main)
End Sub

Sub pnl_search_Click
	StartActivity(RekapMahasiswa)
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

Sub Panel_barcode_Click
	StartActivity(BarcodeMahasiswa)
End Sub