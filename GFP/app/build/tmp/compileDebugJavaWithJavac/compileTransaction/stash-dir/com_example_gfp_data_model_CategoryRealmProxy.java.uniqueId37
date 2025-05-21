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
public class com_example_gfp_data_model_CategoryRealmProxy extends com.example.gfp.data.model.Category
    implements RealmObjectProxy, com_example_gfp_data_model_CategoryRealmProxyInterface {

    static final class CategoryColumnInfo extends ColumnInfo {
        long idCategoryColKey;
        long categoryNameColKey;
        long optionsColKey;

        CategoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Category");
            this.idCategoryColKey = addColumnDetails("idCategory", "idCategory", objectSchemaInfo);
            this.categoryNameColKey = addColumnDetails("categoryName", "categoryName", objectSchemaInfo);
            this.optionsColKey = addColumnDetails("options", "options", objectSchemaInfo);
        }

        CategoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new CategoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final CategoryColumnInfo src = (CategoryColumnInfo) rawSrc;
            final CategoryColumnInfo dst = (CategoryColumnInfo) rawDst;
            dst.idCategoryColKey = src.idCategoryColKey;
            dst.categoryNameColKey = src.categoryNameColKey;
            dst.optionsColKey = src.optionsColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private CategoryColumnInfo columnInfo;
    private ProxyState<com.example.gfp.data.model.Category> proxyState;
    private RealmList<com.example.gfp.data.model.Option> optionsRealmList;

    com_example_gfp_data_model_CategoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CategoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.gfp.data.model.Category>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idCategory' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$categoryName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.categoryNameColKey);
    }

    @Override
    public void realmSet$categoryName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.categoryNameColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.categoryNameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.categoryNameColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.categoryNameColKey, value);
    }

    @Override
    public RealmList<com.example.gfp.data.model.Option> realmGet$options() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (optionsRealmList != null) {
            return optionsRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.optionsColKey);
            optionsRealmList = new RealmList<com.example.gfp.data.model.Option>(com.example.gfp.data.model.Option.class, osList, proxyState.getRealm$realm());
            return optionsRealmList;
        }
    }

    @Override
    public void realmSet$options(RealmList<com.example.gfp.data.model.Option> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("options")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.example.gfp.data.model.Option> original = value;
                value = new RealmList<com.example.gfp.data.model.Option>();
                for (com.example.gfp.data.model.Option item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealmOrUpdate(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.optionsColKey);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.gfp.data.model.Option linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.gfp.data.model.Option linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "Category", false, 3, 0);
        builder.addPersistedProperty(NO_ALIAS, "idCategory", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "categoryName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty(NO_ALIAS, "options", RealmFieldType.LIST, "Option");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CategoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CategoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Category";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Category";
    }

    @SuppressWarnings("cast")
    public static com.example.gfp.data.model.Category createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.example.gfp.data.model.Category obj = null;
        if (update) {
            Table table = realm.getTable(com.example.gfp.data.model.Category.class);
            CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class);
            long pkColumnKey = columnInfo.idCategoryColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("idCategory")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("idCategory"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_gfp_data_model_CategoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("options")) {
                excludeFields.add("options");
            }
            if (json.has("idCategory")) {
                if (json.isNull("idCategory")) {
                    obj = (io.realm.com_example_gfp_data_model_CategoryRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Category.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_gfp_data_model_CategoryRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.Category.class, json.getInt("idCategory"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idCategory'.");
            }
        }

        final com_example_gfp_data_model_CategoryRealmProxyInterface objProxy = (com_example_gfp_data_model_CategoryRealmProxyInterface) obj;
        if (json.has("categoryName")) {
            if (json.isNull("categoryName")) {
                objProxy.realmSet$categoryName(null);
            } else {
                objProxy.realmSet$categoryName((String) json.getString("categoryName"));
            }
        }
        if (json.has("options")) {
            if (json.isNull("options")) {
                objProxy.realmSet$options(null);
            } else {
                objProxy.realmGet$options().clear();
                JSONArray array = json.getJSONArray("options");
                for (int i = 0; i < array.length(); i++) {
                    com.example.gfp.data.model.Option item = com_example_gfp_data_model_OptionRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$options().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.gfp.data.model.Category createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.gfp.data.model.Category obj = new com.example.gfp.data.model.Category();
        final com_example_gfp_data_model_CategoryRealmProxyInterface objProxy = (com_example_gfp_data_model_CategoryRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idCategory")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idCategory((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idCategory' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("categoryName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$categoryName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$categoryName(null);
                }
            } else if (name.equals("options")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$options(null);
                } else {
                    objProxy.realmSet$options(new RealmList<com.example.gfp.data.model.Option>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.gfp.data.model.Option item = com_example_gfp_data_model_OptionRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$options().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idCategory'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_gfp_data_model_CategoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class), false, Collections.<String>emptyList());
        io.realm.com_example_gfp_data_model_CategoryRealmProxy obj = new io.realm.com_example_gfp_data_model_CategoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.gfp.data.model.Category copyOrUpdate(Realm realm, CategoryColumnInfo columnInfo, com.example.gfp.data.model.Category object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.gfp.data.model.Category) cachedRealmObject;
        }

        com.example.gfp.data.model.Category realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.gfp.data.model.Category.class);
            long pkColumnKey = columnInfo.idCategoryColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_gfp_data_model_CategoryRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.gfp.data.model.Category copy(Realm realm, CategoryColumnInfo columnInfo, com.example.gfp.data.model.Category newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.Category) cachedRealmObject;
        }

        com_example_gfp_data_model_CategoryRealmProxyInterface unmanagedSource = (com_example_gfp_data_model_CategoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.gfp.data.model.Category.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idCategoryColKey, unmanagedSource.realmGet$idCategory());
        builder.addString(columnInfo.categoryNameColKey, unmanagedSource.realmGet$categoryName());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_gfp_data_model_CategoryRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        RealmList<com.example.gfp.data.model.Option> optionsUnmanagedList = unmanagedSource.realmGet$options();
        if (optionsUnmanagedList != null) {
            RealmList<com.example.gfp.data.model.Option> optionsManagedList = managedCopy.realmGet$options();
            optionsManagedList.clear();
            for (int i = 0; i < optionsUnmanagedList.size(); i++) {
                com.example.gfp.data.model.Option optionsUnmanagedItem = optionsUnmanagedList.get(i);
                com.example.gfp.data.model.Option cacheoptions = (com.example.gfp.data.model.Option) cache.get(optionsUnmanagedItem);
                if (cacheoptions != null) {
                    optionsManagedList.add(cacheoptions);
                } else {
                    optionsManagedList.add(com_example_gfp_data_model_OptionRealmProxy.copyOrUpdate(realm, (com_example_gfp_data_model_OptionRealmProxy.OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class), optionsUnmanagedItem, update, cache, flags));
                }
            }
        }

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.gfp.data.model.Category object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class);
        long pkColumnKey = columnInfo.idCategoryColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$categoryName = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$categoryName();
        if (realmGet$categoryName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryNameColKey, objKey, realmGet$categoryName, false);
        }

        RealmList<com.example.gfp.data.model.Option> optionsList = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$options();
        if (optionsList != null) {
            OsList optionsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.optionsColKey);
            for (com.example.gfp.data.model.Option optionsItem : optionsList) {
                Long cacheItemIndexoptions = cache.get(optionsItem);
                if (cacheItemIndexoptions == null) {
                    cacheItemIndexoptions = com_example_gfp_data_model_OptionRealmProxy.insert(realm, optionsItem, cache);
                }
                optionsOsList.addRow(cacheItemIndexoptions);
            }
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class);
        long pkColumnKey = columnInfo.idCategoryColKey;
        com.example.gfp.data.model.Category object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Category) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$categoryName = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$categoryName();
            if (realmGet$categoryName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.categoryNameColKey, objKey, realmGet$categoryName, false);
            }

            RealmList<com.example.gfp.data.model.Option> optionsList = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$options();
            if (optionsList != null) {
                OsList optionsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.optionsColKey);
                for (com.example.gfp.data.model.Option optionsItem : optionsList) {
                    Long cacheItemIndexoptions = cache.get(optionsItem);
                    if (cacheItemIndexoptions == null) {
                        cacheItemIndexoptions = com_example_gfp_data_model_OptionRealmProxy.insert(realm, optionsItem, cache);
                    }
                    optionsOsList.addRow(cacheItemIndexoptions);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.gfp.data.model.Category object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class);
        long pkColumnKey = columnInfo.idCategoryColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
        }
        cache.put(object, objKey);
        String realmGet$categoryName = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$categoryName();
        if (realmGet$categoryName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryNameColKey, objKey, realmGet$categoryName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.categoryNameColKey, objKey, false);
        }

        OsList optionsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.optionsColKey);
        RealmList<com.example.gfp.data.model.Option> optionsList = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$options();
        if (optionsList != null && optionsList.size() == optionsOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objectCount = optionsList.size();
            for (int i = 0; i < objectCount; i++) {
                com.example.gfp.data.model.Option optionsItem = optionsList.get(i);
                Long cacheItemIndexoptions = cache.get(optionsItem);
                if (cacheItemIndexoptions == null) {
                    cacheItemIndexoptions = com_example_gfp_data_model_OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                }
                optionsOsList.setRow(i, cacheItemIndexoptions);
            }
        } else {
            optionsOsList.removeAll();
            if (optionsList != null) {
                for (com.example.gfp.data.model.Option optionsItem : optionsList) {
                    Long cacheItemIndexoptions = cache.get(optionsItem);
                    if (cacheItemIndexoptions == null) {
                        cacheItemIndexoptions = com_example_gfp_data_model_OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                    }
                    optionsOsList.addRow(cacheItemIndexoptions);
                }
            }
        }

        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Category.class);
        long pkColumnKey = columnInfo.idCategoryColKey;
        com.example.gfp.data.model.Category object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.Category) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$idCategory());
            }
            cache.put(object, objKey);
            String realmGet$categoryName = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$categoryName();
            if (realmGet$categoryName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.categoryNameColKey, objKey, realmGet$categoryName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.categoryNameColKey, objKey, false);
            }

            OsList optionsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.optionsColKey);
            RealmList<com.example.gfp.data.model.Option> optionsList = ((com_example_gfp_data_model_CategoryRealmProxyInterface) object).realmGet$options();
            if (optionsList != null && optionsList.size() == optionsOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = optionsList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.example.gfp.data.model.Option optionsItem = optionsList.get(i);
                    Long cacheItemIndexoptions = cache.get(optionsItem);
                    if (cacheItemIndexoptions == null) {
                        cacheItemIndexoptions = com_example_gfp_data_model_OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                    }
                    optionsOsList.setRow(i, cacheItemIndexoptions);
                }
            } else {
                optionsOsList.removeAll();
                if (optionsList != null) {
                    for (com.example.gfp.data.model.Option optionsItem : optionsList) {
                        Long cacheItemIndexoptions = cache.get(optionsItem);
                        if (cacheItemIndexoptions == null) {
                            cacheItemIndexoptions = com_example_gfp_data_model_OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                        }
                        optionsOsList.addRow(cacheItemIndexoptions);
                    }
                }
            }

        }
    }

    public static com.example.gfp.data.model.Category createDetachedCopy(com.example.gfp.data.model.Category realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.gfp.data.model.Category unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.gfp.data.model.Category();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.gfp.data.model.Category) cachedObject.object;
            }
            unmanagedObject = (com.example.gfp.data.model.Category) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_gfp_data_model_CategoryRealmProxyInterface unmanagedCopy = (com_example_gfp_data_model_CategoryRealmProxyInterface) unmanagedObject;
        com_example_gfp_data_model_CategoryRealmProxyInterface realmSource = (com_example_gfp_data_model_CategoryRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$idCategory(realmSource.realmGet$idCategory());
        unmanagedCopy.realmSet$categoryName(realmSource.realmGet$categoryName());

        // Deep copy of options
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$options(null);
        } else {
            RealmList<com.example.gfp.data.model.Option> managedoptionsList = realmSource.realmGet$options();
            RealmList<com.example.gfp.data.model.Option> unmanagedoptionsList = new RealmList<com.example.gfp.data.model.Option>();
            unmanagedCopy.realmSet$options(unmanagedoptionsList);
            int nextDepth = currentDepth + 1;
            int size = managedoptionsList.size();
            for (int i = 0; i < size; i++) {
                com.example.gfp.data.model.Option item = com_example_gfp_data_model_OptionRealmProxy.createDetachedCopy(managedoptionsList.get(i), nextDepth, maxDepth, cache);
                unmanagedoptionsList.add(item);
            }
        }

        return unmanagedObject;
    }

    static com.example.gfp.data.model.Category update(Realm realm, CategoryColumnInfo columnInfo, com.example.gfp.data.model.Category realmObject, com.example.gfp.data.model.Category newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_gfp_data_model_CategoryRealmProxyInterface realmObjectTarget = (com_example_gfp_data_model_CategoryRealmProxyInterface) realmObject;
        com_example_gfp_data_model_CategoryRealmProxyInterface realmObjectSource = (com_example_gfp_data_model_CategoryRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.gfp.data.model.Category.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idCategoryColKey, realmObjectSource.realmGet$idCategory());
        builder.addString(columnInfo.categoryNameColKey, realmObjectSource.realmGet$categoryName());

        RealmList<com.example.gfp.data.model.Option> optionsUnmanagedList = realmObjectSource.realmGet$options();
        if (optionsUnmanagedList != null) {
            RealmList<com.example.gfp.data.model.Option> optionsManagedCopy = new RealmList<com.example.gfp.data.model.Option>();
            for (int i = 0; i < optionsUnmanagedList.size(); i++) {
                com.example.gfp.data.model.Option optionsItem = optionsUnmanagedList.get(i);
                com.example.gfp.data.model.Option cacheoptions = (com.example.gfp.data.model.Option) cache.get(optionsItem);
                if (cacheoptions != null) {
                    optionsManagedCopy.add(cacheoptions);
                } else {
                    optionsManagedCopy.add(com_example_gfp_data_model_OptionRealmProxy.copyOrUpdate(realm, (com_example_gfp_data_model_OptionRealmProxy.OptionColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Option.class), optionsItem, true, cache, flags));
                }
            }
            builder.addObjectList(columnInfo.optionsColKey, optionsManagedCopy);
        } else {
            builder.addObjectList(columnInfo.optionsColKey, new RealmList<com.example.gfp.data.model.Option>());
        }

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Category = proxy[");
        stringBuilder.append("{idCategory:");
        stringBuilder.append(realmGet$idCategory());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{categoryName:");
        stringBuilder.append(realmGet$categoryName() != null ? realmGet$categoryName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{options:");
        stringBuilder.append("RealmList<Option>[").append(realmGet$options().size()).append("]");
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
        com_example_gfp_data_model_CategoryRealmProxy aCategory = (com_example_gfp_data_model_CategoryRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aCategory.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCategory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aCategory.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
