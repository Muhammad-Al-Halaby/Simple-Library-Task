package library.pojo;

public abstract class LibraryEntity {
    private String entityName;

    public LibraryEntity(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
