package io.realm;


public interface com_example_gfp_data_model_UserRealmProxyInterface {
    public int realmGet$idUser();
    public void realmSet$idUser(int value);
    public String realmGet$email();
    public void realmSet$email(String value);
    public String realmGet$lastName();
    public void realmSet$lastName(String value);
    public String realmGet$firstName();
    public void realmSet$firstName(String value);
    public String realmGet$password();
    public void realmSet$password(String value);
    public String realmGet$currency();
    public void realmSet$currency(String value);
    public double realmGet$monthlyBudget();
    public void realmSet$monthlyBudget(double value);
    public int realmGet$firstLogin();
    public void realmSet$firstLogin(int value);
    public RealmList<com.example.gfp.data.model.Goal> realmGet$goals();
    public void realmSet$goals(RealmList<com.example.gfp.data.model.Goal> value);
}
