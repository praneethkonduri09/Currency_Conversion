package org.example.repo;
import org.example.entity.entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository

public interface repository extends JpaRepository<entity, Long>{
        Optional<entity> findByBaseCurrency(String baseCurrency);
    }

