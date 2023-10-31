@echo off
set app="Visitation Book API"
echo  %app%

set SBT_PATH="C:\\Program Files (x86)\\sbt\\bin\\sbt.bat"

if not exist %SBT_PATH% (
    echo sbt not found at %SBT_PATH%
    exit /b
)

cd "%WORKSPACE%"
call %SBT_PATH% dist

echo end of execution