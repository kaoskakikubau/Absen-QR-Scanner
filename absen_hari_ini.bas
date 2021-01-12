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

	Private xui As XUI
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private lbl_kelas As Label
	Private lbl_tanggal As Label
	
	Private lbl_namaMHS_hari_ini As Label
	Private lbl_Nomor As Label
	Private CLV_NamaMHS_absen As CustomListView
	Private lbl_matkul As Label
	Private lbl_total As Label
	Private AC_SpinnerMatku As ACSpinner
	Private lblTanggal As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("absen_hari_ini")
	
	lbl_tanggal.Text = DateTime.Date(DateTime.Now)
	lbl_kelas.Text = Dosen.kelas
	lbl_matkul.Text = BarcodeScanner.matkul
	
	GetAbsenToday(lbl_tanggal.Text, lbl_kelas.Text, lbl_matkul.Text, Dosen.dosennn)

End Sub

'===================== CLV Nama - nama mahasiswa ===================='
Private Sub CreateItem(Width As Int, nomor As String, nama As String) As Panel
	Dim p As B4XView = xui.CreatePanel("")
	Dim height As Int = 55dip
	'If GetDeviceLayoutValues.ApproximateScreenSize < 4.5 Then height = 120dip
	p.SetLayoutAnimated(0, 0, 0, Width, height)
	p.LoadLayout("pnl_CLV_absen_hari_ini")
	lbl_Nomor.Text = nomor
	lbl_namaMHS_hari_ini.Text = nama
	
	Return p
End Sub
' =========================== fnish CLV nama mahasiswa ================='

Sub GetAbsenToday(tanggal As String, kelas As String, matkul As String, dsn As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetAbsenToday",Array(tanggal, kelas, matkul, dsn))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim no As Int = 0
		For Each row() As Object In res.Rows
			no = no + 1
			CLV_NamaMHS_absen.Add(CreateItem(CLV_NamaMHS_absen.AsView.Width, no , row(0)),"")
'			ChangeImage(row(0))	
		Next
		lbl_total.Text = no
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
