B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.801
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private frontCamera As Boolean = False
	Dim matkul As String
	Dim val As String
	Dim nm As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private QRCodeReaderView1 As QRCodeReaderView
	Private btn_startScan As Panel
	Private camEx As CameraExClass
	Private Panel2 As Panel
	Dim lbl As Label
	Private lblGetMHS As Label
	
	
	Private lblNama_mhs As Label
	Private lblNpm_mhs As Label
	Private lblKelas_mhs As Label
	Private btn_absen As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("QrScanner")
	lbl.Initialize("")
	
	
End Sub

Sub Activity_Resume
	Log(QRCodeReaderView1.FrontFacingCamera)
	Log(QRCodeReaderView1.BackFacingCamera)
	QRCodeReaderView1.CameraId = QRCodeReaderView1.CAMERA_BACK
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	QRCodeReaderView1.closeDriver
End Sub

Sub btn_startScan_Click
	Log(btn_startScan)
	QRCodeReaderView1.startScan
End Sub

Sub Camera1_Ready (Success As Boolean)
	If Success Then
		camEx.SetJpegQuality(90)
		camEx.CommitParameters
		camEx.StartPreview
	Else
		ToastMessageShow("Cannot open camera.", True)
	End If
End Sub

Sub QRCodeReaderView1_result_found(retval As String)
	GetAbsen(retval)
	GetNama(retval)
	GetKelas(retval)
	val = retval
	QRCodeReaderView1.stopScan
	Msgbox(val,nm)
	lblNpm_mhs.Text = retval
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		QRCodeReaderView1.closeDriver
		QRCodeReaderView1.stopScan
		StartActivity(Dosen)
		Activity.finish
	End If
End Sub

Sub CheckAbsen(tgl As String, npmss As String, matkulss As String, nama As String, dosenn As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("CekAbsen",Array(tgl,npmss,matkulss, nama, dosenn))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		If res.Rows.Size > 0 Then
			ToastMessageShow("Maaf Anda Sudah Absen",False)
		Else
			InsertAbsen(DateTime.Date(DateTime.Now),lblNpm_mhs.Text,matkul,"Masuk", lblNama_mhs.Text, lblKelas_mhs.Text, Dosen.dosennn, Dosen.bufferI)
		End If
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub


Sub GetAbsen(npmss As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("SelectAbsen",Array(npmss))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			lblNpm_mhs.Text = row(0)
		Next
	End If
	j.Release
End Sub


Sub InsertAbsen(tgl As String, npmss As String, matkuls As String, statuss As String, nama As String, kelas As String, dsn As String, profil() As Byte)
	Dim cmd As DBCommand = util.CreateCommand("InsertAbsen",Array(tgl,npmss,matkuls,statuss, nama, kelas, dsn, profil))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		ToastMessageShow("Terima Kasih Sudah Absen", False)
		StartActivity(Dosen)
		Activity.Finish
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub


Sub GetNama(npmsss As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetNama",Array(npmsss))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			lblNama_mhs.Text = row(0)
		Next
		
	End If
	j.Release
End Sub

Sub GetKelas(npmssss As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetKelas",Array(npmssss))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			lblKelas_mhs.Text = row(0)
		Next
		
	End If
	j.Release
End Sub


Sub btn_absen_Click
	If lblKelas_mhs.Text == Dosen.kelas Then
		CheckAbsen(DateTime.Date(DateTime.Now), lblNpm_mhs.Text, matkul, lblNama_mhs.Text, Dosen.dosennn)
	Else 
		ToastMessageShow("Maaf Nama Mahasiswa yang anda masukkan tidak tertera pada kelas " & Dosen.kelas &CRLF&"Melainkan pada kelas " & lblKelas_mhs.Text, False)
	End If
'	CheckAbsen(DateTime.Date(DateTime.Now), lblNpm_mhs.Text, matkul, lblNama_mhs.Text, Dosen.dosennn)
End Sub