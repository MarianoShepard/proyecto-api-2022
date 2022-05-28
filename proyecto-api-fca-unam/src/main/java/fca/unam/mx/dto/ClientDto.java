package fca.unam.mx.dto;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ClientDto {
    private Long id;
    private String name;
    private String apellido_paterno;
    private String apellido_materno;
    private String RFC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ColumnName("nombre")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnName("apellido_paterno")
    public String getApellidoPaterno() {
        return apellido_paterno;
    }

    public void setApellidoPaterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

   @ColumnName("apellido_materno")
    public String getApellidoMaterno() {
        return apellido_materno;
    }

    public void setApellidoMaterno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

   @ColumnName("RFC")
    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }


}
