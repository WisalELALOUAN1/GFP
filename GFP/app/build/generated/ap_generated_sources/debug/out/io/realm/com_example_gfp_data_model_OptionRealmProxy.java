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
public class com_example_gfp_data_model_OptionRealmProxy extends com.example.gfp.data.model.Option
    implements RealmObjectProxy, com_example_gfp_data_model_OptionRealmProxyInterface {

    static final class OptionColumnInfo extends ColumnInfo {
        long idOptionColKey;
        long optionNameColKey;

        OptionColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Option");
            this.idOptionColKey = addColumnDetails("idOption", "idOption", objectSchemaInfo);
            this.optionNameColKey = addColumnDetails("optionName", "optionName", objectSchemaInfo);
        }

        OptionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new OptionColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final OptionColumnInfo src = (OptionColumnInfo) rawSrc;
            final OptionColumnInfo dst = (OptionColumnInfo) rawDst;
            dst.idOptionColKey = src.idOptionColKey;
            dst.optionNameColKey = src.optionNameColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private OptionColumnInfo columnInfo;
    private ProxyState<com.example.gfp.data.model.Option> proxyState;

    com_example_gfp_data_model_OptionRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (OptionColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.gfp.data.model.Option>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idOption' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$optionName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.optionNameColKey);
    }

    @Override
    public void realmSet$optionName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.optionNameColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.optionNameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.optionNameColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.optionNameColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "Option", false, 2, 0);
        builder.addPersistedProperty(NO_ALIAS, "idOption", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "optionName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static OptionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new OptionColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Option";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Option";
    }

    @SuppressWarnings("cast")
    public static com.example.gfp.data.model.Option createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.gfp.data.model.Option obj = null;
        if (update) {
            Table table = realm.getTable(com.example.gfp.data.model.Option.class);
            OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class);
            long pkColumnKey = columnInfo.idOptionColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("idOption")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("idOption"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_gfp_data_model_OptionRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("idOption")) {
                if (json.isNull("idOption")) {
                    obj = (io.realm.com_example_gfp_data_model_OptionRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Option.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_gfp_data_model_OptionRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Option.class, json.getInt("idOption"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idOption'.");
            }
        }

        final com_example_gfp_data_model_OptionRealmProxyInterface objProxy = (com_example_gfp_data_model_OptionRealmProxyInterface) obj;
        if (json.has("optionName")) {
            if (json.isNull("optionName")) {
                objProxy.realmSet$optionName(null);
            } else {
                objProxy.realmSet$optionName((String) json.getString("optionName"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.gfp.data.model.Option createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.gfp.data.model.Option obj = new com.example.gfp.data.model.Option();
        final com_example_gfp_data_model_OptionRealmProxyInterface objProxy = (com_example_gfp_data_model_OptionRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idOption")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idOption((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idOption' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("optionName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$optionName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$optionName(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idOption'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_gfp_data_model_OptionRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class), false, Collections.<String>emptyList());
        io.realm.com_example_gfp_data_model_OptionRealmProxy obj = new io.realm.com_example_gfp_data_model_OptionRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.gfp.data.model.Option copyOrUpdate(Realm realm, OptionColumnInfo columnInfo, com.example.gfp.data.model.Option object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.gfp.data.model.Option) cachedRealmObject;
        }

        com.example.gfp.data.model.Option realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.gfp.data.model.Option.class);
            long pkColumnKey = columnInfo.idOptionColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_gfp_data_model_OptionRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.gfp.data.model.Option copy(Realm realm, OptionColumnInfo columnInfo, com.example.gfp.data.model.Option newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.Option) cachedRealmObject;
        }

        com_example_gfp_data_model_OptionRealmProxyInterface unmanagedSource = (com_example_gfp_data_model_OptionRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.gfp.data.model.Option.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idOptionColKey, unmanagedSource.realmGet$idOption());
        builder.addString(columnInfo.optionNameColKey, unmanagedSource.realmGet$optionName());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_gfp_data_model_OptionRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.gfp.data.model.Option object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class);
        long pkColumnKey = columnInfo.idOptionColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$optionName = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$optionName();
        if (realmGet$optionName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.optionNameColKey, objKey, realmGet$optionName, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class);
        long pkColumnKey = columnInfo.idOptionColKey;
        com.example.gfp.data.model.Option object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Option) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$optionName = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$optionName();
            if (realmGet$optionName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.optionNameColKey, objKey, realmGet$optionName, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.gfp.data.model.Option object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class);
        long pkColumnKey = columnInfo.idOptionColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
        }
        cache.put(object, objKey);
        String realmGet$optionName = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$optionName();
        if (realmGet$optionName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.optionNameColKey, objKey, realmGet$optionName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.optionNameColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class);
        long pkColumnKey = columnInfo.idOptionColKey;
        com.example.gfp.data.model.Option object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Option) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$idOption());
            }
            cache.put(object, objKey);
            String realmGet$optionName = ((com_example_gfp_data_model_OptionRealmProxyInterface) object).realmGet$optionName();
            if (realmGet$optionName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.optionNameColKey, objKey, realmGet$optionName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.optionNameColKey, objKey, false);
            }
        }
    }

    public static com.example.gfp.data.model.Option createDetachedCopy(com.example.gfp.data.model.Option realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.gfp.data.model.Option unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.gfp.data.model.Option();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.gfp.data.model.Option) cachedObject.object;
            }
            unmanagedObject = (com.example.gfp.data.model.Option) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_gfp_data_model_OptionRealmProxyInterface unmanagedCopy = (com_example_gfp_data_model_OptionRealmProxyInterface) unmanagedObject;
        com_example_gfp_data_model_OptionRealmProxyInterface realmSource = (com_example_gfp_data_model_OptionRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$idOption(realmSource.realmGet$idOption());
        unmanagedCopy.realmSet$optionName(realmSource.realmGet$optionName());

        return unmanagedObject;
    }

    static com.example.gfp.data.model.Option update(Realm realm, OptionColumnInfo columnInfo, com.example.gfp.data.model.Option realmObject, com.example.gfp.data.model.Option newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_gfp_data_model_OptionRealmProxyInterface realmObjectTarget = (com_example_gfp_data_model_OptionRealmProxyInterface) realmObject;
        com_example_gfp_data_model_OptionRealmProxyInterface realmObjectSource = (com_example_gfp_data_model_OptionRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.gfp.data.model.Option.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idOptionColKey, realmObjectSource.realmGet$idOption());
        builder.addString(columnInfo.optionNameColKey, realmObjectSource.realmGet$optionName());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Option = proxy[");
        stringBuilder.append("{idOption:");
        stringBuilder.append(realmGet$idOption());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{optionName:");
        stringBuilder.append(realmGet$optionName() != null ? realmGet$optionName() : "null");
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
        com_example_gfp_data_model_OptionRealmProxy aOption = (com_example_gfp_data_model_OptionRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aOption.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOption.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aOption.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
