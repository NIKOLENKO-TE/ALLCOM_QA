@echo off
cd /d "%~dp0..\"
echo //////////////////////////////////////////////////////////////////////////////////////////////////////
echo 			Start allcom_qa tests in Chrome [SEARCH_BOX, HEADLESS MODE]
echo //////////////////////////////////////////////////////////////////////////////////////////////////////
call gradlew -Pbrowser=chrome -Pmode=headless clean allcom_qa_header_search
pause