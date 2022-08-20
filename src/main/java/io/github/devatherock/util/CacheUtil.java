package io.github.devatherock.util;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheUtil {
    private final MeterRegistry meterRegistry;

    public void logCacheMetrics() {
        LOGGER.info("Cache metrics: size={}, hits={}, misses={}, puts={}, evictions={}, removals={}",
                getGaugeValue("cache.size"),
                getFunctionCounterValue("cache.gets", Collections.singletonMap("result", "hit")),
                getFunctionCounterValue("cache.gets", Collections.singletonMap("result", "miss")),
                getFunctionCounterValue("cache.puts"),
                getFunctionCounterValue("cache.evictions"),
                getGaugeValue("cache.removals"));
    }

    private Meter getMeter(String meterName, Map<String, String> tags) {
        return meterRegistry.getMeters().stream()
                .filter(meter -> meter.getId().getName().equals(meterName) &&
                        (tags.isEmpty()
                                || tags.entrySet().stream()
                                        .allMatch(inputTag -> meter.getId().getTags().stream()
                                                .anyMatch(meterTag -> meterTag.getKey().equals(inputTag.getKey())
                                                        && meterTag.getValue().equals(inputTag.getValue())))))
                .findFirst()
                .orElse(null);
    }

    private double getGaugeValue(String gaugeName) {
        Gauge gauge = (Gauge) getMeter(gaugeName, Collections.emptyMap());

        return gauge != null ? gauge.value() : 0;
    }

    private double getFunctionCounterValue(String counterName, Map<String, String> tags) {
        FunctionCounter counter = (FunctionCounter) getMeter(counterName, tags);

        return counter != null ? counter.count() : 0;
    }

    private double getFunctionCounterValue(String counterName) {
        return getFunctionCounterValue(counterName, Collections.emptyMap());
    }
}
