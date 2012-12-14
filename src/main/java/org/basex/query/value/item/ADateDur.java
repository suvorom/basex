package org.basex.query.value.item;

import static org.basex.query.util.Err.*;

import java.math.*;

import org.basex.query.*;
import org.basex.query.value.type.*;
import org.basex.util.*;

/**
 * Abstract super class for dates and durations.
 *
 * @author BaseX Team 2005-12, BSD License
 * @author Christian Gruen
 */
public abstract class ADateDur extends Item {
  /** Maximum long value. */
  public static final BigDecimal BDMAXLONG = BigDecimal.valueOf(Long.MAX_VALUE);
  /** Seconds per day. */
  protected static final BigDecimal DAYSECONDS = BigDecimal.valueOf(86400);
  /** BigDecimal: 146097. */
  protected static final BigDecimal BD146097 = BigDecimal.valueOf(146097);
  /** BigDecimal: 36525. */
  protected static final BigDecimal BD36525 = BigDecimal.valueOf(36525);
  /** BigDecimal: 36524. */
  protected static final BigDecimal BD36524 = BigDecimal.valueOf(36524);
  /** BigDecimal: 60. */
  protected static final BigDecimal BD3600 = BigDecimal.valueOf(3600);
  /** BigDecimal: 1461. */
  protected static final BigDecimal BD1461 = BigDecimal.valueOf(1461);
  /** BigDecimal: 1000. */
  protected static final BigDecimal BD1000 = BigDecimal.valueOf(1000);
  /** BigDecimal: 366. */
  protected static final BigDecimal BD366 = BigDecimal.valueOf(366);
  /** BigDecimal: 365. */
  protected static final BigDecimal BD365 = BigDecimal.valueOf(365);
  /** BigDecimal: 153. */
  protected static final BigDecimal BD153 = BigDecimal.valueOf(153);
  /** BigDecimal: 100. */
  protected static final BigDecimal BD100 = BigDecimal.valueOf(100);
  /** BigDecimal: 60. */
  protected static final BigDecimal BD60 = BigDecimal.valueOf(60);
  /** BigDecimal: 5. */
  protected static final BigDecimal BD5 = BigDecimal.valueOf(5);
  /** BigDecimal: 4. */
  protected static final BigDecimal BD4 = BigDecimal.valueOf(4);
  /** BigDecimal: 2. */
  protected static final BigDecimal BD2 = BigDecimal.valueOf(2);

  /** Seconds and milliseconds ({@code 0-59.\d+}). {@code -1}: undefined. */
  public BigDecimal sec;

  /**
   * Constructor.
   * @param t data type
   */
  protected ADateDur(final Type t) {
    super(t);
  }

  /**
   * Returns the years.
   * @return year
   */
  public abstract long yea();

  /**
   * Returns the months.
   * @return year
   */
  public abstract long mon();

  /**
   * Returns the days.
   * @return day
   */
  public abstract long day();

  /**
   * Returns the hours.
   * @return day
   */
  public abstract long hou();

  /**
   * Returns the minutes.
   * @return day
   */
  public abstract long min();

  /**
   * Returns the seconds.
   * @return day
   */
  public abstract BigDecimal sec();

  /**
   * Throws a date format exception.
   * @param i input
   * @param ex example format
   * @param ii input info
   * @return never
   * @throws QueryException query exception
   */
  protected final QueryException dateErr(final byte[] i, final String ex,
      final InputInfo ii) throws QueryException {
    throw DATEFORMAT.thrw(ii, type, i, ex);
  }

  /**
   * Date and durations: converts the specified string to an integer value.
   * Returns an exception if the value is invalid.
   * @param s string to be converted
   * @param dur duration
   * @param ii input info
   * @return long value
   * @throws QueryException query exception
   */
  protected long toLong(final String s, final boolean dur, final InputInfo ii)
      throws QueryException {

    try {
      return Long.parseLong(s);
    } catch(final NumberFormatException ex) {
      throw (dur ? DURRANGE : DATERANGE).thrw(ii, type, s);
    }
  }

  /**
   * Date and durations: converts the specified string to a decimal value.
   * Returns an exception if the value is invalid.
   * @param s string to be converted
   * @param dur duration
   * @param ii input info
   * @return decimal
   * @throws QueryException query exception
   */
  protected BigDecimal toDecimal(final String s, final boolean dur, final InputInfo ii)
      throws QueryException {

    try {
      return new BigDecimal(s);
    } catch(final NumberFormatException ex) {
      throw (dur ? DURRANGE : DATERANGE).thrw(ii, type, s);
    }
  }
}