pid=`ps auxww | grep java | grep ebrm_01 | awk '{print $2}'`
kill -9 $pid
