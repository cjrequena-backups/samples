package com.sample.architecture.commons.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * @author Carlos Requena
 * 
 */
public final class DateUtils {

	/**
	 * DATE FORMATS
	 */
	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	// Example Fri, 01/01/2013
	public static String DD_MM_YYYY = "dd/MM/yyyy";

	// Example Fri, June 7 2013
	public static String E_MMM_DD_YYYY = "E, MMM dd yyyy";

	// Example Friday, Jun 7, 2013 12:10:56 PM
	public static String EEEE_MMM_DD_YYYY_HH_MM_SS_A = "EEEE, MMM dd, yyyy HH:mm:ss a";

	// Example Jun 7, 2013
	public static String MMM_DD_YYYY = "MMM dd, yyyy";

	// Example 7-Jun-2013
	public static String DD_MMM_YYYY = "dd-MMM-yyyy";

	private final static long DAY_IN_MS = 24 * 60 * 60 * 1000;
	private static final Calendar calendar = Calendar.getInstance();

	/**
	 * Checks if the calendar object is same date as today.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same date as today.
	 */
	public static boolean isToday(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && today.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Checks if the calendar object is same week as today.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same week as today.
	 */
	public static boolean isThisWeek(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && today.get(Calendar.WEEK_OF_YEAR) == cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Checks if the calendar object is same month as today.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same month as today.
	 */
	public static boolean isThisMonth(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && today.get(Calendar.MONTH) == cal.get(Calendar.MONTH);
	}

	/**
	 * Checks if the calendar object is same quarter as today.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same quarter as today.
	 */
	public static boolean isThisQuarter(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && today.get(Calendar.MONTH) / 3 == cal.get(Calendar.MONTH) / 3;
	}

	/**
	 * Checks if the calendar object is same year as today.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same year as today.
	 */
	public static boolean isThisYear(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR);

	}

	/**
	 * Checks if the calendar object is same date as yesterday.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same date as yesterday.
	 */
	public static boolean isYesterday(Calendar cal) {
		Calendar yesterday = adjustDate(Calendar.getInstance(), -1);
		return yesterday.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && yesterday.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Checks if the calendar object is last week.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is last week.
	 */
	public static boolean isLastWeek(Calendar cal) {
		Calendar lastWeek = adjustDate(Calendar.getInstance(), -7);
		return lastWeek.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && lastWeek.get(Calendar.WEEK_OF_YEAR) == cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Checks if the calendar object is last month.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is last month.
	 */
	public static boolean isLastMonth(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisMonth = today.get(Calendar.MONTH);
		if (thisMonth > 1) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && thisMonth - 1 == cal.get(Calendar.MONTH);
		} else {
			return today.get(Calendar.YEAR) - 1 == cal.get(Calendar.YEAR) && today.getActualMaximum(Calendar.MONTH) == cal.get(Calendar.MONTH);
		}
	}

	/**
	 * Checks if the calendar object is last quarter.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is last quarter.
	 */
	public static boolean isLastQuarter(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisQuarter = today.get(Calendar.MONTH) / 3;
		if (thisQuarter > 1) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && thisQuarter - 1 == cal.get(Calendar.MONTH) / 3;
		} else {
			return today.get(Calendar.YEAR) - 1 == cal.get(Calendar.YEAR) && today.getActualMaximum(Calendar.MONTH) / 3 == cal.get(Calendar.MONTH) / 3;
		}
	}

	/**
	 * Checks if the calendar object is last year.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is last year.
	 */
	public static boolean isLastYear(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) - 1 == cal.get(Calendar.YEAR);

	}

