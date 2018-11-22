#### epatesting Start.sh ###
#!/bin/bash

echo "Iniciando Epa Benchmark..."
export _JAVA_OPTIONS=-Xmx1024m
cd /home/jgodoy/workspace-epa/epatesting
newName=$(date +%Y-%m-%d_%H%M%S)
newName=epa_output.$newName.txt
rm -r /home/jgodoy/Replication-Package/epa-benchmark/evosuite-report/ /home/jgodoy/Replication-Package/epa-benchmark/results/all_resumes.csv
nohup python3.6 /home/jgodoy/Replication-Package/epa-benchmark/script.py "/home/jgodoy/Replication-Package/epa-benchmark/config_example.ini" "/home/jgodoy/Replication-Package/epa-benchmark/runs_example.ini" > /home/jgodoy/Replication-Package/epa-benchmark/results/$newName &
exit 0
