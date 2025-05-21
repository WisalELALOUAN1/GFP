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
public class com_example_gfp_data_model_GoalRealmProxy extends com.example.gfp.data.model.Goal
    implements RealmObjectProxy, com_example_gfp_data_model_GoalRealmProxyInterface {

    static final class GoalColumnInfo extends ColumnInfo {
        long idObjColKey;
        long budgetTotalColKey;
        long sommeEpargneColKey;
        long descriptionColKey;
        long dateFinColKey;

        GoalColumnInfo(OsSchemaInfo schemaInfo) {
            super(5);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Goal");
            this.idObjColKey = addColumnDetails("idObj", "idObj", objectSchemaInfo);
            this.budgetTotalColKey = addColumnDetails("budgetTotal", "budgetTotal", objectSchemaInfo);
            this.sommeEpargneColKey = addColumnDetails("sommeEpargne", "sommeEpargne", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.dateFinColKey = addColumnDetails("dateFin", "dateFin", objectSchemaInfo);
        }

        GoalColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new GoalColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final GoalColumnInfo src = (GoalColumnInfo) rawSrc;
            final GoalColumnInfo dst = (GoalColumnInfo) rawDst;
            dst.idObjColKey = src.idObjColKey;
            dst.budgetTotalColKey = src.budgetTotalColKey;
            dst.sommeEpargneColKey = src.sommeEpargneColKey;
            dst.descriptionColKey = src.descriptionColKey;
            dst.dateFinColKey = src.dateFinColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private GoalColumnInfo columnInfo;
    private ProxyState<com.example.gfp.data.model.Goal> proxyState;

    com_example_gfp_data_model_GoalRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (GoalColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.gfp.data.model.Goal>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$idObj() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idObjColKey);
    }

    @Override
    public void realmSet$idObj(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idObj' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$budgetTotal() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.budgetTotalColKey);
    }

    @Override
    public void realmSet$budgetTotal(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.budgetTotalColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.budgetTotalColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$sommeEpargne() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.sommeEpargneColKey);
    }

    @Override
    public void realmSet$sommeEpargne(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.sommeEpargneColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.sommeEpargneColKey, value);
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
    public String realmGet$dateFin() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dateFinColKey);
    }

    @Override
    public void realmSet$dateFin(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'dateFin' to null.");
            }
            row.getTable().setString(columnInfo.dateFinColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'dateFin' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.dateFinColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "Goal", false, 5, 0);
        builder.addPersistedProperty(NO_ALIAS, "idObj", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "budgetTotal", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "sommeEpargne", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "dateFin", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static GoalColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new GoalColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Goal";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Goal";
    }

    @SuppressWarnings("cast")
    public static com.example.gfp.data.model.Goal createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.gfp.data.model.Goal obj = null;
        if (update) {
            Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
            GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class);
            long pkColumnKey = columnInfo.idObjColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("idObj")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("idObj"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_gfp_data_model_GoalRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("idObj")) {
                if (json.isNull("idObj")) {
                    obj = (io.realm.com_example_gfp_data_model_GoalRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Goal.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_gfp_data_model_GoalRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Goal.class, json.getInt("idObj"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idObj'.");
            }
        }

        final com_example_gfp_data_model_GoalRealmProxyInterface objProxy = (com_example_gfp_data_model_GoalRealmProxyInterface) obj;
        if (json.has("budgetTotal")) {
            if (json.isNull("budgetTotal")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'budgetTotal' to null.");
            } else {
                objProxy.realmSet$budgetTotal((double) json.getDouble("budgetTotal"));
            }
        }
        if (json.has("sommeEpargne")) {
            if (json.isNull("sommeEpargne")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'sommeEpargne' to null.");
            } else {
                objProxy.realmSet$sommeEpargne((double) json.getDouble("sommeEpargne"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("dateFin")) {
            if (json.isNull("dateFin")) {
                objProxy.realmSet$dateFin(null);
            } else {
                objProxy.realmSet$dateFin((String) json.getString("dateFin"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.gfp.data.model.Goal createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.gfp.data.model.Goal obj = new com.example.gfp.data.model.Goal();
        final com_example_gfp_data_model_GoalRealmProxyInterface objProxy = (com_example_gfp_data_model_GoalRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idObj")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idObj((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idObj' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("budgetTotal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$budgetTotal((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'budgetTotal' to null.");
                }
            } else if (name.equals("sommeEpargne")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$sommeEpargne((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'sommeEpargne' to null.");
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("dateFin")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dateFin((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dateFin(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idObj'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_gfp_data_model_GoalRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class), false, Collections.<String>emptyList());
        io.realm.com_example_gfp_data_model_GoalRealmProxy obj = new io.realm.com_example_gfp_data_model_GoalRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.gfp.data.model.Goal copyOrUpdate(Realm realm, GoalColumnInfo columnInfo, com.example.gfp.data.model.Goal object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.gfp.data.model.Goal) cachedRealmObject;
        }

        com.example.gfp.data.model.Goal realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
            long pkColumnKey = columnInfo.idObjColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_gfp_data_model_GoalRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.gfp.data.model.Goal copy(Realm realm, GoalColumnInfo columnInfo, com.example.gfp.data.model.Goal newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.Goal) cachedRealmObject;
        }

        com_example_gfp_data_model_GoalRealmProxyInterface unmanagedSource = (com_example_gfp_data_model_GoalRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idObjColKey, unmanagedSource.realmGet$idObj());
        builder.addDouble(columnInfo.budgetTotalColKey, unmanagedSource.realmGet$budgetTotal());
        builder.addDouble(columnInfo.sommeEpargneColKey, unmanagedSource.realmGet$sommeEpargne());
        builder.addString(columnInfo.descriptionColKey, unmanagedSource.realmGet$description());
        builder.addString(columnInfo.dateFinColKey, unmanagedSource.realmGet$dateFin());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_gfp_data_model_GoalRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.gfp.data.model.Goal object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class);
        long pkColumnKey = columnInfo.idObjColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        Table.nativeSetDouble(tableNativePtr, columnInfo.budgetTotalColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$budgetTotal(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.sommeEpargneColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$sommeEpargne(), false);
        String realmGet$description = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
        }
        String realmGet$dateFin = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$dateFin();
        if (realmGet$dateFin != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateFinColKey, objKey, realmGet$dateFin, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class);
        long pkColumnKey = columnInfo.idObjColKey;
        com.example.gfp.data.model.Goal object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Goal) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            Table.nativeSetDouble(tableNativePtr, columnInfo.budgetTotalColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$budgetTotal(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.sommeEpargneColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$sommeEpargne(), false);
            String realmGet$description = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
            }
            String realmGet$dateFin = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$dateFin();
            if (realmGet$dateFin != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dateFinColKey, objKey, realmGet$dateFin, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.gfp.data.model.Goal object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class);
        long pkColumnKey = columnInfo.idObjColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
        }
        cache.put(object, objKey);
        Table.nativeSetDouble(tableNativePtr, columnInfo.budgetTotalColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$budgetTotal(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.sommeEpargneColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$sommeEpargne(), false);
        String realmGet$description = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, objKey, false);
        }
        String realmGet$dateFin = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$dateFin();
        if (realmGet$dateFin != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateFinColKey, objKey, realmGet$dateFin, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateFinColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class);
        long pkColumnKey = columnInfo.idObjColKey;
        com.example.gfp.data.model.Goal object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Goal) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$idObj());
            }
            cache.put(object, objKey);
            Table.nativeSetDouble(tableNativePtr, columnInfo.budgetTotalColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$budgetTotal(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.sommeEpargneColKey, objKey, ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$sommeEpargne(), false);
            String realmGet$description = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, objKey, false);
            }
            String realmGet$dateFin = ((com_example_gfp_data_model_GoalRealmProxyInterface) object).realmGet$dateFin();
            if (realmGet$dateFin != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dateFinColKey, objKey, realmGet$dateFin, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dateFinColKey, objKey, false);
            }
        }
    }

    public static com.example.gfp.data.model.Goal createDetachedCopy(com.example.gfp.data.model.Goal realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.gfp.data.model.Goal unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.gfp.data.model.Goal();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.gfp.data.model.Goal) cachedObject.object;
            }
            unmanagedObject = (com.example.gfp.data.model.Goal) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_gfp_data_model_GoalRealmProxyInterface unmanagedCopy = (com_example_gfp_data_model_GoalRealmProxyInterface) unmanagedObject;
        com_example_gfp_data_model_GoalRealmProxyInterface realmSource = (com_example_gfp_data_model_GoalRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$idObj(realmSource.realmGet$idObj());
        unmanagedCopy.realmSet$budgetTotal(realmSource.realmGet$budgetTotal());
        unmanagedCopy.realmSet$sommeEpargne(realmSource.realmGet$sommeEpargne());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$dateFin(realmSource.realmGet$dateFin());

        return unmanagedObject;
    }

    static com.example.gfp.data.model.Goal update(Realm realm, GoalColumnInfo columnInfo, com.example.gfp.data.model.Goal realmObject, com.example.gfp.data.model.Goal newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_gfp_data_model_GoalRealmProxyInterface realmObjectTarget = (com_example_gfp_data_model_GoalRealmProxyInterface) realmObject;
        com_example_gfp_data_model_GoalRealmProxyInterface realmObjectSource = (com_example_gfp_data_model_GoalRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.gfp.data.model.Goal.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idObjColKey, realmObjectSource.realmGet$idObj());
        builder.addDouble(columnInfo.budgetTotalColKey, realmObjectSource.realmGet$budgetTotal());
        builder.addDouble(columnInfo.sommeEpargneColKey, realmObjectSource.realmGet$sommeEpargne());
        builder.addString(columnInfo.descriptionColKey, realmObjectSource.realmGet$description());
        builder.addString(columnInfo.dateFinColKey, realmObjectSource.realmGet$dateFin());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Goal = proxy[");
        stringBuilder.append("{idObj:");
        stringBuilder.append(realmGet$idObj());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{budgetTotal:");
        stringBuilder.append(realmGet$budgetTotal());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sommeEpargne:");
        stringBuilder.append(realmGet$sommeEpargne());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dateFin:");
        stringBuilder.append(realmGet$dateFin());
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
        com_example_gfp_data_model_GoalRealmProxy aGoal = (com_example_gfp_data_model_GoalRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aGoal.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aGoal.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aGoal.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
