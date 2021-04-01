package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;

public interface ReservatieRepository {
    long create(Reservatie reservatie);
    List<Reservatie> findAll();
    void delete(Reservatie klantId);
}
