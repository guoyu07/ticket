package com.ticket.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

@SuppressWarnings("unchecked")
public class Mapx extends LinkedHashMap {
	private static final float DEFAULT_LOAD_FACTOR = 0.75F;
	private static final int DEFAULT_INIT_CAPACITY = 16;
	private static final long serialVersionUID = 200904201752L;
	private final int maxCapacity;
	private final boolean maxFlag;
	private int hitCount;
	private int missCount;
	private long lastWarnTime;
	private ExitEventListener listener;

	public Mapx(int maxCapacity, boolean LRUFlag) {
		super(maxCapacity, 0.75F, LRUFlag);

		this.hitCount = 0;

		this.missCount = 0;

		this.lastWarnTime = 0L;

		this.maxCapacity = maxCapacity;
		this.maxFlag = true;
	}

	public Mapx(int maxCapacity) {
		this(maxCapacity, true);
	}

	public Mapx() {
		super(DEFAULT_INIT_CAPACITY, DEFAULT_LOAD_FACTOR, false);

		this.hitCount = 0;

		this.missCount = 0;

		this.lastWarnTime = 0L;

		this.maxCapacity = 0;
		this.maxFlag = false;
	}

	public Object clone() {
		Mapx map = (Mapx) super.clone();
		Object[] ks = keyArray();
		Object[] vs = valueArray();
		for (int i = 0; i < ks.length; ++i) {
			Object v = vs[i];
			if (v instanceof Mapx) {
				map.put(ks[i], ((Mapx) v).clone());
			}
		}
		return map;
	}

	protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
		boolean flag = maxFlag && size() > maxCapacity;
		if (flag && listener != null)
			listener.onExit(eldest.getKey(), eldest.getValue());
		return flag;
	}

	public void setExitEventListener(ExitEventListener listener) {
		this.listener = listener;
	}

	public Object[] keyArray() {
		if (size() == 0) {
			return new Object[0];
		}
		Object[] arr = new Object[size()];
		int i = 0;
		for (Iterator iter = keySet().iterator(); iter.hasNext();) {
			arr[(i++)] = iter.next();
		}
		return arr;
	}

	public Object[] valueArray() {
		if (size() == 0) {
			return new Object[0];
		}
		Object[] arr = new Object[size()];
		int i = 0;
		for (Iterator iter = values().iterator(); iter.hasNext();) {
			arr[(i++)] = iter.next();
		}
		return arr;
	}

	public String getString(Object key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		return o.toString();
	}

	public void put(Object key, int num) {
		put(key, new Integer(num));
	}

	public void put(Object key, long num) {
		put(key, new Long(num));
	}

	public int getInt(Object key) {
		Integer obj = (Integer) get(key);
		if (obj == null) {
			return 0;
		}
		return obj.intValue();
	}

	public long getLong(Object key) {
		Long obj = (Long) get(key);
		if (obj == null) {
			return 0L;
		}
		return obj.longValue();
	}

	public Object get(Object key) {
		Object o = super.get(key);
		if (maxFlag) {
			if (o == null)
				missCount++;
			else
				hitCount++;
			if (missCount > 1000
					&& ((double) hitCount * 1.0D) / (double) missCount < 0.5D
					&& System.currentTimeMillis() - lastWarnTime > 0xf4240L) {
				lastWarnTime = System.currentTimeMillis();
				StackTraceElement stack[] = (new Throwable()).getStackTrace();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < stack.length; i++) {
					StackTraceElement ste = stack[i];
					if (ste.getClassName().indexOf("DBConnPoolImpl") == -1) {
						sb.append("\t");
						sb.append(ste.getClassName());
						sb.append(".");
						sb.append(ste.getMethodName());
						sb.append("(),�к�:");
						sb.append(ste.getLineNumber());
						sb.append("\n");
					}
				}

				LogFactory.getLog(Mapx.class).warn("���������ʹ��!");
				LogFactory.getLog(Mapx.class).warn(sb);
			}
		}
		return o;
	}

	public static Mapx convertToMapx(Map map) {
		Mapx mapx = new Mapx();
		mapx.putAll(map);
		return mapx;
	}
}
