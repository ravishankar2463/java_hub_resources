# Level 13: JVM Profiling with JFR & Custom Events

Continuous production profiling using **Java Flight Recorder (JFR)** introduces less than 1% overhead, making it safe for production hyperscale deployments.

---

## Starting JFR Recording at JVM Startup

```bash
java -XX:StartFlightRecording=disk=true,dumponexit=true,filename=recording.jfr,settings=profile -jar app.jar
```

## Starting JFR via JDK `jcmd` on a Running Process

```bash
# 1. Discover JVM PID
jcmd

# 2. Start a 60-second diagnostic recording
jcmd <PID> JFR.start name=prod-profiling duration=60s filename=prod-profile.jfr
```
