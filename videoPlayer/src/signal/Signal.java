package signal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class Signal{
	private List<Method> methods;
	private Class<?>[] parmeterTypes;
	private List<Object> objects;
	
	public Signal(Class<?>... parmeterTypes) {
		this.parmeterTypes = parmeterTypes;
	}
	
	public void link(Object object, String methodName) {
		this.objects.add(object);
		Method[] methods = object.getClass().getDeclaredMethods();
		for(Method m: methods) {
			if(m.getName().equals(methodName) && Modifier.isPublic(m.getModifiers())) {
				Class<?>[] parmeterTypes = m.getParameterTypes();
				if(Arrays.equals(this.parmeterTypes, parmeterTypes)) {
					this.methods.add(m);
					break;
				}

				
			}
		}
	}
	
	public void shoot(Object... value) {
		try {
			for(int i = 0; i < methods.size(); i ++) {
				Method method = methods.get(i);
				method.invoke(objects.get(i), value);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
