package net.kr9ly.thinfw.dagger.component;

import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.sql.DataSource;
import net.kr9ly.dagger.module.DatabaseEnvionmentModule;
import net.kr9ly.dagger.module.DatabaseEnvionmentModule_SettingsFactory;
import net.kr9ly.dagger.module.DatabaseEnvionmentModule_SqlDialectFactory;
import net.kr9ly.thinfw.dagger.module.DatabaseModule;
import net.kr9ly.thinfw.dagger.module.DatabaseModule_DataSourceFactory;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerApplicationComponent implements ApplicationComponent {
  private Provider<DataSource> dataSourceProvider;
  private Provider<SQLDialect> sqlDialectProvider;
  private Provider<Settings> settingsProvider;

  private DaggerApplicationComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  public static ApplicationComponent create() {  
    return builder().build();
  }

  private void initialize(final Builder builder) {  
    this.dataSourceProvider = ScopedProvider.create(DatabaseModule_DataSourceFactory.create(builder.databaseModule));
    this.sqlDialectProvider = ScopedProvider.create(DatabaseEnvionmentModule_SqlDialectFactory.create(builder.databaseEnvionmentModule));
    this.settingsProvider = ScopedProvider.create(DatabaseEnvionmentModule_SettingsFactory.create(builder.databaseEnvionmentModule));
  }

  @Override
  public DataSource dataSource() {  
    return dataSourceProvider.get();
  }

  @Override
  public SQLDialect sQLDialect() {  
    return sqlDialectProvider.get();
  }

  @Override
  public Settings settings() {  
    return settingsProvider.get();
  }

  public static final class Builder {
    private DatabaseModule databaseModule;
    private DatabaseEnvionmentModule databaseEnvionmentModule;
  
    private Builder() {  
    }
  
    public ApplicationComponent build() {  
      if (databaseModule == null) {
        this.databaseModule = new DatabaseModule();
      }
      if (databaseEnvionmentModule == null) {
        this.databaseEnvionmentModule = new DatabaseEnvionmentModule();
      }
      return new DaggerApplicationComponent(this);
    }
  
    public Builder databaseModule(DatabaseModule databaseModule) {  
      if (databaseModule == null) {
        throw new NullPointerException("databaseModule");
      }
      this.databaseModule = databaseModule;
      return this;
    }
  
    public Builder databaseEnvionmentModule(DatabaseEnvionmentModule databaseEnvionmentModule) {  
      if (databaseEnvionmentModule == null) {
        throw new NullPointerException("databaseEnvionmentModule");
      }
      this.databaseEnvionmentModule = databaseEnvionmentModule;
      return this;
    }
  }
}

