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

	Private img_barcode As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("lay_BarcodeMHS")
	GetQR(Mahasiswa.npm, Mahasiswa.nama, Mahasiswa.kelas)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub GetQR(npm As String, nama As String, kelas As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetQR",Array(npm, nama, kelas))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			Dim gambar As Bitmap = req.BytesToImage(row(0))
			img_barcode.Bitmap = gambar
		Next
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub

Sub ImageViewBarcode_Click
	StartActivity(Mahasiswa)
End Sub