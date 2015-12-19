package net.kr9ly.thinfw.dagger.component;

import dagger.internal.Factory;
import dagger.internal.ScopedProvider;
import java.sql.Connection;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.sql.DataSource;
import net.kr9ly.thinfw.dagger.module.ConnectionModule;
import net.kr9ly.thinfw.dagger.module.ConnectionModule_ConnectionFactory;
import net.kr9ly.thinfw.dagger.module.JooqModule;
import net.kr9ly.thinfw.dagger.module.JooqModule_DslContextFactory;
import net.kr9ly.thinfw.dagger.module.RequestModule;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerRequestComponent implements RequestComponent {
  private Provider<DataSource> dataSourceProvider;
  private Provider<Connection> connectionProvider;
  private Provider<SQLDialect> sQLDialectProvider;
  private Provider<Settings> settingsProvider;
  private Provider<DSLContext> dslContextProvider;

  private DaggerRequestComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.dataSourceProvider = new Factory<DataSource>() {
      private final ApplicationComponent applicationComponent = builder.applicationComponent;
      @Override public DataSource get() {
        DataSource provided = applicationComponent.dataSource();
        if (provided == null) {
          throw new NullPointerException("Cannot return null from a non-@Nullable component method");
        }
        return provided;
      }
    };
    this.connectionProvider = ScopedProvider.create(ConnectionModule_ConnectionFactory.create(builder.connectionModule, dataSourceProvider));
    this.sQLDialectProvider = new Factory<SQLDialect>() {
      private final ApplicationComponent applicationComponent = builder.applicationComponent;
      @Override public SQLDialect get() {
        SQLDialect provided = applicationComponent.sQLDialect();
        if (provided == null) {
          throw new NullPointerException("Cannot return null from a non-@Nullable component method");
        }
        return provided;
      }
    };
    this.settingsProvider = new Factory<Settings>() {
      private final ApplicationComponent applicationComponent = builder.applicationComponent;
      @Override public Settings get() {
        Settings provided = applicationComponent.settings();
        if (provided == null) {
          throw new NullPointerException("Cannot return null from a non-@Nullable component method");
        }
        return provided;
      }
    };
    this.dslContextProvider = JooqModule_DslContextFactory.create(builder.jooqModule, connectionProvider, sQLDialectProvider, settingsProvider);
  }

  @Override
  public Connection connection() {  
    return connectionProvider.get();
  }

  @Override
  public DSLContext dSLContext() {  
    return dslContextProvider.get();
  }

  public static final class Builder {
    private RequestModule requestModule;
    private ConnectionModule connectionModule;
    private JooqModule jooqModule;
    private ApplicationComponent applicationComponent;
  
    private Builder() {  
    }
  
    public RequestComponent build() {  
      if (requestModule == null) {
        throw new IllegalStateException("requestModule must be set");
      }
      if (connectionModule == null) {
        this.connectionModule = new ConnectionModule();
      }
      if (jooqModule == null) {
        this.jooqModule = new JooqModule();
      }
      if (applicationComponent == null) {
        throw new IllegalStateException("applicationComponent must be set");
      }
      return new DaggerRequestComponent(this);
    }
  
    public Builder requestModule(RequestModule requestModule) {  
      if (requestModule == null) {
        throw new NullPointerException("requestModule");
      }
      this.requestModule = requestModule;
      return this;
    }
  
    public Builder connectionModule(ConnectionModule connectionModule) {  
      if (connectionModule == null) {
        throw new NullPointerException("connectionModule");
      }
      this.connectionModule = connectionModule;
      return this;
    }
  
    public Builder jooqModule(JooqModule jooqModule) {  
      if (jooqModule == null) {
        throw new NullPointerException("jooqModule");
      }
      this.jooqModule = jooqModule;
      return this;
    }
  
    public Builder applicationComponent(ApplicationComponent applicationComponent) {  
      if (applicationComponent == null) {
        throw new NullPointerException("applicationComponent");
      }
      this.applicationComponent = applicationComponent;
      return this;
    }
  }
}

