@echo off
set app="Visitation Book API"
echo  %app%
cd ../
call sbt dist

echo end of execution