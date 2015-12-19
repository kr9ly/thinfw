/**
 * This class is generated by jOOQ
 */
package net.kr9ly.thinfw.db.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import net.kr9ly.thinfw.db.tables.Usercredentials;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UsercredentialsRecord extends UpdatableRecordImpl<UsercredentialsRecord> implements Record5<Long, String, String, LocalDateTime, LocalDateTime> {

	private static final long serialVersionUID = 1468154561;

	/**
	 * Setter for <code>thinfw.UserCredentials.userId</code>.
	 */
	public void setUserid(Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>thinfw.UserCredentials.userId</code>.
	 */
	public Long getUserid() {
		return (Long) getValue(0);
	}

	/**
	 * Setter for <code>thinfw.UserCredentials.authKey</code>.
	 */
	public void setAuthkey(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>thinfw.UserCredentials.authKey</code>.
	 */
	public String getAuthkey() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>thinfw.UserCredentials.passwordHash</code>.
	 */
	public void setPasswordhash(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>thinfw.UserCredentials.passwordHash</code>.
	 */
	public String getPasswordhash() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>thinfw.UserCredentials.createdAt</code>.
	 */
	public void setCreatedat(LocalDateTime value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>thinfw.UserCredentials.createdAt</code>.
	 */
	public LocalDateTime getCreatedat() {
		return (LocalDateTime) getValue(3);
	}

	/**
	 * Setter for <code>thinfw.UserCredentials.updatedAt</code>.
	 */
	public void setUpdatedat(LocalDateTime value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>thinfw.UserCredentials.updatedAt</code>.
	 */
	public LocalDateTime getUpdatedat() {
		return (LocalDateTime) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Long> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Long, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Long, String, String, LocalDateTime, LocalDateTime> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return Usercredentials.USERCREDENTIALS.USERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Usercredentials.USERCREDENTIALS.AUTHKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Usercredentials.USERCREDENTIALS.PASSWORDHASH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<LocalDateTime> field4() {
		return Usercredentials.USERCREDENTIALS.CREATEDAT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<LocalDateTime> field5() {
		return Usercredentials.USERCREDENTIALS.UPDATEDAT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getUserid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getAuthkey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getPasswordhash();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDateTime value4() {
		return getCreatedat();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDateTime value5() {
		return getUpdatedat();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsercredentialsRecord value1(Long value) {
		setUserid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsercredentialsRecord value2(String value) {
		setAuthkey(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsercredentialsRecord value3(String value) {
		setPasswordhash(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsercredentialsRecord value4(LocalDateTime value) {
		setCreatedat(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsercredentialsRecord value5(LocalDateTime value) {
		setUpdatedat(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsercredentialsRecord values(Long value1, String value2, String value3, LocalDateTime value4, LocalDateTime value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UsercredentialsRecord
	 */
	public UsercredentialsRecord() {
		super(Usercredentials.USERCREDENTIALS);
	}

	/**
	 * Create a detached, initialised UsercredentialsRecord
	 */
	public UsercredentialsRecord(Long userid, String authkey, String passwordhash, LocalDateTime createdat, LocalDateTime updatedat) {
		super(Usercredentials.USERCREDENTIALS);

		setValue(0, userid);
		setValue(1, authkey);
		setValue(2, passwordhash);
		setValue(3, createdat);
		setValue(4, updatedat);
	}
}