Set projectLocation=C:\Selenium\Workspace\FlightReservation(POM)
cd %projectLocation%
Set classPath=%projectLocation%\bin;%projectLocation%\libs\*
java org.testng.TestNG testng.xml
pause