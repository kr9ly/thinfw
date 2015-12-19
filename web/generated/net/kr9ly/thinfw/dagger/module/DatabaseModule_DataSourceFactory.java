package net.kr9ly.thinfw.dagger.module;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.sql.DataSource;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DatabaseModule_DataSourceFactory implements Factory<DataSource> {
  private final DatabaseModule module;

  public DatabaseModule_DataSourceFactory(DatabaseModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public DataSource get() {  
    DataSource provided = module.dataSource();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<DataSource> create(DatabaseModule module) {  
    return new DatabaseModule_DataSourceFactory(module);
  }
}

