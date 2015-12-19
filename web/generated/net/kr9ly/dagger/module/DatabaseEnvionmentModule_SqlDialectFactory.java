package net.kr9ly.dagger.module;

import dagger.internal.Factory;
import javax.annotation.Generated;
import org.jooq.SQLDialect;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DatabaseEnvionmentModule_SqlDialectFactory implements Factory<SQLDialect> {
  private final DatabaseEnvionmentModule module;

  public DatabaseEnvionmentModule_SqlDialectFactory(DatabaseEnvionmentModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public SQLDialect get() {  
    SQLDialect provided = module.sqlDialect();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<SQLDialect> create(DatabaseEnvionmentModule module) {  
    return new DatabaseEnvionmentModule_SqlDialectFactory(module);
  }
}

