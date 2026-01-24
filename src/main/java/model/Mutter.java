package model;

public class Mutter {
    private int id;          // ID
    private int userId;      // ユーザーID
    private String userName; // ユーザー名
    private String text;     // つぶやき内容

    // 投稿用
    public Mutter(int userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    // 表示用(JOIN結果)
    public Mutter(int id, int userId, String userName, String text) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.text = text;
    }

    // getter
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getText() { return text; }
}