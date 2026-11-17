//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Lists;
import me.shedaniel.clothconfig2.api.RunSixtyTimesEverySec;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** @deprecated */
@Deprecated
public class RunSixtyTimesEverySecImpl {
    public static final List<RunSixtyTimesEverySec> TICKS_LIST = Lists.newCopyOnWriteArrayList();
    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    public RunSixtyTimesEverySecImpl() {
    }

    static {
        EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
            TICKS_LIST.removeIf(Objects::isNull);
            TICKS_LIST.iterator().forEachRemaining((run) -> {
                try {
                    run.run();
                } catch (Throwable var2) {
                    Throwable throwable = var2;
                    throwable.printStackTrace();
                }

            });
        }, 0L, 16L, TimeUnit.MILLISECONDS);
    }
}
