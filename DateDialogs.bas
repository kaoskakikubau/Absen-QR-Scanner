B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=7.3
@EndOfDesignText@
Sub Class_Globals
	Public DateSelected As Long
	
	Private Colore As Int = Colors.RGB(0,150,136)
	Private ColorLabelNow As Int
	Private MyAct As Activity
	Private DateRet As Long = 0
	Private Ret As Int = 0

	Private Pa As Panel
	Private Cale As Panel
	Private Testa As Panel
	Private ButtonOk As Button
	Private ButtoCancel As Button
	Private LabelNow As Label
	Private LDay As Label
	Private LYear As Label
	Private LMounth As Label
	
	Private listDays As List
	Private listMonths As List
	
	Private DayShift As Int=0
	Private SundayHdrRed As Boolean=True
	Private SundayDaysRed As Boolean=False
	Private SaturdayHdrRed As Boolean=False
	Private SaturdayDaysRed As Boolean=False
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(Myactivity As Activity, Date As Long)
	MyAct=Myactivity
	DateSelected=Date
	DateRet=Date
	InitGiorno
	InitMese
End Sub

'True=Week starts on Monday
'False=Week starts on Sunday
Public Sub setStartOnMonday(StartOnMonday As Boolean)
	If StartOnMonday Then DayShift=1
End Sub

'Set to 0 for Sunday days and header in black
'Set to 1 to turn red only the header of Sundays 
'Set to 2 to turn red header and days for Sundays 
Public Sub setRedSunday(IsRed As Int)
	Select Case IsRed
		Case 0
			SundayHdrRed=False
			SundayDaysRed=False
		Case 1
			SundayHdrRed=True
			SundayDaysRed=False
		Case 2
			SundayHdrRed=True
			SundayDaysRed=True
	End Select
End Sub

'Set to 0 for Saturday days and header in black
'Set to 1 to turn red only the header of Saturdays 
'Set to 2 to turn red header and days for Saturdays
Public Sub setRedSaturday(IsRed As Int)
	Select Case IsRed
		Case 0
			SaturdayHdrRed=False
			SaturdayDaysRed=False
		Case 1
			SaturdayHdrRed=True
			SaturdayDaysRed=False
		Case 2
			SaturdayHdrRed=True
			SaturdayDaysRed=True
	End Select
End Sub
#Region Appoggio

private Sub Corner(Color As Int) As ColorDrawable
	Dim cdb As ColorDrawable
	cdb.Initialize(Color, 20dip)
	Return cdb
End Sub

Private Sub InitGiorno()
	Select Lingua
		Case "it"
			listDays = Array As String ("D","L","M","M","G","V","S","D")
		Case "fr"
			listDays = Array As String ("D","L","M","M","J","V","S","D")
		Case "ru"
			listDays = Array As String ("В","П","В","С","Ч","П","С","B")
		Case "es"
			listDays = Array As String ("D","L","M","M","J","V","S","D")
		Case "de"
			listDays = Array As String ("S","M","D","M","D","F","S","S")
		Case "pt"
			listDays = Array As String ("D","S","T","Q","Q","S","S","D")
		Case "ko"
			listDays = Array As String ("일","월","화","수","목","금","토","일")
		Case Else
			listDays = Array As String ("S","M","T","W","T","F","S","S")
	End Select
End Sub

Private Sub InitMese()
	Select Lingua
		Case "it"
			listMonths = Array As String ("Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre")
		Case "fr"
			listMonths = Array As String ("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre")
		Case "ru"
			listMonths = Array As String ("Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь")
		Case "es"
			listMonths = Array As String ("Enero","Febrero","Marzo","Abril","Mayo","Junio​​","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre")
		Case "de"
			listMonths = Array As String ("Januar","Februar","März","April","Mai","June","Juli","August","September","October","November","December")
		Case "pt"
			listMonths = Array As String ("Janeiro","fevereiro","March"," Abril","pode","June","Julho","August","September","Outubro","November","dezembro")
		Case "ko"
			listMonths = Array As String ("1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월")
		Case Else
			listMonths = Array As String ("January","February","March","April","May","June","July","August","September","October","November","December")
	End Select
End Sub

Private Sub NomeGiorno(D As Int) As String
	Return listDays.Get(D+DayShift)
End Sub

private Sub NomeMese(M As Int) As String
	Return listMonths.Get(M-1)
End Sub

Private Sub Lingua As String
	Dim r As Reflector
	Dim s As String
	r.Target = r.RunStaticMethod("java.util.Locale", "getDefault", Null, Null)
	s = r.RunMethod("getLanguage")
	Return s.SubString2(0, 2)
End Sub
#End Region

Public Sub SetDate(Date As Long)
	DateSelected=Date
	DateRet=Date
End Sub