	/**
	 * Checks if the calendar object is same date as tomorrow.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is the same date as tomorrow.
	 */
	public static boolean isTomorrow(Calendar cal) {
		Calendar tomorrow = adjustDate(Calendar.getInstance(), 1);
		return tomorrow.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && tomorrow.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Checks if the calendar object is next week.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is next week.
	 */
	public static boolean isNextWeek(Calendar cal) {
		Calendar nextWeek = adjustDate(Calendar.getInstance(), 7);
		return nextWeek.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && nextWeek.get(Calendar.WEEK_OF_YEAR) == cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Checks if the calendar object is next month.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is next month.
	 */
	public static boolean isNextMonth(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisMonth = today.get(Calendar.MONTH);
		if (thisMonth < today.getActualMaximum(Calendar.MONTH)) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && thisMonth + 1 == cal.get(Calendar.MONTH);
		} else {
			return today.get(Calendar.YEAR) + 1 == cal.get(Calendar.YEAR) && today.getMinimum(Calendar.MONTH) == cal.get(Calendar.MONTH);
		}
	}

	/**
	 * Checks if the calendar object is next quarter.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is next quarter.
	 */
	public static boolean isNextQuarter(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisQuarter = today.get(Calendar.MONTH) / 3;
		if (thisQuarter < today.getActualMaximum(Calendar.MONTH) / 3) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && thisQuarter + 1 == cal.get(Calendar.MONTH) / 3;
		} else {
			return today.get(Calendar.YEAR) + 1 == cal.get(Calendar.YEAR) && today.getActualMinimum(Calendar.MONTH) / 3 == cal.get(Calendar.MONTH) / 3;
		}
	}

