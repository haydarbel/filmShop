package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.repositories.KlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DefaultKlantService implements KlantService{
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public Optional<Klant> findKlantById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    public List<Klant> findAllKlanten() {
        return klantRepository.findAll();
    }

    @Override
    public List<Klant> fintKlantByLetters(String letters) {
        return klantRepository.findByLetters(letters);
    }
}
