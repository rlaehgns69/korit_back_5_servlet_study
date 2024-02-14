package config;

public interface DBConfig {
	 String URL = "jdbc:mysql://mysql-db.cp06k8cc266u.ap-northeast-2.rds.amazonaws.com/db_study";
	 String USERNAME = "aws";
	 String PASSWORD = "1q2w3e4!!";

	// public static final String URL = "";
	// 접근지정 해제 생성안하고 상수
	// String URL = ""; interface에 지정하면 public static final
}
