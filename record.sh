file="$1.csv"
echo "cpu%" > $file
pid=`adb shell pidof $1`
while [ true ]
do
  pid=`adb shell pidof $1`
  res=`adb shell top -b -n 1 -o %CPU,%MEM -p $pid`
  # echo $res
  # end=`sed -e 's#.*M\(\)#\1#' <<< $res`
  cpu=`echo $res | cut -d " " -f 41`
  # echo "[$(date +"%T")] $pid: $cpu, $mem"
  echo "$cpu" >> $file
  echo "$cpu"
  sleep 0.01
done