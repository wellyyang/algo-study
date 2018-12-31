package com.welly.algo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

import lombok.Data;

/**
 * 用于对分布概率统计, 线程安全
 *
 * @author yangchuan02
 * @date 2018年12月30日
 */
@Data
public class DistributionStat<T> {

	private final int count;

	private final Map<T, Map<Integer, LongAdder>> stat;

	public DistributionStat(int count) {
		this.count = count;
		this.stat = new ConcurrentHashMap<>();
	}

	public void increase(T t, int pos) {
		if (!stat.containsKey(t)) {
			stat.putIfAbsent(t, new ConcurrentHashMap<>());
		}
		if (!stat.get(t).containsKey(pos)) {
			stat.get(t).putIfAbsent(pos, new LongAdder());
		}
		stat.get(t).get(pos).increment();
	}

}
