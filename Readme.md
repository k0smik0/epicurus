#Epicurus
  
This is a patched and enhanced version of [Martin Fischer Observer][0] implementation.

###1.	Patch:
SyncObservableDelegate removes its observers directly from its ArrayList observers field, using observers.remove(item).  
But, according to [javadoc][1], we must use Iterator in order to obtain this stuff (and avoid exception).  
###2. Enhance:  
Generics parameters are not only "O extends Observer", but also "NA extends INotificationAction<O>",
in order to allow a more accurate classes topology: so you can use your own interface for notification action.  
		

Enjoy it !


[0]: http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html
[1]: http://www.jroller.com/martin_fischer/entry/a_generic_java_observer_pattern
