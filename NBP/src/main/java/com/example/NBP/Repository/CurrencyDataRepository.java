package com.example.NBP.Repository;

import com.example.NBP.Model.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
}
