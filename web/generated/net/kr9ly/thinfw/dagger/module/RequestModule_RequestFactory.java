package net.kr9ly.thinfw.dagger.module;

import dagger.internal.Factory;
import javax.annotation.Generated;
import spark.Request;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class RequestModule_RequestFactory implements Factory<Request> {
  private final RequestModule module;

  public RequestModule_RequestFactory(RequestModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Request get() {  
    Request provided = module.request();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Request> create(RequestModule module) {  
    return new RequestModule_RequestFactory(module);
  }
}

