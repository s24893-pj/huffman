package com.example.NBP.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CurrencyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Unikalne ID rekordu")
    private Long id;

    @Schema(description = "Waluta dla której zostało wykonane zapytanie")
    private String currency;

    @Schema(description = "Ilość dni, które zostały wzięte pod uwagę")
    private int days;

    @Schema(description = "Obliczony średni kurs waluty")
    private double averageRate;

    @Schema(description = "Data i czas wykonania zapytania")
    private LocalDateTime queryDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public LocalDateTime getQueryDateTime() {
        return queryDateTime;
    }

    public void setQueryDateTime(LocalDateTime queryDateTime) {
        this.queryDateTime = queryDateTime;
    }
}
