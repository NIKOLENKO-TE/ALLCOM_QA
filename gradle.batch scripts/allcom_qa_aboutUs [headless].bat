@echo off
cd /d "%~dp0..\"
echo //////////////////////////////////////////////////////////////////////////////////////////////////////
echo 			Start allcom_qa tests in Chrome [ABOUTUS, HEADLESS MODE]
echo //////////////////////////////////////////////////////////////////////////////////////////////////////
call gradlew -Pbrowser=chrome -Pmode=headless clean allcom_qa_aboutUs
pause