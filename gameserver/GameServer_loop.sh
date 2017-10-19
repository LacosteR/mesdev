#!/bin/bash

while :;
do
	java -client -Dfile.encoding=UTF-8 -Xms4096m -Xmx8192m -XX:+PrintGCDetails -Xloggc:gc.log -cp config:./../lib/smrt.jar:./../lib/smrt-core.jar:./../lib/* ru.akumu.smartguard.SmartGuard l2p.gameserver.BootManager > log/stdout.log 2>&1

	[ $? -ne 2 ] && break
	sleep 30;
done

