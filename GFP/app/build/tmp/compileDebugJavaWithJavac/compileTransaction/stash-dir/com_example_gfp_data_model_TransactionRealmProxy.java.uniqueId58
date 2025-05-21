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
public class com_example_gfp_data_model_TransactionRealmProxy extends com.example.gfp.data.model.Transaction
    implements RealmObjectProxy, com_example_gfp_data_model_TransactionRealmProxyInterface {

    static final class TransactionColumnInfo extends ColumnInfo {
        long idTransactionColKey;
        long timeColKey;
        long descriptionColKey;
        long amountColKey;
        long typeColKey;
        long idUserCategoryColKey;

        TransactionColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Transaction");
            this.idTransactionColKey = addColumnDetails("idTransaction", "idTransaction", objectSchemaInfo);
            this.timeColKey = addColumnDetails("time", "time", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.amountColKey = addColumnDetails("amount", "amount", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.idUserCategoryColKey = addColumnDetails("idUserCategory", "idUserCategory", objectSchemaInfo);
        }

        TransactionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new TransactionColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final TransactionColumnInfo src = (TransactionColumnInfo) rawSrc;
            final TransactionColumnInfo dst = (TransactionColumnInfo) rawDst;
            dst.idTransactionColKey = src.idTransactionColKey;
            dst.timeColKey = src.timeColKey;
            dst.descriptionColKey = src.descriptionColKey;
            dst.amountColKey = src.amountColKey;
            dst.typeColKey = src.typeColKey;
            dst.idUserCategoryColKey = src.idUserCategoryColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private TransactionColumnInfo columnInfo;
    private ProxyState<com.example.gfp.data.model.Transaction> proxyState;

    com_example_gfp_data_model_TransactionRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TransactionColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.gfp.data.model.Transaction>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$idTransaction() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idTransactionColKey);
    }

    @Override
    public void realmSet$idTransaction(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idTransaction' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$time() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.timeColKey);
    }

    @Override
    public void realmSet$time(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
            }
            row.getTable().setString(columnInfo.timeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.timeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.descriptionColKey);
    }

    @Override
    public void realmSet$description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'description' to null.");
            }
            row.getTable().setString(columnInfo.descriptionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'description' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.descriptionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$amount() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.amountColKey);
    }

    @Override
    public void realmSet$amount(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.amountColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.amountColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.typeColKey);
    }

    @Override
    public void realmSet$type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
            }
            row.getTable().setString(columnInfo.typeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.typeColKey, value);
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
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idUserCategoryColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idUserCategoryColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "Transaction", false, 6, 0);
        builder.addPersistedProperty(NO_ALIAS, "idTransaction", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "time", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "amount", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "idUserCategory", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TransactionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TransactionColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Transaction";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Transaction";
    }

    @SuppressWarnings("cast")
    public static com.example.gfp.data.model.Transaction createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.gfp.data.model.Transaction obj = null;
        if (update) {
            Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
            TransactionColumnInfo columnInfo = (TransactionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class);
            long pkColumnKey = columnInfo.idTransactionColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("idTransaction")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("idTransaction"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_gfp_data_model_TransactionRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("idTransaction")) {
                if (json.isNull("idTransaction")) {
                    obj = (io.realm.com_example_gfp_data_model_TransactionRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Transaction.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_gfp_data_model_TransactionRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Transaction.class, json.getInt("idTransaction"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idTransaction'.");
            }
        }

        final com_example_gfp_data_model_TransactionRealmProxyInterface objProxy = (com_example_gfp_data_model_TransactionRealmProxyInterface) obj;
        if (json.has("time")) {
            if (json.isNull("time")) {
                objProxy.realmSet$time(null);
            } else {
                objProxy.realmSet$time((String) json.getString("time"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("amount")) {
            if (json.isNull("amount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
            } else {
                objProxy.realmSet$amount((double) json.getDouble("amount"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type((String) json.getString("type"));
            }
        }
        if (json.has("idUserCategory")) {
            if (json.isNull("idUserCategory")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'idUserCategory' to null.");
            } else {
                objProxy.realmSet$idUserCategory((int) json.getInt("idUserCategory"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.gfp.data.model.Transaction createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.gfp.data.model.Transaction obj = new com.example.gfp.data.model.Transaction();
        final com_example_gfp_data_model_TransactionRealmProxyInterface objProxy = (com_example_gfp_data_model_TransactionRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idTransaction")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idTransaction((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idTransaction' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("time")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$time((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$time(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("amount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$amount((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("idUserCategory")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idUserCategory((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idUserCategory' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idTransaction'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_gfp_data_model_TransactionRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class), false, Collections.<String>emptyList());
        io.realm.com_example_gfp_data_model_TransactionRealmProxy obj = new io.realm.com_example_gfp_data_model_TransactionRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.gfp.data.model.Transaction copyOrUpdate(Realm realm, TransactionColumnInfo columnInfo, com.example.gfp.data.model.Transaction object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.gfp.data.model.Transaction) cachedRealmObject;
        }

        com.example.gfp.data.model.Transaction realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
            long pkColumnKey = columnInfo.idTransactionColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_gfp_data_model_TransactionRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.gfp.data.model.Transaction copy(Realm realm, TransactionColumnInfo columnInfo, com.example.gfp.data.model.Transaction newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.Transaction) cachedRealmObject;
        }

        com_example_gfp_data_model_TransactionRealmProxyInterface unmanagedSource = (com_example_gfp_data_model_TransactionRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idTransactionColKey, unmanagedSource.realmGet$idTransaction());
        builder.addString(columnInfo.timeColKey, unmanagedSource.realmGet$time());
        builder.addString(columnInfo.descriptionColKey, unmanagedSource.realmGet$description());
        builder.addDouble(columnInfo.amountColKey, unmanagedSource.realmGet$amount());
        builder.addString(columnInfo.typeColKey, unmanagedSource.realmGet$type());
        builder.addInteger(columnInfo.idUserCategoryColKey, unmanagedSource.realmGet$idUserCategory());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_gfp_data_model_TransactionRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.gfp.data.model.Transaction object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
        long tableNativePtr = table.getNativePtr();
        TransactionColumnInfo columnInfo = (TransactionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class);
        long pkColumnKey = columnInfo.idTransactionColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$time = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeColKey, objKey, realmGet$time, false);
        }
        String realmGet$description = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$amount(), false);
        String realmGet$type = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeColKey, objKey, realmGet$type, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.idUserCategoryColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idUserCategory(), false);
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
        long tableNativePtr = table.getNativePtr();
        TransactionColumnInfo columnInfo = (TransactionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class);
        long pkColumnKey = columnInfo.idTransactionColKey;
        com.example.gfp.data.model.Transaction object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Transaction) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$time = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeColKey, objKey, realmGet$time, false);
            }
            String realmGet$description = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$amount(), false);
            String realmGet$type = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$type();
            if (realmGet$type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.typeColKey, objKey, realmGet$type, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.idUserCategoryColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idUserCategory(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.gfp.data.model.Transaction object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
        long tableNativePtr = table.getNativePtr();
        TransactionColumnInfo columnInfo = (TransactionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class);
        long pkColumnKey = columnInfo.idTransactionColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
        }
        cache.put(object, objKey);
        String realmGet$time = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeColKey, objKey, realmGet$time, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timeColKey, objKey, false);
        }
        String realmGet$description = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, objKey, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$amount(), false);
        String realmGet$type = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeColKey, objKey, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeColKey, objKey, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.idUserCategoryColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idUserCategory(), false);
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
        long tableNativePtr = table.getNativePtr();
        TransactionColumnInfo columnInfo = (TransactionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Transaction.class);
        long pkColumnKey = columnInfo.idTransactionColKey;
        com.example.gfp.data.model.Transaction object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Transaction) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idTransaction());
            }
            cache.put(object, objKey);
            String realmGet$time = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeColKey, objKey, realmGet$time, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.timeColKey, objKey, false);
            }
            String realmGet$description = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, objKey, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$amount(), false);
            String realmGet$type = ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$type();
            if (realmGet$type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.typeColKey, objKey, realmGet$type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.typeColKey, objKey, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.idUserCategoryColKey, objKey, ((com_example_gfp_data_model_TransactionRealmProxyInterface) object).realmGet$idUserCategory(), false);
        }
    }

    public static com.example.gfp.data.model.Transaction createDetachedCopy(com.example.gfp.data.model.Transaction realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.gfp.data.model.Transaction unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.gfp.data.model.Transaction();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.gfp.data.model.Transaction) cachedObject.object;
            }
            unmanagedObject = (com.example.gfp.data.model.Transaction) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_gfp_data_model_TransactionRealmProxyInterface unmanagedCopy = (com_example_gfp_data_model_TransactionRealmProxyInterface) unmanagedObject;
        com_example_gfp_data_model_TransactionRealmProxyInterface realmSource = (com_example_gfp_data_model_TransactionRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$idTransaction(realmSource.realmGet$idTransaction());
        unmanagedCopy.realmSet$time(realmSource.realmGet$time());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$amount(realmSource.realmGet$amount());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$idUserCategory(realmSource.realmGet$idUserCategory());

        return unmanagedObject;
    }

    static com.example.gfp.data.model.Transaction update(Realm realm, TransactionColumnInfo columnInfo, com.example.gfp.data.model.Transaction realmObject, com.example.gfp.data.model.Transaction newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_gfp_data_model_TransactionRealmProxyInterface realmObjectTarget = (com_example_gfp_data_model_TransactionRealmProxyInterface) realmObject;
        com_example_gfp_data_model_TransactionRealmProxyInterface realmObjectSource = (com_example_gfp_data_model_TransactionRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.gfp.data.model.Transaction.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idTransactionColKey, realmObjectSource.realmGet$idTransaction());
        builder.addString(columnInfo.timeColKey, realmObjectSource.realmGet$time());
        builder.addString(columnInfo.descriptionColKey, realmObjectSource.realmGet$description());
        builder.addDouble(columnInfo.amountColKey, realmObjectSource.realmGet$amount());
        builder.addString(columnInfo.typeColKey, realmObjectSource.realmGet$type());
        builder.addInteger(columnInfo.idUserCategoryColKey, realmObjectSource.realmGet$idUserCategory());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Transaction = proxy[");
        stringBuilder.append("{idTransaction:");
        stringBuilder.append(realmGet$idTransaction());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{time:");
        stringBuilder.append(realmGet$time());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{amount:");
        stringBuilder.append(realmGet$amount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{idUserCategory:");
        stringBuilder.append(realmGet$idUserCategory());
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
        com_example_gfp_data_model_TransactionRealmProxy aTransaction = (com_example_gfp_data_model_TransactionRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aTransaction.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransaction.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aTransaction.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
