package fap_sports.integrador.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclamos")
public class Reclamo{
    // Identificador único del reclamo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long recId;

    // Motivo del reclamo
    @Column(name = "rec_motivo")
    private String recMotivo;

    // Descripción del reclamo
    @Column(name = "rec_descripcion")
    private String recDescripcion;

    // Solicitud del reclamo
    @Column(name = "rec_solicitud")
    private String recSolicitud;

    // Solicitud del reclamo
    @Column(name = "rec_respuesta")
    private String recRespuesta;

    // Estado del reclamo
    @Column(name = "rec_estado")
    private String recEstado;

    
    // Constructor
    public Reclamo(String recMotivo, String recDescripcion, String recSolicitud) {
        this.recMotivo = recMotivo;
        this.recDescripcion = recDescripcion;
        this.recSolicitud = recSolicitud;
    }
    
    public Reclamo() {
    }




    // Métodos get y set
    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getRecMotivo() {
        return recMotivo;
    }

    public void setRecMotivo(String recMotivo) {
        this.recMotivo = recMotivo;
    }

    public String getRecDescripcion() {
        return recDescripcion;
    }

    public void setRecDescripcion(String recDescripcion) {
        this.recDescripcion = recDescripcion;
    }

    public String getRecSolicitud() {
        return recSolicitud;
    }

    public void setRecSolicitud(String recSolicitud) {
        this.recSolicitud = recSolicitud;
    }

    public String getRecRespuesta() {
        return recRespuesta;
    }

    public void setRecRespuesta(String recRespuesta) {
        this.recRespuesta = recRespuesta;
    }

    public String getRecEstado() {
        return recEstado;
    }

    public void setRecEstado(String recEstado) {
        this.recEstado = recEstado;
    }
    
}

