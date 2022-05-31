#!/usr/bin/env bash

java -classpath ./oom.jar \
     -Dfile.encoding=UTF-8 \
     -Xmx100m -XX:+UseParallelGC \
     -XX:ParallelGCThreads=4 \
     -XX:+UseParallelOldGC \
     -XX:YoungGenerationSizeIncrement=20 \
     -XX:OnOutOfMemoryError="./oom.sh %p" \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=./dumps.txt \
     com.unclezs.samples.java.oom.OutOfMemorySample
