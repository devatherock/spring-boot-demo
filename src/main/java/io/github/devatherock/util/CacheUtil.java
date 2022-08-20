package io.github.devatherock.util;

import java.util.Collections;
import java.util.Map;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheUtil {

    public static void logCacheMetrics(MeterRegistry meterRegistry) {
        LOGGER.info("Cache metrics: size={}, hits={}, misses={}, puts={}, evictions={}, removals={}",
                getGaugeValue(meterRegistry, "cache.size"),
                getFunctionCounterValue(meterRegistry, "cache.gets", Collections.singletonMap("result", "hit")),
                getFunctionCounterValue(meterRegistry, "cache.gets", Collections.singletonMap("result", "miss")),
                getFunctionCounterValue(meterRegistry, "cache.puts"),
                getFunctionCounterValue(meterRegistry, "cache.evictions"),
                getGaugeValue(meterRegistry, "cache.removals"));
    }

    private static Meter getMeter(MeterRegistry meterRegistry, String meterName, Map<String, String> tags) {
        return meterRegistry.getMeters().stream()
                .filter(meter -> meter.getId().getName().equals(meterName) &&
                        tags.entrySet().stream()
                                .allMatch(inputTag -> meter.getId().getTags().stream()
                                        .anyMatch(meterTag -> meterTag.getKey().equals(inputTag.getKey())
                                                && meterTag.getValue().equals(inputTag.getValue()))))
                .findFirst()
                .orElse(null);
    }

    private static double getGaugeValue(MeterRegistry meterRegistry, String gaugeName) {
        Gauge gauge = (Gauge) getMeter(meterRegistry, gaugeName, Collections.emptyMap());

        return gauge != null ? gauge.value() : 0;
    }

    private static double getFunctionCounterValue(MeterRegistry meterRegistry, String counterName,
            Map<String, String> tags) {
        FunctionCounter counter = (FunctionCounter) getMeter(meterRegistry, counterName, tags);

        return counter != null ? counter.count() : 0;
    }

    private static double getFunctionCounterValue(MeterRegistry meterRegistry, String counterName) {
        return getFunctionCounterValue(meterRegistry, counterName, Collections.emptyMap());
    }
}
