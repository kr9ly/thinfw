/**
 * This class is generated by jOOQ
 */
package net.kr9ly.thinfw.db.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import net.kr9ly.thinfw.converter.LocalDateTimeConverter;
import net.kr9ly.thinfw.db.Keys;
import net.kr9ly.thinfw.db.Thinfw;
import net.kr9ly.thinfw.db.tables.records.UsersRecord;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users extends TableImpl<UsersRecord> {

	private static final long serialVersionUID = -1976427865;

	/**
	 * The reference instance of <code>thinfw.User</code>
	 */
	public static final Users USERS = new Users();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<UsersRecord> getRecordType() {
		return UsersRecord.class;
	}

	/**
	 * The column <code>thinfw.User.userId</code>.
	 */
	public final TableField<UsersRecord, Long> USERID = createField("userId", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>thinfw.User.userName</code>.
	 */
	public final TableField<UsersRecord, String> USERNAME = createField("userName", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>thinfw.User.createdAt</code>.
	 */
	public final TableField<UsersRecord, LocalDateTime> CREATEDAT = createField("createdAt", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "", new LocalDateTimeConverter());

	/**
	 * The column <code>thinfw.User.updatedAt</code>.
	 */
	public final TableField<UsersRecord, LocalDateTime> UPDATEDAT = createField("updatedAt", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "", new LocalDateTimeConverter());

	/**
	 * Create a <code>thinfw.User</code> table reference
	 */
	public Users() {
		this("Users", null);
	}

	/**
	 * Create an aliased <code>thinfw.User</code> table reference
	 */
	public Users(String alias) {
		this(alias, USERS);
	}

	private Users(String alias, Table<UsersRecord> aliased) {
		this(alias, aliased, null);
	}

	private Users(String alias, Table<UsersRecord> aliased, Field<?>[] parameters) {
		super(alias, Thinfw.THINFW, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<UsersRecord, Long> getIdentity() {
		return Keys.IDENTITY_USERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<UsersRecord> getPrimaryKey() {
		return Keys.KEY_USERS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<UsersRecord>> getKeys() {
		return Arrays.<UniqueKey<UsersRecord>>asList(Keys.KEY_USERS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Users as(String alias) {
		return new Users(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Users rename(String name) {
		return new Users(name, null);
	}
}
