package net.kr9ly.thinfw.dagger.module;

import dagger.internal.Factory;
import java.sql.Connection;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.sql.DataSource;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ConnectionModule_ConnectionFactory implements Factory<Connection> {
  private final ConnectionModule module;
  private final Provider<DataSource> dsProvider;

  public ConnectionModule_ConnectionFactory(ConnectionModule module, Provider<DataSource> dsProvider) {  
    assert module != null;
    this.module = module;
    assert dsProvider != null;
    this.dsProvider = dsProvider;
  }

  @Override
  public Connection get() {  
    Connection provided = module.connection(dsProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Connection> create(ConnectionModule module, Provider<DataSource> dsProvider) {  
    return new ConnectionModule_ConnectionFactory(module, dsProvider);
  }
}