	/**
	 * Checks if the calendar object is next year.
	 * 
	 * @param cal
	 *            the calendar object
	 * @return true if the calendar object is next year.
	 */
	public static boolean isNextYear(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) + 1 == cal.get(Calendar.YEAR);

	}

	/**
	 * Checks if the calendar object is in the specified month, regardless of the year.
	 * 
	 * @param cal
	 *            the calendar object.
	 * @param month
	 *            the month, starting from 0 for January. You can use the value defined in Calendar
	 *            such as Calendar.JANUARY, Calendar.FEBRUARY, etc.
	 * @return true if the calendar object is the specified month.
	 */
	public static boolean isAtMonth(Calendar cal, int month) {
		return cal.get(Calendar.MONTH) == month;
	}

	/**
	 * Checks if the calendar object is in the specified quarter, regardless of the year.
	 * 
	 * @param cal
	 *            the calendar object.
	 * @param quarter
	 *            the quarter, starting from 1 for the first quarter (including January, February,
	 *            and March).
	 * @return true if the calendar object is the specified quarter.
	 */
	public static boolean isAtQuarter(Calendar cal, int quarter) {
		return cal.get(Calendar.MONTH) / 3 + 1 == quarter;
	}

	/**
	 * Adjusts the Calendar to several days before or after the current date.
	 * 
	 * @param calendar
	 *            the Calendar object to be adjusted.
	 * @param differenceInDay
	 *            the difference in days. It accepts both position and negative number.
	 * @return the calendar after the adjustment. It should always be the same instance as the
	 *         calendar parameter.
	 */
	public static Calendar adjustDate(Calendar calendar, int differenceInDay) {
		calendar.setTimeInMillis(calendar.getTimeInMillis() + DAY_IN_MS * differenceInDay);
		return calendar;
	}

	/**
	 * Returns the minimum Date in the Date list.
	 * 
	 * @param dates
	 *            the list of Date to calculate the minimum.
	 * @return the minimum date in the Date list.
	 */
	public static Date getMinDate(List<Date> dates) {
		long min = Long.MAX_VALUE;
		Date minDate = null;
		for (Date value : dates) {
			long v = value.getTime();
			if (v < min) {
				min = v;
				minDate = value;
			}
		}
		return minDate;
	}

	/**
	 * Returns the maximum Date in the Date list.
	 * 
	 * @param dates
	 *            the list of Date to calculate the maximum.
	 * @return the maximum date in the Date list.
	 */
	public static Date getMaxDate(List<Date> dates) {
		long max = Long.MIN_VALUE;
		Date maxDate = null;
		for (Date value : dates) {
			long v = value.getTime();
			if (v > max) {
				max = v;
				maxDate = value;
			}
		}
		return maxDate;
	}

	/**
	 * Returns the minimum Calendar in the Calendar list.
	 * 
	 * @param calendars
	 *            the list of Calendar to calculate the minimum.
	 * @return the minimum calendar in the Calendar list.
	 */
	public static Calendar getMinCalendar(List<Calendar> calendars) {
		long min = Long.MAX_VALUE;
		Calendar minCalendar = null;
		for (Calendar value : calendars) {
			long v = value.getTimeInMillis();
			if (v < min) {
				min = v;
				minCalendar = value;
			}
		}
		return minCalendar;
	}

	/**
	 * Returns the maximum Calendar in the Calendar list.
	 * 
	 * @param calendars
	 *            the list of Calendar to calculate the maximum.
	 * @return the maximum calendar in the Calendar list.
	 */
	public static Calendar getMaxCalendar(List<Calendar> calendars) {
		long max = Long.MIN_VALUE;
		Calendar maxCalendar = null;
		for (Calendar value : calendars) {
			long v = value.getTimeInMillis();
			if (v > max) {
				max = v;
				maxCalendar = value;
			}
		}
		return maxCalendar;
	}

	/**
	 * Returns days between to dates
	 * 
	 * @param date2
	 * @param date1
	 * @return
	 */
	public static int getDaysBetweenDates(GregorianCalendar date2, GregorianCalendar date1) {
		int rank = 0;
		// verificamos si estamos en el mismo año
		if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
			rank = (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
		} else {
			// Si los años son distintos verificar que el año de inicio no sea bisiesto, ya que de
			// serlo serian 366 dias el año, en caso contratio 365
			int daysOfYear = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;
			// Calculando el rango de los años
			int rankYears = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);
			// Calculando rango de los dias que hay
			rank = (rankYears * daysOfYear) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
		}
		return rank;
	}

	/**
	 * Creates a date <br>
	 * Example <br>
	 * 
	 * <pre>
	 * DateUtils.createDate(2011,10,5);
	 * 
	 * <pre>
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date createDate(int year, int month, int day, int hour, int minute, int second) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}

	public static Date createDate(String dateInString, String formatDate) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
			Date date = formatter.parse(dateInString.trim());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Date createDate_DD_MM_YYYY(String dateInString) throws ParseException {
		return createDate(dateInString, DD_MM_YYYY);
	}

	public static Date createDate_YYYY_MM_DD_HH_MM_SS(String dateInString) {
		return createDate(dateInString, YYYY_MM_DD_HH_MM_SS);
	}

	public static Date createDate_EEEE_MMM_DD_YYYY_HH_MM_SS_A(String dateInString) {
		return createDate(dateInString, EEEE_MMM_DD_YYYY_HH_MM_SS_A);
	}

	public static Date createDate_E_MMM_DD_YYYY(String dateInString) {
		return createDate(dateInString, E_MMM_DD_YYYY);
	}

	public static Date createDate_MMM_DD_YYYY(String dateInString) {
		return createDate(dateInString, MMM_DD_YYYY);
	}

	public static Date createDate_DD_MMM_YYYY(String dateInString) {
		return createDate(dateInString, DD_MMM_YYYY);
	}

	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * Returns the current date <br>
	 * Example <br>
	 * 
	 * <pre>
	 * DateUtils.getToday();
	 * 
	 * <pre>
	 * 
	 * @return
	 */
	public static Date getToday() {
		return new Date();
	}

	/**
	 * Returns the current year <br>
	 * Example <br>
	 * 
	 * <pre>
	 * DateUtils.getCurrentYear();
	 * 
	 * <pre>
	 * @return
	 */
	public static int getCurrentYear() {
		calendar.setTime(getToday());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Returns the current month <br>
	 * For example, for <code>5 de Diciembre de 2011</code> returns <code>12</code> <br>
	 * 
	 * <pre>
	 * DateUtils.getCurrentMonth();
	 * 
	 * <pre>
	 * <ol>
	 * <li>January <code>1</code></li>
	 * <li>February <code>2</code></li>
	 * <li>March <code>3</code></li>
	 * <li>April <code>4</code></li>
	 * <li>May <code>5</code></li>
	 * <li>June <code>6</code></li>
	 * <li>July <code>7</code></li>
	 * <li>August <code>8</code	></li>
	 * <li>Semtember <code>9</code></li>
	 * <li>October <code>10</code></li>
	 * <li>November <code>11</code></li>
	 * <li>December <code>12</code></li>
	 * @return
	 */
	public static int getCurrentMonth() {
		calendar.setTime(getToday());
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * Returns the current day of current month <br>
	 * For example, for <code>5 de Diciembre de 2011</code> returns <code>5</code> <br>
	 * </br>
	 * 
	 * <pre>
	 * DateUtils.getCurrentDayOfMonth();
	 * 
	 * <pre>
	 * @return
	 */
	public static int getCurrentDayOfMonth() {
		calendar.setTime(getToday());
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Returns the year of the date specified <br>
	 * Example </br>
	 * 
	 * <pre>
	 * DateUtils.getYear(new Date());
	 * </pre>
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Returns the month of the date specified
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * Returns the day of the date specified
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Adds a number of days in the calendar, based on the specified date.
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * Adds a number of years in the calendar, based on the specified date.
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	/**
	 * Adds a number of months in the calendar, based on the specified date.
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * Decrements a number of days in the calendar from the specified date.
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date removeDays(Date date, int days) {
		return addDays(date, -days);
	}

	/**
	 * Returns a date with format dd/MM/yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDDMMYYYY(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}

	public static String formatDate(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYeay(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	/**
	 * 
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int month) {
		if (month == 2) {
			if (isLeapYeay(getCurrentYear())) {
				return 29;
			}
			return 28;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		default:
			return 30;
		}
	}

	/**
	 * 
	 * @param date
	 * @param hours
	 * @param minuts
	 * @param seconds
	 * @return
	 */
	public static Date changeTime(Date date, int hours, int minuts, int seconds) {
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minuts);
		calendar.set(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSunday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isMonday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isTuesday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWednesday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isThursday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFriday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSaturday(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	/**
	 * 
	 * @param currentDateTime
	 * @return
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(Date currentDateTime) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(currentDateTime);

		XMLGregorianCalendar createDate;
		try {
			createDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			createDate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			createDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}

		return createDate;
	}

	/**
	 * 
	 * @param currentDateTime
	 * @return
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendarDate(Date currentDateTime) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(currentDateTime);

		XMLGregorianCalendar createDate;
		try {
			createDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}

		return createDate;
	}

	/**
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static XMLGregorianCalendar createXMLGregorianCalendarDate(Date date, Time time) {
		try {
			XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			xmlCalendar.setDay(calendar.get(Calendar.DATE));
			xmlCalendar.setMonth(calendar.get(Calendar.MONTH) + 1);
			xmlCalendar.setYear(calendar.get(Calendar.YEAR));
			if (time != null) {
				calendar.setTime(time);
				xmlCalendar.setHour(calendar.get(Calendar.HOUR_OF_DAY));
				xmlCalendar.setMinute(calendar.get(calendar.MINUTE));
				xmlCalendar.setSecond(calendar.get(calendar.SECOND));
			}
			return xmlCalendar;
		} catch (DatatypeConfigurationException e) {

			return null;
		}
	}

	/**
	 * 
	 * @param xmlGregorianCalendar
	 * @return
	 */
	public static Date getDateFromXMLGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
		if (xmlGregorianCalendar != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth() - 1, xmlGregorianCalendar.getDay());
			return calendar.getTime();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param xmlGregorianCalendar
	 * @return
	 */
	public static Time getTimeFromXMLGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay(), xmlGregorianCalendar.getHour(), xmlGregorianCalendar.getMinute(), xmlGregorianCalendar.getSecond());
		return new Time(calendar.getTimeInMillis());
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long difDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		long diffDays = (cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long difWorkingDays(Date date1, Date date2) {
		long difDays = 0;
		while (date1.before(date2)) {
			if (!isSaturday(date1) && !isSunday(date1)) {
				difDays++;
			}
			date1 = addDays(date1, 1);
		}
		return difDays;
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date removeWorkingDays(Date date, int days) {
		if (date != null) {
			Date previous = date;
			for (int i = 0; i < days; i++) {
				previous = getPreviousWorkingDay(removeDays(previous, 1));
			}
			return previous;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public static Date getNextWorkingDay(Date date) {
		if (isSaturday(date)) {
			return addDays(date, 2);
		} else {
			if (isSunday(date)) {
				return addDays(date, 1);
			} else {
				return date;
			}
		}
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public static Date getPreviousWorkingDay(Date date) {
		if (isSaturday(date)) {
			return removeDays(date, 1);
		} else {
			if (isSunday(date)) {
				return removeDays(date, 2);
			} else {
				return date;
			}
		}
	}

	public static Time getTimeNow() {
		return new Time(System.currentTimeMillis());
	}
}
