@echo off
cd /d "C:\Users\PORTISHEAD\Desktop\ALLCOM\allcom_qa"
echo ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
echo 		   Start allcom_qa tests in Chrome [PASSWORD RESTORE TESTS, HEADLESS MODE]
echo ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
call gradlew -Pbrowser=chrome -Pmode=headless clean allcom_qa_restore_password_page
pause