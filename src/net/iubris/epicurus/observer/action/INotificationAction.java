/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * INotificationAction.java is part of Epicurus.
 * 
 * Eepicurus is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Epicurus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Epicurus ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.epicurus.observer.action;

/**Generic interface that describes a NotificationAction that is used by a observable to inform
 * the observer about changes in the observable. This interface is generic because there is no fixed
 * observer notification interface. 
 * @param  The type of the observer notification interface.
 * 
 * @author Martin Fischer
 * @param <O>
 */
public interface INotificationAction<O> {
	/**Execute the notification action which calls a method of the observer notification interface.
	 * @param a_observer The observer to inform about changes
	 */
	void execute(final O observer);
}
