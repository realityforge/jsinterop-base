/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package jsinterop.base;

import static jsinterop.base.InternalPreconditions.checkType;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;

/**
 * A JavaScript constructor function.
 *
 * <p>Note that this interface is not intented for manual implementation.
 */
@JsFunction
public interface JsConstructorFn<T> {

  @JsOverlay
  public static <T> JsConstructorFn<T> of(Class<T> clazz) {
    JsConstructorFn<T> fn = InternalJsUtil.toCtor(clazz);
    checkType(fn != null);
    return fn;
  }

  /**
   * Most of the time you don't want to call this method but call {@link #construct} instead since
   * this is a constructor function.
   */
  void onInvoke(Object... args);

  /** Invokes 'new' operator on this constructor. */
  @JsOverlay
  default T construct(Object... args) {
    return InternalJsUtil.construct(this, args);
  }

  /** Returns this constructor as a Class instance. */
  @JsOverlay
  default Class<T> asClass() {
    Class<T> clazz = InternalJsUtil.toClass(this);
    checkType(clazz != null);
    return clazz;
  }
}
