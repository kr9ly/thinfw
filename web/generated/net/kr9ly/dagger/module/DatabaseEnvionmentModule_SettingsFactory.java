package net.kr9ly.dagger.module;

import dagger.internal.Factory;
import javax.annotation.Generated;
import org.jooq.conf.Settings;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DatabaseEnvionmentModule_SettingsFactory implements Factory<Settings> {
  private final DatabaseEnvionmentModule module;

  public DatabaseEnvionmentModule_SettingsFactory(DatabaseEnvionmentModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Settings get() {  
    Settings provided = module.settings();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Settings> create(DatabaseEnvionmentModule module) {  
    return new DatabaseEnvionmentModule_SettingsFactory(module);
  }
}

