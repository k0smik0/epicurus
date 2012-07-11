/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SyncObservableDelegate.java is part of Epicurus.
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.iubris.epicurus.observer.action.INotificationAction;

/**Implementation of a Observable that can be used as delegate for your own implementations.
 * This implementation notifies the observers in a synchronous fashion. Note that this
 * can cause trouble if you notify the observers while in a transactional context because the 
 * notification is then done also in the transaction. If this is a problem use the AsyncObservableDelegate
 * instead.
 * @param  The observer notification callback interface this observerable handles
 * 
 * @author Martin Fischer
 * @param <O>
 */
//@ThreadSafe
public class SyncObservableDelegate<O,NA extends INotificationAction<O>> implements IObservable<O,NA> {
	
	//protected final List<WeakReference<O>> observers = new CopyOnWriteArrayList<WeakReference<O>>();
	protected final List<WeakReference<O>> observers = new ArrayList<WeakReference<O>>();
	//private List<WeakReference<O>> observers = new LinkedList<WeakReference<O>>();
	

	@Override
	public void attachObserver(final O observer) {
		if (observer == null) throw new NullPointerException("observer is null");		
		observers.add(new WeakReference<O>(observer));
	}

	@Override
	public void detachObserver(final O observer) {
		if (observer == null) throw new NullPointerException("observer is null");
		for (Iterator<WeakReference<O>> iterator = observers.iterator(); iterator.hasNext(); )   {    
            WeakReference<O> ref = iterator.next();  
            O weakListener = ref.get();
            
            if ((weakListener == null) || (weakListener == observer)) {    
                //System.out.println("MyDialog has been garbage collected, cool!");    
                iterator.remove();  // Remove the empty weak reference from the list    
            }       
        }   
		/* can't remove directly on list - we have to use iterator
		for (WeakReference<O> weakRef : observers) {
			O weakListener = weakRef.get();
			if ((weakListener == null) || (weakListener == observer)) observers.remove(weakRef);
		}
		*/
	}

	@Override
	public void notifyObserver(final NA action) {
		for (WeakReference<O> weakRef : observers) {
			O weakListener = weakRef.get();
			if (weakListener == null) {
				observers.remove(weakRef);
			} else {
				action.execute(weakListener);
			}
		}
	}	
}
