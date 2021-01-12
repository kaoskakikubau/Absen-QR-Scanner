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
	Dim kelas As String
	Dim mtkl As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private AC_SpinnerKelas As ACSpinner
	Private AC_SpinnerMatku As ACSpinner
	Private lblTanggal As Label
	Private Button1 As Button
	
	Private satOption, sunOption As Int
	Private startMonday As Boolean
	
	Private lbl_Nomor As Label
	Private lbl_namaMHS_rekap As Label
	Private pnl_CLVAbsenDosen As Panel
	Private CLV_NamaMHS As CustomListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_RekapAbsenDosen")
	
	GetDosen
	
	GetMatkul

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub AC_SpinnerKelas_ItemClick (Position As Int, Value As Object)
	kelas = Value
End Sub


Sub AC_SpinnerMatku_ItemClick (Position As Int, Value As Object)
	mtkl = Value
End Sub

'===================== CLV Nama - nama mahasiswa ===================='
Private Sub CreateItem(Width As Int, nomor As String, nama As String) As Panel
	Dim p As B4XView = xui.CreatePanel("")
	Dim height As Int = 55dip
	'If GetDeviceLayoutValues.ApproximateScreenSize < 4.5 Then height = 120dip
	p.SetLayoutAnimated(0, 0, 0, Width, height)
	p.LoadLayout("pnl_CLV_RekapAbsenDosen")
	lbl_Nomor.Text = nomor
	lbl_namaMHS_rekap.Text = nama
	
	Return p
End Sub
' =========================== fnish CLV nama mahasiswa ================='

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
			AC_SpinnerMatku.AddAll(Array As String(row(0)))
		Next
	End If
End Sub

Sub lblTanggal_Click
	Dim I As Long
	Dim D As DateDialogs
	Dim s As String
	Dim a As String
	
	Log(DateTime.DateFormat)
	D.Initialize(Activity, DateTime.Now)
	D.RedSaturday=satOption
	D.RedSunday=sunOption
	D.StartOnMonday=startMonday
	I=D.Show("Select Date")
'	Log(DateTime.DateFormat)
	a = DateTime.GetMonth(D.DateSelected) & "/" & DateTime.GetDayOfMonth(D.DateSelected) & "/" & DateTime.GetYear(D.DateSelected)
	
	DateTime.DateFormat = "MM/dd/yyyy"
	Dim t As Long
	t = DateTime.DateParse(a)
	DateTime.DateFormat = "MM/dd/yyyy"
	Dim s As String
	lblTanggal.Text = DateTime.Date(t)
End Sub

Sub GetAbsenDate(kls As String, matkul As String, tanggal As String, dosennn As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetAbsenDate",Array(kls, matkul, tanggal, dosennn))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		Dim no As Int = 0
		For Each row() As Object In res.Rows
			no = no + 1
			CLV_NamaMHS.Add(CreateItem(CLV_NamaMHS.AsView.Width, no , row(0)),"")
'			ChangeImage(row(0))	
		Next
	End If
End Sub


Sub Button1_Click
	CLV_NamaMHS.Clear
	GetAbsenDate(kelas, mtkl,lblTanggal.Text, Dosen.dosennn)
End Sub