private Sub FillCalendar
	Dim GDay,D,M,A,Y,X As Int
	Dim DateStart As Long
	Dim CurrentDateFormat As String
	
	Cale.RemoveAllViews
	'CALENDARIO
	D = DateTime.GetDayOfMonth(DateRet)
	M = DateTime.GetMonth(DateRet)
	A = DateTime.GetYear(DateRet)
	
	CurrentDateFormat = DateTime.DateFormat
	DateTime.DateFormat="MM/dd/yyy"
	DateStart=DateTime.DateParse(M & "/01/" & A)
	GDay=DateTime.GetDayOfWeek(DateStart)-DayShift
	If GDay=0 Then GDay=7
	DateTime.DateFormat = CurrentDateFormat
	
	For i=0 To 6
		Dim Lab As Label
		Lab.Initialize("")
		Lab.Text=NomeGiorno(i)
		Lab.Color=Colors.Transparent
		Lab.Textcolor=Colors.Black
		If ((i=6 And DayShift=0) Or (i=6 And DayShift=1)) Then
			If SundayHdrRed Then Lab.Textcolor=Colors.Red 
		Else If ((i=0 And DayShift=0) Or (i=5 And DayShift=1)) Then
			If SaturdayHdrRed Then Lab.Textcolor=Colors.Red
		End If
		
		Lab.Gravity=Gravity.CENTER
		Lab.Typeface=Typeface.DEFAULT_BOLD
		Lab.TextSize=14
		Cale.AddView(Lab, i*40dip, 0dip, 40dip, 40dip)
	Next
	
	Y=40dip
 	X=(GDay-1)*40dip
	For i=1 To 31
		If DateTime.GetMonth(DateStart)=m Then
			Dim Lab As Label
			
			Lab.Initialize("Giorno")
			If d=i Then
				Lab.Background=Corner(Colore)
				Lab.Textcolor=Colors.White
				LabelNow=Lab
				If (((SundayDaysRed And (DateTime.GetDayOfWeek(DateStart)=1))) Or ((SaturdayDaysRed And (DateTime.GetDayOfWeek(DateStart)=7)))) Then
					ColorLabelNow = Colors.Red
				Else
					ColorLabelNow = Colors.Black
				End If
			Else
				Lab.Color=Colors.Transparent
				If (((SundayDaysRed And (DateTime.GetDayOfWeek(DateStart)=1))) Or ((SaturdayDaysRed And (DateTime.GetDayOfWeek(DateStart)=7)))) Then
					Lab.TextColor = Colors.Red
				Else
					Lab.TextColor = Colors.Black
				End If
			End If
			Lab.Text=i
			Lab.Tag=DateStart
			Lab.Gravity=Gravity.CENTER
			Lab.TextSize=14
			Cale.AddView(Lab,X,Y,40dip,40dip)
			
			GDay=GDay+1
			If GDay>7 Then
				GDay=1
				Y=Y+35dip
				X=0dip
			Else
				X=X+40dip
			End If
		End If
		DateStart=DateTime.Add(DateStart, 0, 0, 1)
	Next
	
	'Button
	ButtonOk.Initialize("ButtonOk")
	ButtonOk.Color=Colors.Transparent
	ButtonOk.TextColor=Colore
	ButtonOk.Gravity=Gravity.CENTER
	ButtonOk.Typeface=Typeface.DEFAULT_BOLD
	ButtonOk.Text="OK"
	ButtonOk.Textsize=14
	Cale.AddView(ButtonOk, 180dip, 240dip, 80dip, 40dip)
	
	ButtoCancel.Initialize("ButtonCancel")
	ButtoCancel.Color=Colors.Transparent
	ButtoCancel.TextColor=Colore
	ButtoCancel.Gravity=Gravity.CENTER
	ButtoCancel.Typeface=Typeface.DEFAULT_BOLD
	ButtoCancel.Text="CANCEL"
	ButtoCancel.Textsize=14
	Cale.AddView(ButtoCancel,20dip,240dip,80dip,40dip)
End Sub

