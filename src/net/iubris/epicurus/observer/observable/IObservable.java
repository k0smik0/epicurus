/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * IObservable.java is part of Epicurus.
 * 
 * Epicurus is free software; you can redistribute it and/or modify
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
package net.iubris.epicurus.observer.observable;

import net.iubris.epicurus.observer.action.INotificationAction;

/**Interface for a generic observable according to the <> pattern.
 * Each observable has to implement this interface.
 * Different to the OBSERVER pattern the observer can implement a arbitrary notification
 * interface (Observer pattern: a fixed update() like interface) the observabe has to call.
 * 
 * @param  The interface the observer implements as notification interface.
 * 	O has to extend the IObserver marker interface.
 *  
 * @author Martin Fischer
 * @param <O>
 */
public interface IObservable<O, NA extends INotificationAction<O>> {
	/**Attaches a observer to the abservable. After attachment the observer gets informed about
	 * changes in the observable.
	 * @param a_observer The observer to attach to the observable
	 */
	void attachObserver(final O observer);
	
	/**Detaches a previously attached observer to the observable.
	 * After detachment the observer does no longer receive change notifications from the observable.
	 * @param a_observer The observer to detach from the observable
	 */
	void detachObserver(final O observer);
	
	/**Notifies all attached observers about changes in the observable.
	 * @param a_action Generic NotificationAction that encapsulates what notification method to call
	 * 	on the observer
	 */
	void notifyObserver(final NA action);
}
