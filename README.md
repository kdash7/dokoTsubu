# dokoTsubu

ユーザー登録・ログイン機能付きの簡易SNS（つぶやき）アプリです。  
Java / Servlet / JSP を用いた MVC 構成で、基本的な Web アプリケーションの流れを学習・実装しました。

---

## アプリ概要

- ユーザー登録・ログイン機能を備えた簡易SNS
- ログインユーザーがつぶやきを投稿・閲覧・検索可能
- JDBCを使用したデータベース操作
- アプリ起動時にDBを自動初期化

---

## 使用技術

### 言語・フレームワーク
- Java 17
- Servlet / JSP
- JDBC

### データベース
- H2 Database  
  - ローカル環境：fileモード  
  - デモ環境：memモード

### 開発環境・ツール
- Eclipse（Pleiades）
- Apache Tomcat 10.x
- Maven
- Git / GitHub

---

## 主な機能

- ユーザー登録
- ログイン / ログアウト
- つぶやき投稿
- つぶやき一覧表示
- つぶやき・ユーザー名検索

---

## 起動方法（ローカル実行）

### 前提環境
- Java 17
- Eclipse（Pleiades）
- Apache Tomcat 10.x

### 起動手順

1. このリポジトリをクローンします。
   ```bash
   git clone https://github.com/kdash7/dokoTsubu.git
Eclipseで「既存のMavenプロジェクト」としてインポートします。

Tomcat 10 をサーバーとして設定し、プロジェクトを追加します。

Eclipseからサーバーを起動します。

ブラウザで以下のURLにアクセスします。

http://localhost:8080/dokoTsubu/
ユーザー登録後、ログインして利用できます。

補足
ローカル環境では H2 Database（fileモード）を使用しています。

アプリケーション起動時に ServletContextListener により
データベースおよびテーブルが自動作成されます。

工夫した点・学んだこと
H2 Database の mem / file モード切り替えによる環境差異への対応

ServletContextListener を用いたDB初期化処理の実装

Maven・Tomcat・JDBC の連携時に発生するクラスローダ問題への対応

MVC構成を意識した責務分離（Servlet / Logic / DAO）

補足
本アプリは学習目的で作成したものであり、
面接時にはローカル環境での実行・コード説明を想定しています。
