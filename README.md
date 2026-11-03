# Level 13: Advanced JVM Tuning & Generational GC Profiling

This project provides live runtime diagnostics into JVM generational memory structures (Eden, Survivor, Old Gen / Tenured, Metaspace) and GC metrics.

---

## Production Recommended Flag Profiles

### 1. Low Latency: Generational ZGC (Java 21+)
```bash
java -XX:+UseZGC -XX:+ZGenerational -Xms4g -Xmx4g -jar app.jar
```

### 2. High Throughput: G1GC with String Deduplication
```bash
java -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=100 \
     -XX:+UseStringDeduplication \
     -Xlog:gc*,gc+phases=debug:file=/var/log/app/gc.log:time,uptime,pid:filecount=5,filesize=50M \
     -jar app.jar
```
