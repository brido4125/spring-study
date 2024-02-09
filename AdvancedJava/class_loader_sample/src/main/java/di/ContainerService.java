package di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {
  public static <T> T getObject(Class<T> classType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    T instance = createInstance(classType);

    Arrays.stream(classType.getDeclaredFields())
            .forEach(field -> {
              if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                try {
                  field.set(instance, createInstance(field.getType()));
                } catch (IllegalAccessException e) {
                  throw new RuntimeException(e);
                }
              }
            });

    return instance;
  }

  private static <T> T createInstance(Class<T> object) {
    try {
      return object.getConstructor(null).newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
