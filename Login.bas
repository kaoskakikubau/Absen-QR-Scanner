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
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private btn_skip As Label
	Private EditText1 As EditText
	Private btn_fingerprint As Button
	
	Dim x As XmlLayoutBuilder
	Dim s As Animation
	
	
	Private Button1 As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("layLogin")
	
	s = x.LoadAnimation("atg","Atg1")

End Sub

Sub Activity_Resume
	s.Start(Button1)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Button1_Click
	If EditText1.Text.Length <= 8  Then
		LoginTest(EditText1.Text)
	Else If EditText1.Text.Length >= 9 Then
		LoginDosen(EditText1.Text)
	Else
		ToastMessageShow("nulis apaan lo",False)
	End If
End Sub

Sub LoginTest(npms As Int)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("Login",Array(npms))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
		If j.Success Then
			req.HandleJobAsync(j,"req")
			Wait For(req) req_Result(res As DBResult)
			For Each row() As Object In res.Rows
				Mahasiswa.nama = row(1)
				Mahasiswa.npm = row(0)
				Mahasiswa.kelas = row(2)
			Next
			StartActivity(Mahasiswa)
		Else
			Log(j.ErrorMessage)
		End If
	j.Release
End Sub

Sub LoginDosen(nips As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetDosen",Array(nips))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
		If j.Success Then
			req.HandleJobAsync(j,"req")
			Wait For(req) req_Result(res As DBResult)
			If res.Rows.Size > 0 Then
				For Each row() As Object In res.Rows
					Dosen.dosennn = row(0)
				Next
			End If
			StartActivity(Dosen)
			Activity.Finish
		Else
			Log(j.ErrorMessage)
		End If
	j.Release
End Sub
