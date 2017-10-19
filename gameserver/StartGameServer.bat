@echo off
:start
title GameServer
color 0D
echo Starting GameServer
echo.

java -server -Dfile.encoding=UTF-8 -Xms4096m -Xmx4096m -XX:+PrintGCDetails -Xloggc:gc.log -cp config;./../lib/* l2p.gameserver.BootManager

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Server restarted ...
echo.
goto start
:error
echo.
echo Server terminated abnormaly ...
echo.
:end
echo.
echo Server terminated ...
echo.

pause 