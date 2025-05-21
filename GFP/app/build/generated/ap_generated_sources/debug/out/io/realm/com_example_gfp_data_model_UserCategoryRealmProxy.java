package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.NativeContext;
import io.realm.internal.OsList;
import io.realm.internal.OsMap;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSet;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.core.NativeRealmAny;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_example_gfp_data_model_UserCategoryRealmProxy extends com.example.gfp.data.model.UserCategory
    implements RealmObjectProxy, com_example_gfp_data_model_UserCategoryRealmProxyInterface {

    static final class UserCategoryColumnInfo extends ColumnInfo {
        long idUserCategoryColKey;
        long idUserColKey;
        long idCategoryColKey;
        long idOptionColKey;
        long recommendedBudgetColKey;
        long isFixedColKey;
        long finalBudgetColKey;

        UserCategoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("UserCategory");
            this.idUserCategoryColKey = addColumnDetails("idUserCategory", "idUserCategory", objectSchemaInfo);
            this.idUserColKey = addColumnDetails("idUser", "idUser", objectSchemaInfo);
            this.idCategoryColKey = addColumnDetails("idCategory", "idCategory", objectSchemaInfo);
            this.idOptionColKey = addColumnDetails("idOption", "idOption", objectSchemaInfo);
            this.recommendedBudgetColKey = addColumnDetails("recommendedBudget", "recommendedBudget", objectSchemaInfo);
            this.isFixedColKey = addColumnDetails("isFixed", "isFixed", objectSchemaInfo);
            this.finalBudgetColKey = addColumnDetails("finalBudget", "finalBudget", objectSchemaInfo);
        }

        UserCategoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UserCategoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UserCategoryColumnInfo src = (UserCategoryColumnInfo) rawSrc;
            final UserCategoryColumnInfo dst = (UserCategoryColumnInfo) rawDst;
            dst.idUserCategoryColKey = src.idUserCategoryColKey;
            dst.idUserColKey = src.idUserColKey;
            dst.idCategoryColKey = src.idCategoryColKey;
            dst.idOptionColKey = src.idOptionColKey;
            dst.recommendedBudgetColKey = src.recommendedBudgetColKey;
            dst.isFixedColKey = src.isFixedColKey;
            dst.finalBudgetColKey = src.finalBudgetColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private UserCategoryColumnInfo columnInfo;
    private ProxyState<com.example.gfp.data.model.UserCategory> proxyState;

    com_example_gfp_data_model_UserCategoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserCategoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.gfp.data.model.UserCategory>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$idUserCategory() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idUserCategoryColKey);
    }

    @Override
    public void realmSet$idUserCategory(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idUserCategory' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$idUser() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idUserColKey);
    }

    @Override
    public void realmSet$idUser(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idUserColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idUserColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$idCategory() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idCategoryColKey);
    }

    @Override
    public void realmSet$idCategory(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idCategoryColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idCategoryColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$idOption() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idOptionColKey);
    }

    @Override
    public void realmSet$idOption(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idOptionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idOptionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$recommendedBudget() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.recommendedBudgetColKey);
    }

    @Override
    public void realmSet$recommendedBudget(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.recommendedBudgetColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.recommendedBudgetColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$isFixed() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isFixedColKey);
    }

    @Override
    public void realmSet$isFixed(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isFixedColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isFixedColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$finalBudget() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.finalBudgetColKey);
    }

    @Override
    public void realmSet$finalBudget(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.finalBudgetColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.finalBudgetColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "UserCategory", false, 7, 0);
        builder.addPersistedProperty(NO_ALIAS, "idUserCategory", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "idUser", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "idCategory", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "idOption", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "recommendedBudget", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "isFixed", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "finalBudget", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserCategoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserCategoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "UserCategory";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "UserCategory";
    }

    @SuppressWarnings("cast")
    public static com.example.gfp.data.model.UserCategory createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.gfp.data.model.UserCategory obj = null;
        if (update) {
            Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
            UserCategoryColumnInfo columnInfo = (UserCategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class);
            long pkColumnKey = columnInfo.idUserCategoryColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("idUserCategory")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("idUserCategory"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_gfp_data_model_UserCategoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("idUserCategory")) {
                if (json.isNull("idUserCategory")) {
                    obj = (io.realm.com_example_gfp_data_model_UserCategoryRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.UserCategory.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_gfp_data_model_UserCategoryRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.UserCategory.class, json.getInt("idUserCategory"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idUserCategory'.");
            }
        }

        final com_example_gfp_data_model_UserCategoryRealmProxyInterface objProxy = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) obj;
        if (json.has("idUser")) {
            if (json.isNull("idUser")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'idUser' to null.");
            } else {
                objProxy.realmSet$idUser((int) json.getInt("idUser"));
            }
        }
        if (json.has("idCategory")) {
            if (json.isNull("idCategory")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'idCategory' to null.");
            } else {
                objProxy.realmSet$idCategory((int) json.getInt("idCategory"));
            }
        }
        if (json.has("idOption")) {
            if (json.isNull("idOption")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'idOption' to null.");
            } else {
                objProxy.realmSet$idOption((int) json.getInt("idOption"));
            }
        }
        if (json.has("recommendedBudget")) {
            if (json.isNull("recommendedBudget")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'recommendedBudget' to null.");
            } else {
                objProxy.realmSet$recommendedBudget((double) json.getDouble("recommendedBudget"));
            }
        }
        if (json.has("isFixed")) {
            if (json.isNull("isFixed")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isFixed' to null.");
            } else {
                objProxy.realmSet$isFixed((boolean) json.getBoolean("isFixed"));
            }
        }
        if (json.has("finalBudget")) {
            if (json.isNull("finalBudget")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'finalBudget' to null.");
            } else {
                objProxy.realmSet$finalBudget((double) json.getDouble("finalBudget"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.gfp.data.model.UserCategory createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.gfp.data.model.UserCategory obj = new com.example.gfp.data.model.UserCategory();
        final com_example_gfp_data_model_UserCategoryRealmProxyInterface objProxy = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idUserCategory")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idUserCategory((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idUserCategory' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("idUser")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idUser((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idUser' to null.");
                }
            } else if (name.equals("idCategory")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idCategory((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idCategory' to null.");
                }
            } else if (name.equals("idOption")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idOption((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idOption' to null.");
                }
            } else if (name.equals("recommendedBudget")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$recommendedBudget((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'recommendedBudget' to null.");
                }
            } else if (name.equals("isFixed")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$isFixed((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isFixed' to null.");
                }
            } else if (name.equals("finalBudget")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$finalBudget((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'finalBudget' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idUserCategory'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_gfp_data_model_UserCategoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class), false, Collections.<String>emptyList());
        io.realm.com_example_gfp_data_model_UserCategoryRealmProxy obj = new io.realm.com_example_gfp_data_model_UserCategoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.gfp.data.model.UserCategory copyOrUpdate(Realm realm, UserCategoryColumnInfo columnInfo, com.example.gfp.data.model.UserCategory object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.UserCategory) cachedRealmObject;
        }

        com.example.gfp.data.model.UserCategory realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
            long pkColumnKey = columnInfo.idUserCategoryColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_gfp_data_model_UserCategoryRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.gfp.data.model.UserCategory copy(Realm realm, UserCategoryColumnInfo columnInfo, com.example.gfp.data.model.UserCategory newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.UserCategory) cachedRealmObject;
        }

        com_example_gfp_data_model_UserCategoryRealmProxyInterface unmanagedSource = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idUserCategoryColKey, unmanagedSource.realmGet$idUserCategory());
        builder.addInteger(columnInfo.idUserColKey, unmanagedSource.realmGet$idUser());
        builder.addInteger(columnInfo.idCategoryColKey, unmanagedSource.realmGet$idCategory());
        builder.addInteger(columnInfo.idOptionColKey, unmanagedSource.realmGet$idOption());
        builder.addDouble(columnInfo.recommendedBudgetColKey, unmanagedSource.realmGet$recommendedBudget());
        builder.addBoolean(columnInfo.isFixedColKey, unmanagedSource.realmGet$isFixed());
        builder.addDouble(columnInfo.finalBudgetColKey, unmanagedSource.realmGet$finalBudget());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_gfp_data_model_UserCategoryRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.gfp.data.model.UserCategory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
        long tableNativePtr = table.getNativePtr();
        UserCategoryColumnInfo columnInfo = (UserCategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class);
        long pkColumnKey = columnInfo.idUserCategoryColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.idUserColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUser(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.idCategoryColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idCategory(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.idOptionColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idOption(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.recommendedBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$recommendedBudget(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isFixedColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$isFixed(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.finalBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$finalBudget(), false);
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
        long tableNativePtr = table.getNativePtr();
        UserCategoryColumnInfo columnInfo = (UserCategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class);
        long pkColumnKey = columnInfo.idUserCategoryColKey;
        com.example.gfp.data.model.UserCategory object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.UserCategory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.idUserColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUser(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.idCategoryColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idCategory(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.idOptionColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idOption(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.recommendedBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$recommendedBudget(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isFixedColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$isFixed(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.finalBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$finalBudget(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.gfp.data.model.UserCategory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
        long tableNativePtr = table.getNativePtr();
        UserCategoryColumnInfo columnInfo = (UserCategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class);
        long pkColumnKey = columnInfo.idUserCategoryColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
        }
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.idUserColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUser(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.idCategoryColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idCategory(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.idOptionColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idOption(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.recommendedBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$recommendedBudget(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isFixedColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$isFixed(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.finalBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$finalBudget(), false);
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
        long tableNativePtr = table.getNativePtr();
        UserCategoryColumnInfo columnInfo = (UserCategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.UserCategory.class);
        long pkColumnKey = columnInfo.idUserCategoryColKey;
        com.example.gfp.data.model.UserCategory object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.UserCategory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUserCategory());
            }
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.idUserColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idUser(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.idCategoryColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idCategory(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.idOptionColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$idOption(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.recommendedBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$recommendedBudget(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isFixedColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$isFixed(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.finalBudgetColKey, objKey, ((com_example_gfp_data_model_UserCategoryRealmProxyInterface) object).realmGet$finalBudget(), false);
        }
    }

    public static com.example.gfp.data.model.UserCategory createDetachedCopy(com.example.gfp.data.model.UserCategory realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.gfp.data.model.UserCategory unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.gfp.data.model.UserCategory();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.gfp.data.model.UserCategory) cachedObject.object;
            }
            unmanagedObject = (com.example.gfp.data.model.UserCategory) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_gfp_data_model_UserCategoryRealmProxyInterface unmanagedCopy = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) unmanagedObject;
        com_example_gfp_data_model_UserCategoryRealmProxyInterface realmSource = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$idUserCategory(realmSource.realmGet$idUserCategory());
        unmanagedCopy.realmSet$idUser(realmSource.realmGet$idUser());
        unmanagedCopy.realmSet$idCategory(realmSource.realmGet$idCategory());
        unmanagedCopy.realmSet$idOption(realmSource.realmGet$idOption());
        unmanagedCopy.realmSet$recommendedBudget(realmSource.realmGet$recommendedBudget());
        unmanagedCopy.realmSet$isFixed(realmSource.realmGet$isFixed());
        unmanagedCopy.realmSet$finalBudget(realmSource.realmGet$finalBudget());

        return unmanagedObject;
    }

    static com.example.gfp.data.model.UserCategory update(Realm realm, UserCategoryColumnInfo columnInfo, com.example.gfp.data.model.UserCategory realmObject, com.example.gfp.data.model.UserCategory newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_gfp_data_model_UserCategoryRealmProxyInterface realmObjectTarget = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) realmObject;
        com_example_gfp_data_model_UserCategoryRealmProxyInterface realmObjectSource = (com_example_gfp_data_model_UserCategoryRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.gfp.data.model.UserCategory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idUserCategoryColKey, realmObjectSource.realmGet$idUserCategory());
        builder.addInteger(columnInfo.idUserColKey, realmObjectSource.realmGet$idUser());
        builder.addInteger(columnInfo.idCategoryColKey, realmObjectSource.realmGet$idCategory());
        builder.addInteger(columnInfo.idOptionColKey, realmObjectSource.realmGet$idOption());
        builder.addDouble(columnInfo.recommendedBudgetColKey, realmObjectSource.realmGet$recommendedBudget());
        builder.addBoolean(columnInfo.isFixedColKey, realmObjectSource.realmGet$isFixed());
        builder.addDouble(columnInfo.finalBudgetColKey, realmObjectSource.realmGet$finalBudget());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UserCategory = proxy[");
        stringBuilder.append("{idUserCategory:");
        stringBuilder.append(realmGet$idUserCategory());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{idUser:");
        stringBuilder.append(realmGet$idUser());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{idCategory:");
        stringBuilder.append(realmGet$idCategory());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{idOption:");
        stringBuilder.append(realmGet$idOption());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{recommendedBudget:");
        stringBuilder.append(realmGet$recommendedBudget());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isFixed:");
        stringBuilder.append(realmGet$isFixed());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{finalBudget:");
        stringBuilder.append(realmGet$finalBudget());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long objKey = proxyState.getRow$realm().getObjectKey();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (objKey ^ (objKey >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_example_gfp_data_model_UserCategoryRealmProxy aUserCategory = (com_example_gfp_data_model_UserCategoryRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aUserCategory.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserCategory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aUserCategory.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
