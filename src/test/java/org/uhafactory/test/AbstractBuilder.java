package org.uhafactory.test;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Sets;
import org.springframework.util.CollectionUtils;

import static java.util.Collections.emptySet;

/**
 * Created by lineplus on 2017. 4. 18..
 */
public abstract class AbstractBuilder<T extends Object> {

    public T build() {
        try {

            Object targetObject = ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            Field[] fields = this.getClass().getDeclaredFields();

            for (Field field : fields) {
                // TODO list, map, set 처리
                //			if (field.getType().getSuperclass() == null) {
                //				continue;
                //			}

                apply(this, field, targetObject);
            }
            return (T)targetObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void apply(Object source, Field field, Object target) throws Exception {
        for (SupportClassType supportClassType : SupportClassType.values()) {
            if (!supportClassType.isSupportType(field)) {
                continue;
            }
            field.setAccessible(true);
            Object sourceValue = supportClassType.getSourceValue(field, source, target);
            copyField(field, sourceValue, target);
            return;
        }
    }

    public void copyField(Field field, Object source, Object target) throws Exception {
        /**
         * jacoco javaagent가 $jacocoData, $jacocoInit 을 생성함.
         * http://www.eclemma.org/jacoco/trunk/doc/faq.html
         */
        if (field.getType() == Class.forName("[Z")) {
            return;
        }
        Field targetField = getDeclaredFieldIncludeSuperClass(target.getClass(), field.getName());
        targetField.setAccessible(true);
        targetField.set(target, source);
    }

    private Field getDeclaredFieldIncludeSuperClass(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        }
        catch(NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) {
                return getDeclaredFieldIncludeSuperClass(clazz.getSuperclass(), name);
            }
            throw new RuntimeException(e);
        }

    }

    enum SupportClassType {
        ABSTRACT_BUILDER {
            @Override
            boolean isSupportType(Field field) {
                if (field.getType().getSuperclass() == null) {
                    return false;
                }
                return AbstractBuilder.class.isAssignableFrom(field.getType().getSuperclass());
            }

            @Override
            Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception {
                AbstractBuilder buildHelper = (AbstractBuilder)field.get(sourceObject);
                if (buildHelper == null) {
                    return null;
                }
                return buildHelper.build();
            }
        },
        PRIMITIVE_OBJECT {
            @Override
            boolean isSupportType(Field field) {
                if (field.getType().getSuperclass() == null) {
                    return false;
                }
                return Object.class.isAssignableFrom(field.getType().getSuperclass());
            }

            @Override
            Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception {
                return field.get(sourceObject);
            }
        },
        PRIMITIVE {
            @Override
            boolean isSupportType(Field field) {
                if (field.getType().isPrimitive()) {
                    return true;
                }
                return false;
            }

            @Override
            Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception {
                return field.get(sourceObject);
            }
        },

        LIST {
            private List TEST = Collections.emptyList();

            @Override
            boolean isSupportType(Field field) {
                return field.getType().isInstance(TEST);
            }

            @Override
            Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception {
                List list = (List)field.get(sourceObject);

                if(!isBuilderCollection(list)){
                    return list;
                }
                return buildCollection(list);
            }
        },
        MAP {
            @Override
            boolean isSupportType(Field field) {
                return false;
            }

            @Override
            Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception {
                return false;
            }

        },
        SET {
            @Override
            boolean isSupportType(Field field) {
                return field.getType().isInstance(emptySet());
            }

            @Override
            Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception {
                Set set = (Set)field.get(sourceObject);
                if(!isBuilderCollection(set)){
                    return set;
                }

                return Sets.newLinkedHashSet(buildCollection(set));
            }
        };

        private static boolean isBuilderCollection(Collection collection) {
            if(CollectionUtils.isEmpty(collection) || !AbstractBuilder.class.isAssignableFrom(collection.iterator().next().getClass().getSuperclass())) {
                return false;
            }
            return true;
        }

        private static List buildCollection(Collection<AbstractBuilder> collection) {
            return collection.stream().map( b -> b.build()).collect(Collectors.toList());
        }

        abstract boolean isSupportType(Field field);

        abstract Object getSourceValue(Field field, Object sourceObject, Object target) throws Exception;

    }

}

