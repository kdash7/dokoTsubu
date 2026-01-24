package model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;      // ID
    private String name; // ユーザー名
    private String pass; // パスワード

    public User() {} //デフォルトコンストラクタ

    public User(int id, String name, String pass) { // DBから取得したユーザー用
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public User(String name, String pass) { // 登録・ログイン入力用
        this.name = name;
        this.pass = pass;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPass() { return pass; }
}
