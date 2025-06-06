package io.realm;


import android.util.JsonReader;
import io.realm.ImportFlag;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class MyRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(5);
        modelClasses.add(com.example.gfp.data.model.UserCategory.class);
        modelClasses.add(com.example.gfp.data.model.User.class);
        modelClasses.add(com.example.gfp.data.model.Transaction.class);
        modelClasses.add(com.example.gfp.data.model.Goal.class);
        modelClasses.add(com.example.gfp.data.model.Category.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(5);
        infoMap.put(com.example.gfp.data.model.UserCategory.class, io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.example.gfp.data.model.User.class, io.realm.com_example_gfp_data_model_UserRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.example.gfp.data.model.Transaction.class, io.realm.com_example_gfp_data_model_TransactionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.example.gfp.data.model.Goal.class, io.realm.com_example_gfp_data_model_GoalRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.example.gfp.data.model.Category.class, io.realm.com_example_gfp_data_model_CategoryRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return io.realm.com_example_gfp_data_model_UserRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return io.realm.com_example_gfp_data_model_TransactionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return io.realm.com_example_gfp_data_model_GoalRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return io.realm.com_example_gfp_data_model_CategoryRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return "UserCategory";
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return "User";
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return "Transaction";
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return "Goal";
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return "Category";
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public Class<? extends RealmModel> getClazzImpl(String className) {
        checkClassName(className);

        if (className.equals("UserCategory")) {
            return com.example.gfp.data.model.UserCategory.class;
        }
        if (className.equals("User")) {
            return com.example.gfp.data.model.User.class;
        }
        if (className.equals("Transaction")) {
            return com.example.gfp.data.model.Transaction.class;
        }
        if (className.equals("Goal")) {
            return com.example.gfp.data.model.Goal.class;
        }
        if (className.equals("Category")) {
            return com.example.gfp.data.model.Category.class;
        }
        throw getMissingProxyClassException(className);
    }

    @Override
    public boolean hasPrimaryKeyImpl(Class<? extends RealmModel> clazz) {
        return com.example.gfp.data.model.UserCategory.class.isAssignableFrom(clazz)
                || com.example.gfp.data.model.User.class.isAssignableFrom(clazz)
                || com.example.gfp.data.model.Transaction.class.isAssignableFrom(clazz)
                || com.example.gfp.data.model.Goal.class.isAssignableFrom(clazz)
                || com.example.gfp.data.model.Category.class.isAssignableFrom(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
                return clazz.cast(new io.realm.com_example_gfp_data_model_UserCategoryRealmProxy());
            }
            if (clazz.equals(com.example.gfp.data.model.User.class)) {
                return clazz.cast(new io.realm.com_example_gfp_data_model_UserRealmProxy());
            }
            if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
                return clazz.cast(new io.realm.com_example_gfp_data_model_TransactionRealmProxy());
            }
            if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
                return clazz.cast(new io.realm.com_example_gfp_data_model_GoalRealmProxy());
            }
            if (clazz.equals(com.example.gfp.data.model.Category.class)) {
                return clazz.cast(new io.realm.com_example_gfp_data_model_CategoryRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            com_example_gfp_data_model_UserCategoryRealmProxy.UserCategoryColumnInfo columnInfo = (com_example_gfp_data_model_UserCategoryRealmProxy.UserCategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class);
            return clazz.cast(io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.copyOrUpdate(realm, columnInfo, (com.example.gfp.data.model.UserCategory) obj, update, cache, flags));
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            com_example_gfp_data_model_UserRealmProxy.UserColumnInfo columnInfo = (com_example_gfp_data_model_UserRealmProxy.UserColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class);
            return clazz.cast(io.realm.com_example_gfp_data_model_UserRealmProxy.copyOrUpdate(realm, columnInfo, (com.example.gfp.data.model.User) obj, update, cache, flags));
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            com_example_gfp_data_model_TransactionRealmProxy.TransactionColumnInfo columnInfo = (com_example_gfp_data_model_TransactionRealmProxy.TransactionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class);
            return clazz.cast(io.realm.com_example_gfp_data_model_TransactionRealmProxy.copyOrUpdate(realm, columnInfo, (com.example.gfp.data.model.Transaction) obj, update, cache, flags));
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            com_example_gfp_data_model_GoalRealmProxy.GoalColumnInfo columnInfo = (com_example_gfp_data_model_GoalRealmProxy.GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class);
            return clazz.cast(io.realm.com_example_gfp_data_model_GoalRealmProxy.copyOrUpdate(realm, columnInfo, (com.example.gfp.data.model.Goal) obj, update, cache, flags));
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            com_example_gfp_data_model_CategoryRealmProxy.CategoryColumnInfo columnInfo = (com_example_gfp_data_model_CategoryRealmProxy.CategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class);
            return clazz.cast(io.realm.com_example_gfp_data_model_CategoryRealmProxy.copyOrUpdate(realm, columnInfo, (com.example.gfp.data.model.Category) obj, update, cache, flags));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public long insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.insert(realm, (com.example.gfp.data.model.UserCategory) object, cache);
        } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return io.realm.com_example_gfp_data_model_UserRealmProxy.insert(realm, (com.example.gfp.data.model.User) object, cache);
        } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return io.realm.com_example_gfp_data_model_TransactionRealmProxy.insert(realm, (com.example.gfp.data.model.Transaction) object, cache);
        } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return io.realm.com_example_gfp_data_model_GoalRealmProxy.insert(realm, (com.example.gfp.data.model.Goal) object, cache);
        } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return io.realm.com_example_gfp_data_model_CategoryRealmProxy.insert(realm, (com.example.gfp.data.model.Category) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
                io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.insert(realm, (com.example.gfp.data.model.UserCategory) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
                io.realm.com_example_gfp_data_model_UserRealmProxy.insert(realm, (com.example.gfp.data.model.User) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
                io.realm.com_example_gfp_data_model_TransactionRealmProxy.insert(realm, (com.example.gfp.data.model.Transaction) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
                io.realm.com_example_gfp_data_model_GoalRealmProxy.insert(realm, (com.example.gfp.data.model.Goal) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
                io.realm.com_example_gfp_data_model_CategoryRealmProxy.insert(realm, (com.example.gfp.data.model.Category) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
                    io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
                    io.realm.com_example_gfp_data_model_UserRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
                    io.realm.com_example_gfp_data_model_TransactionRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
                    io.realm.com_example_gfp_data_model_GoalRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
                    io.realm.com_example_gfp_data_model_CategoryRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public long insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.UserCategory) obj, cache);
        } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return io.realm.com_example_gfp_data_model_UserRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.User) obj, cache);
        } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return io.realm.com_example_gfp_data_model_TransactionRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.Transaction) obj, cache);
        } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return io.realm.com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.Goal) obj, cache);
        } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return io.realm.com_example_gfp_data_model_CategoryRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.Category) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
                io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.UserCategory) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
                io.realm.com_example_gfp_data_model_UserRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.User) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
                io.realm.com_example_gfp_data_model_TransactionRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.Transaction) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
                io.realm.com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.Goal) object, cache);
            } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
                io.realm.com_example_gfp_data_model_CategoryRealmProxy.insertOrUpdate(realm, (com.example.gfp.data.model.Category) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
                    io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
                    io.realm.com_example_gfp_data_model_UserRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
                    io.realm.com_example_gfp_data_model_TransactionRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
                    io.realm.com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
                    io.realm.com_example_gfp_data_model_CategoryRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_UserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_TransactionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_GoalRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_CategoryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_UserRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_TransactionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_GoalRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_CategoryRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_UserCategoryRealmProxy.createDetachedCopy((com.example.gfp.data.model.UserCategory) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_UserRealmProxy.createDetachedCopy((com.example.gfp.data.model.User) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_TransactionRealmProxy.createDetachedCopy((com.example.gfp.data.model.Transaction) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_GoalRealmProxy.createDetachedCopy((com.example.gfp.data.model.Goal) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return clazz.cast(io.realm.com_example_gfp_data_model_CategoryRealmProxy.createDetachedCopy((com.example.gfp.data.model.Category) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> boolean isEmbedded(Class<E> clazz) {
        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            return false;
        }
        if (clazz.equals(com.example.gfp.data.model.User.class)) {
            return false;
        }
        if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            return false;
        }
        if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            return false;
        }
        if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            return false;
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> void updateEmbeddedObject(Realm realm, E unmanagedObject, E managedObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) managedObject.getClass().getSuperclass();

        if (clazz.equals(com.example.gfp.data.model.UserCategory.class)) {
            throw getNotEmbeddedClassException("com.example.gfp.data.model.UserCategory");
        } else if (clazz.equals(com.example.gfp.data.model.User.class)) {
            throw getNotEmbeddedClassException("com.example.gfp.data.model.User");
        } else if (clazz.equals(com.example.gfp.data.model.Transaction.class)) {
            throw getNotEmbeddedClassException("com.example.gfp.data.model.Transaction");
        } else if (clazz.equals(com.example.gfp.data.model.Goal.class)) {
            throw getNotEmbeddedClassException("com.example.gfp.data.model.Goal");
        } else if (clazz.equals(com.example.gfp.data.model.Category.class)) {
            throw getNotEmbeddedClassException("com.example.gfp.data.model.Category");
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
