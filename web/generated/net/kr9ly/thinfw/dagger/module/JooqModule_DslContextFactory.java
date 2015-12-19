package net.kr9ly.thinfw.dagger.module;

import dagger.internal.Factory;
import java.sql.Connection;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class JooqModule_DslContextFactory implements Factory<DSLContext> {
  private final JooqModule module;
  private final Provider<Connection> connectionProvider;
  private final Provider<SQLDialect> dialectProvider;
  private final Provider<Settings> settingsProvider;

  public JooqModule_DslContextFactory(JooqModule module, Provider<Connection> connectionProvider, Provider<SQLDialect> dialectProvider, Provider<Settings> settingsProvider) {  
    assert module != null;
    this.module = module;
    assert connectionProvider != null;
    this.connectionProvider = connectionProvider;
    assert dialectProvider != null;
    this.dialectProvider = dialectProvider;
    assert settingsProvider != null;
    this.settingsProvider = settingsProvider;
  }

  @Override
  public DSLContext get() {  
    DSLContext provided = module.dslContext(connectionProvider.get(), dialectProvider.get(), settingsProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<DSLContext> create(JooqModule module, Provider<Connection> connectionProvider, Provider<SQLDialect> dialectProvider, Provider<Settings> settingsProvider) {  
    return new JooqModule_DslContextFactory(module, connectionProvider, dialectProvider, settingsProvider);
  }
}

