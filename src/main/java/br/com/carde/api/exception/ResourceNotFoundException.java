package br.com.carde.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String resource;
    private final String id;

    public ResourceNotFoundException(String resource, String id) {
        super(resource + " not found with id: '" + id + "'");
        this.resource = resource;
        this.id = id;
    }

    public String getResource() { return resource; }
    public String getId()       { return id; }
}
