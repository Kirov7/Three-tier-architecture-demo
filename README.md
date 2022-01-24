# Three-tier-architecture-demo
Based on three layer architecture console to achieve simple login registration function of small demo

Util包里的`JdbcUtil`类中的`url`需要自行更改

MySQL数据库中创建数据表结构如下

```sql
CREATE TABLE IF NOT EXISTS `user`(
	`username` VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '用户名',
	`password` VARCHAR(255) NOT NULL COMMENT '用户密码',
	`salt` VARCHAR(50) NOT NULL COMMENT '密码盐' 
)ENGINE=INNODB CHARSET=UTF8 COMMENT '用户表';
```
