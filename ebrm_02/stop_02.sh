pid=`ps auxww | grep java | grep ebrm_02 | awk '{print $2}'`
kill -9 $pid
