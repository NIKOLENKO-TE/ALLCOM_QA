@echo off
cd /d "%~dp0..\"
echo ////////////////////////////////////////////////////////////////////////////////////////////////
echo 		   Start allcom_qa tests in Firefox [REGRESSION, HEADLESS MODE]
echo ////////////////////////////////////////////////////////////////////////////////////////////////
call gradlew -Pbrowser=firefox -Pmode=headless clean allcom_qa_regression
pause