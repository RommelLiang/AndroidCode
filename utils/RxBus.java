package com.product.jiamiao.healthbooks.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/31 11:08
 */
public class RxBus {
	private static volatile RxBus mDefaultInstance;

	private RxBus() {
	}

	public static RxBus getInstance() {
		if (mDefaultInstance == null) {
			synchronized (RxBus.class) {
				if (mDefaultInstance == null) {
					mDefaultInstance = new RxBus();
				}
			}
		}
		return mDefaultInstance;
	}

	private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

	public void send(Object o) {
		_bus.onNext(o);
	}

	public Observable<Object> toObservable() {
		return _bus;
	}
}