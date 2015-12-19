package net.kr9ly.dagger.module;

import javax.annotation.Generated;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

@Generated("net.kr9ly.doubler.DoublerProcessor")
public interface DatabaseEnvionmentModuleSupport {
  SQLDialect sQLDialect();

  Settings settings();
}
