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
public class com_example_gfp_data_model_UserRealmProxy extends com.example.gfp.data.model.User
    implements RealmObjectProxy, com_example_gfp_data_model_UserRealmProxyInterface {

    static final class UserColumnInfo extends ColumnInfo {
        long idUserColKey;
        long emailColKey;
        long lastNameColKey;
        long firstNameColKey;
        long passwordColKey;
        long currencyColKey;
        long monthlyBudgetColKey;
        long firstLoginColKey;
        long goalsColKey;

        UserColumnInfo(OsSchemaInfo schemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("User");
            this.idUserColKey = addColumnDetails("idUser", "idUser", objectSchemaInfo);
            this.emailColKey = addColumnDetails("email", "email", objectSchemaInfo);
            this.lastNameColKey = addColumnDetails("lastName", "lastName", objectSchemaInfo);
            this.firstNameColKey = addColumnDetails("firstName", "firstName", objectSchemaInfo);
            this.passwordColKey = addColumnDetails("password", "password", objectSchemaInfo);
            this.currencyColKey = addColumnDetails("currency", "currency", objectSchemaInfo);
            this.monthlyBudgetColKey = addColumnDetails("monthlyBudget", "monthlyBudget", objectSchemaInfo);
            this.firstLoginColKey = addColumnDetails("firstLogin", "firstLogin", objectSchemaInfo);
            this.goalsColKey = addColumnDetails("goals", "goals", objectSchemaInfo);
        }

        UserColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UserColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UserColumnInfo src = (UserColumnInfo) rawSrc;
            final UserColumnInfo dst = (UserColumnInfo) rawDst;
            dst.idUserColKey = src.idUserColKey;
            dst.emailColKey = src.emailColKey;
            dst.lastNameColKey = src.lastNameColKey;
            dst.firstNameColKey = src.firstNameColKey;
            dst.passwordColKey = src.passwordColKey;
            dst.currencyColKey = src.currencyColKey;
            dst.monthlyBudgetColKey = src.monthlyBudgetColKey;
            dst.firstLoginColKey = src.firstLoginColKey;
            dst.goalsColKey = src.goalsColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private UserColumnInfo columnInfo;
    private ProxyState<com.example.gfp.data.model.User> proxyState;
    private RealmList<com.example.gfp.data.model.Goal> goalsRealmList;

    com_example_gfp_data_model_UserRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.gfp.data.model.User>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idUser' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$email() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.emailColKey);
    }

    @Override
    public void realmSet$email(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'email' to null.");
            }
            row.getTable().setString(columnInfo.emailColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'email' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.emailColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$lastName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.lastNameColKey);
    }

    @Override
    public void realmSet$lastName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'lastName' to null.");
            }
            row.getTable().setString(columnInfo.lastNameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'lastName' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.lastNameColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$firstName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.firstNameColKey);
    }

    @Override
    public void realmSet$firstName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'firstName' to null.");
            }
            row.getTable().setString(columnInfo.firstNameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'firstName' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.firstNameColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$password() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.passwordColKey);
    }

    @Override
    public void realmSet$password(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'password' to null.");
            }
            row.getTable().setString(columnInfo.passwordColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'password' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.passwordColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$currency() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.currencyColKey);
    }

    @Override
    public void realmSet$currency(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.currencyColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.currencyColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.currencyColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.currencyColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$monthlyBudget() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.monthlyBudgetColKey);
    }

    @Override
    public void realmSet$monthlyBudget(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.monthlyBudgetColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.monthlyBudgetColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$firstLogin() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.firstLoginColKey);
    }

    @Override
    public void realmSet$firstLogin(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.firstLoginColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.firstLoginColKey, value);
    }

    @Override
    public RealmList<com.example.gfp.data.model.Goal> realmGet$goals() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (goalsRealmList != null) {
            return goalsRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.goalsColKey);
            goalsRealmList = new RealmList<com.example.gfp.data.model.Goal>(com.example.gfp.data.model.Goal.class, osList, proxyState.getRealm$realm());
            return goalsRealmList;
        }
    }

    @Override
    public void realmSet$goals(RealmList<com.example.gfp.data.model.Goal> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("goals")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.example.gfp.data.model.Goal> original = value;
                value = new RealmList<com.example.gfp.data.model.Goal>();
                for (com.example.gfp.data.model.Goal item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealmOrUpdate(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.goalsColKey);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.gfp.data.model.Goal linkedObject = value.get(i);
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
                com.example.gfp.data.model.Goal linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "User", false, 9, 0);
        builder.addPersistedProperty(NO_ALIAS, "idUser", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "email", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "lastName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "firstName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "password", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "currency", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "monthlyBudget", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "firstLogin", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty(NO_ALIAS, "goals", RealmFieldType.LIST, "Goal");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "User";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "User";
    }

    @SuppressWarnings("cast")
    public static com.example.gfp.data.model.User createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.example.gfp.data.model.User obj = null;
        if (update) {
            Table table = realm.getTable(com.example.gfp.data.model.User.class);
            UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class);
            long pkColumnKey = columnInfo.idUserColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("idUser")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("idUser"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_gfp_data_model_UserRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("goals")) {
                excludeFields.add("goals");
            }
            if (json.has("idUser")) {
                if (json.isNull("idUser")) {
                    obj = (io.realm.com_example_gfp_data_model_UserRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.User.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_gfp_data_model_UserRealmProxy) realm.createObjectInternal(com.example.gfp.data.model.User.class, json.getInt("idUser"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idUser'.");
            }
        }

        final com_example_gfp_data_model_UserRealmProxyInterface objProxy = (com_example_gfp_data_model_UserRealmProxyInterface) obj;
        if (json.has("email")) {
            if (json.isNull("email")) {
                objProxy.realmSet$email(null);
            } else {
                objProxy.realmSet$email((String) json.getString("email"));
            }
        }
        if (json.has("lastName")) {
            if (json.isNull("lastName")) {
                objProxy.realmSet$lastName(null);
            } else {
                objProxy.realmSet$lastName((String) json.getString("lastName"));
            }
        }
        if (json.has("firstName")) {
            if (json.isNull("firstName")) {
                objProxy.realmSet$firstName(null);
            } else {
                objProxy.realmSet$firstName((String) json.getString("firstName"));
            }
        }
        if (json.has("password")) {
            if (json.isNull("password")) {
                objProxy.realmSet$password(null);
            } else {
                objProxy.realmSet$password((String) json.getString("password"));
            }
        }
        if (json.has("currency")) {
            if (json.isNull("currency")) {
                objProxy.realmSet$currency(null);
            } else {
                objProxy.realmSet$currency((String) json.getString("currency"));
            }
        }
        if (json.has("monthlyBudget")) {
            if (json.isNull("monthlyBudget")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'monthlyBudget' to null.");
            } else {
                objProxy.realmSet$monthlyBudget((double) json.getDouble("monthlyBudget"));
            }
        }
        if (json.has("firstLogin")) {
            if (json.isNull("firstLogin")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'firstLogin' to null.");
            } else {
                objProxy.realmSet$firstLogin((int) json.getInt("firstLogin"));
            }
        }
        if (json.has("goals")) {
            if (json.isNull("goals")) {
                objProxy.realmSet$goals(null);
            } else {
                objProxy.realmGet$goals().clear();
                JSONArray array = json.getJSONArray("goals");
                for (int i = 0; i < array.length(); i++) {
                    com.example.gfp.data.model.Goal item = com_example_gfp_data_model_GoalRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$goals().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.gfp.data.model.User createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.gfp.data.model.User obj = new com.example.gfp.data.model.User();
        final com_example_gfp_data_model_UserRealmProxyInterface objProxy = (com_example_gfp_data_model_UserRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idUser")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$idUser((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'idUser' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
            } else if (name.equals("lastName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lastName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$lastName(null);
                }
            } else if (name.equals("firstName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$firstName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$firstName(null);
                }
            } else if (name.equals("password")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$password((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$password(null);
                }
            } else if (name.equals("currency")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$currency((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$currency(null);
                }
            } else if (name.equals("monthlyBudget")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$monthlyBudget((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'monthlyBudget' to null.");
                }
            } else if (name.equals("firstLogin")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$firstLogin((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'firstLogin' to null.");
                }
            } else if (name.equals("goals")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$goals(null);
                } else {
                    objProxy.realmSet$goals(new RealmList<com.example.gfp.data.model.Goal>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.gfp.data.model.Goal item = com_example_gfp_data_model_GoalRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$goals().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idUser'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_gfp_data_model_UserRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class), false, Collections.<String>emptyList());
        io.realm.com_example_gfp_data_model_UserRealmProxy obj = new io.realm.com_example_gfp_data_model_UserRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.gfp.data.model.User copyOrUpdate(Realm realm, UserColumnInfo columnInfo, com.example.gfp.data.model.User object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.gfp.data.model.User) cachedRealmObject;
        }

        com.example.gfp.data.model.User realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.gfp.data.model.User.class);
            long pkColumnKey = columnInfo.idUserColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_gfp_data_model_UserRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.gfp.data.model.User copy(Realm realm, UserColumnInfo columnInfo, com.example.gfp.data.model.User newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.gfp.data.model.User) cachedRealmObject;
        }

        com_example_gfp_data_model_UserRealmProxyInterface unmanagedSource = (com_example_gfp_data_model_UserRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.gfp.data.model.User.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idUserColKey, unmanagedSource.realmGet$idUser());
        builder.addString(columnInfo.emailColKey, unmanagedSource.realmGet$email());
        builder.addString(columnInfo.lastNameColKey, unmanagedSource.realmGet$lastName());
        builder.addString(columnInfo.firstNameColKey, unmanagedSource.realmGet$firstName());
        builder.addString(columnInfo.passwordColKey, unmanagedSource.realmGet$password());
        builder.addString(columnInfo.currencyColKey, unmanagedSource.realmGet$currency());
        builder.addDouble(columnInfo.monthlyBudgetColKey, unmanagedSource.realmGet$monthlyBudget());
        builder.addInteger(columnInfo.firstLoginColKey, unmanagedSource.realmGet$firstLogin());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_gfp_data_model_UserRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        RealmList<com.example.gfp.data.model.Goal> goalsUnmanagedList = unmanagedSource.realmGet$goals();
        if (goalsUnmanagedList != null) {
            RealmList<com.example.gfp.data.model.Goal> goalsManagedList = managedCopy.realmGet$goals();
            goalsManagedList.clear();
            for (int i = 0; i < goalsUnmanagedList.size(); i++) {
                com.example.gfp.data.model.Goal goalsUnmanagedItem = goalsUnmanagedList.get(i);
                com.example.gfp.data.model.Goal cachegoals = (com.example.gfp.data.model.Goal) cache.get(goalsUnmanagedItem);
                if (cachegoals != null) {
                    goalsManagedList.add(cachegoals);
                } else {
                    goalsManagedList.add(com_example_gfp_data_model_GoalRealmProxy.copyOrUpdate(realm, (com_example_gfp_data_model_GoalRealmProxy.GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class), goalsUnmanagedItem, update, cache, flags));
                }
            }
        }

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.gfp.data.model.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class);
        long pkColumnKey = columnInfo.idUserColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$email = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, objKey, realmGet$email, false);
        }
        String realmGet$lastName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$lastName();
        if (realmGet$lastName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lastNameColKey, objKey, realmGet$lastName, false);
        }
        String realmGet$firstName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstName();
        if (realmGet$firstName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.firstNameColKey, objKey, realmGet$firstName, false);
        }
        String realmGet$password = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, objKey, realmGet$password, false);
        }
        String realmGet$currency = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$currency();
        if (realmGet$currency != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.currencyColKey, objKey, realmGet$currency, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.monthlyBudgetColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$monthlyBudget(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.firstLoginColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstLogin(), false);

        RealmList<com.example.gfp.data.model.Goal> goalsList = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$goals();
        if (goalsList != null) {
            OsList goalsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.goalsColKey);
            for (com.example.gfp.data.model.Goal goalsItem : goalsList) {
                Long cacheItemIndexgoals = cache.get(goalsItem);
                if (cacheItemIndexgoals == null) {
                    cacheItemIndexgoals = com_example_gfp_data_model_GoalRealmProxy.insert(realm, goalsItem, cache);
                }
                goalsOsList.addRow(cacheItemIndexgoals);
            }
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class);
        long pkColumnKey = columnInfo.idUserColKey;
        com.example.gfp.data.model.User object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.User) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$email = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, objKey, realmGet$email, false);
            }
            String realmGet$lastName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$lastName();
            if (realmGet$lastName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.lastNameColKey, objKey, realmGet$lastName, false);
            }
            String realmGet$firstName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstName();
            if (realmGet$firstName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.firstNameColKey, objKey, realmGet$firstName, false);
            }
            String realmGet$password = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$password();
            if (realmGet$password != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, objKey, realmGet$password, false);
            }
            String realmGet$currency = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$currency();
            if (realmGet$currency != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.currencyColKey, objKey, realmGet$currency, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.monthlyBudgetColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$monthlyBudget(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.firstLoginColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstLogin(), false);

            RealmList<com.example.gfp.data.model.Goal> goalsList = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$goals();
            if (goalsList != null) {
                OsList goalsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.goalsColKey);
                for (com.example.gfp.data.model.Goal goalsItem : goalsList) {
                    Long cacheItemIndexgoals = cache.get(goalsItem);
                    if (cacheItemIndexgoals == null) {
                        cacheItemIndexgoals = com_example_gfp_data_model_GoalRealmProxy.insert(realm, goalsItem, cache);
                    }
                    goalsOsList.addRow(cacheItemIndexgoals);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.gfp.data.model.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.gfp.data.model.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class);
        long pkColumnKey = columnInfo.idUserColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
        }
        cache.put(object, objKey);
        String realmGet$email = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, objKey, realmGet$email, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailColKey, objKey, false);
        }
        String realmGet$lastName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$lastName();
        if (realmGet$lastName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lastNameColKey, objKey, realmGet$lastName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.lastNameColKey, objKey, false);
        }
        String realmGet$firstName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstName();
        if (realmGet$firstName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.firstNameColKey, objKey, realmGet$firstName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.firstNameColKey, objKey, false);
        }
        String realmGet$password = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, objKey, realmGet$password, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.passwordColKey, objKey, false);
        }
        String realmGet$currency = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$currency();
        if (realmGet$currency != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.currencyColKey, objKey, realmGet$currency, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.currencyColKey, objKey, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.monthlyBudgetColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$monthlyBudget(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.firstLoginColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstLogin(), false);

        OsList goalsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.goalsColKey);
        RealmList<com.example.gfp.data.model.Goal> goalsList = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$goals();
        if (goalsList != null && goalsList.size() == goalsOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objectCount = goalsList.size();
            for (int i = 0; i < objectCount; i++) {
                com.example.gfp.data.model.Goal goalsItem = goalsList.get(i);
                Long cacheItemIndexgoals = cache.get(goalsItem);
                if (cacheItemIndexgoals == null) {
                    cacheItemIndexgoals = com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, goalsItem, cache);
                }
                goalsOsList.setRow(i, cacheItemIndexgoals);
            }
        } else {
            goalsOsList.removeAll();
            if (goalsList != null) {
                for (com.example.gfp.data.model.Goal goalsItem : goalsList) {
                    Long cacheItemIndexgoals = cache.get(goalsItem);
                    if (cacheItemIndexgoals == null) {
                        cacheItemIndexgoals = com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, goalsItem, cache);
                    }
                    goalsOsList.addRow(cacheItemIndexgoals);
                }
            }
        }

        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.gfp.data.model.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.User.class);
        long pkColumnKey = columnInfo.idUserColKey;
        com.example.gfp.data.model.User object = null;
        while (objects.hasNext()) {
            object = (com.example.gfp.data.model.User) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$idUser());
            }
            cache.put(object, objKey);
            String realmGet$email = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, objKey, realmGet$email, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.emailColKey, objKey, false);
            }
            String realmGet$lastName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$lastName();
            if (realmGet$lastName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.lastNameColKey, objKey, realmGet$lastName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.lastNameColKey, objKey, false);
            }
            String realmGet$firstName = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstName();
            if (realmGet$firstName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.firstNameColKey, objKey, realmGet$firstName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.firstNameColKey, objKey, false);
            }
            String realmGet$password = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$password();
            if (realmGet$password != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, objKey, realmGet$password, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.passwordColKey, objKey, false);
            }
            String realmGet$currency = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$currency();
            if (realmGet$currency != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.currencyColKey, objKey, realmGet$currency, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.currencyColKey, objKey, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.monthlyBudgetColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$monthlyBudget(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.firstLoginColKey, objKey, ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$firstLogin(), false);

            OsList goalsOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.goalsColKey);
            RealmList<com.example.gfp.data.model.Goal> goalsList = ((com_example_gfp_data_model_UserRealmProxyInterface) object).realmGet$goals();
            if (goalsList != null && goalsList.size() == goalsOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = goalsList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.example.gfp.data.model.Goal goalsItem = goalsList.get(i);
                    Long cacheItemIndexgoals = cache.get(goalsItem);
                    if (cacheItemIndexgoals == null) {
                        cacheItemIndexgoals = com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, goalsItem, cache);
                    }
                    goalsOsList.setRow(i, cacheItemIndexgoals);
                }
            } else {
                goalsOsList.removeAll();
                if (goalsList != null) {
                    for (com.example.gfp.data.model.Goal goalsItem : goalsList) {
                        Long cacheItemIndexgoals = cache.get(goalsItem);
                        if (cacheItemIndexgoals == null) {
                            cacheItemIndexgoals = com_example_gfp_data_model_GoalRealmProxy.insertOrUpdate(realm, goalsItem, cache);
                        }
                        goalsOsList.addRow(cacheItemIndexgoals);
                    }
                }
            }

        }
    }

    public static com.example.gfp.data.model.User createDetachedCopy(com.example.gfp.data.model.User realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.gfp.data.model.User unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.gfp.data.model.User();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.gfp.data.model.User) cachedObject.object;
            }
            unmanagedObject = (com.example.gfp.data.model.User) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_gfp_data_model_UserRealmProxyInterface unmanagedCopy = (com_example_gfp_data_model_UserRealmProxyInterface) unmanagedObject;
        com_example_gfp_data_model_UserRealmProxyInterface realmSource = (com_example_gfp_data_model_UserRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$idUser(realmSource.realmGet$idUser());
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$lastName(realmSource.realmGet$lastName());
        unmanagedCopy.realmSet$firstName(realmSource.realmGet$firstName());
        unmanagedCopy.realmSet$password(realmSource.realmGet$password());
        unmanagedCopy.realmSet$currency(realmSource.realmGet$currency());
        unmanagedCopy.realmSet$monthlyBudget(realmSource.realmGet$monthlyBudget());
        unmanagedCopy.realmSet$firstLogin(realmSource.realmGet$firstLogin());

        // Deep copy of goals
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$goals(null);
        } else {
            RealmList<com.example.gfp.data.model.Goal> managedgoalsList = realmSource.realmGet$goals();
            RealmList<com.example.gfp.data.model.Goal> unmanagedgoalsList = new RealmList<com.example.gfp.data.model.Goal>();
            unmanagedCopy.realmSet$goals(unmanagedgoalsList);
            int nextDepth = currentDepth + 1;
            int size = managedgoalsList.size();
            for (int i = 0; i < size; i++) {
                com.example.gfp.data.model.Goal item = com_example_gfp_data_model_GoalRealmProxy.createDetachedCopy(managedgoalsList.get(i), nextDepth, maxDepth, cache);
                unmanagedgoalsList.add(item);
            }
        }

        return unmanagedObject;
    }

    static com.example.gfp.data.model.User update(Realm realm, UserColumnInfo columnInfo, com.example.gfp.data.model.User realmObject, com.example.gfp.data.model.User newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_gfp_data_model_UserRealmProxyInterface realmObjectTarget = (com_example_gfp_data_model_UserRealmProxyInterface) realmObject;
        com_example_gfp_data_model_UserRealmProxyInterface realmObjectSource = (com_example_gfp_data_model_UserRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.gfp.data.model.User.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idUserColKey, realmObjectSource.realmGet$idUser());
        builder.addString(columnInfo.emailColKey, realmObjectSource.realmGet$email());
        builder.addString(columnInfo.lastNameColKey, realmObjectSource.realmGet$lastName());
        builder.addString(columnInfo.firstNameColKey, realmObjectSource.realmGet$firstName());
        builder.addString(columnInfo.passwordColKey, realmObjectSource.realmGet$password());
        builder.addString(columnInfo.currencyColKey, realmObjectSource.realmGet$currency());
        builder.addDouble(columnInfo.monthlyBudgetColKey, realmObjectSource.realmGet$monthlyBudget());
        builder.addInteger(columnInfo.firstLoginColKey, realmObjectSource.realmGet$firstLogin());

        RealmList<com.example.gfp.data.model.Goal> goalsUnmanagedList = realmObjectSource.realmGet$goals();
        if (goalsUnmanagedList != null) {
            RealmList<com.example.gfp.data.model.Goal> goalsManagedCopy = new RealmList<com.example.gfp.data.model.Goal>();
            for (int i = 0; i < goalsUnmanagedList.size(); i++) {
                com.example.gfp.data.model.Goal goalsItem = goalsUnmanagedList.get(i);
                com.example.gfp.data.model.Goal cachegoals = (com.example.gfp.data.model.Goal) cache.get(goalsItem);
                if (cachegoals != null) {
                    goalsManagedCopy.add(cachegoals);
                } else {
                    goalsManagedCopy.add(com_example_gfp_data_model_GoalRealmProxy.copyOrUpdate(realm, (com_example_gfp_data_model_GoalRealmProxy.GoalColumnInfo) realm.getSchema().getColumnInfo(com.example.gfp.data.model.Goal.class), goalsItem, true, cache, flags));
                }
            }
            builder.addObjectList(columnInfo.goalsColKey, goalsManagedCopy);
        } else {
            builder.addObjectList(columnInfo.goalsColKey, new RealmList<com.example.gfp.data.model.Goal>());
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
        StringBuilder stringBuilder = new StringBuilder("User = proxy[");
        stringBuilder.append("{idUser:");
        stringBuilder.append(realmGet$idUser());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lastName:");
        stringBuilder.append(realmGet$lastName());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{firstName:");
        stringBuilder.append(realmGet$firstName());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{password:");
        stringBuilder.append(realmGet$password());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{currency:");
        stringBuilder.append(realmGet$currency() != null ? realmGet$currency() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{monthlyBudget:");
        stringBuilder.append(realmGet$monthlyBudget());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{firstLogin:");
        stringBuilder.append(realmGet$firstLogin());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{goals:");
        stringBuilder.append("RealmList<Goal>[").append(realmGet$goals().size()).append("]");
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
        com_example_gfp_data_model_UserRealmProxy aUser = (com_example_gfp_data_model_UserRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aUser.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUser.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aUser.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
