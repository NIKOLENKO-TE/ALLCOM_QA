@echo off
cd /d "%~dp0..\"
echo //////////////////////////////////////////////////////////////////////////////////////////////////////
echo 			Start allcom_qa tests in Chrome [FOOTER, NORMAL MODE]
echo //////////////////////////////////////////////////////////////////////////////////////////////////////
call gradlew -Pbrowser=chrome -Pmode=normal clean allcom_qa_footer
pause