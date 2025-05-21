import pandas as pd
import numpy as np
import tensorflow as tf
from sklearn.preprocessing import LabelEncoder, MinMaxScaler
from datetime import datetime
from tensorflow.keras.layers import Input, Embedding, Flatten, Concatenate, Dense, Dropout
from tensorflow.keras.models import Model

# ── 1. Charger et préparer les données ──────────────────────────────────

df1 = pd.read_csv("personal_transactions.csv")
df2 = pd.read_csv("nouvelles_transactions_200.csv")

df1['Date'] = pd.to_datetime(df1['Date'], errors='coerce', format='mixed')
df2['Date'] = pd.to_datetime(df2['Date'], errors='coerce', format='%m/%d/%Y')

data = pd.concat([df1, df2], ignore_index=True)
data.loc[data['Date'] < pd.Timestamp('2025-01-01'), 'Amount'] *= 10.5
data = data[data['Transaction Type'].str.lower() == 'debit']

data['Month'] = data['Date'].dt.month
data['Day_of_Week'] = data['Date'].dt.dayofweek

enc_cat = LabelEncoder().fit(data['Category'])
enc_acc = LabelEncoder().fit(data['Account Name'])
data['Category_Encoded'] = enc_cat.transform(data['Category'])
data['Account_Encoded']  = enc_acc.transform(data['Account Name'])

top10 = data['Category'].value_counts().head(10).index.tolist()
data = data[data['Category'].isin(top10)]

scaler = MinMaxScaler().fit(data[['Amount']])
data['Normalized_Amount'] = scaler.transform(data[['Amount']])

X = data[['Category_Encoded', 'Month', 'Day_of_Week', 'Account_Encoded']]
y = data['Normalized_Amount']

# ── 2. Définition et entraînement du modèle ─────────────────────────

i_cat   = Input(shape=(1,))
i_month = Input(shape=(1,))
i_day   = Input(shape=(1,))
i_acc   = Input(shape=(1,))

emb = Embedding(input_dim=len(enc_cat.classes_), output_dim=4)(i_cat)
flat = Flatten()(emb)
x = Concatenate()([flat, i_month, i_day, i_acc])
x = Dense(64, activation='relu')(x)
x = Dropout(0.2)(x)
x = Dense(32, activation='relu')(x)
o = Dense(1, activation='sigmoid')(x)

model = Model(inputs=[i_cat, i_month, i_day, i_acc], outputs=o)
model.compile(optimizer='adam', loss='mse', metrics=['mae'])
model.fit(
    [X['Category_Encoded'], X['Month'], X['Day_of_Week'], X['Account_Encoded']],
    y,
    epochs=20,
    batch_size=16,
    validation_split=0.2,
    verbose=1
)

# ── 3. Classification automatique des catégories ────────────────────────

default_necessities = {
    'Groceries', 'Food & Dining', 'Gas & Fuel', 'Utilities',
    'Internet', 'Mobile Phone', 'Health & Fitness'
}

fixed_payments = {
    'Auto Insurance', 'Credit Card Payment', 'Mortgage & Rent'
}

all_categories = list(enc_cat.classes_)
default_entertainment = set(all_categories) - default_necessities - fixed_payments

# ── 4. Fonction de prédiction et allocation ────────────────────────

def predict_and_allocate(budget: float, user_cats: list) -> dict:
    encs = enc_cat.transform(user_cats)
    now = datetime.now()
    month, dow = now.month, now.weekday()
    acc = enc_acc.transform(['Silver Card'])[0]

    preds_norm = model.predict([
        np.array(encs),
        np.full(len(encs), month),
        np.full(len(encs), dow),
        np.full(len(encs), acc)
    ])
    preds_dh = scaler.inverse_transform(preds_norm).flatten()
    raw = dict(zip(user_cats, np.maximum(preds_dh, 0.0)))

    necessities   = [c for c in user_cats if c in default_necessities]
    entertainment = [c for c in user_cats if c in default_entertainment]
    fixed         = [c for c in user_cats if c in fixed_payments]

    excluded_fixed = [c for c in fixed_payments if c not in user_cats]
    for c in excluded_fixed:
        print(f"⛔ {c} non sélectionné → Allocation : 0 DH")

    sum_need = sum(raw[c] for c in necessities) or 1.0
    sum_ent  = sum(raw[c] for c in entertainment) or 1.0
    sum_fixed = sum(raw[c] for c in fixed) or 1.0

    weights = {'necessities': 0.5, 'savings': 0.2, 'entertainment': 0.3}
    alloc = {}

    for c in necessities:
        alloc[c] = raw[c] / sum_need * (budget * weights['necessities'])
    for c in fixed:
        alloc[c] = raw[c] / sum_fixed * (budget * weights['savings'])
    for c in entertainment:
        alloc[c] = raw[c] / sum_ent * (budget * weights['entertainment'])

    for c in excluded_fixed:
        alloc[c] = 0.0

    return alloc

# ── 5. Interaction utilisateur ───────────────────────────

if __name__ == "__main__":
    B = float(input("Entrez votre budget total (DH) : "))
    print("Catégories disponibles :", all_categories)
    uc = input("Choisissez vos catégories (virgule) : ")
    user_cats = [c.strip() for c in uc.split(",") if c.strip() in all_categories]

    allocation = predict_and_allocate(B, user_cats)

    print("\n💡 Répartition finale :")
    for cat, amt in allocation.items():
        print(f"- {cat:<25}: {amt:7.2f} DH")
# ── 3. Test de prédiction ────────────────────────────────────────────────

def test_prediction():
    budget = 5000  # DH
    categories_exemple = ['Groceries', 'Internet', 'Restaurants', 'Gas & Fuel', 'Mobile Phone']

    # S'assurer que les catégories existent
    available = list(enc_cat.classes_)
    categories_valide = [cat for cat in categories_exemple if cat in available]
    
    if not categories_valide:
        print("❌ Aucune catégorie valide trouvée pour le test.")
        return
    
    # Encodage
    encoded_cats = enc_cat.transform(categories_valide)
    now = datetime.now()
    month = now.month
    dow = now.weekday()
    acc = enc_acc.transform([enc_acc.classes_[0]])[0]  # utiliser le premier compte

    # Prédictions normalisées
    preds_norm = model.predict([
        np.array(encoded_cats),
        np.full(len(encoded_cats), month),
        np.full(len(encoded_cats), dow),
        np.full(len(encoded_cats), acc)
    ])
    
    # Transformation inverse vers DH
    preds_dh = scaler.inverse_transform(preds_norm).flatten()
    
    # Répartition brute
    total = preds_dh.sum()
    allocations = {cat: (val / total) * budget for cat, val in zip(categories_valide, preds_dh)}

    print(f"\n🧪 Test de répartition budgétaire pour un budget de {budget:.2f} DH")
    print("------------------------------------------------------------")
    for cat, montant in allocations.items():
        print(f"- {cat:<20} : {montant:7.2f} DH")
    print("------------------------------------------------------------")
    print(f"✅ Total alloué : {sum(allocations.values()):.2f} DH\n")


# Appeler la fonction de test
if __name__ == "__main__":
    test_prediction()
# Sauvegarde du modèle
model.save("budget_model.h5")

# Conversion en TFLite
converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()

with open("budget_model.tflite", "wb") as f:
    f.write(tflite_model)