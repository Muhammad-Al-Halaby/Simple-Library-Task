package library.data;

public class QueryBuilder {
    public static String buildInsertQuery(String entityName, Object... values){
        String query = "INSERT INTO " + entityName + " VALUES (";
        for(int i = 0;i < values.length;i++){
            Object obj = values[i];

            if(obj instanceof String){
                query += "'" + String.valueOf(obj) + "'";
            }
            else if(obj instanceof Number){
                query += String.valueOf(obj);
            }

            if(i < (values.length - 1)){
                query += ", ";
            }
        }
        query += ")";
        return query;
    }

    public static String buildUpdateQuery(String entityName, Object... values){
        String query = "REPLACE INTO " + entityName + " VALUES (";
        for(int i = 0;i < values.length;i++){
            Object obj = values[i];

            if(obj instanceof String){
                query += "'" + String.valueOf(obj) + "'";
            }
            else if(obj instanceof Number){
                query += String.valueOf(obj);
            }

            if(i < (values.length - 1)){
                query += ", ";
            }
        }
        query += ")";
        return query;
    }

    public static String buildDeleteQuery(String entityName, int id){
        String query = "DELETE FROM " + entityName + " WHERE " + entityName + "_id = " + id;
        return query;
    }

    public static String buildGetByIdQuery(String entityName, int id){
        String query = "SELECT * FROM " + entityName + " WHERE " + entityName + "_id = " + id;
        return query;
    }

    public static String buildGetAllQuery(String entityName){
        String query = "SELECT * FROM " + entityName;
        return query;
    }

}