private Sub FillTesta(Title As String)
	'TITOLO
	Dim Lab As Label
	Lab.Initialize("")
	Lab.Gravity=Gravity.CENTER
	Lab.Text=Title
	Lab.TextSize=14
	Lab.Textcolor=Colors.White
	Lab.Color=Colors.ARGB(150,0,0,0)
	Testa.AddView(Lab,0dip,0dip,280dip,30dip)
	
	'Mese
	LMounth.Initialize("")
	LMounth.Gravity=Gravity.CENTER
	LMounth.Text=NomeMese(DateTime.GetMonth(DateRet))
	LMounth.TextSize=25
	LMounth.Textcolor=Colors.White
	LMounth.Color=Colors.Transparent
	Testa.AddView(LMounth,0dip,30dip,280dip,60dip)
	'Prev Mese
	Dim Lab As Label
	Lab.Initialize("PrevM")
	Lab.Gravity=Gravity.CENTER
	Lab.Text="<"
	Lab.TextSize=30
	Lab.Textcolor=Colors.ARGB(200,255,255,255)
	Lab.Color=Colors.Transparent
	Testa.AddView(Lab,10dip,30dip,40dip,40dip)
	'Next Mese
	Dim Lab As Label
	Lab.Initialize("NextM")
	Lab.Gravity=Gravity.CENTER
	Lab.Text=">"
	Lab.TextSize=30
	Lab.Textcolor=Colors.ARGB(200,255,255,255)
	Lab.Color=Colors.Transparent
	Testa.AddView(Lab,230dip,30dip,40dip,40dip)
	
	'giorno
	LDay.Initialize("")
	LDay.Gravity=Gravity.CENTER
	LDay.Text=DateTime.GetDayOfMonth(DateRet)
	LDay.TextSize=100
	LDay.Textcolor=Colors.White
	LDay.Typeface=Typeface.SANS_SERIF
	LDay.Color=Colors.Transparent
	Testa.AddView(LDay,0dip,70dip,280dip,120dip)
	
	'Anno
	LYear.Initialize("")
	LYear.Gravity=Gravity.CENTER
	LYear.Text=DateTime.GetYear(DateRet)
	LYear.TextSize=30
	LYear.Textcolor=Colors.ARGB(200,255,255,255)
	LYear.Color=Colors.Transparent
	Testa.AddView(LYear,0dip,190dip,280dip,40dip)
	'Prev Anno
	Dim Lab As Label
	Lab.Initialize("PrevY")
	Lab.Gravity=Gravity.CENTER
	Lab.Text="<"
	Lab.TextSize=30
	Lab.Textcolor=Colors.ARGB(200,255,255,255)
	Lab.Color=Colors.Transparent
	Testa.AddView(Lab,10dip,190dip,40dip,40dip)
	'Next year
	Dim Lab As Label
	Lab.Initialize("NextY")
	Lab.Gravity=Gravity.CENTER
	Lab.Text=">"
	Lab.TextSize=30
	Lab.Textcolor=Colors.ARGB(200,255,255,255)
	Lab.Color=Colors.Transparent
	Testa.AddView(Lab,230dip,190dip,40dip,40dip)
End Sub

Public Sub Show(Title As String) As Int
		
	Pa.Initialize("Pa")
	Pa.Color=Colors.ARGB(200,0,0,0)
	
	Cale.Initialize("Cale")
	Cale.Color=Colors.White
	
	Testa.Initialize("Testa")
	Testa. Color=Colore
	
	FillCalendar
	FillTesta(Title)
		
	If 100%y>100%x Then 
		'verticale -250 +280 = 530
		Pa.AddView(Testa,50%x-140dip,50%y-265dip,280dip,250dip)
		Pa.AddView(Cale,50%x-140dip,50%y-15dip,280dip,280dip)
		MyAct.AddView(Pa,0dip,0dip,100%x,100%y)
	Else
		'orizzontale
		Pa.AddView(Testa,50%x-280dip,50%y-140dip,280dip,280dip)
		Pa.AddView(Cale,50%x,50%y-140dip,280dip,280dip)
		MyAct.AddView(Pa,0dip,0dip,100%x,100%y)
	End If
	
	Do While Ret=0
		DoEvents
	Loop
	
	Pa.RemoveView
	Return Ret
End Sub

private Sub Giorno_Click
	LabelNow.Color=Colors.Transparent
	LabelNow.TextColor=ColorLabelNow
	
	LabelNow=Sender
	ColorLabelNow=LabelNow.Textcolor
	LabelNow.Background=Corner(Colore)
	LabelNow.Textcolor=Colors.White
	
	DateRet=LabelNow.Tag
	LDay.Text=LabelNow.Text
End Sub

Private Sub MoveDate(deltaMonth As Int, deltaYear As Int)
	DateRet = DateTime.Add(DateRet, deltaYear, deltaMonth, 0)
	FillCalendar
	LMounth.Text=NomeMese(DateTime.GetMonth(DateRet))
	LYear.Text=DateTime.GetYear(DateRet)
	LDay.Text=DateTime.GetDayOfMonth(DateRet)
End Sub

Private Sub PrevM_Click
	MoveDate(-1, 0)
End Sub

Private Sub NextM_Click
	MoveDate(1, 0)
End Sub

Private Sub PrevY_Click
	MoveDate(0, -1)
End Sub

Private Sub NextY_Click
	MoveDate(0, 1)
End Sub

private Sub ButtonOk_Click
	DateSelected=DateRet
	Ret=DialogResponse.POSITIVE
End Sub

private Sub ButtonCancel_Click
	Ret=DialogResponse.NEGATIVE
End Sub