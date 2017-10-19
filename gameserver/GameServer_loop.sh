#!/bin/bash

while :;
do
	java -server -Dfile.encoding=UTF-8 -Xmx4096m -Xmx8192m -cp config:./../lib/* l2p.gameserver.BootManager > log/stdout.log 2>&1

	[ $? -ne 2 ] && break
	sleep 30;
done

