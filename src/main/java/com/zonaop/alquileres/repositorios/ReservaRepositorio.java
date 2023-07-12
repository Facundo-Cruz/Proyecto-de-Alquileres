package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Reserva;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, String> {  

    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId")
    public List<Reserva> buscarPorCliente(@Param("clienteId") String clienteId);
    
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId AND r.estado = 'Activa'")
    public List<Reserva> buscarPorClienteActiva(@Param("clienteId") String clienteId);
    
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId AND r.estado = 'Pendiente'")
    public List<Reserva> buscarPorClientePendiente(@Param("clienteId") String clienteId);
    
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId AND r.estado = 'Cancelada'")
    public List<Reserva> buscarPorClienteCancelada(@Param("clienteId") String clienteId);
    
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId AND r.estado = 'Finalizada'")
    public List<Reserva> buscarPorClienteFinalizada(@Param("clienteId") String clienteId);
    
    @Query("SELECT r FROM Reserva r WHERE r.propietario.id = :propietarioId")
    public List<Reserva> buscarPorPropietario(@Param("propietarioId") String propietarioId);
    
    @Query("SELECT r FROM Reserva r WHERE r.propietario.id = :propietarioId AND r.estado = 'Activa'")
    public List<Reserva> buscarPorPropietarioActiva(@Param("propietarioId") String propietarioId);
    
    @Query("SELECT r FROM Reserva r WHERE r.propietario.id = :propietarioId AND r.estado = 'Pendiente'")
    public List<Reserva> buscarPorPropietarioPendiente(@Param("propietarioId") String propietarioId);
    
    @Query("SELECT r FROM Reserva r WHERE r.propietario.id = :propietarioId AND r.estado = 'Cancelada'")
    public List<Reserva> buscarPorPropietarioCancelada(@Param("propietarioId") String propietarioId);
    
    @Query("SELECT r FROM Reserva r WHERE r.propietario.id = :propietarioId AND r.estado = 'Finalizada'")
    public List<Reserva> buscarPorPropietarioFinalizada(@Param("propietarioId") String propietarioId);
    
    @Query("SELECT r FROM Reserva r WHERE r.propiedad.id = :propiedadId")
    public List<Reserva> buscarPorPropiedad(@Param("propiedadId") String propiedadId);

    @Query(value = "SELECT r.fecha_desde FROM propiedad p JOIN reserva r ON p.id = r.propiedad_id WHERE r.estado = 'Activa' AND p.id = :propiedadId", nativeQuery = true)
    public List<Date> buscarFechasDesde(@Param("propiedadId") String propiedadId);

    @Query(value = "SELECT r.fecha_hasta FROM propiedad p JOIN reserva r ON p.id = r.propiedad_id WHERE r.estado = 'Activa' AND p.id = :propiedadId", nativeQuery = true)
    public List<Date> buscarFechasHasta(@Param("propiedadId") String propiedadId);

    @Query("SELECT r FROM Reserva r WHERE r.propiedad.id = :propiedadId AND r.estado = 'Activa' AND r.fechaDesde <= :fechaHasta AND r.fechaHasta >= :fechaDesde")
    public List<Reserva> verificarDisponibilidad(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta, @Param("propiedadId") String propiedadId);
    
    @Query("SELECT COUNT(r) FROM Reserva r")
    public int contarReservas();
    
}
