DROP FUNCTION IF EXISTS columnExists;;
CREATE FUNCTION columnExists(tableName varchar(64), columnName varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1)
    FROM information_schema.COLUMNS
    WHERE TABLE_NAME = tableName
      AND COLUMN_NAME = columnName
    INTO c;
    RETURN c > 0;
END;;
DROP FUNCTION IF EXISTS columnNotExists;;
CREATE FUNCTION columnNotExists(tableName varchar(64), columnName varchar(64)) RETURNS boolean
BEGIN
    RETURN NOT columnExists(tableName, columnName);
END;;

DROP FUNCTION IF EXISTS tableExists;;
CREATE FUNCTION tableExists(tableName varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1) FROM information_schema.TABLES WHERE TABLE_NAME = tableName INTO c;
    RETURN c > 0;
END;;

DROP FUNCTION IF EXISTS tableNotExists;;
CREATE FUNCTION tableNotExists(tableName varchar(64)) RETURNS boolean
BEGIN
    RETURN NOT tableExists(tableName);
END;;

DROP FUNCTION IF EXISTS testType;;
CREATE FUNCTION testType(tableName varchar(64), columnName varchar(64),
                         type varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1)
    FROM information_schema.COLUMNS
    WHERE TABLE_NAME = tableName
      AND COLUMN_NAME = columnName
      AND DATA_TYPE = type
    INTO c;
    RETURN c > 0;
END;;

DROP FUNCTION IF EXISTS nullable;;
CREATE FUNCTION nullable(tableName varchar(64), columnName varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1)
    FROM information_schema.COLUMNS
    WHERE TABLE_NAME = tableName
      AND COLUMN_NAME = columnName
      AND IS_NULLABLE = 'YES'
    INTO c;
    RETURN c > 0;
END;;

DROP FUNCTION IF EXISTS notNullable;;
CREATE FUNCTION notNullable(tableName varchar(64), columnName varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1)
    FROM information_schema.COLUMNS
    WHERE TABLE_NAME = tableName
      AND COLUMN_NAME = columnName
      AND IS_NULLABLE != 'YES'
    INTO c;
    RETURN c > 0;
END;;

DROP FUNCTION IF EXISTS indexExists;;
CREATE FUNCTION indexExists(tableName varchar(64), indexName varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1)
    FROM information_schema.STATISTICS
    WHERE TABLE_NAME = tableName
      AND INDEX_NAME = indexName
    INTO c;
    RETURN c > 0;
END;;

DROP FUNCTION IF EXISTS foreignKeyExists;;
CREATE FUNCTION foreignKeyExists(tableName varchar(64), foreignKeyName varchar(64)) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT COUNT(1)
    FROM information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_NAME = tableName
      AND CONSTRAINT_NAME = foreignKeyName
    INTO c;
    RETURN c > 0;
END;;

DROP FUNCTION IF EXISTS columnLengthEq;;
CREATE FUNCTION columnLengthEq(tableName varchar(64), columnName varchar(64),
                               length int) RETURNS boolean
BEGIN
    DECLARE c int;
    SELECT CHARACTER_MAXIMUM_LENGTH
    FROM information_schema.COLUMNS
    WHERE TABLE_NAME = tableName
      AND COLUMN_NAME = columnName
    INTO c;
    RETURN c = length;
END;;

DROP FUNCTION IF EXISTS columnAutoIncrement;;
CREATE FUNCTION columnAutoIncrement(tableName varchar(64), columnName varchar(64)) RETURNS boolean
BEGIN
    DECLARE r int;
    SELECT COUNT(0)
    FROM information_schema.COLUMNS
    WHERE TABLE_NAME = tableName
      AND COLUMN_NAME = columnName
      AND EXTRA = 'auto_increment'
    INTO r;
    RETURN r > 0;
END;;
